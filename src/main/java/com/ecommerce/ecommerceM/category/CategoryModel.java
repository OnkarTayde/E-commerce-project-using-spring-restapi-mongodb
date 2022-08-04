package com.ecommerce.ecommerceM.category;

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

@Document(collection = "category")
public class CategoryModel {

	@Transient
	public static final String SEQUENCE_NAME = "category_sequence";

	@Id
	private int id;
	private String category_name;
}
