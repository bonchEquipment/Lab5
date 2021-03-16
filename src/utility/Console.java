package utility;

import comands.*;
import exceptions.NoArgumentsInUserCommandException;

import java.util.ArrayList;
import java.util.Scanner;

public class Console {

    private String userCommand = "";
    private ArrayList<Command> commandsList;
    private OutputSystem outputSystem;


    public void run() throws Exception {
        initialize();

        try (Scanner commandReader = new Scanner(System.in)) {
            while (true) {
                try {
                    userCommand = commandReader.nextLine();
                    if (isACommand(userCommand)) {
                        executeCommand(userCommand);
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


    public void initialize() throws Exception {
        FactoryOfCommands factoryOfCommands = new FactoryOfCommands();
        commandsList = new ArrayList<>();
        commandsList = factoryOfCommands.getCommandList();
        outputSystem = new OutputSystem();
    }

    public void executeCommand(String userCommand) {
        Command commandForExecution = null;
        String argument;
        commandForExecution = getCommandFromString(userCommand);
        //окей, выяснили, что вызываем
        if (commandForExecution instanceof CommandWithArgument) {
            //работает с командой с аргументами
            //вызываем метод gerArg, отдавая аргумент, вызываем саму команду
            //ну пока попробую просто вызывать
            try {
                argument = getArgumentFromUsersInputString();
            } catch (NoArgumentsInUserCommandException e){
                String errorMassage = "invalid syntactics: \"" + userCommand +
                        "\", expected " + ((CommandWithArgument) commandForExecution).getSyntacticsExample();
               outputSystem.showMessage(errorMassage);
               return;
            }
            ((CommandWithArgument) commandForExecution).getArgumentFromOutside(argument);
            outputSystem.showMessage(commandForExecution.execute());
        } else {
            outputSystem.showMessage(commandForExecution.execute());
        }

    }


    public boolean isACommand(String usersString) {
        boolean isACommand = false;
        String [] words = usersString.trim().toLowerCase().split(" ");
        String validatedString = words[0];
        for (Command command : commandsList) {
            if (command.getName().equals(validatedString)) {
                if (words.length > 1 & !(command instanceof CommandWithArgument)){
                    return false;
                } else if(command instanceof CommandWithArgument & words.length > 2){
                    return false;
                }
                isACommand = true;

            }
        }
        return isACommand;
    }

    private String getArgumentFromUsersInputString() throws NoArgumentsInUserCommandException{
        String argument;
        String[] words = userCommand.trim().toLowerCase().split(" ");
       if (words.length < 2){
           throw new NoArgumentsInUserCommandException();
       } else {
           argument = words[1];
       }
       return argument;
    }

    private Command getCommandFromString(String futureCommand){
        String[] words = futureCommand.trim().toLowerCase().split(" ");
        futureCommand = words[0];
        for (Command command: commandsList){
            if (command.getName().equals(futureCommand)) {
                return  command;
            }
        }
        return new CommandExit();
    }

}

//    Vehicle collectionElement = new Vehicle(11, "moto-moto", 110, 115, 53.5f, VehicleType.MOTORCYCLE, FuelType.DIESEL);
//    Vehicle vehicle1 = new Vehicle(12,"rocket",20,15.6f,680.1f, VehicleType.SPACESHIP, FuelType.ANTIMATTER);
//    Vehicle vehicle2 = new Vehicle(13, "vertofly", 1000, 5000, 400, VehicleType.CHOPPER, FuelType.MANPOWER);
//        list.add(collectionElement);
//                list.add(vehicle1);
//                list.add(vehicle2);
//                fileManager.saveCollectionInFile();
