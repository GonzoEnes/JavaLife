package pt.isec.pa.javalife.ui.gui.panes;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.elements.Flora;
import pt.isec.pa.javalife.model.data.elements.IElemento;
import pt.isec.pa.javalife.model.data.elements.Inanimado;
import pt.isec.pa.javalife.ui.gui.menuitems.MenuItemPageUI;

import java.io.File;

public class EcossistemaMenu extends MenuBar {
    EcossistemaManager manager;
    Menu mnFile;
    Menu mnEcossistema;
    Menu mnSimulacao;
    Menu mnEventos;
    MenuItem mnUndo, mnRedo;
    MenuItem mnNew, mnOpen, mnSave, mnExport, mnImport, mnExit;
    MenuItem mnAddInanimado, mnAddFlora, mnAddFauna;
    MenuItem mnEditarElemento;
    MenuItem mnEliminarElemento;
    MenuItem mnParar, mnExecutar, mnPausar, mnContinuar;
    MenuItem mnSaveSnap, mnRestoreSnap;
    MenuItem mnSol, mnHerbicida, mnForca;

    public EcossistemaMenu(EcossistemaManager manager) {
        this.manager = manager;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        this.mnFile = new Menu("Ficheiro");
        this.mnEcossistema = new Menu("Ecossistema");
        this.mnSimulacao = new Menu("Simulação");
        this.mnEventos = new Menu("Eventos");
        this.mnUndo = new MenuItem("_Undo");
        this.mnRedo = new MenuItem("_Redo");
        this.mnExit = new MenuItem("_Sair");
        this.mnSave = new MenuItem("_Guardar");
        this.mnNew = new MenuItem("_Novo");
        this.mnOpen = new MenuItem("_Abrir");
        this.mnImport = new MenuItem("_Importar");
        this.mnExport = new MenuItem("_Exportar");
        this.mnAddInanimado = new MenuItem("_Adicionar Inanimado");
        this.mnAddFlora = new MenuItem("_Adicionar Flora");
        this.mnAddFauna = new MenuItem("_Adicionar Fauna");
        this.mnEditarElemento = new MenuItem("_Editar Elemento");
        this.mnEliminarElemento = new MenuItem("_Eliminar Elemento");
        this.mnExecutar = new MenuItem("_Executar");
        this.mnParar = new MenuItem("_Parar");
        this.mnSaveSnap = new MenuItem("_Guardar Snapshot");
        this.mnRestoreSnap = new MenuItem("_Restaurar Snapshot");
        this.mnPausar = new MenuItem("_Pausar");
        this.mnContinuar = new MenuItem("_Continuar");
        this.mnSol = new MenuItem("_Sol");
        this.mnHerbicida = new MenuItem("_Herbicida");
        this.mnForca = new MenuItem("_Força");
        //this.mnOpen.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCodeCombination.ALT_DOWN));
        this.mnFile.getItems().addAll(mnNew,mnOpen,mnSave,mnImport,mnExport,new SeparatorMenuItem(), mnExit);
        mnEcossistema.getItems().addAll(mnAddInanimado, mnAddFlora, mnAddFauna, mnEditarElemento, mnEliminarElemento, mnUndo,mnRedo);
        mnSimulacao.getItems().addAll(mnExecutar, mnParar, mnPausar, mnContinuar, mnSaveSnap, mnRestoreSnap);
        mnEventos.getItems().addAll(mnSol, mnHerbicida,mnForca);
        this.getMenus().addAll(mnFile, mnEcossistema, mnSimulacao, mnEventos);
    }

    private void registerHandlers() {
        mnExit.setOnAction(event -> {
            Platform.exit();
        });

        mnOpen.setOnAction(e -> {
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
                manager.load(hFile);
            }
            manager.resumeEngine();
        });

        mnSave.setOnAction(e -> {
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
                manager.save(hFile);
            }
            manager.resumeEngine();
        });

        mnAddFauna.setOnAction(actionEvent -> {
            manager.pauseEngine();
            MenuItemPageUI menuItemPageUI = new MenuItemPageUI(manager);
            menuItemPageUI.configureAddFaunaMenuItem(mnAddFauna);
            manager.resumeEngine();
        });

        mnExport.setOnAction(actionEvent -> {
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
                manager.exportCSVElements(hFile);
            }
            manager.resumeEngine();
        });

        mnImport.setOnAction(actionEvent -> {
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
    }

    private void update() {
        this.mnUndo.setDisable(true);
        this.mnRedo.setDisable(true);
    }
}