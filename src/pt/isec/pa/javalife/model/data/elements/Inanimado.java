package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.Area;

public sealed class Inanimado extends ElementoBase permits Pedra {
    public Inanimado(int id, Area area, Elemento tipo) {
        super(id, area, tipo);
    }

    @Override
    public Elemento getType() {
        return Elemento.INANIMADO;
    }
}
