package pt.isec.pa.javalife.ui.gui.panes;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;

public class RootPane extends BorderPane {

    EcossistemaManager manager;
    public RootPane(EcossistemaManager manager) {
        this.manager = manager;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        HomePage homePage = new HomePage(manager);
        BorderPane home = new BorderPane(homePage);
        setCenter(home);
    }

    private void registerHandlers() {
    }

    private void update() {

    }
}