package pt.isec.pa.javalife;

import javafx.application.Application;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;
import pt.isec.pa.javalife.model.gameengine.GameEngine;
import pt.isec.pa.javalife.ui.gui.JavaLifeFX;

public class Main {
    public static void main(String[] args){
        Application.launch(JavaLifeFX.class, args);
    }
}