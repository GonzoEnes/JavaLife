package pt.isec.pa.javalife.model.data.fsm.states;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeAdapter;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeContext;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeState;

public class ProcurarComidaState extends JavaLifeAdapter {
    public ProcurarComidaState(Fauna fauna, Ecossistema ecossistema) {
        super(fauna, ecossistema);
    }

    @Override
    public JavaLifeState getState() {
        return JavaLifeState.PROCURAR_COMIDA;
    }

    @Override
    public boolean evolve() {
        return false;
    }
}
