package com.jv.bookshelf.app;

import com.jv.bookshelf.dao.BookDAO;
import com.jv.bookshelf.model.Book;
import java.util.List;

/**
 *
 * @author João Victor
 */
public class Bookshelf {
    public static void main(String[] args) {
        BookDAO bookDAO = new BookDAO();
        
        List<Book> books = bookDAO.findAll();
        
        books.forEach((b) -> {
            System.out.println(b);
        });
    }
}