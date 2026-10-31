
import java.math.BigDecimal;

/*
 * CLASE: CalculadoraControlador
 *
 * RESPONSABILIDAD:
 * Controlar los eventos de los botones
 * y comunicarse con el modelo matematico.
 */

public class CalculadoraControlador {

    // Objetos de las otras clases
    private CalculadoraVista vista;

    private CalculadoraModelo modelo;

    // Variables de funcionamiento
    private BigDecimal primerNumero = BigDecimal.ZERO;

    private String operador = "";

    private boolean nuevoNumero = true;

    // Limite de 15 digitos
    private static final int MAX_DIGITOS = 15;

    // Constructor
    public CalculadoraControlador(
        CalculadoraVista vista,
        CalculadoraModelo modelo
    ) {

        this.vista = vista;

        this.modelo = modelo;

        // Asignar eventos a los botones
        this.vista.asignarEventos(
            this::procesarBoton
        );
    }

    // Procesar pulsaciones de botones
    private void procesarBoton(String texto) {

        switch (texto) {

            case "AC":
                limpiar();
                break;

            case "DEL":
                borrarUltimo();
                break;

            case "+/-":
                cambiarSigno();
                break;

            case ".":
                agregarDecimal();
                break;

            case "+":
            case "−":
            case "×":
            case "÷":
                seleccionarOperador(texto);
                break;

            case "=":
                calcular();
                break;

            case "%":
                porcentaje();
                break;

            default:
                agregarNumero(texto);
                break;
        }
    }

    // Agregar numeros
    private void agregarNumero(String numero) {

        String actual = vista.obtenerPantalla();

        if (nuevoNumero || actual.equals("0")) {

            vista.mostrarPantalla(numero);

            nuevoNumero = false;

            return;
        }

        String digitos = actual.replaceAll(
            "[^0-9]",
            ""
        );

        if (digitos.length() < MAX_DIGITOS) {

            vista.mostrarPantalla(
                actual + numero
            );
        }
    }

    // Agregar punto decimal
    private void agregarDecimal() {

        if (nuevoNumero) {

            vista.mostrarPantalla("0.");

            nuevoNumero = false;

            return;
        }

        if (!vista.obtenerPantalla().contains(".")) {

            vista.mostrarPantalla(
                vista.obtenerPantalla() + "."
            );
        }
    }

    // Seleccionar operador
    private void seleccionarOperador(String op) {

        try {

            primerNumero = new BigDecimal(
                vista.obtenerPantalla()
            );

            operador = op;

            nuevoNumero = true;

        } catch (NumberFormatException e) {

            mostrarError();
        }
    }

    // Ejecutar operaciones matematicas
    private void calcular() {

        if (operador.isEmpty()) {
            return;
        }

        try {

            BigDecimal segundoNumero =
                new BigDecimal(
                    vista.obtenerPantalla()
                );

            BigDecimal resultado;

            switch (operador) {

                case "+":

                    resultado = modelo.sumar(
                        primerNumero,
                        segundoNumero
                    );

                    break;

                case "−":

                    resultado = modelo.restar(
                        primerNumero,
                        segundoNumero
                    );

                    break;

                case "×":

                    resultado = modelo.multiplicar(
                        primerNumero,
                        segundoNumero
                    );

                    break;

                case "÷":

                    resultado = modelo.dividir(
                        primerNumero,
                        segundoNumero
                    );

                    break;

                default:
                    return;
            }

            mostrarResultado(resultado);

            operador = "";

            nuevoNumero = true;

        } catch (ArithmeticException |
                 NumberFormatException e) {

            mostrarError();
        }
    }

    // Calcular porcentaje
    private void porcentaje() {

        try {

            BigDecimal numero = new BigDecimal(
                vista.obtenerPantalla()
            );

            BigDecimal resultado =
                modelo.porcentaje(numero);

            mostrarResultado(resultado);

            nuevoNumero = true;

        } catch (NumberFormatException e) {

            mostrarError();
        }
    }

    // Cambiar signo
    private void cambiarSigno() {

        try {

            BigDecimal numero = new BigDecimal(
                vista.obtenerPantalla()
            );

            BigDecimal resultado =
                modelo.cambiarSigno(numero);

            mostrarResultado(resultado);

        } catch (NumberFormatException e) {

            mostrarError();
        }
    }

    // Borrar ultimo caracter
    private void borrarUltimo() {

        String actual = vista.obtenerPantalla();

        if (nuevoNumero || actual.startsWith("Error")) {

            limpiar();

            return;
        }

        if (actual.length() > 1) {

            actual = actual.substring(
                0,
                actual.length() - 1
            );

            if (actual.equals("-") || actual.isEmpty()) {

                actual = "0";
            }

            vista.mostrarPantalla(actual);

        } else {

            vista.mostrarPantalla("0");
        }
    }

    // Limpiar calculadora
    private void limpiar() {

        vista.mostrarPantalla("0");

        primerNumero = BigDecimal.ZERO;

        operador = "";

        nuevoNumero = true;
    }

    // Mostrar resultado
    private void mostrarResultado(
        BigDecimal resultado
    ) {

        if (resultado.compareTo(BigDecimal.ZERO) == 0) {

            vista.mostrarPantalla("0");

        } else {

            vista.mostrarPantalla(
                resultado
                .stripTrailingZeros()
                .toPlainString()
            );
        }
    }

    // Mostrar errores
    private void mostrarError() {

        vista.mostrarPantalla("Error");

        operador = "";

        nuevoNumero = true;
    }
}
