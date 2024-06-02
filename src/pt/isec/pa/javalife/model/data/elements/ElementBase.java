package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.area.Area;

import java.io.Serializable;

public abstract sealed class ElementBase implements IElement, Cloneable, Serializable permits Inanimado, Flora, Fauna {
    private int id;
    private Area area;

    public ElementBase(int id, Area area) {
        this.id = id;
        this.area = area;
    }
    @Override
    public int getId() {
        return id;
    }
    @Override
    public Area getArea() {
        return area;
    }
    @Override
    public Element getType() {
        return null;
    }
    public void setArea(Area area) {
        this.area = area;
    }
    @Override
    public IElement clone() throws CloneNotSupportedException{
        return (IElement) super.clone();
    }
    @Override
    public String toString() {
        return "ElementoBase{" +
                "id=" + id +
                ", area=" + area +
                '}';
    }
}