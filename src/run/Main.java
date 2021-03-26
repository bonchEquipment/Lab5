package run;

import utility.Console;

import java.io.PrintStream;
import java.nio.file.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The main class
 */

public class Main {


    /**
     * program entry point
     */
    public static void main(String[] args) throws Exception {

        //new Scanner(Paths.get("2.txt"));
    Console console = new Console(new Scanner(System.in));
    console.run();

    }



}