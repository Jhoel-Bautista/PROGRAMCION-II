package Dominio;

import java.util.Date;

public class Ticket {
    private int idTicket;
    private Date fechaCreacion;
    private String descripcion;
    private int prioridad;
    private Cliente clienteAsociado;
    private Tecnico tecnicoAsignado;
    private Equipo equipoAfectado;
    private Estado estadoActual;
    private Diagnostico[] listaDiagnosticos;
    private Solucion[] listaSoluciones;

    public Ticket() {}

    public Ticket(int idTicket, Date fechaCreacion, String descripcion, int prioridad, Cliente clienteAsociado, Tecnico tecnicoAsignado, Equipo equipoAfectado, Estado estadoActual) {
        this.idTicket = idTicket;
        this.fechaCreacion = fechaCreacion;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.clienteAsociado = clienteAsociado;
        this.tecnicoAsignado = tecnicoAsignado;
        this.equipoAfectado = equipoAfectado;
        this.estadoActual = estadoActual;
        this.listaDiagnosticos = new Diagnostico[10];
        this.listaSoluciones = new Solucion[10];
    }

    public int getIdTicket() { return idTicket; }
    public void setIdTicket(int idTicket) { this.idTicket = idTicket; }
    public Date getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Date fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public int getPrioridad() { return prioridad; }
    public void setPrioridad(int prioridad) { this.prioridad = prioridad; }
    public Cliente getClienteAsociado() { return clienteAsociado; }
    public void setClienteAsociado(Cliente clienteAsociado) { this.clienteAsociado = clienteAsociado; }
    public Tecnico getTecnicoAsignado() { return tecnicoAsignado; }
    public void setTecnicoAsignado(Tecnico tecnicoAsignado) { this.tecnicoAsignado = tecnicoAsignado; }
    public Equipo getEquipoAfectado() { return equipoAfectado; }
    public void setEquipoAfectado(Equipo equipoAfectado) { this.equipoAfectado = equipoAfectado; }
    public Estado getEstadoActual() { return estadoActual; }
    public void setEstadoActual(Estado estadoActual) { this.estadoActual = estadoActual; }
    public Diagnostico[] getListaDiagnosticos() { return listaDiagnosticos; }
    public void setListaDiagnosticos(Diagnostico[] listaDiagnosticos) { this.listaDiagnosticos = listaDiagnosticos; }
    public Solucion[] getListaSoluciones() { return listaSoluciones; }
    public void setListaSoluciones(Solucion[] listaSoluciones) { this.listaSoluciones = listaSoluciones; }

    public String toString() {
        return "Ticket | ID: " + idTicket + " | Desc: " + descripcion + " | Cliente: " + (clienteAsociado != null ? clienteAsociado.getNombre() : "N/A") + " | Estado: " + (estadoActual != null ? estadoActual.getNombreEstado() : "N/A");
    }
}