package DAO;

import Dominio.Reparacion;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jhoel
 */
public class ReparacionDAO implements GenericDAO<Reparacion> {
    private static ReparacionDAO instancia;
    private final List<Reparacion> listaReparacions;
    private int contadorId = 0;

    private ReparacionDAO() {
        this.listaReparacions = new ArrayList<>();
    }

    public static synchronized ReparacionDAO getInstancia() {
        if (instancia == null) {
            instancia = new ReparacionDAO();
        }
        return instancia;
    }

    @Override
    public void nuevo(Reparacion reparacion) {
        if (reparacion == null) return;

        // Se eliminó la restricción: es normal tener reparaciones con descripciones iguales.
        if (reparacion.getIdReparacion() == 0) {
            contadorId++;
            reparacion.setIdReparacion(contadorId);
        } else if (reparacion.getIdReparacion() > contadorId) {
            contadorId = reparacion.getIdReparacion();
        }
        listaReparacions.add(reparacion);
    }

    @Override
    public void editar(Reparacion reparacion) {
        if (reparacion == null) return;
        for (int i = 0; i < listaReparacions.size(); i++) {
            if (listaReparacions.get(i).getIdReparacion() == reparacion.getIdReparacion()) {
                listaReparacions.set(i, reparacion);
                return;
            }
        }
    }

    @Override
    public void eliminar(int id) {
        listaReparacions.removeIf(reparacion -> reparacion.getIdReparacion() == id);
    }

    @Override
    public Reparacion buscarPorID(int id) {
        for (Reparacion reparacion : listaReparacions) {
            if (reparacion.getIdReparacion() == id) {
                return reparacion;
            }
        }
        return null;
    }

    @Override
    public Reparacion buscarPorNombre(String nombre) {
        return null;
    }

    @Override
    public Reparacion[] listar() {
        return listaReparacions.toArray(new Reparacion[0]);
    }

    @Override
    public List<Reparacion> listarTodos() {
        return new ArrayList<>(listaReparacions);
    }

    @Override
    public void limpiar() {
        listaReparacions.clear();
        contadorId = 0;
    }

    // 🔥 Persistencia: guardar y cargar reparaciones desde archivo 🔥
    public void guardarEnArchivo(String ruta) {
        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(new java.io.FileOutputStream(ruta))) {
            oos.writeObject(listaReparacions);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarDesdeArchivo(String ruta) {
        try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.FileInputStream(ruta))) {
            listaReparacions.clear();
            listaReparacions.addAll((List<Reparacion>) ois.readObject());
            for (Reparacion r : listaReparacions) {
                if (r.getIdReparacion() > contadorId) contadorId = r.getIdReparacion();
            }
        } catch (java.io.IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}