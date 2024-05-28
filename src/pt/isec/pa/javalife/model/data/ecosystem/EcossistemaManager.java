package pt.isec.pa.javalife.model.data.ecosystem;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.elements.Elemento;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.elements.Flora;
import pt.isec.pa.javalife.model.data.elements.IElemento;
import pt.isec.pa.javalife.model.data.fsm.Context;
import pt.isec.pa.javalife.model.gameengine.interfaces.IGameEngine;

import java.io.*;
import java.util.ArrayList;
import java.util.Set;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
// esta classe vai servir como Facade do Ecossistema para que as outras classes não consigam manipular o Ecossistema diretamente
public class EcossistemaManager {
    public static final String ECOSSISTEMA_EVOLVE = "_evolve";
    public static final String ECOSSISTEMA_TOOLS = "_tools_";
    public static final String ECOSSISTEMA_ELEMENTS = "_elements_";
    private Ecossistema ecossistema;
    public static final String EVOLVE = "_evolve";
    private long timeInMillis;
    PropertyChangeSupport pcs;

    // observer/observable managenment
    public void addClient(String property, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(property, listener);
    }

    public void removeClient(String property, PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(property, listener);
    }

    public EcossistemaManager(long timeInMillis) throws InterruptedException {
        pcs = new PropertyChangeSupport(this);
        this.ecossistema = new Ecossistema(600, 600);
        IElemento elemento = new Fauna(new Area(400,300,450,320), ecossistema);
        IElemento elemento1 = new Flora(new Area(200,150,250,180));
        this.ecossistema.addElemento(elemento);
        this.ecossistema.addElemento(elemento1);
        this.timeInMillis = timeInMillis;
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

    public void evolve (IGameEngine gameEngine, long currentTime) {
        ecossistema.evolve(gameEngine,currentTime);
        for (IElemento elemento : ecossistema.getElementos()) {
            if(elemento instanceof Fauna){
                System.out.println(elemento.toString());
            }
        }
        System.out.println();
        pcs.firePropertyChange(EVOLVE, null, null);
        pcs.firePropertyChange(ECOSSISTEMA_EVOLVE, null, null);
        /*i++;
        if(i%10 == 0){
            IElemento elemento = new Fauna(new Area(15,15,15,15), Elemento.FAUNA);
            try {
                this.ecossistema.addElemento(elemento);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }*/
    }

    public boolean save(File file) {
        try (
                ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream(file) )
        ) {
            oos.writeObject(ecossistema);
        } catch (Exception e) {
            System.err.println("Erro na escrita do Ecossistema!");
            return false;
        }
        return true;
    }

    public boolean load(File file) {
        try (
                ObjectInputStream ois = new ObjectInputStream(
                        new FileInputStream(file) )
        ) {
            ecossistema = (Ecossistema) ois.readObject();
        } catch (Exception e) {
            System.err.println("Erro a carregar Ecossistema!");
            return false;
        }

        pcs.firePropertyChange(ECOSSISTEMA_ELEMENTS,null,null);
        pcs.firePropertyChange(ECOSSISTEMA_TOOLS,null,null);

        return true;
    }
}