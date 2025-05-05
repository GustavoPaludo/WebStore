package com.server.core.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.server.models.ProductInfoModel;

@Service
public class ProductServiceImpl implements ProductService {

	@Override
	public List<ProductInfoModel> listProucts(int page, int size) {
		List<ProductInfoModel> productModelList = new ArrayList<ProductInfoModel>();

		for (int i = 0; i < 20; i++) {
			ProductInfoModel productModel = new ProductInfoModel(
					i,
					"Produto " + i,
					"Descrição do produto " + i,
					i * 10,
					1 * 5d
			);

			productModelList.add(productModel);
		}

		int start = (page - 1) * size;
		int end = Math.min(start + size, productModelList.size());

		if (start >= productModelList.size()) {
			return new ArrayList<>();
		}

		return productModelList.subList(start, end);
	}
}
