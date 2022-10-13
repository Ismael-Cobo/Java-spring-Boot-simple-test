package com.oblibros.libros;

import com.oblibros.libros.Entity.Libro;

public class CalcularPrecioLibro {
    
    public Double calculatePrice (Libro libro) {
    
        double price = libro.getPrice();
        if(libro.getPages() > 300) {
            price += 5;
        }
        
        // Envio +2.99€
        
        price += 2.99;
        
        return price;
    }
}
