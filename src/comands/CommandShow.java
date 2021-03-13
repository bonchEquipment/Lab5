package comands;

import collection.Vehicle;

import java.util.Collection;


/**
 * class for command "show" realization
 */
public class CommandShow implements Command {

    private Collection<Vehicle> collection;

    public CommandShow(Collection<Vehicle> collection) {
        this.collection = collection;
    }

    /**
     *
     * @return information about all elements in the collection or says that collection is empty if it is
     */
    @Override
    public String execute() {
        if (collection.isEmpty()) {
            return "Collection is empty";
        } else {
            String res = "Collection of vehicle: ";
            for (Vehicle collectionElement : collection) {
               res +=  "\nname          | " + collectionElement.getName() +
                       "\nid            | " + collectionElement.getId() +
                       "\nxCoordinate   | " + collectionElement.getCoordinates().getX() +
                       "\nyCoordinate   | " + collectionElement.getCoordinates().getY() +
                       "\nenginePower   | " + collectionElement.getEnginePower() +
                       "\nVehicleType   | " + collectionElement.getType() +
                       "\nFuelType      | " + collectionElement.getFuelType() +
                       "\n--------------------------";

            }
            return res;
        }
    }

    /**
     *
     * @return description of a command
     */
    @Override
    public String getDescription() {
        return "print all elements of the collection to stdout in string representation";
    }
}
