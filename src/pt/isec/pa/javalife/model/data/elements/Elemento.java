package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;

public enum Elemento {
    INANIMADO, FLORA, FAUNA;

    public IElemento createElemento(Ecossistema ecossistema, String imagem) {
        return switch (this) {
            case INANIMADO -> new Inanimado(new Area(0,0,0,0));
            case FLORA -> new Flora(new Area(0,0,0,0), imagem);
            case FAUNA -> new Fauna(new Area(0,0,0,0), ecossistema);
        };
    }
}
