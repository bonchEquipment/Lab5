package comands;

import collection.Vehicle;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

public class CommandInfo implements Command {
    private final List<Vehicle> list;

    public CommandInfo(List<Vehicle> list) {
        this.list = list;
    }


    @Override
    public String execute() {
        if (list.isEmpty()){
            return "collection is empty\n"+
                    "type of collection:\n" +
                    list.getClass().getTypeName();
        } else {
            int size = list.size();
            String initializationDate = DateTimeFormatter.ISO_DATE_TIME.format(list.get(0).getCreationDate());
            String type = list.getClass().getTypeName();
            String res = "";
            res += "information about collection:\n" +
                    "size                 | " + size +
                    "\ninitializationDate   | " + initializationDate +
                    "\nType of collection   | " + type +
                    "\n";
            return res;
        }

    }

    @Override
    public String getDescription() {
        return "gives print information about the collection " +
                "(type, date of initialization, number of elements, etc.)";
    }
}
