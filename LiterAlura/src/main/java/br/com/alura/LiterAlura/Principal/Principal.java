package br.com.alura.LiterAlura.Principal;

import br.com.alura.LiterAlura.Model.*;
import br.com.alura.LiterAlura.Repository.BookRepository;
import br.com.alura.LiterAlura.Services.ConversaoDados;
import br.com.alura.LiterAlura.Services.RequestAPI;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private RequestAPI requestAPI = new RequestAPI();
    private ConversaoDados conversaoDados = new ConversaoDados();
    private BookRepository repositorio;
    private String livroEscolhido;

    private final String BASE_URL = "https://gutendex.com/books/";

    private List<Livro> livro;

    public Principal(BookRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 6) {
            var menu = """
                    ***** CHALLENGE LITER ALURA *****
                            Buscador de Livros
                    1 - Buscar por título
                    2 - Exibir por lista salva
                    3 - Exibir por Autores
                    4 - Mostrar autores vivos por ano
                    5 - Exibir por idioma
                    6 - Encerrar
                    """;
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarPorTitulo();
                    break;
                case 2:
                    exibeLivrosSalvos();
                    break;
                case 3:
                    exibeAutorSalvo();
                    break;
                case 4:
                    exibeAnoAutor();
                    break;
                case 5:
                    procuraPorIdioma();
                    break;
                case 6:
                    System.out.println("Encerrando");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void buscarPorTitulo() {
        System.out.println("Digite o nome para busca");
        var nome = leitura.nextLine();
        var json = requestAPI.getDados(BASE_URL + "?search=" + nome.replace(" ", "+").toLowerCase());

        if (json.isEmpty() || !json.contains("\"count\":0,\"next\":null,\"previous\":null,\"results\":[]")) {
            var dados = conversaoDados.getDados(json, Dados.class);

            Optional<InfoLivro> livroBuscado = dados.resultado().stream()
                    .findFirst();
            if (livroBuscado.isPresent()) {
                System.out.println("Titulo: " + livroBuscado.get().titulo() +
                        "Autor: " + livroBuscado.get().autores().stream()
                        .map(a -> a.nome()).limit(1).collect(Collectors.joining()) +
                        "Idioma: " + livroBuscado.get().idiomas().stream().collect(Collectors.joining()) +
                        "Quantidade de downloads: " + livroBuscado.get().downloads());

            }

        }
    }

//    private String pesquisarLivro() {
//        System.out.println("Insira o nome do livro para pesquisa:  ");
//        var nome = leitura.nextLine();
//        return nome;
//    }
//    private Dados getInfoLivroApi (String tituloLivro) {
//        var json = requestAPI.getDados(BASE_URL + "?search=%20" + tituloLivro.replace(" ", "+"));
//        var dados = conversaoDados.getDados(json, Dados.class);
//
//        return dados;
//    }

//    private Optional<Livro> getInfoLivro (Dados dadosLivro, String tituloLivro){
//        Optional<Livro> livros = dadosLivro.resultado().stream()
//                .filter(t -> t.titulo().toLowerCase().contains(tituloLivro.toLowerCase()))
//                .map(l -> new Livro(l.titulo(), l.idiomas(), l.downloads(), l.autores()))
//                .findFirst();
//        return livros;
//    }

//    private void buscarSerieWeb () {
//        String titulo = pesquisarLivro();
//        Dados dados = getInfoLivroApi(titulo);
//        Livro livro = new Livro(dados.resultado());
//        repositorio.save(livro);
//
//        System.out.println(livro);
//    }

//    private Optional<Livro> getInfoLivro () {
//        String tituloLivro = pesquisarLivro();
//        Dados infoLivro = getInfoLivroApi(tituloLivro);
//        Optional<Livro> livro = getInfoLivro(infoLivro, tituloLivro);
//
//        if (livro.isPresent()) {
//            var l = livro.get();
//            repositorio.save(l);
//            System.out.println(l);
//        } else {
//            System.out.println("Livro não encontrado");
//        }
//        return livro;
//    }


        private void procuraPorIdioma () {
        String listaIdiomas = """
                Escolha uma linguagem para busca:
                
                Português - "pt"
                Inglês - "ing"
                Espanhol - "spa"
                Italiano - "ita"
                Francês = "fra"
                
                """;
            System.out.println(listaIdiomas);
            String texto = leitura.nextLine();

            var lingua = Idiomas.fromString(texto);
            List<Livro> idiomaLivro = repositorio.findByIdiomas(lingua);

            idiomaLivro.stream()
                    .forEach(System.out::println);
        }

        private void exibeAnoAutor () {
            System.out.println("Digite o ano de nascimento do autor para busca: ");
            int data = leitura.nextInt();
            leitura.nextLine();

            List<InfoAutores> infoAutores = repositorio.getInfoAutorVivo(data);

            infoAutores.stream()
                    .sorted(Comparator.comparing(InfoAutores::getNome))
                    .forEach(a -> System.out.printf("Autor: %s Nascimento: %s Morte %s\n",
                            a.getNome(), a.getAnoNascimento(), a.getAnoMorte()));
        }

        private void exibeAutorSalvo () {
        List<InfoAutores> autores = repositorio.getInfoAutores();

        autores.stream()
                .sorted(Comparator.comparing(InfoAutores::getNome))
                .forEach(a -> System.out.printf("Autor: %s Nascimento: % Morte %s \n",
                        a.getNome(), a.getAnoNascimento(), a.getAnoMorte()));
        }

        private void exibeLivrosSalvos () {
        livro = repositorio.findAll();
        livro.stream()
                .sorted(Comparator.comparing(Livro::getTitulo))
                .forEach(System.out::println);
        }

}







