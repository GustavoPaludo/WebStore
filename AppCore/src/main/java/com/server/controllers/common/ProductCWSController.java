package com.server.controllers.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.server.core.product.ProductService;
import com.server.core.product.model.ProductInfoModel;

@RestController
@RequestMapping(value = "/common/product")
public class ProductCWSController {

	private ProductService productService;

	@RequestMapping(value = "/list-by-paging", method = RequestMethod.GET)
	public ResponseEntity<List<ProductInfoModel>> getSeries(
			@RequestParam(required = true, value = "page") int page,
			@RequestParam(required = true, value = "size") int size) {

		List<ProductInfoModel> productModelList = this.productService.listProucts(page, size);

		if (CollectionUtils.isEmpty(productModelList)) {
			return new ResponseEntity<List<ProductInfoModel>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<ProductInfoModel>>(productModelList, HttpStatus.OK);
	}

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
}
