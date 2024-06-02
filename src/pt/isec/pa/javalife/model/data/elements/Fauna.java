package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.Ecosystem;
import pt.isec.pa.javalife.model.data.fsm.Context;

import java.util.Random;

public sealed class Fauna extends ElementBase implements IElementWithStrength, IElementWithImage permits Animal {
    private Context stateMachine;
    private double strength;
    private String image;
    private static int idCounter = 0;
    private int reproductionCounter = 0;
    private double speed;
    private int reproductionWaitCounter = 0;
    private Ecosystem ecossistema;
    private Fauna faunaBeingAttacked;
    private static final double initialStrength = 50;
    private static final double minimumStrength = 0;
    private static final double maximumStrength = 100;
    private static final double reduceStrength = 0.5;
    private static final double maximumReproduction = 2;
    private static final int minimumSpeed = 3;
    private static final int strengthToChangeStateForProcurarComida = 35;
    private static final int strengthToChangeStateForReproduzir = 50;
    private static final int strengthToChangeStateForMovimentar = 80;
    private static final int maximumReproductionRange = 5;
    private static final int reproductionWaitCounterFlag =10;
    private static final int attackCost =10;

    public Fauna(Area area, Ecosystem ecossistema, String image) {
        super(++idCounter, area);
        this.strength = initialStrength;
        this.speed=minimumSpeed;
        this.ecossistema = ecossistema;
        this.image=image;
        this.faunaBeingAttacked = null;
        this.stateMachine = new Context(this);
    }

    public int getStrengthToChangeStateForMovimentar() {
        return strengthToChangeStateForMovimentar;
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
    public void setReproductionWaitCounter(int reproductionWaitCounter) {
        this.reproductionWaitCounter = reproductionWaitCounter;
    }
    public void setSpeed(double speed) {
        if(speed<=minimumSpeed)
            this.speed=minimumSpeed;
        this.speed = speed;
    }
    public double getSpeed() {
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
            return;
        } else if (strength < minimumStrength) {
            this.strength = minimumStrength;
            return;
        }
        this.strength = strength;
    }
    @Override
    public Element getType() {
        return Element.FAUNA;
    }
    @Override
    public void resetCounterId() {
        idCounter = 0;
    }
    @Override
    public String getImage() {
        return image;
    }
    @Override
    public void setImage(String image) {
        this.image = image;
    }
    private void increaseReproductionWaitCounter() {
        if(reproductionWaitCounter<reproductionWaitCounterFlag)
            reproductionWaitCounter++;
    }
    public void increaseStrength(double increase) {
        if(this.strength+increase>=maximumStrength){
            this.strength=maximumStrength;
            return;
        }
        this.strength+=increase;
    }
    public void decreaseStrength(double decrease) {
        if(this.strength-decrease<=minimumStrength){
            ecossistema.removeElemento(this);
            return;
        }
        this.strength-=decrease;
    }
    public void decreaseStrengthByMovement() {
        decreaseStrength(reduceStrength);
    }
    public boolean checkReproduction() {
        return reproductionCounter<maximumReproduction;
    }
    public void increaseReproductionCounter() {
        reproductionCounter++;
    }
    public void evolve() {
        stateMachine.evolve();
    }

    public void moveRandomly(){
        int direction;
        do{
            direction  = getRandomNumberInRange(360);
            //dar fix em qualquer elemento
        }while((direction<90 &&  ecossistema.hasAnInanimadoOrFauna(new Area(getArea().up(),getArea().down(),getArea().left()+getSpeed(),getArea().right() + getSpeed()),getId()))
                || (direction<180 && ecossistema.hasAnInanimadoOrFauna(new Area(getArea().up() - getSpeed(),getArea().down() - getSpeed(),getArea().left(),getArea().right()),getId()))
                || (direction<270 && ecossistema.hasAnInanimadoOrFauna(new Area(getArea().up(),getArea().down(),getArea().left() - getSpeed(),getArea().right() - getSpeed()),getId()))
                || (direction<360 && ecossistema.hasAnInanimadoOrFauna(new Area(getArea().up() + getSpeed(),getArea().down() + getSpeed(),getArea().left(),getArea().right()),getId())));

        if(direction < 90) {//right
            setArea(new Area(getArea().up(),getArea().down(),getArea().left()+getSpeed(),getArea().right() + getSpeed()));
        }else if(direction < 180) {//up
            setArea(new Area(getArea().up() - getSpeed(),getArea().down() - getSpeed(),getArea().left(),getArea().right()));
        }else if(direction<270) {//left
            setArea(new Area(getArea().up(),getArea().down(),getArea().left() - getSpeed(),getArea().right() - getSpeed()));
        }else if(direction<360) {//down
            setArea(new Area(getArea().up() + getSpeed(),getArea().down() + getSpeed(),getArea().left(),getArea().right()));
        }
        decreaseStrengthByMovement();
    }
    public boolean moveTowardsTheClosestFlora() {
        Area position = ecossistema.getClosestFlora(this);
        if (position == null && getStrength() < 80) {
            return false;
        } else if (position != null) {
            double x = getArea().left()+(getArea().right() - getArea().left()) / 2;
            double y =getArea().up()+ (getArea().down() - getArea().up()) / 2;
            double xClosestFlora =position.left()+ (position.right() - position.left()) / 2;
            double yClosestFlora =position.up()+ (position.down() - position.up()) / 2;
            if (x < xClosestFlora && (xClosestFlora - x) >= (getArea().right() - getArea().left())) {
                if (!ecossistema.hasAnInanimadoOrFauna(new Area(getArea().up(), getArea().down(), getArea().right() + getSpeed(), getArea().left() + getSpeed()),getId())) {
                    setArea(new Area(getArea().up(), getArea().down(), getArea().right() + getSpeed(), getArea().left() + getSpeed()));
                } else {
                    Area aux = null;
                    int direction;
                    do {
                        direction = getRandomNumberInRange(2);
                        if (direction == 0) {
                            aux = new Area(getArea().up() - getSpeed(), getArea().down() - getSpeed(), getArea().right(), getArea().left());
                        } else
                            aux = new Area(getArea().up() + getSpeed(), getArea().down() + getSpeed(), getArea().right(), getArea().left());
                    } while (!ecossistema.hasAnInanimadoOrFauna(aux,getId()));
                    setArea(aux);
                }
            } else if (x > xClosestFlora && (x - xClosestFlora) >= (getArea().right() - getArea().left()) / 2) {
                if (!ecossistema.hasAnInanimadoOrFauna(new Area(getArea().up(), getArea().down(), getArea().right() - getSpeed(), getArea().left() - getSpeed()),getId())){
                    setArea(new Area(getArea().up(), getArea().down(), getArea().right() - getSpeed(), getArea().left() - getSpeed()));
                } else {
                    Area aux = null;
                    int direction;
                    do {
                        direction = getRandomNumberInRange(2);
                        if (direction == 0) {
                            aux = new Area(getArea().up() - getSpeed(), getArea().down() - getSpeed(), getArea().right(), getArea().left());
                        } else
                            aux = new Area(getArea().up() + getSpeed(), getArea().down() + getSpeed(), getArea().right(), getArea().left());
                    } while (!ecossistema.hasAnInanimadoOrFauna(aux,getId()));
                    setArea(aux);
                }
            } else if (y < yClosestFlora && (yClosestFlora - y) >= (getArea().down() - getArea().up()) / 2) {
                if (!ecossistema.hasAnInanimadoOrFauna(new Area(getArea().up() + getSpeed(), getArea().down() + getSpeed(), getArea().right(), getArea().left()),getId())) {
                    setArea(new Area(getArea().up() + getSpeed(), getArea().down() + getSpeed(), getArea().right(), getArea().left()));
                } else {
                    Area aux = null;
                    int direction;
                    do {
                        direction = getRandomNumberInRange(2);
                        if (direction == 0) {
                            aux = new Area(getArea().up(), getArea().down(), getArea().right() - getSpeed(), getArea().left() - getSpeed());
                        } else
                            aux = new Area(getArea().up(), getArea().down(), getArea().right() + getSpeed(), getArea().left() + getSpeed());
                    } while (!ecossistema.hasAnInanimadoOrFauna(aux,getId()));
                    setArea(aux);
                }
            } else if (y > yClosestFlora && (y - yClosestFlora) >= (getArea().down() - getArea().up()) / 2) {
                if (!ecossistema.hasAnInanimadoOrFauna(new Area(getArea().up() - getSpeed(), getArea().down() - getSpeed(), getArea().right(), getArea().left()),getId())) {
                    setArea(new Area(getArea().up() - getSpeed(), getArea().down() - getSpeed(), getArea().right(), getArea().left()));
                } else {
                    Area aux = null;
                    int direction;
                    do {
                        direction = getRandomNumberInRange(2);
                        if (direction == 0) {
                            aux = new Area(getArea().up(), getArea().down(), getArea().right() - getSpeed(), getArea().left() - getSpeed());
                        } else
                            aux = new Area(getArea().up(), getArea().down(), getArea().right() + getSpeed(), getArea().left() + getSpeed());
                    } while (!ecossistema.hasAnInanimadoOrFauna(aux,getId()));
                    setArea(aux);
                }
            }
        }
        decreaseStrengthByMovement();
        return true;
    }
    public boolean onTopOfTheFlora() {
        Flora  flora = ecossistema.hasFloraInThisArea(getArea());
        if(flora != null && getStrength() != getMaximumStrength()){
            flora.decreaseStrength();
            increaseStrength(flora.getDecreaseStrength());
            System.out.println("Flora consumed");
            return true;
        }
        decreaseStrengthByMovement();
        return false;
    }
    public  boolean moveTowardsTheStrongestFauna() {
        Area position = ecossistema.getStrongestFauna(getId());
        if (position == null) {
            decreaseStrengthByMovement();
            return false;
        }
        double x = getArea().left()+(getArea().right() - getArea().left()) / 2;
        double y =getArea().up()+ (getArea().down() - getArea().up()) / 2;
        double xStrongestFauna =position.left()+ (position.right() - position.left()) / 2;
        double yStrongestFauna =position.up()+ (position.down() - position.up()) / 2;
        if (x < xStrongestFauna && (xStrongestFauna-x)>=getSpeed()){
            if(!ecossistema.hasAnElemento(new Area(getArea().up(), getArea().down(), getArea().right() + getSpeed(), getArea().left() + getSpeed()),getId())){
                setArea(new Area(getArea().up(), getArea().down(), getArea().right() + getSpeed(), getArea().left() + getSpeed()));
            }else{
                Area aux=null;
                int direction;
                do{
                    direction = getRandomNumberInRange(2);
                    if(direction==0){
                        aux = new Area(getArea().up() - getSpeed(), getArea().down() - getSpeed(), getArea().right(), getArea().left());
                    }else
                        aux = new Area(getArea().up() + getSpeed(), getArea().down() + getSpeed(), getArea().right(), getArea().left());
                }while(!ecossistema.hasAnElemento(aux,getId()));
                setArea(aux);
            }
        } else if (x > xStrongestFauna && (x-xStrongestFauna)>=getSpeed()){
            if(!ecossistema.hasAnElemento(new Area(getArea().up(), getArea().down(), getArea().right() - getSpeed(), getArea().left() - getSpeed()),getId())){
                setArea(new Area(getArea().up(), getArea().down(), getArea().right() - getSpeed(), getArea().left() - getSpeed()));
            }else{
                Area aux=null;
                int direction;
                do{
                    direction = getRandomNumberInRange(2);
                    if(direction==0){
                        aux = new Area(getArea().up() - getSpeed(), getArea().down() - getSpeed(), getArea().right(), getArea().left());
                    }else
                        aux = new Area(getArea().up() + getSpeed(), getArea().down() + getSpeed(), getArea().right(), getArea().left());
                }while(!ecossistema.hasAnElemento(aux,getId()));
                setArea(aux);
            }
        }else if(y<yStrongestFauna && (yStrongestFauna-y)>=getSpeed()) {
            if(!ecossistema.hasAnElemento(new Area(getArea().up() + getSpeed(), getArea().down() + getSpeed(), getArea().right(), getArea().left()),getId())){
                setArea(new Area(getArea().up() + getSpeed(), getArea().down() + getSpeed(), getArea().right(), getArea().left()));
            }else{
                Area aux=null;
                int direction;
                do{
                    direction = getRandomNumberInRange(2);
                    if(direction==0){
                        aux = new Area(getArea().up() , getArea().down() , getArea().right() - getSpeed(), getArea().left()-getSpeed());
                    }else
                        aux = new Area(getArea().up(), getArea().down(), getArea().right() + getSpeed(), getArea().left()+ getSpeed());
                }while(!ecossistema.hasAnElemento(aux,getId()));
                setArea(aux);
            }
        }else if(y>yStrongestFauna && (y-yStrongestFauna)>=getSpeed() ) {
            if(!ecossistema.hasAnElemento(new Area(getArea().up() - getSpeed(), getArea().down() - getSpeed(), getArea().right(), getArea().left()),getId())){
                setArea(new Area(getArea().up() - getSpeed(), getArea().down() - getSpeed(), getArea().right(), getArea().left()));
            }else{
                Area aux=null;
                int direction;
                do{
                    direction = getRandomNumberInRange(2);
                    if(direction==0){
                        aux = new Area(getArea().up() , getArea().down() , getArea().right()-getSpeed(), getArea().left()-getSpeed());
                    }else
                        aux = new Area(getArea().up(), getArea().down(), getArea().right()+ getSpeed(), getArea().left()+ getSpeed());
                }while(!ecossistema.hasAnElemento(aux,getId()));
                setArea(aux);
            }
        }
        decreaseStrengthByMovement();
        return true;
    }
    public void checkIfAnyFaunaClose() {
        if(ecossistema.hasAFaunaWithinRange(getArea(),maximumReproductionRange))
            increaseReproductionWaitCounter();
    }
    public int getRandomNumberInRange(int maxNumber) {
        Random r = new Random();
        return r.nextInt(maxNumber);
    }
    public boolean attackFauna() {
        if(faunaBeingAttacked == null){
            faunaBeingAttacked = ecossistema.getWeakestFauna(getId());
        }
        if(faunaBeingAttacked == null){
            return true;
        }
        if(ecossistema.isFaunaBeingAttackedNearyby(getArea(),speed,faunaBeingAttacked.getArea())){
            if(strength > attackCost){
                decreaseStrength(attackCost);
                increaseStrength(faunaBeingAttacked.getStrength());
                ecossistema.removeElemento(faunaBeingAttacked);
                faunaBeingAttacked = null;
                return true;
            }
            faunaBeingAttacked.increaseStrength(strength);
            ecossistema.removeElemento(this);
            return true;
        }
        moveTowardsFauna(faunaBeingAttacked.getArea());
        return false;
    }
    public  void moveTowardsFauna(Area position) {
        double x = getArea().left()+(getArea().right() - getArea().left()) / 2;
        double y =getArea().up()+ (getArea().down() - getArea().up()) / 2;
        double xStrongestFauna =position.left()+ (position.right() - position.left()) / 2;
        double yStrongestFauna =position.up()+ (position.down() - position.up()) / 2;
        if (x < xStrongestFauna && (xStrongestFauna-x)>=getSpeed()){
            if(!ecossistema.hasAnElemento(new Area(getArea().up(), getArea().down(), getArea().right() + getSpeed(), getArea().left() + getSpeed()),getId())){
                setArea(new Area(getArea().up(), getArea().down(), getArea().right() + getSpeed(), getArea().left() + getSpeed()));
            }else{
                Area aux=null;
                int direction;
                do{
                    direction = getRandomNumberInRange(2);
                    if(direction==0){
                        aux = new Area(getArea().up() - getSpeed(), getArea().down() - getSpeed(), getArea().right(), getArea().left());
                    }else
                        aux = new Area(getArea().up() + getSpeed(), getArea().down() + getSpeed(), getArea().right(), getArea().left());
                }while(!ecossistema.hasAnElemento(aux,getId()));
                setArea(aux);
            }
        } else if (x > xStrongestFauna && (x-xStrongestFauna)>=getSpeed()){
            if(!ecossistema.hasAnElemento(new Area(getArea().up(), getArea().down(), getArea().right() - getSpeed(), getArea().left() - getSpeed()),getId())){
                setArea(new Area(getArea().up(), getArea().down(), getArea().right() - getSpeed(), getArea().left() - getSpeed()));
            }else{
                Area aux=null;
                int direction;
                do{
                    direction = getRandomNumberInRange(2);
                    if(direction==0){
                        aux = new Area(getArea().up() - getSpeed(), getArea().down() - getSpeed(), getArea().right(), getArea().left());
                    }else
                        aux = new Area(getArea().up() + getSpeed(), getArea().down() + getSpeed(), getArea().right(), getArea().left());
                }while(!ecossistema.hasAnElemento(aux,getId()));
                setArea(aux);
            }
        }else if(y<yStrongestFauna && (yStrongestFauna-y)>=getSpeed()) {
            if(!ecossistema.hasAnElemento(new Area(getArea().up() + getSpeed(), getArea().down() + getSpeed(), getArea().right(), getArea().left()),getId())){
                setArea(new Area(getArea().up() + getSpeed(), getArea().down() + getSpeed(), getArea().right(), getArea().left()));
            }else{
                Area aux=null;
                int direction;
                do{
                    direction = getRandomNumberInRange(2);
                    if(direction==0){
                        aux = new Area(getArea().up() , getArea().down() , getArea().right() - getSpeed(), getArea().left()-getSpeed());
                    }else
                        aux = new Area(getArea().up(), getArea().down(), getArea().right() + getSpeed(), getArea().left()+ getSpeed());
                }while(!ecossistema.hasAnElemento(aux,getId()));
                setArea(aux);
            }
        }else if(y>yStrongestFauna && (y-yStrongestFauna)>=getSpeed() ) {
            if(!ecossistema.hasAnElemento(new Area(getArea().up() - getSpeed(), getArea().down() - getSpeed(), getArea().right(), getArea().left()),getId())){
                setArea(new Area(getArea().up() - getSpeed(), getArea().down() - getSpeed(), getArea().right(), getArea().left()));
            }else{
                Area aux=null;
                int direction;
                do{
                    direction = getRandomNumberInRange(2);
                    if(direction==0){
                        aux = new Area(getArea().up() , getArea().down() , getArea().right()-getSpeed(), getArea().left()-getSpeed());
                    }else
                        aux = new Area(getArea().up(), getArea().down(), getArea().right()+ getSpeed(), getArea().left()+ getSpeed());
                }while(!ecossistema.hasAnElemento(aux,getId()));
                setArea(aux);
            }
        }
    }
    public boolean reproduce() {
        checkIfAnyFaunaClose();
        if(reproductionWaitCounter==reproductionWaitCounterFlag && checkReproduction()){
            Area aux =ecossistema.hasSpaceForNewFauna(getArea());
            if(aux != null){
                IElement newFauna = Element.createElement(Element.FAUNA,aux,ecossistema);
                assert newFauna != null;
                ecossistema.addElemento(newFauna);
                setReproductionWaitCounter(0);
                increaseReproductionCounter();
                return true;
            }
            return true;
        }
        if(!moveTowardsTheStrongestFauna())
            return true;
        return false;
    }

}

