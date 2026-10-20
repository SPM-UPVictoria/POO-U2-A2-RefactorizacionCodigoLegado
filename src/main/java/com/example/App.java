package com.example;

/**
 * Ejemplo de uso del sistema.
 */
public class App {

    public static void main(String[] args) {
        Grupos g1 = SistemaCalificaciones.crearGrupo("Programacion Orientada a Objetos", "POO-1",
                "P001", "Laura Gomez", "Ingenieria de Software", 12);
        Grupos g2 = SistemaCalificaciones.crearGrupo("Base de Datos", "BD-1",
                "P002", "Carlos Ruiz", "Ingenieria de Software", 8);

        Alumno a1 = new Alumno("E1001", "Juan Perez", 20, "Ingenieria de Software");
        Alumno a2 = new Alumno("E1002", "Ana Ruiz", 22, "Ingenieria de Software");
        Alumno a3 = new Alumno("E1003", "Luis Soto", 19, "Ingenieria de Software");

        g1.inscribir(a1);
        g1.inscribir(a2);
        g1.inscribir(a3);
        g2.inscribir(a1);

        g1.registrarCalificacion(a1, 85);
        g1.registrarCalificacion(a2, 65);
        g2.registrarCalificacion(a1, 90);

        g1.reporte();
        g2.reporte();

        ReporteGrupos r = new ReporteGrupos(SistemaCalificaciones.obtenerGrupos());
        r.show();
    }
}
