package pt.isec.pa.javalife.model.data.ecosystem;

import pt.isec.pa.javalife.model.command.CommandManager;
import pt.isec.pa.javalife.model.command.commands.AddElementoCmd;
import pt.isec.pa.javalife.model.command.commands.EditElementoCmd;
import pt.isec.pa.javalife.model.command.commands.RemoveElementoCmd;
import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.elements.*;
import pt.isec.pa.javalife.model.data.events.*;
import pt.isec.pa.javalife.model.data.memento.*;
import pt.isec.pa.javalife.model.gameengine.GameEngine;
import pt.isec.pa.javalife.model.gameengine.interfaces.IGameEngine;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
// esta classe vai servir como Facade do Ecossistema para que as outras classes não consigam manipular o Ecossistema diretamente

public class EcossistemaManager {
    private GameEngine gameEngine;
    private Ecossistema ecossistema;
    private PropertyChangeSupport pcs;
    private long timeBetweenTicks = 1000;
    private CommandManager commandManager;
    private Set<IEvent> events;
    private CareTaker careTaker;
    public static final String ECOSSISTEMA_EVOLVE = "_evolve";

    public EcossistemaManager() {
        this.gameEngine = new GameEngine();
        pcs = new PropertyChangeSupport(this);
        EcossistemaOriginator originator = new EcossistemaOriginator();
        this.careTaker = new CareTaker(originator);
        gameEngine.registerClient((g,t) -> evolve(gameEngine,t));
        commandManager = new CommandManager();
        events = new HashSet<>();
        events.add(new Strength(this));
        events.add(new Herbicide(this));
        events.add(new Sun(this));
    }

    public void saveState() {
        careTaker.save();
    }
    public void undo() throws InterruptedException {
        commandManager.undo();
    }
    public void redo() throws InterruptedException, CloneNotSupportedException {
        commandManager.redo();
    }
    public void reset() {
        careTaker.reset();
    }
    public boolean hasUndo() {
        return careTaker.hasUndo();
    }
    public boolean hasRedo() {
        return careTaker.hasRedo();
    }

    public void addClient(String property, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(property, listener);
    }
    public void removeClient(String property, PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(property, listener);
    }
    public void createEcosystem(int altura, int largura,int time) {
        ecossistema = new Ecossistema(altura, largura);
        timeBetweenTicks=time;
        saveState();
    }
    public void startEcosystem() {
        gameEngine.start(timeBetweenTicks);
        addElemento(new Area(30,50,60,80),"animal.png",Elemento.FAUNA);
        pcs.firePropertyChange(ECOSSISTEMA_EVOLVE, null, null);
    }
    public void addElemento(IElemento element) {
        ecossistema.addElemento(element);
        saveState();
    }
    public void addElemento(Area area, String image,Elemento type) {
        ecossistema.addElemento(area,image,type);
        //saveState();
    }
    public boolean removeElementoEvent(IElemento elemento) {
        saveState();
        return ecossistema.removeElemento(elemento);
    }
    public IElemento addElementocmd(Area area,Elemento type) {
        return ecossistema.addElementocmd(area,type);
    }
    public IElemento removeElementoCmd(int id,Elemento type) throws CloneNotSupportedException {
        //saveState();
        return ecossistema.removeElementocmd(id,type);
    }
    public IElemento editElementoCmd(int id, double strength, Elemento type) throws CloneNotSupportedException {
        //saveState();
        return ecossistema.editElementocmd( id, strength,type);
    }
    public void editElementoUndo(IElemento elemento) {
        ecossistema.editElementoUndo(elemento);
    }
    public Set<IElemento> getElementos() {
        return ecossistema.getElementos();
    }
    public int getLargura() {
        return ecossistema.getLargura();
    }
    public int getAltura() {
        return ecossistema.getAltura();
    }
    public void evolve (IGameEngine gameEngine, long currentTime) {
        sun();
        ecossistema.evolve(gameEngine,currentTime);
        pcs.firePropertyChange(ECOSSISTEMA_EVOLVE, null, null);
    }
    public boolean save(File file) {
        try (
                ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream(file) )
        ) {
            oos.writeObject(ecossistema);
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
            ecossistema = (Ecossistema) ois.readObject();
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
            if (ecossistema == null) {
                return false;
            }

            String line;
            Set<IElemento> importedElements = new HashSet<>();

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String type = fields[0];
                double cima = Double.parseDouble(fields[1]);
                double baixo = Double.parseDouble(fields[2]);
                double esquerda = Double.parseDouble(fields[3]);
                double direita = Double.parseDouble(fields[4]);
                Area area = new Area(cima,baixo,esquerda,direita);

                switch (type.toUpperCase()) {
                    case "FAUNA":
                        double forcaFauna = Double.parseDouble(fields[5]);
                        IElemento fauna = Elemento.createElemento(Elemento.FAUNA, area, ecossistema);
                        assert fauna != null;
                        ((Fauna)fauna).setStrength(forcaFauna);
                        importedElements.add(fauna);
                        break;
                    case "FLORA":
                        double forcaFlora = Double.parseDouble(fields[5]);
                        IElemento flora = Elemento.createElemento(Elemento.FLORA, area, ecossistema);
                        assert flora != null;
                        ((Flora)flora).setStrength(forcaFlora);
                        importedElements.add(flora);
                        break;
                    case "INANIMADO":
                        IElemento inanimado = Elemento.createElemento(Elemento.INANIMADO, area, ecossistema);
                        importedElements.add(inanimado);
                        break;
                    default:
                        System.err.println("Tipo desconhecido: " + type);
                        break;
                }
            }

            for (IElemento elemento : importedElements) {
                ecossistema.addElemento(elemento);
            }

        } catch (Exception e) {
            System.err.println("Erro a importar elementos do CSV: " + e.getMessage());
            return false;
        }
        return true;
    }
    public boolean exportCSVElements(File file) {
        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter(file))
        ) {
            for (IElemento elemento : ecossistema.getElementos()) {
                StringBuilder line = new StringBuilder();

                switch (elemento) {
                    case Fauna fauna-> {
                        line.append(fauna.getType());
                        line.append(fauna.getArea().up()).append(",");
                        line.append(fauna.getArea().down()).append(",");
                        line.append(fauna.getArea().left()).append(",");
                        line.append(fauna.getArea().right()).append(",");
                        line.append(fauna.getStrength());
                    }
                    case Flora flora -> {
                        line.append(flora.getType());
                        line.append(flora.getArea().up()).append(",");
                        line.append(flora.getArea().down()).append(",");
                        line.append(flora.getArea().left()).append(",");
                        line.append(flora.getArea().right()).append(",");
                        line.append(flora.getStrength());
                    }
                    case Inanimado inanimado -> {
                        line.append(inanimado.getType());
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

    public void addElementWithCommand(Area area,Elemento type) throws InterruptedException, CloneNotSupportedException {
        AddElementoCmd addCommand = new AddElementoCmd(this, area, type);
        commandManager.executeCommand(addCommand);
        pcs.firePropertyChange(ECOSSISTEMA_EVOLVE, null, null);
    }
    public void removeElementWithCommand(int id,Elemento type) throws InterruptedException, CloneNotSupportedException {
        RemoveElementoCmd removeElementoCmd = new RemoveElementoCmd(this,id,type);
        commandManager.executeCommand(removeElementoCmd);
        pcs.firePropertyChange(ECOSSISTEMA_EVOLVE, null, null);
    }
    public void editElementWithCommand(int id,double strength,Elemento type) throws InterruptedException, CloneNotSupportedException {
        EditElementoCmd editElementoCmd = new EditElementoCmd(this,id,strength,type);
        commandManager.executeCommand(editElementoCmd);
        pcs.firePropertyChange(ECOSSISTEMA_EVOLVE, null, null);
    }

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

    public List<IElemento> getElementsOfType(Elemento newValue) {
        return ecossistema.getElementsOfType(newValue);
    }

    public void setGainStrengthFasterFlora() {
        ecossistema.setGainStrengthFasterFlora();
    }

    public void setSpeedSlowerFauna() {
        ecossistema.setSpeedSlowerFauna();
    }

    public void setGainStrengthNormalFlora() {
        ecossistema.setGainStrengthNormalFlora();
    }

    public void setSpeedNormalFauna() {
        ecossistema.setSpeedNormalFauna();
    }

    public void removeElementoEvent(int id, Elemento elemento) {
        ecossistema.removeElementoEvent(id,elemento);
    }

    public void addStrengthEvent(int id, Elemento elemento) {
        ecossistema.addStrengthEvent(id,elemento);
    }
    public void sun(){
        for (IEvent event : events) {
            if(event.getTipo()==Event.SUN && ((Sun)event).isActive()){
                event.apply();
            }
        }
    }
}