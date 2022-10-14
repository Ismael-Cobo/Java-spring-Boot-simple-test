package com.oblibros.libros.Controller;

import com.oblibros.libros.Entity.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LibroControllerTest {
    
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    
    @Autowired
    private TestRestTemplate testRestTemplate;
    
    @LocalServerPort
    private int port;
    
    
    
    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:"+port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }
    
    @Test
    void findAll() {
        ResponseEntity<Libro[]> response =
        testRestTemplate.getForEntity("/api/libros", Libro[].class);
    
        assertEquals(200, response.getStatusCode());
    
        List<Libro> libros = Arrays.asList(response.getBody());
        assertEquals(200, response.getStatusCode());
        assertEquals(0, libros.size());
    }
    
    @Test
    void findById() {
        ResponseEntity<Libro> response =
                testRestTemplate.getForEntity("/api/libros/1", Libro.class);
    
        assertEquals(404, response.getStatusCode());
        
    }
    
    @Test
    @DisplayName("Comprobar que se añaden datos correctamente a la base de datos")
    void create() {
    
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        
        String json = """
                {
                    "title": "Libro nuevo 122",
                    "author": "Yo mismo 1",
                    "pages": 140,
                    "price": 10.95,
                    "releaseDate": "2022-12-01",
                    "online": true
                }
                """;
    
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        
        ResponseEntity<Libro> response = testRestTemplate.exchange(
                "/api/libro",
                HttpMethod.POST,
                request,
                Libro.class
        );
        
        Libro libro = response.getBody();
        
        assertEquals(1L, libro.getId());
        assertEquals("Libro nuevo 122", libro.getTitle());
        
    }
}