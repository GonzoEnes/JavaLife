package pt.isec.pa.javalife.ui.gui.panes;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;
import pt.isec.pa.javalife.model.data.elements.*;
import pt.isec.pa.javalife.ui.gui.resources.ImageManager;

import java.util.Map;
import java.util.Set;

public class EcossistemaUI extends BorderPane {
    private EcossistemaManager manager;
    private GridPane board;
    private Stage stage;

    public EcossistemaUI(Stage stage, EcossistemaManager manager) {
        this.stage = stage;
        this.manager = manager;
        createViews();
        registerHandlers();
        update();
        this.requestFocus();

    }

    public void registerHandlers() {
        manager.addListener(EcossistemaManager.EVOLVE, evt -> Platform.runLater(this::update));
    }

    public void createViews() {
        this.board = new GridPane();
        this.board.setStyle("-fx-background-color: black");
        this.setCenter(board);
    }

    public void update() { // mudar para as X,Y coordenadas

        board.getChildren().clear();
        Set<IElemento> elementos = manager.getElementos();
        for (IElemento elemento : elementos) {
            //System.out.println(elemento.toString());
            ImageView imageView = createImageView(elemento, elemento.getArea().cima(), elemento.getArea().direita());
            board.add(imageView, elemento.getX(),elemento.getY());
        }

        /*for (IElemento elemento : manager.getElementos()) {
            System.out.println(elemento.getArea());
        }
        board.getChildren().clear();
        Set<IElemento> elementos = manager.getElementos();
        double ecoHeight = manager.getAltura();
        double ecoWidth = manager.getLargura();
        double screenHeight = stage.getHeight();
        double screenWidth = stage.getWidth();

        for (IElemento elemento : elementos) {
            Area area = elemento.getArea();

            double eleX = (area.esquerda() + area.direita()) / 2.0;
            double eleY = (area.cima() + area.baixo()) / 2.0;

            double heightScale = (screenHeight > ecoHeight) ? screenHeight / ecoHeight : ecoHeight / screenHeight;
            double widthScale = (screenWidth > ecoWidth) ? screenWidth / ecoWidth : ecoWidth / screenWidth;

            double posX = eleX * widthScale;
            double posY = eleY * heightScale;

            if (posX > screenWidth || posY > screenHeight) {
                System.out.println("FORA DO PANE\n");
            }

            System.out.println("posX: " + posX + " posY: " + posY + " screenWidth: " + screenWidth + " screenHeight: " + screenHeight + " ecoWidth: " + ecoWidth + " ecoHeight: " + ecoHeight + " widthScale: " + widthScale + " heightScale: " + heightScale);
            ImageView imageView = createImageView(elemento, heightScale, widthScale);
            board.add(imageView, (int) posX, (int) posY);
        }*/

    }

    private ImageView createImageView(IElemento elemento, double heightScale, double widthScale) {
        Image image = getElementImage(elemento);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(widthScale);
        imageView.setFitHeight(heightScale);
        return imageView;
    }


    private Image getElementImage(IElemento element) {
        switch (element.getType()) {
            case FAUNA -> {
                return ImageManager.getImage("animal.png");
            }
            case FLORA -> {
                return ImageManager.getImage("flora.png");
            }
            case INANIMADO -> {
                return ImageManager.getImage("pedra.png");
            }
            default -> {
                return null;
            }
        }
    }
}
