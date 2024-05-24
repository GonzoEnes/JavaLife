package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.area.Area;

public final class Flora extends ElementoBase implements IElementoComForca, IElementoComImagem {
    private double forca;
    private String imagem;
    private static int idS = 0;

    public Flora(Area area, String imagem) {
        super(++idS, area);
        this.forca = 50;
        this.imagem = imagem;
    }

    @Override
    public double getForca() {
        return forca;
    }

    @Override
    public void setForca(double forca) { // ISTO ESTÁ MAL
        if (forca > 100 || forca < 0) {
            return;
        }

        this.forca += forca;
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
    public Elemento getType() {
        return Elemento.FLORA;
    }
}
