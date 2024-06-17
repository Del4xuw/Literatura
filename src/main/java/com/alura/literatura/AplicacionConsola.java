package com.alura.literatura;

import com.alura.literatura.exception.LibroNoEncontradoException;
import com.alura.literatura.model.Autor;
import com.alura.literatura.model.Libro;
import com.alura.literatura.repository.AutorRepository;
import com.alura.literatura.repository.LibroRepository;
import com.alura.literatura.service.GutendexService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * @author Kevin
 */
@Component
public class AplicacionConsola {
    private final GutendexService gutendexService;
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public AplicacionConsola(GutendexService gutendexService, LibroRepository libroRepository, AutorRepository autorRepository) {
        this.gutendexService = gutendexService;
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el título del libro: ");
                    String titulo = scanner.nextLine();
                    try {
                        Libro libro = gutendexService.buscarLibroPorTitulo(titulo);
                        System.out.println("---------LIBRO----------");
                        System.out.println("Titulo: " + libro.getTitulo());
                        System.out.println("Autor: " + libro.getAutor().getNombre() + " " + libro.getAutor().getApellido());
                        System.out.println("Idioma: " + libro.getIdioma());
                        System.out.println("Numero de descargas: " + libro.getDescargas());
                    } catch (LibroNoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    System.out.print("Ingrese el año: ");
                    int año = scanner.nextInt();
                    listarAutoresVivosEnAño(año);
                    break;
                case 5:
                    System.out.print("Ingrese el idioma (ES, EN, FR, PT): ");
                    String idioma = scanner.nextLine();
                    listarLibrosPorIdioma(idioma);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("Elija la opción a través de su número:");
        System.out.println("1- buscar libro por título");
        System.out.println("2- listar libros registrados");
        System.out.println("3- listar autores registrados");
        System.out.println("4- listar autores vivos en un determinado año");
        System.out.println("5- listar libros por idioma");
        System.out.println("0- Salir");
    }

    private void listarLibros() {
        List<Libro> libros = libroRepository.findAll();
        libros.forEach(libro -> {
            System.out.println("---------LIBRO----------");
            System.out.println("Titulo: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor().getNombre() + " " + libro.getAutor().getApellido());
            System.out.println("Idioma: " + libro.getIdioma());
            System.out.println("Numero de descargas: " + libro.getDescargas());
        });
    }

    private void listarAutores() {
        List<Autor> autores = autorRepository.findAllWithLibros();
        autores.forEach(autor -> {
            System.out.println("-----------Autor---------------");
            System.out.println("Autor: " + (autor.getApellido() != null ? autor.getApellido() : "Desconocido") + ", " + (autor.getNombre() != null ? autor.getNombre() : "Desconocido"));
            System.out.println("Fecha de nacimiento: " + (autor.getFechaNacimiento() != null ? autor.getFechaNacimiento() : "Desconocida"));
            System.out.println("Fecha de fallecimiento: " + (autor.getFechaFallecimiento() != null ? autor.getFechaFallecimiento() : "Desconocida"));
            System.out.println("Libros:");
            autor.getLibros().forEach(libro -> System.out.println("\t- " + libro.getTitulo()));
        });
    }

    private void listarAutoresVivosEnAño(int año) {
        LocalDate fecha = LocalDate.of(año, 1, 1);
        List<Autor> autores = autorRepository.findByFechaNacimientoBeforeAndFechaFallecimientoAfter(fecha, fecha);
        autores.forEach(autor -> System.out.println(autor.getApellido() + ", " + autor.getNombre()));
    }

    private void listarLibrosPorIdioma(String idioma) {
        List<Libro> libros = libroRepository.findByIdioma(idioma);
        libros.forEach(libro -> {
            System.out.println("---------LIBRO----------");
            System.out.println("Titulo: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor().getNombre() + " " + libro.getAutor().getApellido());
            System.out.println("Idioma: " + libro.getIdioma());
            System.out.println("Numero de descargas: " + libro.getDescargas());
        });
    }
}