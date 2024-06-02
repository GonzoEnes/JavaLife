package pt.isec.pa.javalife.ui.gui.panes;

import javafx.scene.layout.BorderPane;
import pt.isec.pa.javalife.model.data.ecosystem.EcosystemManager;

public class RootPane extends BorderPane {

    EcosystemManager manager;
    public RootPane(EcosystemManager manager) {
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