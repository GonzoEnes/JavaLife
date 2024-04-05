package pt.isec.pa.javalife.model.data.fsm;

import pt.isec.pa.javalife.model.data.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.Elemento;
import pt.isec.pa.javalife.model.data.elements.IElemento;
import pt.isec.pa.javalife.model.data.fsm.states.IJavaLifeState;

import java.util.Set;

public class JavaLifeContext {
    private Ecossistema ecossistema;
    private IJavaLifeState state;

    public JavaLifeContext() { // ver isto
        this.ecossistema = new Ecossistema(100, 100);
        this.state = IJavaLifeState.createState(JavaLifeState.MOVIMENTAR, this, ecossistema);
    }

    public JavaLifeState getState() {
        return state.getState();
    }

    void changeState(IJavaLifeState state) {
        this.state = state;
    }

    public Set<IElemento> getElements() {
        return ecossistema.getElementos();
    }
}
