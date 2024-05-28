package pt.isec.pa.javalife.model.data.fsm.states;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;
import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.elements.Flora;
import pt.isec.pa.javalife.model.data.elements.IElemento;
import pt.isec.pa.javalife.model.data.fsm.StateAdapter;
import pt.isec.pa.javalife.model.data.fsm.Context;
import pt.isec.pa.javalife.model.data.fsm.State;

import java.util.Random;

public class ProcurarComidaState extends StateAdapter {
    public ProcurarComidaState(Context context, Ecossistema ecossistema) {
        super(context, ecossistema);
    }

    @Override
    public State getState() {
        return State.PROCURAR_COMIDA;
    }

    @Override
    public void evolve(Fauna fauna, Ecossistema ecossistema) {
        if(!checkIfFloraIsInArea(fauna, ecossistema)){
            moveTowardsTheClosestFlora(fauna, ecossistema);
        }
    }

    public int getRandomNumberInRange(int maxNumber) {
        Random r = new Random();
        return r.nextInt(maxNumber);
    }

    private void moveTowardsTheClosestFlora(Fauna fauna, Ecossistema ecossistema) {
        Area position = ecossistema.getClosestFlora(fauna);
        if (position == null && fauna.getStrength() < 80) {
            changeState(State.ATAQUE);
            return;
        } else if (position != null) {
            double x = (fauna.getArea().right() - fauna.getArea().left()) / 2;
            double y = (fauna.getArea().down() - fauna.getArea().up()) / 2;
            double xClosestFlora = (position.right() - position.left()) / 2;
            double yClosestFlora = (position.down() - position.up()) / 2;
            if (x < xClosestFlora && (xClosestFlora - x) >= 1) {
                if (!ecossistema.hasAnInanimadoOrFauna(new Area(fauna.getArea().up(), fauna.getArea().down(), fauna.getArea().right() + fauna.getSpeed(), fauna.getArea().left() + fauna.getSpeed()))) {
                    fauna.setArea(new Area(fauna.getArea().up(), fauna.getArea().down(), fauna.getArea().right() + fauna.getSpeed(), fauna.getArea().left() + fauna.getSpeed()));
                } else {
                    Area aux = null;
                    int direction;
                    do {
                        direction = getRandomNumberInRange(2);
                        if (direction == 0) {
                            aux = new Area(fauna.getArea().up() - fauna.getSpeed(), fauna.getArea().down() - fauna.getSpeed(), fauna.getArea().right(), fauna.getArea().left());
                        } else
                            aux = new Area(fauna.getArea().up() + fauna.getSpeed(), fauna.getArea().down() + fauna.getSpeed(), fauna.getArea().right(), fauna.getArea().left());
                    } while (!ecossistema.hasAnInanimadoOrFauna(aux));
                    fauna.setArea(aux);
                }
            } else if (x > xClosestFlora && (x - xClosestFlora) >= 1) {
                if (!ecossistema.hasAnInanimadoOrFauna(new Area(fauna.getArea().up(), fauna.getArea().down(), fauna.getArea().right() - fauna.getSpeed(), fauna.getArea().left() - fauna.getSpeed()))) {
                    fauna.setArea(new Area(fauna.getArea().up(), fauna.getArea().down(), fauna.getArea().right() - fauna.getSpeed(), fauna.getArea().left() - fauna.getSpeed()));
                } else {
                    Area aux = null;
                    int direction;
                    do {
                        direction = getRandomNumberInRange(2);
                        if (direction == 0) {
                            aux = new Area(fauna.getArea().up() - fauna.getSpeed(), fauna.getArea().down() - fauna.getSpeed(), fauna.getArea().right(), fauna.getArea().left());
                        } else
                            aux = new Area(fauna.getArea().up() + fauna.getSpeed(), fauna.getArea().down() + fauna.getSpeed(), fauna.getArea().right(), fauna.getArea().left());
                    } while (!ecossistema.hasAnInanimadoOrFauna(aux));
                    fauna.setArea(aux);
                }
            } else if (y < yClosestFlora && (yClosestFlora - y) >= 1) {
                if (!ecossistema.hasAnInanimadoOrFauna(new Area(fauna.getArea().up() + fauna.getSpeed(), fauna.getArea().down() + fauna.getSpeed(), fauna.getArea().right(), fauna.getArea().left()))) {
                    fauna.setArea(new Area(fauna.getArea().up() + fauna.getSpeed(), fauna.getArea().down() + fauna.getSpeed(), fauna.getArea().right(), fauna.getArea().left()));
                } else {
                    Area aux = null;
                    int direction;
                    do {
                        direction = getRandomNumberInRange(2);
                        if (direction == 0) {
                            aux = new Area(fauna.getArea().up(), fauna.getArea().down(), fauna.getArea().right() - fauna.getSpeed(), fauna.getArea().left() - fauna.getSpeed());
                        } else
                            aux = new Area(fauna.getArea().up(), fauna.getArea().down(), fauna.getArea().right() + fauna.getSpeed(), fauna.getArea().left() + fauna.getSpeed());
                    } while (!ecossistema.hasAnInanimadoOrFauna(aux));
                    fauna.setArea(aux);
                }
            } else if (y > yClosestFlora && (y - yClosestFlora) >= 1) {
                if (!ecossistema.hasAnInanimadoOrFauna(new Area(fauna.getArea().up() - fauna.getSpeed(), fauna.getArea().down() - fauna.getSpeed(), fauna.getArea().right(), fauna.getArea().left()))) {
                    fauna.setArea(new Area(fauna.getArea().up() - fauna.getSpeed(), fauna.getArea().down() - fauna.getSpeed(), fauna.getArea().right(), fauna.getArea().left()));
                } else {
                    Area aux = null;
                    int direction;
                    do {
                        direction = getRandomNumberInRange(2);
                        if (direction == 0) {
                            aux = new Area(fauna.getArea().up(), fauna.getArea().down(), fauna.getArea().right() - fauna.getSpeed(), fauna.getArea().left() - fauna.getSpeed());
                        } else
                            aux = new Area(fauna.getArea().up(), fauna.getArea().down(), fauna.getArea().right() + fauna.getSpeed(), fauna.getArea().left() + fauna.getSpeed());
                    } while (!ecossistema.hasAnInanimadoOrFauna(aux));
                    fauna.setArea(aux);
                }
            }
        }
    }

    private boolean checkIfFloraIsInArea(Fauna fauna, Ecossistema ecossistema) {
        Flora  flora = ecossistema.hasFloraInThisArea(fauna.getArea());
        if(flora != null && flora.getStrength()!= fauna.getMaximumStrength()){
            flora.beConsumed();
            fauna.increaseStrength(flora.getReduceStrength());
            return true;
        }else if((flora != null && flora.getStrength()== fauna.getMaximumStrength())|| (fauna.getStrength()>=80)){
            changeState(State.MOVIMENTAR);
            return true;
        }
        return false;
    }
}
