package pt.isec.pa.javalife.model.data.fsm.states;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.fsm.StateAdapter;
import pt.isec.pa.javalife.model.data.fsm.Context;
import pt.isec.pa.javalife.model.data.fsm.State;

import static java.lang.Thread.sleep;

public class MovimentarState extends StateAdapter {
    public MovimentarState(Context context, Ecossistema ecossistema) {
        super(context,ecossistema);
    }
    @Override
    public State getState() {
        return State.MOVIMENTAR;
    }

    @Override
    public void evolve(Fauna fauna, Ecossistema ecossistema){
        //fauna.perdeForca();
        if(fauna.getForca() <35){
            changeState(State.PROCURAR_COMIDA);
        }
    }
}
