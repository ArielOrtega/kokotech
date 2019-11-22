package com.yoyo.ventas.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClientForm {
	
	private int clienteId;
	@NotNull
	@Size(min=8, max=50)
	private String username;
	@NotNull
	@Size(min=8, max=50)
	private String password;
	@NotNull
	@Size(min=8, max=50)
	private String confirmPassword;
	@NotNull
	@Size(min=2, max=50)
	private String lastName;
	@NotNull
	@Size(min=2, max=50)
	private String firstName;
	
	public ClientForm() {
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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
	
}//end