package DAO;

import Dominio.Cliente;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jhoel
 */
public class ClienteDAO implements GenericDAO<Cliente> {
    private static ClienteDAO instancia;
    private final List<Cliente> listaClientes;
    private int contadorId = 0;

    private ClienteDAO() {
        this.listaClientes = new ArrayList<>();
    }

    public static synchronized ClienteDAO getInstancia() {
        if (instancia == null) {
            instancia = new ClienteDAO();
        }
        return instancia;
    }

    @Override
    public void nuevo(Cliente cliente) {
        if (cliente == null) return;

        // Validación para evitar correos duplicados
        for (Cliente c : listaClientes) {
            if (c.getEmail() != null && c.getEmail().equalsIgnoreCase(cliente.getEmail())) {
                throw new IllegalArgumentException("El correo electrónico '" + cliente.getEmail() + "' ya está registrado por otro cliente.");
            }
        }

        if (cliente.getIdUsuario() == 0) {
            contadorId++;
            cliente.setIdUsuario(contadorId);
            cliente.setNumeroCliente("CLI-00" + contadorId);
        } else if (cliente.getIdUsuario() > contadorId) {
            contadorId = cliente.getIdUsuario();
        }

        listaClientes.add(cliente);
    }

    @Override
    public void editar(Cliente cliente) {
        if (cliente == null) return;
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i).getIdUsuario() == cliente.getIdUsuario()) {
                listaClientes.set(i, cliente);
                return;
            }
        }
    }

    @Override
    public void eliminar(int id) {
        listaClientes.removeIf(cliente -> cliente.getIdUsuario() == id);
    }

    @Override
    public Cliente buscarPorID(int idUsuario) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getIdUsuario() == idUsuario) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public Cliente buscarPorNombre(String nombre) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getNombre() != null && cliente.getNombre().equalsIgnoreCase(nombre)) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public Cliente[] listar() {
        return listaClientes.toArray(new Cliente[0]);
    }

    @Override
    public List<Cliente> listarTodos() {
        return new ArrayList<>(listaClientes);
    }

    @Override
    public void limpiar() {
        listaClientes.clear();
        contadorId = 0;
    }

    // 🔥 Persistencia: guardar y cargar clientes desde archivo 🔥
    public void guardarEnArchivo(String ruta) {
        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(new java.io.FileOutputStream(ruta))) {
            oos.writeObject(listaClientes);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarDesdeArchivo(String ruta) {
        try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.FileInputStream(ruta))) {
            listaClientes.clear();
            listaClientes.addAll((List<Cliente>) ois.readObject());
            for (Cliente c : listaClientes) {
                if (c.getIdUsuario() > contadorId) contadorId = c.getIdUsuario();
            }
        } catch (java.io.IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}