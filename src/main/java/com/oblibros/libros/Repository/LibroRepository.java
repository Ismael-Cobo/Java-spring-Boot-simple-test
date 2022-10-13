package com.oblibros.libros.Repository;

import com.oblibros.libros.Entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
}
