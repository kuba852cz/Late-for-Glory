package Commands;

public interface Command {

    public String execute(String targetingName);
    public boolean exit();


}
