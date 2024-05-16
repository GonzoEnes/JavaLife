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
    private JavaLifeContext context;
    public Fauna(Area area, Elemento tipo, int x, int y) {
        super(++idS, area, tipo, x, y);
        System.out.println(idS);
        this.forca = 50;
        this.context = new JavaLifeContext(this);
        //context.evolve();
        this.context.evolve();
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
        context.evolve();
    }
    public JavaLifeState getState(){
       return context.getState();
    }

    @Override
    public String toString() {
        return getId() + " " + context.getState();
    }
}
