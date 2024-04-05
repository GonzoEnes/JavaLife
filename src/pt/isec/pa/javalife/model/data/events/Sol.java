package pt.isec.pa.javalife.model.data.events;

import pt.isec.pa.javalife.model.data.Ecossistema;

public final class Sol extends EventoBase implements IEvento{

    public Sol(Evento tipo, Ecossistema ecossistema) {
        super(tipo, ecossistema);
    }

    @Override
    public boolean apply(int id) {
        return false;
    }

    @Override
    public Evento getTipo() {
        return Evento.SOL;
    }
}
