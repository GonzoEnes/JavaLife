package pt.isec.pa.javalife.model.data.elements;

public sealed interface IElementoComImagem
        permits Flora {
    String getImage(); // path da imagem
    void setImage(String image);
}

