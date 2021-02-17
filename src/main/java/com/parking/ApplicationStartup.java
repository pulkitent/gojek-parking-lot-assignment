package com.parking;

import com.parking.io.input.ConsoleReaderUtility;
import com.parking.io.input.FileReaderUtility;

public class ApplicationStartup {
    public static void main(String[] args) {
        if (args.length >= 1) {
            String fileName = args[0];
            FileReaderUtility.read(fileName);
        } else {
            ConsoleReaderUtility.read();
        }
    }
}
