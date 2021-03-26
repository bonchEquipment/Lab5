package utility;

import commands.*;
import exceptions.NoArgumentsInUserCommandException;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Console {

    protected String userCommand = "";
    protected ArrayList<Command> commandsList;
    protected OutputSystem outputSystem;
    protected Scanner scanner;

    public Console(Scanner scanner) {
        this.scanner = scanner;
         initialize();
    }


    public void run() {
        while (scanner.hasNext()) {
            userCommand = scanner.nextLine();
            if (isACommand(userCommand)) {
                executeCommand(userCommand);
            } else {
                outputSystem.showMessage("no such command \"" + userCommand +
                        "\" type \"help\" to see commands available");
            }
        }
    }

    public void initialize() { //на самом деле ошибка тут не ловится и у меня нет ни одного объяснения почему
        try {
            outputSystem = new OutputSystem();
            FactoryOfCommands factoryOfCommands = new FactoryOfCommands(scanner);
            commandsList = new ArrayList<>();
            commandsList = factoryOfCommands.getCommandList();
            outputSystem.showMessage("collection was successfully load from a file");
        } catch (NullPointerException e) {
            outputSystem.showMessage("unable to load collection from a file");
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

