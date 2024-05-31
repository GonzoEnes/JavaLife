package pt.isec.pa.javalife.model.data.fsm.states;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.fsm.StateAdapter;
import pt.isec.pa.javalife.model.data.fsm.Context;
import pt.isec.pa.javalife.model.data.fsm.State;

public class ReproduceState extends StateAdapter {
    public ReproduceState(Context context, Fauna fauna) {
        super(context, fauna);
    }

    @Override
    public State getState() {
        return State.REPRODUCE;
    }

    @Override
    public void evolve() {
        if(fauna.reproduce()){
            changeState(State.MOVE);
        }
    }
}

