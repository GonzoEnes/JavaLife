package pt.isec.pa.javalife.model.command.commands;

import pt.isec.pa.javalife.model.command.CommandAdapter;
import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.EcosystemManager;
import pt.isec.pa.javalife.model.data.elements.Element;
import pt.isec.pa.javalife.model.data.elements.IElement;

public class AddElementoCmd extends CommandAdapter {
    private IElement elemento;
    private Element type;
    private Area area;
    private String image;

    public AddElementoCmd(EcosystemManager manager, Area area, Element type,String image) {
        super(manager);
        this.area = area;
        this.type = type;
        this.image = image;
    }
    @Override
    public void undo() {
        manager.removeElementoEvent(elemento);
    }
    @Override
    public void execute() {
        elemento = manager.addElementocmd(area,type,image);
    }
}
