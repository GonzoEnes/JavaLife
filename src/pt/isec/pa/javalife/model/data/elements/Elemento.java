package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.ui.gui.resources.ImageLoader;

public enum Elemento {
    INANIMADO, FLORA, FAUNA;

    public static IElemento createElemento(Elemento type,Area area, Ecossistema ecossistema) {
        switch (type) {
            case INANIMADO -> {
                return new Pedra(area);
            }
            case FLORA -> {
                return new Erva(area, ecossistema, "flora.png");
            }
            case FAUNA -> {
                String[] images = ImageLoader.loadAllImagesFromDirectory();
                int randomIndex = (int) (Math.random() * images.length);
                String randomImage = images[randomIndex];
                new Animal(area, ecossistema, randomImage);
            }
        }
        return null;
    }
}
