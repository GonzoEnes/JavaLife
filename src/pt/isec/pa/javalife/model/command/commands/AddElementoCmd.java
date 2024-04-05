package pt.isec.pa.javalife.model.command.commands;

import pt.isec.pa.javalife.model.command.CommandAdapter;
import pt.isec.pa.javalife.model.data.Area;
import pt.isec.pa.javalife.model.data.EcossistemaManager;
import pt.isec.pa.javalife.model.data.elements.Elemento;

public class AddElementoCmd extends CommandAdapter {

    private Elemento tipo;
    private int id;
    private Area area;
    private String imagem;
    public AddElementoCmd(EcossistemaManager manager, Elemento tipo, int id, Area area, String imagem) {
        super(manager);
        this.id = id;
        this.area = area;
        this.tipo = tipo;
        this.imagem = imagem;
    }

    @Override
    public boolean undo() {
        manager.removeElemento(tipo, id);
        return true;
    }

    @Override
    public boolean execute() {
        manager.addElemento(area, tipo, imagem);
        return true;
    }
}
