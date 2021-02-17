package com.parking.io.input;

import com.parking.io.output.ConsoleOutputUtility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.parking.io.input.CommonUtility.executeCommandsFromFile;

public class FileReaderUtility {
    public static void read(String fileName) {
        String filePathFromRoot = "src/main/resources/" + fileName;
        String line = "";
        List<String> commands = new LinkedList();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePathFromRoot));
            while ((line = reader.readLine()) != null) {
                commands.add(line);
            }
        } catch (IOException exception) {
            ConsoleOutputUtility.print(exception.getMessage());
        }

        executeCommandsFromFile(commands);
    }
}
