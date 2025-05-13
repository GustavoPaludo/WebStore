package com.server.controllers.authenticated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.server.core.product.ProductService;
import com.server.core.product.model.ProductInfoModel;

@RestController
@RequestMapping(value = "/authenticated/product")
public class ProductWSController {

	private ProductService productService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<ProductInfoModel> createProduct(@RequestBody ProductInfoModel productInfoModel) {
		ProductInfoModel productModel = this.productService.createProduct(productInfoModel);

		return new ResponseEntity<ProductInfoModel>(productModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<ProductInfoModel> getSeries(@RequestBody ProductInfoModel productInfoModel) {
		ProductInfoModel productModel = this.productService.updateProduct(productInfoModel);

		return new ResponseEntity<ProductInfoModel>(productModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<Void> getSeries(@RequestBody Long id) {
		this.productService.deleteProduct(id);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
}
