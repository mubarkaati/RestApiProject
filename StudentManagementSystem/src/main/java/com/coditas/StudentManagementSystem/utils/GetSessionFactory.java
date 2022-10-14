package com.coditas.StudentManagementSystem.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetSessionFactory {
    static SessionFactory sessionFactory;

    //Private Constructor
    private GetSessionFactory() {}

    static {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    //Get Session Factory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}