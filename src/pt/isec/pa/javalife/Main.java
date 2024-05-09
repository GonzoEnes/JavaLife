package pt.isec.pa.javalife;

import javafx.application.Application;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.ui.TextUI;
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
        TextUI ui = new TextUI(manager);
        ui.start();
    }
}