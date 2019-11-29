package com.yoyo.ventas.domain;

import javax.xml.bind.annotation.XmlRootElement;

public class Tax {
	
	private int idTax;
	private int vat;
	private int salesTax;
	private int shippingTax;
	private int discountRate;
	
	
	public Tax() {
		
	}
	
	public int getIdTax() {
		return idTax;
	}



	public void setIdTax(int idTax) {
		this.idTax = idTax;
	}



	public int getVat() {
		return vat;
	}


	public void setVat(int vat) {
		this.vat = vat;
	}


	public int getSalesTax() {
		return salesTax;
	}


	public void setSalesTax(int salesTax) {
		this.salesTax = salesTax;
	}


	public int getShippingTax() {
		return shippingTax;
	}


	public void setShippingTax(int shippingTax) {
		this.shippingTax = shippingTax;
	}


	public int getDiscountRate() {
		return discountRate;
	}


	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}
	
	
	
}
