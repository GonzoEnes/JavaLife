package pt.isec.pa.javalife.model.data.events;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.IElemento;

public final class Sun extends BaseEvent implements IEvent {

    public Sun(Event tipo, Ecossistema ecossistema) {
        super(tipo, ecossistema);
    }

    @Override
    public boolean apply(IElemento elemento) {
        // TODO: lógica do Sol
        return true;
    }

    @Override
    public Event getTipo() {
        return Event.SUN;
    }
}
