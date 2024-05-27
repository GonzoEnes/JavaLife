package pt.isec.pa.javalife.model.data.fsm.states;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.fsm.StateAdapter;
import pt.isec.pa.javalife.model.data.fsm.Context;
import pt.isec.pa.javalife.model.data.fsm.State;

import java.util.Random;

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
    public void evolve(Fauna fauna, Ecossistema ecossistema){
        int aux = getRandomNumberInRange(360);
        if(aux < 91) {
            if(fauna.getArea().direita() + fauna.getVelocidade()>=ecossistema.getLargura()){
                fauna.setArea(new Area(fauna.getArea().cima(),
                        ecossistema.getLargura()-1,
                        fauna.getArea().baixo(),
                        ecossistema.getLargura()-1-(fauna.getArea().esquerda()-fauna.getArea().direita())));
                fauna.perderForca();
                if(fauna.getForca()<=35)
                    changeState(State.PROCURAR_COMIDA);
                return;
            }
            fauna.setArea(new Area(fauna.getArea().cima(),
                    fauna.getArea().esquerda() + fauna.getVelocidade(),
                    fauna.getArea().baixo(),
                    fauna.getArea().direita() + fauna.getVelocidade()));
            fauna.perderForca();
            if(fauna.getForca()<=35)
                changeState(State.PROCURAR_COMIDA);
        }else if(aux < 181) {
            if(fauna.getArea().cima() - fauna.getVelocidade()<0){
                fauna.setArea(new Area(0,
                        fauna.getArea().esquerda(),
                        fauna.getArea().baixo()-fauna.getArea().cima()-1,
                        fauna.getArea().direita()));
                fauna.perderForca();
                if(fauna.getForca()<=35)
                    changeState(State.PROCURAR_COMIDA);
                return;
            }
            fauna.setArea(new Area(fauna.getArea().cima() - fauna.getVelocidade(),
                    fauna.getArea().esquerda(),
                    fauna.getArea().baixo() - fauna.getVelocidade(),
                    fauna.getArea().direita()));
            fauna.perderForca();
            if(fauna.getForca()<=35)
                changeState(State.PROCURAR_COMIDA);
        }else if(aux < 271) {
            if(fauna.getArea().esquerda() - fauna.getVelocidade()<0){
                fauna.setArea(new Area(fauna.getArea().cima(),
                        0,
                        fauna.getArea().baixo(),
                        fauna.getArea().direita()-fauna.getArea().esquerda()-1));
                fauna.perderForca();
                if(fauna.getForca()<=35)
                    changeState(State.PROCURAR_COMIDA);
                return;
            }
            fauna.setArea(new Area(fauna.getArea().cima(),
                    fauna.getArea().esquerda() - fauna.getVelocidade(),
                    fauna.getArea().baixo(),
                    fauna.getArea().direita() - fauna.getVelocidade()));
            fauna.perderForca();
            if(fauna.getForca()<=35)
                changeState(State.PROCURAR_COMIDA);
        }else if(aux < 361) {
            if(fauna.getArea().baixo() + fauna.getVelocidade()>=ecossistema.getAltura()){
                fauna.setArea(new Area(ecossistema.getAltura()-1-(fauna.getArea().baixo()-fauna.getArea().cima()),
                        fauna.getArea().esquerda(),
                        ecossistema.getAltura()-1,
                        fauna.getArea().direita()));
                fauna.perderForca();
                if(fauna.getForca()<=35)
                    changeState(State.PROCURAR_COMIDA);
                return;
            }
            fauna.setArea(new Area(fauna.getArea().cima() + fauna.getVelocidade(),
                    fauna.getArea().esquerda(),
                    fauna.getArea().baixo() + fauna.getVelocidade(),
                    fauna.getArea().direita()));
            fauna.perderForca();
            if(fauna.getForca()<=35)
                changeState(State.PROCURAR_COMIDA);
        }
    }

    public int getRandomNumberInRange(int maxNumber) {
        Random r = new Random();
        return r.nextInt(maxNumber);
    }
}
