package pt.isec.pa.javalife.ui.gui.panes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;
import pt.isec.pa.javalife.ui.gui.resources.CSSLoader;

import java.io.File;
import java.util.Objects;

public class HomePage extends BorderPane {

    private EcossistemaManager manager;

    private Button btnNew,btnLoad, btnCredits, btnExit;

    public HomePage(EcossistemaManager manager) {
        this.manager = manager;
        createView();
        registerListeners();
        this.requestFocus();
    }
    private void createView() {
        Pane backgroundPane = new Pane();
        String imagePath = Objects.requireNonNull(getClass().getResource("/pt/isec/pa/javalife/ui/gui/resources/images/ecosystem.jpg")).toExternalForm();
        backgroundPane.setStyle("-fx-background-image: url('" + imagePath + "');" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-position: center center;" +
                "-fx-background-size: cover;");
        GaussianBlur blur = new GaussianBlur(10);
        backgroundPane.setEffect(blur);

        Text title = new Text("JavaLife");
        title.setFont(Font.loadFont(getClass().getResourceAsStream("/pt/isec/pa/javalife/ui/gui/resources/fonts/ShadowsIntoLight-Regular.ttf"), 120));
        title.setFill(Color.WHITE);
        DropShadow shadow = new DropShadow();
        shadow.setOffsetY(3.0);
        shadow.setColor(Color.BLACK);
        title.setEffect(shadow);

        btnNew = new Button("New");
        CSSLoader.applyCSS(btnNew);
        btnLoad = new Button("Load");
        CSSLoader.applyCSS(btnLoad);
        btnCredits = new Button("Credits");
        CSSLoader.applyCSS(btnCredits);
        btnExit = new Button("Exit");
        CSSLoader.applyCSS(btnExit);

        VBox vBox = new VBox(title,btnNew,btnLoad, btnCredits, btnExit);
        vBox.setAlignment(Pos.CENTER);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundPane, vBox);
        StackPane.setAlignment(vBox, Pos.CENTER);
        this.setCenter(stackPane);
    }
    private void registerListeners() {
        btnNew.setOnAction(actionEvent -> {
            InitialSettingsPage initialConfigScreen = new InitialSettingsPage(manager);
            BorderPane configPane = new BorderPane(initialConfigScreen);
            setCenter(configPane);
        });

        btnLoad.setOnAction(actionEvent -> {
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
                    //setTop(new VBox(new TopBar(manager,this)));
                    EcossistemaUI ecossistemaUI = new EcossistemaUI(manager);
                    BorderPane ecossistemaPane = new BorderPane(ecossistemaUI);
                    setCenter(ecossistemaPane);
                }
            }
        });

        btnCredits.setOnAction(actionEvent -> {
            //credits
        });

        btnExit.setOnAction(actionEvent -> {
            System.exit(1);
        });
    }
}
