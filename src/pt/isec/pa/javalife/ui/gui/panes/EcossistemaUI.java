package pt.isec.pa.javalife.ui.gui.panes;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
        this.setHeight(stage.getHeight());
        this.setWidth(manager.getLargura());
        createViews();
        registerHandlers();
        update();
        this.requestFocus();

    }

    public void registerHandlers() {

    }

    public void createViews() {
        Set<IElemento> elementos = manager.getElementos();
        this.board = new GridPane();
        this.setCenter(board);

            /*switch (elemento.getType()) {
                case FAUNA:
                    rect.setFill(Color.BLACK);
                    break;
                case INANIMADO:
                    rect.setFill(Color.BLUE);
                    break;
                case FLORA:
                    rect.setFill(Color.VIOLET);
                    break;
            }

            board.add(rect, (int) (elemento.getArea().esquerda() * widthScaleFactor), (int) (elemento.getArea().cima() * heightScaleFactor));
        }*/
    }

    public void update() {
        board.getChildren().clear();
        Set<IElemento> elementos = manager.getElementos();

        double cellWidth = stage.getWidth() / manager.getLargura();
        for (IElemento elemento : elementos) {
            Area area = elemento.getArea();
            int columnIndex = (int)area.esquerda() * (int)cellWidth;//(int) (area.esquerda() * (stage.getWidth() / manager.getLargura())); // Usar stage.getWidth() em vez de stage.getHeight()
            int rowIndex = (int)area.cima();//(int) (area.cima() * (stage.getHeight())); // Manter o uso de stage.getHeight() aqui
            System.out.println("\n" + columnIndex + "\n" + rowIndex);
            ImageView imageView = createImageView(elemento);
            System.out.println("OLA " + imageView.getFitHeight());
            System.out.println("OLA 2" + imageView.getFitWidth());

            board.add(imageView, columnIndex, rowIndex);
        }
    }

    private ImageView createImageView(IElemento elemento) {
        Image image = getElementImage(elemento);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(board.getWidth() / manager.getLargura());
        imageView.setFitHeight(board.getWidth() / manager.getLargura());
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
