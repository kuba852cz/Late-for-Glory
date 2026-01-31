package Logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class LineCounter {

    public static void main(String[] args) {
        // Cesta k tvému zdrojovému kódu (většinou "src")
        // System.getProperty("user.dir") vezme aktuální složku projektu
        Path startPath = Paths.get(System.getProperty("user.dir"), "src");

        System.out.println("Počítám řádky v: " + startPath.toAbsolutePath());
        System.out.println("------------------------------------------------");

        try {
            long totalLines = 0;
            long fileCount = 0;

            // Projdeme všechny soubory
            try (Stream<Path> paths = Files.walk(startPath)) {

                // Vyfiltrujeme jen soubory .java
                var javaFiles = paths
                        .filter(Files::isRegularFile)
                        .filter(p -> p.toString().endsWith(".java"))
                        .toList();

                for (Path file : javaFiles) {
                    long lines = countLines(file);
                    System.out.println(file.getFileName() + ": " + lines + " řádků");
                    totalLines += lines;
                    fileCount++;
                }
            }

            System.out.println("------------------------------------------------");
            System.out.println("CELKEM SOUBORŮ: " + fileCount);
            System.out.println("CELKEM ŘÁDKŮ KÓDU: " + totalLines + " 🚀");
            System.out.println("------------------------------------------------");

        } catch (IOException e) {
            System.out.println("Chyba při čtení souborů: " + e.getMessage());
        }
    }

    // Metoda pro spočítání řádků v jednom souboru
    private static long countLines(Path path) {
        try (Stream<String> lines = Files.lines(path)) {
            // .filter(line -> !line.trim().isEmpty()) // Odkomentuj, pokud nechceš počítat prázdné řádky
            return lines.count();
        } catch (IOException e) {
            return 0;
        }
    }
}