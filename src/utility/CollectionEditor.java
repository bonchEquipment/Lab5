package utility;

import collection.FuelType;
import collection.IdHolder;
import collection.Vehicle;
import collection.VehicleType;
import exceptions.NoElementWithSuchIdException;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedList;


/**
 * class for working with the collection
 */
public class CollectionEditor {
    private LinkedList<Vehicle> collection;
    private ZonedDateTime initializationDate;

    /**
     *
     * @param collection with witch class editor will work
     */
    public CollectionEditor(LinkedList<Vehicle> collection) {
        this.initializationDate = ZonedDateTime.now();
        this.collection = collection;
    }

    /**
     *
     * @return the average engine power of all items in the collection
     */
    public String getAverageOfEnginePower() {
        if (!collection.isEmpty()) {
            int amountOfElements = 0;
            float sumOfEnginePowers = 0;
            for (Vehicle collectionElement : collection) {
                amountOfElements++;
                sumOfEnginePowers += collectionElement.getEnginePower();
            }
            float avarageEnginePower = sumOfEnginePowers / amountOfElements;
            return "average engine power of all vehicle in a collection: " +
                    String.valueOf(avarageEnginePower) + "\n";
        } else {
            return "unable to count average engine power because collection is empty";
        }
    }

    /**
     * cleans collection
     */
    public void cleanCollection() {
        collection.clear();
    }

    /**
     *
     * @return all information about collection (size, initializationDate, type of collection, stored type)
     */
    public String getInformationAboutCollection() {
            String size = String.valueOf(collection.size());
            if (collection.isEmpty()){
               size = "collection is empty";
            }
            String initializationDate = DateTimeFormatter.ISO_DATE_TIME.format(this.initializationDate);
            String type = collection.getClass().getTypeName();
            String storedType = "Vehicle";
            String res = "";
            res += "information about collection:" +
                    "\nsize                 | " + size +
                    "\ninitializationDate   | " + initializationDate +
                    "\ntype of collection   | " + type +
                    "\nstored type          | " + storedType +
                    "\n";
            return res;

    }

    /**
     *
     * @return Fuel type field values in descending order
     */
    public String getTheFuelTypeFieldValuesInDescendingOrder() {
        if (!collection.isEmpty()) {
            String res = "fuel Type field values of all elements in descending order:\n";
            for (int maxOrdinalAtTheMoment = 4; maxOrdinalAtTheMoment >= 0; maxOrdinalAtTheMoment--) {
                for (Vehicle collectionElement : collection) {
                    if (collectionElement.getFuelType().ordinal() == maxOrdinalAtTheMoment) {
                        res += collectionElement.getFuelType().toString() + "\n";
                    }
                }
            }
            return res;
        } else {
            return "collection is clear, so theres no objects to show their fuel type ¯\\_(ツ)_/¯";
        }
    }

    /**
     *  ensures that there is no element with this id in the collection
     * @param
     */
    public void removeById(int id) {
        for (Vehicle collectionElement : collection) {
            if (collectionElement.getId() == id) {
                collection.remove(collectionElement);
            }
        }
    }

    public String getStringInerpretationOfCollection() {
        if (collection.isEmpty()) {
            return "Collection is empty";
        } else {
            String res = "Collection of vehicle: ";
            for (Vehicle collectionElement : collection) {
                res += "\nname          | " + collectionElement.getName() +
                        "\nid            | " + collectionElement.getId() +
                        "\nxCoordinate   | " + collectionElement.getCoordinates().getX() +
                        "\nyCoordinate   | " + collectionElement.getCoordinates().getY() +
                        "\nenginePower   | " + collectionElement.getEnginePower() +
                        "\nVehicleType   | " + collectionElement.getType() +
                        "\nFuelType      | " + collectionElement.getFuelType() +
                        "\ninitialization time | " + DateTimeFormatter.ISO_DATE_TIME.format(collectionElement.getCreationDate()) +
                        "\n--------------------------";

            }
            return res;
        }
    }


    public boolean removeElementsWithGraterId(int id) {
        boolean haveElementWithGraterId = false;
        LinkedList<Vehicle> elementsForDeleting = new LinkedList<>();
        for (Vehicle element : collection) {
            if (element.getId() > id) {
                elementsForDeleting.add(element);
                haveElementWithGraterId = true;
            }
        }
        for (Vehicle vehicle : elementsForDeleting) {
            collection.remove(vehicle);
        }
        //collection.remove(elementsForDeleting);  ////не, а какого фига так не работает????
        return haveElementWithGraterId;
    }

    public boolean removeElementsWithLowerId(int id) {
        boolean haveElementWithGraterId = false;
        LinkedList<Vehicle> elementsForDeleting = new LinkedList<>();
        for (Vehicle element : collection) {
            if (element.getId() < id) {
                elementsForDeleting.add(element);
                haveElementWithGraterId = true;
            }
        }
        for (Vehicle vehicle : elementsForDeleting) {
            collection.remove(vehicle);
        }
        //collection.remove(elementsForDeleting);  ////не, а какого фига так не работает????
        return haveElementWithGraterId;
    }


    public int getMaxIdOfCollection() {
        int maxId = 0;
        for (Vehicle collectionElement : collection) {
            if (collectionElement.getId() > maxId) {
                maxId = collectionElement.getId();
            }
        }
        return maxId;
    }

    public void removeLastElement() {
        collection.remove(collection.size() - 1);
    }

    public int getAmountOfElementWithType(VehicleType type) {
        int amount = 0;
        for (Vehicle vehicle : collection) {
            if (!(vehicle.getType() == null) && vehicle.getType().equals(type)) {
                amount++;
            }
        }
        return amount;
    }

    public void addElement(Vehicle vehicle) {
        collection.add(vehicle);
    }

    public boolean alreadyHaveElementWithSameId(int id) {
        for (Vehicle collectionElement : collection) {
            if (collectionElement.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean isCollectionEmpty() {
        return collection.isEmpty();
    }

    public Collection<Vehicle> getCollection() {
        return collection;
    }

    public void updateElement(Vehicle element) throws NoElementWithSuchIdException {
        int elementId = element.getId();
        int elementIndex = getElementIndexById(elementId);
        collection.set(elementIndex, element);
    }

    public Vehicle getVehicleById(int id) throws NoElementWithSuchIdException {
        for (Vehicle collectionElement : collection) {
            if (collectionElement.getId() == id) {
                return collectionElement;
            }
        }
        throw new NoElementWithSuchIdException();
    }

    public boolean isThereAnElementWithSuchId(int id) {
        for (Vehicle vehicle : collection) {
            if (vehicle.getId() == id) {
                return true;
            }
        }
        return false;
    }

    private int getElementIndexById(int id) throws NoElementWithSuchIdException {
        int index = 0;
        for (Vehicle collectionElement : collection) {
            if (collectionElement.getId() == id) {
                return index;
            }
            index++;
        }
        throw new NoElementWithSuchIdException();
    }

}



