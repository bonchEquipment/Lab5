package comands;


import collection.Vehicle;

import java.util.Collection;

public class CommandPrintFieldDescendingFuelType implements Command {

    private Collection<Vehicle> collection;

    public CommandPrintFieldDescendingFuelType(Collection<Vehicle> collection) {
        this.collection = collection;
    }

    /**
     * @return the fuel Type field values of all elements in descending order
     */
    @Override
    public String execute() {
        String res = "fuel Type field values of all elements in descending order:\n";
        for (int maxOrdinalAtTheMoment = 4; maxOrdinalAtTheMoment >= 0; maxOrdinalAtTheMoment--){
            for (Vehicle collectionElement : collection) {
                if (collectionElement.getFuelType().ordinal() == maxOrdinalAtTheMoment){
                    res += collectionElement.getFuelType().toString() + "\n";
                }
            }
        }
        return res;
    }

    @Override
    public String getDescription() {
        return "print the fuel Type field values of all elements in descending order";
    }
}
