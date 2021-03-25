package commands;

import utility.CollectionEditor;

public class CommandRemoveGrater implements CommandWithArgument {
    private CollectionEditor collectionEditor;
    private String userEnteredId;
    private int id;

    public CommandRemoveGrater(CollectionEditor collectionEditor) {
        this.collectionEditor = collectionEditor;
    }

    @Override
    public String execute() {
        if (isArgumentANumber(userEnteredId)) {
            if (collectionEditor.removeElementsWithGraterId(id)) {
                return "all elements with id greater that " + userEnteredId + " was successfully deleted\n";
            } else {
                return "there is no elements with id greater that  " + userEnteredId;
            }
        } else {
            return "invalid number format \"" + userEnteredId + "\", try better";
        }

    }


    @Override
    public String getDescription() {
        return "remove all elements with grater id";
    }

    private boolean isArgumentANumber(String argument) {
        try {
            id = (int) Double.parseDouble(argument);

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public void getArgumentFromOutside(String argument) {
        userEnteredId = argument;
    }

    @Override
    public String getSyntacticsExample(){
        return getName() + " 11";
    }

}
