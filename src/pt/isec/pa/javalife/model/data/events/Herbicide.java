package pt.isec.pa.javalife.model.data.events;

import pt.isec.pa.javalife.model.data.ecosystem.EcosystemManager;
import pt.isec.pa.javalife.model.data.elements.Element;

public final class Herbicide extends BaseEvent implements IEvent {
    private int id;
    public Herbicide(EcosystemManager ecossistema) {
        super(ecossistema);
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public void apply() {
        ecossistema.removeElementoEvent(id, Element.FLORA);
    }
    @Override
    public Event getTipo() {
        return Event.HERBICIDE;
    }
}
