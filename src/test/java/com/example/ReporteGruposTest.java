package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Pruebas unitarias de {@link ReporteGrupos}.
 */
public class ReporteGruposTest {

    private Grupos poo;
    private Grupos bd;
    private Alumno juan;
    private Alumno ana;
    private List<Grupos> grupos;

    @Before
    public void setUp() {
        this.poo = new Grupos("Programacion Orientada a Objetos", "POO-1",
                new Profesor("P001", "Laura Gomez", "Ingenieria de Software", 12));
        this.bd = new Grupos("Base de Datos", "BD-1",
                new Profesor("P002", "Carlos Ruiz", "Ingenieria de Software", 8));
        this.juan = new Alumno("E1001", "Juan Perez", 20, "Ingenieria de Software");
        this.ana = new Alumno("E1002", "Ana Ruiz", 22, "Ingenieria de Software");
        this.grupos = Arrays.asList(this.poo, this.bd);

        this.poo.inscribir(this.juan);
        this.poo.inscribir(this.ana);
        this.bd.inscribir(this.juan);
        this.poo.registrarCalificacion(this.juan, 80);
        this.poo.registrarCalificacion(this.ana, 60);
        this.bd.registrarCalificacion(this.juan, 90);
    }

    @Test
    public void elReporteCuentaLosAlumnosDeTodosLosGrupos() {
        ReporteGrupos reporte = new ReporteGrupos(this.grupos);

        assertEquals(3, reporte.totalAlumnos());
    }

    @Test
    public void losConteosDelReporteCoincidenConLosDeLosGrupos() {
        ReporteGrupos reporte = new ReporteGrupos(this.grupos);

        assertEquals(this.poo.obtenerAprobados().size() + this.bd.obtenerAprobados().size(), reporte.totalAprobados());
        assertEquals(this.poo.obtenerReprobados().size() + this.bd.obtenerReprobados().size(), reporte.totalReprobados());
    }

    @Test
    public void elReporteCalculaElPromedioGeneral() {
        ReporteGrupos reporte = new ReporteGrupos(this.grupos);

        assertEquals(230.0 / 3.0, reporte.promedioGeneral(), 0.0001);
    }

    @Test
    public void elReporteContieneLaInformacionDeCadaGrupo() {
        ReporteGrupos reporte = new ReporteGrupos(this.grupos);

        String texto = reporte.generarReporte();

        assertTrue(texto.contains("Grupos analizados: 2"));
        assertTrue(texto.contains("Total alumnos: 3"));
        assertTrue(texto.contains("Laura Gomez"));
        assertTrue(texto.contains("Carlos Ruiz"));
    }
}
