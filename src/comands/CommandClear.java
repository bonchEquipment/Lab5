package comands;

import collection.Vehicle;

import java.util.Collection;

public class CommandClear implements Command {

    private Collection<Vehicle> collection;

    public CommandClear(Collection<Vehicle> collection){
        this.collection = collection;

    }

    @Override
    public String execute() {
        collection.clear();
        return "Collection was cleaned\n";
    }

    @Override
    public String getDescription() {
        return "deleting all elements from the collection";
    }
}
