package br.com.alura.LiterAlura.Services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestAPI {

    public String getDados (String url){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null;
        try{
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException();
        } catch (NullPointerException | InterruptedException e) {
            System.out.println("Não encontrado " + e);
        }

        String json = response.body();
        return json;
        }
    }

