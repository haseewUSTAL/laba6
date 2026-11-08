package ru.university.lab6;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Запуск Лабораторной работы № 6 (JDK 25 LTS) ===\n");

        // Запуск задания 1 (Рефлексия и аннотации)
        Invoker.runReflectionDemo();

        // Запуск задания 2 (Работа с файловой системой)
        FileSystemDemo.runFileSystemTasks();

        System.out.println("\nЛабораторная работа № 6 выполнена успешно!");
    }
}