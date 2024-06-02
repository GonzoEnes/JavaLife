package pt.isec.pa.javalife.ui.gui.panes;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;
import pt.isec.pa.javalife.ui.gui.resources.CSSLoader;

import java.util.Objects;

public class InitialSettingsPage extends BorderPane {
    private EcossistemaManager manager;
    private Pane ecossistemaPane;
    private EcossistemaUI ecossistemaUI;
    Button btnStart,btnBack;
    TextField heightField, widthField, timeField;

    public InitialSettingsPage(EcossistemaManager manager) {
        this.manager = manager;
        createView();
        registerListeners();
    }

    public void createView() {
        Pane backgroundPane = new Pane();
        String imagePath = Objects.requireNonNull(getClass().getResource("/pt/isec/pa/javalife/ui/gui/resources/images/ecosystem.jpg")).toExternalForm();
        backgroundPane.setStyle("-fx-background-image: url('" + imagePath + "');" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-position: center center;" +
                "-fx-background-size: cover;");
        GaussianBlur blur = new GaussianBlur(10);
        backgroundPane.setEffect(blur);

        Text title = new Text("Settings");
        title.setFont(Font.loadFont(getClass().getResourceAsStream("/pt/isec/pa/javalife/ui/gui/resources/fonts/ShadowsIntoLight-Regular.ttf"), 100));
        title.setFill(Color.WHITE);
        DropShadow shadow = new DropShadow();
        shadow.setOffsetY(3.0);
        shadow.setColor(Color.BLACK);
        title.setEffect(shadow);

        Label heightLabel = new Label("Height:");
        heightLabel.setStyle("-fx-font-family: 'Shadows Into Light';" +
                "-fx-font-size: 20;" +
                "-fx-text-fill: white;" +
                "-fx-effect: dropshadow(three-pass-box, black, 10, 0, 0, 3);");
        heightField = new TextField();
        heightField.setPromptText("Enter height");
        heightField.setFont(Font.loadFont(getClass().getResourceAsStream("/pt/isec/pa/javalife/ui/gui/resources/fonts/ShadowsIntoLight-Regular.ttf"), 20));
        heightField.setStyle("-fx-background-color: transparent; -fx-text-inner-color: black;-fx-prompt-text-fill: black;");
        heightField.setPrefWidth(130);

        Label widthLabel = new Label("Width:");
        widthLabel.setStyle("-fx-font-family: 'Shadows Into Light';" +
                "-fx-font-size: 20;" +
                "-fx-text-fill: white;" +
                "-fx-effect: dropshadow(three-pass-box, black, 10, 0, 0, 3);");
        widthField = new TextField();
        widthField.setPromptText("Enter width");
        widthField.setFont(Font.loadFont(getClass().getResourceAsStream("/pt/isec/pa/javalife/ui/gui/resources/fonts/ShadowsIntoLight-Regular.ttf"), 20));
        widthField.setStyle("-fx-background-color: transparent; -fx-text-inner-color: black;-fx-prompt-text-fill: black;");
        widthField.setPrefWidth(130);

        Label timeLabel = new Label("Time:");
        timeLabel.setStyle("-fx-font-family: 'Shadows Into Light';" +
                "-fx-font-size: 20;" +
                "-fx-text-fill: white;" +
                "-fx-effect: dropshadow(three-pass-box, black, 10, 0, 0, 3);");
        timeField = new TextField();
        timeField.setPromptText("Enter time");
        timeField.setFont(Font.loadFont(getClass().getResourceAsStream("/pt/isec/pa/javalife/ui/gui/resources/fonts/ShadowsIntoLight-Regular.ttf"), 20));
        timeField.setStyle("-fx-background-color: transparent; -fx-text-inner-color: black;-fx-prompt-text-fill: black;");
        timeField.setPrefWidth(130);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10); // Horizontal gap
        gridPane.setVgap(10); // Vertical gap

        gridPane.add(heightLabel, 0, 0); // Add to column 0, row 0
        gridPane.add(heightField, 1, 0); // Add to column 1, row 0

        gridPane.add(widthLabel, 0, 1); // Add to column 0, row 1
        gridPane.add(widthField, 1, 1); // Add to column 1, row 1

        gridPane.add(timeLabel, 0, 2); // Add to column 0, row 2
        gridPane.add(timeField, 1, 2); // Add to column 1, row 2
        gridPane.setAlignment(Pos.CENTER); // Centers the elements in the GridPane

        btnStart = new Button("Start");
        CSSLoader.applyCSS(btnStart);

        btnBack = new Button("Back");
        CSSLoader.applyCSS(btnBack);
        HBox buttonBox = new HBox(btnBack, btnStart);
        buttonBox.setSpacing(10);
        buttonBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(title, gridPane, buttonBox);
        vBox.setAlignment(Pos.CENTER); // Centers the elements in the VBox

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundPane, vBox);
        StackPane.setAlignment(vBox, Pos.CENTER); // Centers the VBox in the StackPane
        this.setCenter(stackPane);

    }
    private void registerListeners() {
        btnStart.setOnAction(event -> {
            String height = heightField.getText();
            String width = widthField.getText();
            String time = timeField.getText();
            if (!height.isEmpty() && !width.isEmpty()) {
                try {
                    manager.createEcosystem(Integer.parseInt(height),Integer.parseInt(width),Integer.parseInt(time));
                    System.out.println(manager.getAltura() + " " + manager.getLargura());
                    setTop(new VBox(new TopBar(manager,this)));
                    ecossistemaUI = new EcossistemaUI(manager);
                    ecossistemaPane = new BorderPane(ecossistemaUI);
                    setCenter(ecossistemaPane);
                    registerHandlers();
                } catch (NumberFormatException e) {
                    showErrorDialog("Valores inválidos! Por favor insira números inteiros.");
                }
            } else {
                showErrorDialog("Todos os campos devem ser preenchidos!");
            }
        });

        btnBack.setOnAction(event -> {
            HomePage mainMenu = new HomePage(manager);
            BorderPane mainMenuPane = new BorderPane(mainMenu);
            setCenter(mainMenuPane);
        });

    }

    private void registerHandlers() {
        if (ecossistemaPane != null && ecossistemaUI != null) {
            ecossistemaPane.widthProperty().addListener(observable -> ecossistemaUI.updateSize(ecossistemaPane.getWidth(), ecossistemaPane.getHeight()));
            ecossistemaPane.heightProperty().addListener(observable -> ecossistemaUI.updateSize(ecossistemaPane.getWidth(), ecossistemaPane.getHeight()));
        }
    }

    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}