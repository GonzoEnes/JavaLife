package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.fsm.Context;
import pt.isec.pa.javalife.model.data.fsm.State;

public final class Fauna extends ElementoBase implements IElementoComForca {

    private double forca;

    private static int idS = 0;

    private Context context;

    public Fauna(Area area, Ecossistema ecossistema) {
        super(++idS, area);
        this.forca = 0;
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

    public Fauna evolve()
    {
      return context.evolve(this);
    }

    public State getState(){
       return context.getState();
    }

    @Override
    public String toString() {
        return getId() + " " + context.getState();
    }

    public void move() {
        this.forca = this.forca - 0.5;
    }
}
