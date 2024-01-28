package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //inputData();
        //simpleArray();
        //sortPerson();
        String userName = "root";
        String password ="123456";
        String host="localhost";
        String port="3306";
        String database="java_vpd111";
        String strConn = "jdbc:mariadb://"+host+":"+port+"/"+database;
        //createNewTable(strConn, userName, password);
        insertCategory(strConn,userName,password);
    }

    private static void insertCategory(String strConn, String userName, String password) {
        Scanner input = new Scanner(System.in);
        CategoryCreate categoryCreate = new CategoryCreate();
        System.out.println("Вкажіть назву категорії:");
        categoryCreate.setName(input.nextLine());
        System.out.println("Вкажіть опис категорії:");
        categoryCreate.setDescription(input.nextLine());

        try(Connection conn = DriverManager.getConnection(strConn,userName,password)) {
            Statement statement = conn.createStatement();
            String insertQuery = "INSERT INTO categories (name, description) VALUES (?, ?)";
            // Create a PreparedStatement
            PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
            // Set values for the placeholders
            preparedStatement.setString(1, categoryCreate.getName());
            preparedStatement.setString(2, categoryCreate.getDescription());
            // Execute the SQL query
            int rowsAffected = preparedStatement.executeUpdate();
            // Close the resources
            preparedStatement.close();
            System.out.println("Rows affected: " + rowsAffected);
            System.out.println("Category inserted successfully.");
        }
        catch(Exception ex) {
            System.out.println("Помилка створення категорії: "+ex.getMessage());
        }
    }
    private static void createNewTable(String strConn, String userName, String password) {
        try(Connection conn = DriverManager.getConnection(strConn,userName,password)){
            Statement statement = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS categories ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "name VARCHAR(255) NOT NULL,"
                    + "description TEXT"
                    + ")";
            statement.execute(sql);
            statement.close();
            System.out.println("-----Таблицю 'categories' успішно створено-------");

        }catch(Exception ex) {
            System.out.println("Помилка підключення: "+ex.getMessage());
        }
    }

    private static void sortPerson() {
        Person[] list = {
                new Person("Мудрик", "Капітанов"),
                new Person("Андрій", "Жулік"),
                new Person("Петро", "Рогатка"),
                new Person("Микола", "Підкаблучник"),
        };
        System.out.println("---Звичайний список---");
        for (var item : list) {
            System.out.println(item);
        }
//        Arrays.sort(list, new Comparator<Person>() {
//            @Override
//            public int compare(Person o1, Person o2) {
//                return o1.getLastName().compareTo(o2.getLastName());
//            }
//        });
        Arrays.sort(list);
        System.out.println("---Сортований список---");
        for (var item : list) {
            System.out.println(item);
        }
    }

    private static void simpleArray() {
        int n = 10;
        int [] mas = new int[n];
        Random rand = new Random();
        for (int i=0;i<n;i++) {
            mas[i]=getRandom(-10,10);
        }
        System.out.println("Array:");
        for (var item : mas) {
            System.out.printf("%d\t",item);
        }
        System.out.println();
        System.out.println("Sort Array:");
        Arrays.sort(mas);
        for (var item : mas) {
            System.out.printf("%d\t",item);
        }
        int count=0;
        for (var item : mas) {
            if(item>=0)
                count++;
        }
        System.out.println("\nКількість додатніх числе: "+count);
    }
    private static int getRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max-min)+min;
    }

    private static void inputData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Як Вас звати?");
        String name = scanner.nextLine();
        System.out.printf("Привіт %s!\n",name);
        System.out.println("Привіт "+name+"!");
    }
}