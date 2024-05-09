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
        createCerca();
        //this.context = new JavaLifeContext();
    }

    public void createCerca() {
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                if (i == 0 || i == altura - 1 || j == 0 || j == largura - 1) {
                    elementos.add(new Inanimado(new Area(i+1, j, i, j + 1), Elemento.INANIMADO));
                }
            }
        }
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
    public boolean addElemento(IElemento elemento) throws InterruptedException { // tem de ser feito com factory
        if (!isAreaValida(elemento.getArea())) {
            return false;
        }

        return switch (elemento.getType()) {
            case INANIMADO -> elementos.add(new Inanimado(elemento.getArea(), elemento.getType()));
            case FAUNA -> elementos.add(new Fauna(elemento.getArea(), elemento.getType()));
            case FLORA -> elementos.add(new Flora(elemento.getArea(), elemento.getType(), ((Flora) elemento).getImagem()));
        };
    }

    public boolean removeElemento(IElemento elemento) {
        if (elemento.getType() == Elemento.INANIMADO) { // não se podem remover inanimados
            return false;
        }
        elementos.remove(elemento);
        return true;
    }

    public boolean editElemento(Elemento tipo, int id, ArrayList<String> parametros) { // ir reforçando, claro
        //ArrayList<String> parametrosOld = new ArrayList<>();
        for (IElemento elemento : elementos) {
            if (elemento.getId() == id && elemento.getType() == tipo) {
                switch (tipo) {
                    case FAUNA -> {
                        //parametrosOld.add(Double.toString(((Animal)elemento).getForca()));
                        ((Fauna)elemento).setForca(Double.parseDouble(parametros.get(0)));
                        return true;
                    }
                    case FLORA -> { // a flora pode editar a força ou a imagem dela acho eu
                        //parametrosOld.add(Double.toString(((Erva)elemento).getForca()));
                        ((Flora)elemento).setForca(Double.parseDouble(parametros.get(0)));
                        if (!parametros.get(1).isBlank()) { // se o campo da edição de imagem estiver vazio não vale a pena meter nada
                            //parametrosOld.add(((Erva)elemento).getImagem());
                            ((Flora)elemento).setImagem("files/" + parametros.get(1));
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

    private boolean isAreaValida(Area area) {
        return area.cima() >= 0 && area.esquerda() >= 0 &&
                area.baixo() <= altura && area.direita() <= largura;
    }
}