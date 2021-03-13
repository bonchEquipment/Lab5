package utility;

import collection.FuelType;
import collection.Vehicle;
import collection.VehicleType;
import comands.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Console {

    private String userCommand = "";
    private ArrayList<Command> commandsList;
    private OutputSystem outputSystem;

    public Console(ArrayList<Command> commandsList, OutputSystem outputSystem) {
        this.commandsList = commandsList;
        this.outputSystem = outputSystem;
    }


    public void run() throws Exception {

        try (Scanner commandReader = new Scanner(System.in)) {
            while (true) {
                try {
                    userCommand = commandReader.nextLine();
                    boolean commandIsAvailable = false;
                    Command commandForExecution = null;

                    for (Command command : commandsList) {
                        if (userCommand.equals(command.getName())) {
                            commandIsAvailable = true;
                            commandForExecution = command;
                        }
                    }

                    if (commandIsAvailable) {
                        outputSystem.showMessage(commandForExecution.execute());
                    } else if (userCommand.equals("exit")) {
                        System.exit(0);
                    } else {
                        outputSystem.showMessage("no such command \"" + userCommand +
                                "\" type \"help\" to see commands available");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


