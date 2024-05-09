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
    public JavaLifeContext(Fauna fauna) {
        this.fauna = fauna;
        this.state = JavaLifeState.createState(JavaLifeState.MOVIMENTAR, this, null);
    }

    public JavaLifeState getState() {
        return state.getState();
    }

    void changeState(IJavaLifeState state) {
        this.state = state;
    }

    public boolean evolve(/*GameEngine gameEngine, long currentTime*/) throws InterruptedException {

       /* if(getState() == JavaLifeState.MOVIMENTAR){
=======
        if(getState() == JavaLifeState.MOVIMENTAR){
>>>>>>> Stashed changes
            state.evolve();
            return true;
        }*/
        state.evolve(fauna);
        return false;
    }
    public Fauna getFauna() {
        return fauna;
    }
}
