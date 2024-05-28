package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;

public sealed class Flora extends ElementoBase implements IElementoComForca, IElementoComImagem permits Erva {
    private double strength;
    private String image;
    private static int idCounter = 0;
    private int reproductionCounter = 0;

    private static final double initialStrength = 50;
    private static final double minimumStrength = 0;
    private static final double maximumStrength = 100;
    private static final double increaseStrength = 0.5;
    private static final double reduceStrength = 0.5;
    private static final double maximumReproduction = 2;


    public Flora(Area area) {
        super(++idCounter, area);
        this.strength = initialStrength;
    }
    public Flora(Area area, String imagem) {
        super(++idCounter, area);
        this.strength = 50;
        this.image = imagem;
    }

    public double getReduceStrength() {
        return reduceStrength;
    }

    @Override
    public double getStrength() {
        return strength;
    }
    @Override
    public void setStrength(double strength) {
        if (strength > maximumStrength) {
            this.strength = maximumStrength;
        } else if (strength < minimumStrength) {
            this.strength = minimumStrength;
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

    public void beConsumed() {
        strength -= reduceStrength;
    }
    @Override
    public Elemento getType() {
        return Elemento.FLORA;
    }
}
