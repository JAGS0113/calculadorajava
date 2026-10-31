

import java.math.BigDecimal;
import java.math.RoundingMode;

/*
 * CLASE: CalculadoraModelo
 *
 * RESPONSABILIDAD:
 * Realizar todas las operaciones matematicas.
 *
 * Esta clase no contiene interfaz grafica.
 */

public class CalculadoraModelo {

    // Metodo para sumar
    public BigDecimal sumar(
        BigDecimal numero1,
        BigDecimal numero2
    ) {
        return numero1.add(numero2);
    }

    // Metodo para restar
    public BigDecimal restar(
        BigDecimal numero1,
        BigDecimal numero2
    ) {
        return numero1.subtract(numero2);
    }

    // Metodo para multiplicar
    public BigDecimal multiplicar(
        BigDecimal numero1,
        BigDecimal numero2
    ) {
        return numero1.multiply(numero2);
    }

    // Metodo para dividir
    public BigDecimal dividir(
        BigDecimal numero1,
        BigDecimal numero2
    ) {

        if (numero2.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException(
                "Division por cero"
            );
        }

        return numero1.divide(
            numero2,
            12,
            RoundingMode.HALF_UP
        );
    }

    // Metodo para calcular porcentaje
    public BigDecimal porcentaje(BigDecimal numero) {

        return numero.divide(
            BigDecimal.valueOf(100),
            12,
            RoundingMode.HALF_UP
        );
    }

    // Metodo para cambiar el signo
    public BigDecimal cambiarSigno(BigDecimal numero) {

        return numero.negate();
    }
}
