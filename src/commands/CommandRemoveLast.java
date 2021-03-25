package commands;

import utility.CollectionEditor;

public class CommandRemoveLast implements Command {

    private CollectionEditor collectionEditor;

    public CommandRemoveLast(CollectionEditor collectionEditor) {
        this.collectionEditor = collectionEditor;
    }

    @Override
    public String execute(){
        if (!collectionEditor.isCollectionEmpty()){
            collectionEditor.removeLastElement();
            return "last element was successfully removed";
        } else {
            return "unable to delete last element because there is no elements in the collection";
        }
    }



    @Override
    public String getDescription() {
        return "remove the last element of the collection";
    }
}
