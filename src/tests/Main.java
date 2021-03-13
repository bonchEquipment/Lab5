package tests;

import collection.*;
import comands.*;
import utility.*;

import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        LinkedList<Vehicle> list = new LinkedList<>();
        FileManager fileManager = new FileManager(list);
        list = fileManager.readCollection();
        Vehicle vehicle1 = new Vehicle(12,"rocket",20,15.6f,680.1f, VehicleType.SPACESHIP, FuelType.ANTIMATTER);
        System.out.println(vehicle1.getType().toString());



        ArrayList<Command> commandsList = new ArrayList<>();

        Command commandAverageOfEnginePower = new CommandAverageOfEnginePower(list);
        Command clear = new CommandClear(list);
        Command help = new CommandHelp(commandsList);
        Command info = new CommandInfo(list);
        Command commandPrintFieldDescendingFuelType = new CommandPrintFieldDescendingFuelType(list);
        Command save = new CommandSave(fileManager);
        Command show = new CommandShow(list);
        commandsList.add(commandPrintFieldDescendingFuelType);
        commandsList.add(clear);
        commandsList.add(help);
        commandsList.add(info);
        commandsList.add(save);
        commandsList.add(show);
        commandsList.add(commandAverageOfEnginePower);

        OutputSystem outputSystem = new OutputSystem();

        Console console = new Console(commandsList, outputSystem);

        //console.run();


    }
}


//    Vehicle collectionElement = new Vehicle(11, "moto-moto", 110, 115, 53.5f, VehicleType.MOTORCYCLE, FuelType.DIESEL);
//    Vehicle vehicle1 = new Vehicle(12,"rocket",20,15.6f,680.1f, VehicleType.SPACESHIP, FuelType.ANTIMATTER);
//    Vehicle vehicle2 = new Vehicle(13, "vertofly", 1000, 5000, 400, VehicleType.CHOPPER, FuelType.MANPOWER);
//        list.add(collectionElement);
//                list.add(vehicle1);
//                list.add(vehicle2);
//                fileManager.saveCollectionInFile();