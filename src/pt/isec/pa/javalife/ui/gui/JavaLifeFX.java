package pt.isec.pa.javalife.ui.gui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;

import java.io.File;

public class JavaLifeFX extends Application {
    EcossistemaManager manager;

    @Override
    public void start(Stage primaryStage) {
        manager = new EcossistemaManager();

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);

        // Menu Bar
        MenuBar menuBar = new MenuBar();

        // Menu File
        Menu menuFile = new Menu("Ficheiro");
        MenuItem criarMenuItem = new MenuItem("Criar");
        MenuItem abrirMenuItem = new MenuItem("Abrir");
        MenuItem gravarMenuItem = new MenuItem("Gravar");
        MenuItem exportarMenuItem = new MenuItem("Exportar");
        MenuItem importarMenuItem = new MenuItem("Importar");
        MenuItem sairMenuItem = new MenuItem("Sair");
        menuFile.getItems().addAll(criarMenuItem, abrirMenuItem, gravarMenuItem, exportarMenuItem, importarMenuItem, new SeparatorMenuItem(), sairMenuItem);
        abrirMenuItem.setOnAction(event -> abrirSimulacao(primaryStage));

        // Menu Ecossistema
        Menu menuEcossistema = new Menu("Ecossistema");
        MenuItem configGeraisMenuItem = new MenuItem("Configurações Gerais");
        MenuItem addElementoInanimadoMenuItem = new MenuItem("Adicionar Elemento Inanimado");
        MenuItem addElementoFloraMenuItem = new MenuItem("Adicionar Elemento Flora");
        MenuItem addElementoFaunaMenuItem = new MenuItem("Adicionar Elemento Fauna");
        MenuItem editarElementoMenuItem = new MenuItem("Editar Elemento");
        MenuItem eliminarElementoMenuItem = new MenuItem("Eliminar Elemento");
        MenuItem undoRedoMenuItem = new MenuItem("Undo/Redo");
        menuEcossistema.getItems().addAll(configGeraisMenuItem, addElementoInanimadoMenuItem, addElementoFloraMenuItem, addElementoFaunaMenuItem, editarElementoMenuItem, eliminarElementoMenuItem, undoRedoMenuItem);

        // Menu Simulação
        Menu menuSimulacao = new Menu("Simulação");
        MenuItem configSimulacaoMenuItem = new MenuItem("Configuração da Simulação");
        MenuItem executarPararMenuItem = new MenuItem("Executar/Parar");
        MenuItem pausarContinuarMenuItem = new MenuItem("Pausar/Continuar");
        MenuItem gravarSnapshotMenuItem = new MenuItem("Gravar Snapshot");
        MenuItem restaurarSnapshotMenuItem = new MenuItem("Restaurar Snapshot");
        menuSimulacao.getItems().addAll(configSimulacaoMenuItem, executarPararMenuItem, pausarContinuarMenuItem, gravarSnapshotMenuItem, restaurarSnapshotMenuItem);

        // Menu Eventos
        Menu menuEventos = new Menu("Eventos");
        MenuItem aplicarSolMenuItem = new MenuItem("Aplicar Sol");
        MenuItem aplicarHerbicidaMenuItem = new MenuItem("Aplicar Herbicida");
        MenuItem injetarForcaMenuItem = new MenuItem("Injetar Força");
        menuEventos.getItems().addAll(aplicarSolMenuItem, aplicarHerbicidaMenuItem, injetarForcaMenuItem);

        menuBar.getMenus().addAll(menuFile, menuEcossistema, menuSimulacao, menuEventos);

        root.setTop(menuBar);

        primaryStage.setTitle("Simulação");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void abrirSimulacao(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir Simulação");
        File file = fileChooser.showOpenDialog(primaryStage);
        if (file != null) {
            manager.carregarSimulacao(file);
            System.out.println("Abrir simulação do arquivo: " + file.getAbsolutePath());
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}