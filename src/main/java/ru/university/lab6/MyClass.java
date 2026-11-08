package ru.university.lab6;

public class MyClass {

    // --- Публичные методы (2-3 шт) ---
    public void publicMethod1(String msg) {
        System.out.println("Public 1: " + msg);
    }

    public int publicMethod2(int a, int b) {
        return a + b;
    }

    // --- Защищенные методы с параметрами (2-3 шт) ---
    @Repeat(times = 2)
    protected void protectedMethod1(String text) {
        System.out.println("Protected 1 (repeated): " + text);
    }

    @Repeat(times = 3)
    protected void protectedMethod2(int number) {
        System.out.println("Protected 2 (repeated): number = " + number);
    }

    protected void protectedMethod3(double val) {
        System.out.println("Protected 3 (not annotated): " + val);
    }

    // --- Приватные методы с параметрами (2-3 шт) ---
    @Repeat(times = 4)
    private void privateMethod1(String name, int age) {
        System.out.println("Private 1 (repeated): Name=" + name + ", Age=" + age);
    }

    @Repeat(times = 2)
    private void privateMethod2(String word) {
        System.out.println("Private 2 (repeated): Word=" + word);
    }

    private void privateMethod3(boolean flag) {
        System.out.println("Private 3 (not annotated): " + flag);
    }
}