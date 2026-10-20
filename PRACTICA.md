# Práctica: refactorización del sistema de calificaciones

Materia: Programación Orientada a Objetos — Ingeniería en Tecnologías de la Información.

El sistema de calificaciones de la carpeta funciona y está cubierto por pruebas
unitarias. El código, en cambio, es **código heredado**: tiene años, lo han
tocado varias personas con distintas prácticas y nadie se ha sentado a
limpiarlo. Eso es exactamente lo que van a hacer ustedes.

---

## 1. Qué hay que hacer

Refactorizar el sistema **sin cambiar su comportamiento**. La suite de pruebas
es la red de seguridad: si la rompen, el refactor no terminó.

### Reglas

1. **No se cambia el comportamiento.** Las 44 pruebas deben seguir en verde
   antes, durante y después. El formato de los reportes tampoco se toca
   (lo consume el programa de boletas).
2. **No se reescribe desde cero.** El ejercicio es|refactorizar, no
   reimplementar. Si una clase queda irreconocible, se perdió el ejercicio.
3. **Una entrega = un commit por problema corregido**, con un mensaje que
   explique qué se corrigió y por qué. Se evalúa el criterio, no el resultado.
4. **El diseño final se decide en equipo** y se justifica en una nota de una
   página al final.
5. **Las pruebas se pueden ampliar**, pero nunca weakenerse: si modifican una
   prueba existente, debe ser para agregar casos, no para acomodar el código.

### Qué se evalúa

| Criterio | Peso |
|---|---|
| Pruebas en verde durante y después del refactor | 30 % |
| Problemas detectados y corregidos | 30 % |
| Calidad del diseño resultante (encapsulamiento, cohesión, acoplamiento) | 25 % |
| Claridad del código y de los commits | 15 % |

---

## 2. Requisitos del dominio

El sistema tiene que seguir cumplir esto:

| Requisito | Cómo se comprueba |
|---|---|
| Un grupo tiene muchos alumnos y **un solo** profesor | `GruposTest.elGrupoTieneUnSoloProfesor`, `noSePuedeCrearUnGrupoSinProfesor` |
| Un mismo alumno puede estar en **varios grupos** | `GruposTest.elMismoAlumnoPuedePertenecerAVariosGruposConNotasDistintas` |
| La calificación **se almacena en el grupo**, no en el alumno | `AlumnoTest.elAlumnoNoGuardaNingunaCalificacion` |
| Una nota **menor a 70 es reprobación** (70 es aprobado) | `GruposTest.unaNotaDe70EsAprobado`, `unaNotaMenorA70EsReprobado` |
| Las notas van de 0 a 100 | `GruposTest.noSePuedeRegistrarUnaNotaNegativa`, `...MayorACien` |
| No se puede calificar a un alumno no inscrito | `GruposTest.noSePuedeCalificarAUnAlumnoNoInscrito` |
| No se puede inscribir dos veces al mismo alumno | `GruposTest.noSePuedeInscribirDosVecesAlMismoAlumno` |

Definiciones que el sistema usa (están en el javadoc, no las cambien sin
avisar en la nota final):

- `calcularPromedio()`: promedia **solo** a los alumnos que ya tienen nota.
- `tasa()`: aprobados sobre alumnos **inscritos** (los pendientes cuentan en
  el denominador).
- `promedioGeneral()`: promedio de **todas** las notas registradas; un alumno
  en dos grupos aporta sus dos notas.

---

## 3. Cómo empezar

1. `mvn test` — confirme que las 44 pruebas pasan antes de tocar nada.
2. Corra `App.main` y use la salida como referencia de lo que hay que
   conservar.
3. Lean el código buscando: nombres, responsabilidades duplicadas, números
   sueltos, estado global, decisiones escondidas en strings, métodos que
   devuelven datos internos.
4. **Anoten los problemas que encuentren antes de corregirlos.** Al final se
   entrega esa lista: es parte de la nota.
5. Refactoricen de a un problema por commit, corriendo las pruebas en cada
   paso.

Un buen primer paso: `Grupos.java` concentra casi todo. Hagan una lista de
cosas que cambia por motivos distintos y decidan en equipo cómo separarla.

---

## 4. Entrega

- Repositorio con los commits del refactor.
- Lista de problemas detectados (con ubicación) y de los que se corrigieron.
- Nota de una página: diseño final, decisiones tomadas y cosas que quedaron
  fuera y por qué.
