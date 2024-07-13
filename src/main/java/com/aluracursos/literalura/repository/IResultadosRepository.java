package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.modelo.Autores;
import com.aluracursos.literalura.modelo.Resultados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IResultadosRepository extends JpaRepository<Resultados,Long> {

    @Query("SELECT r.idiomas FROM Resultados r")
    List<String> extraerIdiomas();

    @Query("SELECT r FROM Resultados r WHERE CAST(r.idiomas as String) ILIKE '%' || :idioma || '%'")
    List<Resultados> encontrarLibrosPorIdiomas(String idioma);

    boolean existsByTitulo(String titulo);


}
