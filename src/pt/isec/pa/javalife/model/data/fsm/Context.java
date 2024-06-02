package pt.isec.pa.javalife.model.data.fsm;

import pt.isec.pa.javalife.model.data.elements.Fauna;

import java.io.Serializable;

public class Context implements Serializable {
    private IState state;
    private Fauna fauna;

    public Context(Fauna fauna) {
        this.fauna = fauna;
        this.state = State.createState(State.MOVE,this,fauna);
    }
    void changeState(IState state) {
        this.state = state;
    }
    public State getState() {
        return state.getState();
    }
    public void evolve() {
        state.evolve();
    }
}
