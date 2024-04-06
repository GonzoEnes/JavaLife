package pt.isec.pa.javalife;

import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;
import pt.isec.pa.javalife.ui.TextUI;

public class Main {
    public static void main(String[] args) {
        EcossistemaManager manager = new EcossistemaManager();
        TextUI ui = new TextUI(manager);
        //ui.start();
    }
}