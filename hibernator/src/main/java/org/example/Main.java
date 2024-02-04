package org.example;

import org.example.models.Category;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Calendar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calendar calendar = Calendar.getInstance();
        var sf = HibernateUtil.getSessionFactory();
        try(Session context = sf.openSession()) {
            context.beginTransaction();
            Category category = new Category();
            System.out.println("Вказіть назву категорії");
            String temp = scanner.nextLine();
            category.setName(temp);
            System.out.println("Вкажіть фото");
            temp = scanner.nextLine();
            category.setImage(temp);
            category.setCateCreated(calendar.getTime());
            context.save(category);
            context.getTransaction().commit();
        }
    }
}