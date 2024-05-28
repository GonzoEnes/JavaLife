package pt.isec.pa.javalife.model.data.fsm;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.fsm.states.*;

public enum State {
    // EM PRINCIPIO, OS ESTADOS SERÃO ESTES
    // ABAIXO MAS POSSO ESTAR ENGANADO

    PROCURAR_COMIDA, MOVIMENTAR, REPRODUZIR, ATAQUE;

    public static IState createState(State type, Context context, Ecossistema ecossistema) { // factory de objetos
        return switch (type) {
            case PROCURAR_COMIDA -> new ProcurarComidaState(context,ecossistema);
            case ATAQUE -> new Ataque(context,ecossistema);
            case REPRODUZIR -> new ReproduzirState(context,ecossistema);
            case MOVIMENTAR -> new MovimentarState(context,ecossistema);
        };
    }
}
