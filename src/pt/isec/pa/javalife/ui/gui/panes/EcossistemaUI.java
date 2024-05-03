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

    }

    public void createViews() {
        this.board = new GridPane();
        this.board.setStyle("-fx-background-color: black");
        this.setCenter(board);
        //setAlignment(board, Pos.CENTER);
    }

    public void update() {
        board.getChildren().clear();
        Set<IElemento> elementos = manager.getElementos();

        double widthScaleFactor = stage.getWidth() / manager.getLargura();
        double heightScaleFactor = stage.getHeight() / manager.getAltura();

        for (IElemento elemento : elementos) {
            ImageView imageView = createImageView(elemento, widthScaleFactor, heightScaleFactor);
            board.add(imageView, (int) elemento.getArea().esquerda(), (int) elemento.getArea().cima());
        }
    }
    private ImageView createImageView(IElemento elemento, double widthScaleFactor, double heightScaleFactor) {
        Area area = elemento.getArea();
        double larg = (area.direita() - area.esquerda()) * widthScaleFactor;
        double alt = (area.baixo() - area.cima()) * heightScaleFactor;
        Image image = getElementImage(elemento);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(larg);
        imageView.setFitHeight(alt);
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
