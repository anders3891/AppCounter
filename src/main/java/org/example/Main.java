package org.example;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Прогорамма COUNTER работает");
        System.out.println("Загружаем счетчик...");
        Counter counter;
        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Temp\\count.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            counter = (Counter) objectInputStream.readObject();
            System.out.println("Счетчик загружен, значение: " + counter.getCount());
        } catch (IOException | ClassNotFoundException f) {
            System.out.println("Что-то пошло не так... загружаем новый счетчик");
            counter = new Counter();
            System.out.println("Счетчик загружен, значение: " + counter.getCount());
        }
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("Введите команду: ");
            String command = input.next();
            if (command.equalsIgnoreCase("/inc")) {
                counter.increment();
                System.out.println("Новое значение счетчика: " + counter);
            } else if (command.equalsIgnoreCase("/reset")) {
                System.out.println("Обнуляем счетчик...");
                counter.reset();
                System.out.println("Текущее значение счетчика: " + counter);
            } else if (command.equalsIgnoreCase("/stop")) {
                System.out.println("Текущее значение счетчика: " + counter);
                System.out.println("Завершаю работу");
                break;
            } else {
                System.out.println("Неизвестная команда...");
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream("C:\\Temp\\count.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            // сохраняем
            objectOutputStream.writeObject(counter);
            //закрываем поток и освобождаем ресурсы
            objectOutputStream.close();
        } catch (IOException e) {
            System.out.println("Что-то пошло не так...");
        }
    }
}


