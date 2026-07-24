package DAO;

import Dominio.Equipo;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jhoel
 */
public class EquipoDAO implements GenericDAO<Equipo> {
    private static EquipoDAO instancia;
    private final List<Equipo> listaEquipos;
    private int contadorId = 0;

    private EquipoDAO() {
        this.listaEquipos = new ArrayList<>();
    }

    public static synchronized EquipoDAO getInstancia() {
        if (instancia == null) {
            instancia = new EquipoDAO();
        }
        return instancia;
    }

    @Override
    public void nuevo(Equipo equipo) {
        if (equipo == null) return;

        for (Equipo e : listaEquipos) {
            if (e.getNumeroSerie() != null && e.getNumeroSerie().equalsIgnoreCase(equipo.getNumeroSerie())) {
                throw new IllegalArgumentException("El número de serie '" + equipo.getNumeroSerie() + "' ya pertenece a otro equipo registrado.");
            }
        }

        if (equipo.getIdEquipo() == 0) {
            contadorId++;
            equipo.setIdEquipo(contadorId);
        } else if (equipo.getIdEquipo() > contadorId) {
            contadorId = equipo.getIdEquipo();
        }

        listaEquipos.add(equipo);
    }

    @Override
    public void editar(Equipo equipo) {
        if (equipo == null) return;
        for (int i = 0; i < listaEquipos.size(); i++) {
            if (listaEquipos.get(i).getIdEquipo() == equipo.getIdEquipo()) {
                listaEquipos.set(i, equipo);
                return;
            }
        }
    }

    @Override
    public void eliminar(int id) {
        listaEquipos.removeIf(equipo -> equipo.getIdEquipo() == id);
    }

    @Override
    public Equipo buscarPorID(int id) {
        for (Equipo equipo : listaEquipos) {
            if (equipo.getIdEquipo() == id) {
                return equipo;
            }
        }
        return null;
    }

    @Override
    public Equipo buscarPorNombre(String nombre) {
        return null;
    }

    @Override
    public Equipo[] listar() {
        return listaEquipos.toArray(new Equipo[0]);
    }

    @Override
    public List<Equipo> listarTodos() {
        return new ArrayList<>(listaEquipos);
    }

    @Override
    public void limpiar() {
        listaEquipos.clear();
        contadorId = 0;
    }

    // 🔥 Persistencia: guardar y cargar equipos desde archivo 🔥
    public void guardarEnArchivo(String ruta) {
        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(new java.io.FileOutputStream(ruta))) {
            oos.writeObject(listaEquipos);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarDesdeArchivo(String ruta) {
        try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.FileInputStream(ruta))) {
            listaEquipos.clear();
            listaEquipos.addAll((List<Equipo>) ois.readObject());
            for (Equipo e : listaEquipos) {
                if (e.getIdEquipo() > contadorId) contadorId = e.getIdEquipo();
            }
        } catch (java.io.IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}