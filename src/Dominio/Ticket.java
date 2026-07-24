package Dominio;

import DAO.ValidadorDuplicados;
import Util.Validador;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Jhoel
 */
// ✅ Cumple con Serializable
public class Ticket implements ValidadorDuplicados, Serializable {
    private int idTicket;
    private Date fechaCreacion;
    private String descripcion;
    private int prioridad;
    private Cliente clienteAsociado;
    private Tecnico tecnicoAsignado;
    private Equipo equipoAfectado;
    private Estado estadoActual;
    private List<Diagnostico> listaDiagnosticos;
    private List<Solucion> listaSoluciones;

    public Ticket() {
        this.idTicket = 0;
        this.fechaCreacion = new Date();
        this.descripcion = "Sin descripcion";
        this.prioridad = 1;
        this.clienteAsociado = null;
        this.tecnicoAsignado = null;
        this.equipoAfectado = null;
        this.estadoActual = Estado.PENDIENTE;
        this.listaDiagnosticos = new ArrayList<>();
        this.listaSoluciones = new ArrayList<>();
    }

    public Ticket(int idTicket, String descripcion, Estado estado) {
        this();
        this.idTicket = idTicket;
        setDescripcion(descripcion);
        setEstadoActual(estado);
    }

    public Ticket(int idTicket, Date fechaCreacion, String descripcion, int prioridad,
                  Cliente clienteAsociado, Tecnico tecnicoAsignado, Equipo equipoAfectado,
                  Estado estadoActual) {
        this();
        this.idTicket = idTicket;
        setFechaCreacion(fechaCreacion);
        setDescripcion(descripcion);
        setPrioridad(prioridad);
        setClienteAsociado(clienteAsociado);
        setTecnicoAsignado(tecnicoAsignado);
        setEquipoAfectado(equipoAfectado);
        setEstadoActual(estadoActual);
    }

    @Override
    public boolean validarDuplicado(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ticket otro = (Ticket) obj;
        return this.idTicket == otro.idTicket ||
                (this.descripcion != null && this.descripcion.equalsIgnoreCase(otro.descripcion));
    }

    public void abrirTicket() {
        this.fechaCreacion = new Date();
        this.estadoActual = Estado.PENDIENTE;
    }

    public void asignarTecnico(Tecnico tecnico) {
        this.tecnicoAsignado = tecnico;
        if (tecnico != null && this.estadoActual == Estado.PENDIENTE) {
            this.estadoActual = Estado.EN_PROCESO;
        }
    }

    public void actualizarEstado(Estado nuevoEstado) {
        setEstadoActual(nuevoEstado);
    }

    public void cerrarTicket() {
        this.estadoActual = Estado.CERRADO;
    }

    public void finalizarTicket() {
        cerrarTicket();
    }

    public void agregarDiagnostico(Diagnostico diagnostico) {
        if (diagnostico != null && !listaDiagnosticos.contains(diagnostico)) {
            listaDiagnosticos.add(diagnostico);
        }
    }

    public void agregarSolucion(Solucion solucion) {
        if (solucion != null && !listaSoluciones.contains(solucion)) {
            listaSoluciones.add(solucion);
        }
    }

    public boolean esDescripcionValida() {
        return Validador.validarDescripcion(descripcion);
    }

    public int getIdTicket() { return idTicket; }
    public void setIdTicket(int idTicket) { this.idTicket = idTicket; }

    public Date getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion != null ? fechaCreacion : new Date();
    }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) {
        if (descripcion != null && !descripcion.trim().isEmpty()) {
            this.descripcion = descripcion;
        } else {
            this.descripcion = "Sin descripcion";
        }
    }

    public int getPrioridad() { return prioridad; }
    public void setPrioridad(int prioridad) {
        this.prioridad = Validador.validarPrioridad(String.valueOf(prioridad)) ? prioridad : 1;
    }

    public Cliente getClienteAsociado() { return clienteAsociado; }
    public void setClienteAsociado(Cliente clienteAsociado) { this.clienteAsociado = clienteAsociado; }

    public Tecnico getTecnicoAsignado() { return tecnicoAsignado; }
    public void setTecnicoAsignado(Tecnico tecnicoAsignado) { this.tecnicoAsignado = tecnicoAsignado; }

    public Equipo getEquipoAfectado() { return equipoAfectado; }
    public void setEquipoAfectado(Equipo equipoAfectado) { this.equipoAfectado = equipoAfectado; }

    public Estado getEstadoActual() { return estadoActual; }
    public void setEstadoActual(Estado estado) { this.estadoActual = estado != null ? estado : Estado.PENDIENTE; }

    public List<Diagnostico> getListaDiagnosticos() { return new ArrayList<>(listaDiagnosticos); }
    public void setListaDiagnosticos(List<Diagnostico> listaDiagnosticos) {
        this.listaDiagnosticos = listaDiagnosticos != null ? new ArrayList<>(listaDiagnosticos) : new ArrayList<>();
    }

    public List<Solucion> getListaSoluciones() { return new ArrayList<>(listaSoluciones); }
    public void setListaSoluciones(List<Solucion> listaSoluciones) {
        this.listaSoluciones = listaSoluciones != null ? new ArrayList<>(listaSoluciones) : new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ticket ticket = (Ticket) obj;
        return idTicket == ticket.idTicket;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTicket);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Ticket{");
        sb.append("idTicket=").append(idTicket);
        sb.append(", fechaCreacion=").append(fechaCreacion);
        sb.append(", descripcion='").append(descripcion).append('\'');
        sb.append(", prioridad=").append(prioridad);
        sb.append(", estado=").append(estadoActual);
        sb.append('}');
        return sb.toString();
    }
}