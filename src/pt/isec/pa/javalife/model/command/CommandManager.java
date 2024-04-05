package pt.isec.pa.javalife.model.command;

import java.util.ArrayDeque;
import java.util.Deque;

public class CommandManager {

    private Deque<ICommand> undo;

    private Deque<ICommand> redo;

    public CommandManager() {
        this.redo = new ArrayDeque<>();
        this.undo = new ArrayDeque<>();
    }

    public boolean undo() {
        if (undo.isEmpty())
            return false;
        ICommand command = undo.pop();
        command.undo();
        undo.push(command);
        return true;
    }

    public boolean redo() {
        if (redo.isEmpty())
            return false;
        ICommand command = redo.pop();
        command.execute();
        undo.push(command);
        return true;
    }
}
