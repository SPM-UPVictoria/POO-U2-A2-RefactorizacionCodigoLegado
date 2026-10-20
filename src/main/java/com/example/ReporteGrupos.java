package com.example;

import java.util.List;

/**
 * Reporte general de todos los grupos.
 */
public class ReporteGrupos {

    private List<Grupos> g;

    public ReporteGrupos(List<Grupos> g) {
        this.g = g;
    }

    public int totalAlumnos() {
        int t = 0;
        for (int i = 0; i < g.size(); i++) {
            t = t + g.get(i).getAlumnos().size();
        }
        return t;
    }

    public int totalAprobados() {
        int t = 0;
        for (int i = 0; i < g.size(); i++) {
            for (int j = 0; j < g.get(i).getAlumnos().size(); j++) {
                Double v = g.get(i).getNotas().get(g.get(i).getAlumnos().get(j).getCarnet());
                if (v != null && v.doubleValue() >= 70) {
                    t = t + 1;
                }
            }
        }
        return t;
    }

    public int totalReprobados() {
        int t = 0;
        for (int i = 0; i < g.size(); i++) {
            for (int j = 0; j < g.get(i).getAlumnos().size(); j++) {
                Double v = g.get(i).getNotas().get(g.get(i).getAlumnos().get(j).getCarnet());
                if (v != null && v.doubleValue() < 70) {
                    t = t + 1;
                }
            }
        }
        return t;
    }

    public double promedioGeneral() {
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

    public String generarReporte() {
        StringBuffer sb = new StringBuffer();
        sb.append("REPORTE GENERAL DE CALIFICACIONES\n");
        sb.append("Grupos analizados: ").append(g.size()).append("\n");
        sb.append("Total alumnos: ").append(totalAlumnos()).append("\n");
        sb.append("Total aprobados: ").append(totalAprobados()).append("\n");
        sb.append("Total reprobados: ").append(totalReprobados()).append("\n");
        sb.append("Promedio general: ").append(promedioGeneral()).append("\n");
        for (int i = 0; i < g.size(); i++) {
            sb.append(" - ").append(g.get(i).getNombre()).append(" (").append(g.get(i).getSeccion()).append("): ")
              .append(g.get(i).getProfesor().getNombre()).append("\n");
        }
        return sb.toString();
    }

    public void show() {
        System.out.println(generarReporte());
    }
}
