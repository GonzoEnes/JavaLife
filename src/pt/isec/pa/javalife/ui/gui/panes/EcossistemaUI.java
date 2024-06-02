package pt.isec.pa.javalife.ui.gui.panes;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pt.isec.pa.javalife.model.data.ecosystem.EcosystemManager;
import pt.isec.pa.javalife.model.data.elements.*;
import pt.isec.pa.javalife.ui.gui.resources.ImageLoader;

import java.util.Objects;


public class EcossistemaUI extends BorderPane {
    private EcosystemManager manager;
    private Canvas canvas;

    public EcossistemaUI(EcosystemManager manager) throws CloneNotSupportedException {
        super();
        this.manager = manager;
        createView();
        registerHandlers();
        update();
    }

    private void createView(){
        Pane backgroundPane = new Pane();
        String imagePath = Objects.requireNonNull(getClass().getResource("/pt/isec/pa/javalife/ui/gui/resources/images/ecosystem.jpg")).toExternalForm();
        backgroundPane.setStyle("-fx-background-image: url('" + imagePath + "');" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-position: center center;" +
                "-fx-background-size: cover;");
        GaussianBlur blur = new GaussianBlur(10);
        backgroundPane.setEffect(blur);
        manager.startEcosystem();
        this.canvas = new Canvas(manager.getLargura(), manager.getAltura());
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundPane, canvas);
        this.setCenter(stackPane);
    }

    public void registerHandlers() {
        manager.addClient(EcosystemManager.ECOSSISTEMA_EVOLVE, evt -> Platform.runLater(() -> {
            try {
                update();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }));
    }

    public void update() throws CloneNotSupportedException {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        clearScreen(gc);
        manager.getElementos().forEach(elemento -> drawElement(gc, elemento));
    }

    private void clearScreen(GraphicsContext gc) {
        gc.setFill(Color.LIGHTGREEN);
        gc.fillRect(0,0, manager.getLargura(), manager.getAltura());
    }

    public void updateSize(double newWidth, double newHeight) throws CloneNotSupportedException {
        setWidth(newWidth);
        setHeight(newHeight);
        update();
    }

    private void drawElement(GraphicsContext gc, IElement element) {
        switch (element.getType()) {
            case FAUNA -> {
                gc.drawImage(ImageLoader.getImage("fauna/"+((Fauna)element).getImage()), element.getArea().left(), element.getArea().up(), element.getArea().right() - element.getArea().left(), element.getArea().down() - element.getArea().up());
                gc.fillText(String.valueOf(((Fauna)element).getStrength()), element.getArea().right(), element.getArea().down());
            }
            case FLORA -> {
                gc.drawImage(ImageLoader.getImage("flora/"+((Flora)element).getImage()), element.getArea().left(), element.getArea().up(), element.getArea().right() - element.getArea().left(), element.getArea().down() - element.getArea().up());
                gc.fillText(String.valueOf(((Flora)element).getStrength()), element.getArea().right(), element.getArea().down());
                System.out.println(element.getArea().right()+" "+element.getArea().down()+" "+((Flora)element).getStrength());
            }
            case INANIMADO -> {
                gc.setFill(Color.GRAY);
                gc.fillRect(element.getArea().left(), element.getArea().up(), element.getArea().right() - element.getArea().left(), element.getArea().down() - element.getArea().up());
            }
        }
    }
}