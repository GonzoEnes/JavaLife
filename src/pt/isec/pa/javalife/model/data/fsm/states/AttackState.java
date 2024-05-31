package pt.isec.pa.javalife.model.data.fsm.states;

import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.fsm.Context;
import pt.isec.pa.javalife.model.data.fsm.State;
import pt.isec.pa.javalife.model.data.fsm.StateAdapter;

public class AttackState extends StateAdapter {
    public AttackState(Context context, Fauna fauna) {
        super(context, fauna);
    }
    @Override
    public void evolve() {
        if(fauna.attackFauna()){
            changeState(State.MOVE);
        }
    }
    @Override
    public State getState() {
            return State.ATTACK;
    }
}
