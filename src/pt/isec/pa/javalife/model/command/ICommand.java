package pt.isec.pa.javalife.model.command;

public interface ICommand {
    boolean undo() throws InterruptedException;

    boolean execute() throws InterruptedException;
}
