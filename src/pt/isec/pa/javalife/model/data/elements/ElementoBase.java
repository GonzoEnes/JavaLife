package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.area.Area;

import java.io.Serializable;

public abstract sealed class ElementoBase implements IElemento, Cloneable, Serializable permits Inanimado, Flora, Fauna {
    private int id;
    private Area area;

    public ElementoBase(int id, Area area) {
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
    public Elemento getType() {
        return null;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public void setPos(double x, double y) {
        double height = area.baixo() - area.cima();
        double width = area.direita() - area.esquerda();
        setArea(new Area(x, y, x + width, y + height));
    }

    @Override
    public IElemento clone() throws CloneNotSupportedException{
        return (IElemento) super.clone();
    }

    @Override
    public String toString() {
        return "ElementoBase{" +
                "id=" + id +
                ", area=" + area +
                '}';
    }
}