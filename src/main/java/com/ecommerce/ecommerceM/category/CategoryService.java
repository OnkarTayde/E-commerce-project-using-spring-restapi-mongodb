package com.ecommerce.ecommerceM.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.ecommerce.ecommerceM.brand.BrandModel;
import com.ecommerce.ecommerceM.brand.BrandRepository;
import com.ecommerce.ecommerceM.exception.AlreadyExistsException;
import com.ecommerce.ecommerceM.exception.BadRequestException;
import com.ecommerce.ecommerceM.exception.NotFoundException;
import com.ecommerce.ecommerceM.exception.OkException;
import com.ecommerce.ecommerceM.product.ProductModel;
import com.ecommerce.ecommerceM.product.ProductRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepo categoryRepository;

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private ProductRepository productRepository;

	// TO add category in db
	public CategoryModel addCategory(CategoryModel categoryModel) {

		List<CategoryModel> modelList = categoryRepository.findAll();
		CategoryModel categoryModel1 = new CategoryModel();

		for (CategoryModel c : modelList) {
			System.out.println("inside for");
			if (c.getCategory_name().equals(categoryModel.getCategory_name())) {
				System.out.println("inside if" + c.getCategory_name());

				throw new AlreadyExistsException(
						"Category already exists with name :- " + categoryModel.getCategory_name());

			} else if (categoryModel.getCategory_name().isEmpty()) {
				throw new BadRequestException("Category Name Not be Empty");

			} else if (categoryModel.getCategory_name().equals(" ")) {
				throw new BadRequestException("Category Name Not be Space");
			} else if (categoryModel.getCategory_name().contains("null")) {
				throw new BadRequestException("Category Name Not be null");
			}
		}
		categoryModel1 = categoryRepository.save(categoryModel);

		return categoryModel1;
	}

	// To get AllCategory
	public List<CategoryModel> getAllCategory() {
		List<CategoryModel> clist = new ArrayList<>();
		categoryRepository.findAll().forEach(clist::add);

		return clist;
	}

	// get Category By Id
//	public CategoryModel getCategoryById(int id)
//	{
//		CategoryModel cat =categoryRepository.findById(id).get();
//		
//		return cat;
//	}

	// get Category By Id
	public List<Object> getCategoryById(int id) {

		List<Object> mylist = new ArrayList<>();

		CategoryModel categoryModel = categoryRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Category Not Exist With Id :- " + id));

		mylist.add(categoryModel);
		List<BrandModel> bList = brandRepository.findAll();
		List<ProductModel> pList = productRepository.findAll();
		for (BrandModel b : bList) {
			if (b.getCategory_id() == id) {
				mylist.add(b);
				for (ProductModel p : pList) {
					if (p.getBrandId() == b.getBrand_id()) {
						mylist.add(p);
					}
				}
			}
		}

		return mylist;
	}

	public List<Object> getCategoryByName(String category_name) {
		List<Object> list = new ArrayList<>();

		List<CategoryModel> cList = categoryRepository.findAll();
		List<BrandModel> bList = brandRepository.findAll();
		List<ProductModel> pList = productRepository.findAll();

		for (CategoryModel c : cList) {
			if (c.getCategory_name().equals(category_name)) {
				list.add(c);
				for (BrandModel b : bList) {
					if (c.getId() == b.getCategory_id()) {
						list.add(b);
						for (ProductModel p : pList) {
							if (b.getBrand_id() == p.getBrandId()) {
								list.add(p);
							}
						}
					}
				}
			}
		}

		if (list.isEmpty()) {

			throw new NotFoundException("Category Not Exist With Name :- " + category_name);

		}

		return list;
	}

	// To delete categoryByid With brand and product
	public void deleteCategoryById(int id) {
		// categoryRepository.deleteById(id);

		List<CategoryModel> modelList = categoryRepository.findAll();
		List<BrandModel> brandModelsList = brandRepository.findAll();
		List<ProductModel> productModels = productRepository.findAll();

		for (CategoryModel c : modelList) {
			if (c.getId() == id) {
				for (BrandModel b : brandModelsList) {
					if (b.getBrand_id() == id) {
						for (ProductModel p : productModels) {
							if (p.getCategoryId() == id) {

								productRepository.deleteById(p.getProduct_id());
								brandRepository.deleteById(b.getBrand_id());
								categoryRepository.deleteById(id);

							}
						}
					}
				}
			}
		}

		throw new OkException("The request was fulfilled.");

	}
}
