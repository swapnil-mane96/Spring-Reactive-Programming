package com.reactive.app.entities;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	@Id
	private Integer bookId;
	private String bookName;
	private String description;
	private String publisher;
	private String author;
	
}
