package pt.isec.pa.javalife.model.data.fsm;

import pt.isec.pa.javalife.model.data.elements.Fauna;

import java.io.Serializable;

public abstract class StateAdapter implements IState, Serializable {
    protected Context context;
    protected Fauna fauna;

    public StateAdapter(Context context, Fauna fauna) {
        this.context = context;
        this.fauna = fauna;
    }

    public void changeState(State state) {
        context.changeState(State.createState(state, context,fauna));
    }
    @Override
    public void evolve() {
    }
}
