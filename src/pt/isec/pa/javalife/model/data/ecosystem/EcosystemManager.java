package pt.isec.pa.javalife.model.data.ecosystem;

import pt.isec.pa.javalife.model.command.CommandManager;
import pt.isec.pa.javalife.model.command.commands.AddElementoCmd;
import pt.isec.pa.javalife.model.command.commands.EditElementoCmd;
import pt.isec.pa.javalife.model.command.commands.RemoveElementoCmd;
import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.elements.*;
import pt.isec.pa.javalife.model.data.events.*;
import pt.isec.pa.javalife.model.data.memento.CareTaker;
import pt.isec.pa.javalife.model.gameengine.GameEngine;
import pt.isec.pa.javalife.model.gameengine.interfaces.IGameEngine;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
// esta classe vai servir como Facade do Ecossistema para que as outras classes não consigam manipular o Ecossistema diretamente

public class EcosystemManager {
    private GameEngine gameEngine;
    private Ecosystem ecosystem;
    private PropertyChangeSupport pcs;
    private long timeBetweenTicks = 1000;
    private CommandManager commandManager;
    public static final String ECOSSISTEMA_EVOLVE = "_evolve";
    private CareTaker careTaker;

    public EcosystemManager() {
        this.gameEngine = new GameEngine();
        pcs = new PropertyChangeSupport(this);
        gameEngine.registerClient((g,t) -> evolve(gameEngine,t));
        commandManager = new CommandManager();
    }
    public void createEcosystem(int altura, int largura,int time) {
        ecosystem = new Ecosystem(altura, largura);
        timeBetweenTicks=time;
        this.careTaker = new CareTaker(ecosystem);
    }
    public void startEcosystem() {
        gameEngine.start(timeBetweenTicks);
        pcs.firePropertyChange(ECOSSISTEMA_EVOLVE, null, null);
    }

    //gets
    public List<IElement> getElementos() throws CloneNotSupportedException {
        return ecosystem.getElements();
    }
    public List<IElement> getElementsOfType(Element newValue) throws CloneNotSupportedException {
        return ecosystem.getElementsOfType(newValue);
    }
    public int getLargura() {
        return ecosystem.getLargura();
    }
    public int getAltura() {
        return ecosystem.getHeight();
    }

    public void addClient(String property, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(property, listener);
    }
    public void removeClient(String property, PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(property, listener);
    }

    public void addElemento(IElement element) {
        ecosystem.addElemento(element);
    }
    public void addElemento(Area area, Element type) {
        ecosystem.addElemento(area,type);
    }
    public void removeElementoEvent(IElement elemento) {
        ecosystem.removeElemento(elemento);
    }

    public void resetCounterId(){
        ecosystem.setContadorElementos();
    }

    //o edit ainda nao esta feito
    public void editElementoUndo(IElement elemento) {
        ecosystem.editElementoUndo(elemento);
    }

    //memento e save em csv
    public boolean save(File file) {
        try (
                ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream(file) )
        ) {
            oos.writeObject(ecosystem);
        } catch (Exception e) {
            System.err.println("Erro na escrita do Ecossistema!");
            return false;
        }
        return true;
    }
    public boolean load(File file) {
        try (
                ObjectInputStream ois = new ObjectInputStream(
                        new FileInputStream(file) )
        ) {
            ecosystem = (Ecosystem) ois.readObject();
        } catch (Exception e) {
            System.err.println("Erro a carregar Ecossistema!");
            return false;
        }
        pcs.firePropertyChange(ECOSSISTEMA_EVOLVE, null, null);
        return true;
    }

    public boolean importCSVElements(File file) {
        try (
                BufferedReader br = new BufferedReader(new FileReader(file))
        ) {
            if (ecosystem == null) {
                return false;
            }

            String line;

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String type = fields[0];
                double cima = Double.parseDouble(fields[1]);
                double baixo = Double.parseDouble(fields[2]);
                double esquerda = Double.parseDouble(fields[3]);
                double direita = Double.parseDouble(fields[4]);
                Area area = new Area(cima, baixo, esquerda, direita);
                addElemento(area, Element.valueOf(type));
            }

        } catch (Exception e) {
            System.err.println("Erro a importar elementos do CSV: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean exportCSVElements(File file) throws CloneNotSupportedException {
        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter(file))
        ) {
            for (IElement elemento : ecosystem.getElements()) {

                StringBuilder line = new StringBuilder();

                switch (elemento) {
                    case Fauna fauna -> {
                        line.append(fauna.getType()).append(",");
                        line.append(fauna.getArea().up()).append(",");
                        line.append(fauna.getArea().down()).append(",");
                        line.append(fauna.getArea().left()).append(",");
                        line.append(fauna.getArea().right()).append(",");
                        line.append(fauna.getStrength());
                    }
                    case Flora flora -> {
                        line.append(flora.getType()).append(",");
                        line.append(flora.getArea().up()).append(",");
                        line.append(flora.getArea().down()).append(",");
                        line.append(flora.getArea().left()).append(",");
                        line.append(flora.getArea().right()).append(",");
                        line.append(flora.getStrength());
                    }
                    case Inanimado inanimado -> {
                        line.append(inanimado.getType()).append(",");
                        line.append(inanimado.getArea().up()).append(",");
                        line.append(inanimado.getArea().down()).append(",");
                        line.append(inanimado.getArea().left()).append(",");
                        line.append(inanimado.getArea().right());
                    }
                    default -> {
                        System.err.println("Tipo desconhecido: " + elemento.getClass().getName());
                        continue;
                    }
                }
                bw.write(line.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao exportar elementos para o CSV: " + e.getMessage());
            return false;
        }
        return true;
    }

    //controlGameEngine
    public void stopEngine() {
        gameEngine.stop();
    }
    public void pauseEngine() {
        gameEngine.pause();
    }
    public void resumeEngine() {
        gameEngine.resume();
    }
    public void startEngine() {
        gameEngine.start(timeBetweenTicks);
    }
    public void evolve (IGameEngine gameEngine, long currentTime) {
        ecosystem.evolve(gameEngine,currentTime);
        pcs.firePropertyChange(ECOSSISTEMA_EVOLVE, null, null);
    }

    //commandsUI
    public void undo() throws InterruptedException {
        commandManager.undo();
    }
    public void redo() throws InterruptedException, CloneNotSupportedException {
        commandManager.redo();
    }
    public void addElementWithCommand(Area area, Element type,String image) throws InterruptedException, CloneNotSupportedException {
        AddElementoCmd addCommand = new AddElementoCmd(this, area, type,image);
        commandManager.executeCommand(addCommand);
        pcs.firePropertyChange(ECOSSISTEMA_EVOLVE, null, null);
    }
    public void removeElementWithCommand(int id, Element type) throws InterruptedException, CloneNotSupportedException {
        RemoveElementoCmd removeElementoCmd = new RemoveElementoCmd(this,id,type);
        commandManager.executeCommand(removeElementoCmd);
        pcs.firePropertyChange(ECOSSISTEMA_EVOLVE, null, null);
    }
    public void editElementWithCommand(int id,Element type, Area area, double speed,double strength) throws InterruptedException, CloneNotSupportedException {
        EditElementoCmd editElementoCmd = new EditElementoCmd(this,id,type,area,speed,strength);
        commandManager.executeCommand(editElementoCmd);
        pcs.firePropertyChange(ECOSSISTEMA_EVOLVE, null, null);
    }
    //commandsCommands
    public IElement addElementocmd(Area area, Element type,String image) {
        return ecosystem.addElementocmd(area,type);
    }
    public IElement removeElementoCmd(int id, Element type) throws CloneNotSupportedException {
        //saveState();
        return ecosystem.removeElementocmd(id,type);
    }
    public IElement editElementoCmd(int id, Element type, Area area,double speed,double strength) throws CloneNotSupportedException {
        //saveState();
        return ecosystem.editElementocmd( id,type,area,speed,strength);
    }

    //events
    public void applyEvent(Event event,int id) {
        if(event == Event.STRENGTH)
            ecosystem.applyStrength(id);
        ecosystem.applyHerbicide(id);

    }
    public void applyEvent(Event event) {
        ecosystem.applySun();
    }

    public IElement getElementById(int id, Element type) throws CloneNotSupportedException {
        return ecosystem.getElementById(id,type);
    }

    public void saveSnapshot() {
        careTaker.save();
    }

    public void restoreSnapshot() {
       careTaker.undo();
        pcs.firePropertyChange(ECOSSISTEMA_EVOLVE, null, null);
    }
}