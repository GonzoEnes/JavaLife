package pt.isec.pa.javalife.model.data.fsm;

import pt.isec.pa.javalife.model.data.elements.Fauna;
import pt.isec.pa.javalife.model.data.fsm.states.*;

public enum State {
    ATTACK,
    MOVE,
    REPRODUCE,
    LOOKINGFORFOOD;
    public static IState createState(State type, Context context,Fauna fauna) { // factory de objetos
        return switch (type) {
            case ATTACK -> new AttackState(context,fauna);
            case MOVE -> new MoveState(context,fauna);
            case REPRODUCE -> new ReproduceState(context,fauna);
            case LOOKINGFORFOOD -> new LookingForFoodState(context,fauna);
        };
    }
}
