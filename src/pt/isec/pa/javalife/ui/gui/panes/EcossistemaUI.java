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
        this.requestFocus();
    }

    public void registerHandlers() {

    }

    public void createViews() {
        Set<IElemento> elementos = manager.getElementos();

        this.board = new GridPane();
        this.board.setMaxHeight(manager.getAltura());
        this.board.setMaxWidth(manager.getLargura());
        this.setCenter(board);

        int cellWidth = (int)getWidth() / manager.getLargura(); // ir buscar o tamanho de cada cell
        int cellHeight = (int)getHeight() / manager.getAltura(); // e a altura

        System.out.println("cellWidth: " + cellWidth + "\n\n" + "cellHeight: " + cellHeight);

        for (IElemento elemento : elementos) {
            Rectangle rect = new Rectangle(
                    elemento.getArea().esquerda() * cellWidth,
                    elemento.getArea().cima() * cellHeight,
                    (elemento.getArea().direita() - elemento.getArea().esquerda()) * cellWidth,
                    (elemento.getArea().baixo() - elemento.getArea().cima()) * cellHeight
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

            board.add(rect, (int)elemento.getArea().esquerda(), (int)elemento.getArea().cima());
        }
    }
}
