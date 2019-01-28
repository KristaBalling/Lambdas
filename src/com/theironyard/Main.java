package com.theironyard;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        new Thread(() -> System.out.println("Printing from the Runnable")).start();

        Employee john = new Employee("John Doe", 30);
        Employee tim = new Employee("Time Buchaka", 21);
        Employee jack = new Employee("Jack Hill", 40);
        Employee snow = new Employee("Snow White", 22);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(tim);
        employees.add(jack);
        employees.add(snow);

        for (Employee employee : employees) {
            System.out.println(employee.getName());
            new Thread(() -> System.out.println(employee.getAge())).start();
        }

        System.out.println("****************");

        Employee employee;

        for(int i=0; i<employees.size(); i++) {
            employee = employees.get(i);
            System.out.println(employee.getName());
            new Thread(() -> System.out.println(employee.getAge())).start();

        }
    }


    public final static String doStringStuff(UpperConcat uc, String s1, String s2) {
        return uc.upperAndConcat(s1, s2);
    }
}

class Employee {
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getAge() {

        return age;
    }

    public void setAge(int age) {

        this.age = age;
    }
}

interface UpperConcat {
    public String upperAndConcat(String s1, String s2);
}

class AnotherClass {

    public String doSomething() {
//        UpperConcat uc = (s1, s2) -> {
//            System.out.println("The lambda expression's class is " + getClass().getSimpleName());
//            String result = s1.toUpperCase() + s2.toUpperCase();
//            return result;
//        };
        int i = 0;

        UpperConcat uc = (s1, s2) -> {
            System.out.println("The lambda expression's class is " + getClass().getSimpleName());
            System.out.println("i in the lambda expression = " + i);
            String result = s1.toUpperCase() + s2.toUpperCase();
            return result;
        };

        int j = 0;
        {
            String s1;
            String s2;
        }

        System.out.println("The AnotherClass class's name is " + getClass().getSimpleName());
        return Main.doStringStuff(uc, "String1", "String2");
    }

    //method will finish executing before the lambda runs
    //int will = 0
    public void printValue() {

        int number = 25;

        Runnable r = () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("The value is " + number);
        };

        new Thread(r).start();
    }
}

//            UpperConcat uc = new UpperConcat() {
//                @Override
//                public String upperAndConcat(String s1, String s2) {
//                    System.out.println("i (within anonymous class) = " + i);
//                    return s1.toUpperCase() + s2.toUpperCase();
//                }
//            };
//
//            System.out.println("The AnotherClass class's name is " + getClass().getSimpleName());
//
////            i++;
//            System.out.println("i = " + i);
//            return Main.doStringStuff(uc, "String1", "String2");



        //both statements above print AnotherClass as their class because a lambda is not a class
        //a lamda is treated as a nested block of code within a class

//    public String doSomething() {
//        System.out.println("The AnotherClass class's name is: " + getClass().getSimpleName());
//        return Main.doStringStuff(new UpperConcat() {
//            @Override
//            public String upperAndConcat(String s1, String s2) {
//                System.out.println("The anonymous class's name is: " + getClass().getSimpleName());
//                return s1.toUpperCase() + s2.toUpperCase();
//            }
//        }, "String 1 ", "String 2");

