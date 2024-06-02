package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.area.Area;

public sealed class Inanimado extends ElementBase permits Pedra {
    private static int idCounter = 0;

    public Inanimado(Area area) {
        super(++idCounter, area);
    }
    @Override
    public Element getType() {
        return Element.INANIMADO;
    }
    @Override
    public void resetCounterId() {
        idCounter = 0;
    }
}
