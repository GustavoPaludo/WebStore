package com.server.core.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.server.core.product.dao.Product;
import com.server.core.product.dao.ProductDAO;
import com.server.core.product.vo.ProductInfoModel;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductDAO productDAO;

	@Override
	public List<ProductInfoModel> listProucts(int page, int size) {
		List<Product> productList = this.productDAO.listByPaging(page, size); 

		List<ProductInfoModel> productModelList = new ArrayList<>();
		if(!CollectionUtils.isEmpty(productList)) {
			productModelList = new ProductInfoModel().fromList(productList);
		}

		return productModelList;
	}

	@Autowired
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
}
