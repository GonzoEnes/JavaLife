package pt.isec.pa.javalife.model.data.events;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.Elemento;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.elements.IElemento;

public final class Forca extends EventoBase implements IEvento{

    public Forca(Evento tipo, Ecossistema ecossistema) {
        super(tipo, ecossistema);
    }

    @Override
    public boolean apply(IElemento elemento) { // lógica de aplicar a injecao de força (pode estar errado)
        if (elemento.getType() == Elemento.FAUNA) {
            ((Fauna)elemento).setForca(((Fauna)elemento).getForca() + 50);
            return true;
        }
        return false;
    }

    @Override
    public Evento getTipo() {
        return Evento.FORCA;
    }
}
