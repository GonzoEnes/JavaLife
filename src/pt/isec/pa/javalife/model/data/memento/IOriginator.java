package pt.isec.pa.javalife.model.data.memento;

public interface IOriginator {
    IMemento save();
    void restore(IMemento memento);
}
