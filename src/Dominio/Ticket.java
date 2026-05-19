package Dominio;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Ticket {
    private int idTicket;
    private Date fechaCreacion;
    private String descripcion;
    private int prioridad;
    private String comentario;
    private Cliente clienteAsociado;
    private Tecnico tecnicoAsignado;
    private Equipo equipoAfectado;
    private Estado estadoActual;
    private List<Diagnostico> listaDiagnosticos;
    private List<Solucion> listaSoluciones;

    public Ticket() {
        this.listaDiagnosticos = new ArrayList<>();
        this.listaSoluciones = new ArrayList<>();
    }

    public Ticket(int idTicket, Date fechaCreacion, String descripcion, int prioridad, Cliente clienteAsociado, Tecnico tecnicoAsignado, Equipo equipoAfectado, Estado estadoActual) {
        this.idTicket = idTicket;
        this.fechaCreacion = fechaCreacion;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.clienteAsociado = clienteAsociado;
        this.tecnicoAsignado = tecnicoAsignado;
        this.equipoAfectado = equipoAfectado;
        this.estadoActual = estadoActual;
        this.listaDiagnosticos = new ArrayList<>();
        this.listaSoluciones = new ArrayList<>();
    }

    public int getIdTicket() {
        return idTicket; }
    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket; }
    public Date getFechaCreacion() {
        return fechaCreacion; }
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion; }
    public String getDescripcion() {
        return descripcion; }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion; }
    public int getPrioridad() {
        return prioridad; }
    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad; }
    public String getComentario() {
        return comentario; }
    public void setComentario(String comentario) {
        this.comentario = comentario; }
    public Cliente getClienteAsociado() {
        return clienteAsociado; }
    public void setClienteAsociado(Cliente clienteAsociado) {
        this.clienteAsociado = clienteAsociado; }
    public Tecnico getTecnicoAsignado() {
        return tecnicoAsignado; }
    public void setTecnicoAsignado(Tecnico tecnicoAsignado) {
        this.tecnicoAsignado = tecnicoAsignado; }
    public Equipo getEquipoAfectado() {
        return equipoAfectado; }
    public void setEquipoAfectado(Equipo equipoAfectado) {
        this.equipoAfectado = equipoAfectado; }
    public Estado getEstadoActual() {
        return estadoActual; }
    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual; }
    public List<Diagnostico> getListaDiagnosticos() {
        return listaDiagnosticos; }
    public void setListaDiagnosticos(List<Diagnostico> listaDiagnosticos) {
        this.listaDiagnosticos = listaDiagnosticos; }
    public List<Solucion> getListaSoluciones() {
        return listaSoluciones; }
    public void setListaSoluciones(List<Solucion> listaSoluciones) {
        this.listaSoluciones = listaSoluciones; }

    public void abrirTicket() {
        this.estadoActual = new Estado(1, "Abierto"); }
    public void asignarTecnico(Tecnico tecnico) {
        this.tecnicoAsignado = tecnico; }
    public void actualizarEstado(Estado nuevoEstado) {
        this.estadoActual = nuevoEstado; }
    public void cerrarTicket() {
        this.estadoActual = new Estado(3, "Terminado"); }
}