package com.yoyo.ventas.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yoyo.ventas.data.UserData;
import com.yoyo.ventas.domain.Client;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDataTest {
	
	@Autowired
	private UserData userData;

	@Test
	public void findByEmailTest() {
		Client cliente = new Client();
		cliente = userData.findByEmail("arielortega@gmail.com");
		System.out.println(cliente.getClienteId());
		System.out.println(cliente.getFirstName());
		System.out.println(cliente.getLastName());
		System.out.println(cliente.getPassword());
		System.out.println(cliente.getUsername());
		System.out.println(cliente.getAddresses());
		System.out.println(cliente.getContactInformation().getContactInfoId());
	}//end userDataTest
	
	
	@Test
	public void insertClientTest(){
		Client client = new Client();
		client.setFirstName("Dilan");
		client.setLastName("Angulo Ruiz");
		client.setSuscribed(true);
		client.setUsername("dilan0400ar@gmail.com");
		client.setPassword("dilan");
		userData.insertClient(null, null, client);
	
	}

}//end testClass
