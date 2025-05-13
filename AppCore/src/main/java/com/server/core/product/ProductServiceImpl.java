package com.server.core.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.server.core.product.dao.Product;
import com.server.core.product.dao.ProductDAO;
import com.server.core.product.model.ProductInfoModel;
import com.server.core.product.model.ProductPageModel;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductDAO productDAO;

	@Override
	public ProductPageModel listProducts(int page, int size) {
		ProductPageModel productPageModel = new ProductPageModel();
		List<Product> productList = this.productDAO.listByPaging(page, size);

		List<ProductInfoModel> productModelList = new ArrayList<>();
		if (!CollectionUtils.isEmpty(productList)) {
			productModelList = new ProductInfoModel().fromList(productList);
		}

		productPageModel.setProducts(productModelList);
		productPageModel.setTotalItems(null);

		return productPageModel;
	}

	@Override
	public ProductInfoModel createProduct(ProductInfoModel productInfoModel) {
		Product product = new Product();
		product.setDescription(productInfoModel.getDescription());
		product.setImageUrl(productInfoModel.getImageUrl());
		product.setLastUpdate(new Date());
		product.setName(productInfoModel.getName());
		product.setPrice(productInfoModel.getPrice());
		product.setQuantity(productInfoModel.getQuantity());

		this.productDAO.save(product);

		return new ProductInfoModel().from(product);
	}

	@Override
	public ProductInfoModel updateProduct(ProductInfoModel productInfoModel) {
		Product product = new Product();
		product.setId(productInfoModel.getId());
		product.setDescription(productInfoModel.getDescription());
		product.setImageUrl(productInfoModel.getImageUrl());
		product.setLastUpdate(new Date());
		product.setName(productInfoModel.getName());
		product.setPrice(productInfoModel.getPrice());
		product.setQuantity(productInfoModel.getQuantity());

		this.productDAO.save(product);

		return new ProductInfoModel().from(product);
	}

	@Override
	public void deleteProduct(Long id) {
		this.productDAO.delete(id);
	}

	@Autowired
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
}
