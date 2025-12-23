//Сделано с помощью ИИ "DeepSeek"
package org.example;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;

public class JavaCopyFile {

    // Константы для цветов ANSI
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String CYAN = "\u001B[36m";
    private static final String PURPLE = "\u001B[35m";
    private static final String BOLD = "\u001B[1m";
    private static final String RED = "\u001B[31m";

    // Форматтер для размеров файлов
    private static final DecimalFormat SIZE_FORMATTER = new DecimalFormat("#,##0.00");
    private static final DecimalFormat TIME_FORMATTER = new DecimalFormat("#,##0.000");

    public static void main(String[] args) {
        displayHeader();

        try {
            // Определяем пути к файлам (можно заменить на свои)
            String basePath = System.getProperty("user.home") + "/Documents/программирование/системное программирование/";
            String labPath = basePath + "8 лаба (асинхронность)/";

            // Операция 1: Копирование файла
            System.out.println(CYAN + BOLD + "\n══════════════════════════════════════════════════════" + RESET);
            System.out.println(PURPLE + BOLD + "          ОПЕРАЦИЯ 1: КОПИРОВАНИЕ ФАЙЛА             " + RESET);
            System.out.println(CYAN + BOLD + "══════════════════════════════════════════════════════" + RESET);

            File source = new File(labPath + "source.txt");
            File destination = new File(basePath + "destination.txt");

            performCopyOperation(source, destination);

            // Операция 2: Перемещение файла
            System.out.println(CYAN + BOLD + "\n══════════════════════════════════════════════════════" + RESET);
            System.out.println(PURPLE + BOLD + "          ОПЕРАЦИЯ 2: ПЕРЕМЕЩЕНИЕ ФАЙЛА             " + RESET);
            System.out.println(CYAN + BOLD + "══════════════════════════════════════════════════════" + RESET);

            File source1 = new File(labPath + "source1.txt");
            File destination1 = new File(basePath + "destination1.txt");

            performMoveOperation(source1, destination1);

            System.out.println(CYAN + BOLD + "\n══════════════════════════════════════════════════════" + RESET);
            System.out.println(GREEN + BOLD + "            ВСЕ ОПЕРАЦИИ УСПЕШНО ВЫПОЛНЕНЫ           " + RESET);
            System.out.println(CYAN + BOLD + "══════════════════════════════════════════════════════" + RESET);

        } catch (Exception e) {
            System.err.println(RED + BOLD + "\n⚠ ОШИБКА: " + e.getMessage() + RESET);
            e.printStackTrace();
        }
    }

    private static void displayHeader() {
        System.out.println(GREEN + BOLD + "╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║         ФАЙЛОВЫЕ ОПЕРАЦИИ JAVA (NIO.2 API)               ║");
        System.out.println("║            Копирование и перемещение файлов             ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝" + RESET);
        System.out.println();
        System.out.println(YELLOW + "📁 Базовая директория: " + System.getProperty("user.home") + RESET);
    }

    private static void performCopyOperation(File source, File destination) throws IOException {
        System.out.println("\n📄 " + BLUE + "Информация об исходном файле:" + RESET);
        displayFileInfo(source, "Исходный файл");

        System.out.println("\n🎯 " + BLUE + "Целевой путь:" + RESET);
        System.out.println("   " + destination.getAbsolutePath());

        // Проверяем существование исходного файла
        if (!source.exists()) {
            System.out.println(RED + "\n⚠ Предупреждение: исходный файл не существует!" + RESET);
            System.out.println(YELLOW + "   Создаём тестовый файл для демонстрации..." + RESET);
            createTestFile(source);
        }

        System.out.println("\n⏳ " + PURPLE + "Начало копирования..." + RESET);
        Instant start = Instant.now();

        try {
            copyFileUsingJava7Files(source, destination);
            Instant end = Instant.now();
            long duration = Duration.between(start, end).toNanos();

            System.out.println(GREEN + "✅ Файл успешно скопирован!" + RESET);

            displayOperationResults("Копирование", duration, source, destination);

        } catch (IOException e) {
            System.err.println(RED + "❌ Ошибка при копировании: " + e.getMessage() + RESET);
            throw e;
        }
    }

    private static void performMoveOperation(File source, File destination) throws IOException {
        System.out.println("\n📄 " + BLUE + "Информация об исходном файле:" + RESET);
        displayFileInfo(source, "Исходный файл");

        System.out.println("\n🎯 " + BLUE + "Целевой путь:" + RESET);
        System.out.println("   " + destination.getAbsolutePath());

        // Проверяем существование исходного файла
        if (!source.exists()) {
            System.out.println(RED + "\n⚠ Предупреждение: исходный файл не существует!" + RESET);
            System.out.println(YELLOW + "   Создаём тестовый файл для демонстрации..." + RESET);
            createTestFile(source);
        }

        System.out.println("\n⏳ " + PURPLE + "Начало перемещения..." + RESET);
        Instant start = Instant.now();

        try {
            moveFileUsingJava7Files(source, destination);
            Instant end = Instant.now();
            long duration = Duration.between(start, end).toNanos();

            System.out.println(GREEN + "✅ Файл успешно перемещён!" + RESET);

            displayOperationResults("Перемещение", duration, source, destination);

        } catch (IOException e) {
            System.err.println(RED + "❌ Ошибка при перемещении: " + e.getMessage() + RESET);
            throw e;
        }
    }

    private static void copyFileUsingJava7Files(File source, File destination) throws IOException {
        Files.copy(source.toPath(), destination.toPath());
    }

    private static void moveFileUsingJava7Files(File source, File destination) throws IOException {
        Files.move(source.toPath(), destination.toPath());
    }

    private static void displayFileInfo(File file, String label) {
        System.out.println("   " + CYAN + "• " + label + ":" + RESET);
        System.out.println("     Путь: " + file.getAbsolutePath());
        System.out.println("     Существует: " + (file.exists() ? GREEN + "Да" + RESET : RED + "Нет" + RESET));

        if (file.exists()) {
            System.out.println("     Размер: " + formatFileSize(file.length()));
            System.out.println("     Последнее изменение: " +
                    new java.util.Date(file.lastModified()).toString());
            System.out.println("     Можно читать: " +
                    (file.canRead() ? GREEN + "Да" + RESET : RED + "Нет" + RESET));
            System.out.println("     Можно писать: " +
                    (file.canWrite() ? GREEN + "Да" + RESET : RED + "Нет" + RESET));
        }
    }

    private static void displayOperationResults(String operationName, long durationNanos,
                                                File source, File destination) {
        double durationMillis = durationNanos / 1_000_000.0;
        double durationSeconds = durationMillis / 1_000.0;

        System.out.println("\n📊 " + YELLOW + BOLD + "Результаты операции \"" + operationName + "\":" + RESET);
        System.out.println(CYAN + "   ──────────────────────────────────────" + RESET);
        System.out.println("   • Время выполнения: " +
                String.format("%s нс / %s мс / %s с",
                        formatNumber(durationNanos),
                        TIME_FORMATTER.format(durationMillis),
                        TIME_FORMATTER.format(durationSeconds)));

        if (operationName.equals("Копирование") && destination.exists()) {
            long sourceSize = source.exists() ? source.length() : 0;
            long destSize = destination.length();
            System.out.println("   • Размер исходного файла: " + formatFileSize(sourceSize));
            System.out.println("   • Размер скопированного файла: " + formatFileSize(destSize));

            if (sourceSize == destSize) {
                System.out.println("   • " + GREEN + "✓ Размеры файлов совпадают" + RESET);
            } else {
                System.out.println("   • " + RED + "⚠ Размеры файлов не совпадают!" + RESET);
            }
        }

        System.out.println("   • Используемый API: " + BLUE + "java.nio.file.Files (Java 7+)" + RESET);
        System.out.println("   • Метод: " +
                (operationName.equals("Копирование") ? "Files.copy()" : "Files.move()"));
    }

    private static String formatFileSize(long bytes) {
        if (bytes < 1024) {
            return bytes + " байт";
        } else if (bytes < 1024 * 1024) {
            return SIZE_FORMATTER.format(bytes / 1024.0) + " КБ";
        } else if (bytes < 1024 * 1024 * 1024) {
            return SIZE_FORMATTER.format(bytes / (1024.0 * 1024.0)) + " МБ";
        } else {
            return SIZE_FORMATTER.format(bytes / (1024.0 * 1024.0 * 1024.0)) + " ГБ";
        }
    }

    private static String formatNumber(long number) {
        return String.format("%,d", number);
    }

    private static void createTestFile(File file) throws IOException {
        file.getParentFile().mkdirs();

        String testContent = "Это тестовый файл, созданный для демонстрации операций.\n" +
                "Время создания: " + new java.util.Date() + "\n" +
                "Строка 1\nСтрока 2\nСтрока 3\n".repeat(10);

        Files.write(file.toPath(), testContent.getBytes());
        System.out.println(GREEN + "   ✓ Тестовый файл создан: " + file.getAbsolutePath() + RESET);
    }
}