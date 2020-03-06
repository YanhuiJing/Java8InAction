package java8Action;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author gavin
 * @createDate 2020/3/6
 */
public class FilesAction {

    public static void main(String[] args) throws IOException {

        fileStream();
    }


    public static void fileStream() throws IOException {

        Path path = Paths.get("F:\\Java8InAction\\src\\main\\java\\java8Action\\file");

        Files.lines(path)
                .forEach(System.out::println);
    }
}

