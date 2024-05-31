package pt.isec.pa.javalife.ui.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pt.isec.pa.javalife.Main;
import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;
import pt.isec.pa.javalife.model.data.elements.Elemento;
import pt.isec.pa.javalife.model.gameengine.GameEngine;
import pt.isec.pa.javalife.ui.gui.panes.RootPane;

public class JavaLifeFX extends Application {
    private EcossistemaManager manager;

    public JavaLifeFX() {
        this.manager = new EcossistemaManager();
    }

    @Override
    public void start(Stage stage) throws Exception {
        firstStage(stage);
    }

    private void firstStage(Stage stage) {
        RootPane rootPane = new RootPane(manager, stage);
        Scene scene = new Scene(rootPane, 700,700);
        stage.setScene(scene);
        stage.setTitle("JavaLife - Trabalho Académico");
        stage.show();
    }
}
