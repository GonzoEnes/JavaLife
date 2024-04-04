package pt.isec.pa.javalife.model.data;

import pt.isec.pa.javalife.model.data.elements.*;
import pt.isec.pa.javalife.model.gameengine.interfaces.IGameEngine;
import pt.isec.pa.javalife.model.gameengine.interfaces.IGameEngineEvolve;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Ecossistema implements Serializable, IGameEngineEvolve {
    private Set<IElemento> elementos;
    private int altura;
    private int largura;

    public Ecossistema(int altura, int largura) {
        this.altura = altura;
        this.largura = largura;
        this.elementos = new HashSet<>();
    }

    public int getAltura() {
        return altura;
    }

    public int getLargura() {
        return largura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public Set<IElemento> getElementos() {
        return elementos;
    }

    /*public boolean addElemento(IElemento elemento) { // ir melhorando obviamente com coords e assim
        if (elemento instanceof Fauna) {
            elementos.add(new Animal());
            return true;
        }
        else if (elemento instanceof Flora) {
            elementos.add(new Erva());
            return true;
        }
        else if (elemento instanceof Inanimado) {
            elementos.add(new Pedra());
            return true;
        }

        return false;
    }*/

    public void editElemento(int id, ArrayList<String> parametros) { // ir reforçando, claro
        for (IElemento elemento : elementos) {
            if (elemento.getId() == id) {
                if (elemento instanceof Erva) {
                    ((Erva) elemento).setForca(Double.parseDouble(parametros.get(0)));
                }
                else if (elemento instanceof Animal) {
                    ((Animal) elemento).setForca(Double.parseDouble(parametros.get(0)));
                }
            }
        }
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {

    }
}