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
    public boolean evolve(Fauna fauna) {
        /*sleep(1000);
        changeState(JavaLifeState.MORRER);*/
        /*fauna.setForca(fauna.getForca()-1);
        if (fauna.getForca() == 0){
            changeState(JavaLifeState.MORRER);
        }

        if(fauna.getForca()<35){
            changeState(JavaLifeState.PROCURAR_COMIDA);
        }
        if(fauna.getForca() < 70 && fauna.getForca() > 55){
            changeState(JavaLifeState.REPRODUZIR);
        }*/
        fauna.setX(fauna.getX()+1);
        //System.out.println("OLA ESTADO " + getState());
        return true;
    }
}
