package pt.isec.pa.javalife.model.data.events;

import pt.isec.pa.javalife.model.data.Ecossistema;

public sealed abstract class EventoBase implements IEvento permits Sol, Herbicida, Forca {
    private final Evento tipo;

    protected final Ecossistema ecossistema;

    public EventoBase(Evento tipo, Ecossistema ecossistema) {
        this.tipo = tipo;
        this.ecossistema = ecossistema;
    }

    @Override
    public Evento getTipo() {
        return tipo;
    }

    @Override
    public boolean apply(int id) {
        return false;
    }
}
