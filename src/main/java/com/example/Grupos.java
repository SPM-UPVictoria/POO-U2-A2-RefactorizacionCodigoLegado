package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Grupo de una asignatura. Al principio solo guardaba alumnos, despues se le
 * fueron metiendo cosas (notas, promedios, reportes).
 */
public class Grupos {

    private String n;
    private String s;
    private Profesor p;
    private List<Alumno> a;
    private Map<String, Double> c;

    public Grupos(String n, String s, Profesor p) {
        this.n = n;
        this.s = s;
        this.p = p;
        this.a = new ArrayList<Alumno>();
        this.c = new HashMap<String, Double>();

        if (p == null) {
            throw new IllegalArgumentException("El grupo necesita un profesor");
        }
    }

    public void inscribir(Alumno al) {
        if (al == null) {
            throw new IllegalArgumentException("El alumno no puede ser null");
        }
        if (al.getCarnet() == null || al.getCarnet().trim().isEmpty()) {
            throw new IllegalArgumentException("El alumno necesita un carnet");
        }
        if (al.getNombre() == null || al.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El alumno necesita un nombre");
        }

        for (int i = 0; i < this.a.size(); i++) {
            if (this.a.get(i).getCarnet().equals(al.getCarnet())) {
                throw new IllegalStateException("El alumno " + al.getCarnet() + " ya esta inscrito en el grupo");
            }
        }

        this.a.add(al);
    }

    public void registrarCalificacion(Alumno al, double v) {
        if (al == null) {
            throw new IllegalArgumentException("El alumno no puede ser null");
        }
        if (al.getCarnet() == null || al.getCarnet().trim().isEmpty()) {
            throw new IllegalArgumentException("El alumno necesita un carnet");
        }
        if (Double.isNaN(v) || v < 0 || v > 100) {
            throw new IllegalArgumentException("La nota debe estar entre 0 y 100");
        }

        boolean f = false;
        for (int i = 0; i < this.a.size(); i++) {
            if (this.a.get(i).getCarnet().equals(al.getCarnet())) {
                f = true;
                break;
            }
        }
        if (!f) {
            throw new IllegalStateException("El alumno " + al.getCarnet() + " no esta inscrito en el grupo " + this.n);
        }

        this.c.put(al.getCarnet(), Double.valueOf(v));
    }

    // TODO: revisar que pasa cuando el alumno todavia no tiene nota
    public Double obtenerNota(Alumno al) {
        if (al == null) {
            return null;
        }
        return this.c.get(al.getCarnet());
    }

    public String estadoAlumno(Alumno al) {
        Double v = this.c.get(al.getCarnet());
        if (v == null) {
            return "PENDIENTE";
        }
        if (v.doubleValue() >= 70) {
            return "APROBADO";
        } else {
            return "REPROBADO";
        }
    }

    public List<Alumno> obtenerAprobados() {
        List<Alumno> l = new ArrayList<Alumno>();
        for (int i = 0; i < this.a.size(); i++) {
            Double v = this.c.get(this.a.get(i).getCarnet());
            if (v != null && v.doubleValue() >= 70) {
                l.add(this.a.get(i));
            }
        }
        return l;
    }

    public List<Alumno> obtenerReprobados() {
        List<Alumno> l = new ArrayList<Alumno>();
        for (int i = 0; i < this.a.size(); i++) {
            Double v = this.c.get(this.a.get(i).getCarnet());
            if (v != null && v.doubleValue() < 70) {
                l.add(this.a.get(i));
            }
        }
        return l;
    }

    public List<Alumno> obtenerPendientes() {
        List<Alumno> l = new ArrayList<Alumno>();
        for (int i = 0; i < this.a.size(); i++) {
            if (!this.c.containsKey(this.a.get(i).getCarnet())) {
                l.add(this.a.get(i));
            }
        }
        return l;
    }

    public double calcularPromedio() {
        double s = 0;
        int k = 0;
        for (int i = 0; i < this.a.size(); i++) {
            Double v = this.c.get(this.a.get(i).getCarnet());
            if (v != null) {
                s = s + v.doubleValue();
                k = k + 1;
            }
        }
        if (k == 0) {
            return 0;
        }
        return s / k;
    }

    public double tasa() {
        if (this.a.isEmpty()) {
            return 0;
        }
        return (obtenerAprobados().size() * 100.0) / this.a.size();
    }

    public int totalAlumnos() {
        return this.a.size();
    }

    public int totalCalificados() {
        return this.c.size();
    }

    // El formato lo usa el programa de boletas, cambiar con cuidado
    public void reporte() {
        StringBuffer sb = new StringBuffer();
        sb.append("=========================================\n");
        sb.append("Grupo: ").append(this.n).append(" (").append(this.s).append(")\n");
        sb.append("Profesor: ").append(this.p.toString()).append("\n");
        sb.append("Alumnos inscritos: ").append(this.a.size()).append("\n");
        sb.append("Alumnos calificados: ").append(this.c.size()).append("\n");
        sb.append("Promedio: ").append(calcularPromedio()).append("\n");
        sb.append("-----------------------------------------\n");

        sb.append("APROBADOS (").append(obtenerAprobados().size()).append("):\n");
        for (int i = 0; i < obtenerAprobados().size(); i++) {
            sb.append("  - ").append(obtenerAprobados().get(i).toString()).append("\n");
        }

        sb.append("REPROBADOS (").append(obtenerReprobados().size()).append("):\n");
        for (int i = 0; i < obtenerReprobados().size(); i++) {
            sb.append("  - ").append(obtenerReprobados().get(i).toString()).append("\n");
        }

        sb.append("PENDIENTES (").append(obtenerPendientes().size()).append("):\n");
        for (int i = 0; i < obtenerPendientes().size(); i++) {
            sb.append("  - ").append(obtenerPendientes().get(i).toString()).append("\n");
        }

        sb.append("=========================================\n");
        System.out.println(sb.toString());
    }

    public String getNombre() {
        return this.n;
    }

    public String getSeccion() {
        return this.s;
    }

    public Profesor getProfesor() {
        return this.p;
    }

    public List<Alumno> getAlumnos() {
        return this.a;
    }

    public Map<String, Double> getNotas() {
        return this.c;
    }
}
