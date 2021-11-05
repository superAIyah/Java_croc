package task9;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.StringJoiner;

public class LogsFileVisitor extends SimpleFileVisitor<Path> {
    public StringJoiner sj; // для сохранения файлов .log .trace

    public LogsFileVisitor() {
        sj = new StringJoiner("\n");
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {

        String fileName = file.getFileName().toString();
        if (fileName.endsWith(".log") || fileName.endsWith(".trace")) {
            sj.add(file.toString()); // если файл лог -> сохраним его
        }
        return FileVisitResult.CONTINUE; // продолжаем обход дерева
    }
}