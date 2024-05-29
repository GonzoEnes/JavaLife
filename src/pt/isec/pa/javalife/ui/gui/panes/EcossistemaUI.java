package pt.isec.pa.javalife.ui.gui.panes;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;
import pt.isec.pa.javalife.model.data.elements.*;
import pt.isec.pa.javalife.ui.gui.resources.ImageLoader;

public class EcossistemaUI extends Canvas {
    private EcossistemaManager manager;

    public EcossistemaUI(EcossistemaManager manager) {
        super(100, 100);
        this.manager = manager;

        registerHandlers();
        update();
    }

    public void registerHandlers() {
        manager.addClient(EcossistemaManager.ECOSSISTEMA_EVOLVE, evt -> Platform.runLater(this::update));
    }

    public void update() { // mudar para as X,Y coordenadas

        /*board.getChildren().clear();
        Set<IElemento> elementos = manager.getElementos();
        for (IElemento elemento : elementos) {
            //System.out.println(elemento.toString());
            ImageView imageView = createImageView(elemento, elemento.getArea().cima(), elemento.getArea().direita());
            board.add(imageView, elemento.getX(),elemento.getY());
        }*/
        GraphicsContext gc = this.getGraphicsContext2D();
        clearScreen(gc);
        manager.getElementos().forEach(elemento -> drawElement(gc, elemento));
        //manager.setInitialEcossistemaConfigs((int)getHeight(), (int)getWidth());
    }

    private void clearScreen(GraphicsContext gc) {
        gc.setFill(Color.LIGHTGREEN);
        gc.fillRect(0,0,manager.getLargura(), manager.getAltura());
    }

    public void updateSize(double newWidth, double newHeight) {
        setWidth(newWidth);
        setHeight(newHeight);
        update();
    }

    private void drawElement(GraphicsContext gc, IElemento element) {
        switch (element.getType()) {
            case FAUNA -> {
                gc.drawImage(ImageLoader.getImage("animal.png"), element.getArea().left(), element.getArea().up(), element.getArea().right() - element.getArea().left(), element.getArea().down() - element.getArea().up());
            }
            case FLORA -> {
                gc.drawImage(ImageLoader.getImage("erva.png"), element.getArea().left(), element.getArea().up(), element.getArea().right() - element.getArea().left(), element.getArea().down() - element.getArea().up());
            }
            case INANIMADO -> {
                //gc.drawImage(ImageManager.getImage("pedra.png"), element.getArea().esquerda(), element.getArea().cima(), element.getArea().direita() - element.getArea().esquerda(), element.getArea().baixo() - element.getArea().cima());
                gc.setFill(Color.GRAY);
                gc.fillRect(element.getArea().left(), element.getArea().up(), element.getArea().right() - element.getArea().left(), element.getArea().down() - element.getArea().up());
            }
        }
    }
}