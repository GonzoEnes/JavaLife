package pt.isec.pa.javalife.model.data.ecosystem;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.elements.*;
import pt.isec.pa.javalife.model.data.events.*;
import pt.isec.pa.javalife.model.gameengine.interfaces.IGameEngine;
import pt.isec.pa.javalife.model.gameengine.interfaces.IGameEngineEvolve;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Ecosystem implements Serializable, IGameEngineEvolve {
    @Serial
    private static final long serialVersionUID = 1L;
    private Set<IElement> elements;
    private Set<IEvent> events;
    private int height;
    private int width;

    public Ecosystem(int altura, int largura) {
        this.height = altura;
        this.width = largura;
        this.elements = ConcurrentHashMap.newKeySet();
        events = new HashSet<>();
        events.add(new Strength(this));
        events.add(new Herbicide(this));
        events.add(new Sun(this));
        createEcosystemBoundaries();
    }
    public void createEcosystemBoundaries() {
        addElemento(new Area(0,7,0,getLargura()), Element.INANIMADO);
        addElemento(new Area(getHeight()-7, getHeight(),0,getLargura()), Element.INANIMADO);
        addElemento(new Area(7, getHeight()-7,0,7), Element.INANIMADO);
        addElemento(new Area(7, getHeight()-7,getLargura()-7,getLargura()), Element.INANIMADO);
    }

    //gets e sets
    public int getLargura() {
        return width;
    }
    public int getHeight() { return height; }
    public void setLargura(int largura) {
        this.width = largura;
    }
    public void setHei(int hei) {
        this.height = hei;
    }
    public List<IElement> getElements() throws CloneNotSupportedException {
        List<IElement> toReturn = new ArrayList<>();
        for (IElement elemento : elements) {
                toReturn.add(elemento.clone());
        }
        return toReturn;
    }
    public List<IElement> getElementsOfType(Element newValue) throws CloneNotSupportedException {
        List<IElement> aux= new ArrayList<>();
        for(IElement elemento : elements){
            if(elemento.getType()==newValue)
                aux.add(elemento.clone());
        }
        return aux;
    }
    public void addElemento(IElement elemento){
        if (!hasAnElemento(elemento.getArea()))
            elements.add(elemento);
    }
    public IElement addElementocmd(Area area, Element type){//problemas ao adicionar um elemento tipo rock
        if (!hasAnElemento(area)) {
            IElement elemento = Element.createElement(type,area,this);
            elements.add(elemento);
            return elemento;
        }
        return null;
    }
    public IElement editElementocmd(int id,Element type,Area area, double speed, double strength) throws CloneNotSupportedException {
        for (IElement elemento : elements) {
            if (elemento.getId() == id && elemento.getType() == type && elemento.getType() == Element.FAUNA) {
                IElement aux = elemento.clone();
                ((Fauna)elemento).setStrength(strength);
                ((Fauna)elemento).setSpeed(speed);
                ((Fauna)elemento).setArea(area);
                return aux;
            }else if(elemento.getId() == id && elemento.getType() == type && elemento.getType() == Element.FLORA) {
                IElement aux = elemento.clone();
                ((Flora)elemento).setStrength(strength);
                ((Flora)elemento).setArea(area);
                return aux;
            }else if(elemento.getId()==id && elemento.getType() == type && elemento.getType() == Element.INANIMADO) {
                IElement aux = elemento.clone();
                ((Inanimado)elemento).setArea(area);
                return aux;
            }
        }
        return null;
    }
    public IElement getElementById(int id, Element type) throws CloneNotSupportedException {
        for (IElement elemento : elements)
            if (elemento.getId() == id && elemento.getType() == type)
                return elemento.clone();

        return null;
    }
    public void editElementoUndo(IElement elemento) {
        for(IElement element : elements){
            if(element.getId() == elemento.getId()){
                if(element.getType() == Element.FAUNA){
                    ((Fauna)element).setStrength(((Fauna)elemento).getStrength());
                }else if(element.getType() == Element.FLORA){
                    ((Flora)element).setStrength(((Flora)elemento).getStrength());
                    ((Flora)element).setImage(((Flora)elemento).getImage());
                }
            }
        }
    }
    public void addElemento(Area area, Element type) {
        if (!hasAnElemento(area)) {
            IElement elemento = Element.createElement(type,area,this);
            elements.add(elemento);
        }
    }
    public void removeElemento(IElement elemento) {
        if (elemento.getType() == Element.INANIMADO && elemento.getId()<5) {
            return;
        }
        elements.remove(elemento);
    }
    public IElement removeElementocmd(int id, Element type) throws CloneNotSupportedException {
        if(type== Element.INANIMADO && id<5)
            return null;
        for (IElement elemento : elements) {
            if (elemento.getId() == id && elemento.getType() == type){
                IElement aux= elemento.clone();
                elements.remove(elemento);
                return aux;
            }
        }
        return null;
    }

    public boolean editElemento(Element tipo, int id, ArrayList<String> parametros) { // ir reforçando, claro
        for (IElement elemento : elements) {
            if (elemento.getId() == id && elemento.getType() == tipo) {
                switch (tipo) {
                    case FAUNA -> {
                        //parametrosOld.add(Double.toString(((Animal)elemento).getForca()));
                        ((Fauna)elemento).setStrength(Double.parseDouble(parametros.getFirst()));
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
        sun();
        for (IElement elemento : elements) {
            if (elemento instanceof Fauna fauna) {
                fauna.evolve();
                System.out.println(fauna.getArea());
            }else if(elemento instanceof Flora flora)
                flora.evolve();
        }
        checkStrength();
    }

    private void checkStrength() {
        for (IElement elemento : elements) {
            if (elemento instanceof Fauna fauna && fauna.getStrength() <= 0) {
                elements.remove(fauna);
            }
            if(elemento instanceof Flora flora && flora.getStrength() <= 0){
                elements.remove(flora);
            }
        }
    }

    //logica para a fauna e flora interagirem
    public boolean hasAnElemento(Area area,int id) {
        for (IElement elemento : elements) {
            if (elemento instanceof Inanimado inanimado && inanimado.getArea().equals(area)) {
                return true;
            }
            if (elemento instanceof Fauna fauna && fauna.getId() != id && fauna.getArea().equals(area)) {
                return true;
            }
            if (elemento instanceof Flora flora && flora.getArea().equals(area)) {
                return true;
            }
        }
        return false;
    }
    public boolean hasAnElemento(Area area) {
        for (IElement elemento : elements) {
            if (elemento.getArea().equals(area)) {
                return true;
            }
        }
        return false;
    }
    public Area getStrongestFauna(int id) {
        double strength = 0;
        Area aux=null;
        for (IElement elemento : elements) {
            if (elemento instanceof Fauna fauna && fauna.getId() != id && fauna.getStrength() > strength){
                    strength = fauna.getStrength();
                    aux = fauna.getArea();
                }
            }
        return aux;
    }
    public Fauna getWeakestFauna(int id) {
        Fauna aux=null;
        for (IElement elemento : elements) {
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
            if(haveAFauna(aux))
                return true;
        }
        return false;
    }

    private boolean haveAFauna(Area aux) {
        for(IElement elemento : elements){
            if(elemento instanceof Fauna fauna && fauna.getArea().equals(aux))
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
        for (IElement elemento : elements) {
            if (elemento instanceof Flora flora && flora.getArea().equals(area)) {
                return flora;
            }
        }
        return null;
    }
    public Area getClosestFlora(Fauna fauna) {
        double x = fauna.getArea().left()+((fauna.getArea().right() - fauna.getArea().left()) / 2);
        double y = fauna.getArea().up()+((fauna.getArea().down() - fauna.getArea().up()) / 2);
        double xClosestFlora;
        double yClosestFlora;
        double distance=-1;
        double distanceClosestFauna;
        Area aux=null;
        for(IElement elemento : elements){
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
        speed= speed+1;
        Area aux = new Area(hunter.up()-speed,hunter.down()+speed,hunter.left()-speed,hunter.right()+speed);
        return aux.equals(prey);
    }
    public boolean hasAnInanimadoOrFauna(Area area, int id) {
        for (IElement elemento : elements) {
            if (elemento instanceof Inanimado inanimado && inanimado.getArea().equals(area)) {
                return true;
            }
            if (elemento instanceof Fauna fauna && fauna.getId() != id && fauna.getArea().equals(area)) {
                return true;
            }
        }
        return false;
    }
    //events
    public void setGainStrengthFasterFlora() {
        for(IElement elemento : elements){
            if(elemento instanceof Flora flora){
                ((Flora) elemento).setIncreaseStrength(((Flora) elemento).getIncreaseStrength()*2);
            }
        }
    }
    public void setGainStrengthNormalFlora() {
        for(IElement elemento : elements){
            if(elemento instanceof Flora flora){
                ((Flora) elemento).setIncreaseStrength(((Flora) elemento).getIncreaseStrength()/2);
            }
        }
    }
    public void setSpeedSlowerFauna() {
        for(IElement elemento : elements){
            if(elemento instanceof Fauna fauna){
                fauna.setSpeed(fauna.getSpeed()/2);
            }
        }
    }
    public void setSpeedNormalFauna() {
        for(IElement elemento : elements){
            if(elemento instanceof Fauna fauna){
                fauna.setSpeed(fauna.getSpeed()*2);
            }
        }
    }
    public void removeElementoEvent(int id, Element elemento) {
        for(IElement element : elements){
            if(element.getId() == id && element.getType() == elemento){
                elements.remove(element);
                return;
            }
        }
    }
    public void addStrengthEvent(int id, Element elemento) {
        for(IElement element : elements){
            if(element.getId() == id && element.getType() == elemento){
                if(element.getType() == Element.FAUNA){
                    ((Fauna)element).increaseStrength(50);
                    return;
                }
                return;
            }
        }
    }
    public void setContadorElementos() {
        for(IElement elemento : elements){
            if(elemento instanceof Fauna fauna)
                fauna.resetCounterId();
            if(elemento instanceof Flora flora)
                flora.resetCounterId();
            if(elemento instanceof Inanimado inanimado)
                inanimado.resetCounterId();
        }
    }


    //events
    public void applyHerbicide(int id) {
        for (IEvent event : events) {
            if(event.getTipo()==Event.HERBICIDE){
                ((Herbicide)event).setId(id);
                event.apply();
            }
        }
    }
    public void applyStrength(int id) {
        for (IEvent event : events) {
            if(event.getTipo()==Event.STRENGTH){
                ((Strength)event).setId(id);
                event.apply();
            }
        }
    }
    public void applySun() {
        for (IEvent event : events) {
            if(event.getTipo()==Event.SUN){
                event.apply();
            }
        }
    }
    public void sun(){
        for (IEvent event : events) {
            if(event.getTipo()==Event.SUN && ((Sun)event).isActive()){
                event.apply();
            }
        }
    }

}
