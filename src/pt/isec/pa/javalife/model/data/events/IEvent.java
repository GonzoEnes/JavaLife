package pt.isec.pa.javalife.model.data.events;

import pt.isec.pa.javalife.model.data.elements.IElemento;

public interface IEvent {
    Event getTipo();

    void apply();
}
