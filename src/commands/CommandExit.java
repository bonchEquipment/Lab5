package commands;

/**
 * ends the program
 */
public class CommandExit implements Command{

    @Override
    public String execute() {
        System.exit(0);
        return "this will never be written(((, I mean if it will, there is something wrong";
    }

    @Override
    public String getDescription() {
        return "ends the program";
    }
}
