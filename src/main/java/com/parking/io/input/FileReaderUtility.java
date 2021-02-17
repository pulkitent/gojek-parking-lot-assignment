package com.parking.io.input;

import com.parking.io.output.ConsoleOutputUtility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static com.parking.constant.Constants.BASE_PATH_OF_FILE;
import static com.parking.constant.Constants.EMPTY_STRING;
import static com.parking.io.input.CommonUtility.executeCommandsFromFile;

public class FileReaderUtility {

    private FileReaderUtility() {
    }

    public static void read(String fileName) {
        String filePathFromRoot = BASE_PATH_OF_FILE + fileName;
        String line = EMPTY_STRING;
        List<String> commands = new LinkedList<>();

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
