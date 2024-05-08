package pt.isec.pa.javalife.model.data.fsm;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.elements.IElemento;
import pt.isec.pa.javalife.model.data.fsm.states.IJavaLifeState;
import pt.isec.pa.javalife.model.data.fsm.states.MovimentarState;

import java.util.Set;

public class JavaLifeContext {
    private Fauna fauna;
    private IJavaLifeState state;
    public JavaLifeContext(Fauna fauna) { // ver isto
        this.fauna = fauna;
        this.state= new MovimentarState(this,null);
       // this.state = JavaLifeState.createState(JavaLifeState.MOVIMENTAR, this, ecossistema);
    }

    //TUDO COISO CORRIGIR
    public JavaLifeState getState() {
        return state.getState();}

    void changeState(IJavaLifeState state) {
        this.state = state;
    }

    public boolean evolve(/*GameEngine gameEngine, long currentTime*/) throws InterruptedException {

        if(getState()== JavaLifeState.MOVIMENTAR){

            state.evolve();
            return true;
        }
        return false;
    }
    public Fauna getFauna() {
        return fauna;
    }


}
