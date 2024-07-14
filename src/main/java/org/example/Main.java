package org.example;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Программа COUNTER работает");
        System.out.println("Загружаем счетчик...");
        FileHandler fileHandler = new FileHandler();
        Counter counter = fileHandler.tryToRead();

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
                System.out.println("Доступные команды: /inc, /reset, /stop");
            }
        }
        fileHandler.tryToWrite();
    }
}


