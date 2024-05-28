package pt.isec.pa.javalife.model.data.area;


import java.io.Serializable;

// este é para definir a área dos elementos, ou seja, quanto espaço ocupam no ecrã
public record Area(double up, double down, double right, double left) implements Serializable {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Area area = (Area) obj;
        return this.up <= area.down && this.down >= area.up && this.right >= area.left && this.left <= area.right;
    }
}

