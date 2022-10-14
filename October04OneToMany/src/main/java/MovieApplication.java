import com.bean.Director;
import com.bean.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;

public class MovieApplication {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Director director = new Director();
        director.setDirectorId(101);
        director.setName("Yash Chopra");

        HashSet<Movie> hashSet = new HashSet<>();
        hashSet.add(new Movie(1, "DDLJ"));
        hashSet.add(new Movie(2, "Kala Patthar"));
        hashSet.add(new Movie(3, "Mohabbatein"));
        hashSet.add(new Movie(4, "Dhoom"));

        director.setMovies(hashSet);

        System.out.println("************************************************");

        Director director1 = new Director();
        director1.setDirectorId(102);
        director1.setName("Subhash Ghai");


        hashSet.clear();
        hashSet.add(new Movie(1, "DDLJ"));
        hashSet.add(new Movie(2, "Kala Patthar"));
        hashSet.add(new Movie(3, "Mohabbatein"));
        hashSet.add(new Movie(4, "Dhoom"));
    }
}