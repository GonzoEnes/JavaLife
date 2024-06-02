package pt.isec.pa.javalife.model.data.elements;

public sealed interface IElementoComForca permits Fauna, Flora {
    double getStrength();
    void setStrength(double strength);
}