package pt.isec.pa.javalife.model.data.fsm;

public interface IState {
   void evolve();

   State getState();
}
