package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeContext;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeState;
import pt.isec.pa.javalife.model.data.fsm.states.IJavaLifeState;
import pt.isec.pa.javalife.model.data.fsm.states.MovimentarState;
import pt.isec.pa.javalife.model.gameengine.GameEngine;

public final class Fauna extends ElementoBase implements IElementoComForca {
    private double forca;
    private static int idS = 0;
    private Ecossistema ecossistema;
    private JavaLifeContext context;
    public Fauna(Area area, Elemento tipo) throws InterruptedException {
        super(++idS, area, tipo);
        this.forca = 50;
        this.ecossistema = ecossistema;
        this.initStateMachine();
    }

    private void initStateMachine() throws InterruptedException {
        // Criar o contexto da máquina de estados
        this.context = new JavaLifeContext(this);
        // Evoluir o estado inicial
        //context.evolve();
    }

    @Override
    public double getForca() {
        return forca;
    }

    @Override
    public void setForca(double forca) {
        this.forca = forca;
    }

    @Override
    public Elemento getType() {
        return Elemento.FAUNA;
    }

    public void evolve(){
        try {
            context.evolve();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public JavaLifeState getState(){


       return context.getState();

    }

    @Override
    public String toString() {
        return String.valueOf(idS);
    }
}
