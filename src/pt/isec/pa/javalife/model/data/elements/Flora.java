package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;

public sealed class Flora extends ElementoBase implements IElementoComForca, IElementoComImagem permits Erva {
    private double strength;
    private String image;
    private static int idCounter = 0;
    private int reproductionCounter = 0;
    private Ecossistema ecossistema;
    private static final double initialStrength = 50;
    private static final double minimumStrength = 0;
    private static final double maximumStrength = 100;
    private static final double decreaseStrength = 1;
    private static final double increaseStrength = 0.5;
    private static final double maximumReproduction = 2;
    private static final int strengthToReproduce = 90;
    private static final double newbornFloraStrength = 50;
    private static final double afterReproduceStrength = 60;

    public Flora(Area area,Ecossistema ecossistema,String image){
        super(++idCounter, area);
        this.ecossistema=ecossistema;
        this.image=image;
        this.strength = initialStrength;
    }
    public Flora(Area area,Ecossistema ecossistema,String image,double strength){
        super(++idCounter, area);
        this.ecossistema=ecossistema;
        this.image=image;
        this.strength = strength;
    }

    public double getDecreaseStrength() {
        return decreaseStrength;
    }
    @Override
    public double getStrength() {
        return strength;
    }
    @Override
    public void setStrength(double strength) {
        if (strength > maximumStrength) {
            this.strength = maximumStrength;
            return;
        } else if (strength < minimumStrength) {
            this.strength = minimumStrength;
            return;
        }
        this.strength = strength;
    }
    @Override
    public String getImage() {
        return "";
    }
    @Override
    public void setImage(String imagem) {

    }
    public void increaseStrength() {
        if (this.strength + increaseStrength >= maximumStrength){
            this.strength = maximumStrength;
            return;
        }
        this.strength+=increaseStrength;
    }
    public void decreaseStrength() {
        if(this.strength-decreaseStrength<=minimumStrength){
            this.strength=minimumStrength;
            return;
        }
        this.strength-=decreaseStrength;
    }
    private void increaseReproductionCounter() {
        reproductionCounter++;
    }
    @Override
    public Elemento getType() {
        return Elemento.FLORA;
    }
    public void reproduce() {
        if (strength >= strengthToReproduce && reproductionCounter < maximumReproduction) {
            Area aux = ecossistema.hasSpaceForNewFauna(getArea());
            if (aux != null) {
                IElemento newFlora = Elemento.createElemento(Elemento.FLORA,aux,ecossistema);
                assert newFlora != null;
                ((Flora)newFlora).setStrength(newbornFloraStrength);
                ecossistema.addElemento(newFlora);
                increaseReproductionCounter();
                setStrength(afterReproduceStrength);
            }
        }
    }
    public void evolve() {
        increaseStrength();
        reproduce();
    }
}
