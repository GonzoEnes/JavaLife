package pt.isec.pa.javalife.model.data.fsm;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.fsm.states.IJavaLifeState;

public abstract class JavaLifeAdapter implements IJavaLifeState {
    private Ecossistema ecossistema;
    private Fauna fauna;
    public JavaLifeAdapter(Fauna fauna, Ecossistema ecossistema) {
        this.fauna = fauna;
        this.ecossistema= ecossistema;
    }

    public void changeState(JavaLifeState state) {
        fauna.setFaunaState(JavaLifeState.createState(state, fauna, ecossistema));
    }

    @Override
    public abstract boolean evolve();
}
