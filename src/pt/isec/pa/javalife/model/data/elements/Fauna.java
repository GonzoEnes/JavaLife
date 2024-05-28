package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.fsm.Context;
import pt.isec.pa.javalife.model.data.fsm.State;

public sealed class Fauna extends ElementoBase implements IElementoComForca permits Animal {
    //esta sempre em movimento a nao ser q se esteja a alimentar(mesma posicao q alguma flora)
//movimento tem velocidade por tick e direcao (0-359) direita esquerda cima baixo
// considerando-se adjacente uma área de igual dimensão à sua (em cima, em baixo, à esquerda ou à direita);
    private Context stateMachine;
    private double strength;
    private static int idCounter = 0;
    private int reproductionCounter = 0;
    private int speed;
    private int reproductionWaitCounter = 0;
    private static final double initialStrength = 50;
    private static final double minimumStrength = 0;
    private static final double maximumStrength = 100;
    private static final double strengthLoss = 0.5;
    private static final double maximumReproduction = 2;
    private static final int minimumSpeed = 1;
    private static final int strengthToChangeStateForProcurarComida = 35;
    private static final int strengthToChangeStateForReproduzir = 50;
    private static final int maximumReproductionRange = 5;
    private static final int reproductionWaitCounterFlag =10;

    public Fauna(Area area, Ecossistema ecossistema) {
        super(++idCounter, area);
        this.strength = initialStrength;
        this.speed=minimumSpeed;
        this.stateMachine = new Context(ecossistema);
    }
    public double getMaximumStrength() {
        return maximumStrength;
    }
    public int getStrengthToChangeStateForProcurarComida() {
        return strengthToChangeStateForProcurarComida;
    }
    public int getStrengthToChangeStateForReproduzir() {
        return strengthToChangeStateForReproduzir;
    }
    public int getMaximumReproductionRange() {
        return maximumReproductionRange;
    }
    public int getReproductionWaitCounterFlag() {
        return reproductionWaitCounterFlag;
    }
    public void setReproductionWaitCounter(int reproductionWaitCounter) {
        this.reproductionWaitCounter = reproductionWaitCounter;
    }
    public int getReproductionWaitCounter() {
        return reproductionWaitCounter;
    }
    public void increaseReproductionWaitCounter() {
        reproductionWaitCounter++;
    }
    public void setSpeed(int speed) {
        if(speed<=minimumSpeed)
            this.speed=minimumSpeed;
        this.speed = speed;
    }
    public int getSpeed() {
        return speed;
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
    public Elemento getType() {
        return Elemento.FAUNA;
    }
    public void increaseStrength(double increase) {
        if(this.strength+increase>=maximumStrength)
            this.strength=maximumStrength;
        this.strength+=increase;
    }
    public void decreaseStrength(double decrease) {
        if(this.strength-decrease<=minimumStrength)
            this.strength=minimumStrength;
        this.strength-=decrease;
    }
    public void decreaseStrengthByMovement() {
        decreaseStrength(strengthLoss);
    }
    public boolean checkReproduction() {
        return reproductionCounter<maximumReproduction;
    }
    public void increaseReproductionCounter() {
        reproductionCounter++;
    }
    public void evolve() {
        stateMachine.evolve(this);
    }

}
