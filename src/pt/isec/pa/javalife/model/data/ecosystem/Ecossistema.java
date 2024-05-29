package pt.isec.pa.javalife.model.data.ecosystem;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.elements.*;
import pt.isec.pa.javalife.model.gameengine.interfaces.IGameEngine;
import pt.isec.pa.javalife.model.gameengine.interfaces.IGameEngineEvolve;
import pt.isec.pa.javalife.ui.gui.resources.ImageLoader;

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
        Pedra cima = new Pedra(new Area(0,0,getLargura(), 7));
        Pedra baixo = new Pedra(new Area(0,getAltura()-7,getLargura(), getAltura()));
        Pedra esquerda = new Pedra(new Area(0,0,7,getAltura()));
        Pedra direita = new Pedra(new Area(getLargura()-7,0,getLargura(),getAltura()));
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
        System.out.println(elementos.size());
        return elementos;
    }
    // LÓGICA
    public void addElemento(IElemento elemento){ // tem de ser feito com factory
        elementos.add(elemento);
    }
    public void addElemento(Area area, Ecossistema ecossistema, String image, Elemento type) {
        IElemento elemento=null;
        switch (type) {
            case INANIMADO -> {
                elemento = new Pedra(area);
            }
            case FLORA -> {
                elemento = new Erva(area, ecossistema, "flora.png");
            }
            case FAUNA -> {
                elemento = new Animal(area, ecossistema, image);
            }
        }
        if (elemento != null) {
            elementos.add(elemento);
        }
    }
    public boolean removeElemento(IElemento elemento) {
        if (elemento.getType() == Elemento.INANIMADO) { // não se podem remover inanimados
            return false;
        }
        elementos.remove(elemento);
        return true;
    }
    public boolean editElemento(Elemento tipo, int id, ArrayList<String> parametros) { // ir reforçando, claro
        for (IElemento elemento : elementos) {
            if (elemento.getId() == id && elemento.getType() == tipo) {
                switch (tipo) {
                    case FAUNA -> {
                        //parametrosOld.add(Double.toString(((Animal)elemento).getForca()));
                        ((Fauna)elemento).setStrength(Double.parseDouble(parametros.get(0)));
                        return true;
                    }
                    case FLORA -> { // a flora pode editar a força ou a imagem dela acho eu
                        //parametrosOld.add(Double.toString(((Erva)elemento).getForca()));
                        ((Flora)elemento).setStrength(Double.parseDouble(parametros.get(0)));
                        if (!parametros.get(1).isBlank()) { // se o campo da edição de imagem estiver vazio não vale a pena meter nada
                            //parametrosOld.add(((Erva)elemento).getImagem());
                            ((Flora)elemento).setImage("files/" + parametros.get(1));
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
            }else if(elemento instanceof Flora flora)
                flora.evolve();
        }
    }
    public boolean hasAnInanimadoOrFauna(Area area,int id) {
        for (IElemento elemento : elementos) {
            if (elemento instanceof Inanimado inanimado && inanimado.getArea().equals(area)) {
                return true;
            }
            if (elemento instanceof Fauna fauna && fauna.getId() != id && fauna.getArea().equals(area)) {
                return true;
            }
        }
        return false;
    }
    public Area getStrongestFauna(int id) {
        double strength = 0;
        Area aux=null;
        for (IElemento elemento : elementos) {
            if (elemento instanceof Fauna fauna && fauna.getId() != id && fauna.getStrength() > strength){
                    strength = fauna.getStrength();
                    aux = fauna.getArea();
                }
            }
        return aux;
    }
    public Fauna getWeakestFauna(int id) {
        Fauna aux=null;
        for (IElemento elemento : elementos) {
            if (elemento instanceof Fauna fauna && fauna.getId() != id){
                if(aux==null)
                    aux = fauna;
                else if(aux.getStrength()>fauna.getStrength())
                    aux = fauna;
            }
        }
        return aux;
    }
    public boolean hasAFaunaWithinRange(Area area, int maximumReproductionRange) {
        Area aux =null;
        for(int i=0;i<4;i++){
            switch (i){
                case 0 -> aux = new Area(area.up() - maximumReproductionRange,area.down() - maximumReproductionRange,area.left(),area.right());
                case 1 -> aux = new Area(area.up() + maximumReproductionRange,area.down() + maximumReproductionRange,area.left(),area.right());
                case 2 -> aux = new Area(area.up(),area.down(),area.left() - maximumReproductionRange,area.right() - maximumReproductionRange);
                case 3 -> aux = new Area(area.up(),area.down(),area.left()+ maximumReproductionRange,area.right()+maximumReproductionRange);
            }
            if(!hasAnInanimadoOrFauna(aux,-1))
                return true;
        }
        return false;
    }
    public Area hasSpaceForNewFauna(Area area) {
        double x = area.right() - area.left();
        double y = area.down() - area.up();
        Area aux =null;
        for(int i=0;i<4;i++){
            switch (i){
                case 0 -> aux = new Area(area.up() - y,area.down() - y,area.left(),area.right());
                case 1 -> aux = new Area(area.up() + y,area.down() + y,area.left(),area.right());
                case 2 -> aux = new Area(area.up(),area.down(),area.left() - x,area.right() - x);
                case 3 -> aux = new Area(area.up(),area.down(),area.left()+ x,area.right()+x);
            }
            if(!hasAnInanimadoOrFauna(aux,-1))
                return aux;
        }
        return null;
    }
    public Flora hasFloraInThisArea(Area area) {
        for (IElemento elemento : elementos) {
            if (elemento instanceof Flora flora && flora.getArea().equals(area)) {
                return flora;
            }
        }
        return null;
    }
    public Area getClosestFlora(Fauna fauna) {
        double x = fauna.getArea().left()+(fauna.getArea().right() - fauna.getArea().left()) / 2;
        double y = fauna.getArea().up()+(fauna.getArea().down() - fauna.getArea().up()) / 2;
        double xClosestFlora;
        double yClosestFlora;
        double distance=-1;
        double distanceClosestFauna;
        Area aux=null;
        for(IElemento elemento :elementos){
            if(elemento instanceof Flora flora){
                xClosestFlora =flora.getArea().right()+ (flora.getArea().right() - flora.getArea().left()) / 2;
                yClosestFlora = flora.getArea().up()+(flora.getArea().down() - flora.getArea().up()) / 2;
                distanceClosestFauna = Math.abs(x-xClosestFlora)+Math.abs(y-yClosestFlora);
                if(distance==-1) {
                    aux = flora.getArea();
                    distance=distanceClosestFauna;
                } else
                    if(distance>distanceClosestFauna){
                        aux=flora.getArea();
                        distance =distanceClosestFauna;
                    }
            }
        }
        return aux;
    }
    public boolean isFaunaBeingAttackedNearyby(Area hunter,double speed,Area prey) {
        Area aux= new Area(hunter.up()+speed,hunter.left()+speed,hunter.down()+speed,hunter.right()+speed);
        return aux.equals(prey);
    }
}
