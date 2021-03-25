package commands;

public interface CommandWithArgument extends Command {

    void getArgumentFromOutside(String argument);

    String getSyntacticsExample();
}
