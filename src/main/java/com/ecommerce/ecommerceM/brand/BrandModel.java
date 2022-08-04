package com.ecommerce.ecommerceM.brand;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "brand")
public class BrandModel {
	@Transient
	public static final String SEQUENCE_NAME = "brand_sequence";

	@Id
	private int brand_id;

	private String brand_name;

	private int category_id;

}
