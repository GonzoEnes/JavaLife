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

        // Definir as dimensões do ecossistema e da tela
        double ecoWidth = manager.getLargura(); // largura do ecossistema
        double ecoHeight = manager.getAltura(); // altura do ecossistema
        double screenWidth = stage.getWidth(); // largura do ecrã
        double screenHeight = stage.getHeight(); // altura do ecrã

        if (ecoWidth == 0 || ecoHeight == 0 || screenWidth == 0 || screenHeight == 0) {
            System.out.println("One of the dimensions is zero");
            return;
        }

        for (IElemento elemento : elementos) {
            double eleX = elemento.getArea().esquerda();
            double eleY = elemento.getArea().cima();

            double widthScale = screenWidth / ecoWidth;
            double heightScale = screenHeight / ecoHeight;

            double posX = eleX * widthScale;
            double posY = eleY * heightScale;

            if (posX < 0 || posX >= screenWidth || posY < 0 || posY >= screenHeight) {
                System.out.println("Image is being added outside the GridPane");
                continue;
            }

            double larg = (elemento.getArea().direita() - elemento.getArea().esquerda()) * widthScale;
            double alt = (elemento.getArea().cima() - elemento.getArea().baixo()) * heightScale;

            ImageView imageView = createImageView(elemento, larg, alt);
            if (imageView.getImage() == null) {
                System.out.println("Image is not being loaded correctly");
                continue;
            }

            board.add(imageView, (int)Math.ceil(posX), (int)Math.ceil(posY));
        }
    }
    /*private double[] calculateElementDimensions(IElemento elemento, double scaleFactor) {
        // Coordenadas do canto superior esquerdo do elemento no ecossistema
        double eleX = (elemento.getArea().direita() - elemento.getArea().esquerda()) / 2.0;
        double eleY = (elemento.getArea().cima() - elemento.getArea().baixo()) / 2.0;

        // Aplica o fator de escala às coordenadas e dimensões
        double posX = eleX * scaleFactor;
        double posY = eleY * scaleFactor;
        double larg = (elemento.getArea().direita() - elemento.getArea().esquerda()) * scaleFactor;
        double alt = (elemento.getArea().cima() - elemento.getArea().baixo()) * scaleFactor;

        return new double[]{larg, alt, posX, posY};
    }*/

    private ImageView createImageView(IElemento elemento, double larg, double alt) {
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
