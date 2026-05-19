package Dominio;
import java.util.Date;

public class Reparacion {
    private int idReparacion;
    private Date fechaInicio;
    private Date fechaEntregaEstimada;
    private Date fechaFinalizacion;
    private String descripcionProblema;
    private String estadoReparacion;
    private String historialCambios;
    private boolean retrasoEntrega;

    public Reparacion() {}
    public Reparacion(int idReparacion, Date fechaInicio, Date fechaEntregaEstimada, String descripcionProblema) {
        this.idReparacion = idReparacion;
        this.fechaInicio = fechaInicio;
        this.fechaEntregaEstimada = fechaEntregaEstimada;
        this.descripcionProblema = descripcionProblema;
        this.estadoReparacion = "En proceso";
        this.retrasoEntrega = false;
    }

    public int getIdReparacion() {
        return idReparacion; }
    public void setIdReparacion(int idReparacion) {
        this.idReparacion = idReparacion; }
    public Date getFechaInicio() {
        return fechaInicio; }
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio; }
    public Date getFechaEntregaEstimada() {
        return fechaEntregaEstimada; }
    public void setFechaEntregaEstimada(Date fechaEntregaEstimada) {
        this.fechaEntregaEstimada = fechaEntregaEstimada; }
    public Date getFechaFinalizacion() {
        return fechaFinalizacion; }
    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion; }
    public String getDescripcionProblema() {
        return descripcionProblema; }
    public void setDescripcionProblema(String descripcionProblema) {
        this.descripcionProblema = descripcionProblema; }
    public String getEstadoReparacion() {
        return estadoReparacion; }
    public void setEstadoReparacion(String estadoReparacion) {
        this.estadoReparacion = estadoReparacion; }
    public String getHistorialCambios() {
        return historialCambios; }
    public void setHistorialCambios(String historialCambios) {
        this.historialCambios = historialCambios; }
    public boolean isRetrasoEntrega() {
        return retrasoEntrega; }
    public void setRetrasoEntrega(boolean retrasoEntrega) {
        this.retrasoEntrega = retrasoEntrega; }

    public void revisarHistorialCambios() {}
    public void controlarTiemposEntrega() {}
    public void enviarAvisoRetraso() {}
    public void verificarReparacionesRealizadas() {}
    public String generarResumenServicio() {
        return "Resumen"; }
    public void imprimirTicketSalida() {}
}