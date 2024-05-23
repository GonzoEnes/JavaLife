package pt.isec.pa.javalife.model.data.fsm.states;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.fsm.StateAdapter;
import pt.isec.pa.javalife.model.data.fsm.Context;
import pt.isec.pa.javalife.model.data.fsm.State;

import static java.lang.Thread.sleep;

public class MovimentarState extends StateAdapter {
    public MovimentarState(Context context, Ecossistema ecossistema) {
        super(context,ecossistema);
    }
    @Override
    public State getState() {
        return State.MOVIMENTAR;
    }

    @Override
    public Fauna evolve(Fauna fauna,Ecossistema ecossistema){
        fauna.move();
        if(fauna.getForca()==0){
            changeState(State.MORRER);
        }
        if(fauna.getForca()<35){
            changeState(State.PROCURAR_COMIDA);
        }
        if(fauna.getForca()>50){
            //fazer o moviumento normal Quando a sua força está acima de 50 e não está em procura de comida, dirigir-se-á no
            //sentido do elemento fauna com mais força (notar que o elemento mais forte poderá
            //mudar e, nesse caso, o sentido seguido também mudará);
        }
        return null;
    }
}
