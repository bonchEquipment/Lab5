package comands;

import utility.*;

import java.io.IOException;
import java.util.Collection;


public class CommandSave implements Command {

    private FileManager fileManager;
    private CollectionEditor collectionEditor;

    /**
     * @param fileManager exist
     */
    public CommandSave( CollectionEditor collectionEditor, FileManager fileManager) {
        this.fileManager = fileManager;
        this.collectionEditor = collectionEditor;
    }

    /**
     * @return nothing or message about error if unable to save
     */
    public String execute() {
        try {
            fileManager.saveCollectionInFile(collectionEditor.getCollection());
            return "collection was saved";
        } catch (IOException e) {
            return "something wrong with the file";
        }


    }

    /**
     * @return description of a command
     */
    public String getDescription() {
        return "saving collection in a file";
    }
}


