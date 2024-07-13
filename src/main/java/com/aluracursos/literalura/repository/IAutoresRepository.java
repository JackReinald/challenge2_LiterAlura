package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.modelo.Autores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IAutoresRepository extends JpaRepository<Autores,Long> {

    @Query("SELECT a FROM Autores a WHERE a.anhoMuerte IS NULL OR (:anho >= a.anhoNacimiento AND :anho <= a.anhoMuerte)")
    List<Autores> hallarAutoresVivosEnAnho(int anho);

//    @Query("SELECT a FROM Resultados r JOIN r.autor a WHERE a.nombre ILIKE %:autor%")
//    List<Autores> autoresPorLibro(String autor);

//    @Query("SELECT DISTINCT a FROM Resultados r JOIN r.autor a JOIN FETCH r.libros l WHERE a.nombre ILIKE %:autor%")
//    List<Autores> autoresPorLibro(String autor);

}
