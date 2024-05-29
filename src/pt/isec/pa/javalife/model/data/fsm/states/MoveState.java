package pt.isec.pa.javalife.model.data.fsm.states;

import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.fsm.StateAdapter;
import pt.isec.pa.javalife.model.data.fsm.Context;
import pt.isec.pa.javalife.model.data.fsm.State;

import static java.lang.Thread.sleep;

public class MoveState extends StateAdapter {
    public MoveState(Context context, Fauna fauna) {
        super(context,fauna);
    }
    @Override
    public State getState() {
        return State.MOVE;
    }

    @Override
    public void evolve(){
        fauna.moveRandomly();
        if(fauna.getStrength() < fauna.getStrengthToChangeStateForProcurarComida())
            changeState(State.LOOKINGFORFOOD);
        if(fauna.getStrength() > fauna.getStrengthToChangeStateForReproduzir())
            changeState(State.REPRODUCE);
    }

}
