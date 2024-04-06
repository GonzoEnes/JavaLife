package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.area.Area;

public abstract sealed class ElementoBase
        implements IElemento
        permits Inanimado, Flora, Fauna {
    private int id;
    private int x;
    private int y;
    private Area area;
    private Elemento tipo;

    public ElementoBase(int x, int y, int id, Area area, Elemento tipo) {
        this.id = id;
        this.area = area;
        this.tipo = tipo;
        this.x = x;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public void setTipo(Elemento tipo) {
        this.tipo = tipo;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}