package pt.isec.pa.javalife;

import javafx.application.Application;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;
import pt.isec.pa.javalife.model.gameengine.GameEngine;
import pt.isec.pa.javalife.ui.gui.JavaLifeFX;

public class Main {
    public static EcossistemaManager manager;

    static {
        try {
            manager = new EcossistemaManager(350);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        //Application.launch(JavaLifeFX.class, args);
        /*TextUI ui = new TextUI(manager);
        ui.start()*/;
        //GameEngine gameEngine = new GameEngine();
        //gameEngine.registerClient((g,t) -> manager.evolve(gameEngine,t));
        //gameEngine.start(500);
        Application.launch(JavaLifeFX.class, args);
        //gameEngine.waitForTheEnd();

    }
}