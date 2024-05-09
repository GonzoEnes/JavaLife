package pt.isec.pa.javalife.model.command.commands;

import pt.isec.pa.javalife.model.command.CommandAdapter;
import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;
import pt.isec.pa.javalife.model.data.elements.Elemento;
import pt.isec.pa.javalife.model.data.elements.IElemento;

public class AddElementoCmd extends CommandAdapter {
    private IElemento elemento;
    public AddElementoCmd(EcossistemaManager manager, IElemento elemento) {
        super(manager);
        this.elemento = elemento;
    }

    @Override
    public boolean undo() {
        return manager.removeElemento(elemento);
    }

    @Override
    public boolean execute() throws InterruptedException {
        manager.addElemento(elemento);
        return true;
    }
}
