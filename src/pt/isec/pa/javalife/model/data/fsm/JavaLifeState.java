package pt.isec.pa.javalife.model.data.fsm;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.fsm.states.*;

public enum JavaLifeState {
    // EM PRINCIPIO, OS ESTADOS SERÃO ESTES
    // ABAIXO MAS POSSO ESTAR ENGANADO

    PROCURAR_COMIDA, MOVIMENTAR, REPRODUZIR, MORRER;

    static IJavaLifeState createState(JavaLifeState type, JavaLifeContext context, Ecossistema ecossistema) { // factory de objetos
        return switch (type) {
            case PROCURAR_COMIDA -> new ProcurarComidaState(context,ecossistema);
            case MORRER -> new MorrerState(context, ecossistema);
            case REPRODUZIR -> new ReproduzirState(context, ecossistema);
            case MOVIMENTAR -> new MovimentarState(context, ecossistema);
        };
    }
}
