package pt.isec.pa.javalife.model.data.fsm.states;

import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.fsm.StateAdapter;
import pt.isec.pa.javalife.model.data.fsm.Context;
import pt.isec.pa.javalife.model.data.fsm.State;

public class LookingForFoodState extends StateAdapter {
    public LookingForFoodState(Context context, Fauna fauna) {
        super(context, fauna);
    }

    @Override
    public State getState() {
        return State.LOOKINGFORFOOD;
    }

    @Override
    public void evolve() {
        if (!fauna.onTopOfTheFlora())
            if (fauna.getStrength() < fauna.getStrengthToChangeStateForMovimentar() && !fauna.moveTowardsTheClosestFlora())
                changeState(State.ATTACK);
        if (fauna.getStrength() >= fauna.getStrengthToChangeStateForMovimentar())
            changeState(State.MOVE);
    }
}
