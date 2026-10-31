
import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/*
 * CLASE: CalculadoraVista
 *
 * RESPONSABILIDAD:
 * Crear y mostrar la interfaz grafica.
 */

public class CalculadoraVista extends JFrame {

    // Pantalla de la calculadora
    private JTextField pantalla;

    // Constructor
    public CalculadoraVista() {

        configurarVentana();

        crearComponentes();

    }

    // Configurar ventana principal
    private void configurarVentana() {

        setTitle("Calculadora Java");

        setSize(360, 510);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);
    }

    // Crear todos los componentes
    private void crearComponentes() {

        JPanel panelPrincipal = new JPanel();

        panelPrincipal.setLayout(
            new BorderLayout(10, 10)
        );

        panelPrincipal.setBackground(
            new Color(35, 35, 35)
        );

        panelPrincipal.setBorder(
            BorderFactory.createEmptyBorder(
                15, 15, 15, 15
            )
        );

        crearPantalla(panelPrincipal);

        crearBotones(panelPrincipal);

        add(panelPrincipal);
    }

    // Crear pantalla
    private void crearPantalla(JPanel panelPrincipal) {

        pantalla = new JTextField("0");

        pantalla.setEditable(false);

        pantalla.setHorizontalAlignment(
            JTextField.RIGHT
        );

        pantalla.setFont(
            new Font("Arial", Font.BOLD, 30)
        );

        pantalla.setBackground(
            new Color(225, 235, 225)
        );

        pantalla.setForeground(Color.BLACK);

        pantalla.setBorder(
            BorderFactory.createEmptyBorder(
                15, 10, 15, 10
            )
        );

        panelPrincipal.add(
            pantalla,
            BorderLayout.NORTH
        );
    }

    // Crear panel y botones
    private void crearBotones(JPanel panelPrincipal) {

        JPanel panelBotones = new JPanel();

        panelBotones.setLayout(
            new GridLayout(5, 4, 8, 8)
        );

        panelBotones.setBackground(
            new Color(35, 35, 35)
        );

        String[] botones = {

            "AC", "DEL", "%", "÷",

            "7", "8", "9", "×",

            "4", "5", "6", "−",

            "1", "2", "3", "+",

            "+/-", "0", ".", "="
        };

        // Crear los botones
        for (String texto : botones) {

            JButton boton = new JButton(texto);

            boton.setFont(
                new Font("Arial", Font.BOLD, 21)
            );

            boton.setFocusPainted(false);

            boton.setForeground(Color.WHITE);

            configurarColorBoton(boton, texto);

            panelBotones.add(boton);
        }

        panelPrincipal.add(
            panelBotones,
            BorderLayout.CENTER
        );
    }

    // Configurar colores de los botones
    private void configurarColorBoton(
        JButton boton,
        String texto
    ) {

        if (texto.equals("=")) {

            boton.setBackground(
                new Color(30, 145, 75)
            );

        } else if (
            texto.equals("+") ||
            texto.equals("−") ||
            texto.equals("×") ||
            texto.equals("÷") ||
            texto.equals("%")
        ) {

            boton.setBackground(
                new Color(230, 135, 30)
            );

        } else if (
            texto.equals("AC") ||
            texto.equals("DEL") ||
            texto.equals("+/-")
        ) {

            boton.setBackground(
                new Color(90, 90, 90)
            );

        } else {

            boton.setBackground(
                new Color(65, 65, 65)
            );
        }
    }

    // Conectar los botones con el controlador
    public void asignarEventos(Consumer<String> accion) {

        Component[] componentes =
            getContentPane()
            .getComponents();

        JPanel panelPrincipal =
            (JPanel) componentes[0];

        JPanel panelBotones =
            (JPanel) panelPrincipal
            .getComponent(1);

        for (Component componente :
             panelBotones.getComponents()) {

            JButton boton = (JButton) componente;

            String texto = boton.getText();

            boton.addActionListener(
                e -> accion.accept(texto)
            );
        }
    }

    // Obtener el contenido de la pantalla
    public String obtenerPantalla() {

        return pantalla.getText();
    }

    // Mostrar un valor en la pantalla
    public void mostrarPantalla(String valor) {

        pantalla.setText(valor);
    }
}

    

