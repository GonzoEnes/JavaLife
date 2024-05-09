package pt.isec.pa.javalife.model.data.fsm.states;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeAdapter;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeContext;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeState;

import static java.lang.Thread.sleep;

public class MovimentarState extends JavaLifeAdapter {
    public MovimentarState(JavaLifeContext context, Ecossistema ecossistema) {
        super(context,ecossistema);
    }
    @Override
    public JavaLifeState getState() {
        return JavaLifeState.MOVIMENTAR;
    }
    @Override
    public boolean evolve() throws InterruptedException {
        sleep(1000);
        changeState(JavaLifeState.MORRER);
        //System.out.println("OLA ESTADO " + getState());
        return true;
    }
}
