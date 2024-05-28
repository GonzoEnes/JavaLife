package pt.isec.pa.javalife.model.data.area;


import java.io.Serializable;

// este é para definir a área dos elementos, ou seja, quanto espaço ocupam no ecrã
public record Area(double up, double down, double right, double left) implements Serializable {

    @Override
    public double up() {
        return up;
    }

    @Override
    public double down() {
        return down;
    }

    @Override
    public double right() {
        return right;
    }

    @Override
    public double left() {
        return left;
    }

}

