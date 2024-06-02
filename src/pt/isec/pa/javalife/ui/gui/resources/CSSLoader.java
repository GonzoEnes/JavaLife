package pt.isec.pa.javalife.ui.gui.resources;

import javafx.scene.Parent;

public class CSSLoader {
    private CSSLoader() { }

    public static void applyCSS(Parent parent) {
        var url = CSSLoader.class.getResource("css/mystyles.css/");
        if (url == null)
            return;
        String fileCSS = url.toExternalForm();
        parent.getStylesheets().add(fileCSS);
    }
}
