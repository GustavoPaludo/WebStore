package com.server.core.product.model;

import java.io.Serializable;
import java.util.List;

public class ProductPageModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ProductInfoModel> products;
	private Long totalItems;

	public List<ProductInfoModel> getProducts() {
		return products;
	}

	public void setProducts(List<ProductInfoModel> products) {
		this.products = products;
	}

	public Long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(Long totalItems) {
		this.totalItems = totalItems;
	}
}
