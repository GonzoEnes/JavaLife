package pt.isec.pa.javalife.model.command.commands;

import pt.isec.pa.javalife.model.command.CommandAdapter;
import pt.isec.pa.javalife.model.data.EcossistemaManager;
import pt.isec.pa.javalife.model.data.elements.Elemento;

import java.util.ArrayList;

public class EditElementoCmd extends CommandAdapter {
    private Elemento tipo;
    private int id;
    private ArrayList<String> parametros;
    public EditElementoCmd(EcossistemaManager manager, Elemento tipo, int id, ArrayList<String> parametros) {
        super(manager);
        this.tipo = tipo;
        this.id = id;
        this.parametros = parametros;
    }

    @Override
    public boolean undo() {
        // perceber como vamos fazer a edição, temos de ir buscar os valores antigos da edição antes para voltar
        // a aplicar com os valores antigos
        return true;
    }

    @Override
    public boolean execute() { // faz o edit()
        return manager.editElemento(tipo, id, parametros);
    }
}
