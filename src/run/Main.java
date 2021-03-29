package run;

import utility.Console;
import java.util.Scanner;

/**
 * The main class
 */
public class Main {

    /**
     * program entry point
     */
    public static void main(String[] args)  {
        Console console = new Console(new Scanner(System.in));
        console.run();
    }
}