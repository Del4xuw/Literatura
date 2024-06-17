package com.alura.literatura.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Kevin
 */
@Entity
@Data
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private LocalDate fechaFallecimiento;

    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER)  // Cambiado a EAGER
    private List<Libro> libros;
}