package pt.isec.pa.javalife.model.data.fsm.states;

import pt.isec.pa.javalife.model.data.Ecossistema;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeContext;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeState;

public interface IJavaLifeState {
   JavaLifeState getState();

   // meter funções de transição de estados

    static IJavaLifeState createState(JavaLifeState type, JavaLifeContext context, Ecossistema ecossistema) {
        return switch (type) {
            case PROCURAR_COMIDA -> new ProcurarComidaState(context,ecossistema);
            case MORRER -> new MorrerState(context, ecossistema);
            case REPRODUZIR -> new ReproduzirState(context, ecossistema);
            case MOVIMENTAR -> new MovimentarState(context, ecossistema);
        };
    }
}
