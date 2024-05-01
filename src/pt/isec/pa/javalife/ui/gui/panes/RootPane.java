package pt.isec.pa.javalife.ui.gui.panes;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;

import java.util.Stack;

public class RootPane extends BorderPane {
    private EcossistemaManager manager;
    private StackPane stackPane;
    private Stage stage;
    private MainMenuUI mainMenuUI;

    public RootPane(Stage stage, EcossistemaManager manager) {
        this.stage = stage;
        this.manager = manager;
        createViews();
        registerHandlers();
    }

    public void registerHandlers() {

    }

    public void createViews() {
        mainMenuUI = new MainMenuUI(manager);
        /*board = new EcossistemaUI(manager);
        this.stackPane = new StackPane(board);
        this.setCenter(stackPane);*/

        this.stackPane = new StackPane(mainMenuUI);
        this.setCenter(stackPane);
    }

    public void showEcossistemaUI() {
        StackPane stackPane = (StackPane)this.getCenter();
        stackPane.getChildren().clear();

        EcossistemaUI ecossistemaUI = new EcossistemaUI(stage, manager);
        stackPane.getChildren().add(ecossistemaUI);
        ecossistemaUI.requestFocus();
    }
}
