package pt.isec.pa.javalife.model.data.events;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.Flora;
import pt.isec.pa.javalife.model.data.elements.IElemento;

public final class Herbicida extends EventoBase implements IEvento{

    public Herbicida(Evento tipo, Ecossistema ecossistema) {
        super(tipo, ecossistema);
    }

    @Override
    public boolean apply(int id) { // lógica de aplicar herbicida (pode estar errado)
        for (IElemento elemento : ecossistema.getElementos()) {
            if (elemento instanceof Flora && elemento.getId() == id) {
                ((Flora)elemento).setForca(0);
                return true;
            }
        }
        return false;
    }

    @Override
    public Evento getTipo() {
        return Evento.HERBICIDA;
    }
}
