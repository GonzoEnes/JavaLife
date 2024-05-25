package pt.isec.pa.javalife.model.data.events;

import pt.isec.pa.javalife.model.data.elements.IElemento;

public interface IEvento {
    Evento getTipo();

    boolean apply(IElemento elemento);
}
