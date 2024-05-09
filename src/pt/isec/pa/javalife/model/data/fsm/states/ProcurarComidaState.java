package pt.isec.pa.javalife.model.data.fsm.states;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeAdapter;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeContext;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeState;

public class ProcurarComidaState extends JavaLifeAdapter {
    public ProcurarComidaState(JavaLifeContext context, Ecossistema ecossistema) {
        super(context, ecossistema);
    }

    @Override
    public JavaLifeState getState() {
        return JavaLifeState.PROCURAR_COMIDA;
    }

    @Override
    public boolean evolve(Fauna fauna) {

        fauna.setForca(fauna.getForca()+1);
        if (fauna.getForca()==0){
            changeState(JavaLifeState.MORRER);
        }

        if(fauna.getForca()>75){
            changeState((JavaLifeState.MOVIMENTAR));
        }



        return false;
    }
}
