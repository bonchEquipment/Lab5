package utility;

import collection.Vehicle;
import com.google.gson.reflect.TypeToken;
import exceptions.NoReadPermissionException;

import java.io.FileNotFoundException;
import java.util.LinkedList;

/**
 * class for giving the only instance of vehicle collection
 */
public class ClassCollection {

    private static LinkedList<Vehicle> list;

    public static LinkedList<Vehicle> getList() {
        if (list == null){
            FileManager<Vehicle> fileManager = new FileManager<Vehicle>("LAB5",new TypeToken<LinkedList<Vehicle>>(){}.getType());
            try {
                list = new LinkedList<>(); //doing it in case that exception will be thrown
                list = fileManager.readCollection();
            } catch (NoReadPermissionException | FileNotFoundException e) {
                //...
            }
        }
        return list;
    }
}
