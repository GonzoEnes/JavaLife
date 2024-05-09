package pt.isec.pa.javalife.model.data.ecosystem;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.elements.Elemento;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.elements.IElemento;
import pt.isec.pa.javalife.model.data.fsm.JavaLifeContext;
import pt.isec.pa.javalife.model.gameengine.interfaces.IGameEngine;

import java.util.ArrayList;
import java.util.Set;

// esta classe vai servir como Facade do Ecossistema para que as outras classes não consigam manipular o Ecossistema diretamente
public class EcossistemaManager {
    private Ecossistema ecossistema;
    private JavaLifeContext context;
    private long timeInMillis;

    // falta aqui depois o CmdManager e o PropertyChangeSupport (para a sinalização dos clientes) quando fizermos a GUI

    public EcossistemaManager(long timeInMillis) throws InterruptedException {
        this.ecossistema = new Ecossistema(50, 50);
        //this.ecossistema.addElemento(new Area(0, 1, 1, 1), Elemento.FAUNA, null);
        //this.ecossistema.addElemento(new Area(300, 300, 70, 400), Elemento.FLORA, null);
        //this.ecossistema.addElemento(new Area(30, 10, 20, 20), Elemento.INANIMADO, null);
        IElemento elemento = new Fauna(new Area(10,10,10,10), Elemento.FAUNA);
        this.ecossistema.addElemento(elemento);
        //IElemento elemento = new Fauna(new Area(10,10,10,10), Elemento.FAUNA, ecossistema);
        //this.ecossistema.addElemento(elemento);
        //this.ecossistema.addElemento(new Area(100, 300, 70, 400), Elemento.FAUNA, null);
        this.timeInMillis = timeInMillis;
    }

    public boolean addElemento(IElemento elemento) throws InterruptedException {
        //elemento = new Fauna(new Area(10,10,10,10), Elemento.FAUNA, ecossistema);
        return this.ecossistema.addElemento(elemento);
    }
    public JavaLifeContext getFsm() {return context;}
    public boolean removeElemento(IElemento elemento) {
        return ecossistema.removeElemento(elemento);
    }

    public boolean editElemento(Elemento tipo, int id, ArrayList<String> parametros) {
        return ecossistema.editElemento(tipo, id, parametros);
    }

    public Set<IElemento> getElementos() {
        return ecossistema.getElementos();
    }

    public void evolve() throws InterruptedException {
        getFsm().evolve();
    }

    public long getTimeInMillis() {
        return timeInMillis;
    }

    public void setTimeInMillis(long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }

    public int getLargura() {
        return ecossistema.getLargura();
    }

    public int getAltura() {
        return ecossistema.getAltura();
    }

    public void evolve (IGameEngine gameEngine, long currentTime) throws InterruptedException {

         getFsm().evolve();
         getFsm().evolve(gameEngine, currentTime);

    }
}