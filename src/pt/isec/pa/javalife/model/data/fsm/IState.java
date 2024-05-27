package pt.isec.pa.javalife.model.data.fsm;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.Fauna;

public interface IState {

   void evolve(Fauna fauna, Ecossistema ecossistema);

   State getState();
   // meter funções de transição de estados


}
