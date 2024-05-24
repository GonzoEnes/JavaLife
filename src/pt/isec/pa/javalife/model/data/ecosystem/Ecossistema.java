package pt.isec.pa.javalife.model.data.ecosystem;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.elements.*;
import pt.isec.pa.javalife.model.data.fsm.State;
import pt.isec.pa.javalife.model.gameengine.interfaces.IGameEngine;
import pt.isec.pa.javalife.model.gameengine.interfaces.IGameEngineEvolve;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Ecossistema implements Serializable, IGameEngineEvolve {
    @Serial
    private static final long serialUID = 1L;
    private Set<IElemento> elementos;
    private int altura;
    private int largura;

    public Ecossistema(int altura, int largura) {
        this.altura = altura;
        this.largura = largura;
        this.elementos = ConcurrentHashMap.newKeySet();
        createCerca();
    }

    public void createCerca() {
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                if (i == 0 || i == altura - 1 || j == 0 || j == largura - 1) {
                    elementos.add(new Inanimado(new Area(10,10,10,10),i,j));
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
    public boolean addElemento(IElemento elemento){ // tem de ser feito com factory
        if (!isAreaValida(elemento.getArea())) {
            return false;
        }
        synchronized (elementos){
            elementos.add(elemento);
        }
        return true;
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
        List<IElemento> newElementos = new ArrayList<>();
        List<IElemento> deleteElementos = new ArrayList<>();
        synchronized(elementos){
            Iterator<IElemento> iterator = elementos.iterator();
            while (iterator.hasNext()) {
                IElemento elemento = iterator.next();
                if (elemento instanceof Fauna) {
                    Fauna fauna = (Fauna) elemento;
                    Fauna newFauna = fauna.evolve();
                    if(fauna.getState()== State.MOVIMENTAR){
                        if (newFauna != null) {
                            addElemento(newFauna);
                            newElementos.add(newFauna);
                        }
                    }
                    if(fauna.getState()== State.PROCURAR_COMIDA){
                        if (newFauna != null) {
                            deleteElementos.add(newFauna);
                        }
                    }

                }
            }
        }
        elementos.addAll(newElementos);
        elementos.removeAll(deleteElementos);
    }

    private boolean isAreaValida(Area area) {
        return area.cima() >= 0 && area.esquerda() >= 0 &&
                area.baixo() <= altura && area.direita() <= largura;
    }
}