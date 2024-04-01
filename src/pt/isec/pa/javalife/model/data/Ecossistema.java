package pt.isec.pa.javalife.model.data;

import pt.isec.pa.javalife.model.data.interfaces.IElemento;
import pt.isec.pa.javalife.model.gameengine.interfaces.IGameEngine;
import pt.isec.pa.javalife.model.gameengine.interfaces.IGameEngineEvolve;

import java.io.Serializable;
import java.util.Set;

public class Ecossistema
        implements Serializable, IGameEngineEvolve {
    private Set<IElemento> elementos;

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {

    }
}