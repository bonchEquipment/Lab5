package comands;

import utility.*;
import java.io.IOException;


public class CommandSave implements Command {
    private FileManager fileManager;

    /**
     *
     * @param fileManager
     */
    public CommandSave(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    /**
     *
     * @return nothing or message about error if unable to save
     */
    public String execute() {
        try {
            fileManager.saveCollectionInFile();
            return "collection was saved";
        } catch (IOException e) {
            return "something wrong with the file";
        }


    }

    /**
     *
     * @return description of a command
     */
    public String getDescription(){
        return "saving collection in a file";
}
}


