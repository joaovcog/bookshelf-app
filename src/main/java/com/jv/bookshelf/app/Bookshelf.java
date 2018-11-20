package com.jv.bookshelf.app;

import com.jv.bookshelf.dao.BookDAO;
import com.jv.bookshelf.model.Book;
import java.util.List;

/**
 *
 * @author joaov
 */
public class Bookshelf {
    public static void main(String[] args) {
        BookDAO bookDAO = new BookDAO();
        
        List<Book> books = bookDAO.findAll();
        
        for (Book b : books) {
            System.out.println(b);
        }
    }
}