package br.com.alura.LiterAlura.Model;

public enum Idiomas {
    ENGLISH("ing"),
    SPANISH("spa"),
    FRENCH("fra"),
    ITAIAN("ita"),
    PORTUGUESE("pt");



    private String opcaoIdiomas;

    Idiomas (String opcaoIdiomas){
        this.opcaoIdiomas = opcaoIdiomas;
    }

    public static Idiomas fromString(String texto) {
        for (Idiomas categoria : Idiomas.values())
            if (categoria.opcaoIdiomas.equalsIgnoreCase(texto)){
                return categoria;
            }
        throw new IllegalArgumentException("Idioma não encontrado: " + texto);
    }
}