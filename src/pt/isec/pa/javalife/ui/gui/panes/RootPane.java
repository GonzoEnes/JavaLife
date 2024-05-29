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

    public RootPane(EcossistemaManager manager) {
        this.manager = manager;
        createViews();
        registerHandlers();
        update();
    }
    private void createViews() {
        setTop(new VBox(new EcossistemaMenu(manager)));
        EcossistemaUI mainMenuUI = new EcossistemaUI(manager);
        configPane = new Pane(mainMenuUI);
        setCenter(configPane);
    }
    private void registerHandlers() {
    }
    private void update() {
    }
}