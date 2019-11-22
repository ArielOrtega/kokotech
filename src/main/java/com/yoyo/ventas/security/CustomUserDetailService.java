package com.yoyo.ventas.security;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoyo.ventas.data.UserData;
import com.yoyo.ventas.domain.Client;
import com.yoyo.ventas.domain.Employee;

@Service
@Transactional
public class CustomUserDetailService implements UserDetailsService{
	@Autowired
	private UserData userData;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Client client = userData.findByEmail(username);
		Employee employee = userData.findEmployeeByEmail(username);
		
		if(client.getClienteId() == 0 && employee.getEmployeeId() == 0)
			throw new UsernameNotFoundException("Email "+ username + "not found");
		else if(employee.getEmployeeId() == 0)
			return new org.springframework.security.core.userdetails.User(client.getUsername(), client.getPassword(), getAuthorities(client));
		else
			return new org.springframework.security.core.userdetails.User(employee.getUsername(), employee.getPassword(), getAuthoritiesE(employee));
	}
	
	private static Collection<? extends GrantedAuthority> getAuthorities(Client user){
		String[] userRoles = user.getRoles()
				.stream()
				.map((role)-> role.getTypeName())
				.toArray(String[]::new);
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
		return authorities;
	}
	
	private static Collection<? extends GrantedAuthority> getAuthoritiesE(Employee employee){
		String[] userRoles = employee.getRoles()
				.stream()
				.map((role)-> role.getTypeName())
				.toArray(String[]::new);
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
		return authorities;
	}
}
