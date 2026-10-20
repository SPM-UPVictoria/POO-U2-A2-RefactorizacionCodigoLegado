package com.example;

/**
 * Alumno. Se le agrego la carrera en 03/2019.
 */
public class Alumno {

    private String c;
    private String n;
    private int e;
    private String r;

    public Alumno(String c, String n, int e, String r) {
        this.c = c;
        this.n = n;
        this.e = e;
        this.r = r;
    }

    public String getCarnet() {
        return c;
    }

    public void setCarnet(String c) {
        this.c = c;
    }

    public String getNombre() {
        return n;
    }

    public void setNombre(String n) {
        this.n = n;
    }

    public int getEdad() {
        return e;
    }

    public void setEdad(int e) {
        this.e = e;
    }

    public String getCarrera() {
        return r;
    }

    public void setCarrera(String r) {
        this.r = r;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Alumno b = (Alumno) o;
        if (c == null) {
            return b.c == null;
        }
        return c.equals(b.c);
    }

    @Override
    public int hashCode() {
        return c != null ? c.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Alumno[" + c + " " + n + "]";
    }
}
