package utility;

import collection.*;
import com.google.gson.*;
import com.google.gson.reflect.*;

import java.lang.reflect.Type;
import java.util.*;
import java.io.*;

/**
 * Operates the file for saving/reading collection.
 */
public class FileManager<T> {

    final String path = System.getenv("LAB5");
    OutputSystem outputSystem = new OutputSystem();
    private Collection<T> collection;
    private Type type;

    /**
     * @param collection with which the class will work
     */
    public FileManager(Collection<T> collection, Type type) {
        this.collection = collection;
        this.type = type;
    }

    /**
     * @throws IOException if file for saving is missing or damaged
     */
    public void saveCollectionInFile(Collection<T> collection) {
        try {
            Gson gson = new Gson();
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(gson.toJson(collection));
            writer.close();
        } catch (IOException e){
            outputSystem.showMessage("you have no right to write to the file");
            System.exit(0);
        }
        //а вот если в конструктор передавать collection и позже использовать его тут, ничего не работает(((
    }

    /**
     * @return LinkedList read from a file
     * @throws IOException only if jsonToString method throw it
     */
    public LinkedList<T> readCollection()  {
        LinkedList<T> list = null;
            String stringInterpretationOfJson = this.jsonToString();
            Gson gson = new Gson();
            list = gson.fromJson(stringInterpretationOfJson, type);
//new TypeToken<LinkedList<Vehicle>>(){}.getType()

            outputSystem.showMessage("you have no right to read the file");
        return list;
    }

    /**
     * @return String interpretation of json file
     * @throws IOException if BufferedInputStream object can't read
     */
    private String jsonToString(){
        String jsonText = "";
        try {
            BufferedInputStream reader = new BufferedInputStream(new FileInputStream(path));
            int i;
            while ((i = reader.read()) != -1) {
                jsonText += String.valueOf((char) i);
            }
            reader.close();

        } catch (java.lang.NullPointerException| IOException e){
            outputSystem.showMessage("you have no right to read the file");
            System.exit(0);
        }
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

    @FunctionalInterface
    public interface TypeTokenFactory {
        Type createTypeToken();
    }

}
