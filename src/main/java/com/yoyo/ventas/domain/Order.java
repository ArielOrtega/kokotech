package com.yoyo.ventas.domain;

public class Order {
	private int orderId;
	private String orderDate;
	private String shipData;
	private int trackingNum;
	private float totalValue;
	private boolean status;
	private Client client;
	
	public Order() {
		client = new Client();
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getShipData() {
		return shipData;
	}

	public void setShipData(String shipData) {
		this.shipData = shipData;
	}

	public int getTrackingNum() {
		return trackingNum;
	}

	public void setTrackingNum(int trackingNum) {
		this.trackingNum = trackingNum;
	}

	public float getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(float totalValue) {
		this.totalValue = totalValue;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	
}