import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.regex.*;
import javax.swing.*;

/**
 * Interfaz gráfica del proyecto Wichita 6.
 * Permite introducir un nombre y un correo electrónico,
 * almacenarlos en una lista y mostrarlos por consola.
 * 
 * Proyecto educativo de programación con Java Swing.
 * 
 * @author 
 * Iván Escanciano Blanco
 * 
 * @version 
 * 1.0
 */
public class Wichita6 extends JFrame {

    /** Campo de texto para introducir el nombre del contacto. */
    public JTextField campoNombre;

    /** Campo de texto para introducir el correo electrónico del contacto. */
    public JTextField campoEmail;

    /** Botón para añadir el contacto a la lista. */
    public JButton botonEnviar;

    /** Botón para mostrar los contactos en consola. */
    public JButton botonGuardar;

    /** Lista de contactos guardados en el formato "nombre - email". */
    public ArrayList<String> contactos;

    /**
     * Constructor principal de la interfaz gráfica.
     * Configura la ventana, los campos de texto, botones y listeners.
     */
    public Wichita6() {
        setTitle("Interfaz gráfica - Wichita 6 - Iván Escanciano");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 270);
        setLocationRelativeTo(null); 

        contactos = new ArrayList<>();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        panel.add(new JLabel("Nombre y apellidos:"));
        campoNombre = new JTextField();
        panel.add(campoNombre);

        panel.add(new JLabel("Correo electrónico:"));
        campoEmail = new JTextField();
        panel.add(campoEmail);

        botonEnviar = new JButton("Añadir en la lista");
        panel.add(botonEnviar);

        botonGuardar = new JButton("Guardar contactos en consola");
        panel.add(botonGuardar);

        panel.add(new JLabel("<html>*Añadir para guardar en la lista.<br>*Guardar para mostrar en consola.</html>"));

        botonEnviar.addActionListener(new ActionListener() {
            /**
             * Listener del botón "Añadir en la lista".
             * Llama al método agregarContacto().
             * 
             * @param e Evento de acción del botón.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarContacto();
            }
        });

        botonGuardar.addActionListener(new ActionListener() {
            /**
             * Listener del botón "Guardar contactos".
             * Llama al método mostrarContactos().
             * 
             * @param e Evento de acción del botón.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarContactos();
            }
        });

        add(panel);
    }

    /**
     * Añade un contacto a la lista de contactos si los datos son válidos.
     * Valida que los campos no estén vacíos y que el correo tenga un formato correcto.
     */
    public void agregarContacto() {
        String nombre = campoNombre.getText().trim();
        String email = campoEmail.getText().trim();

        if (nombre.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa ambos campos.",
                    "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String regex = ".*@.*\\..*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(this, "Correo electrónico incorrecto. Debe contener al menos un '@' y un '.'",
                    "Formato no válido", JOptionPane.ERROR_MESSAGE);
            return;
        }

        contactos.add(nombre + " - " + email);
        JOptionPane.showMessageDialog(this, "Contacto añadido a la lista.",
                "Añadido", JOptionPane.INFORMATION_MESSAGE);
        campoNombre.setText("");
        campoEmail.setText("");
    }

    /**
     * Muestra todos los contactos almacenados en consola.
     * Si no hay contactos, informa al usuario mediante un cuadro de diálogo.
     */
    public void mostrarContactos() {
        if (contactos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay contactos en la lista.",
                    "Lista vacía", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        System.out.println("\n--- Contactos guardados en consola ---");
        for (String contacto : contactos) {
            System.out.println(contacto);
        }
        JOptionPane.showMessageDialog(this, "Contactos mostrados en consola.",
                "Guardado completo", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Método principal que lanza la interfaz gráfica.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Wichita6 ventana = new Wichita6();
            ventana.setVisible(true);
        });
    }
}
