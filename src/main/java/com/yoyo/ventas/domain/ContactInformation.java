package com.yoyo.ventas.domain;

public class ContactInformation {
	private int contactInfoId;
	private String phone;
	private String phone2;
	private String fax;
	
	public ContactInformation() {
		
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
