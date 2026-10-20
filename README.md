# Sistema de Calificaciones

Aplicacion para llevar el control de calificaciones de los grupos de la
universidad. Grupo = asignatura + seccion, con un profesor y sus alumnos.
Las notas se capturan por grupo (un alumno puede estar en varios grupos).

Java 21 / Maven / JUnit 4.

## Compilar y probar

    mvn clean package
    mvn test

El jar queda en `target/alumno-1.0-SNAPSHOT.jar`.

## Estructura

    src/main/java/com/example/
        Alumno.java
        Profesor.java
        Grupos.java
        SistemaCalificaciones.java
        ReporteGrupos.java
        App.java

    src/test/java/com/example/
        AlumnoTest.java
        ProfesorTest.java
        GruposTest.java
        SistemaCalificacionesTest.java
        ReporteGruposTest.java
        AppTest.java

`App.main` deja datos de ejemplo y los imprime por consola.

## Notas

- La base de datos se dejo de lado en 2020, ahora todo se guarda en memoria y
  se pierde al cerrar el programa.
- El formato del reporte lo consume el programa de boletas, no tocarlo sin
  avisar.
- Varios métodos devuelven las listas y mapas internos, se sabe que es feo
  pero no se ha tocado porque hasta ahora nadie lo ha necesitado.
- Las notas van de 0 a 100. Menos de 70 es reprobación.
