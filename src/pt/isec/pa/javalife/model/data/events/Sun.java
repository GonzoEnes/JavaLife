package pt.isec.pa.javalife.model.data.events;

import pt.isec.pa.javalife.model.data.ecosystem.Ecosystem;

public final class Sun extends BaseEvent implements IEvent {
    private int count;
    private boolean active = false;
    public Sun(Ecosystem ecosystem) {
        super(ecosystem);
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
        ecosystem.setGainStrengthFasterFlora();
        ecosystem.setSpeedSlowerFauna();
        count--;
        if (count == 0) {
            active=false;
            ecosystem.setGainStrengthNormalFlora();
            ecosystem.setSpeedNormalFauna();
        }
    }
    @Override
    public Event getTipo() {
        return Event.SUN;
    }
}
