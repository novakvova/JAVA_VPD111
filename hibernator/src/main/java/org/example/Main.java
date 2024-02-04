package org.example;

import org.example.models.Category;
import org.example.models.Product;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //AddCategory();
        //getListCategories();
        AddProduct();

    }

    private static void getListCategories() {
        var sf = HibernateUtil.getSessionFactory();
        try(Session context = sf.openSession()) {
            context.beginTransaction();

            List<Category> list = context.createQuery("from Category", Category.class)
                    .getResultList();

            for(Category category : list) {
                System.out.println("Category: " + category);
            }

            context.getTransaction().commit();
        }
    }
    private static void AddCategory() {
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

    private static void AddProduct() {
        Scanner scanner = new Scanner(System.in);
        var sf = HibernateUtil.getSessionFactory();
        try(Session context = sf.openSession()) {
            context.beginTransaction();
            Product product = new Product();

            System.out.println("Вказіть назву продукта");
            String temp = scanner.nextLine();
            product.setName(temp);

            System.out.println("Вкажіть опис");
            temp = scanner.nextLine();
            product.setDescription(temp);

            System.out.println("Вкажіть ціну");
            product.setPrice(scanner.nextDouble());

            Category category = new Category();
            System.out.println("Вкажіть id категорії");
            category.setId(scanner.nextInt());

            product.setCategory(category);
            context.save(product);
            context.getTransaction().commit();
        }
    }
}