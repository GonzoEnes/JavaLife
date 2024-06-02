package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.area.Area;

import java.io.Serializable;

public sealed interface IElement extends Serializable, Cloneable permits ElementBase {
    int getId();
    Element getType();
    Area getArea();
    void resetCounterId();
    IElement clone() throws CloneNotSupportedException;
}
