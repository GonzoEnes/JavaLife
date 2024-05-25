package pt.isec.pa.javalife.model.data.fsm.states;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.fsm.StateAdapter;
import pt.isec.pa.javalife.model.data.fsm.Context;
import pt.isec.pa.javalife.model.data.fsm.State;

public class ProcurarComidaState extends StateAdapter {
    public ProcurarComidaState(Context context, Ecossistema ecossistema) {
        super(context, ecossistema);
    }

    @Override
    public State getState() {
        return State.PROCURAR_COMIDA;
    }

    @Override
    public Fauna evolve(Fauna fauna,Ecossistema ecossistema) {

        fauna.setForca(fauna.getForca()+1);
        if (fauna.getForca()==0){
            changeState(State.MORRER);
        }

        if(fauna.getForca()>75){
            changeState((State.MOVIMENTAR));
        }



        return null;
    }
}
