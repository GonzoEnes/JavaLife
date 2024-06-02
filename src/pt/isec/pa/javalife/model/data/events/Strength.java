package pt.isec.pa.javalife.model.data.events;

import pt.isec.pa.javalife.model.data.ecosystem.EcosystemManager;
import pt.isec.pa.javalife.model.data.elements.Element;

public final class Strength extends BaseEvent implements IEvent {
    private int id;
    public Strength(EcosystemManager ecossistema) {
        super(ecossistema);
    }
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public void apply() { // lógica de aplicar a injecao de força (pode estar errado)
        ecossistema.addStrengthEvent(id, Element.FAUNA);
    }
    @Override
    public Event getTipo() {
        return Event.STRENGTH;
    }
}
