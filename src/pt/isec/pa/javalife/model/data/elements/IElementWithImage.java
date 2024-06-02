package pt.isec.pa.javalife.model.data.elements;

public sealed interface IElementWithImage permits Flora, Fauna{
    String getImage(); // path da imagem
    void setImage(String image);
}

