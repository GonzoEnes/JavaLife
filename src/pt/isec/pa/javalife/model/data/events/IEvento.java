package pt.isec.pa.javalife.model.data.events;

public interface IEvento {
    Evento getTipo();

    boolean apply(int id);
}
