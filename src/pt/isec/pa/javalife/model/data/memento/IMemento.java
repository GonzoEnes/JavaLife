package pt.isec.pa.javalife.model.data.memento;

public interface IMemento {
    default Object getSnapshot() {
        return null;
    }
}
