package br.com.alura.LiterAlura.Repository;

import br.com.alura.LiterAlura.Model.Idiomas;
import br.com.alura.LiterAlura.Model.InfoAutores;
import br.com.alura.LiterAlura.Model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Livro, Long>{

    @Query("SELECT a FROM Livro l JOIN l.autores a")
    List<InfoAutores> getInfoAutores();


    @Query ("SELECT a FROM Livro l JOIN l.autores a WHERE anoNascimento > :data")
    List<InfoAutores> getInfoAutorVivo (Integer data);

    List<Livro> findByIdiomas (Idiomas idiomas);


}
