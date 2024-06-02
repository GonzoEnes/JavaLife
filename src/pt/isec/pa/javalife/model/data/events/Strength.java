package pt.isec.pa.javalife.model.data.events;

import pt.isec.pa.javalife.model.data.ecosystem.Ecosystem;
import pt.isec.pa.javalife.model.data.elements.Element;

public final class Strength extends BaseEvent implements IEvent {
    private int id;
    public Strength(Ecosystem ecosystem) {
        super(ecosystem);
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public void apply() { // lógica de aplicar a injecao de força (pode estar errado)
        ecosystem.addStrengthEvent(id, Element.FAUNA);
    }
    @Override
    public Event getTipo() {
        return Event.STRENGTH;
    }
}
