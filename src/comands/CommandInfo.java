package comands;

import collection.Vehicle;
import utility.CollectionEditor;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

public class CommandInfo implements Command {
    private CollectionEditor collectionEditor;

    public CommandInfo(CollectionEditor collectionEditor) {
        this.collectionEditor = collectionEditor;
    }

    @Override
    public String execute() {
      return collectionEditor.getInformationAboutCollection();
    }

    @Override
    public String getDescription() {
        return "gives print information about the collection " +
                "(type, date of initialization, number of elements, etc.)";
    }
}
