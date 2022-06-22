package com.adminportal.adminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.adminportal.domain.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

}
