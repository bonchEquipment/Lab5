package utility;

import collection.*;
import exceptions.*;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


/**
 * class for working with the collection
 */
public class CollectionEditor {
    private LinkedList<Vehicle> collection;
    private ZonedDateTime initializationDate;

    /**
     * constructor is private, so there is only one instance of that class
     */
    public CollectionEditor() {
        this.initializationDate = ZonedDateTime.now();
        this.collection = ClassCollection.getList();
    }

    /**
     * return the average engine power of all items in the collection
     */
    public String getAverageOfEnginePower() {
        if (!collection.isEmpty()) {
            int amountOfElements = 0;
            float sumOfEnginePowers = 0;
            for (Vehicle collectionElement : collection) {
                amountOfElements++;
                sumOfEnginePowers += collectionElement.getEnginePower();
            }
            float averageEnginePower = sumOfEnginePowers / amountOfElements;
            return "average engine power of all vehicle in a collection: " +
                    String.valueOf(averageEnginePower) + "\n";
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
     * return all information about collection (size, initializationDate, type of collection, stored type)
     */
    public String getInformationAboutCollection() {
        String size = String.valueOf(collection.size());
        if (collection.isEmpty()) {
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
     * return Fuel type field values in descending order
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
     * ensures that there is no element with this id in the collection
     *
     * @param id of element you want to remove
     */
    public void removeById(int id) {
        for (Vehicle collectionElement : collection) {
            if (collectionElement.getId() == id) {
                collection.remove(collectionElement);
            }
        }
    }

    /**
     * return list of collection elements with their parameters
     */
    public String getStringInterpretationOfCollection() {
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

    /**
     * removing all elements with id greater that the one you specified
     *
     * @param id of the element, all elements greater than which you want to remove
     * @return true if collection changed after operation
     */
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
        //а какого фига я не могу в ходе итерации (на 7 строчек выше) удалять элементы, а
        //вынужден собирать коллекцию элементов для удаления??
        //collection.remove(elementsForDeleting);  ////не, а какого фига так не работает????
        return haveElementWithGraterId;
    }


    /**
     * removing all elements with id lower that the one you specified
     *
     * @param id of the element, all elements lower than which you want to remove
     * @return true if collection changed after operation
     */
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


    /**
     * return the biggest id in the collection or return 0 if there is no element
     *
     * @return max id of the collection
     */
    public int getMaxIdOfCollection() {
        int maxId = 0;
        for (Vehicle collectionElement : collection) {
            if (collectionElement.getId() > maxId) {
                maxId = collectionElement.getId();
            }
        }
        return maxId;
    }

    /**
     * removing last element of the collection
     */
    public void removeLastElement() {
        collection.remove(collection.size() - 1);
    }

    /**
     * return the amount of elements with the same type
     *
     * @param type of Vehicle
     */
    public int getAmountOfElementWithType(VehicleType type) {
        int amount = 0;
        for (Vehicle vehicle : collection) {
            if (!(vehicle.getType() == null) && vehicle.getType().equals(type)) {
                amount++;
            }
        }
        return amount;
    }

    /**
     * adding vehicle to the collection
     *
     * @param vehicle you want to add to the collection
     */
    public void addElement(Vehicle vehicle) {
        collection.add(vehicle);
    }

    /**
     * return boolean depends on whether element in the collection or not
     *
     * @param id of the element you want to check
     */
    public boolean alreadyHaveElementWithSameId(int id) {
        for (Vehicle collectionElement : collection) {
            if (collectionElement.getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * says whether collection is empty or not
     *
     * @return boolean
     */
    public boolean isCollectionEmpty() {
        return collection.isEmpty();
    }

    /**
     * return collection
     *
     * @return collection
     */
    public Collection<Vehicle> getCollection() {
        return collection;
    }

    /**
     * replace existing element with some id with another with the same id
     *
     * @param element that we want to put instead of existing
     * @throws NoElementWithSuchIdException if theres no elements in the
     *                                      collection with the id of entered element
     */
    public void updateElement(Vehicle element) throws NoElementWithSuchIdException {
        int elementId = element.getId();
        int elementIndex = getElementIndexById(elementId);
        collection.set(elementIndex, element);
    }

    /**
     * return element with specified id
     *
     * @param id of the element you want to get
     * @throws NoElementWithSuchIdException if theres no elements in the
     *                                      collection with the id of entered element
     */
    public Vehicle getVehicleById(int id) throws NoElementWithSuchIdException {
        for (Vehicle collectionElement : collection) {
            if (collectionElement.getId() == id) {
                return collectionElement;
            }
        }
        throw new NoElementWithSuchIdException();
    }

    /**
     *  says whether collection have element with specified id or not
     *
     * @param id integer value
     * @return boolean
     */
    public boolean isThereAnElementWithSuchId(int id) {
        for (Vehicle vehicle : collection) {
            if (vehicle.getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * gives you an element by it index (or throws an exception at you
     * @param id of the element
     * @return int value
     * @throws NoElementWithSuchIdException if element not found
     */
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



