package pt.isec.pa.javalife.model.data.fsm.states;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.fsm.StateAdapter;
import pt.isec.pa.javalife.model.data.fsm.Context;
import pt.isec.pa.javalife.model.data.fsm.State;

import java.util.Random;

public class ProcurarComidaState extends StateAdapter {
    public ProcurarComidaState(Context context, Ecossistema ecossistema) {
        super(context, ecossistema);
    }

    @Override
    public State getState() {
        return State.PROCURAR_COMIDA;
    }

    public boolean getRandomBoolean() {
        Random r = new Random();
        return r.nextBoolean();
    }
    @Override
    public void evolve(Fauna fauna, Ecossistema ecossistema) {

        //se esta em cima de uma flora consume a flora
        Area area = ecossistema.getFloraMaisProxima(fauna.getArea());
        if (area == null) {
            System.out.println("Nao existe flora no ecossistema");
            area = ecossistema.getFaunaMaisProximaComMenorForca(fauna.getArea(),fauna.getId());
            //consumirFauna
            if(area == null){
                //nao exite mais fauna nem flora no ecossitema
                return;
            }
        }
        double pontox= (area.direita() + area.esquerda())/2;
        double pontoy= (area.baixo() + area.cima())/2;

        double pontoXFauna = (fauna.getArea().direita() + fauna.getArea().esquerda())/2;
        double pontoYFauna = (fauna.getArea().baixo() + fauna.getArea().cima())/2;
        if(((pontoXFauna>pontox && pontoXFauna-pontox<(area.direita() - area.esquerda())/2) && (pontoYFauna>pontoy && pontoYFauna-pontoy<(area.baixo() - area.cima())/2)) ||
                ((pontoXFauna<pontox && pontox-pontoXFauna<(area.direita() - area.esquerda())/2) && (pontoYFauna<pontoy && pontoy-pontoYFauna<(area.baixo() - area.cima())/2)) ||
                ((pontoXFauna<pontox && pontox-pontoXFauna<(area.direita() - area.esquerda())/2) && (pontoYFauna>pontoy && pontoYFauna-pontoy<(area.baixo() - area.cima())/2)) ||
                ((pontoXFauna>pontox && pontoXFauna-pontox<(area.direita() - area.esquerda())/2) && (pontoYFauna<pontoy && pontoy-pontoYFauna<(area.baixo() - area.cima())/2))){
            //cosumirFlora
            return;
        }

        if(pontoXFauna > pontox  && pontoYFauna > pontoy){
            fauna.setArea(new Area(fauna.getArea().cima()-fauna.getVelocidade(),
                    fauna.getArea().esquerda()-fauna.getVelocidade(),
                    fauna.getArea().baixo()-fauna.getVelocidade(),
                    fauna.getArea().direita()-fauna.getVelocidade()));
        }else if(pontoXFauna > pontox  && pontoYFauna < pontoy){
            fauna.setArea(new Area(fauna.getArea().cima()+fauna.getVelocidade(),
                    fauna.getArea().esquerda()-fauna.getVelocidade(),
                    fauna.getArea().baixo()+fauna.getVelocidade(),
                    fauna.getArea().direita()-fauna.getVelocidade()));
        }else if(pontoXFauna < pontox  && pontoYFauna > pontoy){
            fauna.setArea(new Area(fauna.getArea().cima()-fauna.getVelocidade(),
                    fauna.getArea().esquerda()+fauna.getVelocidade(),
                    fauna.getArea().baixo()-fauna.getVelocidade(),
                    fauna.getArea().direita()+fauna.getVelocidade()));
        }else if(pontoXFauna < pontox  && pontoYFauna < pontoy){
            fauna.setArea(new Area(fauna.getArea().cima()+fauna.getVelocidade(),
                    fauna.getArea().esquerda()+fauna.getVelocidade(),
                    fauna.getArea().baixo()+fauna.getVelocidade(),
                    fauna.getArea().direita()+fauna.getVelocidade()));
        }else if(pontoXFauna-pontox<2 && pontoYFauna<pontoy){
            fauna.setArea(new Area(fauna.getArea().cima()+fauna.getVelocidade(),
                    fauna.getArea().esquerda(),
                    fauna.getArea().baixo()+fauna.getVelocidade(),
                    fauna.getArea().direita()));
        }else if(pontoXFauna-pontox<2 && pontoYFauna>pontoy){
            fauna.setArea(new Area(fauna.getArea().cima()-fauna.getVelocidade(),
                    fauna.getArea().esquerda(),
                    fauna.getArea().baixo()-fauna.getVelocidade(),
                    fauna.getArea().direita()));
        }else if(pontoXFauna<pontox && pontoYFauna-pontoy<2){
            fauna.setArea(new Area(fauna.getArea().cima(),
                    fauna.getArea().esquerda()+fauna.getVelocidade(),
                    fauna.getArea().baixo(),
                    fauna.getArea().direita()+fauna.getVelocidade()));
        }else if(pontoXFauna>pontox && pontoYFauna-pontoy<2){
            fauna.setArea(new Area(fauna.getArea().cima(),
                    fauna.getArea().esquerda()-fauna.getVelocidade(),
                    fauna.getArea().baixo(),
                    fauna.getArea().direita()-fauna.getVelocidade()));
        }
    }
}
