import com.model.Author;
import com.model.Book;
import com.services.BookDao;
import com.services.BookDaoImplementation;
import com.utils.GetSessionFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuOptions {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BookDao bookDao = new BookDaoImplementation();
    public void menu() throws IOException {
        GetSessionFactory.getSessionFactory();
        do {
            System.out.println("0. Exit" +
                    "\n1. Insert" +
                    "\n2. Update" +
                    "\n3. Delete" +
                    "\n4. Fetch All" +
                    "\n5. Fetch By Id");
            switch (Integer.parseInt(bufferedReader.readLine())) {
                case 0:
                    System.exit(0);

                case 1:
                    if(bookDao.insertBook(takeBook(), takeAuthor()) == true) {
                        System.out.println("Record Edit Successfully");
                    }
                    else {
                        System.out.println("Cannot Add Record");
                    }
                    break;

                case 2:
                    System.out.println("Enter Book Id to Update");
                    bookDao.updateBook(Integer.parseInt(bufferedReader.readLine()));
                    System.out.println("Book Updated Successfully");
                    break;

                case 3:
                    System.out.println("Enter Book Id to Delete");
                    if(bookDao.deleteBook(Integer.parseInt(bufferedReader.readLine()))) {
                        System.out.println("Book Deleted Successfully");
                    }
                    else {
                        System.out.println("Book Not Found");
                    }
                    break;

                case 4:
                    bookDao.fetchAllBook();
                    break;

                case 5:
                    System.out.println("Enter Id");
                    bookDao.fetchById(Integer.parseInt(bufferedReader.readLine()));
                    break;
            }
        }
        while (true);
    }
    private Book takeBook() throws IOException {
        Book book = new Book();
        System.out.println("Enter Book Id");
        book.setBookId(Integer.parseInt(bufferedReader.readLine()));
        System.out.println("Enter Book Name");
        book.setBookName(bufferedReader.readLine());
        System.out.println("Enter Book Price");
        book.setBookPrice(Float.parseFloat(bufferedReader.readLine()));
        return book;
    }
    private Author takeAuthor() throws IOException {
        Author author = new Author();
        System.out.println("Enter Author Id");
        author.setAuthorId(Integer.parseInt(bufferedReader.readLine()));
        System.out.println("Enter Author Name");
        author.setAuthorName(bufferedReader.readLine());
        return author;
    }
}