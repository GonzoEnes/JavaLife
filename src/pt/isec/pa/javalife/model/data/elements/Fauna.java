package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.fsm.Context;
import pt.isec.pa.javalife.model.data.fsm.State;

public final class Fauna extends ElementoBase implements IElementoComForca {
//esta sempre em movimento a nao ser q se esteja a alimentar(mesma posicao q alguma flora)
//movimento tem velocidade por tick e direcao (0-359) direita esquerda cima baixo
// considerando-se adjacente uma área de igual dimensão à sua (em cima, em baixo, à esquerda ou à direita);
    private double forca;
    private static int contadorId = 0;
    private Context context;
    private int reproduzaoContador = 0;
    public static final int MAX_FORCA = 100;
    public static final int MIN_FORCA = 0;
    public static final int INIT_FORCA = 50;
    public static final int PERDA_FORCA = 1;
    public static final int MAX_REPRODUCAO = 2;

    public Fauna(Area area, Ecossistema ecossistema) {
        super(++contadorId, area);
        this.forca = INIT_FORCA;
        this.context = new Context(ecossistema);
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
        context.evolve(this);
    }

    public State getState(){
       return context.getState();
    }

    @Override
    public String toString() {
        return getId() + " " + context.getState();
    }

}
