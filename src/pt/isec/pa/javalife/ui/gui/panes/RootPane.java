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

    Pane configPane;

    public RootPane(EcossistemaManager manager) {
        this.manager = manager;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        setTop(new VBox(new EcossistemaMenu(manager)));
        InitialConfigScreen initialConfigScreen = new InitialConfigScreen(manager);
        configPane = new Pane(initialConfigScreen);
        //EcossistemaUI ecossistemaUI = new EcossistemaUI(manager);
        //Pane ecossistemaPane = new Pane(ecossistemaUI);
        setCenter(initialConfigScreen);

        /*mainMenuUI = new MainMenuUI(manager);
        this.stackPane = new StackPane(mainMenuUI);
        this.setCenter(stackPane);*/
    }

    private void registerHandlers() {

    }

    private void update() {
    }
}
