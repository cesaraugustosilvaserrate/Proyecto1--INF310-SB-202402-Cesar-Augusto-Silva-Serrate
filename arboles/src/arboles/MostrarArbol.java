package arboles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MostrarArbol extends JFrame {

    private ArbolBinario<Integer> arbol;  // Árbol binario
    private JTextField txtDato;          // Campo para introducir datos
    private JTextArea txtRecorridos;     // Área de texto para mostrar recorridos

    public MostrarArbol() {
        arbol = new ArbolBinario<>();
        setTitle("Árbol Binario de Búsqueda");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        // Panel de entrada y botones
        JPanel panelEntrada = new JPanel();
        panelEntrada.setLayout(new FlowLayout());

        JLabel lblDato = new JLabel("Introduce un número:");
        panelEntrada.add(lblDato);

        txtDato = new JTextField(10);
        panelEntrada.add(txtDato);

        JButton btnInsertar = new JButton("Insertar");
        panelEntrada.add(btnInsertar);

        JButton btnEliminar = new JButton("Eliminar");
        panelEntrada.add(btnEliminar);

        JButton btnBuscar = new JButton("Buscar");
        panelEntrada.add(btnBuscar);

        // Botones de recorrido
        JPanel panelRecorridos = new JPanel();
        panelRecorridos.setLayout(new FlowLayout());

        JButton btnInOrden = new JButton("In-Orden");
        panelRecorridos.add(btnInOrden);

        JButton btnPreOrden = new JButton("Pre-Orden");
        panelRecorridos.add(btnPreOrden);

        JButton btnPostOrden = new JButton("Post-Orden");
        panelRecorridos.add(btnPostOrden);

        JButton btnPorNiveles = new JButton("Por Niveles");
        panelRecorridos.add(btnPorNiveles);

        // Área de texto para mostrar los recorridos
        txtRecorridos = new JTextArea(10, 30);
        txtRecorridos.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtRecorridos);

        // Añadir todo al panel principal
        panel.add(panelEntrada, BorderLayout.NORTH);
        panel.add(panelRecorridos, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.SOUTH);

        // Añadir panel principal a la ventana
        add(panel);

        // Definir las acciones de los botones
        btnInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertarDato();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarDato();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarDato();
            }
        });

        btnInOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarRecorridoInOrden();
            }
        });

        btnPreOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarRecorridoPreOrden();
            }
        });

        btnPostOrden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarRecorridoPostOrden();
            }
        });

        btnPorNiveles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarRecorridoPorNiveles();
            }
        });
    }

    // Métodos para insertar, eliminar y buscar datos en el árbol
    private void insertarDato() {
        try {
            int dato = Integer.parseInt(txtDato.getText());
            arbol.insertar(dato);
            txtDato.setText("");
            mostrarMensaje("El dato ha sido insertado.");
        } catch (NumberFormatException e) {
            mostrarMensaje("Por favor, introduce un número válido.");
        }
    }

    private void eliminarDato() {
        try {
            int dato = Integer.parseInt(txtDato.getText());
            boolean eliminado = arbol.eliminar(dato);
            if (eliminado) {
                mostrarMensaje("El dato ha sido eliminado.");
            } else {
                mostrarMensaje("El dato no se encontró en el árbol.");
            }
            txtDato.setText("");
        } catch (NumberFormatException e) {
            mostrarMensaje("Por favor, introduce un número válido.");
        }
    }

    private void buscarDato() {
        try {
            int dato = Integer.parseInt(txtDato.getText());
            boolean encontrado = arbol.buscar(dato);
            if (encontrado) {
                mostrarMensaje("El dato se encuentra en el árbol.");
            } else {
                mostrarMensaje("El dato no se encuentra en el árbol.");
            }
        } catch (NumberFormatException e) {
            mostrarMensaje("Por favor, introduce un número válido.");
        }
    }

    private void mostrarRecorridoInOrden() {
        txtRecorridos.setText("In-Orden: " + arbol.recorridoEnInOrden());
    }

    private void mostrarRecorridoPreOrden() {
        txtRecorridos.setText("Pre-Orden: " + arbol.recorridoEnPreOrden());
    }

    private void mostrarRecorridoPostOrden() {
        txtRecorridos.setText("Post-Orden: " + arbol.recorridoEnPostOrden());
    }

    private void mostrarRecorridoPorNiveles() {
        txtRecorridos.setText("Por Niveles: " + arbol.recorridoPorNiveles());
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    // Método principal
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MostrarArbol().setVisible(true);
            }
        });
    }
}
