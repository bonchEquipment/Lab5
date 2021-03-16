package comands;

import collection.Vehicle;
import utility.CollectionEditor;

import java.util.Collection;


/**
 * class for command "show" realization
 */
public class CommandShow implements Command {

    private CollectionEditor collectionEditor;

    public CommandShow(CollectionEditor collectionEditor) {
        this.collectionEditor = collectionEditor;
    }

    /**
     * @return information about all elements in the collection or says that collection is empty if it is
     */
    @Override
    public String execute() {
        return collectionEditor.getStringInerpretationOfCollection();
    }

    /**
     * @return description of a command
     */
    @Override
    public String getDescription() {
        return "print all elements of the collection to stdout in string representation";
    }
}
