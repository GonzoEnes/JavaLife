package pt.isec.pa.javalife.model.data.area;


import java.io.Serializable;

// este é para definir a área dos elementos, ou seja, quanto espaço ocupam no ecrã
public record Area(double cima, double esquerda,
                   double baixo, double direita) implements Serializable {}

