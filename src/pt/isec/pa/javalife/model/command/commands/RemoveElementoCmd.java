package pt.isec.pa.javalife.model.command.commands;

import pt.isec.pa.javalife.model.command.CommandAdapter;
import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;
import pt.isec.pa.javalife.model.data.elements.Elemento;

public class RemoveElementoCmd extends CommandAdapter {
    private Elemento tipo;
    private int id;
    private Area area;
    private String imagem;
    private int x;
    private int y;
    public RemoveElementoCmd(EcossistemaManager manager, Elemento tipo, int id, Area area, String imagem, int x, int y) {
        super(manager);
        this.id = id;
        this.tipo = tipo;
        this.area = area;
        this.imagem = imagem;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean undo() {
        manager.addElemento(area, tipo, imagem, x, y);
        return true;
    }

    @Override
    public boolean execute() {
        return manager.removeElemento(tipo, id);
    }
}
