package DAO;

import Dominio.Diagnostico;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jhoel
 */
public class DiagnosticoDAO implements GenericDAO<Diagnostico> {
    private static DiagnosticoDAO instancia;
    private final List<Diagnostico> listaDiagnosticos;
    private int contadorId = 0;

    private DiagnosticoDAO() {
        this.listaDiagnosticos = new ArrayList<>();
    }

    public static synchronized DiagnosticoDAO getInstancia() {
        if (instancia == null) {
            instancia = new DiagnosticoDAO();
        }
        return instancia;
    }

    @Override
    public void nuevo(Diagnostico diagnostico) {
        if (diagnostico == null) return;

        if (diagnostico.getIdDiagnostico() == 0) {
            contadorId++;
            diagnostico.setIdDiagnostico(contadorId);
        } else if (diagnostico.getIdDiagnostico() > contadorId) {
            contadorId = diagnostico.getIdDiagnostico();
        }
        listaDiagnosticos.add(diagnostico);
    }

    @Override
    public void editar(Diagnostico diagnostico) {
        if (diagnostico == null) return;
        for (int i = 0; i < listaDiagnosticos.size(); i++) {
            if (listaDiagnosticos.get(i).getIdDiagnostico() == diagnostico.getIdDiagnostico()) {
                listaDiagnosticos.set(i, diagnostico);
                return;
            }
        }
    }

    @Override
    public void eliminar(int id) {
        listaDiagnosticos.removeIf(diagnostico -> diagnostico.getIdDiagnostico() == id);
    }

    @Override
    public Diagnostico buscarPorID(int id) {
        for (Diagnostico diagnostico : listaDiagnosticos) {
            if (diagnostico.getIdDiagnostico() == id) {
                return diagnostico;
            }
        }
        return null;
    }

    @Override
    public Diagnostico buscarPorNombre(String nombre) {
        return null;
    }

    @Override
    public Diagnostico[] listar() {
        return listaDiagnosticos.toArray(new Diagnostico[0]);
    }

    @Override
    public List<Diagnostico> listarTodos() {
        return new ArrayList<>(listaDiagnosticos);
    }

    @Override
    public void limpiar() {
        listaDiagnosticos.clear();
        contadorId = 0;
    }

    // 🔥 Persistencia: guardar y cargar diagnósticos desde archivo 🔥
    public void guardarEnArchivo(String ruta) {
        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(new java.io.FileOutputStream(ruta))) {
            oos.writeObject(listaDiagnosticos);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarDesdeArchivo(String ruta) {
        try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.FileInputStream(ruta))) {
            listaDiagnosticos.clear();
            listaDiagnosticos.addAll((List<Diagnostico>) ois.readObject());
            for (Diagnostico d : listaDiagnosticos) {
                if (d.getIdDiagnostico() > contadorId) contadorId = d.getIdDiagnostico();
            }
        } catch (java.io.IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}