package pt.isec.pa.javalife.model.command;

public interface ICommand {
    boolean undo();

    boolean execute();
}
