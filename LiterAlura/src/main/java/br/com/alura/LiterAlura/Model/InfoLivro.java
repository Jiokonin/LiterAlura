package br.com.alura.LiterAlura.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record InfoLivro(@JsonAlias("title") String titulo,
                        @JsonAlias("authors") List<Autores> autores,
                        @JsonAlias("languages") List<String> idiomas,
                        @JsonAlias("download_count") Double downloads) {
}
