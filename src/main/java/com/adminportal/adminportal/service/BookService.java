package com.adminportal.adminportal.service;

import java.util.List;
import java.util.Optional;

import com.adminportal.adminportal.domain.Book;

public interface BookService {

	Book save(Book book);

	List<Book> findAll();

	Optional<Book> findOne(Long id);

	void removeOne(long id);
}
