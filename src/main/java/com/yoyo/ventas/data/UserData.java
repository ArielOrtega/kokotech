package com.yoyo.ventas.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.yoyo.ventas.domain.Address;
import com.yoyo.ventas.domain.Client;
import com.yoyo.ventas.domain.ContactInformation;
import com.yoyo.ventas.domain.Employee;
import com.yoyo.ventas.domain.Role;

@Configuration
@EnableWebSecurity
@Repository
public class UserData {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private DataSource dataSource;
	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	
	public Client findByEmail(String email) {
		String selectSql = "Select c.id_end_user, c.is_suscribed, c.last_name,"
				+ " c.first_name, c.username , c.password, r.id_rol, r.type_name"
				+ " as role_name from client c, userrole r" + 
				" where c.id_rol = r.id_rol and c.username = '" + email + "'";
		List<Client> clients = jdbcTemplate.query(selectSql, new RowMapper<Client>() {

			@Override
			public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
				Client client = new Client();
				client.setClienteId(rs.getInt("id_end_user"));
				client.setFirstName(rs.getString("first_name"));
				client.setLastName(rs.getString("last_name"));
				client.setUsername(rs.getString("username"));
				client.setPassword(rs.getString("password"));
				client.setSuscribed(rs.getBoolean("is_suscribed"));
				do {
					if(rs.getString("username").equals(client.getUsername())) {
						Role role = new Role();
						role.setRolId(rs.getInt("id_rol"));
						role.setTypeName(rs.getString("role_name"));
						client.getRoles().add(role);
					}
				}while(rs.next());
				return client;
			}//mapRow
		});
		return (clients.isEmpty()? new Client() : clients.get(0));
	}//end findByEmail
	
	public boolean findByPhone(String phone) {
		String selectSql = "select id_contact_infromation from contactinformation where phone = '" + phone + "'"; 
		List<ContactInformation> contactI = jdbcTemplate.query(selectSql, new RowMapper<ContactInformation>() {

			@Override
			public ContactInformation mapRow(ResultSet rs, int rowNum) throws SQLException {
				ContactInformation contactInformation = new ContactInformation();
				contactInformation.setContactInfoId(rs.getInt("id_contact_infromation"));
				return contactInformation;
			}//mapRow
		});
		return (contactI.isEmpty()? false : true);
	}//end findByPhone
	
	public boolean findEmail(String email) {
		String selectSql = "select id_address from client c, address a where a.address_line_1 = '" + email + 
				"' or a.address_line_2 = '" + email + "'" + " or (c.username = '" + email + "' and c.id_end_user = a.client_id )"; 
		List<Address> addressList = jdbcTemplate.query(selectSql, new RowMapper<Address>() {

			@Override
			public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
				Address address = new Address();
				address.setAddressId(rs.getInt("id_address"));
				return address;
			}//mapRow
		});
		return (addressList.isEmpty()? false : true);
	}//end findEmail
	
	
	public boolean findFax(String fax) {
		String selectSql = "select id_contact_infromation from contactinformation where fax = '" + fax + "'"; 
		List<ContactInformation> contactI = jdbcTemplate.query(selectSql, new RowMapper<ContactInformation>() {

			@Override
			public ContactInformation mapRow(ResultSet rs, int rowNum) throws SQLException {
				ContactInformation contactInformation = new ContactInformation();
				contactInformation.setContactInfoId(rs.getInt("id_contact_infromation"));
				return contactInformation;
			}//mapRow
		});
		return (contactI.isEmpty()? false : true);
	}//end findByPhone
	
	
	public Employee findEmployeeByEmail(String email) {
		String selectSql = "Select c.id_employee, c.last_name, c.first_name, c.username , c.password, r.id_rol, r.type_name as role_name from employee c, userrole r" + 
				" where c.rol_id = r.id_rol and c.username = '" + email + "'";
		List<Employee> employees = jdbcTemplate.query(selectSql, new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee employee  = new Employee();
				employee .setEmployeeId(rs.getInt("id_employee"));
				employee .setFirstName(rs.getString("first_name"));
				employee .setLastName(rs.getString("last_name"));
				employee .setUsername(rs.getString("username"));
				employee .setPassword(rs.getString("password"));
				do {
					if(rs.getString("username").equals(employee.getUsername())) {
						Role role = new Role();
						role.setRolId(rs.getInt("id_rol"));
						role.setTypeName(rs.getString("role_name"));
						employee.getRoles().add(role);
					}
				}while(rs.next());
				return employee;
			}//mapRow
		});
		return (employees.isEmpty()? new Employee() : employees.get(0));
	}//end findByEmail
	
	//Insertar un cliente en la base de datos
	public void insertClient (Address address, ContactInformation contactInformation, Client client) {
		
		Connection conexion = null;

		try {
			conexion = dataSource.getConnection();
			conexion.setAutoCommit(false);
			
			//Insert Client
			CallableStatement cstmt = conexion.prepareCall("CALL yoyoInsertClient(?,?,?,?,?,?,?)");
			cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
			cstmt.setString(2, client.getFirstName());
			cstmt.setString(3, client.getLastName());
			cstmt.setString(4, client.getUsername());
			cstmt.setString(5, passwordEncoder.encode(client.getPassword()));
			cstmt.setBoolean(6, client.isSuscribed());
			cstmt.setInt(7, 2);
			cstmt.executeUpdate();
			client.setClienteId(cstmt.getInt(1));
			System.out.println(client.getClienteId());
			
			//Insert Address
			CallableStatement cstmt2 = conexion.prepareCall("CALL yoyoInsertAddress(?,?,?,?,?,?,?)");
			cstmt2.registerOutParameter(1, java.sql.Types.INTEGER);
			cstmt2.setString(2, client.getUsername());
			if(address.getAddressLine2() == "") {
				address.setAddressLine2(null);
			}
			cstmt2.setString(3, address.getAddressLine2());
			cstmt2.setString(4, address.getCity());
			cstmt2.setString(5, address.getState());
			cstmt2.setString(6, address.getPostalCode());
			cstmt2.setInt(7, client.getClienteId());
			cstmt2.executeUpdate();
			address.setAddressId(cstmt2.getInt(1));
			System.out.println(address.getAddressId());
			
			//Insert ContactInformation
			CallableStatement cstmt3 = conexion.prepareCall("CALL yoyoInsertContactInformation(?,?,?,?,?)");
			cstmt3.registerOutParameter(1, java.sql.Types.INTEGER);
			cstmt3.setString(2, contactInformation.getPhone());
			if(contactInformation.getPhone2().isEmpty()) {
				contactInformation.setPhone2(null);
			}
			cstmt3.setString(3, contactInformation.getPhone2());
			if(contactInformation.getFax().isEmpty()) {
				contactInformation.setFax(null);
			}
			cstmt3.setString(4, contactInformation.getFax());
			cstmt3.setInt(5, client.getClienteId());
			cstmt3.executeUpdate();
			contactInformation.setContactInfoId(cstmt3.getInt(1));
			System.out.println(contactInformation.getContactInfoId());
			
			client.setContactInformation(contactInformation);
			
			System.out.println(client.getClienteId());
			System.out.println(client.getFirstName());
			System.out.println(client.getLastName());
			System.out.println(client.getPassword());
			System.out.println(client.getUsername());
			System.out.println(address.getAddressId());
			System.out.println(address.getAddressLine());
			System.out.println(address.getAddressLine2());
			System.out.println(address.getCity());
			System.out.println(address.getPostalCode());
			System.out.println(address.getState());
			System.out.println(contactInformation.getContactInfoId());
			System.out.println(contactInformation.getPhone());
			System.out.println(contactInformation.getPhone2());
			System.out.println(contactInformation.getFax());
			
			conexion.commit();
			
		} catch (SQLException e) {
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		} finally {
			if (conexion != null) {
				try {
					conexion.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
		
	}//end method insertClient

}//end class