package pt.isec.pa.javalife.model.data.events;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.Elemento;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.elements.IElemento;

public final class Strength extends BaseEvent implements IEvent {

    public Strength(Event tipo, Ecossistema ecossistema) {
        super(tipo, ecossistema);
    }

    @Override
    public boolean apply(IElemento elemento) { // lógica de aplicar a injecao de força (pode estar errado)
        if (elemento.getType() == Elemento.FAUNA) {
            ((Fauna)elemento).increaseStrength(50);
        }
        return false;
    }

    @Override
    public Event getTipo() {
        return Event.STRENGTH;
    }
}
