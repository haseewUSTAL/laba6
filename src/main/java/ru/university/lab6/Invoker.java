package ru.university.lab6;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Invoker {
    public static void runReflectionDemo() {
        System.out.println("=== Задание № 1. Рефлексия и аннотации ===");
        MyClass obj = new MyClass();

        // Получаем все объявленные методы класса MyClass
        Method[] methods = MyClass.class.getDeclaredMethods();

        for (Method method : methods) {
            // Проверяем, что метод имеет аннотацию @Repeat
            if (method.isAnnotationPresent(Repeat.class)) {
                int modifiers = method.getModifiers();
                boolean isProtected = Modifier.isProtected(modifiers);
                boolean isPrivate = Modifier.isPrivate(modifiers);

                // По условию вызываем все защищённые и приватные методы, имеющие аннотацию
                if (isProtected || isPrivate) {
                    Repeat repeat = method.getAnnotation(Repeat.class);
                    int times = repeat.times();

                    // Разрешаем доступ к защищенным (если из другого пакета) и приватным методам
                    method.setAccessible(true);

                    // Подготавливаем тестовые аргументы в зависимости от параметров метода
                    Object[] args = getArgumentsForMethod(method);

                    System.out.println("Вызов метода: " + method.getName() + " (модификатор: " +
                            (isProtected ? "protected" : "private") + "), повторов: " + times);

                    try {
                        for (int i = 0; i < times; i++) {
                            method.invoke(obj, args);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("--------------------------------------------------");
                }
            }
        }
    }

    // Вспомогательный метод для генерации аргументов под конкретные типы параметров метода
    private static Object[] getArgumentsForMethod(Method method) {
        Class<?>[] paramTypes = method.getParameterTypes();
        Object[] args = new Object[paramTypes.length];

        for (int i = 0; i < paramTypes.length; i++) {
            if (paramTypes[i] == String.class) {
                args[i] = "ТестовыйАргумент";
            } else if (paramTypes[i] == int.class || paramTypes[i] == Integer.class) {
                args[i] = 100;
            } else if (paramTypes[i] == double.class || paramTypes[i] == Double.class) {
                args[i] = 99.9;
            } else if (paramTypes[i] == boolean.class || paramTypes[i] == Boolean.class) {
                args[i] = true;
            } else {
                args[i] = null;
            }
        }
        return args;
    }
}