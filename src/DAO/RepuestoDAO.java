package DAO;

import Dominio.Repuesto;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jhoel
 */
public class RepuestoDAO implements GenericDAO<Repuesto> {
    private static RepuestoDAO instancia;
    private final List<Repuesto> listaRepuestos;
    private int contadorId = 0;

    private RepuestoDAO() {
        this.listaRepuestos = new ArrayList<>();
    }

    public static synchronized RepuestoDAO getInstancia() {
        if (instancia == null) {
            instancia = new RepuestoDAO();
        }
        return instancia;
    }

    @Override
    public void nuevo(Repuesto repuesto) {
        if (repuesto == null) return;

        // Validación profunda: Bloquea si el nombre o la descripción ya existen
        for (Repuesto r : listaRepuestos) {
            if (r.getNombreRepuesto() != null && r.getNombreRepuesto().trim().equalsIgnoreCase(repuesto.getNombreRepuesto().trim())) {
                throw new IllegalArgumentException("⚠️ Este nombre de producto ya existe en el inventario.");
            }
            if (r.getDescripcionRepuesto() != null && r.getDescripcionRepuesto().trim().equalsIgnoreCase(repuesto.getDescripcionRepuesto().trim())) {
                throw new IllegalArgumentException("⚠️ Esta descripción exacta ya pertenece a otro producto en bodega.");
            }
        }

        if (repuesto.getIdRepuesto() == 0) {
            contadorId++;
            repuesto.setIdRepuesto(contadorId);
        } else if (repuesto.getIdRepuesto() > contadorId) {
            contadorId = repuesto.getIdRepuesto();
        }

        listaRepuestos.add(repuesto);
    }

    @Override
    public void editar(Repuesto repuesto) {
        if (repuesto == null) return;
        for (int i = 0; i < listaRepuestos.size(); i++) {
            if (listaRepuestos.get(i).getIdRepuesto() == repuesto.getIdRepuesto()) {
                listaRepuestos.set(i, repuesto);
                return;
            }
        }
    }

    @Override
    public void eliminar(int id) {
        listaRepuestos.removeIf(repuesto -> repuesto.getIdRepuesto() == id);
    }

    @Override
    public Repuesto buscarPorID(int id) {
        for (Repuesto repuesto : listaRepuestos) {
            if (repuesto.getIdRepuesto() == id) {
                return repuesto;
            }
        }
        return null;
    }

    @Override
    public Repuesto buscarPorNombre(String nombre) {
        for (Repuesto repuesto : listaRepuestos) {
            if (repuesto.getNombreRepuesto() != null && repuesto.getNombreRepuesto().equalsIgnoreCase(nombre)) {
                return repuesto;
            }
        }
        return null;
    }

    @Override
    public Repuesto[] listar() {
        return listaRepuestos.toArray(new Repuesto[0]);
    }

    @Override
    public List<Repuesto> listarTodos() {
        return new ArrayList<>(listaRepuestos);
    }

    @Override
    public void limpiar() {
        listaRepuestos.clear();
        contadorId = 0;
    }

    // 🔥 Persistencia: guardar y cargar repuestos desde archivo 🔥
    public void guardarEnArchivo(String ruta) {
        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(new java.io.FileOutputStream(ruta))) {
            oos.writeObject(listaRepuestos);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarDesdeArchivo(String ruta) {
        try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.FileInputStream(ruta))) {
            listaRepuestos.clear();
            listaRepuestos.addAll((List<Repuesto>) ois.readObject());
            for (Repuesto r : listaRepuestos) {
                if (r.getIdRepuesto() > contadorId) contadorId = r.getIdRepuesto();
            }
        } catch (java.io.IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}