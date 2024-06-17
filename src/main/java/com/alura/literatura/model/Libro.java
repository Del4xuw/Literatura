package com.alura.literatura.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author Kevin
 */
@Entity
@Data
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idioma;
    private int descargas;

    @ManyToOne
    private Autor autor;
}
