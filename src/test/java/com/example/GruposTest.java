package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

/**
 * Pruebas unitarias de {@link Grupos}.
 */
public class GruposTest {

    private Grupos grupo;
    private Profesor profesor;
    private Alumno juan;
    private Alumno ana;
    private Alumno luis;

    @Before
    public void setUp() {
        this.profesor = new Profesor("P001", "Laura Gomez", "Ingenieria de Software", 12);
        this.grupo = new Grupos("Programacion Orientada a Objetos", "POO-1", this.profesor);
        this.juan = new Alumno("E1001", "Juan Perez", 20, "Ingenieria de Software");
        this.ana = new Alumno("E1002", "Ana Ruiz", 22, "Ingenieria de Software");
        this.luis = new Alumno("E1003", "Luis Soto", 19, "Ingenieria de Software");
    }

    @Test
    public void elGrupoArrancaVacio() {
        assertEquals(0, this.grupo.getAlumnos().size());
        assertEquals(0, this.grupo.totalCalificados());
        assertEquals(0.0, this.grupo.calcularPromedio(), 0.0001);
    }

    @Test
    public void elGrupoTieneUnSoloProfesor() {
        assertNotNull(this.grupo.getProfesor());
        assertEquals("P001", this.grupo.getProfesor().getId());
        assertEquals("Laura Gomez", this.grupo.getProfesor().getNombre());
    }

    @Test(expected = IllegalArgumentException.class)
    public void noSePuedeCrearUnGrupoSinProfesor() {
        new Grupos("Programacion Orientada a Objetos", "POO-1", null);
    }

    @Test
    public void inscribirAgregaAlAlumnoAlGrupo() {
        this.grupo.inscribir(this.juan);

        assertEquals(1, this.grupo.getAlumnos().size());
        assertTrue(this.grupo.getAlumnos().contains(this.juan));
    }

    @Test(expected = IllegalStateException.class)
    public void noSePuedeInscribirDosVecesAlMismoAlumno() {
        this.grupo.inscribir(this.juan);
        this.grupo.inscribir(new Alumno("E1001", "Juan Perez Soto", 20, "Ingenieria de Software"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void noSePuedeInscribirUnAlumnoSinNombre() {
        this.grupo.inscribir(new Alumno("E1009", "   ", 20, "Ingenieria de Software"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void noSePuedeInscribirUnAlumnoSinCarnet() {
        this.grupo.inscribir(new Alumno(null, "Maria Lopez", 20, "Ingenieria de Software"));
    }

    @Test
    public void laCalificacionSeGuardaEnElGrupo() {
        this.grupo.inscribir(this.juan);
        this.grupo.registrarCalificacion(this.juan, 85);

        assertEquals(Double.valueOf(85.0), this.grupo.obtenerNota(this.juan));
        assertTrue(this.grupo.getNotas().containsKey("E1001"));
    }

    @Test
    public void unAlumnoSinNotaDevuelveNull() {
        this.grupo.inscribir(this.juan);

        assertNull(this.grupo.obtenerNota(this.juan));
    }

    @Test(expected = IllegalStateException.class)
    public void noSePuedeCalificarAUnAlumnoNoInscrito() {
        this.grupo.registrarCalificacion(this.juan, 85);
    }

    @Test(expected = IllegalArgumentException.class)
    public void noSePuedeRegistrarUnaNotaNegativa() {
        this.grupo.inscribir(this.juan);
        this.grupo.registrarCalificacion(this.juan, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void noSePuedeRegistrarUnaNotaMayorACien() {
        this.grupo.inscribir(this.juan);
        this.grupo.registrarCalificacion(this.juan, 101);
    }

    @Test
    public void unaNotaDe70EsAprobado() {
        this.grupo.inscribir(this.juan);
        this.grupo.registrarCalificacion(this.juan, 70);

        assertEquals("APROBADO", this.grupo.estadoAlumno(this.juan));
    }

    @Test
    public void unaNotaMenorA70EsReprobado() {
        this.grupo.inscribir(this.juan);
        this.grupo.registrarCalificacion(this.juan, 69.9);

        assertEquals("REPROBADO", this.grupo.estadoAlumno(this.juan));
    }

    @Test
    public void unAlumnoSinNotaEstaPendiente() {
        this.grupo.inscribir(this.juan);

        assertEquals("PENDIENTE", this.grupo.estadoAlumno(this.juan));
    }

    @Test
    public void lasListasDeAprobadosReprobadosYPendientesCubrenLosInscritos() {
        this.grupo.inscribir(this.juan);
        this.grupo.inscribir(this.ana);
        this.grupo.inscribir(this.luis);
        this.grupo.registrarCalificacion(this.juan, 80);
        this.grupo.registrarCalificacion(this.ana, 60);

        assertEquals(1, this.grupo.obtenerAprobados().size());
        assertEquals(1, this.grupo.obtenerReprobados().size());
        assertEquals(1, this.grupo.obtenerPendientes().size());
        assertTrue(this.grupo.obtenerAprobados().contains(this.juan));
        assertTrue(this.grupo.obtenerReprobados().contains(this.ana));
        assertTrue(this.grupo.obtenerPendientes().contains(this.luis));
    }

    @Test
    public void elPromedioSoloCuentaLosAlumnosCalificados() {
        this.grupo.inscribir(this.juan);
        this.grupo.inscribir(this.ana);
        this.grupo.inscribir(this.luis);
        this.grupo.registrarCalificacion(this.juan, 80);
        this.grupo.registrarCalificacion(this.ana, 60);

        assertEquals(70.0, this.grupo.calcularPromedio(), 0.0001);
    }

    @Test
    public void elMismoAlumnoPuedePertenecerAVariosGruposConNotasDistintas() {
        Grupos otro = new Grupos("Base de Datos", "BD-1", new Profesor("P002", "Carlos Ruiz", "Ingenieria de Software", 8));
        this.grupo.inscribir(this.juan);
        otro.inscribir(this.juan);

        this.grupo.registrarCalificacion(this.juan, 70);
        otro.registrarCalificacion(this.juan, 90);

        assertEquals(Double.valueOf(70.0), this.grupo.obtenerNota(this.juan));
        assertEquals(Double.valueOf(90.0), otro.obtenerNota(this.juan));
    }

    @Test
    public void laTasaDeAprobacionSeCalculaSobreLosAlumnosInscritos() {
        this.grupo.inscribir(this.juan);
        this.grupo.inscribir(this.ana);
        this.grupo.registrarCalificacion(this.juan, 80);

        assertEquals(50.0, this.grupo.tasa(), 0.0001);
    }

    @Test
    public void laTasaDeAprobacionDeUnGrupoVacioEsCero() {
        assertEquals(0.0, this.grupo.tasa(), 0.0001);
        assertEquals(0, this.grupo.totalAlumnos());
    }

    @Test
    public void elReporteMuestraLaInformacionDelGrupo() {
        this.grupo.inscribir(this.juan);
        this.grupo.inscribir(this.ana);
        this.grupo.registrarCalificacion(this.juan, 80);
        this.grupo.registrarCalificacion(this.ana, 60);

        String salida = capturarSalida(new Runnable() {
            public void run() {
                grupo.reporte();
            }
        });

        assertTrue(salida.contains("Programacion Orientada a Objetos"));
        assertTrue(salida.contains("APROBADOS (1)"));
        assertTrue(salida.contains("REPROBADOS (1)"));
        assertTrue(salida.contains("Juan Perez"));
        assertTrue(salida.contains("Ana Ruiz"));
    }

    private String capturarSalida(Runnable accion) {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        try {
            accion.run();
        } finally {
            System.setOut(original);
        }
        return buffer.toString();
    }
}
