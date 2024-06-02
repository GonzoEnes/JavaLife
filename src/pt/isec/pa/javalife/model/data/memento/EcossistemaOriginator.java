package pt.isec.pa.javalife.model.data.memento;

import pt.isec.pa.javalife.model.data.ecosystem.Ecosystem;

import java.io.Serializable;

public class EcossistemaOriginator implements Serializable, IOriginator {
    Ecosystem ecossistema;

    @Override
    public IMemento save() {
        return new Memento(this);
    }

    @Override
    public void restore(IMemento memento) {
        Object obj = memento.getSnapshot();
        if (obj instanceof EcossistemaOriginator orig) {
            System.out.println(obj.getClass());
            ecossistema = orig.ecossistema;
        }
    }
}
