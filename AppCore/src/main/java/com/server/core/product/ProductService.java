package com.server.core.product;

import com.server.core.product.model.ProductInfoModel;
import com.server.core.product.model.ProductPageModel;

public interface ProductService {

	public ProductPageModel listProducts(int page, int size);

	public ProductInfoModel createProduct(ProductInfoModel productInfoModel);

	public ProductInfoModel updateProduct(ProductInfoModel productInfoModel);

	public void deleteProduct(Long id);
}
