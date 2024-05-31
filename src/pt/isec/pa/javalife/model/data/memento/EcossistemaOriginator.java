package pt.isec.pa.javalife.model.data.memento;

import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;

import java.io.Serializable;

public class EcossistemaOriginator implements Serializable, IOriginator {
    Ecossistema ecossistema;

    @Override
    public IMemento save() {
        return new Memento(this);
    }

    @Override
    public void restore(IMemento memento) {
        Object obj = memento.getSnapshot();
        if (obj instanceof EcossistemaOriginator orig) {
            ecossistema = orig.ecossistema;
            //System.out.println(ecossistema.getAltura());
            //System.out.println(ecossistema.getLargura() + " SEPARATOR ELEMENTOS: " + ecossistema.getElementos().size());
        }
    }
}
