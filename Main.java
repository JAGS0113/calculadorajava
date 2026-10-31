
import javax.swing.SwingUtilities;

/*
 * CLASE: Main
 *
 * RESPONSABILIDAD:
 * Punto de entrada de la aplicacion.
 */

public class Main {

    // Metodo principal
    public static void main(String[] args) {

        // Ejecutar interfaz en el hilo de Swing
        SwingUtilities.invokeLater(() -> {

            // Crear objeto de la vista
            CalculadoraVista vista =
                new CalculadoraVista();

            // Crear objeto del modelo
            CalculadoraModelo modelo =
                new CalculadoraModelo();

            // Crear objeto del controlador
            CalculadoraControlador controlador =
                new CalculadoraControlador(
                    vista,
                    modelo
                );

            // Mostrar calculadora
            vista.setVisible(true);

        });
    }
}
