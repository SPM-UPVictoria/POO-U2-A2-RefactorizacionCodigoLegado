package com.example;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Pruebas unitarias de {@link SistemaCalificaciones}.
 */
public class SistemaCalificacionesTest {

    private Grupos poo;
    private Grupos bd;
    private Alumno juan;
    private Alumno ana;

    @Before
    public void setUp() {
        SistemaCalificaciones.limpiar();
        this.poo = SistemaCalificaciones.crearGrupo("Programacion Orientada a Objetos", "POO-1",
                "P001", "Laura Gomez", "Ingenieria de Software", 12);
        this.bd = SistemaCalificaciones.crearGrupo("Base de Datos", "BD-1",
                "P002", "Carlos Ruiz", "Ingenieria de Software", 8);
        this.juan = new Alumno("E1001", "Juan Perez", 20, "Ingenieria de Software");
        this.ana = new Alumno("E1002", "Ana Ruiz", 22, "Ingenieria de Software");
    }

    @After
    public void tearDown() {
        SistemaCalificaciones.limpiar();
    }

    @Test
    public void crearGrupoDejaElGrupoRegistradoEnElSistema() {
        assertEquals(2, SistemaCalificaciones.obtenerGrupos().size());
        assertEquals(this.poo, SistemaCalificaciones.obtenerGrupos().get(0));
        assertEquals(this.bd, SistemaCalificaciones.obtenerGrupos().get(1));
    }

    @Test
    public void elGrupoCreadoTieneSuProfesor() {
        assertEquals("P001", this.poo.getProfesor().getId());
        assertEquals("Laura Gomez", this.poo.getProfesor().getNombre());
    }

    @Test
    public void elSistemaCuentaLosAlumnosDeTodosLosGrupos() {
        this.poo.inscribir(this.juan);
        this.poo.inscribir(this.ana);
        this.bd.inscribir(this.juan);

        assertEquals(3, SistemaCalificaciones.totalAlumnos());
    }

    @Test
    public void elPromedioGeneralUsaTodasLasNotasRegistradas() {
        this.poo.inscribir(this.juan);
        this.bd.inscribir(this.juan);
        this.poo.registrarCalificacion(this.juan, 80);
        this.bd.registrarCalificacion(this.juan, 90);

        assertEquals(85.0, SistemaCalificaciones.promedioGeneral(), 0.0001);
    }

    @Test
    public void elSistemaCuentaAprobadosYReprobados() {
        this.poo.inscribir(this.juan);
        this.poo.inscribir(this.ana);
        this.poo.registrarCalificacion(this.juan, 80);
        this.poo.registrarCalificacion(this.ana, 60);

        assertEquals(1, SistemaCalificaciones.totalAprobados());
        assertEquals(1, SistemaCalificaciones.totalReprobados());
    }

    @Test
    public void unSistemaSinNotasTienePromedioCero() {
        this.poo.inscribir(this.juan);

        assertEquals(0.0, SistemaCalificaciones.promedioGeneral(), 0.0001);
    }

    @Test
    public void limpiarDejaElSistemaVacio() {
        SistemaCalificaciones.limpiar();

        assertEquals(0, SistemaCalificaciones.obtenerGrupos().size());
        assertEquals(0, SistemaCalificaciones.totalAlumnos());
    }
}
