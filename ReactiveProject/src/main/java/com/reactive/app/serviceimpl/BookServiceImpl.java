package com.reactive.app.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reactive.app.entities.Book;
import com.reactive.app.repositories.BookRepository;
import com.reactive.app.service.BookService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookServiceImpl implements BookService {

	@Autowired // This is a Field injection
	private BookRepository bookRepository;

	@Override
	public Mono<Book> createBook(Book book) {
		return this.bookRepository.save(book);
	}

	@Override
	public Flux<Book> getAllBooks() {
		return this.bookRepository.findAll();
	}

	@Override
	public Mono<Book> getSingleBook(Integer bookId) {
		Mono<Book> book = this.bookRepository.findById(bookId);
		return book;
	}

	@Override
	public Mono<Book> updateBook(Book book, Integer bookId) {
		Mono<Book> oldBookData = this.bookRepository.findById(bookId);
		return oldBookData.flatMap(newBookData -> {
			newBookData.setBookName(book.getBookName());
			newBookData.setDescription(book.getDescription());
			newBookData.setPublisher(book.getPublisher());
			newBookData.setAuthor(book.getAuthor());
			return this.bookRepository.save(newBookData);
		});

		// return this.bookRepository.save(newBookData);;
	}

	@Override
	public Mono<Void> deleteBook(Integer bookId) {
		Mono<Book> book = this.bookRepository.findById(bookId);

		return book.flatMap(deleteBook -> {
			return this.bookRepository.delete(deleteBook);
		});

		// return null;
	}

	@Override
	public Flux<Book> searchBook(String query) {
		return this.bookRepository.getAllBooksByAuthorName("%" + query +"%");
		//return null;
	}

}
