package com.example;

import org.junit.After;
import org.junit.Test;

/**
 * Pruebas del punto de entrada de la aplicacion.
 */
public class AppTest {

    @After
    public void tearDown() {
        SistemaCalificaciones.limpiar();
    }

    @Test
    public void mainSeEjecutaSinErrores() {
        App.main(new String[]{});
    }
}
