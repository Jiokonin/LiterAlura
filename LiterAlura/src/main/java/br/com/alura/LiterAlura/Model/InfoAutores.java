package br.com.alura.LiterAlura.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "InfoAutor")
public class InfoAutores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nome;
    private Integer anoNascimento;
    private Integer anoMorte;

    @ManyToOne
    private Livro livro;

    public InfoAutores(InfoAutores infoAutores){}

    public InfoAutores(String nome, Integer anoNascimento, Integer anoMorte, Livro livro){

        this.livro = livro;
        this.nome = nome;
        this.anoNascimento = anoNascimento;
        this.anoMorte = anoMorte;

    }

    public Livro getLivro(){
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Integer getAnoMorte() {
        return anoMorte;
    }

    public void setAnoMorte(Integer anoMorte) {
        this.anoMorte = anoMorte;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return
                "nome='" + nome + '\'' +
                ", Data de Nascimento: " + anoNascimento +
                ", Data de Falecimento:" + anoMorte;
    }
}
