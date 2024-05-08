package pt.isec.pa.javalife.model.command;

import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;

public class CommandAdapter implements ICommand {

    protected EcossistemaManager manager;

    public CommandAdapter(EcossistemaManager manager) {
        this.manager = manager;
    }
    @Override
    public boolean undo() throws InterruptedException {
        return false;
    }

    @Override
    public boolean execute() throws InterruptedException {
        return false;
    }
}
