package br.com.alura.LiterAlura.Model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Livros")

public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(unique = true)
    private String titulo;

    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<InfoAutores> autores;

    @Enumerated(EnumType.STRING)
    private Idiomas idiomas;
    private double downloads;

    public Livro (List<InfoLivro> resultado){}

    public Livro (String titulo, List<String> idiomas, double downloads, List<Autores> autores){

        this.titulo = titulo;
        this.idiomas = Idiomas.fromString(idiomas.get(0));
        this.downloads = downloads;
        this.autores = new ArrayList<>();

        for (Autores infoAutores : autores){
            InfoAutores autor = new InfoAutores(infoAutores.nome(), infoAutores.anoNascimento(),
                    infoAutores.anoMorte(), this);
            this.autores.add(autor);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<InfoAutores> getAutores() {
        return autores;
    }

    public void setAutores(List<InfoAutores> autores) {
        this.autores = autores;
    }

    public Idiomas getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Idiomas idiomas) {
        this.idiomas = idiomas;
    }

    public double getDownloads() {
        return downloads;
    }

    public void setDownloads(double downloads) {
        this.downloads = downloads;
    }

    @Override
    public String toString() {
        return
                "titulo='" + titulo + '\'' +
                ", autores=" + autores +
                ", idiomas=" + idiomas +
                ", downloads=" + downloads;

    }

//    public void setAutores (List<InfoAutores> autores){
//        autores.forEach(e -> e.setLivro(this));
//        this.autores = autores;
//    }
}
