package utility;

import collection.*;
import com.google.gson.*;
import com.google.gson.reflect.*;

import java.util.*;
import java.io.*;

/**
 * Operates the file for saving/reading collection.
 */
public class FileManager {

   final String path = System.getenv("LAB5.2");


    private BufferedInputStream reader;
    private BufferedWriter writer;
    private Gson gson = new Gson();
    private Collection<Vehicle> collection;

    /**
     * @throws IOException if file path is wrong
     */
    public FileManager(Collection<Vehicle> collection) {
        this.collection = collection;
    }

    /**
     * @throws IOException if file for saving is missing or damaged
     */
    public void saveCollectionInFile() throws IOException {
        writer = new BufferedWriter(new FileWriter(path));
        writer.write(gson.toJson(collection));
        writer.close();

    }

    /**
     * @return LinkedList read from a file
     * @throws IOException only if jsonToString method throw it
     */
    public LinkedList<Vehicle> readCollection() throws IOException {
        LinkedList<Vehicle> list;
        String stringInterpretationOfJson = this.jsonToString();
        list = gson.fromJson(stringInterpretationOfJson, new TypeToken<LinkedList<Vehicle>>() {
        }.getType());

        reader.close();
        return list;
    }

    /**
     * @return String interpretation of json file
     * @throws IOException if BufferedInputStream object can't read
     */
    private String jsonToString() throws IOException {
        String jsonText = "";
        reader = new BufferedInputStream(new FileInputStream(path));
        int i;
        while ((i = reader.read()) != -1) {
            jsonText += String.valueOf((char) i);
        }
        reader.close();

        return jsonText;
    }

    public void clear() throws IOException {
        writer = new BufferedWriter(new FileWriter(path));
        writer.write("");
        writer.close();
    }

    /**
     * @return String representation of object
     */
    @Override
    public String toString() {
        return "FileManager object for working with file " + path;
    }
}
