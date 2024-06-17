package com.alura.literatura.service;

import com.alura.literatura.api.response.GutendexBook;
import com.alura.literatura.api.response.GutendexResponse;
import com.alura.literatura.exception.LibroNoEncontradoException;
import com.alura.literatura.model.Autor;
import com.alura.literatura.model.Libro;
import com.alura.literatura.repository.AutorRepository;
import com.alura.literatura.repository.LibroRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Kevin
 */
@Service
public class GutendexService {

    private final RestTemplate restTemplate;
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public GutendexService(RestTemplateBuilder restTemplateBuilder, LibroRepository libroRepository, AutorRepository autorRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public Libro buscarLibroPorTitulo(String titulo) {
        String url = "https://gutendex.com/books?search=" + titulo;
        ResponseEntity<GutendexResponse> response = restTemplate.getForEntity(url, GutendexResponse.class);

        if (response.getBody() != null && !response.getBody().getResults().isEmpty()) {
            GutendexBook book = response.getBody().getResults().get(0);
            List<GutendexBook.Author> authors = book.getAuthors();
            if (authors.isEmpty()) {
                throw new LibroNoEncontradoException("No se encontraron autores para el libro");
            }
            GutendexBook.Author authorData = authors.get(0);

            String fullName = authorData.getName();
            String[] nameParts = fullName.split(" ");
            String nombre = nameParts.length > 1 ? nameParts[0] : fullName;
            String apellido = nameParts.length > 1 ? nameParts[1] : "";

            Autor autor = autorRepository.findByNombreAndApellido(nombre, apellido)
                    .orElseGet(() -> {
                        Autor nuevoAutor = new Autor();
                        nuevoAutor.setNombre(nombre);
                        nuevoAutor.setApellido(apellido);
                        return autorRepository.save(nuevoAutor);
                    });

            Libro libro = new Libro();
            libro.setTitulo(book.getTitle());
            libro.setIdioma(book.getLanguages().isEmpty() ? null : book.getLanguages().get(0));
            libro.setDescargas(book.getDownloadCount());
            libro.setAutor(autor);

            return libroRepository.save(libro);
        } else {
            throw new LibroNoEncontradoException("Libro no encontrado");
        }
    }
}