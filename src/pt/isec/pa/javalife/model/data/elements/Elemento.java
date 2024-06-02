package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.ui.gui.resources.ImageLoader;

import java.util.List;

public enum Elemento {
    INANIMADO, FLORA, FAUNA;

    public static IElemento createElemento(Elemento type,Area area, Ecossistema ecossistema) {
        switch (type) {
            case INANIMADO -> {
                return new Pedra(area);
            }
            case FLORA -> {
                List<String> images = ImageLoader.loadAllImagesFromDirectory("flora/");
                int randomIndex = (int) (Math.random() * images.size());
                String randomImage = images.get(randomIndex);
                System.out.println(randomImage+"dasdsa");
                return new Erva(area, ecossistema, randomImage);
            }
            case FAUNA -> {
                List<String> images = ImageLoader.loadAllImagesFromDirectory("fauna/");
                int randomIndex = (int) (Math.random() * images.size());
                String randomImage = images.get(randomIndex);
                System.out.println(randomImage+" "+area+" "+ecossistema);
                return new Animal(area, ecossistema, randomImage);
            }
        }
        return null;
    }
}
