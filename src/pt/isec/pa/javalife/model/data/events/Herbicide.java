package pt.isec.pa.javalife.model.data.events;

import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;
import pt.isec.pa.javalife.model.data.elements.Elemento;
import pt.isec.pa.javalife.model.data.elements.IElemento;

public final class Herbicide extends BaseEvent implements IEvent {

    private int id;
    public Herbicide(EcossistemaManager ecossistema) {
        super(ecossistema);
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public void apply() {
        ecossistema.removeElementoEvent(id,Elemento.FLORA);
    }

    @Override
    public Event getTipo() {
        return Event.HERBICIDE;
    }
}
