package pt.isec.pa.javalife.model.data.fsm;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.gameengine.interfaces.IGameEngine;

import java.io.Serializable;

public class Context implements Serializable {
    private IState state;
    private Ecossistema ecossistema;

    public Context(Ecossistema ecossistema) {
        this.state = State.createState(State.MORRER, this, ecossistema);
        this.ecossistema = ecossistema;
    }
    void changeState(IState state) {
        this.state = state;
    }

    public State getState() {
        return state.getState();
    }


    public Fauna evolve(Fauna fauna) {
        return state.evolve(fauna,ecossistema);
    }

    public void evolve(IGameEngine gameEngine, long currentTime){

        // a ver ecossistema.evolve;
    }

}
