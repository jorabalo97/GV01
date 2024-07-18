package controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import modelo.Inventario;
import modelo.Producto;

public class InventarioControlador {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtPrecio;

    @FXML
    private ListView<Producto> listViewProductos;

    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, Integer> colCantidad;

    @FXML
    private TableColumn<Producto, Double> colPrecio;

    @FXML
    private VBox vboxCampos;

    @FXML
    private Button btnAgregarProducto;

    private Inventario inventario;
    private ObservableList<Producto> productos;

    public void initialize() {
        inventario = new Inventario();
        productos = FXCollections.observableArrayList(inventario.listarProductos());

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        tablaProductos.setItems(productos);
        listViewProductos.setItems(productos);

        // Ocultar los campos de entrada al inicio
        vboxCampos.setVisible(false);
    }

    @FXML
    private void manejarAgregarProducto() {
        if (vboxCampos.isVisible()) {
            // Intentar agregar el producto si los campos están visibles
            try {
                String nombre = txtNombre.getText();
                int cantidad = Integer.parseInt(txtCantidad.getText());
                double precio = Double.parseDouble(txtPrecio.getText());

                Producto nuevoProducto = new Producto(nombre, cantidad, precio);
                inventario.agregarProducto(nuevoProducto);
                productos.setAll(inventario.listarProductos());

                // Limpiar campos
                txtNombre.clear();
                txtCantidad.clear();
                txtPrecio.clear();

                // Ocultar los campos nuevamente
                vboxCampos.setVisible(false);

                // Cambiar el texto del botón
                btnAgregarProducto.setText("Mostrar Campos para Agregar Producto");
            } catch (NumberFormatException e) {
                // Mostrar mensaje de error al usuario
                System.out.println("Por favor, ingrese valores válidos para cantidad y precio.");
            }
        } else {
            // Mostrar los campos si están ocultos
            vboxCampos.setVisible(true);
            // Cambiar el texto del botón
            btnAgregarProducto.setText("Agregar Producto");
        }
    }
}

