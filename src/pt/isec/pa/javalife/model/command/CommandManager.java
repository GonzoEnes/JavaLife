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
    public void executeCommand(ICommand command) throws InterruptedException, CloneNotSupportedException {
        command.execute();
        undo.push(command);
        redo.clear();
    }
    public void undo() throws InterruptedException {
        if (undo.isEmpty())
            return;
        ICommand command = undo.pop();
        command.undo();
        undo.push(command);
    }
    public void redo() throws InterruptedException, CloneNotSupportedException {
        if (redo.isEmpty())
            return;
        ICommand command = redo.pop();
        command.execute();
        undo.push(command);
    }
}
