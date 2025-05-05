package com.server.core.product;

import java.util.List;

import com.server.models.ProductInfoModel;

public interface ProductService {

	public List<ProductInfoModel> listProucts(int page, int size);
}
