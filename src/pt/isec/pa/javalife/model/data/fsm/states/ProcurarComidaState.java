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
            area = ecossistema.getFaunaMaisProximaComMenorForca(fauna.getArea(),fauna.getId());
            if(area == null){
                //nao exite mais fauna nem flora no ecossitema
                return;
            }
        }
        double pontox= area.direita() - area.esquerda();
        double pontoy= area.baixo() - area.cima();

        //falta saber lidar com as pedras no meio do ecossistema
        if(fauna.getArea().cima() > pontox
                && (fauna.getArea().cima() - pontox)>0.5
                && fauna.getArea().esquerda()>pontoy
                && (fauna.getArea().esquerda()-pontoy)>0.5) {
            fauna.setArea(new Area(fauna.getArea().cima()-1,fauna.getArea().esquerda()-1,fauna.getArea().baixo()-1,fauna.getArea().direita()-1));
            return;
        }else if(fauna.getArea().cima() > pontox
                && (fauna.getArea().cima() - pontox)>0.5
                && fauna.getArea().esquerda()<pontoy
                && (fauna.getArea().esquerda()-pontoy)<0.5) {
            fauna.setArea(new Area(fauna.getArea().cima()-1,fauna.getArea().esquerda()+1,fauna.getArea().baixo()-1,fauna.getArea().direita()+1));
            return;
        }else if(fauna.getArea().cima() < pontox
                && (fauna.getArea().cima() - pontox)>0.5
                && fauna.getArea().esquerda()>pontoy
                && (fauna.getArea().esquerda()-pontoy)>0.5) {
            fauna.setArea(new Area(fauna.getArea().cima()+1,fauna.getArea().esquerda()-1,fauna.getArea().baixo()+1,fauna.getArea().direita()-1));
            return;
        }else if(fauna.getArea().cima() < pontox
                && (fauna.getArea().cima() - pontox)>0.5
                && fauna.getArea().esquerda()<pontoy
                && (fauna.getArea().esquerda()-pontoy)<0.5) {
            fauna.setArea(new Area(fauna.getArea().cima()+1,fauna.getArea().esquerda()+1,fauna.getArea().baixo()+1,fauna.getArea().direita()+1));
            return;
        }else if((fauna.getArea().cima() - pontox)<0.5
                && fauna.getArea().esquerda()>pontoy
                && (fauna.getArea().esquerda()-pontoy)>0.5) {
            fauna.setArea(new Area(fauna.getArea().cima(),fauna.getArea().esquerda()-1,fauna.getArea().baixo(),fauna.getArea().direita()-1));
            return;
        }else if((fauna.getArea().cima() - pontox)<0.5
                && fauna.getArea().esquerda()<pontoy
                && (fauna.getArea().esquerda()-pontoy)<0.5) {
            fauna.setArea(new Area(fauna.getArea().cima(),fauna.getArea().esquerda()+1,fauna.getArea().baixo(),fauna.getArea().direita()+1));
            return;
        }else if((fauna.getArea().esquerda() - pontoy)<0.5
                && fauna.getArea().cima()>pontox
                && (fauna.getArea().cima()-pontox)>0.5) {
            fauna.setArea(new Area(fauna.getArea().cima()-1,fauna.getArea().esquerda(),fauna.getArea().baixo()-1,fauna.getArea().direita()));
            return;
        }else if((fauna.getArea().esquerda() - pontoy)<0.5
                && fauna.getArea().cima()<pontox
                && (fauna.getArea().cima()-pontox)<0.5) {
            fauna.setArea(new Area(fauna.getArea().cima()+1,fauna.getArea().esquerda(),fauna.getArea().baixo()+1,fauna.getArea().direita()));
            return;
        }
    }
}
