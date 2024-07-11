package br.com.alura.LiterAlura.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Dados (@JsonAlias("results") List<InfoLivro> resultado){
}
