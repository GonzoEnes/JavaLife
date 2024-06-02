package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.Ecosystem;
import pt.isec.pa.javalife.ui.gui.resources.ImageLoader;

import java.util.List;

public enum Element {
    INANIMADO, FLORA, FAUNA;

    public static IElement createElement(Element type, Area area, Ecosystem ecossistema) {
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
    public static IElement createElement(Element type, Area area,String image, Ecosystem ecossistema) {
        switch (type) {
            case INANIMADO -> {
                return new Pedra(area);
            }
            case FLORA -> {
                return new Erva(area, ecossistema, image);
            }
            case FAUNA -> {
                return new Animal(area, ecossistema, image);
            }
        }
        return null;
    }
}
