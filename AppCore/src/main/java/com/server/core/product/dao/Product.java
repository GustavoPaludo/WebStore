package com.server.core.product.dao;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCT")
@NamedQueries({
    @NamedQuery(name = Product.FIND_ALL, query = "SELECT p FROM Product p"),
    @NamedQuery(name = Product.FIND_BY_ID, query = "SELECT p FROM Product p WHERE p.id = :id")
})
public class Product {

	public static final String FIND_ALL = "Product.FIND_ALL";
	public static final String FIND_BY_ID = "Product.FIND_BY_ID";

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME", nullable = false, length = 100)
	private String name;

	@Column(name = "DESCRIPTION", nullable = false, length = 255)
	private String description;

	@Column(name = "QUANTITY", nullable = false)
	private Integer quantity;

	@Column(name = "PRICE", nullable = false)
	private Double price;

	@Column(name = "LAST_UPDATE", nullable = false)
	private Date lastUpdate;

	@Column(name = "IMAGE_URL", nullable = true, length = 255)
	private String imageUrl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
