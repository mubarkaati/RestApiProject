package com.services;

import com.model.Author;
import com.model.Book;
import com.utils.GetSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class BookDaoImplementation implements BookDao {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    @Override
    public boolean insertBook(Book book, Author author) {
        int status1 = 0;
        int status2 = 0;
        Session session = null;
        try {
            System.out.println(book + " " + author);
            session = GetSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            book.setAuthor(author);
            status1 = (int) session.save(book);
            status2 = (int) session.save(author);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e) {
            status1 = 0;
            status2 = 0;
            session.getTransaction().rollback();
        }
        return (status1 > 0 && status2 > 0) ? true : false;
    }

    @Override
    public boolean deleteBook(int bookId) {
        Session session = null;
        try {
            session = GetSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(session.get(Book.class, bookId).getAuthor());
            session.delete(session.get(Book.class, bookId));
            session.getTransaction().commit();
        }
        catch (Exception e) {
            session.getTransaction().rollback();
            return false;
        }
        return true;
    }

    @Override
    public void fetchAllBook() {
        Session session = GetSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Book");
        List<Book> books = (List<Book>) query.list();
        if(query.list().size() > 0) {
            query.list().stream().forEach(System.out::println);
            books.stream().forEach(book -> System.out.println(book.getAuthor()));
        }
        else
            System.out.println("No Book In Database");
    }

    @Override
    public void fetchById(int bookId) {
        Session session = GetSessionFactory.getSessionFactory().openSession();
        Book book = session.get(Book.class, bookId);
        if(book != null) {
            System.out.println(book);
            System.out.println(book.getAuthor());
        }
        else
            System.out.println("No Book found with this id");
    }

    @Override
    public void updateBook(int bookId) throws IOException {
        if (GetSessionFactory.getSessionFactory().openSession().get(Book.class, bookId) != null) {
            boolean status = true;
            do {
                System.out.println("0. Exit");
                System.out.println("1. Book Name");
                System.out.println("2. Book Price");
                System.out.println("3. Author Name");
                switch (Integer.parseInt(bufferedReader.readLine())) {
                    case 0:
                        status = false;
                        break;

                    case 1:
                        System.out.println("Enter New Book Name");
                        Session session = GetSessionFactory.getSessionFactory().openSession();
                        session.beginTransaction();
                        Book book = (Book) session.get(Book.class, bookId);
                        book.setBookName(bufferedReader.readLine());
                        session.saveOrUpdate(book);
                        session.getTransaction().commit();
                        break;

                    case 2:
                        System.out.println("Enter New Book Price");
                        session = GetSessionFactory.getSessionFactory().openSession();
                        session.beginTransaction();
                        book = session.get(Book.class, bookId);
                        book.setBookPrice(Float.parseFloat(bufferedReader.readLine()));
                        session.saveOrUpdate(book);
                        session.getTransaction().commit();
                        break;

                    case 3:
                        System.out.println("Enter New Author Name");
                        session = GetSessionFactory.getSessionFactory().openSession();
                        session.beginTransaction();
                        Author author = session.get(Book.class, bookId).getAuthor();
                        author.setAuthorName(bufferedReader.readLine());
                        session.saveOrUpdate(author);
                        session.getTransaction().commit();
                        break;

                    default:
                        System.out.println("Invalid Choice");
                }
            }
            while (status);
            System.out.println("Book Updated Successfully");
        }
        else {
            System.out.println("Invalid Id");
        }
    }
}