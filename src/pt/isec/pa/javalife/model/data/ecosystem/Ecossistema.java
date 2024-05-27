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
    private static final long serialVersionUID = 1L;
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
        /*for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                if (i == 0 || i == altura - 1 || j == 0 || j == largura - 1) {
                    elementos.add(new Inanimado(new Area(i,j,i+1,j+1)));
                }
            }
        }*/
        // DUAS ALTERNATIVAS: CRIAR UM FOR LOOP COM VÁRIOS INANIMADOS OU CRIAR UM INANIMADO COM UMA ÁREA GRANDE, QUALQUER UMA DAS DUAS FUNCIONA IGUAL
        Inanimado cima = new Inanimado(new Area(0,0,0,0));
        Inanimado baixo = new Inanimado(new Area(0,0,0,0));
        Inanimado esquerda = new Inanimado(new Area(0,0,0,0));
        Inanimado direita = new Inanimado(new Area(0,0,0,0));

        cima.setArea(new Area(0,0,getLargura(), 7));
        baixo.setArea(new Area(0,getAltura()-7,getLargura(), getAltura()));
        esquerda.setArea(new Area(0,0,7,getAltura()));
        direita.setArea(new Area(getLargura()-7,0,getLargura(),getAltura()));

        elementos.add(cima);
        elementos.add(baixo);
        elementos.add(esquerda);
        elementos.add(direita);
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
        Set<IElemento> toReturn = new HashSet<>();
        for (IElemento elemento : elementos) {
            try {
                toReturn.add(elemento.clone());
            } catch (CloneNotSupportedException ignored) {
            }
        }
        return toReturn;
    }

    // LÓGICA
    public boolean addElemento(IElemento elemento){ // tem de ser feito com factory
        elementos.add(elemento);
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
        for (IElemento elemento : elementos) {
            if (elemento instanceof Fauna fauna) {
                fauna.evolve();
            }
        }
    }

    public Area getFloraMaisProxima(Area area) {
        double pontoMedioX = area.direita() - area.esquerda();
        double pontoMedioY = area.baixo() - area.cima();
        Area toReturn = null;
        double resultado=1000;
        double x;
        double y;
        for (IElemento elemento : elementos) {
            if (elemento instanceof Flora flora) {
                double auxPontoMedioX= flora.getArea().direita() - flora.getArea().esquerda();
                double auxPontoMedioY = flora.getArea().baixo() - flora.getArea().cima();
                if(auxPontoMedioX >= pontoMedioX) {
                    x = auxPontoMedioX - pontoMedioX;
                    if(auxPontoMedioY >= pontoMedioY) {
                        y = auxPontoMedioY - pontoMedioY;
                    }else
                        y= pontoMedioY - auxPontoMedioY;
                    if(resultado>x+y){
                        resultado=x+y;
                        toReturn=flora.getArea();
                    }

                }else{
                    x = pontoMedioX - auxPontoMedioX;
                    if(auxPontoMedioY >= pontoMedioY) {
                        y = auxPontoMedioY - pontoMedioY;
                    }else
                        y= pontoMedioY - auxPontoMedioY;
                    if(resultado>x+y){
                        resultado=x+y;
                        toReturn=flora.getArea();
                    }
                }
            }
        }
        return toReturn;
    }

    public Area getFaunaMaisProximaComMenorForca(Area area, int id) {
        double pontoMedioX = area.direita() - area.esquerda();
        double pontoMedioY = area.baixo() - area.cima();
        Area toReturn = null;
        double resultado=1000;
        double x;
        double y;
        for (IElemento elemento : elementos) {
            if (elemento instanceof Fauna fauna && fauna.getId()!=id) {
                double auxPontoMedioX= fauna.getArea().direita() - fauna.getArea().esquerda();
                double auxPontoMedioY = fauna.getArea().baixo() - fauna.getArea().cima();
                if(auxPontoMedioX >= pontoMedioX) {
                    x = auxPontoMedioX - pontoMedioX;
                    if(auxPontoMedioY >= pontoMedioY) {
                        y = auxPontoMedioY - pontoMedioY;
                    }else
                        y= pontoMedioY - auxPontoMedioY;
                    if(resultado>x+y){
                        resultado=x+y;
                        toReturn=fauna.getArea();
                    }
                }else{
                    x = pontoMedioX - auxPontoMedioX;
                    if(auxPontoMedioY >= pontoMedioY) {
                        y = auxPontoMedioY - pontoMedioY;
                    }else
                        y= pontoMedioY - auxPontoMedioY;
                    if(resultado>x+y){
                        resultado=x+y;
                        toReturn=fauna.getArea();
                    }
                }
            }
        }
        return toReturn;
    }
}