package commands;

import collection.Vehicle;
import exceptions.NoElementWithSuchIdException;
import utility.CollectionEditor;

import java.util.Scanner;

public class CommandUpdate implements CommandWithArgument {

    private CollectionEditor collectionEditor;
    private String userEnteredId;
    private int id;
    private Scanner scanner;

    public CommandUpdate(CollectionEditor collectionEditor, Scanner scanner) {
        this.collectionEditor = collectionEditor;
        this.scanner = scanner;
    }

    @Override
    public String execute() {
        if (!isArgumentANumber(userEnteredId)) {
            return "invalid id format \"" + userEnteredId + "\", try better";
        }
            try {
                Vehicle oldVehicle = collectionEditor.getVehicleById(id);
                Vehicle newVehicle = new Vehicle(id, System.out, scanner);
                newVehicle.setCreationDate(oldVehicle.getCreationDate());
                collectionEditor.updateElement(newVehicle);
                return "the values of the element was successfully update";
            } catch (NoElementWithSuchIdException e) {
                return "unable to find element with id " + id;
            }

    }

    public void getArgumentFromOutside(String argument) {
        userEnteredId = argument;
    }

    @Override
    public String getSyntacticsExample() {
        return getName() + " 51";
    }

    private boolean isArgumentANumber(String argument) {
        try {
            id = Integer.parseInt(argument);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    @Override
    public String getDescription() {
        return "updating element with the specified id";
    }

}
