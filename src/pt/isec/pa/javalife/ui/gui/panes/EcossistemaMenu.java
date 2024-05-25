package pt.isec.pa.javalife.ui.gui.panes;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.FileChooser;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;

import java.io.File;

public class EcossistemaMenu extends MenuBar {
    EcossistemaManager manager;
    Menu mnFile;
    Menu mnEdit;
    MenuItem mnUndo, mnRedo;
    MenuItem mnNew, mnOpen, mnSave, mnExit;

    public EcossistemaMenu(EcossistemaManager manager) {
        this.manager = manager;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        this.mnFile = new Menu("Ficheiro");
        this.mnEdit = new Menu("Editar");
        this.mnUndo = new MenuItem("_Undo");
        this.mnRedo = new MenuItem("_Redo");
        this.mnExit = new MenuItem("_Sair");
        this.mnSave = new MenuItem("_Guardar");
        this.mnNew = new MenuItem("_Novo");
        this.mnOpen = new MenuItem("_Abrir");
        //this.mnOpen.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCodeCombination.ALT_DOWN));
        this.mnFile.getItems().addAll(mnNew,mnOpen,mnSave,new SeparatorMenuItem(), mnExit);
        mnEdit.getItems().addAll(mnUndo,mnRedo);
        this.getMenus().addAll(mnFile,mnEdit);
    }

    private void registerHandlers() {
        mnExit.setOnAction(event -> {
            Platform.exit();
        });
        mnOpen.setOnAction(e -> {
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
        });
        mnSave.setOnAction(e -> {
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
        });
    }

    private void update() {
        this.mnUndo.setDisable(true);
        this.mnRedo.setDisable(true);
    }
}
