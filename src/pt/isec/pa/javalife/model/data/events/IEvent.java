package pt.isec.pa.javalife.model.data.events;

import pt.isec.pa.javalife.model.data.elements.IElemento;

public interface IEvent {
    Event getTipo();

    boolean apply(IElemento elemento);
}
