package com.parking.io.output;

import static com.parking.constant.Constants.EMPTY_STRING;
import static java.lang.System.out;

public class ConsoleOutputUtility {
    private ConsoleOutputUtility() {
    }

    public static void printInNextLine(String message) {
        out.println(EMPTY_STRING);
        out.println(message);
    }

    public static void print(String message) {
        out.print(message);
        out.println(EMPTY_STRING);
    }
}
