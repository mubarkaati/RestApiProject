package com.services;

import com.model.Author;
import com.model.Book;

import java.io.IOException;

public interface BookDao {
    boolean insertBook(Book book, Author author);
    boolean deleteBook(int bookId);
    void fetchAllBook();
    void fetchById(int bookId);
    void updateBook(int bookId) throws IOException;
}
