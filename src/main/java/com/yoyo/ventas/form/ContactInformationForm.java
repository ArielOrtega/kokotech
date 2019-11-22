package com.yoyo.ventas.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ContactInformationForm {
	
	private int contactInfoId;
	@NotNull
	@Size(min=8, max=50)
	private String phone;
	private String phone2;
	private String fax;
	
	public ContactInformationForm() {
		
	}

	public int getContactInfoId() {
		return contactInfoId;
	}

	public void setContactInfoId(int contactInfoId) {
		this.contactInfoId = contactInfoId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
}