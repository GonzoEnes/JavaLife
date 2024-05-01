package pt.isec.pa.javalife.ui.gui.panes;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;

public class MainMenuUI extends BorderPane {
    private EcossistemaManager manager;
    private Button btnStartSimul, btnExit, btnCredits;

    public MainMenuUI(EcossistemaManager manager) {
        this.manager = manager;
        createViews();
        registerHandlers();
        update();

        this.requestFocus();
    }

    public void createViews() {
        this.setStyle("-fx-background-color: lightgreen;");

        btnStartSimul = new Button("Start");

        btnStartSimul.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-border-width: 4px; -fx-text-fill: white; -fx-font-size: 40; -fx-font-weight: bold");

        btnStartSimul.setPrefWidth(300);

        btnStartSimul.setPrefHeight(80);

        btnExit = new Button("Exit");
        btnExit.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-border-width: 4px; -fx-text-fill: white; -fx-font-size: 40; -fx-font-weight: bold;");
        btnExit.setPrefWidth(300);
        btnExit.setPrefHeight(80);

        btnCredits = new Button("Credits");
        btnCredits.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-border-width: 4px; -fx-text-fill: white; -fx-font-size: 40; -fx-font-weight: bold;");
        btnCredits.setPrefWidth(300);
        btnCredits.setPrefHeight(80);

        VBox vBox = new VBox(btnStartSimul, btnCredits, btnExit);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        this.setCenter(vBox);

    }

    public void registerHandlers() {
        btnStartSimul.setOnAction(actionEvent -> {
            RootPane rootPane = (RootPane) getScene().getRoot();
            rootPane.showEcossistemaUI();
        });

        btnExit.setOnAction(actionEvent -> {
            System.exit(1);
        });
    }

    public void update() {

    }
}
