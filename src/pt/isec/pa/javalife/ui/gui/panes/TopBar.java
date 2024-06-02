package pt.isec.pa.javalife.ui.gui.panes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.EcosystemManager;
import pt.isec.pa.javalife.model.data.elements.Element;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.elements.Flora;
import pt.isec.pa.javalife.model.data.elements.IElement;
import pt.isec.pa.javalife.model.data.events.Event;

import java.io.File;
import java.util.List;

public class TopBar extends MenuBar {
    BorderPane mainPane;
    EcosystemManager manager;
    Menu menuFile, menuEcosystem, menuSimulation, menuEvents;
    MenuItem btnNew, btnOpen, btnSave, btnExport, btnImport, btnExit;
    MenuItem btnConfigurations, btnAddInanimado, btnAddFlora, btnAddFauna, btnEditElement, btnDeleteElement, btnUndo, btnRedo;
    MenuItem btnSettings, btnStart, btnStop, btnContinue, btnPause, btnSaveSnapshot, btnRestoreSnapshot;
    MenuItem btnSun, btnHerbicide, btnStrength;
    public TopBar(EcosystemManager manager, BorderPane mainPane) {
        this.manager = manager;
        this.mainPane=mainPane;
        createViews();
        registerHandlers();
    }

    private void createViews() {
        this.menuFile = new Menu("File");
        this.btnNew = new MenuItem("_New");
        this.btnOpen = new MenuItem("_Open");
        this.btnSave = new MenuItem("_Save");
        this.btnExport = new MenuItem("_Export");
        this.btnImport = new MenuItem("_Import");
        this.btnExit = new MenuItem("_Exit");
        this.menuFile.getItems().addAll(btnNew, btnOpen, btnSave, btnExport, btnImport, new SeparatorMenuItem(), btnExit);

        this.menuEcosystem = new Menu("Ecosystem");
        this.btnConfigurations = new MenuItem("_Configurations");
        this.btnAddInanimado = new MenuItem("_AddInanimado");
        this.btnAddFlora = new MenuItem("_AddFlora");
        this.btnAddFauna = new MenuItem("_AddFauna");
        this.btnEditElement = new MenuItem("_EditElement");
        this.btnDeleteElement = new MenuItem("_DeleteElement");
        this.btnUndo = new MenuItem("_Undo");
        this.btnRedo = new MenuItem("_Redo");
        this.menuEcosystem.getItems().addAll(btnConfigurations, btnAddInanimado, btnAddFlora, btnAddFauna, btnEditElement, btnDeleteElement, btnUndo, btnRedo);

        this.menuSimulation = new Menu("Simulation");
        this.btnSettings = new MenuItem("_Settings");
        this.btnStart = new MenuItem("_Start");
        this.btnStop = new MenuItem("_Stop");
        this.btnContinue = new MenuItem("_Continue");
        this.btnPause = new MenuItem("_Pause");
        this.btnSaveSnapshot = new MenuItem("_SaveSnapshot");
        this.btnRestoreSnapshot = new MenuItem("_RestoreSnapshot");
        this.menuSimulation.getItems().addAll(btnSettings, btnStart, btnStop, btnContinue, btnPause, btnSaveSnapshot, btnRestoreSnapshot);

        this.menuEvents = new Menu("Events");
        this.btnSun = new MenuItem("_Sun");
        this.btnHerbicide = new MenuItem("_Herbicide");
        this.btnStrength = new MenuItem("_Strength");
        this.menuEvents.getItems().addAll(btnSun, btnHerbicide, btnStrength);
        this.getMenus().addAll(menuFile, menuEcosystem, menuSimulation, menuEvents);
    }
    private void registerHandlers() {
        btnNew.setOnAction(e -> {
            manager.stopEngine();
            manager.resetCounterId();
            InitialSettingsPage initialConfigScreen = new InitialSettingsPage(manager);
            BorderPane configPane = new BorderPane(initialConfigScreen);
            mainPane.setCenter(configPane);
            mainPane.setTop(null);
        });
        btnOpen.setOnAction(e -> {
            manager.pauseEngine();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("File open...");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Ecossistema (*.dat)", "*.dat"),
                    new FileChooser.ExtensionFilter("All", "*.*")
            );
            File hFile = fileChooser.showOpenDialog(this.getScene().getWindow());
            if (hFile != null) {
                manager.load(hFile);
            }
            manager.resumeEngine();
        });
        btnSave.setOnAction(e -> {
            manager.pauseEngine();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("File save...");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Ecossistema (*.dat)", "*.dat"),
                    new FileChooser.ExtensionFilter("All", "*.*")
            );
            File hFile = fileChooser.showSaveDialog(this.getScene().getWindow());
            if (hFile != null) {
                manager.save(hFile);
            }
            manager.resumeEngine();
        });
        btnExport.setOnAction(e -> {
            manager.pauseEngine();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("File save...");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Ecossistema (*.csv)", "*.csv"),
                    new FileChooser.ExtensionFilter("All", "*.*")
            );
            File hFile = fileChooser.showSaveDialog(this.getScene().getWindow());
            if (hFile != null) {
                try {
                    manager.exportCSVElements(hFile);
                } catch (CloneNotSupportedException ex) {
                    throw new RuntimeException(ex);
                }
            }
            manager.resumeEngine();
        });
        btnImport.setOnAction(e -> {
            manager.pauseEngine();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("File open...");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Ecossistema (*.csv)", "*.csv"),
                    new FileChooser.ExtensionFilter("All", "*.*")
            );
            File hFile = fileChooser.showOpenDialog(this.getScene().getWindow());
            if (hFile != null) {
                manager.importCSVElements(hFile);
            }
            manager.resumeEngine();
        });
        btnExit.setOnAction(e -> {

        });
        btnConfigurations.setOnAction(e -> {

        });
        btnAddInanimado.setOnAction(e -> {
                    manager.pauseEngine();
                    Stage popupStage = new Stage();
                    popupStage.initModality(Modality.APPLICATION_MODAL);
                    popupStage.initOwner(this.getScene().getWindow());
                    popupStage.setTitle("Add Inanimado");

                    TextField upField = new TextField();
                    TextField downField = new TextField();
                    TextField leftField = new TextField();
                    TextField rightField = new TextField();
                    GridPane gridPane = new GridPane();
                    gridPane.setPadding(new Insets(10));
                    gridPane.setHgap(10);
                    gridPane.setVgap(10);

                    gridPane.add(new Label("Position"), 0, 1);
                    gridPane.add(new Label("Up:"), 0, 2);
                    gridPane.add(upField, 1, 2);
                    gridPane.add(new Label("Down:"), 0, 3);
                    gridPane.add(downField, 1, 3);
                    gridPane.add(new Label("Left:"), 0, 4);
                    gridPane.add(leftField, 1, 4);
                    gridPane.add(new Label("Right:"), 0, 5);
                    gridPane.add(rightField, 1, 5);

                    Button btnCreate = new Button("Add");

                    btnCreate.setOnAction(event -> {
                        String up = upField.getText();
                        String down = downField.getText();
                        String left = leftField.getText();
                        String right = rightField.getText();

                        if (!up.isEmpty() && !down.isEmpty() && !left.isEmpty() && !right.isEmpty()) {
                            try {
                                double upValue = Double.parseDouble(up);
                                double downValue = Double.parseDouble(down);
                                double leftValue = Double.parseDouble(left);
                                double rightValue = Double.parseDouble(right);

                                manager.addElementWithCommand(new Area(upValue, downValue, leftValue, rightValue), Element.INANIMADO);
                                popupStage.close();
                            } catch (NumberFormatException | InterruptedException | CloneNotSupportedException j) {
                                showErrorDialog("Invalid values!");
                            }
                        } else {
                            showErrorDialog("All fields must be filled in!");
                        }
                    });

                    VBox vbox = new VBox(gridPane, btnCreate);
                    vbox.setPadding(new Insets(10));
                    vbox.setSpacing(10);

                    Scene scene = new Scene(vbox);
                    popupStage.setScene(scene);
                    popupStage.showAndWait();
            manager.resumeEngine();
        });
        btnAddFlora.setOnAction(e -> {
            manager.pauseEngine();
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initOwner(this.getScene().getWindow());
            popupStage.setTitle("Add Flora");

            TextField upField = new TextField();
            TextField downField = new TextField();
            TextField leftField = new TextField();
            TextField rightField = new TextField();
            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(10));
            gridPane.setHgap(10);
            gridPane.setVgap(10);

            gridPane.add(new Label("Position"), 0, 1);
            gridPane.add(new Label("Up:"), 0, 2);
            gridPane.add(upField, 1, 2);
            gridPane.add(new Label("Down:"), 0, 3);
            gridPane.add(downField, 1, 3);
            gridPane.add(new Label("Left:"), 0, 4);
            gridPane.add(leftField, 1, 4);
            gridPane.add(new Label("Right:"), 0, 5);
            gridPane.add(rightField, 1, 5);

            Button btnCreate = new Button("Add");

            btnCreate.setOnAction(event -> {
                String up = upField.getText();
                String down = downField.getText();
                String left = leftField.getText();
                String right = rightField.getText();

                if (!up.isEmpty() && !down.isEmpty() && !left.isEmpty() && !right.isEmpty()) {
                    try {
                        double upValue = Double.parseDouble(up);
                        double downValue = Double.parseDouble(down);
                        double leftValue = Double.parseDouble(left);
                        double rightValue = Double.parseDouble(right);

                        manager.addElementWithCommand(new Area(upValue, downValue, leftValue, rightValue), Element.FLORA);
                        popupStage.close();
                    } catch (NumberFormatException | InterruptedException | CloneNotSupportedException j) {
                        showErrorDialog("Invalid values!");
                    }
                } else {
                    showErrorDialog("All fields must be filled in!");
                }
            });

            VBox vbox = new VBox(gridPane, btnCreate);
            vbox.setPadding(new Insets(10));
            vbox.setSpacing(10);

            Scene scene = new Scene(vbox);
            popupStage.setScene(scene);
            popupStage.showAndWait();
            manager.resumeEngine();
        });
        btnAddFauna.setOnAction(e -> {
            manager.pauseEngine();
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initOwner(this.getScene().getWindow());
            popupStage.setTitle("Add Fauna");

            TextField upField = new TextField();
            TextField downField = new TextField();
            TextField leftField = new TextField();
            TextField rightField = new TextField();
            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(10));
            gridPane.setHgap(10);
            gridPane.setVgap(10);

            gridPane.add(new Label("Position"), 0, 1);
            gridPane.add(new Label("Up:"), 0, 2);
            gridPane.add(upField, 1, 2);
            gridPane.add(new Label("Down:"), 0, 3);
            gridPane.add(downField, 1, 3);
            gridPane.add(new Label("Left:"), 0, 4);
            gridPane.add(leftField, 1, 4);
            gridPane.add(new Label("Right:"), 0, 5);
            gridPane.add(rightField, 1, 5);

            Button btnCreate = new Button("Add");

            btnCreate.setOnAction(event -> {
                String up = upField.getText();
                String down = downField.getText();
                String left = leftField.getText();
                String right = rightField.getText();

                if (!up.isEmpty() && !down.isEmpty() && !left.isEmpty() && !right.isEmpty()) {
                    try {
                        double upValue = Double.parseDouble(up);
                        double downValue = Double.parseDouble(down);
                        double leftValue = Double.parseDouble(left);
                        double rightValue = Double.parseDouble(right);

                        manager.addElementWithCommand(new Area(upValue, downValue, leftValue, rightValue), Element.FAUNA);
                        popupStage.close();
                    } catch (NumberFormatException | InterruptedException | CloneNotSupportedException j) {
                        showErrorDialog("Invalid values!");
                    }
                } else {
                    showErrorDialog("All fields must be filled in!");
                }
            });

            VBox vbox = new VBox(gridPane, btnCreate);
            vbox.setPadding(new Insets(10));
            vbox.setSpacing(10);

            Scene scene = new Scene(vbox);
            popupStage.setScene(scene);
            popupStage.showAndWait();
            manager.resumeEngine();
        });
        btnEditElement.setOnAction(e -> {
            manager.pauseEngine();
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initOwner(this.getScene().getWindow());
            popupStage.setTitle("Edit Element");

            ComboBox<Element> typeComboBox = new ComboBox<>();
            typeComboBox.getItems().addAll(Element.values());
            typeComboBox.setValue(typeComboBox.getItems().isEmpty() ? null : typeComboBox.getItems().getFirst());

            ComboBox<Integer> idComboBox = new ComboBox<>();

            TextField upField = new TextField();
            TextField downField = new TextField();
            TextField leftField = new TextField();
            TextField rightField = new TextField();
            TextField speedField = new TextField();
            TextField strengthField = new TextField();

            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(10));
            gridPane.setHgap(10);
            gridPane.setVgap(10);

            gridPane.add(new Label("Type:"), 0, 0);
            gridPane.add(typeComboBox, 1, 0);
            gridPane.add(new Label("ID:"), 0, 1);
            gridPane.add(idComboBox, 1, 1);
            gridPane.add(new Label("Up:"), 0, 2);
            gridPane.add(upField, 1, 2);
            gridPane.add(new Label("Down:"), 0, 3);
            gridPane.add(downField, 1, 3);
            gridPane.add(new Label("Left:"), 0, 4);
            gridPane.add(leftField, 1, 4);
            gridPane.add(new Label("Right:"), 0, 5);
            gridPane.add(rightField, 1, 5);
            gridPane.add(new Label("Strength:"), 0, 6);
            gridPane.add(strengthField, 1, 6);
            gridPane.add(new Label("Speed:"), 0, 7);
            gridPane.add(speedField, 1, 7);

            typeComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                idComboBox.getItems().clear();
                if (newValue != null) {
                    List<IElement> elementsOfType = null;
                    try {
                        elementsOfType = manager.getElementsOfType(newValue);
                    } catch (CloneNotSupportedException ex) {
                        throw new RuntimeException(ex);
                    }
                    for (IElement element : elementsOfType) {
                        idComboBox.getItems().add(element.getId());
                    }
                    if (!idComboBox.getItems().isEmpty()) {
                        idComboBox.setValue(idComboBox.getItems().getFirst());
                    }
                }
            });

            idComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    IElement element = null;
                    try {
                        element = manager.getElementById(newValue, typeComboBox.getValue());
                    } catch (CloneNotSupportedException ex) {
                        throw new RuntimeException(ex);
                    }
                    upField.setText(String.valueOf(element.getArea().up()));
                    downField.setText(String.valueOf(element.getArea().down()));
                    leftField.setText(String.valueOf(element.getArea().left()));
                    rightField.setText(String.valueOf(element.getArea().right()));
                    if(typeComboBox.getValue()  == Element.FAUNA) {
                        speedField.setText(String.valueOf(((Fauna)element).getSpeed()));
                        strengthField.setText(String.valueOf(((Fauna)element).getStrength()));
                    }
                    if(typeComboBox.getValue() == Element.FLORA) {
                        strengthField.setText(String.valueOf(((Flora)element).getStrength()));
                    }
                    if(element.getType()==Element.INANIMADO){
                        speedField.setText("");
                        strengthField.setText("");
                    }
                }
            });

            Button btnEdit = new Button("Edit");

            btnEdit.setOnAction(event -> {
                Element type = typeComboBox.getValue();
                Integer id = idComboBox.getValue();
                String up = upField.getText();
                String down = downField.getText();
                String left = leftField.getText();
                String right = rightField.getText();
                String speed = speedField.getText();
                String strength = strengthField.getText();

                if (id!=null && !up.isEmpty() && !down.isEmpty() && !left.isEmpty() && !right.isEmpty() && (type==Element.FAUNA && !speed.isEmpty() && !strength.isEmpty()) || (type==Element.FLORA && !strength.isEmpty())|| (type==Element.INANIMADO && speed.isEmpty() && strength.isEmpty())) {
                    try {
                        double upValue = Double.parseDouble(up);
                        double downValue = Double.parseDouble(down);
                        double leftValue = Double.parseDouble(left);
                        double rightValue = Double.parseDouble(right);
                        double speedValue = Double.parseDouble(speed);
                        double strengthValue = Double.parseDouble(strength);

                        manager.editElementWithCommand(id, type, new Area(upValue, downValue, leftValue, rightValue), speedValue, strengthValue);
                        popupStage.close();
                    } catch (NumberFormatException | InterruptedException | CloneNotSupportedException j) {
                        showErrorDialog("Invalid values!");
                    }
                } else {
                    showErrorDialog("All fields must be filled in!");
                }
            });

            VBox vbox = new VBox(gridPane, btnEdit);
            vbox.setPadding(new Insets(10));
            vbox.setSpacing(10);

            Scene scene = new Scene(vbox);
            popupStage.setScene(scene);
            popupStage.showAndWait();
            manager.resumeEngine();
        });
        btnDeleteElement.setOnAction(e -> {
                    manager.pauseEngine();
                    Stage popupStage = new Stage();
                    popupStage.initModality(Modality.APPLICATION_MODAL);
                    popupStage.initOwner(this.getScene().getWindow());
                    popupStage.setTitle("Delete element");

                    TextField idField = new TextField();
                    ComboBox<Element> tipoComboBox = new ComboBox<>();
                    tipoComboBox.getItems().addAll(Element.values()); // ver elemento.getTipo
                    tipoComboBox.setValue(tipoComboBox.getItems().isEmpty() ? null : tipoComboBox.getItems().getFirst());

                    ComboBox<Integer> idComboBox = new ComboBox<>();
                    tipoComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                        idComboBox.getItems().clear();
                        if (newValue != null) {
                            List<IElement> elementsOfType = null;
                            try {
                                elementsOfType = manager.getElementsOfType(newValue);
                            } catch (CloneNotSupportedException ex) {
                                throw new RuntimeException(ex);
                            }
                            for (IElement elemento : elementsOfType) {
                                idComboBox.getItems().add(elemento.getId());
                            }
                            if (!idComboBox.getItems().isEmpty()) {
                                idComboBox.setValue(idComboBox.getItems().getFirst());
                            }
                        }
                    });
                    GridPane gridPane = new GridPane();
                    gridPane.setPadding(new Insets(10));
                    gridPane.setHgap(10);
                    gridPane.setVgap(10);

                    gridPane.add(new Label("Type of element:"), 0, 0);
                    gridPane.add(tipoComboBox, 1, 0);
                    gridPane.add(new Label("ID:"), 0, 2);
                    gridPane.add(idComboBox, 1, 2);
                    Button btnCreate = new Button("Delete Element");

                    btnCreate.setOnAction(event -> {
                        Integer id = idComboBox.getValue();
                        Element tipo = tipoComboBox.getValue();
                        if (id == null || tipo == null) {
                            showErrorDialog("All fields must be filled in!");
                            return;
                        }
                        try {
                            manager.removeElementWithCommand(id, tipo);
                            popupStage.close();
                        } catch (InterruptedException | CloneNotSupportedException j) {
                            showErrorDialog("Error deleting element!");
                        }
                    });
                    VBox vbox = new VBox(gridPane, btnCreate);
                    vbox.setPadding(new Insets(10));
                    vbox.setSpacing(10);
                    Scene scene = new Scene(vbox);
                    popupStage.setScene(scene);
                    popupStage.showAndWait();
                    manager.resumeEngine();
        });
        btnUndo.setOnAction(e -> {
            try {
                manager.undo();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnRedo.setOnAction(e -> {
            try {
                manager.redo();
            } catch (InterruptedException | CloneNotSupportedException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnSettings.setOnAction(e -> {

        });
        btnStart.setOnAction(e -> {
            manager.startEngine();
        });
        btnStop.setOnAction(e -> {
            manager.stopEngine();
        });
        btnContinue.setOnAction(e -> {
            manager.resumeEngine();
        });
        btnPause.setOnAction(e -> {
            manager.pauseEngine();
        });
        btnSaveSnapshot.setOnAction(e -> {
            manager.saveSnapshot();
        });
        btnRestoreSnapshot.setOnAction(e -> {
            manager.restoreSnapshot();
        });
        btnSun.setOnAction(e -> {
            manager.applyEvent(Event.SUN);
        });
        btnHerbicide.setOnAction(e -> {
            manager.pauseEngine();
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initOwner(this.getScene().getWindow());
            popupStage.setTitle("Apply Herbicide");

            ComboBox<Integer> idComboBox = new ComboBox<>();
            Element selectedElemento = Element.FLORA; // Directly use Elemento.FLORA

            List<IElement> elementsOfType = null;
            try {
                elementsOfType = manager.getElementsOfType(selectedElemento);
            } catch (CloneNotSupportedException ex) {
                throw new RuntimeException(ex);
            }
            for (IElement elemento : elementsOfType) {
                idComboBox.getItems().add(elemento.getId());
            }
            if (!idComboBox.getItems().isEmpty()) {
                idComboBox.setValue(idComboBox.getItems().getFirst());
            }
            Button btnCreate = new Button("Apply Herbicide");

            btnCreate.setOnAction(event -> {
                Integer id = idComboBox.getValue();
                if (id == null ) {
                    showErrorDialog("All fields must be filled in!");
                    return;
                }
                manager.applyEvent(Event.HERBICIDE,id);
                popupStage.close();
            });
            VBox vbox = new VBox(idComboBox, btnCreate);
            vbox.setPadding(new Insets(10));
            vbox.setSpacing(10);
            Scene scene = new Scene(vbox);
            popupStage.setScene(scene);
            popupStage.showAndWait();
            manager.resumeEngine();
        });
        btnStrength.setOnAction(e -> {
            manager.pauseEngine();
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initOwner(this.getScene().getWindow());
            popupStage.setTitle("Apply Strength");

            ComboBox<Integer> idComboBox = new ComboBox<>();
            Element selectedElemento = Element.FAUNA;

            List<IElement> elementsOfType = null;
            try {
                elementsOfType = manager.getElementsOfType(selectedElemento);
            } catch (CloneNotSupportedException ex) {
                throw new RuntimeException(ex);
            }
            for (IElement elemento : elementsOfType) {
                idComboBox.getItems().add(elemento.getId());
            }
            if (!idComboBox.getItems().isEmpty()) {
                idComboBox.setValue(idComboBox.getItems().getFirst());
            }
            Button btnCreate = new Button("Apply Strength");

            btnCreate.setOnAction(event -> {
                Integer id = idComboBox.getValue();
                if (id == null ) {
                    showErrorDialog("All fields must be filled in!");
                    return;
                }
                manager.applyEvent(Event.STRENGTH,id);
                popupStage.close();
            });
            VBox vbox = new VBox(idComboBox, btnCreate);
            vbox.setPadding(new Insets(10));
            vbox.setSpacing(10);
            Scene scene = new Scene(vbox);
            popupStage.setScene(scene);
            popupStage.showAndWait();
            manager.resumeEngine();
        });
    }
    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}