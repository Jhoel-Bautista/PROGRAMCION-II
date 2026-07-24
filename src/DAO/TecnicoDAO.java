package DAO;

import Dominio.Tecnico;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jhoel
 */
public class TecnicoDAO implements GenericDAO<Tecnico> {
    private static TecnicoDAO instancia;
    private final List<Tecnico> listaTecnicos;
    private int contadorId = 0;

    private TecnicoDAO() {
        this.listaTecnicos = new ArrayList<>();
    }

    public static synchronized TecnicoDAO getInstancia() {
        if (instancia == null) {
            instancia = new TecnicoDAO();
        }
        return instancia;
    }

    @Override
    public void nuevo(Tecnico tecnico) {
        if (tecnico == null) return;

        for (Tecnico t : listaTecnicos) {
            if (t.getEmail() != null && t.getEmail().equalsIgnoreCase(tecnico.getEmail())) {
                throw new IllegalArgumentException("El correo electrónico '" + tecnico.getEmail() + "' ya está asignado a otro técnico.");
            }
        }

        if (tecnico.getIdUsuario() == 0) {
            contadorId++;
            tecnico.setIdUsuario(contadorId);
            tecnico.setNumeroEmpleado("EMP-00" + contadorId);
        } else if (tecnico.getIdUsuario() > contadorId) {
            contadorId = tecnico.getIdUsuario();
        }

        listaTecnicos.add(tecnico);
    }

    @Override
    public void editar(Tecnico tecnico) {
        if (tecnico == null) return;
        for (int i = 0; i < listaTecnicos.size(); i++) {
            if (listaTecnicos.get(i).getIdUsuario() == tecnico.getIdUsuario()) {
                listaTecnicos.set(i, tecnico);
                return;
            }
        }
    }

    @Override
    public void eliminar(int id) {
        listaTecnicos.removeIf(tecnico -> tecnico.getIdUsuario() == id);
    }

    @Override
    public Tecnico buscarPorID(int idUsuario) {
        for (Tecnico tecnico : listaTecnicos) {
            if (tecnico.getIdUsuario() == idUsuario) {
                return tecnico;
            }
        }
        return null;
    }

    @Override
    public Tecnico buscarPorNombre(String nombre) {
        for (Tecnico tecnico : listaTecnicos) {
            if (tecnico.getNombre() != null && tecnico.getNombre().equalsIgnoreCase(nombre)) {
                return tecnico;
            }
        }
        return null;
    }

    @Override
    public Tecnico[] listar() {
        return listaTecnicos.toArray(new Tecnico[0]);
    }

    @Override
    public List<Tecnico> listarTodos() {
        return new ArrayList<>(listaTecnicos);
    }

    @Override
    public void limpiar() {
        listaTecnicos.clear();
        contadorId = 0;
    }

    // 🔥 Persistencia: guardar y cargar técnicos desde archivo 🔥
    public void guardarEnArchivo(String ruta) {
        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(new java.io.FileOutputStream(ruta))) {
            oos.writeObject(listaTecnicos);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarDesdeArchivo(String ruta) {
        try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.FileInputStream(ruta))) {
            listaTecnicos.clear();
            listaTecnicos.addAll((List<Tecnico>) ois.readObject());
            for (Tecnico t : listaTecnicos) {
                if (t.getIdUsuario() > contadorId) contadorId = t.getIdUsuario();
            }
        } catch (java.io.IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}