package pt.isec.pa.javalife.ui.gui.menuitems;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;
import pt.isec.pa.javalife.model.data.elements.*;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MenuItemPageUI {
    private EcossistemaManager manager;

    public MenuItemPageUI(EcossistemaManager manager) {
        this.manager = manager;
    }

    public void configureAddFaunaMenuItem(MenuItem mnAddFauna) {
        mnAddFauna.setOnAction(actionEvent -> {
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initOwner(((MenuItem) actionEvent.getSource()).getParentPopup().getOwnerWindow());
            popupStage.setTitle("Criar Elemento");

            TextField cimaField = new TextField();
            TextField baixoField = new TextField();
            TextField direitaField = new TextField();
            TextField esquerdaField = new TextField();

            ComboBox<String> tipoComboBox = new ComboBox<>();
            tipoComboBox.getItems().addAll(loadImageNames("imgs"));
            tipoComboBox.setValue(tipoComboBox.getItems().isEmpty() ? null : tipoComboBox.getItems().get(0)); // ir buscar logo a primeira imagem

            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(10));
            gridPane.setHgap(10);
            gridPane.setVgap(10);

            gridPane.add(new Label("Imagem:"), 0, 0);
            gridPane.add(tipoComboBox, 1, 0);
            gridPane.add(new Label("Área da Fauna"), 0, 1);
            gridPane.add(new Label("Cima:"), 0, 2);
            gridPane.add(cimaField, 1, 2);
            gridPane.add(new Label("Baixo:"), 0, 3);
            gridPane.add(baixoField, 1, 3);
            gridPane.add(new Label("Direita:"), 0, 4);
            gridPane.add(direitaField, 1, 4);
            gridPane.add(new Label("Esquerda:"), 0, 5);
            gridPane.add(esquerdaField, 1, 5);

            Button btnCreate = new Button("Criar Fauna");

            btnCreate.setOnAction(event -> {
                String cima = cimaField.getText();
                String baixo = baixoField.getText();
                String direita = direitaField.getText();
                String esquerda = esquerdaField.getText();

                if (!cima.isEmpty() && !baixo.isEmpty() && !direita.isEmpty() && !esquerda.isEmpty()) {
                    try {
                        double cimaValue = Double.parseDouble(cima);
                        double baixoValue = Double.parseDouble(baixo);
                        double direitaValue = Double.parseDouble(direita);
                        double esquerdaValue = Double.parseDouble(esquerda);

                        manager.addElemento(new Area(cimaValue, esquerdaValue, baixoValue, direitaValue),tipoComboBox.getValue(),Elemento.FAUNA);
                        popupStage.close();
                    } catch (NumberFormatException e) {
                        showErrorDialog("Valores inválidos! Por favor insira números inteiros.");
                    }
                } else {
                    showErrorDialog("Todos os campos devem ser preenchidos!");
                }
            });

            VBox vbox = new VBox(gridPane, btnCreate);
            vbox.setPadding(new Insets(10));
            vbox.setSpacing(10);

            Scene scene = new Scene(vbox);
            popupStage.setScene(scene);
            popupStage.show();
        });
    }

    private List<String> loadImageNames(String directoryPath) {
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            return Arrays.stream(Objects.requireNonNull(directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".jpeg"))))
                    .map(File::getName)
                    .collect(Collectors.toList());
        }
        return List.of();
    }

    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}