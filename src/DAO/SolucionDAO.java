package DAO;

import Dominio.Solucion;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jhoel
 */
public class SolucionDAO implements GenericDAO<Solucion> {
    private static SolucionDAO instancia;
    private final List<Solucion> listaSolucions;
    private int contadorId = 0;

    private SolucionDAO() {
        this.listaSolucions = new ArrayList<>();
    }

    public static synchronized SolucionDAO getInstancia() {
        if (instancia == null) {
            instancia = new SolucionDAO();
        }
        return instancia;
    }

    @Override
    public void nuevo(Solucion solucion) {
        if (solucion == null) return;

        if (solucion.getIdSolucion() == 0) {
            contadorId++;
            solucion.setIdSolucion(contadorId);
        } else if (solucion.getIdSolucion() > contadorId) {
            contadorId = solucion.getIdSolucion();
        }
        listaSolucions.add(solucion);
    }

    @Override
    public void editar(Solucion solucion) {
        if (solucion == null) return;
        for (int i = 0; i < listaSolucions.size(); i++) {
            if (listaSolucions.get(i).getIdSolucion() == solucion.getIdSolucion()) {
                listaSolucions.set(i, solucion);
                return;
            }
        }
    }

    @Override
    public void eliminar(int id) {
        listaSolucions.removeIf(solucion -> solucion.getIdSolucion() == id);
    }

    @Override
    public Solucion buscarPorID(int id) {
        for (Solucion solucion : listaSolucions) {
            if (solucion.getIdSolucion() == id) {
                return solucion;
            }
        }
        return null;
    }

    @Override
    public Solucion buscarPorNombre(String nombre) {
        return null;
    }

    @Override
    public Solucion[] listar() {
        return listaSolucions.toArray(new Solucion[0]);
    }

    @Override
    public List<Solucion> listarTodos() {
        return new ArrayList<>(listaSolucions);
    }

    @Override
    public void limpiar() {
        listaSolucions.clear();
        contadorId = 0;
    }

    // 🔥 Persistencia: guardar y cargar soluciones desde archivo 🔥
    public void guardarEnArchivo(String ruta) {
        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(new java.io.FileOutputStream(ruta))) {
            oos.writeObject(listaSolucions);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarDesdeArchivo(String ruta) {
        try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.FileInputStream(ruta))) {
            listaSolucions.clear();
            listaSolucions.addAll((List<Solucion>) ois.readObject());
            for (Solucion s : listaSolucions) {
                if (s.getIdSolucion() > contadorId) contadorId = s.getIdSolucion();
            }
        } catch (java.io.IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}