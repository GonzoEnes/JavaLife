package pt.isec.pa.javalife.model.data.ecosystem;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.elements.*;
import pt.isec.pa.javalife.model.gameengine.interfaces.IGameEngine;
import pt.isec.pa.javalife.model.gameengine.interfaces.IGameEngineEvolve;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Ecossistema implements Serializable, IGameEngineEvolve {
    @Serial
    private static final long serialUID = 1L;
    private Set<IElemento> elementos;
    private int altura;
    private int largura;

    public Ecossistema(int altura, int largura) {
        this.altura = altura;
        this.largura = largura;
        this.elementos = new HashSet<>();
        //this.context = new JavaLifeContext();
    }

    // GETTERS & SETTERS
    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() { return altura; }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public Set<IElemento> getElementos() {
        return elementos;
    }

    // LÓGICA

    public boolean addElemento(Area area, Elemento tipo, String imagem) { // tem de ser feito com factory
        return switch (tipo) {
            case INANIMADO -> elementos.add(new Pedra(area, tipo));
            case FAUNA -> elementos.add(new Animal(area, tipo));
            case FLORA -> elementos.add(new Erva(area, tipo, imagem));
        };
    }

    public boolean removeElemento(Elemento tipo, int id) {
        if (tipo == Elemento.INANIMADO) { // não se podem remover inanimados
            return false;
        }

        for (IElemento elemento : elementos) {
            if (elemento.getType() == tipo && elemento.getId() == id) {
                elementos.remove(elemento);
                return true;
            }
        }

        return false;
    }

    public boolean editElemento(Elemento tipo, int id, ArrayList<String> parametros) { // ir reforçando, claro
        //ArrayList<String> parametrosOld = new ArrayList<>();
        for (IElemento elemento : elementos) {
            if (elemento.getId() == id && elemento.getType() == tipo) {
                switch (tipo) {
                    case FAUNA -> {
                        //parametrosOld.add(Double.toString(((Animal)elemento).getForca()));
                        ((Animal)elemento).setForca(Double.parseDouble(parametros.get(0)));
                        return true;
                    }
                    case FLORA -> { // a flora pode editar a força ou a imagem dela acho eu
                        //parametrosOld.add(Double.toString(((Erva)elemento).getForca()));
                        ((Erva)elemento).setForca(Double.parseDouble(parametros.get(0)));
                        if (!parametros.get(1).isBlank()) { // se o campo da edição de imagem estiver vazio não vale a pena meter nada
                            //parametrosOld.add(((Erva)elemento).getImagem());
                            ((Erva)elemento).setImagem("files/" + parametros.get(1));
                        }
                        return true; // devolver os parâmetros antigos para se dar undo() no editar
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void evolve(IGameEngine gameEngine, long currentTime) {
        // aqui não sei o que há de ser posto mas deve ser a chamada da evolve() da fsm digo eu idk
    }
}