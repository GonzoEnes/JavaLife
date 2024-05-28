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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
        manager = Main.manager;
    }

    @Override
    public void start(Stage stage) throws Exception {
       /* firstStage(stage);
        GameEngine gameEngine = new GameEngine();
        gameEngine.registerClient((g, t) -> {
            Platform.runLater(() -> {
                manager.evolve(g, t);
            });
        });

        gameEngine.start(manager.getTimeInMillis()); */// de acordo com o que o utilizador impõe
        firstStage(stage);
    }

    private void firstStage(Stage stage) {
        RootPane rootPane = new RootPane(manager);
        Scene scene = new Scene(rootPane, 800,600);
        stage.setScene(scene);
        stage.setTitle("JavaLife - Trabalho Académico");
        stage.show();
    } // para propositos de teste, mudar para o menu depois
}
