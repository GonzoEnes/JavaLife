package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.area.Area;

public sealed class Fauna extends ElementoBase implements IElementoComForca permits Animal {
    private double forca;
    private static int idS = 0;

    public Fauna(Area area, Elemento tipo) {
        super(++idS, area, tipo);
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
