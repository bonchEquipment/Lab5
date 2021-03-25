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
        resetVariables();
        if (!isFileExits(userEnteredPath)) {
            return "Invalid file path specified";
        }
        ArrayList<String> lines = getLinesCollectionFromFile(userEnteredPath);
        Console console = new Console();

        for (String userCommand : lines) {
            if (userCommand.toLowerCase().equals("add")){
                isAddExecuted = true;
                continue;
            } else if (isAddExecuted){
                constructVehicleStepByStep(userCommand);
                continue;
            } else if (!console.isACommand(userCommand)) {
                outputSystem.showMessage("no such command \"" + userCommand +
                        "\" type \"help\" to see commands available");
                continue;
            } else if (isLineAScriptWithRecursion(userCommand)) {
                outputSystem.showMessage("script not called due to recursion");
                continue;
            } else {
                console.executeCommand(userCommand);
            }
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
    private boolean isLineAScriptWithRecursion(String line){
        String[] words = line.split(" ");
        if (words.length == 2 ) {
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

    private String getPathFromUsersInputString(String inputString){
        String argument;
        String[] words = inputString.trim().split(" ");
        if (words.length == 2) {
            return words[1];
        } else {
            return "";
        }
    }


    private String name = ""; //Поле не может быть null, Строка не может быть пустой
    private Float enginePower; //Значение поля должно быть больше 0
    private VehicleType vehicleType; //Поле может быть null
    private FuelType fuelType; //Поле не может быть null
    private boolean vehicleTypeIsAlreadySet;
    private Float xCoordinate;
    private Float yCoordinate;
    private boolean isAddExecuted;

    private void constructVehicleStepByStep(String value){
       if (name.length() == 0){
           setNameUsingUserInput(System.out, value);
           return;
       }

       if (xCoordinate == null || xCoordinate <= -958){
           setXCoordinateUsingUserInput(System.out, value);
           return;
       }

        if (yCoordinate == null){
            setYCoordinateUsingUserInput(System.out, value);
            return;
        }

        if (enginePower == null){
            setEnginePowerUsingUserInput(System.out, value);
            return;
        }

        if (!vehicleTypeIsAlreadySet){
            setVehicleTypeUsingUserInput(System.out, value);
            return;
        }

        if (fuelType == null){
            setFuelTypeUsingUserInput(System.out, value);
            if (fuelType == null){
            return;}
            isAddExecuted = false;
            Vehicle vehicle = new Vehicle(getId(), name, xCoordinate, yCoordinate, enginePower, vehicleType, fuelType);
            this.collectionEditor.addElement(vehicle);
        }



    }




    private void setNameUsingUserInput(PrintStream out, String value) {
            out.print("name: ");
            out.println(value);
            this.name = value;
            if (name.length() == 0) {
                out.println("vehicle can't be nameless");
            }
    }

    private void setXCoordinateUsingUserInput(PrintStream out, String value) {
            String coordinateXInString = "";
            try {
                out.print("x: ");
                out.println(value);
                coordinateXInString = value;
                xCoordinate = Float.parseFloat(coordinateXInString);
                if (xCoordinate <= -958) {
                    out.println("Expected X to be greater than -958");
                }
            } catch (NumberFormatException e) {
                if (coordinateXInString.length() == 0) {
                    out.println("you didn't enter a coordinate, try better");
                } else {
                    out.println("wrong number format \"" + coordinateXInString + "\""); //а почему я в catch не могу использовать variable из try?????
                }
            }
    }

    private void setYCoordinateUsingUserInput(PrintStream out, String value) {
            String coordinateYInString = "";
            try {
                out.print("y: ");
                out.println(value);
                coordinateYInString = value;
                yCoordinate = Float.parseFloat(coordinateYInString);
            } catch (NumberFormatException e) {
                if (coordinateYInString.length() == 0) {
                    out.println("you didn't enter a coordinate, try better");
                } else {
                    out.println("wrong number format \"" + coordinateYInString + "\""); //а почему я в catch не могу использовать variable из try?????
                }
            }

    }

    private void setEnginePowerUsingUserInput(PrintStream out, String value) {
            String enginePowerInString = "";
            try {
                out.print("enginePower: ");
                out.println(value);
                enginePowerInString = value;
                float enginePower = Float.parseFloat(enginePowerInString);
                setEnginePower(enginePower);
                if (enginePower < 0) {
                    out.println("Expected engine power to be greater than 0");
                }
            } catch (NumberFormatException e) {
                if (enginePowerInString.length() == 0) {
                    out.println("you didn't enter an engine power, try better");
                } else {
                    out.println("wrong number format \"" + enginePowerInString + "\""); //а почему я в catch не могу использовать variable из try?????
                }
            }
    }


    private void setVehicleTypeUsingUserInput(PrintStream out, String value) {
            vehicleTypeIsAlreadySet = true;
            String vehicleTypeInString = "";
            try {
                String tip = getEnumTip(VehicleType.class.getEnumConstants());
                out.print("type (" + tip + "): ");
                out.println(value);
                vehicleTypeInString = value;
                VehicleType type = VehicleType.valueOf(vehicleTypeInString.toUpperCase());
                setType(type);
            } catch (IllegalArgumentException e) {
                if (vehicleTypeInString.length() == 0) {
                    setType(null);
                    //out.println("you didn't enter a vehicle type, try better");
                } else {
                    out.println("wrong vehicle type format \"" + vehicleTypeInString + "\""); //а почему я в catch не могу использовать variable из try?????
                }
            }

    }

    private void setFuelTypeUsingUserInput(PrintStream out, String value) {
            String fuelTypeInString = "";
            try {
                String fuelTip = getEnumTip(FuelType.class.getEnumConstants());
                out.print("fuelTip (" + fuelTip + "): ");
                out.println(value);
                fuelTypeInString = value;
                FuelType type = FuelType.valueOf(fuelTypeInString.toUpperCase());
                setFuelType(type);
            } catch (IllegalArgumentException e) {
                if (fuelTypeInString.length() == 0) {
                    out.println("you didn't enter a fuel type, try better");
                } else {
                    out.println("wrong fuel type format \"" + fuelTypeInString + "\""); //а почему я в catch не могу использовать variable из try?????
                }
            }
    }

    private int getId() {
        if (collectionEditor.isCollectionEmpty()) {
            return 1;
        } else {
            return collectionEditor.getMaxIdOfCollection() + 1;
        }
    }

    private void resetVariables(){
       String name = ""; //Поле не может быть null, Строка не может быть пустой
          enginePower = null; //Значение поля должно быть больше 0
         vehicleType= null; //Поле может быть null
        fuelType= null; //Поле не может быть null
         vehicleTypeIsAlreadySet = false;
        xCoordinate= null;
        yCoordinate= null;
         isAddExecuted = false;
    }


    private String getEnumTip(Enum[] constants) {
        return Arrays.stream(constants).map(Enum::toString).reduce((a, b) -> a + " | " + b).get();
    }

    public void setType(VehicleType type) {
        this.vehicleType = type;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public void setEnginePower(float enginePower) {
        this.enginePower = enginePower;
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



