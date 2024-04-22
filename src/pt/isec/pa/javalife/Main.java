package pt.isec.pa.javalife;

import javafx.application.Application;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;
import pt.isec.pa.javalife.ui.TextUI;
import pt.isec.pa.javalife.ui.gui.JavaLifeFX;

public class Main {

    public static EcossistemaManager manager;

    static {
        manager = new EcossistemaManager(350);
    }
    public static void main(String[] args) {
        Application.launch(JavaLifeFX.class, args);
    }
}