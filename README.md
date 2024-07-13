<p align="center">
  <img src = "https://github.com/user-attachments/assets/7ce2c06a-6189-47e9-9ef3-9b9831da18a0" alt="Gutendex API logo personalizado">
</p>

<h1 align="center"> Reto 2: LiterAlura 💻💻 </h1>

## Descripción del proyecto
Segundo desafío de Oracle Next Education, una librería personalizada empleando la API de Gutendex para descargar, almacenar y buscar los libros y su información contenida en una base de datos, como por ejemplo el autor (y sus breves datos personales), el titulo del libro, el idioma en que está escrito, etc. Tambien permite realizar búsquedas por autor, por libro y por idioma dependiendo de los registros guardados en PostgreSQL.

## Estado del proyecto
✔️✔️ TERMINADO ✔️✔️

## :speech_balloon: Funcionalidades y ejemplos
- `Funcionalidad 1`: Menú de selección breve y conciso que revela las capacidades del programa.
  
  ![Menú de opciones](https://github.com/user-attachments/assets/7c67e66a-3eaa-43a7-b902-18d709f488fd)

- `Funcionalidad 2`: Búsqueda de libro por título.

Se encarga de solicitar al usuario la entrada del libro de interés. Realiza una solicitud a la API de Gutendex para buscar coincidencias. La propia API se encarga de procesar la solicitud independientemente de las letras mayúsculas o minúsculas empleadas (Case Insensitive 💝)

![Opción 1 seleccionada](https://github.com/user-attachments/assets/71c3267a-d141-4bdc-a615-95951f2f69b8)


- `Funcionalidad 3`: Lista de todos los libros buscados.

Como su nombre lo indica, imprime una lista con la información de los libros buscados previamente, independientemente si antes hubo o no una ejecución.

![opción 2 seleccionada](https://github.com/user-attachments/assets/0acc6f79-ccb1-4f3a-9277-791bc148257c)

- `Funcionalidad 4`: Lista de autores.

Muestra el registro de todos los autores buscados, sin repeticion.

![Lista de autores buscados](https://github.com/user-attachments/assets/62fc9942-c0bc-4c48-8fca-e11ed4f4484b)

- `Funcionalidad 5`: Lista de autores vivos en determinado año.

Pregunta al usuario un año específico para que el programa logre realizar una query a la base de datos la cual regrese los autores que se asumen vivos al analizar si el año ingresado está entre las fechas de nacimiento y muerte de esa persona 

![Autores vivos para cierto ano](https://github.com/user-attachments/assets/b6bef444-2b8f-420c-b816-d02e8182b61b)

- `Funcionalidad 6`: Lista de libros por idioma.

Posee un menú de selección que se actualiza conforme el usuario agrega libros en nuevos lenguajes a la base de datos, por lo tanto, si solo hay libros en inglés, el menú sólo ofrecera la opción "en" (de "English"), si ha anexado libros en inglés y japonés, ofrecerá las opciones
"en" y "ja" (de "Japanese"), la abreviatura depende de cómo Gutendex representa los lenguajes.

![Menú de selección de búsqueda de libros por lenguaje](https://github.com/user-attachments/assets/062c6668-4a6d-4607-9db8-959b89c6b007)

![Libros en español](https://github.com/user-attachments/assets/6f208ded-b23a-4a0d-99bf-34bf8c09ae0f)
Ese es el único libro en español registrado.

![Libros en inglés](https://github.com/user-attachments/assets/1207f080-16a7-45c7-be09-b8cd350b05df)
Y esos son los libros registrados en inglés.

- Funcionalidad 7: Opción de salida.

Al ingresar el número 0 el programa finaliza limpiamente sin necesitar detener abruptamente la ejecución.

![Opción de salida](https://github.com/user-attachments/assets/1de6a10c-6efa-44ad-88df-669d7388bed7)

- Funcionalidad 8: Manejo de errores.

Este proyecto cuenta con medidas a prueba de la mayoría de errores más comunes que el usuario comete, tales como entradas vacías, entradas con tipos de datos incorrectos, etc, pero la mejor cualidad es que este programa es capaz de manejar la mayoría de excepciones sin romper el flujo constante del código.

![Ejemplo de control de excepciones](https://github.com/user-attachments/assets/d58d410e-7b64-491c-a5cb-f037bca3357b)

Ese es uno de los varios casos de manejo de excepciones, te invito a probar el resto por tu cuenta y mejorar el código en caso de hallar una no resuelta.


## 👨‍💻 Tecnologías utilizadas
* Lenguaje de programación: Java 17
* IntelliJ IDEA Community Edition 2024.1
* Administrador de base de datos: PostgreSQL
* Spring Framework (Spring Boot 3.3.1, con gestor Maven)
* Gutendex API.

## Autor
<img src="https://github.com/JackReinald/challenge1_conversorDeMonedas/assets/83184806/3a231a4b-af87-4268-aa5b-4711b7d723d5" width=150><br><sub>JackReinald</sub>

Error tras error nos acercamos a la maestría 📖











