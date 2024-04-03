package pt.isec.pa.javalife.model.data.fsm;

import pt.isec.pa.javalife.model.data.Ecossistema;
import pt.isec.pa.javalife.model.data.fsm.states.IJavaLifeState;

public abstract class JavaLifeAdapter implements IJavaLifeState {
    private Ecossistema ecossistema;
    private JavaLifeContext context;
    public JavaLifeAdapter(JavaLifeContext context, Ecossistema ecossistema) {
        this.context = context;
        this.ecossistema = ecossistema;
    }

    public void changeState(JavaLifeState state) {
        context.changeState(IJavaLifeState.createState(state, context, ecossistema));
    }
}
