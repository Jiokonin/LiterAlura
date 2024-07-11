package br.com.alura.LiterAlura.Model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Autores(
        @JsonAlias("name") String nome,
        @JsonAlias("birth_year") Integer anoNascimento,
        @JsonAlias("death_year") Integer anoMorte
) {
}
