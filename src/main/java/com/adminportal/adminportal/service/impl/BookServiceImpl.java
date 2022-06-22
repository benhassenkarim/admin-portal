package com.adminportal.adminportal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.adminportal.domain.Book;
import com.adminportal.adminportal.repository.BookRepository;
import com.adminportal.adminportal.service.BookService;
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public Book save(Book book) {
		
		return bookRepository.save(book);
	}

	@Override
	public List<Book> findAll() {
		
		return (List<Book>) bookRepository.findAll();
	}

	@Override
	public Optional<Book> findOne(Long id) {
		
		return bookRepository.findById(id);
	}

	@Override
	public void removeOne(long id) {
		bookRepository.deleteById(id);
		
	}

}
