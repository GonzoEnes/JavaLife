package pt.isec.pa.javalife.model.data.ecosystem;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.elements.*;
import pt.isec.pa.javalife.model.data.fsm.Context;
import pt.isec.pa.javalife.model.gameengine.interfaces.IGameEngine;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
// esta classe vai servir como Facade do Ecossistema para que as outras classes não consigam manipular o Ecossistema diretamente
public class EcossistemaManager {
    public static final String ECOSSISTEMA_EVOLVE = "_evolve";
    public static final String ECOSSISTEMA_TOOLS = "_tools_";
    public static final String ECOSSISTEMA_ELEMENTS = "_elements_";

    private Ecossistema ecossistema;

    private boolean isEcossistemaCreated;

    // GUARDAR IDS DE PEDRA, ANIMAL E ERVA PQ SERIALIZABLE NÃO GUARDA STATIC

    private long timeInMillis;

    PropertyChangeSupport pcs;

    public void addListener(String property, PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(property, listener);
    }

    // falta aqui depois o CmdManager e o PropertyChangeSupport (para a sinalização dos clientes) quando fizermos a GUI

    public EcossistemaManager(long timeInMillis) throws InterruptedException {
        pcs = new PropertyChangeSupport(this);
        //IElemento elemento = new Fauna(new Area(30,100,100,100), ecossistema);
        //this.ecossistema.addElemento(elemento);
        this.timeInMillis = timeInMillis;
    }

    public boolean setInitialEcossistemaConfigs(int largura, int altura) {
        pcs.firePropertyChange(null,null,null);
        this.ecossistema = new Ecossistema(altura, largura);
        //this.ecossistema.setLargura(largura);
        //this.ecossistema.setAltura(altura);
        return true;
        /*if (isEcossistemaCreated) {

            return true;
        }
        isEcossistemaCreated = true;
        return true;*/
    }

    public boolean addElemento(IElemento elemento) throws InterruptedException {
        //elemento = new Fauna(new Area(10,10,10,10), Elemento.FAUNA, ecossistema);
        return this.ecossistema.addElemento(elemento);
    }
    public boolean removeElemento(IElemento elemento) {
        return ecossistema.removeElemento(elemento);
    }

    public boolean editElemento(Elemento tipo, int id, ArrayList<String> parametros) {
        return ecossistema.editElemento(tipo, id, parametros);
    }

    public Set<IElemento> getElementos() {
        return ecossistema.getElementos();
    }

    public long getTimeInMillis() {
        return timeInMillis;
    }

    public void setTimeInMillis(long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }

    public int getLargura() {
        return ecossistema.getLargura();
    }

    public int getAltura() {
        return ecossistema.getAltura();
    }

    public void evolve (IGameEngine gameEngine, long currentTime) {
/*
        try {
            getFsm().evolve();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        getFsm().evolve(gameEngine, currentTime);*/
        ecossistema.evolve(gameEngine,currentTime);
        pcs.firePropertyChange(ECOSSISTEMA_EVOLVE, null, null);
        /*i++;
        if(i%10 == 0){
            IElemento elemento = new Fauna(new Area(15,15,15,15), Elemento.FAUNA);
            try {
                this.ecossistema.addElemento(elemento);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }*/
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

        pcs.firePropertyChange(ECOSSISTEMA_ELEMENTS,null,null);
        pcs.firePropertyChange(ECOSSISTEMA_TOOLS,null,null);

        return true;
    }

    public boolean importCSVElements(File file) {
        try (
                BufferedReader br = new BufferedReader(new FileReader(file))
        ) {
            String line;

            Set<IElemento> importedElements = new HashSet<>();

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String type = fields[0];
                int cima = Integer.parseInt(fields[1]);
                int baixo = Integer.parseInt(fields[2]);
                int direita = Integer.parseInt(fields[3]);
                int esquerda = Integer.parseInt(fields[4]);
                Area area = new Area(cima, baixo, direita, esquerda);

                switch (type.toUpperCase()) {
                    case "FAUNA":
                        double forcaFauna = Double.parseDouble(fields[5]);
                        Fauna fauna = new Fauna(area, ecossistema);
                        fauna.setForca(forcaFauna);
                        importedElements.add(fauna);
                        break;
                    case "FLORA":
                        double forcaFlora = Double.parseDouble(fields[5]);
                        Flora flora = new Flora(area, "");
                        flora.setForca(forcaFlora);
                        importedElements.add(flora);
                        break;
                    case "INANIMADO":
                        Inanimado inanimado = new Inanimado(area);
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
                    case Fauna fauna -> {
                        line.append(fauna.getType());
                        line.append(fauna.getArea().cima()).append(",");
                        line.append(fauna.getArea().baixo()).append(",");
                        line.append(fauna.getArea().direita()).append(",");
                        line.append(fauna.getArea().esquerda()).append(",");
                        line.append(fauna.getForca());
                    }
                    case Flora flora -> {
                        line.append(flora.getType());
                        line.append(flora.getArea().cima()).append(",");
                        line.append(flora.getArea().baixo()).append(",");
                        line.append(flora.getArea().direita()).append(",");
                        line.append(flora.getArea().esquerda()).append(",");
                        line.append(flora.getForca());
                    }
                    case Inanimado inanimado -> {
                        line.append(inanimado.getType());
                        line.append(inanimado.getArea().cima()).append(",");
                        line.append(inanimado.getArea().baixo()).append(",");
                        line.append(inanimado.getArea().direita()).append(",");
                        line.append(inanimado.getArea().esquerda());
                    }
                    case null, default -> {
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
}