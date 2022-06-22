package com.adminportal.adminportal.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.adminportal.adminportal.domain.Book;
import com.adminportal.adminportal.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "/add",method = RequestMethod.GET)
	public String addBook(Model model) {
		Book book=new Book();
		model.addAttribute(book);
		return "addBook";
	}
	@RequestMapping(value="/add" , method = RequestMethod.POST)
	public String addBookPost(@ModelAttribute("book") Book book,HttpServletRequest request) {
		bookService.save(book);
		MultipartFile bookImage=book.getBookImage();
		
		try {
			byte[] bytes = bookImage.getBytes();
			String name=book.getId()+".png";
			BufferedOutputStream stream = new BufferedOutputStream(
               new FileOutputStream(new File("src/main/resources/static/image/book/"+name)));
			stream.write(bytes);
			stream.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:booklist";
	}
	@RequestMapping("/bookinfo")
	public String bookinfo(@RequestParam("id") Long id,Model model) {
		Optional<Book> book = bookService.findOne(id);
		Book bkk=book.get();
		model.addAttribute("book",bkk);
		return "bookInfo";
	}
	@RequestMapping("/updatebook")
	public String updateBook(@RequestParam("id") Long id,Model model) {
		Optional<Book> book = bookService.findOne(id);
		Book bkk=book.get();
		model.addAttribute("book",bkk);
		return "updateBook";
	}
	@RequestMapping(value = "updatebook",method = RequestMethod.POST)
	public String updateBook( @ModelAttribute("book") Book book,HttpServletRequest request) {
		bookService.save(book);
		MultipartFile bookImage=book.getBookImage();
		try {
			byte[] bytes = bookImage.getBytes();
			String name=book.getId()+".png";
			Files.delete(Paths.get("src/main/resources/static/image/book/"+name));
			BufferedOutputStream stream = new BufferedOutputStream(
               new FileOutputStream(new File("src/main/resources/static/image/book/"+name)));
			stream.write(bytes);
			stream.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/book/bookinfo?id="+book.getId();
	}
	
	@RequestMapping("/booklist")
	public String BookList(Model model) {
		List<Book> bookList=bookService.findAll();
		model.addAttribute("bookList",bookList);
		return "booklist";
	}
	@RequestMapping(value="/remove",method = RequestMethod.POST)
	public String remove(
			@ModelAttribute("id") String id,Model model
			) {
		bookService.removeOne(Long.parseLong(id.substring(8)));
		List<Book> bookList=bookService.findAll();
		model.addAttribute("bookList",bookList);
		
		return "redirect:/book/booklist";
	}
}
