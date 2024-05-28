package pt.isec.pa.javalife.model.data.fsm.states;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.fsm.StateAdapter;
import pt.isec.pa.javalife.model.data.fsm.Context;
import pt.isec.pa.javalife.model.data.fsm.State;

import java.util.Random;

import static java.lang.Thread.sleep;

public class MovimentarState extends StateAdapter {
    public MovimentarState(Context context, Ecossistema ecossistema) {
        super(context,ecossistema);
    }
    @Override
    public State getState() {
        return State.MOVIMENTAR;
    }

    public void moveRandomly(Fauna fauna, Ecossistema ecossistema){
        int direction;
        do{
            direction  = getRandomNumberInRange(360);
        }while((direction<90 &&  ecossistema.hasAnInanimadoOrFauna(new Area(fauna.getArea().up(),fauna.getArea().down(),fauna.getArea().right()+fauna.getSpeed(),fauna.getArea().left() + fauna.getSpeed())))
                || (direction<180 && ecossistema.hasAnInanimadoOrFauna(new Area(fauna.getArea().up() - fauna.getSpeed(),fauna.getArea().down() - fauna.getSpeed(),fauna.getArea().right(),fauna.getArea().left())))
                || (direction<270 && ecossistema.hasAnInanimadoOrFauna(new Area(fauna.getArea().up(),fauna.getArea().down(),fauna.getArea().right() - fauna.getSpeed(),fauna.getArea().left() - fauna.getSpeed())))
                || (direction<360 && ecossistema.hasAnInanimadoOrFauna(new Area(fauna.getArea().up() + fauna.getSpeed(),fauna.getArea().down() + fauna.getSpeed(),fauna.getArea().right(),fauna.getArea().left()))));

        if(direction < 90) {//right
            fauna.setArea(new Area(fauna.getArea().up(),fauna.getArea().down(),fauna.getArea().right()+fauna.getSpeed(),fauna.getArea().left() + fauna.getSpeed()));
            fauna.decreaseStrengthByMovement();
        }else if(direction < 180) {
            fauna.setArea(new Area(fauna.getArea().up() - fauna.getSpeed(),fauna.getArea().down() - fauna.getSpeed(),fauna.getArea().right(),fauna.getArea().left()));
            fauna.decreaseStrengthByMovement();
        }else if(direction<270) {
            fauna.setArea(new Area(fauna.getArea().up(),fauna.getArea().down(),fauna.getArea().right() - fauna.getSpeed(),fauna.getArea().left() - fauna.getSpeed()));
            fauna.decreaseStrengthByMovement();
        }else if(direction<360) {
            fauna.setArea(new Area(fauna.getArea().up() + fauna.getSpeed(),fauna.getArea().down() + fauna.getSpeed(),fauna.getArea().right(),fauna.getArea().left()));
            fauna.decreaseStrengthByMovement();
        }
        if(fauna.getStrength()<fauna.getStrengthToChangeStateForProcurarComida())
            changeState(State.PROCURAR_COMIDA);
    }

    @Override
    public void evolve(Fauna fauna, Ecossistema ecossistema){
        if(fauna.getStrength()>=fauna.getStrengthToChangeStateForReproduzir()){
            changeState(State.REPRODUZIR);
        }
        moveRandomly(fauna,ecossistema);
    }

    public int getRandomNumberInRange(int maxNumber) {
        Random r = new Random();
        return r.nextInt(maxNumber);
    }
}
