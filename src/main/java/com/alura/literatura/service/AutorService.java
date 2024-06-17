package com.alura.literatura.service;

import com.alura.literatura.model.Autor;
import com.alura.literatura.repository.AutorRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Kevin
 */
public class AutorService {
    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Transactional(readOnly = true)
    public List<Autor> listarAutoresConLibros() {
        return autorRepository.findAll();
    }
}
