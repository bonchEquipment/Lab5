package comands;

import collection.Vehicle;

import java.util.Collection;

public class CommandAverageOfEnginePower implements Command {

    private Collection<Vehicle> collection;

    public CommandAverageOfEnginePower(Collection<Vehicle> collection){
        this.collection = collection;
    }

    @Override
    public String execute() {
        int amountOfElements = 0;
        float sumOfEnginePowers = 0;
        for (Vehicle collectionElement : collection) {
            amountOfElements++;
            sumOfEnginePowers += collectionElement.getEnginePower();
        }
        float avarageEnginePower = sumOfEnginePowers/ amountOfElements;
        return "average engine power of all vehicle in a collection: " +
                String.valueOf(avarageEnginePower) + "\n";
    }

    @Override
    public String getDescription() {
        return "count an average engine power of all vehicle in a collection";
    }
}
