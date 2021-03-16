package comands;

import collection.IdHolder;
import collection.Vehicle;
import utility.CollectionEditor;

import java.util.Collection;
import java.util.Scanner;

public class CommandRemoveById implements CommandWithArgument {
    private CollectionEditor collectionEditor;
    private String userEnteredId;
    private int id;

    public CommandRemoveById(CollectionEditor collectionEditor) {
        this.collectionEditor = collectionEditor;
    }

    @Override
    public String execute() {
        if (isArgumentANumber(userEnteredId)) {
            if (collectionEditor.removeById(id)) {
                return "element with id " + id + " was successfully deleted\n";
            } else {
                return "unable to find element with id " + id;
            }
        } else {
            return "invalid number format \"" + userEnteredId + "\", try better";
        }

    }


    public void getArgumentFromOutside(String argument) {
        userEnteredId = argument;
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
        return "remove elements from the collection by id";
    }

    @Override
    public String getSyntacticsExample() {
        return getName() + " 13";
    }

}
//    Scanner IdReader = new Scanner(System.in);
//    String possibleId = IdReader.nextLine();
//        try{
//                id = Integer.parseInt(possibleId);
//                } catch (NumberFormatException e){
//                return  "\"" + possibleId + "\" is not a number ";
//                }