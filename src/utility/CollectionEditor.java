package utility;

import collection.IdHolder;
import collection.Vehicle;
import collection.VehicleType;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedList;

public class CollectionEditor {
    private LinkedList<Vehicle> collection;

    public CollectionEditor(LinkedList<Vehicle> collection) {
        this.collection = collection;
    }

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

    public void cleanCollection() {
        collection.clear();
    }

    public String getInformationAboutCollection() {
        if (collection.isEmpty()) {
            return "collection is empty\n" +
                    "Type of collection   | " + collection.getClass().getTypeName() + "\n";
        } else {
            int size = collection.size();
            String initializationDate = DateTimeFormatter.ISO_DATE_TIME.format(collection.get(0).getCreationDate());
            String type = collection.getClass().getTypeName();
            String res = "";
            res += "information about collection:\n" +
                    "size                 | " + size +
                    "\ninitializationDate   | " + initializationDate +
                    "\nType of collection   | " + type +
                    "\n";
            return res;
        }
    }

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

    //кстати, сюда нужно прям int отдавать
    //да, пожалуй парсить буду внутри команды
    public boolean removeById(int id) {
        for (Vehicle collectionElement : collection) {
            if (collectionElement.getId() == id) {
                collection.remove(collectionElement);
                return true;
            }
        }
        return false;
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


    public boolean removeGraterThan(int id){
        boolean haveElementWithGraterId = false;
        LinkedList<Vehicle> elementsForDeleting = new LinkedList<>();
        for (Vehicle element: collection){
            if (element.getId() > id){
                elementsForDeleting.add(element);
                haveElementWithGraterId = true;
            }
        }
        for (Vehicle vehicle: elementsForDeleting){
            collection.remove(vehicle);
        }
        //collection.remove(elementsForDeleting);  ////не, а какого фига так не работает????
        return  haveElementWithGraterId;
    }


    public void removeLastElement(){
        collection.remove(collection.size() - 1);
    }

    public int getAmountOfElementWithType (VehicleType type){
        int amount = 0;
        for (Vehicle vehicle: collection){
            if (vehicle.getType().equals(type)){
                amount++;
            }
        }
        return amount;
    }

    public boolean isCollectionEmpty() {
        return collection.isEmpty();
    }

    public Collection<Vehicle> getCollection() {
        return collection;
    }

}
