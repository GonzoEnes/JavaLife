package pt.isec.pa.javalife.model.data.events;

import pt.isec.pa.javalife.model.data.ecosystem.Ecosystem;
import pt.isec.pa.javalife.model.data.elements.Element;

public final class Herbicide extends BaseEvent implements IEvent {
    private int id;
    public Herbicide(Ecosystem ecosystem) {
        super(ecosystem);
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public void apply() {
        ecosystem.removeElementoEvent(id, Element.FLORA);
    }
    @Override
    public Event getTipo() {
        return Event.HERBICIDE;
    }
}
