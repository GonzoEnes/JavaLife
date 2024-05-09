package pt.isec.pa.javalife.model.command.commands;

import pt.isec.pa.javalife.model.command.CommandAdapter;
import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;
import pt.isec.pa.javalife.model.data.elements.Elemento;
import pt.isec.pa.javalife.model.data.elements.IElemento;

public class RemoveElementoCmd extends CommandAdapter {
    private IElemento elemento;
    public RemoveElementoCmd(EcossistemaManager manager, IElemento elemento) {
        super(manager);
        this.elemento = elemento;
    }

    @Override
    public boolean undo() throws InterruptedException {
        manager.addElemento(elemento);
        return true;
    }

    @Override
    public boolean execute() {
        return manager.removeElemento(elemento);
    }
}
