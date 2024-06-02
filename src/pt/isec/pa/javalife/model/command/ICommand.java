package pt.isec.pa.javalife.model.command;

public interface ICommand {

    void undo() throws InterruptedException;
    void execute() throws InterruptedException, CloneNotSupportedException;
}
