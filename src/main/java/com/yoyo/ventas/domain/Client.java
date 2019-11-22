package com.yoyo.ventas.domain;

import java.util.ArrayList;
import java.util.List;

public class Client {
	private int clienteId;
	private String username;
	private String password;
	private String lastName;
	private String firstName;
	private boolean isSuscribed;
	private List<Role> roles;
	private ContactInformation contactInformation;
	private List<Address> addresses;
	
	public Client() {
		contactInformation = new ContactInformation();
		addresses = new ArrayList<>();
		roles = new ArrayList<>();
	}

	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public boolean isSuscribed() {
		return isSuscribed;
	}

	public void setSuscribed(boolean isSuscribed) {
		this.isSuscribed = isSuscribed;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public ContactInformation getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
	
}
