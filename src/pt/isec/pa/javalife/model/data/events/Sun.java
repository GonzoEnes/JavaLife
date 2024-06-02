package pt.isec.pa.javalife.model.data.events;

import pt.isec.pa.javalife.model.data.ecosystem.EcosystemManager;

public final class Sun extends BaseEvent implements IEvent {
    private int count;
    private boolean active = false;
    public Sun(EcosystemManager ecossistema) {
        super(ecossistema);
    }
    public boolean isActive() {
        return active;
    }
    @Override
    public void apply() {
        if(!active){
            active = true;
            count = 10;
        }
        ecossistema.setGainStrengthFasterFlora();
        ecossistema.setSpeedSlowerFauna();
        count--;
        if (count == 0) {
            active=false;
            ecossistema.setGainStrengthNormalFlora();
            ecossistema.setSpeedNormalFauna();
        }
    }
    @Override
    public Event getTipo() {
        return Event.SUN;
    }
}
