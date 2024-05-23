package pt.isec.pa.javalife.model.data.fsm;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.Fauna;

public abstract class StateAdapter implements IState {
    private Context context;
    private Ecossistema ecossistema;

    public StateAdapter(Context context, Ecossistema ecossistema) {
        this.context = context;
        this.ecossistema= ecossistema;
    }

    public void changeState(State state) {
        context.changeState(State.createState(state, context,ecossistema));
    }
}