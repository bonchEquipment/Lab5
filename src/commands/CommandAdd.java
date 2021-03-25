package commands;

import collection.Vehicle;
import utility.CollectionEditor;

import java.util.Scanner;

public class CommandAdd implements Command {

    private CollectionEditor collectionEditor;

    public CommandAdd(CollectionEditor collectionEditor) {
        this.collectionEditor = collectionEditor;
    }

    @Override
    public String execute() {
        Vehicle vehicle = new Vehicle(getId(), System.out, new Scanner(System.in));
        this.collectionEditor.addElement(vehicle);
        return "new element was successfully added to the collection";
    }

    private int getId() {
        if (collectionEditor.isCollectionEmpty()) {
            return 1;
        } else {
            return collectionEditor.getMaxIdOfCollection() + 1;
        }
    }

    @Override
    public String getDescription() {
        return "creating element and adding it to the collection";
    }
}
