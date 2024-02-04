package org.example.utils;

import lombok.Getter;
import org.example.models.Category;
import org.example.models.Product;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    @Getter
    private static SessionFactory sessionFactory;

   static {
        try {
            Configuration conf = new Configuration().configure();
            conf.addAnnotatedClass(Category.class);
            conf.addAnnotatedClass(Product.class);
            sessionFactory = conf.buildSessionFactory();
        }catch(Exception ex) {
            System.out.println("Помилка ініціалізації БД "+ ex.getMessage());
        }
    }
}
