package pt.isec.pa.javalife.model.data.ecosystem;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.elements.Elemento;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.elements.IElemento;
import pt.isec.pa.javalife.model.data.fsm.Context;
import pt.isec.pa.javalife.model.gameengine.interfaces.IGameEngine;

import java.util.ArrayList;
import java.util.Set;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
// esta classe vai servir como Facade do Ecossistema para que as outras classes não consigam manipular o Ecossistema diretamente
public class EcossistemaManager {
    private Ecossistema ecossistema;
    public static final String EVOLVE = "_evolve";
    PropertyChangeSupport pcs;

    public EcossistemaManager(){
        pcs = new PropertyChangeSupport(this);
        this.ecossistema = new Ecossistema(50, 50);
    }

    // observer/observable managenment
    public void addClient(String property, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(property, listener);
    }

    public void removeClient(String property, PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(property, listener);
    }

    public boolean addElemento(IElemento elemento){
        return this.ecossistema.addElemento(elemento);
    }

    public boolean removeElemento(IElemento elemento) {
        return ecossistema.removeElemento(elemento);
    }

    public boolean editElemento(Elemento tipo, int id, ArrayList<String> parametros) {
        return ecossistema.editElemento(tipo, id, parametros);
    }

    public Set<IElemento> getElementos() {
        return ecossistema.getElementos();
    }

    public int getLargura() {
        return ecossistema.getLargura();
    }

    public int getAltura() {
        return ecossistema.getAltura();
    }
    int i = 0;
    public void evolve (IGameEngine gameEngine, long currentTime) {
        ecossistema.evolve(gameEngine,currentTime);
        for (IElemento elemento : ecossistema.getElementos()) {
            if(elemento instanceof Fauna){
                System.out.println(elemento.toString());
            }
        }
        System.out.println();
        pcs.firePropertyChange(EVOLVE, null, null);
    }
}