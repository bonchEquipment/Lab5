package comands;

import collection.VehicleType;
import utility.CollectionEditor;

public class CommandCountByType implements CommandWithArgument  {
    private CollectionEditor collectionEditor;
    private String userEnteredType;

    public CommandCountByType(CollectionEditor collectionEditor) {
        this.collectionEditor = collectionEditor;
    }


    public void getArgumentFromOutside(String argument) {
          userEnteredType = argument;
    }

    @Override
    public String execute(){
        int AmountOfElementsWithTheSameTime;
        if (isAType(userEnteredType)){
            VehicleType vehicleType = getTypeFromString(userEnteredType);
            AmountOfElementsWithTheSameTime = collectionEditor.getAmountOfElementWithType(vehicleType);
            return String.valueOf(AmountOfElementsWithTheSameTime);
        } else {
            return "wrong type " + userEnteredType + ", try better";
        }
    }

    @Override
    public String getDescription() {
        return "show the number of elements with the same vehicle type";
    }

    private boolean isAType(String typeForChek){
        try {
            VehicleType vehicleType =  VehicleType.valueOf(typeForChek);
            return true;
        } catch (IllegalArgumentException e){
            return false;
        }
    }

    private VehicleType getTypeFromString(String type){
        return VehicleType.valueOf(type);
    }


    public String getSyntacticsExample(){
        return  " CHOPPER \n"+
                "MOTORCYCLE and SPACESHIP also available";
    }
}
