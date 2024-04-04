package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.Area;

public sealed class Fauna extends ElementoBase implements IElementoComForca permits Animal {
    private int id;
    private Elemento tipo;
    private Area area;
    private double forca;

    public Fauna() {
        this.forca = 50;
    }
    @Override
    public double getForca() {
        return 0;
    }

    @Override
    public void setForca(double forca) {
        this.forca = forca;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Elemento getType() {
        return tipo;
    }

    @Override
    public Area getArea() {
        return area;
    }
}
