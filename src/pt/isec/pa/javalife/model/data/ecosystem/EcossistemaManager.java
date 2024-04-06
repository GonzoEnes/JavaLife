package pt.isec.pa.javalife.model.data.ecosystem;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.Elemento;
import pt.isec.pa.javalife.model.data.elements.IElemento;

import java.util.ArrayList;

// esta classe vai servir como Facade do Ecossistema para que as outras classes não consigam manipular o Ecossistema diretamente
public class EcossistemaManager {
    private Ecossistema ecossistema;

    // falta aqui depois o CmdManager e o PropertyChangeSupport (para a sinalização dos clientes) quando fizermos a GUI

    public EcossistemaManager() {
        this.ecossistema = new Ecossistema(50, 50);
    }

    public IElemento addElemento(Area area, Elemento tipo, String imagem, int x, int y) {
        return Ecossistema.addElemento(area, tipo, imagem, x, y);
    }

    public boolean removeElemento(Elemento tipo, int id) {
        return ecossistema.removeElemento(tipo, id);
    }

    public boolean editElemento(Elemento tipo, int id, ArrayList<String> parametros) {
        return ecossistema.editElemento(tipo, id, parametros);
    }
}
