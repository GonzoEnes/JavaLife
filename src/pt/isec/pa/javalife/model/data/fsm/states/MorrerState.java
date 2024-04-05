package pt.isec.pa.javalife.model.data.fsm.states;

import pt.isec.pa.javalife.model.data.Ecossistema;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeAdapter;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeContext;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeState;

public class MorrerState extends JavaLifeAdapter {
    public MorrerState(JavaLifeContext context, Ecossistema ecossistema) {
        super(context, ecossistema);
    }

    @Override
    public JavaLifeState getState() {
        return JavaLifeState.MORRER;
    }

    @Override
    public boolean evolve() {
        return false;
    }
}
