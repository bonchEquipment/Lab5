package commands;

import utility.CollectionEditor;

public class CommandClear implements Command {


    private CollectionEditor collectionEditor;

    public CommandClear(CollectionEditor collectionEditor) {
        this.collectionEditor = collectionEditor;
    }

    @Override
    public String execute() {
        if (collectionEditor.isCollectionEmpty()) {
            return "Collection is already empty\n";
        } else {
            collectionEditor.cleanCollection();
            return "Collection successfully was cleaned (⌒‿⌒)\n";
        }
    }

    @Override
    public String getDescription() {
        return "deleting all elements from the collection";
    }
}
