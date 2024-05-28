package pt.isec.pa.javalife;

import javafx.application.Application;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;
import pt.isec.pa.javalife.model.gameengine.GameEngine;
import pt.isec.pa.javalife.ui.gui.JavaLifeFX;

public class Main {
    public static EcossistemaManager manager = null;
    public static void main(String[] args) throws InterruptedException {
        manager = new EcossistemaManager(100);
        GameEngine gameEngine = new GameEngine();
        gameEngine.registerClient((g,t) -> manager.evolve(gameEngine,t));
        gameEngine.start(100);
        Application.launch(JavaLifeFX.class, args);
        gameEngine.waitForTheEnd();

    }
}