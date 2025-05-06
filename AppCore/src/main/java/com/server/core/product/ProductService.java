package com.server.core.product;

import java.util.List;

import com.server.core.product.vo.ProductInfoModel;

public interface ProductService {

	public List<ProductInfoModel> listProucts(int page, int size);
}
