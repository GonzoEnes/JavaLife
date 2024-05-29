package pt.isec.pa.javalife.model.data.events;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.Elemento;
import pt.isec.pa.javalife.model.data.elements.Flora;
import pt.isec.pa.javalife.model.data.elements.IElemento;

public final class Herbicide extends BaseEvent implements IEvent {

    public Herbicide(Event tipo, Ecossistema ecossistema) {
        super(tipo, ecossistema);
    }

    @Override
    public boolean apply(IElemento elemento) { // lógica de aplicar herbicida (pode estar errado)
        if (elemento.getType() == Elemento.FLORA) {
            ((Flora)elemento).setStrength(0);
            return true;
        }
        return false;
    }

    @Override
    public Event getTipo() {
        return Event.HERBICIDE;
    }
}
