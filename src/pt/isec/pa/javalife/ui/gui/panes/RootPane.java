package pt.isec.pa.javalife.ui.gui.panes;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;

import java.util.Stack;

public class RootPane extends BorderPane {
    EcossistemaManager manager;
    EcossistemaUI ecossistemaUI;
    Pane ecossistemaPane;

    public RootPane(EcossistemaManager manager) {
        this.manager = manager;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        setTop(new VBox(new EcossistemaMenu(manager)));
        ecossistemaUI = new EcossistemaUI(manager);
        ecossistemaPane = new Pane(ecossistemaUI);
        setCenter(ecossistemaPane);
        /*mainMenuUI = new MainMenuUI(manager);
        this.stackPane = new StackPane(mainMenuUI);
        this.setCenter(stackPane);*/
    }

    private void registerHandlers() {
        ecossistemaPane.widthProperty().addListener(observable -> ecossistemaUI.updateSize(ecossistemaPane.getWidth(),ecossistemaUI.getHeight()));
        ecossistemaPane.heightProperty().addListener(observable -> ecossistemaUI.updateSize(ecossistemaPane.getWidth(),ecossistemaPane.getHeight()));
    }

    private void update() {

    }

    /*public void showEcossistemaUI() {
        StackPane stackPane = (StackPane)this.getCenter();
        stackPane.getChildren().clear();

        EcossistemaUI ecossistemaUI = new EcossistemaUI(manager);
        stackPane.getChildren().add(ecossistemaUI);
        ecossistemaUI.requestFocus();
    }*/
}
