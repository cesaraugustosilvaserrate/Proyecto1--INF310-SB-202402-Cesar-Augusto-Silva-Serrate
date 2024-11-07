package arboles;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class GestionContactosGUI extends JFrame {

    private ArbolBinario<Contacto> arbolContactos;
    private JTextArea areaTexto;
    private JTextField txtNombre, txtTelefono, txtEmail;

    public GestionContactosGUI() {
        arbolContactos = new ArbolBinario<>();
        initComponents();
    }

    private void initComponents() {
        setTitle("Gestión de Contactos");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Contenedor principal
        Container contenedor = getContentPane();
        contenedor.setLayout(new BorderLayout());

        // Panel de entrada de datos
        JPanel panelEntrada = new JPanel();
        panelEntrada.setLayout(new GridLayout(4, 2));

        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblTelefono = new JLabel("Teléfono:");
        JLabel lblEmail = new JLabel("Email:");

        txtNombre = new JTextField();
        txtTelefono = new JTextField();
        txtEmail = new JTextField();

        panelEntrada.add(lblNombre);
        panelEntrada.add(txtNombre);
        panelEntrada.add(lblTelefono);
        panelEntrada.add(txtTelefono);
        panelEntrada.add(lblEmail);
        panelEntrada.add(txtEmail);

        // Botones
        JButton btnAgregar = new JButton("Agregar Contacto");
        JButton btnBuscar = new JButton("Buscar Contacto");

        panelEntrada.add(btnAgregar);
        panelEntrada.add(btnBuscar);

        // TextArea para mostrar resultados
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        JScrollPane scrollArea = new JScrollPane(areaTexto);

        // Añadir los componentes al contenedor
        contenedor.add(panelEntrada, BorderLayout.NORTH);
        contenedor.add(scrollArea, BorderLayout.CENTER);

        // Acción para el botón de agregar contacto
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarContacto();
            }
        });

        // Acción para el botón de buscar contacto
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarContacto();
            }
        });
    }

    // Método para agregar un contacto
    private void agregarContacto() {
        String nombre = txtNombre.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String email = txtEmail.getText().trim();

        // Verificar que los campos no estén vacíos
        if (nombre.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear el nuevo contacto
        Contacto nuevoContacto = new Contacto(nombre, telefono, email);
        arbolContactos.insertar(nuevoContacto);

        // Limpiar los campos de texto
        txtNombre.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");

        JOptionPane.showMessageDialog(this, "Contacto agregado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para buscar un contacto por primer nombre
    private void buscarContacto() {
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el primer nombre del contacto a buscar:");
        if (nombre != null && !nombre.isEmpty()) {
            // Buscar contactos por el primer nombre
            List<Contacto> contactosEncontrados = buscarContactosPorPrimerNombre(nombre);

            // Mostrar los resultados
            if (!contactosEncontrados.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (Contacto contacto : contactosEncontrados) {
                    sb.append("Nombre: ").append(contacto.getNombre()).append("\n")
                      .append("Teléfono: ").append(contacto.getTelefono()).append("\n")
                      .append("Email: ").append(contacto.getEmail()).append("\n\n");
                }
                areaTexto.setText(sb.toString());
            } else {
                areaTexto.setText("No se encontraron contactos con el primer nombre: " + nombre);
            }
        }
    }

    // Método auxiliar para buscar contactos por primer nombre
    private List<Contacto> buscarContactosPorPrimerNombre(String primerNombre) {
        List<Contacto> contactosEncontrados = new ArrayList<>();

        // Recorremos el árbol en in-orden y buscamos los contactos que coincidan con el primer nombre
        for (Contacto contacto : arbolContactos.recorridoEnInOrden()) {
            // Dividimos el nombre completo para obtener solo el primer nombre
            String primerNombreContacto = contacto.getNombre().split(" ")[0]; // Obtener solo el primer nombre

            if (primerNombreContacto.equalsIgnoreCase(primerNombre)) {
                contactosEncontrados.add(contacto);
            }
        }
        return contactosEncontrados;
    }

    public static void main(String[] args) {
        // Ejecutar la interfaz en el hilo de eventos
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GestionContactosGUI().setVisible(true);
            }
        });
    }
}

