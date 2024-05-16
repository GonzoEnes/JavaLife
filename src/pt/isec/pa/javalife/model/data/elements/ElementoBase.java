package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.area.Area;

public abstract sealed class ElementoBase
        implements IElemento
        permits Inanimado, Flora, Fauna {
    private int id;
    private Area area;
    private Elemento tipo;

    private int x,y;

    public ElementoBase(int id, Area area, Elemento tipo, int x, int y) {
        this.id = id;
        this.area = area;
        this.tipo = tipo;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
        return tipo;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public void setTipo(Elemento tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Coordenadas {" + getX() + ";" + getY() + "}";
    }
}