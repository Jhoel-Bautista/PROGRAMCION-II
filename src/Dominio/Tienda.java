package Dominio;

import DAO.*;
import java.io.Serializable;

/**
 * @author Jhoel
 */
// ✅ Cumple con Serializable
public class Tienda implements Serializable {
    private static Tienda instanciaUnica;

    private int idTienda;
    private String nombreTienda;
    private String direccion;
    private String telefono;
    private Usuario usuario;
    private Ticket ticket;

    private transient DAOFactory fabricaDAO; // Transient porque los DAO no se guardan en el archivo de datos

    private Tienda() {
        this.idTienda = 0;
        this.nombreTienda = "Sin nombre";
        this.direccion = "Sin direccion";
        this.telefono = "Sin telefono";
        this.usuario = null;
        this.ticket = null;
        this.fabricaDAO = DAOFactory.getInstancia();
    }
    public static synchronized Tienda getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new Tienda();
        }
        return instanciaUnica;
    }

    public void limpiarRegistros() {
        fabricaDAO.crearClienteDAO().limpiar();
        fabricaDAO.crearTecnicoDAO().limpiar();
        fabricaDAO.crearTicketDAO().limpiar();
        fabricaDAO.crearEquipoDAO().limpiar();
        fabricaDAO.crearRepuestoDAO().limpiar();
        fabricaDAO.crearReparacionDAO().limpiar();
        fabricaDAO.crearDiagnosticoDAO().limpiar();
        fabricaDAO.crearSolucionDAO().limpiar();
    }

    public void agregarTicket(Ticket nuevoTicket) { fabricaDAO.crearTicketDAO().nuevo(nuevoTicket); }
    public void editarTicket(Ticket ticket) { fabricaDAO.crearTicketDAO().editar(ticket); }
    public void eliminarTicket(int id) { fabricaDAO.crearTicketDAO().eliminar(id); }
    public Ticket buscarTicketPorId(int id) { return fabricaDAO.crearTicketDAO().buscarPorID(id); }
    public Ticket[] getTicketsGestionados() { return fabricaDAO.crearTicketDAO().listar(); }
    public void setTicketsGestionados(Ticket[] nuevosTickets) {
        fabricaDAO.crearTicketDAO().limpiar();
        if (nuevosTickets != null) {
            for (Ticket t : nuevosTickets) fabricaDAO.crearTicketDAO().nuevo(t);
        }
    }

    public void agregarCliente(Cliente nuevoCliente) { fabricaDAO.crearClienteDAO().nuevo(nuevoCliente); }
    public void editarCliente(Cliente cliente) { fabricaDAO.crearClienteDAO().editar(cliente); }
    public void eliminarCliente(int id) { fabricaDAO.crearClienteDAO().eliminar(id); }
    public Cliente buscarClientePorId(int id) { return fabricaDAO.crearClienteDAO().buscarPorID(id); }
    public Cliente[] getClientesRegistrados() { return fabricaDAO.crearClienteDAO().listar(); }
    public void setClientesRegistrados(Cliente[] nuevosClientes) {
        fabricaDAO.crearClienteDAO().limpiar();
        if (nuevosClientes != null) {
            for (Cliente c : nuevosClientes) fabricaDAO.crearClienteDAO().nuevo(c);
        }
    }

    public void agregarTecnico(Tecnico nuevoTecnico) { fabricaDAO.crearTecnicoDAO().nuevo(nuevoTecnico); }
    public void editarTecnico(Tecnico tecnico) { fabricaDAO.crearTecnicoDAO().editar(tecnico); }
    public void eliminarTecnico(int id) { fabricaDAO.crearTecnicoDAO().eliminar(id); }
    public Tecnico buscarTecnicoPorId(int id) { return fabricaDAO.crearTecnicoDAO().buscarPorID(id); }
    public Tecnico[] getTecnicosRegistrados() { return fabricaDAO.crearTecnicoDAO().listar(); }
    public void setTecnicosRegistrados(Tecnico[] nuevosTecnicos) {
        fabricaDAO.crearTecnicoDAO().limpiar();
        if (nuevosTecnicos != null) {
            for (Tecnico t : nuevosTecnicos) fabricaDAO.crearTecnicoDAO().nuevo(t);
        }
    }

    public void agregarEquipo(Equipo equipo) { fabricaDAO.crearEquipoDAO().nuevo(equipo); }
    public void editarEquipo(Equipo equipo) { fabricaDAO.crearEquipoDAO().editar(equipo); }
    public void eliminarEquipo(int id) { fabricaDAO.crearEquipoDAO().eliminar(id); }
    public Equipo buscarEquipoPorId(int id) { return fabricaDAO.crearEquipoDAO().buscarPorID(id); }
    public Equipo[] getEquiposRegistrados() { return fabricaDAO.crearEquipoDAO().listar(); }

    public void agregarRepuesto(Repuesto repuesto) { fabricaDAO.crearRepuestoDAO().nuevo(repuesto); }
    public void editarRepuesto(Repuesto repuesto) { fabricaDAO.crearRepuestoDAO().editar(repuesto); }
    public void eliminarRepuesto(int id) { fabricaDAO.crearRepuestoDAO().eliminar(id); }
    public Repuesto buscarRepuestoPorId(int id) { return fabricaDAO.crearRepuestoDAO().buscarPorID(id); }
    public Repuesto[] getRepuestosRegistrados() { return fabricaDAO.crearRepuestoDAO().listar(); }

    public void agregarReparacion(Reparacion reparacion) { fabricaDAO.crearReparacionDAO().nuevo(reparacion); }
    public void editarReparacion(Reparacion reparacion) { fabricaDAO.crearReparacionDAO().editar(reparacion); }
    public void eliminarReparacion(int id) { fabricaDAO.crearReparacionDAO().eliminar(id); }
    public Reparacion buscarReparacionPorId(int id) { return fabricaDAO.crearReparacionDAO().buscarPorID(id); }
    public Reparacion[] getReparacionesRegistradas() { return fabricaDAO.crearReparacionDAO().listar(); }

    public String reporteGeneral() {
        StringBuilder sb = new StringBuilder();
        sb.append("Reporte de ").append(nombreTienda).append('\n');
        sb.append("Clientes: ").append(getClientesRegistrados().length).append('\n');
        sb.append("Agentes: ").append(getTecnicosRegistrados().length).append('\n');
        sb.append("Tickets: ").append(getTicketsGestionados().length).append('\n');
        sb.append("Activos: ").append(getEquiposRegistrados().length).append('\n');
        sb.append("Artículos: ").append(getRepuestosRegistrados().length).append('\n');
        sb.append("Tareas: ").append(getReparacionesRegistradas().length);
        return sb.toString();
    }

    public int getIdTienda() { return idTienda; }
    public void setIdTienda(int idTienda) { this.idTienda = idTienda; }

    public String getNombreTienda() { return nombreTienda; }
    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = (nombreTienda != null && !nombreTienda.trim().isEmpty()) ? nombreTienda : "Sin nombre";
    }

    public String getNombre() { return getNombreTienda(); }
    public void setNombre(String nombre) { setNombreTienda(nombre); }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) {
        this.direccion = (direccion != null && !direccion.trim().isEmpty()) ? direccion : "Sin direccion";
    }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) {
        this.telefono = (telefono != null && !telefono.trim().isEmpty()) ? telefono : "Sin telefono";
    }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Ticket getTicket() { return ticket; }
    public void setTicket(Ticket ticket) { this.ticket = ticket; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tienda{");
        sb.append("idTienda=").append(idTienda);
        sb.append(", nombreTienda='").append(nombreTienda).append('\'');
        sb.append(", direccion='").append(direccion).append('\'');
        sb.append(", telefono='").append(telefono).append('\'');
        sb.append('}');
        return sb.toString();
    }
}