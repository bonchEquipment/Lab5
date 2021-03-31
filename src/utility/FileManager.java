package utility;

import com.google.gson.*;
import exceptions.NoReadPermissionException;
import exceptions.NoWritePermissionException;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Operates the file for saving/reading collection.
 */
public class FileManager<T> {
    private String path;
    private Type typeToken;

    public FileManager(String envVariable, Type typeToken) {
        path = System.getenv(envVariable);
        this.typeToken = typeToken;
    }

    /**
     * saving collection in the file
     *
     * @param collection that will be saved in a file
     * @throws IOException if file for saving is missing or damaged
     */
    public void saveCollectionInFile(Collection<T> collection) throws FileNotFoundException, NoWritePermissionException {
        if (!isFileExist(path)) {
            throw new FileNotFoundException();
        }
        if (!isFileReadable(path)) {
            throw new NoWritePermissionException();
        }
        try {
            Gson gson = new Gson();
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(gson.toJson(collection));
            writer.close();
        } catch (IOException e) {
            //this will never happen
        }
        //а вот если в конструктор передавать collection и позже использовать его тут, ничего не работает(((
    }

    /**
     * give LinkedList with collection reading from a file
     *
     * @return LinkedList read from a file
     * @throws IOException only if jsonToString method throw it
     */
    public LinkedList<T> readCollection() throws FileNotFoundException, NoReadPermissionException {
        LinkedList<T> list = new LinkedList<>();
        if (!isFileExist(path)) {
            throw new FileNotFoundException();
        }
        if (!isFileReadable(path)) {
            throw new NoReadPermissionException();
        }
        try {
            String stringInterpretationOfJson = this.jsonToString();
            Gson gson = new Gson();
            list = gson.fromJson(stringInterpretationOfJson, typeToken);
            //new TypeToken<LinkedList<Vehicle>>(){}.getType()
        } catch (IOException e) {
            //this will never happen
        }
        return list;
    }

    /**
     * give String interpretation of json file
     *
     * @return String interpretation of json file
     * @throws IOException if BufferedInputStream object can't read
     */
    private String jsonToString() throws IOException {
        String jsonText = "";
        BufferedInputStream reader = new BufferedInputStream(new FileInputStream(path));
        int i;
        while ((i = reader.read()) != -1) {
            jsonText += String.valueOf((char) i);
        }
        reader.close();
        return jsonText;
    }

    /**
     * ensure that the file is empty
     * TODO finish the method
     */
    public void clear() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write("");
            writer.close();
        } catch (IOException e) {
            //ну я этот метод никогда не использую, так что потом его додумаю
        }

    }

    /**
     * says whether the file can be read
     *
     * @param path to the file
     * @return boolean
     */
    private boolean isFileReadable(String path) {
        File file = new File(path);
        return file.canRead();
    }

    /**
     * says whether the file exist
     *
     * @param path to the file
     * @return boolean
     */
    private boolean isFileExist(String path) {
        File file = new File(path);
        return file.exists();
    }

    /**
     * says whether it is possible to write to a file
     *
     * @param path to the file
     * @return boolean
     */
    private boolean isFileWritable(String path) {
        File file = new File(path);
        return file.canWrite();
    }

    /**
     * return String representation of object
     *
     * @return String representation of object
     */
    @Override
    public String toString() {
        return "FileManager object for working with file " + path;
    }

}
