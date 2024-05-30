package pt.isec.pa.javalife.ui.gui.panes;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pt.isec.pa.javalife.Main;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;

import java.util.Stack;

public class RootPane extends BorderPane {

    EcossistemaManager manager;

    Pane configPane;

    Stage stage;

    public RootPane(EcossistemaManager manager, Stage stage) {
        this.manager = manager;
        this.stage = stage;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        setTop(new VBox(new EcossistemaMenu(manager, stage)));
        MainMenuUI mainMenuUI = new MainMenuUI(manager, stage);
        configPane = new BorderPane(mainMenuUI);
        setCenter(configPane);
    }

    private void registerHandlers() {
    }

    private void update() {

    }
}