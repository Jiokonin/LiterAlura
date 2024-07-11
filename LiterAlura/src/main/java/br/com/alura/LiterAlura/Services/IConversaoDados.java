package br.com.alura.LiterAlura.Services;

public interface IConversaoDados {
    <T> T getDados(String json, Class<T> tClass);
}
