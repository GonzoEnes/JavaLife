package pt.isec.pa.javalife.model.data.fsm.states;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.elements.IElemento;
import pt.isec.pa.javalife.model.data.fsm.StateAdapter;
import pt.isec.pa.javalife.model.data.fsm.Context;
import pt.isec.pa.javalife.model.data.fsm.State;

public class ReproduzirState extends StateAdapter {
    public ReproduzirState(Context context, Ecossistema ecossistema) {
        super(context, ecossistema);
    }

    @Override
    public State getState() {
        return State.REPRODUZIR;
    }

    @Override
    public Fauna evolve(Fauna fauna, Ecossistema ecossistema) {
        /*if (fauna.getForca() < 50) {
            changeState(State.MORRER);
            return null;
        }*/
        //if (fauna.getForca() > 60) {
            //fauna.setForca(50);
            Fauna newFauna = new Fauna(fauna.getArea(), ecossistema);
            ecossistema.addElemento(newFauna);
            //changeState(State.MOVIMENTAR);
            return newFauna;
        //}
        //return null;
    }
}

