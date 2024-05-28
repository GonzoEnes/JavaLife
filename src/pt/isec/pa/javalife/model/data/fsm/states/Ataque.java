package pt.isec.pa.javalife.model.data.fsm.states;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.fsm.StateAdapter;
import pt.isec.pa.javalife.model.data.fsm.Context;
import pt.isec.pa.javalife.model.data.fsm.State;

public class Ataque extends StateAdapter {
    public Ataque(Context context, Ecossistema ecossistema) {
        super(context, ecossistema);
    }

    @Override
    public State getState() {
        return State.ATAQUE;
    }

    @Override
    public void evolve(Fauna fauna, Ecossistema ecossistema) {

    }
}
