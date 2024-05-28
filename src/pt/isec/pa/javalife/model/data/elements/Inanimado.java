package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.area.Area;

public sealed class Inanimado extends ElementoBase permits Pedra {
    private static int idS = 0;
    public Inanimado(Area area) {
        super(++idS, area);
    }

    @Override
    public Elemento getType() {
        return Elemento.INANIMADO;
    }
}
