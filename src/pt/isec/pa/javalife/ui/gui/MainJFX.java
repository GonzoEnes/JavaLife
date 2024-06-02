package pt.isec.pa.javalife.ui.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import pt.isec.pa.javalife.model.data.ecosystem.EcosystemManager;
import pt.isec.pa.javalife.ui.gui.panes.HomePage;
import pt.isec.pa.javalife.ui.gui.resources.ImageLoader;

public class MainJFX extends Application {
    private EcosystemManager manager;

    public MainJFX() {
        this.manager = new EcosystemManager();
    }

    @Override
    public void start(Stage stage) throws Exception {
        HomePage home = new HomePage(manager);
        Scene scene = new Scene(home, 1000,700);
        stage.setScene(scene);
        stage.setTitle("JavaLife");
        Image icon = ImageLoader.getImage("ecosystem.jpg");
        stage.getIcons().add(icon);
        stage.show();
    }
}
