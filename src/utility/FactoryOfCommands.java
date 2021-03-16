package utility;

import collection.Vehicle;
import comands.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class FactoryOfCommands {

    private ArrayList<Command> commandsList = new ArrayList<>();

    public ArrayList<Command> getCommandList() {
        try {

            LinkedList<Vehicle> list = new LinkedList<>();
            FileManager<Vehicle> fileManager = new FileManager<>(list);
            list = fileManager.readCollection();
            CollectionEditor collectionEditor = new CollectionEditor(list);

            Command commandAverageOfEnginePower = new CommandAverageOfEnginePower(collectionEditor);
            Command clear = new CommandClear(collectionEditor);
            Command help = new CommandHelp(commandsList);
            Command info = new CommandInfo(collectionEditor);
            Command commandPrintFieldDescendingFuelType = new CommandPrintFieldDescendingFuelType(collectionEditor);
            Command save = new CommandSave(collectionEditor, fileManager);
            Command show = new CommandShow(collectionEditor);
            Command removeById = new CommandRemoveById(collectionEditor);
            Command exit = new CommandExit();
            Command removeGrater = new CommandRemoveGrater(collectionEditor);
            Command removeLast = new CommandRemoveLast(collectionEditor);
            Command countByType = new CommandCountByType(collectionEditor);
            commandsList.add(commandPrintFieldDescendingFuelType);
            commandsList.add(clear);
            commandsList.add(help);
            commandsList.add(info);
            commandsList.add(save);
            commandsList.add(show);
            commandsList.add(commandAverageOfEnginePower);
            commandsList.add(removeById);
            commandsList.add(exit);
            commandsList.add(removeGrater);
            commandsList.add(removeLast);
            commandsList.add(countByType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commandsList;
    }
}
