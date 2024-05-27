package pt.isec.pa.javalife.model.data.elements;

import pt.isec.pa.javalife.model.data.area.Area;
import pt.isec.pa.javalife.model.data.ecosystem.Ecossistema;

public final class Flora extends ElementoBase implements IElementoComForca, IElementoComImagem {
    private double forca;
    private String imagem;
    private static int contadorId = 0;
    private int contadorReproducao = 0;
    public static final int MAX_FORCA = 100;
    public static final int MIN_FORCA = 0;
    public static final int INIT_FORCA = 50;
    public static final int AUMENTO_FORCA = 1;
    public static final int REPRODUCAO_FORCA = 90;
    public static final int POS_REPRODUCAO_FORCA = 60;
    public static final int MAX_REPRODUCAO = 2;
    public static final int SER_CONSUMIDO = 1;

    public Flora(Area area) {
        super(++contadorId, area);
        this.forca = INIT_FORCA;
    }
    public Flora(Area area, String imagem) {
        super(++contadorId, area);
        this.forca = 50;
        this.imagem = imagem;
    }

    @Override
    public double getForca() {
        return forca;
    }
    public void aumentarForca(double aumento) {
        if(forca==MAX_FORCA){
            return;
        }
        forca += aumento;
        if(forca>MAX_FORCA)
            forca=MAX_FORCA;
    }

    public void diminuirForca(double diminuir) {
        if(forca==MIN_FORCA){
            return;
        }
        forca -= diminuir;
        if(forca<MIN_FORCA)
            forca=MIN_FORCA;
    }

    public Boolean verificarReproducao(){
        return forca >= REPRODUCAO_FORCA && contadorReproducao < MAX_REPRODUCAO;
    }

    @Override
    public void setForca(double forca) { // ISTO ESTÁ MAL
        if (forca > 100 || forca < 0) {
            return;
        }

        this.forca += forca;
    }

    public Boolean reproducao(Ecossistema ecossistema){
        //if(ecossistema.existeEspacoLivre(getX(),getY())!=-1){
          //  Flora novaFlora = new Flora(getArea(), getImagem(), getX(), getY());
            //ecossistema.adicionarElemento(novaFlora);
            //forca=POS_REPRODUCAO_FORCA;
            contadorReproducao++;
            return true;
      //  }
       // return false;
    }

    public void evolve(){
        aumentarForca(AUMENTO_FORCA);
        if(verificarReproducao()){
            //reproducao(getEcossistema());
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
    public Elemento getType() {
        return Elemento.FLORA;
    }
}
