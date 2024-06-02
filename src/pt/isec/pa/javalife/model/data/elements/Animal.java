package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.Ecosystem;

public final class Animal extends Fauna {
    public Animal(Area area, Ecosystem ecossistema, String image) {
        super(area, ecossistema,image);
    }
}
