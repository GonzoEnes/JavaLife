package pt.isec.pa.javalife.ui;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.elements.IElemento;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeContext;
import pt.isec.pa.javalife.utils.PAInput;

import java.util.Set;

import static pt.isec.pa.javalife.model.data.fsm.JavaLifeState.*;

public class TextUI {
    private EcossistemaManager manager;


    public TextUI(EcossistemaManager manager) { // está a correr sem dar erro, falta testar as funções :))))
        this.manager = manager;
    }


    public boolean start() throws InterruptedException {
        Set<IElemento> elementos = manager.getElementos();
        for (IElemento elemento : elementos) {
            if (elemento instanceof Fauna) {
                Fauna fauna = (Fauna) elemento;
                manager.getFsm().evolve();
                return true;
            }
        }
        return false;
    }



    public boolean procurarComida() throws InterruptedException {
        System.out.printf("\nWSexo %s\n",manager.getFsm().getState());
        int choice = PAInput.chooseOption("Sexzo", "Movimentar", "Procurar", "Estado Morrer", "Stop machine");
        switch (choice) {
            case 1, 2, 3 -> {
                return manager.getFsm().evolve();
            }
            case 4 -> {
                System.exit(0);
            }
            default -> {
                return false;
            }
        }
        return false;
    }

    public boolean morrer() {
        System.out.printf("\nWSexo %s\n",manager.getFsm().getState());

        return false;

    }
    // return true;


    public boolean reproduzir() throws InterruptedException {
        System.out.printf("\nWSexo %s\n",manager.getFsm().getState());
        int choice = PAInput.chooseOption("Sexzo", "Movimentar", "Estado Morrer", "Stop machine");
        switch (choice) {
            case 1, 2 -> {
                return manager.getFsm().evolve();
            }
            case 3 -> {
                System.exit(0);
            }
            default -> {
                return false;
            }
        }
        return false;
    }

    public boolean movimentar() throws InterruptedException {
        System.out.printf("\nWSexo %s\n",manager.getFsm().getState());
        int choice = PAInput.chooseOption("Sexzo", "Estado Procurar Comida", "Estado Reproduzir", "Estado Morrer", "Stop machine");

        switch (choice) {
            case 1, 2, 3 -> {
                return manager.getFsm().evolve();
            }
            case 4 -> {
                System.exit(0);
            }
            default -> {
                return false;
            }
        }
        return false;
    }

}
