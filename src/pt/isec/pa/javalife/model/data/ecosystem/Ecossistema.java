package pt.isec.pa.javalife.model.data.ecosystem;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.elements.*;
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
        Pedra cima = new Pedra(new Area(0,7,0, getLargura()));
        Pedra baixo = new Pedra(new Area(getAltura()-7,getAltura(),0,getLargura()));
        Pedra esquerda = new Pedra(new Area(0,getAltura(),0,7));
        Pedra direita = new Pedra(new Area(0,getAltura(),getLargura()-7,getLargura()));
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
                //System.out.println(elemento.getArea().right() + " " + " " +elemento.getArea().down()+ " "  +   elemento.getArea().left()+ " "  + elemento.getArea().up());
            } catch (CloneNotSupportedException ignored) {
            }
        }
        return toReturn;
    }
    // LÓGICA

    public boolean getIsAreaValid(Area area) {
        return area.left() > 7 && area.right() < getLargura()-7 && area.up() > 7 && area.down() < getAltura()-7;
    }
    public void addElemento(IElemento elemento){
        if (!getIsAreaValid(elemento.getArea()) || hasAnElemento(elemento.getArea())) {
            return;
        }
        elementos.add(elemento);
    }
    public IElemento addElementocmd(Area area, Elemento type){
        IElemento elemento = Elemento.createElemento(type, area, this);
        if(elemento==null)
            System.out.println("Elemento nulo");
        if (elemento != null) {
            elementos.add(elemento);
            return elemento;
        }
        return null;
    }
    public IElemento editElementocmd(int id, double strength,Elemento type) throws CloneNotSupportedException {
        for (IElemento elemento : elementos) {
            if (elemento.getId() == id && type == Elemento.FAUNA) {
                IElemento aux = elemento.clone();
                ((Fauna)elemento).setStrength(strength);
                return aux;
            }else if(elemento.getId() == id && type == Elemento.FLORA){
                IElemento aux = elemento.clone();
                ((Flora)elemento).setStrength(strength);
                return aux;
            }
        }
        return null;
    }
    public void editElementoUndo(IElemento elemento) {
        for(IElemento element : elementos){
            if(element.getId() == elemento.getId()){
                if(element.getType() == Elemento.FAUNA){
                    ((Fauna)element).setStrength(((Fauna)elemento).getStrength());
                }else if(element.getType() == Elemento.FLORA){
                    ((Flora)element).setStrength(((Flora)elemento).getStrength());
                    ((Flora)element).setImage(((Flora)elemento).getImage());
                }
            }
        }
    }
    public void addElemento(Area area , String image, Elemento type) {
        if (!getIsAreaValid(area)) {
            return;
        }

        IElemento elemento = null;
        switch (type) {
            case INANIMADO -> {
                elemento = new Pedra(area);
            }
            case FLORA -> {
                elemento = new Erva(area, this, "flora.png");
            }
            case FAUNA -> {
                elemento = new Animal(area, this, image);
            }
        }
        if (elemento != null) {
            elementos.add(elemento);
        }
    }
    public boolean removeElemento(IElemento elemento) {
        if (elemento.getType() == Elemento.INANIMADO) { // não se podem remover inanimados da cerca CORRIGIR
            return false;
        }

        elementos.remove(elemento);
        return true;
    }
    public IElemento removeElementocmd(int id,Elemento type) throws CloneNotSupportedException {
        for (IElemento elemento : elementos) {
            if (elemento.getId() == id && elemento.getType() == type){
                IElemento aux= elemento.clone();
                elementos.remove(elemento);
                return aux;
            }
        }
        return null;
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
        checkStrength();
    }
    private void checkStrength() {
        for (IElemento elemento : elementos) {
            if (elemento instanceof Fauna fauna && fauna.getStrength() <= 0) {
                elementos.remove(fauna);
            }
            if(elemento instanceof Flora flora && flora.getStrength() <= 0){
                elementos.remove(flora);
            }
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
    public boolean hasAnElemento(Area area) {
        for (IElemento elemento : elementos) {
            if (elemento.getArea().equals(area)) {
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
        double x = area.right() - area.left()+1;
        double y = area.down() - area.up()+1;
        Area aux =null;
        for(int i=0;i<4;i++){
            switch (i){
                case 0 -> aux = new Area(area.up() - y,area.down() - y,area.left(),area.right());
                case 1 -> aux = new Area(area.up() + y,area.down() + y,area.left(),area.right());
                case 2 -> aux = new Area(area.up(),area.down(),area.left() - x,area.right() - x);
                case 3 -> aux = new Area(area.up(),area.down(),area.left()+ x,area.right()+x);
            }
            if(!hasAnElemento(aux))
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
        Area aux = new Area(hunter.up()+speed,hunter.left()+speed,hunter.down()+speed,hunter.right()+speed);
        return aux.equals(prey);
    }

    public List<IElemento> getElementsOfType(Elemento newValue) {
        List<IElemento> aux= new ArrayList<>();
        for(IElemento elemento : elementos){
            if(elemento.getType()==newValue)
                aux.add(elemento);
        }
        return aux;
    }

    public void setGainStrengthFasterFlora() {
        for(IElemento elemento : elementos){
            if(elemento instanceof Flora flora){
                ((Flora) elemento).setIncreaseStrength(((Flora) elemento).getIncreaseStrength()*2);
            }
        }
    }
    public void setGainStrengthNormalFlora() {
        for(IElemento elemento : elementos){
            if(elemento instanceof Flora flora){
                ((Flora) elemento).setIncreaseStrength(((Flora) elemento).getIncreaseStrength()/2);
            }
        }
    }
    public void setSpeedSlowerFauna() {
        for(IElemento elemento : elementos){
            if(elemento instanceof Fauna fauna){
                fauna.setSpeed(fauna.getSpeed()/2);
            }
        }
    }
    public void setSpeedNormalFauna() {
        for(IElemento elemento : elementos){
            if(elemento instanceof Fauna fauna){
                fauna.setSpeed(fauna.getSpeed()*2);
            }
        }
    }
    public void removeElementoEvent(int id, Elemento elemento) {
        for(IElemento element : elementos){
            if(element.getId() == id && element.getType() == elemento){
                elementos.remove(element);
                return;
            }
        }
    }
    public void addStrengthEvent(int id, Elemento elemento) {
        for(IElemento element : elementos){
            if(element.getId() == id && element.getType() == elemento){
                if(element.getType() == Elemento.FAUNA){
                    ((Fauna)element).increaseStrength(50);
                    return;
                }
                return;
            }
        }
    }
}
