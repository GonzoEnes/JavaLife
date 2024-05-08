package pt.isec.pa.javalife.model.data.fsm.states;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeAdapter;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeContext;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeState;

public class ReproduzirState extends JavaLifeAdapter {
    public ReproduzirState(JavaLifeContext context, Ecossistema ecossistema) {
        super(context, ecossistema);
    }

    @Override
    public JavaLifeState getState() {
        return JavaLifeState.REPRODUZIR;
    }

    @Override
    public boolean evolve() {
        return false;
    }
}
