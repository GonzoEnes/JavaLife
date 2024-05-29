package pt.isec.pa.javalife.model.data.events;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.IElemento;

public sealed abstract class BaseEvent implements IEvent permits Sun, Herbicide, Strength {
    private final Event tipo;

    protected final Ecossistema ecossistema;

    public BaseEvent(Event tipo, Ecossistema ecossistema) {
        this.tipo = tipo;
        this.ecossistema = ecossistema;
    }

    @Override
    public Event getTipo() {
        return tipo;
    }

    @Override
    public boolean apply(IElemento elemento) {
        return false;
    }
}
