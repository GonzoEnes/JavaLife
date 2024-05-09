package pt.isec.pa.javalife.ui;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;
import pt.isec.pa.javalife.model.data.elements.Elemento;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.elements.IElemento;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeContext;
import pt.isec.pa.javalife.utils.PAInput;

import java.util.ArrayList;
import java.util.Set;

import static pt.isec.pa.javalife.model.data.fsm.JavaLifeState.*;

public class TextUI {
    private EcossistemaManager manager;
    public TextUI(EcossistemaManager manager) { // está a correr sem dar erro, falta testar as funções :))))
        this.manager = manager;
    }

    public boolean start() throws InterruptedException {
        //manager.addElemento(new Fauna(new Area(10,10,10,10), Elemento.FAUNA, null));
        movimentar();
        return false;
    }

    public boolean movimentar1() throws InterruptedException {
        System.out.printf("\nWSexo %s\n",manager.getFsm().getState());
        int choice = PAInput.chooseOption("Sexo", "Estado Procurar Comida", "Estado Reproduzir", "Estado Morrer", "Stop machine");

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
    public boolean movimentar() throws InterruptedException {
        //System.out.printf("\nWSexo %s\n",manager.getFsm().getState());
        //int choice = PAInput.chooseOption("Sexo", "Estado Procurar Comida", "Estado Reproduzir", "Estado Morrer", "Stop machine");
        ArrayList<Fauna> a = new ArrayList<>();
        Fauna elemento = new Fauna(new Area(10,10,10,10), Elemento.FAUNA);
        Fauna elemento1 = new Fauna(new Area(10,10,10,10), Elemento.FAUNA);
        Fauna elemento2 = new Fauna(new Area(10,10,10,10), Elemento.FAUNA);
        a.add(elemento);
        a.add(elemento1);
        a.add(elemento2);

        for(int i = 0; i < 100 ;i++){
            for (Fauna o : a) {
                System.out.println(o.toString());
                System.out.println(o.getState());
                o.evolve();
            }
            //manager.getFsm().evolve();
            int choice = PAInput.chooseOption("Sexo", "Estado Procurar Comida", "Estado Reproduzir", "Estado Morrer", "Stop machine");
        }
        return false;
    }
    public boolean procurarComida() throws InterruptedException {
        System.out.printf("\nWSexo %s\n",manager.getFsm().getState());
        int choice = PAInput.chooseOption("Sexo", "Movimentar", "Procurar", "Estado Morrer", "Stop machine");
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
}
