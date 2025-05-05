package com.server.models;

import java.io.Serializable;

public class ProductInfoModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String descriptio;
	private Integer quantity;
	private Double value;

	public ProductInfoModel() {}

	public ProductInfoModel(Integer id, String name, String descriptio, Integer quantity, Double value) {
		this.id = id;
		this.name = name;
		this.descriptio = descriptio;
		this.quantity = quantity;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescriptio() {
		return descriptio;
	}

	public void setDescriptio(String descriptio) {
		this.descriptio = descriptio;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
