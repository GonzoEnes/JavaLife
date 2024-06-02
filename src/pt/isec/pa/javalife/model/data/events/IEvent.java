package pt.isec.pa.javalife.model.data.events;

public interface IEvent {
    Event getTipo();
    void apply();
}
