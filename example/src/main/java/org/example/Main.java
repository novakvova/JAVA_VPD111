package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //inputData();
        //simpleArray();
        sortPerson();

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