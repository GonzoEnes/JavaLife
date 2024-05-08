package pt.isec.pa.javalife.model.data.fsm.states;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeAdapter;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeContext;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeState;

public class MovimentarState extends JavaLifeAdapter {
    public MovimentarState(Fauna fauna, Ecossistema ecossistema) {
        super(fauna,ecossistema);
    }

    @Override
    public JavaLifeState getState() {
        return JavaLifeState.MOVIMENTAR;
    }

    @Override
    public boolean evolve() {
        return false;
    }
}
