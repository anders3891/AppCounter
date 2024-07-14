package org.example;

import java.io.*;

public class FileHandler {
    private  final String PathToFile;
    private Counter counter;

    public FileHandler (){
        this.PathToFile = "C:\\Temp\\count.ser";
    }

    public FileHandler (String PathToFile){
        this.PathToFile = PathToFile;

    }


    public Counter tryToRead (){
        try {
            //пробуем считать из файла
            FileInputStream fileInputStream = new FileInputStream(PathToFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            this.counter = (Counter) objectInputStream.readObject();
            System.out.println("Счетчик загружен, значение: " + counter.getCount());
        } catch (IOException | ClassNotFoundException f) {
            //если не получилось, создаем новый счетчик
            System.out.println("Что-то пошло не так... загружаем новый счетчик");
            this.counter = new Counter();
            System.out.println("Счетчик загружен, значение: " + counter.getCount());
        }
        return this.counter;
    }

    public void tryToWrite () {
        try {
            FileOutputStream outputStream = new FileOutputStream(PathToFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            // сохраняем
            objectOutputStream.writeObject(this.counter);
            //закрываем поток и освобождаем ресурсы
            objectOutputStream.close();
        } catch (IOException e) {
            System.out.println("Что-то пошло не так...");
        }
    }
}
