package pt.isec.pa.javalife.ui.gui.panes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;

import java.io.File;

public class MainMenuUI extends BorderPane {

    private EcossistemaManager manager;

    private Button btnNewSimul, btnLoadSimul, btnExit;

    Label title;

    Stage stage;

    public MainMenuUI(EcossistemaManager manager, Stage stage) {
        this.manager = manager;
        this.stage = stage;
        createViews();
        registerHandlers();
        update();
        this.requestFocus();
    }

    public void createViews() {
        this.setStyle("-fx-background-color: lightgreen;");

        title = new Label("JavaLife");
        title.setStyle("-fx-font-size: 90px; -fx-font-weight: bold; -fx-text-fill: black; -fx-background-color: darkgreen; -fx-border-color: black; -fx-border-width: 4px; -fx-alignment: center; -fx-padding: 20px;");

        btnNewSimul = new Button("Novo");

        btnNewSimul.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-border-width: 4px; -fx-text-fill: white; -fx-font-size: 40; -fx-font-weight: bold");

        btnNewSimul.setPrefWidth(300);

        btnNewSimul.setPrefHeight(80);

        btnExit = new Button("Exit");
        btnExit.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-border-width: 4px; -fx-text-fill: white; -fx-font-size: 40; -fx-font-weight: bold;");
        btnExit.setPrefWidth(300);
        btnExit.setPrefHeight(80);

        btnLoadSimul = new Button("Carregar");
        btnLoadSimul.setStyle("-fx-background-color: black; -fx-border-color: white; -fx-border-width: 4px; -fx-text-fill: white; -fx-font-size: 40; -fx-font-weight: bold;");
        btnLoadSimul.setPrefWidth(300);
        btnLoadSimul.setPrefHeight(80);

        VBox vBox = new VBox(title, btnNewSimul, btnLoadSimul, btnExit);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        this.setCenter(vBox);
    }

    public void registerHandlers() {
        btnNewSimul.setOnAction(actionEvent -> {
            InitialConfigScreen initialConfigScreen = new InitialConfigScreen(manager);
            BorderPane configPane = new BorderPane(initialConfigScreen);
            setCenter(configPane);
        });

        btnLoadSimul.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("File open...");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Ecossistema (*.csv)", "*.csv"),
                    new FileChooser.ExtensionFilter("All", "*.*")
            );
            File hFile = fileChooser.showOpenDialog(this.getScene().getWindow());
            if (hFile != null) {
                if (manager.load(hFile)) {
                    manager.startEngine();
                    EcossistemaUI ecossistemaUI = new EcossistemaUI(manager);
                    Pane ecossistemaPane = new BorderPane(ecossistemaUI);
                    Scene scene = new Scene(ecossistemaPane, 800, 600);
                    stage.setScene(scene);
                }
            }
        });

        btnExit.setOnAction(actionEvent -> {
            System.exit(1);
        });
    }

    public void update() {

    }
}
