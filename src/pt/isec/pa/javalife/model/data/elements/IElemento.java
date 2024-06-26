package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.area.Area;

import java.io.Serializable;

public sealed interface IElemento extends Serializable, Cloneable permits ElementoBase {
    int getId();
    Elemento getType();
    Area getArea();
    IElemento clone() throws CloneNotSupportedException;
}
