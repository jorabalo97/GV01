package modelo;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Producto> productos;
    private List<Cliente> clientes;

    public Inventario() {
        this.productos = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public List<Producto> listarProductos() {
        return new ArrayList<>(productos);
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes);
    }
}
