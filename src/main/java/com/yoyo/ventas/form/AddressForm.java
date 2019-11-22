package com.yoyo.ventas.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddressForm {
	
	private int addressId;
	@NotNull
	@Size(min=2, max=50)
	private String addressLine;
	private String addressLine2;
	@NotNull
	@Size(min=2, max=50)
	private String city;
	@NotNull
	@Size(min=2, max=50)
	private String state;
	@NotNull
	@Size(min=2, max=50)
	private String postalCode;
	
	public AddressForm() {
		
	}

	public String getAddressLine() {
		return addressLine;
	}
	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
}