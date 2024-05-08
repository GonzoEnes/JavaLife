package pt.isec.pa.javalife.model.data.events;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.IElemento;

public final class Forca extends EventoBase implements IEvento{

    public Forca(Evento tipo, Ecossistema ecossistema) {
        super(tipo, ecossistema);
    }

    @Override
    public boolean apply(int id) { // lógica de aplicar a injecao de força (pode estar errado)
        for (IElemento elemento : ecossistema.getElementos()) {
            if (elemento instanceof Animal && elemento.getId() == id) {
                ((Animal)elemento).setForca(0);
                return true;
            }
        }

        return false;
    }

    @Override
    public Evento getTipo() {
        return Evento.FORCA;
    }
}
