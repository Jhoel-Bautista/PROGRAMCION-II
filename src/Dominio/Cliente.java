package Dominio;

import DAO.ValidadorDuplicados;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Jhoel
 */
// ✅ HEREDA DE USUARIO (extends Usuario)
public class Cliente extends Usuario implements ValidadorDuplicados {
    private String numeroCliente;
    // ❌ ATRIBUTO TELÉFONO ELIMINADO PARA USAR LA HERENCIA
    private String direccion;
    private Ticket[] ticketsAsociados;

    public Cliente() {
        super();
        this.numeroCliente = "Sin numero";
        this.direccion = "Sin direccion";
        this.ticketsAsociados = new Ticket[0];
    }

    // CONSTRUCTOR INTACTO, solo manda el teléfono al "super"
    public Cliente(int idUsuario, String nombre, String email, String password,
                   String numeroCliente, String telefono, String direccion, Ticket[] ticketsAsociados) {
        super(idUsuario, nombre, email, password, telefono); // ✅ Le pasa el teléfono al padre
        setNumeroCliente(numeroCliente);
        setDireccion(direccion);
        setTicketsAsociados(ticketsAsociados);
    }

    public Ticket crearTicket() {
        Ticket ticket = new Ticket(0, "Ticket creado por cliente", Estado.PENDIENTE);
        ticket.setClienteAsociado(this);
        agregarTicket(ticket);
        return ticket;
    }

    public Ticket consultarTicket(int idTicket) {
        for (Ticket ticket : ticketsAsociados) {
            if (ticket != null && ticket.getIdTicket() == idTicket) {
                return ticket;
            }
        }
        return null;
    }

    @Override
    public boolean validarDuplicado(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cliente otro = (Cliente) obj;
        return this.getIdUsuario() == otro.getIdUsuario() ||
                (this.getNombre() != null && this.getNombre().equalsIgnoreCase(otro.getNombre())) ||
                (this.getEmail() != null && this.getEmail().equalsIgnoreCase(otro.getEmail())) ||
                (this.numeroCliente != null && this.numeroCliente.equalsIgnoreCase(otro.numeroCliente));
    }

    @Override
    public String obtenerRol() {
        return "ROL: Cliente del Sistema de Gestión";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        Cliente cliente = (Cliente) obj;
        return Objects.equals(numeroCliente, cliente.numeroCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numeroCliente);
    }

    public String getNumeroCliente() { return numeroCliente; }
    public void setNumeroCliente(String numeroCliente) {
        this.numeroCliente = (numeroCliente != null && !numeroCliente.trim().isEmpty()) ? numeroCliente : "Sin numero";
    }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) {
        this.direccion = (direccion != null && !direccion.trim().isEmpty()) ? direccion : "Sin direccion";
    }

    public Ticket[] getTicketsAsociados() {
        return Arrays.copyOf(ticketsAsociados, ticketsAsociados.length);
    }

    public void setTicketsAsociados(Ticket[] ticketsAsociados) {
        this.ticketsAsociados = ticketsAsociados != null ? Arrays.copyOf(ticketsAsociados, ticketsAsociados.length) : new Ticket[0];
    }

    public void agregarTicket(Ticket ticket) {
        if (ticket == null) return;
        Ticket[] nuevoArreglo = Arrays.copyOf(ticketsAsociados, ticketsAsociados.length + 1);
        nuevoArreglo[nuevoArreglo.length - 1] = ticket;
        ticketsAsociados = nuevoArreglo;
    }

    public String reporteTickets() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tickets de ").append(getNombre()).append(':');
        for (Ticket ticket : ticketsAsociados) {
            if (ticket != null) {
                sb.append('\n').append(ticket);
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente{");
        sb.append("numeroCliente='").append(numeroCliente).append('\'');
        sb.append(", direccion='").append(direccion).append('\'');
        sb.append("} ").append(super.toString());
        return sb.toString();
    }
}