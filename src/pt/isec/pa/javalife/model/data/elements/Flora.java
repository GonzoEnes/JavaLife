package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.Area;

public sealed class Flora extends ElementoBase implements IElementoComForca, IElementoComImagem permits Erva {

    private int id;
    private Area area;
    private Elemento tipo;
    private int forca;
    private String imagem;

    public Flora(String imagem) {
        this.imagem = imagem;
        this.forca = 50;
    }

    public Flora(int forca, String imagem) {
        this.setForca(forca);
        this.imagem = imagem;
    }

    @Override
    public double getForca() {
        return forca;
    }

    @Override
    public void setForca(double forca) { // ISTO ESTÁ MAL
        this.forca += forca;
        if (forca > 100 || forca < 0) {
            return;
        }
    }

    @Override
    public String getImagem() {
        return imagem;
    }

    @Override
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Elemento getType() {
        return tipo;
    }

    @Override
    public Area getArea() {
        return area;
    }
}
