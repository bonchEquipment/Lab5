package utility;

import collection.Vehicle;
import com.google.gson.reflect.TypeToken;
import commands.*;
import exceptions.NoReadPermissionException;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * class for constructing commands for later use
 */

public class FactoryOfCommands {

    private ArrayList<Command> commandsList;
    private FileManager<Vehicle> fileManager;

    public FactoryOfCommands(){
        commandsList = new ArrayList<>();
        this.fileManager = new FileManager<Vehicle>("LAB5",new TypeToken<LinkedList<Vehicle>>(){}.getType());
    }

    /**
     * creates and return list of commands,
     * also some commands needs scanner to be constructed, so we need to give it as a parameter
     *
     * @param scanner for some commands
     * @return list with all commands
     */
    public ArrayList<Command> getCommandList(Scanner scanner) {

        CollectionEditor collectionEditor = new CollectionEditor();
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
        Command removeLower = new CommandRemoveLower(collectionEditor);
        Command add = new CommandAdd(collectionEditor, scanner);
        Command update = new CommandUpdate(collectionEditor, scanner);
        Command execute_script = new CommandExecuteScript(collectionEditor);
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
        commandsList.add(removeLower);
        commandsList.add(add);
        commandsList.add(update);
        commandsList.add(execute_script);
        return commandsList;
    }

    /**
     * checking is anything wrong with a file
     *
     * @note this line will also appears when you execute script (it's not a bug, it's a feature)
     */
    public String getStatusOfLoadFile() {
        FileManager<Vehicle> fileManager = new FileManager<>("LAB5", new TypeToken<LinkedList<Vehicle>>() {
        }.getType());
        try {
            fileManager.readCollection();
            return "collection was successfully load from a file";
        } catch (NoReadPermissionException e) {
            return "unable to load collection from a file due to absence read right";
        } catch (FileNotFoundException e) {
            return "collection wasn't load, because file not found";
        }
    }

}
