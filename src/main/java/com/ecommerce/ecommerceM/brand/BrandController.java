package com.ecommerce.ecommerceM.brand;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.ecommerceM.autoidgenerator.SequenceGeneratorService;

@RestController
public class BrandController {

	@Autowired
	SequenceGeneratorService sequenceGeneratorService;
	@Autowired
	BrandService brandService;

	@PostMapping(value = "/addBrand")
	public BrandModel insertIntoTable(@RequestBody BrandModel b) {
		b.setBrand_id(sequenceGeneratorService.getSequenceNumber(BrandModel.SEQUENCE_NAME));
		BrandModel brandModel = brandService.InsertBrand(b);
		return brandModel;

	}

	@GetMapping(value = "/getAllBrands")
	public List<BrandModel> getAllBrands() {
		return brandService.getAllBrand();

	}

	@GetMapping("/getBrandByName/{Brand_name}")
	public List<Object> getBrandByName(@PathVariable("Brand_name") String Brand_name) {
		List<Object> list = brandService.getBrandByName(Brand_name);
		return list;
	}

	@DeleteMapping("/deleteBrandById/{brand_id}")
	public void deleteBrandById(@PathVariable("brand_id") int id) {

		brandService.deleteBrandById(id);
	}
}
