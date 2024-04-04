package pt.isec.pa.javalife.model.data.fsm.states;

import pt.isec.pa.javalife.model.data.Ecossistema;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeAdapter;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeContext;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeState;

public class MovimentarState extends JavaLifeAdapter {
    public MovimentarState(JavaLifeContext context, Ecossistema ecossistema) {
        super(context, ecossistema);
    }

    @Override
    public JavaLifeState getState() {
        return JavaLifeState.MOVIMENTAR;
    }
}
