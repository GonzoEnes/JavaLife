package pt.isec.pa.javalife.ui.gui.panes;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;
import pt.isec.pa.javalife.model.data.elements.Animal;
import pt.isec.pa.javalife.model.data.elements.Elemento;
import pt.isec.pa.javalife.model.data.elements.IElemento;

import java.util.Set;

public class EcossistemaUI extends BorderPane {
    private EcossistemaManager manager;
    private GridPane board;

    public EcossistemaUI(EcossistemaManager manager) {
        this.manager = manager;
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

        /*// Get the dimensions of the ecosystem
        int ecosystemWidth = manager.getLargura();
        int ecosystemHeight = manager.getAltura();

        // Get the dimensions of the screen
        double screenWidth = this.getWidth();
        System.out.println(screenWidth);
        double screenHeight = this.getHeight();

        // Calculate the scaling factors
        double widthScaleFactor = screenWidth / ecosystemWidth;
        double heightScaleFactor = screenHeight / ecosystemHeight;

        // Iterate through elements to create and add rectangles to the GridPane
        for (IElemento elemento : elementos) {
            // Calculate the scaled dimensions of the element
            double scaledWidth = (elemento.getArea().direita() - elemento.getArea().esquerda()) * widthScaleFactor;
            double scaledHeight = (elemento.getArea().baixo() - elemento.getArea().cima()) * heightScaleFactor;

            Rectangle rect = new Rectangle(
                    elemento.getArea().esquerda() * widthScaleFactor,
                    elemento.getArea().cima() * heightScaleFactor,
                    scaledWidth,
                    scaledHeight
            );

            switch (elemento.getType()) {
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

    }
}
