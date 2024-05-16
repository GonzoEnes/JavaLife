package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.area.Area;

public final class Inanimado extends ElementoBase {
    private static int idS = 0;
    public Inanimado(Area area, Elemento tipo, int x, int y) {
        super(++idS, area, tipo, x, y);
    }

    @Override
    public Elemento getType() {
        return Elemento.INANIMADO;
    }
}
