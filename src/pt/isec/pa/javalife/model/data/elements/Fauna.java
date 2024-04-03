package pt.isec.pa.javalife.model.data.elements;

public sealed class Fauna extends ElementoBase implements IElementoComForca permits Animal {
    private double forca;

    public Fauna() {
        this.forca = 50;
    }
    @Override
    public double getForca() {
        return 0;
    }

    @Override
    public void setForca(double forca) {
        this.forca = forca;
    }
}
