package com.ecommerce.ecommerceM.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "product")
public class ProductModel {
	@Transient
	public static final String SEQUENCE_NAME = "product_sequence";

	@Id
	private int product_id;

	private String productImage;
	private String productName;
	private String productDescription;
	private long productPrice;
	private int brandId;
	private int categoryId;

}
