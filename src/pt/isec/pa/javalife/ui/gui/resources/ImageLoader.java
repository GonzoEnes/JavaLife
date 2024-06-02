package pt.isec.pa.javalife.ui.gui.resources;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public static List<String> loadAllImagesFromDirectory(String directoryPath) {
        List<String> imageNames = new ArrayList<>();
        File directory = new File("./src/pt/isec/pa/javalife/ui/gui/resources/images/" + directoryPath);
        System.out.println(directory.getAbsolutePath());
        File[] fList = directory.listFiles();
        if (fList != null) {
            for (File file : fList) {
                if (file.isFile()) {
                    String fileName = file.getName().toLowerCase();
                    if (fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
                        imageNames.add(fileName);
                    }
                }
            }
        }
        return imageNames;
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
}
