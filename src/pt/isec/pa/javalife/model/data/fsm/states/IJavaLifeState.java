package pt.isec.pa.javalife.model.data.fsm.states;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeContext;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeState;

public interface IJavaLifeState {
   JavaLifeState getState();

   boolean evolve() throws InterruptedException;

   // meter funções de transição de estados


}
