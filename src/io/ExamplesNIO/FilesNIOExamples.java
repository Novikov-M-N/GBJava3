package io.ExamplesNIO;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

public class FilesNIOExamples {
    public static void copyFile() {
        Path sourcePath = Paths.get("dos.txt");
        Path destinationPath = Paths.get("dst2.txt");
        try {
            Files.copy(sourcePath, destinationPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        overwriteFile();
        try {
            getFilesWithFilters();
        } catch (Exception ignored) {

        }
        moveFile();
        normalizePath();
        copyFile();
        myExam();
    }

    public static void getFilesWithFilters() throws IOException {
        Path directoryPath = Paths.get("C:\\", "1.txt");

        DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path entry) throws IOException {
                long size = Files.readAttributes(entry, BasicFileAttributes.class, NOFOLLOW_LINKS).size();
                long millis = new Date().getTime();

                boolean isSizeEvenNumber = size % 2 == 0;
                boolean isTheTimeRight = millis % 2 == 0;

                return isTheTimeRight && isSizeEvenNumber;
            }
        };
        Files.setAttribute(directoryPath, "", "", NOFOLLOW_LINKS);
        if (Files.isDirectory(directoryPath)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath, filter)) {
                for (Path path : stream) {
                    System.out.println(path);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void moveFile() {
        Path sourcePath = Paths.get("forMove.txt");
        Path destinationPath = Paths.get("dos.txt");
        try {
            Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void normalizePath() {
        String originalPath = "C:\\java";
        Path path1 = Paths.get(originalPath);
        System.out.println("path = " + path1);
        Path path2 = path1.normalize();
        System.out.println("path = " + path2);
    }

    public static void overwriteFile() {
        Path sourcePath = Paths.get("1.txt");
        Path destinationPath = Paths.get("3.txt");
        try {
            Files.copy(sourcePath, destinationPath, StandardCopyOption.COPY_ATTRIBUTES);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void myExam(){
        DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path entry) throws IOException {
                BasicFileAttributes attr = Files.readAttributes(entry, BasicFileAttributes.class);
                FileTime creationTime = attr.creationTime();
                Calendar cal = Calendar.getInstance();
                long diff = TimeUnit.DAYS.convert(creationTime.toMillis(), TimeUnit.MICROSECONDS);
                return Math.abs(diff)>6000;
            }
        };
    }
}
