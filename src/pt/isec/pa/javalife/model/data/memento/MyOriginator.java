package pt.isec.pa.javalife.model.data.memento;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.ecosystem.EcossistemaManager;

import java.io.Serial;
import java.io.Serializable;

public class MyOriginator implements Serializable, IOriginator {

    @Serial
    private static final long serialVersionUID = 1L;
    EcossistemaManager manager;
    @Override
    public IMemento save() {
        return new Memento(this);
    }

    @Override
    public void restore(IMemento memento) {
        Object obj = memento.getSnapshot();
        if (obj instanceof MyOriginator myOriginator) {
            manager = myOriginator.manager;
        }
    }
}
