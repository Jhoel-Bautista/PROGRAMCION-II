package DAO;

import Dominio.Ticket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jhoel
 */
public class TicketDAO implements GenericDAO<Ticket> {
    private static TicketDAO instancia;
    private final List<Ticket> listaTickets;
    private int contadorId = 0;

    private TicketDAO() {
        this.listaTickets = new ArrayList<>();
    }

    public static synchronized TicketDAO getInstancia() {
        if (instancia == null) {
            instancia = new TicketDAO();
        }
        return instancia;
    }

    @Override
    public void nuevo(Ticket ticket) {
        if (ticket == null) return;

        // Se eliminó la restricción: pueden existir tickets con descripciones idénticas.
        if (ticket.getIdTicket() == 0) {
            contadorId++;
            ticket.setIdTicket(contadorId);
        } else if (ticket.getIdTicket() > contadorId) {
            contadorId = ticket.getIdTicket();
        }
        listaTickets.add(ticket);
    }

    @Override
    public void editar(Ticket ticket) {
        if (ticket == null) return;
        for (int i = 0; i < listaTickets.size(); i++) {
            if (listaTickets.get(i).getIdTicket() == ticket.getIdTicket()) {
                listaTickets.set(i, ticket);
                return;
            }
        }
    }

    @Override
    public void eliminar(int id) {
        listaTickets.removeIf(ticket -> ticket.getIdTicket() == id);
    }

    @Override
    public Ticket buscarPorID(int id) {
        for (Ticket ticket : listaTickets) {
            if (ticket.getIdTicket() == id) {
                return ticket;
            }
        }
        return null;
    }

    @Override
    public Ticket buscarPorNombre(String nombre) {
        return null;
    }

    @Override
    public Ticket[] listar() {
        return listaTickets.toArray(new Ticket[0]);
    }

    @Override
    public List<Ticket> listarTodos() {
        return new ArrayList<>(listaTickets);
    }

    @Override
    public void limpiar() {
        listaTickets.clear();
        contadorId = 0;
    }

    @Override
    public boolean existe(int id) {
        return buscarPorID(id) != null;
    }

    // 🔥 Persistencia: guardar y cargar tickets desde archivo 🔥
    public void guardarEnArchivo(String ruta) {
        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(new java.io.FileOutputStream(ruta))) {
            oos.writeObject(listaTickets);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarDesdeArchivo(String ruta) {
        try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.FileInputStream(ruta))) {
            listaTickets.clear();
            listaTickets.addAll((List<Ticket>) ois.readObject());
            for (Ticket t : listaTickets) {
                if (t.getIdTicket() > contadorId) contadorId = t.getIdTicket();
            }
        } catch (java.io.IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}