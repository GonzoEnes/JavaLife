package pt.isec.pa.javalife.model.data;

import pt.isec.pa.javalife.model.data.elements.IElemento;
import pt.isec.pa.javalife.model.gameengine.interfaces.IGameEngine;
import pt.isec.pa.javalife.model.gameengine.interfaces.IGameEngineEvolve;

import java.io.Serializable;
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
    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {

    }
}