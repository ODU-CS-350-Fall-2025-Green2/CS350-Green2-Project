package oducs350.fall25.green2.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileIO {
    public static List<String> readTextFile(String path) throws IOException{
        Path filePath = Path.of(path);
        if (!Files.exists(filePath)) {
            throw new IOException("File \"" + path + "\" does not exist");
        }
        return Files.readAllLines(filePath).stream()
                    .map(String::strip)
                    .toList();
    }

    public static List<String> readPDFFile(String path) throws IOException{
        Path filePath = Path.of(path);
        if (!Files.exists(filePath)) {
            throw new IOException("File \"" + path + "\" does not exist");
        }
        return null;
    }
}
