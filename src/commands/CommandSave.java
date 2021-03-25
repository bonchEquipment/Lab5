package commands;

import utility.*;

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
            fileManager.saveCollectionInFile(collectionEditor.getCollection());
            return "collection was saved";
    }

    /**
     * @return description of a command
     */
    public String getDescription() {
        return "saving collection in a file";
    }
}


