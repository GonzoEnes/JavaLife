package pt.isec.pa.javalife.model.data.events;

import pt.isec.pa.javalife.model.data.ecosystem.Ecosystem;

public sealed  class BaseEvent implements IEvent permits Sun, Herbicide, Strength {
    protected final Ecosystem ecosystem;
    public BaseEvent(Ecosystem ecosystem) {
        this.ecosystem = ecosystem;
    }
    @Override
    public Event getTipo() {
        return null;
    }
    @Override
    public void apply() {
    }
}
