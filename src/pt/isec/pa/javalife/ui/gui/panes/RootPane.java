package pt.isec.pa.javalife.ui.gui.panes;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;

public class RootPane extends BorderPane {
    private EcossistemaManager manager;
    private EcossistemaUI board;
    private StackPane stackPane;

    public RootPane(EcossistemaManager manager) {
        this.manager = manager;
        createViews();
    }

    public void createViews() {
        board = new EcossistemaUI(manager);
        this.stackPane = new StackPane(board);
        this.setCenter(stackPane);
    }

    public void showBoard() {
        StackPane stackPane = (StackPane)this.getCenter();
        stackPane.getChildren().clear();

        EcossistemaUI ecossistemaUI = new EcossistemaUI(manager);
        stackPane.getChildren().add(ecossistemaUI);
        ecossistemaUI.requestFocus();
    }
}
