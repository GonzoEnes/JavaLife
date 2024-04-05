package pt.isec.pa.javalife;

import pt.isec.pa.javalife.model.data.fsm.JavaLifeContext;
import pt.isec.pa.javalife.ui.TextUI;

public class Main {
    public static void main(String[] args) {
        JavaLifeContext context = new JavaLifeContext();
        TextUI ui = new TextUI(context);
        //ui.start();
    }
}