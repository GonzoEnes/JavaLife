package pt.isec.pa.javalife.ui.gui.panes;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
import pt.isec.pa.javalife.model.data.events.Event;
import pt.isec.pa.javalife.model.data.events.IEvent;
import pt.isec.pa.javalife.model.data.events.Sun;
import pt.isec.pa.javalife.model.data.memento.Memento;
import pt.isec.pa.javalife.ui.gui.menuitems.MenuItemPageUI;

import java.io.File;

public class EcossistemaMenu extends MenuBar {
    EcossistemaManager manager;
    Stage stage;
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

    public EcossistemaMenu(EcossistemaManager manager, Stage stage) {
        this.manager = manager;
        this.stage = stage;
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

        mnNew.setOnAction(actionEvent -> {
            manager.pauseEngine();
            InitialConfigScreen initialConfigScreen = new InitialConfigScreen(manager);
            initialConfigScreen.configInitialSettings();
            manager.resumeEngine();
        });

        mnSol.setOnAction(actionEvent -> {
            IEvent evento = new Sun(Event.SUN, null); // dar fix a isto porque o ecossistema não pode ser null, MÁXIMO MUDA O CONSTRUTOR DOS EVENTOS
            for (IElemento elemento : manager.getElementos()) {
                evento.apply(elemento);
            }
        });

        mnOpen.setOnAction(e -> {
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
                if (manager.load(hFile)) {
                    //EcossistemaUI ecossistemaUI = new EcossistemaUI(manager);
                    //BorderPane ecossistemaPane = new BorderPane(ecossistemaUI);
                    //stage.setScene(new Scene(ecossistemaPane, 800, 600));
                    System.out.println("Save carregado!\n");
                }
            }
            manager.resumeEngine();
        });

        mnSave.setOnAction(e -> {
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

        mnAddFauna.setOnAction(actionEvent -> {
            manager.pauseEngine();
            MenuItemPageUI menuItemPageUI = new MenuItemPageUI(manager);
            menuItemPageUI.configureAddFaunaMenuItem(mnAddFauna);
            manager.resumeEngine();
        });

        mnAddFlora.setOnAction(actionEvent -> {
            manager.pauseEngine();
            MenuItemPageUI menuItemPageUI = new MenuItemPageUI(manager);
            menuItemPageUI.configureAddFloraMenuItem(mnAddFlora);
            manager.resumeEngine();

        });

        mnAddInanimado.setOnAction(actionEvent -> {
            manager.pauseEngine();
            MenuItemPageUI menuItemPageUI = new MenuItemPageUI(manager);
            menuItemPageUI.configureAddInanimadoMenuItem(mnAddInanimado);
            manager.resumeEngine();

        });

        mnEditarElemento.setOnAction(actionEvent -> {
            manager.pauseEngine();
            MenuItemPageUI menuItemPageUI = new MenuItemPageUI(manager);
            menuItemPageUI.configureEditElementoMenuItem(mnEditarElemento);
            manager.resumeEngine();
        });

        mnEliminarElemento.setOnAction(actionEvent -> {
            manager.pauseEngine();
            MenuItemPageUI menuItemPageUI = new MenuItemPageUI(manager);
            menuItemPageUI.configureDeleteElementoMenuItem(mnEliminarElemento);
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

        mnParar.setOnAction(actionEvent -> {
            manager.stopEngine();
        });

        mnExecutar.setOnAction(actionEvent -> {
            manager.startEngine();
        });

        mnContinuar.setOnAction(actionEvent -> {
            manager.resumeEngine();
        });

        mnPausar.setOnAction(actionEvent -> {
            manager.pauseEngine();
        });

        mnSaveSnap.setOnAction(actionEvent -> {
            manager.save();
        });

        mnRestoreSnap.setOnAction(actionEvent -> {
            //manager.restore(); ACABAR AMANHÃ
        });
    }

    private void update() {
        this.mnUndo.setDisable(true);
        this.mnRedo.setDisable(true);
    }
}