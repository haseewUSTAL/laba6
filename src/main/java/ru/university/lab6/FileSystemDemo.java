package ru.university.lab6;

import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.stream.Stream;

public class FileSystemDemo {

    public static void runFileSystemTasks() {
        System.out.println("\n=== Задание № 2. Работа с файловой системой ===");

        // Замени на свою фамилию и имя (согласно требованиям лабы)
        String surname = "Burov";
        String name = "Egor.txt"; // имя файла

        Path surnameDir = Paths.get(surname);
        Path targetFile = surnameDir.resolve(name);

        try {
            // 1. Создайте директорию <surname>
            if (!Files.exists(surnameDir)) {
                Files.createDirectories(surnameDir);
                System.out.println("Создана директория: " + surnameDir.toAbsolutePath());
            }

            // 2. Внутри создайте файл <name>
            if (!Files.exists(targetFile)) {
                Files.createFile(targetFile);
                Files.writeString(targetFile, "Привет, это файл Егора Бурова!");
                System.out.println("Создан файл: " + targetFile);
            }

            // 3. Внутри <surname> создайте вложенные директории dir1/dir2/dir3 и скопируйте туда файл <name>
            Path nestedDirs = surnameDir.resolve("dir1/dir2/dir3");
            Files.createDirectories(nestedDirs);
            System.out.println("Созданы вложенные директории: dir1/dir2/dir3");

            Path copiedFileLocation = nestedDirs.resolve(name);
            Files.copy(targetFile, copiedFileLocation, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Файл скопирован в: " + copiedFileLocation);

            // 4. Внутри директории dir1 создайте файл file1
            Path file1 = surnameDir.resolve("dir1/file1.txt");
            if (!Files.exists(file1)) {
                Files.createFile(file1);
                Files.writeString(file1, "Содержимое файла file1");
                System.out.println("Создан файл file1 внутри dir1");
            }

            // 5. Внутри директории dir2 создайте файл file2
            Path file2 = surnameDir.resolve("dir1/dir2/file2.txt");
            if (!Files.exists(file2)) {
                Files.createFile(file2);
                Files.writeString(file2, "Содержимое файла file2");
                System.out.println("Создан файл file2 внутри dir2");
            }

            // 6. Выполните рекурсивный обход директории <surname> и выведите названия с D: и F:
            System.out.println("\n--- Рекурсивный обход структуры директории " + surname + " ---");
            try (Stream<Path> paths = Files.walk(surnameDir)) {
                paths.forEach(path -> {
                    if (Files.isDirectory(path)) {
                        System.out.println("D: " + path.toAbsolutePath());
                    } else if (Files.isRegularFile(path)) {
                        System.out.println("F: " + path.toAbsolutePath());
                    }
                });
            }

            // 7. Удалите директорию dir1 со всем её содержимым
            Path dir1Path = surnameDir.resolve("dir1");
            if (Files.exists(dir1Path)) {
                System.out.println("\nУдаление директории dir1 со всем содержимым...");
                try (Stream<Path> walk = Files.walk(dir1Path)) {
                    // Сортируем в обратном порядке (сначала файлы и глубокие папки, потом сама dir1)
                    walk.sorted(Comparator.reverseOrder())
                            .forEach(p -> {
                                try {
                                    Files.delete(p);
                                    System.out.println("Удалено: " + p.getFileName());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                }
                System.out.println("Директория dir1 успешно удалена.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}