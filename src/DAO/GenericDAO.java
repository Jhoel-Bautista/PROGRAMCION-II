package DAO;

import java.util.List;

/** Contrato DAO para crear, editar, eliminar, buscar y listar entidades del proyecto final. */
public interface GenericDAO<T> {
    void nuevo(T entidad);
    void editar(T entidad);
    void eliminar(int id);
    T buscarPorID(int id);
    T buscarPorNombre(String nombre);
    T[] listar();
    List<T> listarTodos();
    void limpiar();

    default boolean existe(int id) {
        return buscarPorID(id) != null;
    }

    default void agregar(T entidad) {
        nuevo(entidad);
    }

    default void actualizar(T entidad) {
        editar(entidad);
    }

    default T buscarPorId(int id) {
        return buscarPorID(id);
    }
}