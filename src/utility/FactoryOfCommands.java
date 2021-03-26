package utility;

import collection.Vehicle;
import com.google.gson.reflect.TypeToken;
import commands.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class FactoryOfCommands {

        private Scanner scanner;
        public FactoryOfCommands(Scanner scanner){
                this.scanner = scanner;
        }

    private ArrayList<Command> commandsList = new ArrayList<>();

    public ArrayList<Command> getCommandList() throws NullPointerException{

            try{
            LinkedList<Vehicle> list = new LinkedList<>();
            FileManager<Vehicle> fileManager = new FileManager<>(list, new TypeToken<LinkedList<Vehicle>>(){}.getType());
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
            commandsList.add(execute_script);}
            catch (IOException e){

            }
        return commandsList;
    }
}
