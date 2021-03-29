package commands;

import exceptions.NoWritePermissionException;
import utility.*;

import java.io.FileNotFoundException;

/**
 * saving collection in a file
 */
public class CommandSave implements Command {

    private FileManager fileManager;
    private CollectionEditor collectionEditor;

    /**
     *
     * @param collectionEditor for executing it function
     * @param fileManager for working with a file
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
        } catch (FileNotFoundException e){
           return "file not found";
        } catch (NoWritePermissionException e){
            return "no write rights";
        }
    }

    /**
     * @return description of a command
     */
    public String getDescription() {
        return "saving collection in a file";
    }
}


