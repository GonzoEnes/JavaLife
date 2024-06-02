package pt.isec.pa.javalife.model.command.commands;

import pt.isec.pa.javalife.model.command.CommandAdapter;
import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;
import pt.isec.pa.javalife.model.data.elements.Elemento;
import pt.isec.pa.javalife.model.data.elements.IElemento;

public class AddElementoCmd extends CommandAdapter {
    private IElemento elemento;
    private Area area;
    private Elemento type;
    public AddElementoCmd(EcossistemaManager manager, Area area,Elemento type) {
        super(manager);
        this.area = area;
        this.type = type;
    }

    @Override
    public void undo() {
        manager.removeElementoEvent(elemento);
    }

    @Override
    public void execute() {
        System.out.println(area.right()+" "+area.down()+" "+type);
        elemento = manager.addElementocmd(area,type);
        System.out.println("Elemento adicionado");
    }
}
