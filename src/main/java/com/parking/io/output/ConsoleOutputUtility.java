package com.parking.io.output;

import static java.lang.System.out;

public class ConsoleOutputUtility {
    private ConsoleOutputUtility() {
    }

    public static void printInNextLine(String message) {
        out.println(message);
    }

    public static void print(String message) {
        out.print(message);
    }
}
