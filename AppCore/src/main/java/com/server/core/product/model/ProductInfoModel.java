package com.server.core.product.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.server.core.product.dao.Product;

public class ProductInfoModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String description;
	private Integer quantity;
	private Double price;
	private String imageUrl;

	public ProductInfoModel() {}

	public ProductInfoModel(Long id, String name, String description, Integer quantity, Double price, String imageUrl) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.imageUrl = imageUrl;
	}

	public ProductInfoModel from(Product product) {
		if (product == null) {
			return null;
		}

		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.quantity = product.getQuantity();
		this.price = product.getPrice();
		this.imageUrl = product.getImageUrl();

		return this;
	}

	public List<ProductInfoModel> fromList(List<Product> productList) {
		List<ProductInfoModel> productModelList = new ArrayList<>();

		if (CollectionUtils.isEmpty(productList)) {
			return productModelList;
		}

		for (Product product : productList) {
			ProductInfoModel model = new ProductInfoModel();

			model.setId(product.getId());
			model.setName(product.getName());
			model.setDescription(product.getDescription());
			model.setQuantity(product.getQuantity());
			model.setPrice(product.getPrice());
			model.setImageUrl(product.getImageUrl());

			productModelList.add(this);
		}

		return productModelList;
	}

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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
