package commands;

import collection.Coordinates;
import collection.FuelType;
import collection.Vehicle;
import collection.VehicleType;
import exceptions.NoArgumentsInUserCommandException;
import utility.CollectionEditor;
import utility.Console;
import utility.OutputSystem;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class CommandExecuteScript implements CommandWithArgument {
    private CollectionEditor collectionEditor;
    private String userEnteredPath;
    private OutputSystem outputSystem;
    private Collection<String> paths;


    public CommandExecuteScript(CollectionEditor collectionEditor) {
        this.collectionEditor = collectionEditor;
        this.outputSystem = new OutputSystem();
        this.paths = new ArrayList<>();
    }

    @Override
    public String execute() {
        if (!isFileExits(userEnteredPath)) {
            return "Invalid file path specified";
        }

//можно отдельно создать переопределенную консоль,  а потом уже отдать сюда
        //вот тут нужен комментарий с пояснением, зачем я дописываю run()
        try {
            Console console = new Console(new Scanner(Paths.get(userEnteredPath))) {
                @Override
                public void run() {
                    while (scanner.hasNext()) {
                        userCommand = scanner.nextLine();
                        if (!isACommand(userCommand)) {
                            outputSystem.showMessage("no such command \"" + userCommand +
                                    "\" type \"help\" to see commands available");
                            continue;
                        }
                        if (isLineAScriptWithRecursion(userCommand)) {
                            outputSystem.showMessage("the script was not executed due to recursion");
                            continue;
                        }
                        if (isACommand(userCommand)) {
                            executeCommand(userCommand);
                        }
                    }

                }
            };
            console.run();
        } catch (IOException e) {

        }
        return "";
    }


    public void getArgumentFromOutside(String argument) {
        userEnteredPath = argument;
    }

    private boolean isFileExits(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            reader.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private boolean isThePathHaveRecursion(String path) {
        ArrayList<String> linesCollection = getLinesCollectionFromFile(path);
        if (isStringCollectionHaveSameElements(paths)) {
            return true;
        }

        for (String line : linesCollection) {//хотим проанализировать все строчки в файле
            if (isLineIsACommandExecuteScript(line)) {
                String[] words = line.split(" ");
                paths.add(words[1]);
                if (isThePathHaveRecursion(words[1])) {
                    return true;
                }
            }
        }
        return false;
    }


    //эта штука работает, определенно, я это проверил (а вообще хорошо бы тестики писать для этого)
    private boolean isLineAScriptWithRecursion(String line) {
        String[] words = line.split(" ");
        if (words.length == 2) {
            boolean condition1 = words[0].equals("execute_script");
            boolean condition2 = isThePathHaveRecursion(words[1]);
            return condition1 & condition2;
        }
        return false;
    }

    //окей, проверил, это работает
    private boolean isStringCollectionHaveSameElements(Collection<String> collection) {
        String[] mass = new String[1];
        String[] array = collection.toArray(mass);
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i].equals(array[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    private ArrayList<String> getLinesCollectionFromFile(String path) {
        ArrayList<String> linesCollection = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while (line != null) {
                linesCollection.add(line);
                line = reader.readLine();
            }
            reader.close();

        } catch (IOException e) {
            //this will never happen because I'm using this method only after checking is path valid
        }
        return linesCollection;
    }


    private boolean isLineIsACommandExecuteScript(String line) {
        String[] words = line.split(" ");
        boolean b = words.length == 2 &&
                words[0].toLowerCase().equals("execute_script") &&  //если строчка - execute_script PATH
                isFileExits(words[1]);

        return b;
    }

    @Override
    public String getDescription() {
        return "reads and executes a script from the specified file";
    }

    @Override
    public String getSyntacticsExample() {
        return getName() + " ScriptFile.txt";
    }

}



