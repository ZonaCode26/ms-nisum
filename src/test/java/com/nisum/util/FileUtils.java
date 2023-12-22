package com.nisum.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileUtils {
  private static final String TEST_DATA_ROOT_PATH = "src/test/resources/";

  private FileUtils() {
    throw new IllegalStateException("Utility class");
  }

  public static String loadJson(String jsonFile) {
    StringBuilder contentBuilder = new StringBuilder();

    try (Stream<String> stream =
        Files.lines(Paths.get(TEST_DATA_ROOT_PATH.concat(jsonFile)), StandardCharsets.UTF_8)) {
      stream.forEach(s -> contentBuilder.append(s).append("\n"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    return contentBuilder.toString();
  }
}
