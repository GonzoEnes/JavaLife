package pt.isec.pa.javalife.model.data.fsm.states;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.fsm.StateAdapter;
import pt.isec.pa.javalife.model.data.fsm.Context;
import pt.isec.pa.javalife.model.data.fsm.State;

import java.util.Random;

public class ReproduzirState extends StateAdapter {
    public ReproduzirState(Context context, Ecossistema ecossistema) {
        super(context, ecossistema);
    }

    @Override
    public State getState() {
        return State.REPRODUZIR;
    }

    public int getRandomNumberInRange(int maxNumber) {
        Random r = new Random();
        return r.nextInt(maxNumber);
    }

    private void moveTowardsTheStrongestFauna(Fauna fauna, Ecossistema ecossistema) {
        Area position = ecossistema.getStrongestFauna(fauna.getId());
        if (position == null) {
           fauna.decreaseStrengthByMovement();
           return;
        }
        double x = (fauna.getArea().right()-fauna.getArea().left())/2;
        double y = (fauna.getArea().down()-fauna.getArea().up())/2;
        double xStrongestFauna = (position.right()-position.left())/2;
        double yStrongestFauna = (position.down()-position.up())/2;
        if (x < xStrongestFauna && (xStrongestFauna-x)>=1){
            if(!ecossistema.hasAnInanimadoOrFauna(new Area(fauna.getArea().up(), fauna.getArea().down(), fauna.getArea().right() + fauna.getSpeed(), fauna.getArea().left() + fauna.getSpeed()))){
                fauna.setArea(new Area(fauna.getArea().up(), fauna.getArea().down(), fauna.getArea().right() + fauna.getSpeed(), fauna.getArea().left() + fauna.getSpeed()));
            }else{
                Area aux=null;
                int direction;
                do{
                    direction = getRandomNumberInRange(2);
                    if(direction==0){
                        aux = new Area(fauna.getArea().up() - fauna.getSpeed(), fauna.getArea().down() - fauna.getSpeed(), fauna.getArea().right(), fauna.getArea().left());
                    }else
                        aux = new Area(fauna.getArea().up() + fauna.getSpeed(), fauna.getArea().down() + fauna.getSpeed(), fauna.getArea().right(), fauna.getArea().left());
                }while(!ecossistema.hasAnInanimadoOrFauna(aux));
                fauna.setArea(aux);
            }
        } else if (x > xStrongestFauna && (x-xStrongestFauna)>=1){
            if(!ecossistema.hasAnInanimadoOrFauna(new Area(fauna.getArea().up(), fauna.getArea().down(), fauna.getArea().right() - fauna.getSpeed(), fauna.getArea().left() - fauna.getSpeed()))){
                fauna.setArea(new Area(fauna.getArea().up(), fauna.getArea().down(), fauna.getArea().right() - fauna.getSpeed(), fauna.getArea().left() - fauna.getSpeed()));
            }else{
                Area aux=null;
                int direction;
                do{
                    direction = getRandomNumberInRange(2);
                    if(direction==0){
                        aux = new Area(fauna.getArea().up() - fauna.getSpeed(), fauna.getArea().down() - fauna.getSpeed(), fauna.getArea().right(), fauna.getArea().left());
                    }else
                        aux = new Area(fauna.getArea().up() + fauna.getSpeed(), fauna.getArea().down() + fauna.getSpeed(), fauna.getArea().right(), fauna.getArea().left());
                }while(!ecossistema.hasAnInanimadoOrFauna(aux));
                fauna.setArea(aux);
            }
        }else if(y<yStrongestFauna && (yStrongestFauna-y)>=1) {
            if(!ecossistema.hasAnInanimadoOrFauna(new Area(fauna.getArea().up() + fauna.getSpeed(), fauna.getArea().down() + fauna.getSpeed(), fauna.getArea().right(), fauna.getArea().left()))){
                fauna.setArea(new Area(fauna.getArea().up() + fauna.getSpeed(), fauna.getArea().down() + fauna.getSpeed(), fauna.getArea().right(), fauna.getArea().left()));
            }else{
                Area aux=null;
                int direction;
                do{
                    direction = getRandomNumberInRange(2);
                    if(direction==0){
                        aux = new Area(fauna.getArea().up() , fauna.getArea().down() , fauna.getArea().right()-fauna.getSpeed(), fauna.getArea().left()-fauna.getSpeed());
                    }else
                        aux = new Area(fauna.getArea().up(), fauna.getArea().down(), fauna.getArea().right()+ fauna.getSpeed(), fauna.getArea().left()+ fauna.getSpeed());
                }while(!ecossistema.hasAnInanimadoOrFauna(aux));
                fauna.setArea(aux);
            }
        }else if(y>yStrongestFauna && (y-yStrongestFauna)>=1 ) {
            if(!ecossistema.hasAnInanimadoOrFauna(new Area(fauna.getArea().up() - fauna.getSpeed(), fauna.getArea().down() - fauna.getSpeed(), fauna.getArea().right(), fauna.getArea().left()))){
                fauna.setArea(new Area(fauna.getArea().up() - fauna.getSpeed(), fauna.getArea().down() - fauna.getSpeed(), fauna.getArea().right(), fauna.getArea().left()));            }else{
                Area aux=null;
                int direction;
                do{
                    direction = getRandomNumberInRange(2);
                    if(direction==0){
                        aux = new Area(fauna.getArea().up() , fauna.getArea().down() , fauna.getArea().right()-fauna.getSpeed(), fauna.getArea().left()-fauna.getSpeed());
                    }else
                        aux = new Area(fauna.getArea().up(), fauna.getArea().down(), fauna.getArea().right()+ fauna.getSpeed(), fauna.getArea().left()+ fauna.getSpeed());
                }while(!ecossistema.hasAnInanimadoOrFauna(aux));
                fauna.setArea(aux);
            }
        }
    }

    private void checkIfAnyFaunaClose(Fauna fauna, Ecossistema ecossistema) {
        if(ecossistema.hasAFaunaWithinRange(fauna.getId(),fauna.getMaximumReproductionRange())){
            fauna.increaseReproductionWaitCounter();
        }
        fauna.setReproductionWaitCounter(0);
    }

    private void checkIfReproductionIsPossible(Fauna fauna, Ecossistema ecossistema) {
        if(fauna.getReproductionWaitCounter()==fauna.getReproductionWaitCounterFlag() && fauna.checkReproduction()){
            Area aux =ecossistema.hasSpaceForNewFauna(fauna.getArea());
            if(aux != null){
                Fauna newFauna = new Fauna(aux,ecossistema);
                ecossistema.addElemento(newFauna);
                fauna.setReproductionWaitCounter(0);
                fauna.increaseReproductionCounter();
            }
        }
    }

    @Override
    public void evolve(Fauna fauna, Ecossistema ecossistema) {
        moveTowardsTheStrongestFauna(fauna, ecossistema);
        checkIfAnyFaunaClose(fauna, ecossistema);
        checkIfReproductionIsPossible(fauna,ecossistema);
        if(fauna.getStrength() < fauna.getStrengthToChangeStateForReproduzir())
            changeState(State.MOVIMENTAR);
    }

}

