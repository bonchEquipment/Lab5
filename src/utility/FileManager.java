package utility;

import collection.*;
import com.google.gson.*;
import com.google.gson.reflect.*;

import java.util.*;
import java.io.*;

/**
 * Operates the file for saving/reading collection.
 */
public class FileManager<T> {

    final String path = System.getenv("LAB5");

    private Collection<T> collection;

    /**
     * @param collection with which the class will work
     */
    public FileManager(Collection<T> collection) {
        this.collection = collection;
    }

    /**
     * @throws IOException if file for saving is missing or damaged
     */
    public void saveCollectionInFile(Collection<T> collection) throws IOException {
        Gson gson = new Gson();
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(gson.toJson(collection));
        writer.close();
        //а вот если в конструктор передавать collection и позже использовать его тут, ничего не работает(((
    }

    /**
     * @return LinkedList read from a file
     * @throws IOException only if jsonToString method throw it
     */
    public LinkedList<T> readCollection() throws IOException {
        LinkedList<T> list;
        String stringInterpretationOfJson = this.jsonToString();
        Gson gson = new Gson();
        list = gson.fromJson(stringInterpretationOfJson, new TypeToken<LinkedList<Vehicle>>() {//!!!!ахуеть, тут если вместо Vehicle вставить T тебя в рантайме пошлют!!!!
        }.getType());
        return list;
    }

    /**
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

    public void clear() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
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
