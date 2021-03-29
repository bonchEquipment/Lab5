package utility;

import commands.*;
import exceptions.NoArgumentsInUserCommandException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * class that invokes commands
 */
public class Console {

    protected String userCommand;
    protected ArrayList<Command> commandsList;
    protected AbstractOutputSystem outputSystem;
    protected Scanner scanner;

    public Console(Scanner scanner) {
        this.scanner = scanner;
        userCommand = "";
        outputSystem = new ConsoleOutputSystem();
        FactoryOfCommands factoryOfCommands = new FactoryOfCommands();
        commandsList = new ArrayList<>();
        commandsList = factoryOfCommands.getCommandList(scanner);
        outputSystem.showMessage(factoryOfCommands.getStatusOfLoadFile());
    }

    /**
     * launch point
     */
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

    /**
     * method for executing any command
     * takes any string as a parameter, so it can be some invalid command as well
     *
     * @param userCommand
     */
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

    /**
     * says whether entered string is a command or not
     *
     * @param usersString
     */
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

    /**
     * extracts an argument from the entered string
     * (you entered remove_by_id 911, get 911)
     * @param inputString
     * @throws NoArgumentsInUserCommandException
     */
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

    /**
     * converts string to instance of command
     * (you entered remove_last, get new CommandRemoveLast)
     * @param futureCommand is a String so you possible can enter any command
     * @return command if such command exist or CommandExit if there is no such command
     */
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

