package pt.isec.pa.javalife.model.data.fsm;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.IElemento;
import pt.isec.pa.javalife.model.data.fsm.states.IJavaLifeState;

import java.util.Set;

public class JavaLifeContext {
    private Ecossistema ecossistema;
  //  private IJavaLifeState state;

    public JavaLifeContext(Ecossistema ecossistema) { // ver isto
        this.ecossistema = ecossistema;
       // this.state = JavaLifeState.createState(JavaLifeState.MOVIMENTAR, this, ecossistema);
    }

    //TUDO COISO CORRIGIR
  //  public JavaLifeState getState() {
      //  return state.getState();
   // }

   // void changeState(IJavaLifeState state) {
       // this.state = state;
   // }

    public Set<IElemento> getElements() {
        return ecossistema.getElementos();
    }
}
