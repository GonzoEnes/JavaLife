package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.Area;

public sealed class Fauna extends ElementoBase implements IElementoComForca permits Animal {
    private double forca;

    public Fauna(int id, Area area, Elemento tipo) {
        super(id, area, tipo);
        this.forca = 50;
    }

    @Override
    public double getForca() {
        return forca;
    }

    @Override
    public void setForca(double forca) {
        this.forca = forca;
    }

    @Override
    public Elemento getType() {
        return Elemento.FAUNA;
    }

}
