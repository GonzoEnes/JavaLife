package pt.isec.pa.javalife.model.command;

import pt.isec.pa.javalife.model.data.ecosystem.EcosystemManager;

public class CommandAdapter implements ICommand {
    protected EcosystemManager manager;

    public CommandAdapter(EcosystemManager manager) {
        this.manager = manager;
    }
    @Override
    public void undo() throws InterruptedException {
    }
    @Override
    public void execute() throws CloneNotSupportedException {
    }
}
