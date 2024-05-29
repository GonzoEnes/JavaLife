package pt.isec.pa.javalife.ui.gui.resources;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ImageLoader {
    private static final HashMap<String, Image> images = new HashMap<>();

    public static Image getImage(String name) {
        Image image  = images.get(name);
        if (image == null) {
            image = new Image(Objects.requireNonNull(ImageLoader.class.getResourceAsStream("images/" + name)));
            images.put(name,image);
        }
        return image;
    }

    public static Image getImageFauna(String name) {
        Image image  = images.get(name);
        if (image == null) {
            image = new Image(Objects.requireNonNull(ImageLoader.class.getResourceAsStream("images/fauna" + name)));
            images.put(name,image);
        }
        return image;
    }

    public static Image getExternalImage(String filename) {
        Image image = images.get(filename);
        if (image == null)
            try {
                image = new Image(filename);
                images.put(filename,image);
            } catch (Exception e) {
                return null;
            }
        return image;
    }
    public static void purgeImage(String filename) { images.remove(filename); }

    public static String[] loadAllImagesFromDirectory() {
        File dir = Paths.get("images/fauna").toFile();
        // Filtrar apenas arquivos com extensões de imagem
        FilenameFilter imageFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                return lowercaseName.endsWith(".jpg") || lowercaseName.endsWith(".png");
            }
        };
        File[] files = dir.listFiles(imageFilter);
        ArrayList<String> fileNames = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                Image image = new Image(file.toURI().toString());
                images.put(file.getName(), image);
                fileNames.add(file.getName());
            }
        }
        return fileNames.toArray(new String[0]);
    }
}
