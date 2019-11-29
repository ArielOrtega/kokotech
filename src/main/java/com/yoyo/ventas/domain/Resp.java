package com.yoyo.ventas.domain;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.JsonNode;

@JsonRootName(value = "resp")
public class Resp {
	
	
	private String clave;
	private String xml;
	
	public Resp() {
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	@Override
	public String toString() {
		return "Resp [clave=" + clave + ", xml=" + xml + "]";
	}

	
}
