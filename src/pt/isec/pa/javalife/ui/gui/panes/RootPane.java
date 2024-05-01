package pt.isec.pa.javalife.ui.gui.panes;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;

import java.util.Stack;

public class RootPane extends BorderPane {
    private EcossistemaManager manager;
    private EcossistemaUI board;
    private StackPane stackPane;

    private MainMenuUI mainMenuUI;

    public RootPane(EcossistemaManager manager) {
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

        EcossistemaUI ecossistemaUI = new EcossistemaUI(manager);
        stackPane.getChildren().add(ecossistemaUI);
        ecossistemaUI.requestFocus();
    }
}
