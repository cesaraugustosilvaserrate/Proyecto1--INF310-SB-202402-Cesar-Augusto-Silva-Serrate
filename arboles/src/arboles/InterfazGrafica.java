/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arboles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class InterfazGrafica extends JFrame {
    private ArbolAVL<Producto> arbol;
    private JTextArea textArea;
    private JTextField idField, nombreField, precioField;

    public InterfazGrafica() {
        arbol = new ArbolAVL<>();  // Crear una nueva instancia del árbol AVL
        
        // Configuración de la ventana
        setTitle("Gestión de Productos");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        // Layout
        setLayout(new BorderLayout());

        // Panel para la entrada de datos
        JPanel panelInputs = new JPanel();
        panelInputs.setLayout(new GridLayout(4, 2));

        panelInputs.add(new JLabel("ID:"));
        idField = new JTextField();
        panelInputs.add(idField);

        panelInputs.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        panelInputs.add(nombreField);

        panelInputs.add(new JLabel("Precio:"));
        precioField = new JTextField();
        panelInputs.add(precioField);

        // Botones
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnListar = new JButton("Listar");

        panelInputs.add(btnAgregar);
        panelInputs.add(btnEliminar);
        panelInputs.add(btnBuscar);
        panelInputs.add(btnListar);

        // Área de texto para mostrar resultados
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea);

        add(panelInputs, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // Acciones de los botones
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProducto();
            }
        });

        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarProductos();
            }
        });
    }

    // Agregar un producto
    private void agregarProducto() {
        try {
            int id = Integer.parseInt(idField.getText());
            String nombre = nombreField.getText();
            double precio = Double.parseDouble(precioField.getText());
            Producto producto = new Producto(id, nombre, precio);
            arbol.insertar(producto);
            textArea.setText("Producto agregado: " + producto.toString());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese datos válidos.");
        }
    }

    // Eliminar un producto
    private void eliminarProducto() {
        try {
            int id = Integer.parseInt(idField.getText());
            Producto producto = new Producto(id, "", 0);
            boolean eliminado = arbol.eliminar(producto);
            if (eliminado) {
                textArea.setText("Producto eliminado con ID: " + id);
            } else {
                textArea.setText("No se encontró el producto con ID: " + id);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un ID válido.");
        }
    }

    // Buscar un producto por nombre
    private void buscarProducto() {
        String nombre = nombreField.getText();  // Ahora buscamos por nombre
        Producto producto = new Producto(0, nombre, 0);  // Usamos un objeto temporal con solo el nombre
        if (arbol.buscar(producto)) {
            textArea.setText("Producto encontrado: " + producto.toString());
        } else {
            textArea.setText("Producto no encontrado.");
        }
    }

    // Listar todos los productos
    private void listarProductos() {
        List<Producto> productos = arbol.recorridoEnInOrden();
        if (productos.isEmpty()) {
            textArea.setText("No hay productos.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Producto p : productos) {
                sb.append(p.toString()).append("\n");
            }
            textArea.setText(sb.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfazGrafica().setVisible(true);
            }
        });
    }
}