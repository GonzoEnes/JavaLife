package pt.isec.pa.javalife.model.data.events;

import pt.isec.pa.javalife.model.data.ecosystem.EcosystemManager;

public sealed  class BaseEvent implements IEvent permits Sun, Herbicide, Strength {
    protected final EcosystemManager ecossistema;
    public BaseEvent(EcosystemManager ecossistema) {
        this.ecossistema = ecossistema;
    }
    @Override
    public Event getTipo() {
        return null;
    }
    @Override
    public void apply() {
    }
}
