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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import modelo.Cliente;
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
    private TextField txtNombreCliente;
    @FXML
    private TextField txtTelefonoCliente;
    @FXML
    private TextField txtCorreoCliente;

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
    private TableView<Cliente> tablaClientes;
    @FXML
    private TableColumn<Cliente, String> colNombreCliente;
    @FXML
    private TableColumn<Cliente, String> colTelefonoCliente;
    @FXML
    private TableColumn<Cliente, String> colCorreoCliente;

    @FXML
    private VBox vboxCamposProducto;
    @FXML
    private VBox vboxCamposCliente;
    @FXML
    private Button btnAgregarProducto;
    @FXML
    private Button btnAgregarCliente;

    private Inventario inventario;
    private ObservableList<Producto> productos;
    private ObservableList<Cliente> clientes;

    public void initialize() {
        inventario = new Inventario();
        productos = FXCollections.observableArrayList(inventario.listarProductos());
        clientes = FXCollections.observableArrayList(inventario.listarClientes());

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        tablaProductos.setItems(productos);
        listViewProductos.setItems(productos);

        colNombreCliente.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colTelefonoCliente.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCorreoCliente.setCellValueFactory(new PropertyValueFactory<>("correo"));

        tablaClientes.setItems(clientes);

        vboxCamposProducto.setVisible(false);
        vboxCamposProducto.setManaged(false);
        vboxCamposCliente.setVisible(false);
        vboxCamposCliente.setManaged(false);

        txtNombre.setOnKeyPressed(this::manejarTeclas);
        txtCantidad.setOnKeyPressed(this::manejarTeclas);
        txtPrecio.setOnKeyPressed(this::manejarTeclas);
        txtNombreCliente.setOnKeyPressed(this::manejarTeclas);
        txtTelefonoCliente.setOnKeyPressed(this::manejarTeclas);
        txtCorreoCliente.setOnKeyPressed(this::manejarTeclas);
    }

    @FXML
    private void manejarAgregarProducto() {
        if (vboxCamposProducto.isVisible()) {
            try {
                String nombre = txtNombre.getText();
                int cantidad = Integer.parseInt(txtCantidad.getText());
                double precio = Double.parseDouble(txtPrecio.getText());

                Producto nuevoProducto = new Producto(nombre, cantidad, precio);
                inventario.agregarProducto(nuevoProducto);
                productos.setAll(inventario.listarProductos());

                txtNombre.clear();
                txtCantidad.clear();
                txtPrecio.clear();

                vboxCamposProducto.setVisible(false);
                vboxCamposProducto.setManaged(false);
                btnAgregarProducto.setText("Mostrar Campos para Agregar Producto");
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese valores válidos para cantidad y precio.");
            }
        } else {
            vboxCamposProducto.setVisible(true);
            vboxCamposProducto.setManaged(true);
            btnAgregarProducto.setText("Agregar Producto");
        }
    }

    @FXML
    private void manejarAgregarCliente() {
        if (vboxCamposCliente.isVisible()) {
            String nombre = txtNombreCliente.getText();
            String telefono = txtTelefonoCliente.getText();
            String correo = txtCorreoCliente.getText();

            Cliente nuevoCliente = new Cliente(nombre, telefono, correo);
            inventario.agregarCliente(nuevoCliente);
            clientes.setAll(inventario.listarClientes());

            txtNombreCliente.clear();
            txtTelefonoCliente.clear();
            txtCorreoCliente.clear();

            vboxCamposCliente.setVisible(false);
            vboxCamposCliente.setManaged(false);
            btnAgregarCliente.setText("Mostrar Campos para Agregar Cliente");
        } else {
            vboxCamposCliente.setVisible(true);
            vboxCamposCliente.setManaged(true);
            btnAgregarCliente.setText("Agregar Cliente");
        }
    }

    private void manejarTeclas(KeyEvent event) {
        switch (event.getCode()) {
            case ENTER:
                if (vboxCamposProducto.isVisible()) {
                    manejarAgregarProducto();
                } else if (vboxCamposCliente.isVisible()) {
                    manejarAgregarCliente();
                }
                break;
            case UP:
                if (txtCantidad.isFocused()) {
                    txtNombre.requestFocus();
                } else if (txtPrecio.isFocused()) {
                    txtCantidad.requestFocus();
                } else if (txtNombre.isFocused()) {
                    txtPrecio.requestFocus();
                } else if (txtTelefonoCliente.isFocused()) {
                    txtNombreCliente.requestFocus();
                } else if (txtCorreoCliente.isFocused()) {
                    txtTelefonoCliente.requestFocus();
                } else if (txtNombreCliente.isFocused()) {
                    txtCorreoCliente.requestFocus();
                }
                break;
            case DOWN:
                if (txtNombre.isFocused()) {
                    txtCantidad.requestFocus();
                } else if (txtCantidad.isFocused()) {
                    txtPrecio.requestFocus();
                } else if (txtPrecio.isFocused()) {
                    txtNombre.requestFocus();
                } else if (txtNombreCliente.isFocused()) {
                    txtTelefonoCliente.requestFocus();
                } else if (txtTelefonoCliente.isFocused()) {
                    txtCorreoCliente.requestFocus();
                } else if (txtCorreoCliente.isFocused()) {
                    txtNombreCliente.requestFocus();
                }
                break;
            default:
                break;
        }
    }
}
