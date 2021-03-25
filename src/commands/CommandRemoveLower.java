package commands;

import utility.CollectionEditor;

public class CommandRemoveLower implements CommandWithArgument {
    private CollectionEditor collectionEditor;
    private String userEnteredId;
    private int id;


    public CommandRemoveLower(CollectionEditor collectionEditor) {
        this.collectionEditor = collectionEditor;
    }

    @Override
    public String execute() {
        if (isArgumentANumber(userEnteredId)) {
            if (collectionEditor.removeElementsWithLowerId(id)) { //вот тут вызов другой функции
                return "all elements with id lower that " + userEnteredId + " was successfully deleted\n";
            } else {
                return "there is no elements with id lower that  " + userEnteredId;
            }
        } else {
            return "invalid number format \"" + userEnteredId + "\", try better";
        }

    }


    @Override
    public String getDescription() {
        return "remove all elements with lower id";
    }

    private boolean isArgumentANumber(String argument) {
        try {
            id = Integer.parseInt(argument);
            return true;
        } catch (NumberFormatException e) {
            try {
                id = (int) Double.parseDouble(argument) + 1;
                return true;
            } catch (NumberFormatException exception){
                return false;
            }

        }
    }


    public void getArgumentFromOutside(String argument) {
        userEnteredId = argument;
    }

    @Override
    public String getSyntacticsExample(){
        return getName() + " 48";
    }



}
