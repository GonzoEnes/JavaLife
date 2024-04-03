package pt.isec.pa.javalife.model.data.fsm.states;

import pt.isec.pa.javalife.model.data.Ecossistema;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeAdapter;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeContext;

public class MovimentarState extends JavaLifeAdapter {
    public MovimentarState(JavaLifeContext context, Ecossistema ecossistema) {
        super(context, ecossistema);
    }
}
