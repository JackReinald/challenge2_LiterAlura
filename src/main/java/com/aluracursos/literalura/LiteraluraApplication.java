package com.aluracursos.literalura;

import com.aluracursos.literalura.principal.Principal;
import com.aluracursos.literalura.repository.IAutoresRepository;
import com.aluracursos.literalura.repository.IResultadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner{
	@Autowired
	private IResultadosRepository repository;

	@Autowired
	private IAutoresRepository authorsRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	// Recuerda idear una forma de no generar excepcion cuando
	// en el menú numérico se coloque una letra.
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository,authorsRepository);
		principal.muestraElMenu();
	}



}
