package com.aluracursos.literalura.modelo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Autores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Integer anhoNacimiento;
    private Integer anhoMuerte;
    @ManyToOne
    private Resultados resultados;

    // Constructor por defecto
    public Autores(){}


    // Constructor personalizado
    public Autores(DatosAutores datosAutores){
        this.nombre = datosAutores.nombre();
        this.anhoNacimiento = datosAutores.anhoNacimiento();
        this.anhoMuerte = datosAutores.anhoMuerte();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAnhoNacimiento() {
        return anhoNacimiento;
    }

    public void setAnhoNacimiento(Integer anhoNacimiento) {
        this.anhoNacimiento = anhoNacimiento;
    }

    public Integer getAnho_muerte() {
        return anhoMuerte;
    }

    public void setAnho_muerte(Integer anho_muerte) {
        this.anhoMuerte = anho_muerte;
    }

    public Resultados getResultados() {
        return resultados;
    }

    public void setResultados(Resultados resultados) {
        this.resultados = resultados;
    }

    @Override
    public String toString() {
        return  "nombre='" + getNombre() + '\'' +
                ", año de nacimiento=" + getAnhoNacimiento() +
                ", año de muerte=" + getAnho_muerte();
    }
}
