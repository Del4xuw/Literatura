package com.alura.literatura.repository;

import com.alura.literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Kevin
 */
public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByFechaFallecimientoIsNull();
    Optional<Autor> findByNombreAndApellido(String nombre, String apellido);

    @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.libros")
    List<Autor> findAllWithLibros();

    List<Autor> findByFechaNacimientoBeforeAndFechaFallecimientoAfter(LocalDate fecha, LocalDate fecha2);
}