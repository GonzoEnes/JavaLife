package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.fsm.states.IJavaLifeState;
import pt.isec.pa.javalife.model.data.fsm.states.MovimentarState;

public final class Fauna extends ElementoBase implements IElementoComForca {
    private double forca;
    private static int idS = 0;

    private Ecossistema ecossistema;
    private IJavaLifeState faunaState;

    public Fauna(Area area, Elemento tipo, Ecossistema ecossistema) {
        super(++idS, area, tipo);
        this.forca = 50;
        this.ecossistema = ecossistema;
        this.faunaState = new MovimentarState(this,ecossistema);
    }
    
    public IJavaLifeState getFaunaState() {
        return faunaState;
    }

    public void setFaunaState(IJavaLifeState faunaState) {
        this.faunaState = faunaState;
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

}
