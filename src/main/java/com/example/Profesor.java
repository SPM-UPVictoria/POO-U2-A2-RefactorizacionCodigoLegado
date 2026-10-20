package com.example;

/**
 * Profesor. Los anos de experiencia se agregaron en 2021, no se usan todavia.
 */
public class Profesor {

    private String i;
    private String n;
    private String d;
    private int a;

    public Profesor(String i, String n, String d, int a) {
        this.i = i;
        this.n = n;
        this.d = d;
        this.a = a;
    }

    public String getId() {
        return i;
    }

    public void setId(String i) {
        this.i = i;
    }

    public String getNombre() {
        return n;
    }

    public void setNombre(String n) {
        this.n = n;
    }

    public String getDepartamento() {
        return d;
    }

    public void setDepartamento(String d) {
        this.d = d;
    }

    public int getAniosExperiencia() {
        return a;
    }

    public void setAniosExperiencia(int a) {
        this.a = a;
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
        Profesor b = (Profesor) o;
        if (i == null) {
            return b.i == null;
        }
        return i.equals(b.i);
    }

    @Override
    public int hashCode() {
        return i != null ? i.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Profesor[" + i + " " + n + " (" + d + ")]";
    }
}
