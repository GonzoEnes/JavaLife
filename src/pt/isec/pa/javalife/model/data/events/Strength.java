package pt.isec.pa.javalife.model.data.events;

import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;
import pt.isec.pa.javalife.model.data.elements.Elemento;

public final class Strength extends BaseEvent implements IEvent {
    private int id;
    public Strength(EcossistemaManager ecossistema) {
        super(ecossistema);
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void apply() { // lógica de aplicar a injecao de força (pode estar errado)
        ecossistema.addStrengthEvent(id, Elemento.FAUNA);
    }

    @Override
    public Event getTipo() {
        return Event.STRENGTH;
    }
}
