package pt.isec.pa.javalife.model.data.events;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;
import pt.isec.pa.javalife.model.data.elements.IElemento;

public sealed  class BaseEvent implements IEvent permits Sun, Herbicide, Strength {
    protected final EcossistemaManager ecossistema;

    public BaseEvent(EcossistemaManager ecossistema) {
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
