package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.modelo.*;
import com.aluracursos.literalura.repository.IAutoresRepository;
import com.aluracursos.literalura.repository.IResultadosRepository;
import com.aluracursos.literalura.service.ConsumoAPI;
import com.aluracursos.literalura.service.ConvierteDatos;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Principal {
    public static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private Scanner teclado  = new Scanner(System.in);
    private ConvierteDatos conversor = new ConvierteDatos();
    private IResultadosRepository repository;
    private IAutoresRepository authorsRepository;


    public Principal(IResultadosRepository repository, IAutoresRepository authorsRepository) {
        this.repository = repository;
        this.authorsRepository = authorsRepository;
    }

    public void muestraElMenu() {


        var opcion = -1;
        while(opcion != 0) {
            var menu = """
                    ________________________________________________________________
                    Bienvenido al segundo desafío de ALura: Tu Biblioteca Gutendex.
                    ________________________________________________________________
                    Por favor selecciona la opción que desees usando el numeral de 
                    cada categoría:
                                        
                    1 - Búsqueda de libro por título.
                    2 - Lista de todos los libros buscados.
                    3 - Lista de autores.
                    4 - Lista de autores vivos en determinado año.
                    5 - Lista de libros por idioma.
                                        
                    0 - Para salir.
                    ________________________________________________________________
                                        
                    """;
            System.out.println(menu);
            try {
                opcion = teclado.nextInt();
                switch (opcion) {
                    case 1:
                        buscarLibroPorTitulo();
                        break;
                    case 2:
                        listaDeLibrosBuscados();
                        break;
                    case 3:
                        listaDeAutoresBuscados();
                        break;
                    case 4:
                        listaDeAutoresVivosEnCiertoAnho();
                        break;
                    case 5:
                        listaDeLibrosPorIdioma();
                        break;


                    case 0:
                        System.out.println("Cerrando la aplicación...");
                        break;
                    default:
                        System.out.println("Opción NO VÁLIDA, intente de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Error inesperado de tipo: " + e.getMessage());
                teclado.nextLine(); // Consume la entrada no válida para evitar el loop infinito
            }
        }
    }



    private DatosLibros getDatosLibros(){
        // Consumo de API
        var nombreLibro = teclado.nextLine();

        var URL_generada = URL_BASE + "?search=" + nombreLibro.replace(" ", "%20");
        var json = consumoAPI.obtenerDatos(URL_generada);

        System.out.println("URL ingresada: " + URL_generada);;
        // Deserialización de los datos JSON
        DatosLibros datosLibro = conversor.obtenerDatos(json, DatosLibros.class);
        return datosLibro;
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Ingrese el libro que desea buscar directo de la API");
        teclado.nextLine();
        var datosLibro = getDatosLibros();

        try {
            Resultados resultados = new Resultados(datosLibro);
            System.out.println(repository.existsByTitulo( resultados.getTitulo()));
            if(repository.existsByTitulo( resultados.getTitulo() )) {
                System.out.printf("El libro %s ya se encuentra registrado. \n", resultados.getTitulo());
            } else {
                repository.save(resultados);
                System.out.println("Resultado guardado: " + resultados + "\n");
            }
        } catch (IndexOutOfBoundsException e){
            System.out.println("No se encuentra el elemento que busca");
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }

    }

    private void listaDeLibrosBuscados() {
        List<Resultados> librosBuscados = repository.findAll();
        librosBuscados.forEach(System.out::println);
    }

    private void listaDeAutoresBuscados() {
        var autoresBuscados = repository.findAll();

        Set<String> autores = autoresBuscados.stream()
                .flatMap(resultados -> resultados.getAutor().stream())
                .map(Autores::getNombre) // Obtener solo el nombre del autor
                .collect(Collectors.toCollection(TreeSet::new));

        autores.forEach(System.out::println);
    }

    private void listaDeAutoresVivosEnCiertoAnho() {
        System.out.println("Ingrese el año para saber qué autores aún vivían: ");
        int anho = teclado.nextInt();
        teclado.nextLine();

        var losVivos = authorsRepository.hallarAutoresVivosEnAnho(anho);

        // Registro de los nombres vistos
        Set<String> nombresVistos = new HashSet<>();

        // Filtrar los autores cuyos nombres estén repetidos
        var autoresRepetidos = losVivos.stream()
                .filter(a -> !nombresVistos.add(a.getNombre())) // Agrega el nombre al conjunto; si ya existe, retorna false
                .collect(Collectors.toList());

        var autoresUnicos = losVivos.stream()
                .filter( autor -> !autoresRepetidos.stream()
                        .map(a -> a.getNombre())
                        .collect(Collectors.toSet())
                        .contains(autor.getNombre()) )
                .collect(Collectors.toList());

        List<Autores> autoresSinRepeticion = Stream.concat(autoresUnicos.stream(),autoresRepetidos.stream())
                .collect(Collectors.toList());

        if(losVivos.isEmpty()){
            System.out.println("ningun autor vivo encontrado en ese año");
        } else {
//            losVivos.forEach(System.out::println);
            autoresSinRepeticion.forEach(System.out::println);
        }
    }



    private void listaDeLibrosPorIdioma() {
        //menú dinámico que se actualiza conforme se añaden libros con idiomas no registrados
        var idiomasRepositorio = repository.extraerIdiomas();
        var idiomasNoRepetidos = idiomasRepositorio.stream()
                .distinct().collect(Collectors.toList());
        mostrarMenuIdiomas(idiomasNoRepetidos);

        teclado.nextLine();     // Consume el salto de línea pendiente antes de capturar la entrada del usuario
        var idiomaElegido = teclado.nextLine().toLowerCase().trim();
        System.out.println("Idioma elegido: [" + idiomaElegido + "]");

        // Verifica si el idioma elegido coincide con la lista de idiomas disponibles
        if ( idiomasNoRepetidos.contains(idiomaElegido.toLowerCase()) ){
            var librosEncontrados = repository.encontrarLibrosPorIdiomas(idiomaElegido.toLowerCase());
            if (!librosEncontrados.isEmpty()) {
                librosEncontrados.forEach(libro -> System.out.println("Libro: " + libro.getTitulo() + " Autor: " + libro.getAutor().get(0).getNombre()));
            } else {
                System.out.println("No se encontraron libros para el idioma " + idiomaElegido);
            }
        } else {
            System.out.println("Idioma no válido. Por favor, intente nuevamente.");
        }
    }

    private void mostrarMenuIdiomas(List<String> idiomasNoRepetidos) {
        System.out.println("****************************************************");
        System.out.println("Elija el idioma en el que desea buscar los libros:");
        idiomasNoRepetidos.stream().map(idioma -> "--> " + idioma).forEach(System.out::println);
        System.out.println("****************************************************");
    }

}

