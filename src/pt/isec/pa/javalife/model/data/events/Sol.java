package pt.isec.pa.javalife.model.data.events;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.IElemento;

public final class Sol extends EventoBase implements IEvento{

    public Sol(Evento tipo, Ecossistema ecossistema) {
        super(tipo, ecossistema);
    }

    @Override
    public boolean apply(IElemento elemento) {
        // TODO: lógica do Sol
        return true;
    }

    @Override
    public Evento getTipo() {
        return Evento.SOL;
    }
}
