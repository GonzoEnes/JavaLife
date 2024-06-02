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

    /**
     *  Constructor of the class
     */
    public EcosystemManager() {
        this.gameEngine = new GameEngine();
        pcs = new PropertyChangeSupport(this);
        gameEngine.registerClient((g,t) -> evolve(gameEngine,t));
        commandManager = new CommandManager();
    }

    /**
     * Method used to create the ecosystem
     * @param altura
     * @param largura
     * @param time
     */
    public void createEcosystem(int altura, int largura,int time) {
        ecosystem = new Ecosystem(altura, largura);
        timeBetweenTicks=time;
        this.careTaker = new CareTaker(ecosystem);
    }
    public void startEcosystem() {
        gameEngine.start(timeBetweenTicks);
        pcs.firePropertyChange(ECOSSISTEMA_EVOLVE, null, null);
    }

    /**
     * Method that gets a clone of the Elements
     * @return
     * @throws CloneNotSupportedException
     */
    public List<IElement> getElementos() throws CloneNotSupportedException {
        return ecosystem.getElements();
    }
    /**
     * Method that gets the Elements of a certain type
     * @param newValue
     * @return
     * @throws CloneNotSupportedException
     */
    public List<IElement> getElementsOfType(Element newValue) throws CloneNotSupportedException {
        return ecosystem.getElementsOfType(newValue);
    }
    /**
     * Method that gets the ecosystem's width
     * @return
     */
    public int getLargura() {
        return ecosystem.getLargura();
    }

    /**
     * Method that gets the ecosystem's Height
     * @return
     */
    public int getAltura() {
        return ecosystem.getHeight();
    }
    /**
     * Method that adds the manager to the list of observers
     * @param property
     * @param listener
     */
    public void addClient(String property, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(property, listener);
    }
    /**
     * Method that removes the manager to the list of observers
     * @param property
     * @param listener
     */
    public void removeClient(String property, PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(property, listener);
    }
    /**
     * Method that adds an element to the ecosystem
     * @param element
     */
    public void addElemento(IElement element) {
        ecosystem.addElemento(element);
    }
    /**
     *
     * @param area
     * @param type
     */
    public void addElemento(Area area, Element type) {
        ecosystem.addElemento(area,type);
    }
/**
     * Method that removes an element from the ecosystem
     * @param elemento
     */
    public void removeElementoEvent(IElement elemento) {
        ecosystem.removeElemento(elemento);
    }
    /**
     * Method that resets the counterID
     */
    public void resetCounterId(){
        ecosystem.setContadorElementos();
    }

    public void editElementoUndo(IElement elemento) {
        ecosystem.editElementoUndo(elemento);
    }

    /**
     * Method that saves the current ecosystem into a csv file
     * @param file
     * @return
     */
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
    /**
     * Method that loads a csv file and changes the current ecosystem
     * @param file
     * @return
     */
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
    /**
     * @param file
     * @return
     * @throws CloneNotSupportedException
     */
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

    /**
     * Method that stops the game Engine
     */
    //controlGameEngine
    public void stopEngine() {
        gameEngine.stop();
    }
    /**
     *Method that pauses the game Engine
     */
    public void pauseEngine() {
        gameEngine.pause();
    }
    /**
     *Method that resumes the game Engine
     */
    public void resumeEngine() {
        gameEngine.resume();
    }
    /**
     *Method that starts the game Engine
     */
    public void startEngine() {
        gameEngine.start(timeBetweenTicks);
    }
    /**
     * Method that calls evolve from ecosystem and refreshes the ui
     * @param gameEngine
     * @param currentTime
     */
    public void evolve (IGameEngine gameEngine, long currentTime) {
        ecosystem.evolve(gameEngine,currentTime);
        pcs.firePropertyChange(ECOSSISTEMA_EVOLVE, null, null);
    }

    /**
     * Method that undos the last command
     * @throws InterruptedException
     */
    public void undo() throws InterruptedException {
        commandManager.undo();
    }
    /**
     * Methos that reapplies the past command
     * @throws InterruptedException
     * @throws CloneNotSupportedException
     */
    public void redo() throws InterruptedException, CloneNotSupportedException {
        commandManager.redo();
    }
    /**
     * Method that executes the Add Element command
     * @param area
     * @param type
     * @throws InterruptedException
     * @throws CloneNotSupportedException
     */
    public void addElementWithCommand(Area area, Element type,String image) throws InterruptedException, CloneNotSupportedException {
        AddElementoCmd addCommand = new AddElementoCmd(this, area, type,image);
        commandManager.executeCommand(addCommand);
        pcs.firePropertyChange(ECOSSISTEMA_EVOLVE, null, null);
    }
    /**
     * Method that executes the remove Element command
     * @param id
     * @param type
     * @throws InterruptedException
     * @throws CloneNotSupportedException
     */
    public void removeElementWithCommand(int id, Element type) throws InterruptedException, CloneNotSupportedException {
        RemoveElementoCmd removeElementoCmd = new RemoveElementoCmd(this,id,type);
        commandManager.executeCommand(removeElementoCmd);
        pcs.firePropertyChange(ECOSSISTEMA_EVOLVE, null, null);
    }
    /**
     * Method that executes the Edit Element command
     * @param id
     * @param strength
     * @param type
     * @throws InterruptedException
     * @throws CloneNotSupportedException
     */
    public void editElementWithCommand(int id,Element type, Area area, double speed,double strength) throws InterruptedException, CloneNotSupportedException {
        EditElementoCmd editElementoCmd = new EditElementoCmd(this,id,type,area,speed,strength);
        commandManager.executeCommand(editElementoCmd);
        pcs.firePropertyChange(ECOSSISTEMA_EVOLVE, null, null);
    }
    /**
     * Method that adds the element to the ecosystem from the command
     * @param area
     * @param type
     * @return
     */
    public IElement addElementocmd(Area area, Element type,String image) {
        return ecosystem.addElementocmd(area,type);
    }
    /**
     * Method that removes the element to the ecosystem from the command
     * @param id
     * @param type
     * @return
     * @throws CloneNotSupportedException
     */
    public IElement removeElementoCmd(int id, Element type) throws CloneNotSupportedException {
        //saveState();
        return ecosystem.removeElementocmd(id,type);
    }
    /**
     * Method that edits the element to the ecosystem from the command
     * @param id
     * @param strength
     * @param type
     * @return
     * @throws CloneNotSupportedException
     */
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