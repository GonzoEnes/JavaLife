package pt.isec.pa.javalife.model.command.commands;

import pt.isec.pa.javalife.model.command.CommandAdapter;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;
import pt.isec.pa.javalife.model.data.elements.Elemento;
import pt.isec.pa.javalife.model.data.elements.IElemento;

public class EditElementoCmd extends CommandAdapter {
    private IElemento elemento;
    private int id;
    private double strength;
    private Elemento type;
    public EditElementoCmd(EcossistemaManager manager, int id, double strength,Elemento type) {
        super(manager);
        this.id = id;
        this.strength = strength;
        this.type = type;
    }

    @Override
    public void undo() {
        manager.editElementoUndo(elemento);
    }

    @Override
    public void execute() throws CloneNotSupportedException {
         elemento = manager.editElementoCmd(id, strength,type);
    }
}
