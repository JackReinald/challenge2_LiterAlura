package com.aluracursos.literalura.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "search_results")
public class Resultados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    @OneToMany(mappedBy = "resultados", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autores> autor;

    private List<String> idiomas;
    private Integer cantidadDeDescargas;

    // Constructor por defecto
    public Resultados(){}

    // Constructor personalizado
//    public Resultados(DatosResultados resultados){
//        this.titulo = resultados.titulo();
//        this.idiomas = resultados.idiomas();
//        this.cantidadDeDescargas = resultados.cantidadDeDescargas();
//    }

    public Resultados(DatosLibros datosLibro) {
        this.titulo = datosLibro.resultados().get(0).titulo();
        this.idiomas = datosLibro.resultados().get(0).idiomas();
        this.autor = datosLibro.resultados().get(0).autor().stream().
                findFirst().
                map(datosAutores -> {
                    Autores autor = new Autores(datosAutores);
                    autor.setResultados(this);
                    return autor;
                    }).stream()
                .collect(Collectors.toList());
        this.cantidadDeDescargas = datosLibro.resultados().get(0).cantidadDeDescargas();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autores> getAutor() {
        return autor;
    }

    public void setAutor(List<Autores> autor) {
        autor.forEach(a -> a.setResultados(this));
        this.autor = autor;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getCantidadDeDescargas() {
        return cantidadDeDescargas;
    }

    public void setCantidadDeDescargas(Integer cantidadDeDescargas) {
        this.cantidadDeDescargas = cantidadDeDescargas;
    }

    @Override
    public String toString() {
        return
                "titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", idiomas=" + idiomas +
                ", cantidadDeDescargas=" + cantidadDeDescargas;
    }
}
