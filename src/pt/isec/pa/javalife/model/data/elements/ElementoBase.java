package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.Area;

public abstract sealed class ElementoBase
        implements IElemento
        permits Inanimado, Flora, Fauna {
    private int id;
    private Area area;
    private Elemento tipo;

    public ElementoBase(int id, Area area, Elemento tipo) {
        this.id = id;
        this.area = area;
        this.tipo = tipo;
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
}