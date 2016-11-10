package com.cjon.book.service;

import com.cjon.book.dao.BookDAO;
import com.cjon.book.dto.BookDTO;

public class BookSearchKeywordList implements BookService {

	@Override
	public String execute(BookDTO entity) {
		
		BookDAO dao=new BookDAO();
		String result=dao.select(entity);
		return result;
	}

	
}
