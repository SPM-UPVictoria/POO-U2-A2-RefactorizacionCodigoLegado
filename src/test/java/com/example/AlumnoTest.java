package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import org.junit.Test;

/**
 * Pruebas unitarias de {@link Alumno}.
 */
public class AlumnoTest {

    @Test
    public void guardaLosDatosDelAlumno() {
        Alumno alumno = new Alumno("E1001", "Juan Perez", 20, "Ingenieria de Software");

        assertEquals("E1001", alumno.getCarnet());
        assertEquals("Juan Perez", alumno.getNombre());
        assertEquals(20, alumno.getEdad());
        assertEquals("Ingenieria de Software", alumno.getCarrera());
    }

    @Test
    public void losSettersActualizanLosDatos() {
        Alumno alumno = new Alumno("E1001", "Juan Perez", 20, "Ingenieria de Software");

        alumno.setNombre("Juan Perez Soto");
        alumno.setEdad(21);
        alumno.setCarrera("Sistemas");

        assertEquals("Juan Perez Soto", alumno.getNombre());
        assertEquals(21, alumno.getEdad());
        assertEquals("Sistemas", alumno.getCarrera());
    }

    @Test
    public void dosAlumnosConElMismoCarnetSonIguales() {
        Alumno a = new Alumno("E1001", "Juan Perez", 20, "Ingenieria de Software");
        Alumno b = new Alumno("E1001", "Juan Perez Soto", 21, "Ingenieria de Software");

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    public void alumnosConCarnetsDistintosSonDiferentes() {
        Alumno a = new Alumno("E1001", "Juan Perez", 20, "Ingenieria de Software");
        Alumno b = new Alumno("E1002", "Ana Ruiz", 20, "Ingenieria de Software");

        assertNotEquals(a, b);
    }

    @Test
    public void elAlumnoNoGuardaNingunaCalificacion() {
        // Requisito del sistema: la calificacion se almacena en el grupo
        boolean guardaCalificacion = false;
        for (Field campo : Alumno.class.getDeclaredFields()) {
            String nombre = campo.getName().toLowerCase();
            if (nombre.contains("nota") || nombre.contains("califica") || nombre.contains("promedio")) {
                guardaCalificacion = true;
            }
        }

        assertFalse(guardaCalificacion);
    }

    @Test
    public void toStringMuestraElCarnetYNombre() {
        String texto = new Alumno("E1001", "Juan Perez", 20, "Ingenieria de Software").toString();

        assertTrue(texto.contains("E1001"));
        assertTrue(texto.contains("Juan Perez"));
    }
}
