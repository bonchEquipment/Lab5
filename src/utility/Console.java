package utility;

import commands.*;
import exceptions.NoArgumentsInUserCommandException;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Console {

    private String userCommand = "";
    private ArrayList<Command> commandsList;
    private OutputSystem outputSystem;

    public Console() {
        initialize();
    }


    public void run() {
        outputSystem.showMessage("collection was successfully load from a file");
        try {
            Scanner commandReader = new Scanner(System.in);
            while (true) {
                try {
                    userCommand = commandReader.nextLine();
                    if (isACommand(userCommand)) {
                        executeCommand(userCommand);
                    } else {
                        outputSystem.showMessage("no such command \"" + userCommand +
                                "\" type \"help\" to see commands available");
                    }
                } catch (NoSuchElementException e) {
                    outputSystem.showMessage("don't press ctrl + d  pls");
                }
            }
        } 
    }


    public void initialize() { //на самом деле ошибка тут не ловится и у меня нет ни одного объяснения почему
        try {
            outputSystem = new OutputSystem();
            FactoryOfCommands factoryOfCommands = new FactoryOfCommands();
            commandsList = new ArrayList<>();
            commandsList = factoryOfCommands.getCommandList();
            // outputSystem.showMessage("collection was successfully load from a file");
        } catch (Exception e) {
            //outputSystem.showMessage("unable to load collection from a file");

            //System.exit(0);
        }
    }

    public void executeCommand(String userCommand) {
        Command commandForExecution;
        String argument;
        commandForExecution = getCommandFromString(userCommand);

        if (commandForExecution instanceof CommandWithArgument) {
            try {
                argument = getArgumentFromUsersInputString(userCommand);
            } catch (NoArgumentsInUserCommandException e) {
                String errorMassage = "invalid syntactics: \"" + userCommand +
                        "\", expected \"" + ((CommandWithArgument) commandForExecution).getSyntacticsExample() + "\"";
                outputSystem.showMessage(errorMassage);
                return;
            }
            ((CommandWithArgument) commandForExecution).getArgumentFromOutside(argument);
        }
        outputSystem.showMessage(commandForExecution.execute());

    }


    public boolean isACommand(String usersString) {
        boolean isACommand = false;
        String[] words = usersString.trim().toLowerCase().split(" ");
        String validatedString = words[0];
        for (Command command : commandsList) {
            if (command.getName().equals(validatedString)) {
                if (words.length > 1 & !(command instanceof CommandWithArgument)) {
                    return false;
                } else if (command instanceof CommandWithArgument & words.length > 2) {
                    return false;
                }
                isACommand = true;

            }
        }
        return isACommand;
    }

    public String getArgumentFromUsersInputString(String inputString) throws NoArgumentsInUserCommandException {
        String argument;
        String[] words = inputString.trim().toLowerCase().split(" ");
        if (words.length < 2) {
            throw new NoArgumentsInUserCommandException();
        } else {
            argument = words[1];
        }
        return argument;
    }

    public Command getCommandFromString(String futureCommand) {
        String[] words = futureCommand.trim().toLowerCase().split(" ");
        futureCommand = words[0];
        for (Command command : commandsList) {
            if (command.getName().equals(futureCommand)) {
                return command;
            }
        }
        return new CommandExit();
    }

}

