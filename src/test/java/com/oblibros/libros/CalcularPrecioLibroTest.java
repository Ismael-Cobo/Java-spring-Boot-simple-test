package com.oblibros.libros;

import com.oblibros.libros.Entity.Libro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CalcularPrecioLibroTest {
    
    @Test
    void calculatePrice() {
    
        Libro libro1 = new Libro(
                1L,
                "El señor de los anillos",
                "Ben Adams",
                450,
                35D,
                LocalDate.now(),
                true
        );
    
        Libro libro2 = new Libro(
                2L,
                "El señor de las moscas",
                "Ben Angels",
                250,
                35D,
                LocalDate.now(),
                true
        );
    
        CalcularPrecioLibro calcular = new CalcularPrecioLibro();
    
        double precioLibro1 = (double) Math.round(calcular.calculatePrice(libro1) * 100) / 100;
        double precioLibro2 = (double) Math.round(calcular.calculatePrice(libro2) * 100) / 100;
    
        assertEquals(precioLibro1, libro1.getPrice()+5+2.99);
        assertEquals(precioLibro2, libro2.getPrice()+2.99);
    }
}