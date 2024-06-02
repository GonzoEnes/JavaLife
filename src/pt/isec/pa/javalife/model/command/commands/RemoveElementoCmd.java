package pt.isec.pa.javalife.model.command.commands;

import pt.isec.pa.javalife.model.command.CommandAdapter;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;
import pt.isec.pa.javalife.model.data.elements.Elemento;
import pt.isec.pa.javalife.model.data.elements.IElemento;

public class RemoveElementoCmd extends CommandAdapter {
    private IElemento elemento;
    private int id;
    private Elemento type;
    public RemoveElementoCmd(EcossistemaManager manager, int id, Elemento type) {
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
