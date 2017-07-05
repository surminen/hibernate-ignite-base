package com.msurminen;

import java.util.Date;

import org.hibernate.Session;

import com.msurminen.user.DBUser;
import com.msurminen.util.HibernateUtil;

@SuppressWarnings("javadoc")
public class App
{
    public static void main(String[] args)
    {
        System.out.println("Maven + Hibernate + Oracle");
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        DBUser user = new DBUser();

        user.setUserId(101);
        user.setUsername("superman");
        user.setCreatedBy("system");
        user.setCreatedDate(new Date());

        session.save(user);
        session.getTransaction().commit();

        session.close();
    }
}
