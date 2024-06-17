```
# Proyecto de Literatura

Este proyecto es una aplicación de consola que permite buscar libros y autores utilizando el servicio de Gutendex. La aplicación está construida con Spring Boot y utiliza una base de datos PostgreSQL para almacenar la información de los libros y autores.

## Requisitos

- Java 17
- Maven
- PostgreSQL

## Configuración

1. Clona el repositorio:

   ```bash
   git clone https://github.com/tu-usuario/proyecto-literatura.git
   cd proyecto-literatura
   ```

2. Configura la base de datos en el archivo `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/nombre_de_tu_base_de_datos
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.properties.hibernate.format_sql=true
   ```

3. Compila y ejecuta la aplicación:

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## Uso

La aplicación de consola permite realizar las siguientes acciones:

1. Buscar libro por título
2. Listar libros registrados
3. Listar autores registrados
4. Listar autores vivos en un determinado año
5. Listar libros por idioma
6. Salir

Para iniciar la aplicación de consola, ejecuta el método `iniciar` de la clase `AplicacionConsola`.

## Estructura del Proyecto

- `src/main/java/com/alura/literatura`: Contiene las clases principales de la aplicación.
- `src/main/java/com/alura/literatura/api/response`: Contiene las clases de respuesta de la API de Gutendex.
- `src/main/java/com/alura/literatura/exception`: Contiene las excepciones personalizadas.
- `src/main/java/com/alura/literatura/model`: Contiene las entidades `Autor` y `Libro`.
- `src/main/java/com/alura/literatura/repository`: Contiene los repositorios de `Autor` y `Libro`.
- `src/main/java/com/alura/literatura/service`: Contiene el servicio `GutendexService`.
- `src/main/resources`: Contiene los archivos de configuración.

## Autor

Kevin
```
