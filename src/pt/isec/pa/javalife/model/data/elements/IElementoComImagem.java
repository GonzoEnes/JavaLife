package pt.isec.pa.javalife.model.data.elements;

public sealed interface IElementoComImagem
        permits Flora {
    String getImagem(); // path da imagem
    void setImagem(String imagem);
}

