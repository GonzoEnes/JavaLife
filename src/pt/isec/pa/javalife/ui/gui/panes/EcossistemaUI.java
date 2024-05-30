package pt.isec.pa.javalife.ui.gui.panes;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;
import pt.isec.pa.javalife.model.data.elements.*;
import pt.isec.pa.javalife.ui.gui.resources.ImageLoader;

public class EcossistemaUI extends Canvas {

    private EcossistemaManager manager;

    public EcossistemaUI(EcossistemaManager manager) {
        super(manager.getLargura(), manager.getAltura());
        this.manager = manager;
        registerHandlers();
        update();
    }

    public void registerHandlers() {
        manager.addClient(EcossistemaManager.ECOSSISTEMA_EVOLVE, evt -> Platform.runLater(this::update));
    }

    public void update() {
        GraphicsContext gc = this.getGraphicsContext2D();
        clearScreen(gc);
        manager.getElementos().forEach(elemento -> drawElement(gc, elemento));
    }

    private void clearScreen(GraphicsContext gc) {
        gc.setFill(Color.LIGHTGREEN);
        gc.fillRect(0,0, manager.getLargura(), manager.getAltura());
    }

    public void updateSize(double newWidth, double newHeight) {
        setWidth(newWidth);
        setHeight(newHeight);
        update();
    }

    private void drawElement(GraphicsContext gc, IElemento element) {
        switch (element.getType()) {
            case FAUNA -> {
                gc.drawImage(ImageLoader.getImageFauna(((Fauna)element).getImage()), element.getArea().left(), element.getArea().up(), element.getArea().right() - element.getArea().left(), element.getArea().down() - element.getArea().up());
            }
            case FLORA -> {
                gc.drawImage(ImageLoader.getImage("erva.png"), element.getArea().left(), element.getArea().up(), element.getArea().right() - element.getArea().left(), element.getArea().down() - element.getArea().up());
            }
            case INANIMADO -> {
                gc.setFill(Color.GRAY);
                gc.fillRect(element.getArea().left(), element.getArea().up(), element.getArea().right() - element.getArea().left(), element.getArea().down() - element.getArea().up());
            }
        }
    }
}