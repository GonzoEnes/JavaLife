package pt.isec.pa.javalife.model.data.fsm;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.fsm.states.*;

public enum JavaLifeState {
    // EM PRINCIPIO, OS ESTADOS SERÃO ESTES
    // ABAIXO MAS POSSO ESTAR ENGANADO

    PROCURAR_COMIDA, MOVIMENTAR, REPRODUZIR, MORRER;

    static IJavaLifeState createState(JavaLifeState type, Fauna fauna, Ecossistema ecossistema) { // factory de objetos
        return switch (type) {
            case PROCURAR_COMIDA -> new ProcurarComidaState(fauna,ecossistema);
            case MORRER -> new MorrerState(fauna, ecossistema);
            case REPRODUZIR -> new ReproduzirState(fauna, ecossistema);
            case MOVIMENTAR -> new MovimentarState(fauna, ecossistema);
        };
    }
}
