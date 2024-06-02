package pt.isec.pa.javalife.model.command.commands;

import pt.isec.pa.javalife.model.command.CommandAdapter;
import pt.isec.pa.javalife.model.data.ecosystem.EcosystemManager;
import pt.isec.pa.javalife.model.data.elements.Element;
import pt.isec.pa.javalife.model.data.elements.IElement;

public class EditElementoCmd extends CommandAdapter {
    private IElement elemento;
    private Element type;
    private double strength;
    private int id;

    public EditElementoCmd(EcosystemManager manager, int id, double strength, Element type) {
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
