package pt.isec.pa.javalife.model.command.commands;

import pt.isec.pa.javalife.model.command.CommandAdapter;
import pt.isec.pa.javalife.model.data.ecosystem.EcosystemManager;
import pt.isec.pa.javalife.model.data.elements.Element;
import pt.isec.pa.javalife.model.data.elements.IElement;

public class RemoveElementoCmd extends CommandAdapter {
    private IElement elemento;
    private Element type;
    private int id;

    public RemoveElementoCmd(EcosystemManager manager, int id, Element type) {
        super(manager);
        this.id=id;
        this.type=type;
    }
    @Override
    public void undo() throws InterruptedException {
        manager.addElemento(elemento);
    }
    @Override
    public void execute() throws CloneNotSupportedException {
         elemento = manager.removeElementoCmd(id,type);
    }
}
