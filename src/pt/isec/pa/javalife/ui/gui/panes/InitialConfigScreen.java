package pt.isec.pa.javalife.ui.gui.panes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;

public class InitialConfigScreen extends BorderPane {
    private EcossistemaManager manager;
    private Pane ecossistemaPane;
    private EcossistemaUI ecossistemaUI;

    public InitialConfigScreen(EcossistemaManager manager) {
        this.manager = manager;
        configInitialSettings();
    }

    public void configInitialSettings() {
        Label title = new Label("Insira as dimensões e configurações do Ecossistema");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: black; -fx-alignment: center; -fx-padding: 10px;");
        TextField larguraField = new TextField();
        TextField alturaField = new TextField();
        TextField timeField = new TextField();

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER); // Centralizar elementos verticalmente

        gridPane.add(new Label("Altura:"), 0, 0);
        gridPane.add(alturaField, 1, 0);

        gridPane.add(new Label("Largura:"), 0, 1);
        gridPane.add(larguraField, 1, 1);

        gridPane.add(new Label("Tempo:"), 0, 2);
        gridPane.add(timeField, 1, 2);

        Button btnCreate = new Button("Criar Ecossistema");

        btnCreate.setOnAction(event -> {
            String largura = larguraField.getText();
            String altura = alturaField.getText();
            String time = timeField.getText();
            if (!largura.isEmpty() && !altura.isEmpty()) {
                try {
                    int larguraValue = Integer.parseInt(largura);
                    int alturaValue = Integer.parseInt(altura);
                    int timeValue = Integer.parseInt(time);

                    manager.setInitialEcossistemaConfigs(alturaValue,larguraValue,timeValue);

                    System.out.println(manager.getAltura() + " " + manager.getLargura());

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

        VBox vbox = new VBox(title, gridPane, btnCreate);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER); // Centralizar elementos verticalmente

        setCenter(vbox);
    }

    private void registerHandlers() {
        if (ecossistemaPane != null && ecossistemaUI != null) {
            ecossistemaPane.widthProperty().addListener(observable -> ecossistemaUI.updateSize(ecossistemaPane.getWidth(), ecossistemaPane.getHeight()));
            ecossistemaPane.heightProperty().addListener(observable -> ecossistemaUI.updateSize(ecossistemaPane.getWidth(), ecossistemaPane.getHeight()));
        }
    }

    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}