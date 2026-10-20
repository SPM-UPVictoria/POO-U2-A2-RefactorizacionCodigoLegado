package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Guarda los grupos del sistema. Antes esto venia de la base de datos, ahora
 * queda todo en memoria y se pierde al cerrar.
 */
public class SistemaCalificaciones {

    private static List<Grupos> g = new ArrayList<Grupos>();
    private static int k = 0;

    public static Grupos crearGrupo(String n, String s, String i, String m, String d, int e) {
        Profesor p = new Profesor(i, m, d, e);
        Grupos g1 = new Grupos(n, s, p);
        g.add(g1);
        k = k + 1;
        return g1;
    }

    public static void add(Grupos x) {
        g.add(x);
    }

    public static List<Grupos> obtenerGrupos() {
        return g;
    }

    public static int totalAlumnos() {
        int t = 0;
        for (int i = 0; i < g.size(); i++) {
            t = t + g.get(i).getAlumnos().size();
        }
        return t;
    }

    public static int totalAprobados() {
        int t = 0;
        for (int i = 0; i < g.size(); i++) {
            t = t + g.get(i).obtenerAprobados().size();
        }
        return t;
    }

    public static int totalReprobados() {
        int t = 0;
        for (int i = 0; i < g.size(); i++) {
            t = t + g.get(i).obtenerReprobados().size();
        }
        return t;
    }

    public static double promedioGeneral() {
        double s = 0;
        int c = 0;
        for (int i = 0; i < g.size(); i++) {
            for (int j = 0; j < g.get(i).getAlumnos().size(); j++) {
                Double v = g.get(i).getNotas().get(g.get(i).getAlumnos().get(j).getCarnet());
                if (v != null) {
                    s = s + v.doubleValue();
                    c = c + 1;
                }
            }
        }
        if (c == 0) {
            return 0;
        }
        return s / c;
    }

    public static void limpiar() {
        g.clear();
        k = 0;
    }
}
