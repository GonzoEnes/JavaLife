package pt.isec.pa.javalife.model.data.fsm;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.fsm.states.*;

public enum State {
    // EM PRINCIPIO, OS ESTADOS SERÃO ESTES
    // ABAIXO MAS POSSO ESTAR ENGANADO

    PROCURAR_COMIDA, MOVIMENTAR, REPRODUZIR, MORRER;

    static IState createState(State type, Context context,Ecossistema ecossistema) { // factory de objetos
        return switch (type) {
            case PROCURAR_COMIDA -> new ProcurarComidaState(context,ecossistema);
            case MORRER -> new MorrerState(context,ecossistema);
            case REPRODUZIR -> new ReproduzirState(context,ecossistema);
            case MOVIMENTAR -> new MovimentarState(context,ecossistema);
        };
    }
}