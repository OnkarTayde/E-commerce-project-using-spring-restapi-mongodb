package com.ecommerce.ecommerceM.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.ecommerceM.brand.*;
import com.ecommerce.ecommerceM.category.*;
import com.ecommerce.ecommerceM.exception.AlreadyExistsException;
import com.ecommerce.ecommerceM.exception.BadRequestException;
import com.ecommerce.ecommerceM.exception.NotFoundException;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepo categoryRepository;

	@Autowired
	BrandRepository brandRepository;

	// getAllProduct
	public List<ProductModel> getAllProduct() {
		List<ProductModel> productList = new ArrayList<ProductModel>();
		productRepository.findAll().forEach(productList::add);
		return productList;

	}

	// insert product
	public ProductModel InsertProduct(ProductModel productModel) {
		List<ProductModel> productModels = productRepository.findAll();
		List<BrandModel> brandModels = brandRepository.findAll();
		ProductModel brandMd = new ProductModel();

		for (BrandModel b : brandModels) {
			if (productModel.getBrandId() == b.getBrand_id() && productModel.getCategoryId() == b.getCategory_id()) {
				for (ProductModel p : productModels)
					if (productModel.getProductName().equals(p.getProductName())) {
						throw new AlreadyExistsException(
								"Product already exists with name :-" + productModel.getProductName());
					} else if (productModel.getProductName().isEmpty() && productModel.getProductName().equals(" ")
							&& productModel.getProductName().contains("null")) {
						throw new BadRequestException("Product Name Not be Empty");
					} else if (productModel.getProductDescription().isEmpty()) {
						throw new BadRequestException("Product Description Not be Empty");
					}

			} else if (productModel.getBrandId() != b.getBrand_id()) {
				throw new NotFoundException("Brand Not Found with id :-" + productModel.getBrandId());
			} else if (productModel.getCategoryId() != b.getBrand_id()) {

				throw new NotFoundException("Category Not Found with id :-" + productModel.getCategoryId());
			}
		}

		brandMd = productRepository.save(productModel);

		return brandMd;
	}

	// getProductById
	public ProductModel getProductById(int id) {
		ProductModel pro = productRepository.findById(id).get();
		return pro;
	}

	// deleteProductById
	public void deleteProductById(int id) {
		productRepository.deleteById(id);

	}
}
