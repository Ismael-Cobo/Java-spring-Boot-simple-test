package com.oblibros.libros.Controller;

import com.oblibros.libros.Entity.Libro;
import com.oblibros.libros.Repository.LibroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/libro")
public class LibroController {
    
    private LibroRepository libroRepository;
    
    public LibroController(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }
    
    @GetMapping
    public ResponseEntity<Collection<Libro>> findAll() {
        return ResponseEntity.ok(libroRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Libro> findById(@PathVariable("id") Long id) {
        Optional<Libro> optionalLibro = libroRepository.findById(id);
        
        if(optionalLibro.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(optionalLibro.get());
    }
    
    @PostMapping
    public ResponseEntity<Libro> create(@RequestBody Libro libro) {
        return ResponseEntity.ok(libroRepository.save(libro));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Libro> updateById(@PathVariable("id") Long id, @RequestBody Libro libro) {
        Optional<Libro> optionalLibro = libroRepository.findById(id);
        if (optionalLibro.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        libro.setId(optionalLibro.get().getId());
        libroRepository.save(libro);
        return ResponseEntity.ok(libro);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        Optional<Libro> optionalLibro = libroRepository.findById(id);
        if (optionalLibro.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        libroRepository.delete(optionalLibro.get());
        return ResponseEntity.ok().build();
    }
    
}
