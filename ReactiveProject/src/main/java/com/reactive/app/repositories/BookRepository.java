package com.reactive.app.repositories;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.reactive.app.entities.Book;

import reactor.core.publisher.Flux;

public interface BookRepository extends ReactiveCrudRepository<Book, Integer> {
	
//	@Query("select * from book where author = :author")
//	Flux<Book> getAllBooksByAuthorName(String author);
	
	@Query("select * from book where author LIKE :author")
	Flux<Book> getAllBooksByAuthorName(String author);
}
