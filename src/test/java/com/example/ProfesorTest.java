package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Pruebas unitarias de {@link Profesor}.
 */
public class ProfesorTest {

    @Test
    public void guardaLosDatosDelProfesor() {
        Profesor profesor = new Profesor("P001", "Laura Gomez", "Ingenieria de Software", 12);

        assertEquals("P001", profesor.getId());
        assertEquals("Laura Gomez", profesor.getNombre());
        assertEquals("Ingenieria de Software", profesor.getDepartamento());
        assertEquals(12, profesor.getAniosExperiencia());
    }

    @Test
    public void losSettersActualizanLosDatos() {
        Profesor profesor = new Profesor("P001", "Laura Gomez", "Ingenieria de Software", 12);

        profesor.setNombre("Laura Gomez Soto");
        profesor.setDepartamento("Sistemas");
        profesor.setAniosExperiencia(15);

        assertEquals("Laura Gomez Soto", profesor.getNombre());
        assertEquals("Sistemas", profesor.getDepartamento());
        assertEquals(15, profesor.getAniosExperiencia());
    }

    @Test
    public void dosProfesoresConElMismoIdSonIguales() {
        Profesor a = new Profesor("P001", "Laura Gomez", "Ingenieria de Software", 12);
        Profesor b = new Profesor("P001", "Laura Gomez Soto", "Ingenieria de Software", 15);

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    public void profesoresConIdsDistintosSonDiferentes() {
        Profesor a = new Profesor("P001", "Laura Gomez", "Ingenieria de Software", 12);
        Profesor b = new Profesor("P002", "Carlos Ruiz", "Ingenieria de Software", 8);

        assertNotEquals(a, b);
    }

    @Test
    public void toStringMuestraElIdYNombre() {
        String texto = new Profesor("P001", "Laura Gomez", "Ingenieria de Software", 12).toString();

        assertTrue(texto.contains("P001"));
        assertTrue(texto.contains("Laura Gomez"));
    }
}
