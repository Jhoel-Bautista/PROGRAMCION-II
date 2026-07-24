package Dominio;

import DAO.ValidadorDuplicados;
import Util.Validador;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author Jhoel
 */
// ✅ Cumple con Serializable
public class Reparacion implements ValidadorDuplicados, Serializable {
    private int idReparacion;
    private Date fechaInicio;
    private Date fechaEntregaEstimada;
    private Date fechaFinalizacion;
    private String descripcionProblema;

    // 🔥 AQUÍ ESTÁ EL CAMBIO: Usamos TUS clases exactas 🔥
    private Diagnostico diagnostico;
    private Solucion solucion;

    private String estadoReparacion;
    private String historialCambios;
    private boolean retrasoEntrega;

    public Reparacion() {
        this.idReparacion = 0;
        this.fechaInicio = new Date();
        this.fechaEntregaEstimada = new Date();
        this.fechaFinalizacion = null;
        this.descripcionProblema = "Sin descripcion";
        this.diagnostico = null; // Nace vacío para usar los botones
        this.solucion = null;    // Nace vacío para usar los botones
        this.estadoReparacion = "En proceso";
        this.historialCambios = "Sin cambios";
        this.retrasoEntrega = false;
    }

    public Reparacion(int idReparacion, Date fechaInicio, Date fechaEntregaEstimada, String descripcionProblema) {
        this();
        this.idReparacion = idReparacion;
        this.fechaInicio = fechaInicio != null ? fechaInicio : new Date();
        this.fechaEntregaEstimada = fechaEntregaEstimada != null ? fechaEntregaEstimada : new Date();
        this.descripcionProblema = descripcionProblema;
    }

    public Reparacion(int idReparacion, Date fechaInicio, Date fechaEntregaEstimada, Date fechaFinalizacion,
                      String descripcionProblema, String estadoReparacion, String historialCambios, boolean retrasoEntrega) {
        this(idReparacion, fechaInicio, fechaEntregaEstimada, descripcionProblema);
        this.fechaFinalizacion = fechaFinalizacion;
        setEstadoReparacion(estadoReparacion);
        setHistorialCambios(historialCambios);
        this.retrasoEntrega = retrasoEntrega;
    }

    @Override
    public boolean validarDuplicado(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Reparacion otro = (Reparacion) obj;
        return this.getIdReparacion() == otro.getIdReparacion();
    }

    public void revisarHistorialCambios() {
        if (historialCambios == null || historialCambios.trim().isEmpty()) {
            historialCambios = "Sin cambios";
        }
    }

    public void controlarTiemposEntrega() {
        Date referencia = fechaFinalizacion != null ? fechaFinalizacion : new Date();
        retrasoEntrega = fechaEntregaEstimada != null && referencia.after(fechaEntregaEstimada);
    }

    public void enviarAvisoRetraso() {
        controlarTiemposEntrega();
        if (retrasoEntrega) {
            historialCambios = historialCambios + " | Aviso de retraso enviado";
        }
    }

    public void verificarReparacionesRealizadas() {
        if ("Terminado".equalsIgnoreCase(estadoReparacion)) {
            historialCambios = historialCambios + " | Reparación verificada";
        }
    }

    public String generarResumenServicio() {
        StringBuilder sb = new StringBuilder();
        sb.append("Reparacion ").append(idReparacion);
        sb.append(" | Estado: ").append(estadoReparacion);
        sb.append(" | Problema: ").append(descripcionProblema);
        sb.append(" | Retraso: ").append(retrasoEntrega);
        return sb.toString();
    }

    public void imprimirTicketSalida() {
        System.out.println(generarResumenServicio());
    }

    public void finalizar() {
        this.estadoReparacion = "Terminado";
        this.fechaFinalizacion = new Date();
    }

    public int getIdReparacion() { return idReparacion; }
    public void setIdReparacion(int idReparacion) { this.idReparacion = idReparacion; }

    public Date getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio != null ? fechaInicio : new Date(); }

    public Date getFechaEntregaEstimada() { return fechaEntregaEstimada; }
    public void setFechaEntregaEstimada(Date fechaEntregaEstimada) { this.fechaEntregaEstimada = fechaEntregaEstimada != null ? fechaEntregaEstimada : new Date(); }

    public Date getFechaFinalizacion() { return fechaFinalizacion; }
    public void setFechaFinalizacion(Date fechaFinalizacion) { this.fechaFinalizacion = fechaFinalizacion; }

    public String getDescripcionProblema() { return descripcionProblema; }
    public void setDescripcionProblema(String descripcionProblema) {
        if (Validador.validarDescripcion(descripcionProblema)) {
            this.descripcionProblema = descripcionProblema;
        } else {
            this.descripcionProblema = "Sin descripcion";
        }
    }

    // 🔥 GETTERS Y SETTERS ADAPTADOS A TUS CLASES 🔥
    public Diagnostico getDiagnostico() { return diagnostico; }
    public void setDiagnostico(Diagnostico diagnostico) { this.diagnostico = diagnostico; }

    public Solucion getSolucion() { return solucion; }
    public void setSolucion(Solucion solucion) { this.solucion = solucion; }

    public String getEstadoReparacion() { return estadoReparacion; }
    public void setEstadoReparacion(String estadoReparacion) {
        if (estadoReparacion != null && !estadoReparacion.trim().isEmpty()) {
            this.estadoReparacion = estadoReparacion;
        } else {
            this.estadoReparacion = "En proceso";
        }
    }

    public String getHistorialCambios() { return historialCambios; }
    public void setHistorialCambios(String historialCambios) {
        this.historialCambios = (historialCambios != null && !historialCambios.trim().isEmpty()) ? historialCambios : "Sin cambios";
    }

    public boolean isRetrasoEntrega() { return retrasoEntrega; }
    public boolean getRetrasoEntrega() { return retrasoEntrega; }
    public void setRetrasoEntrega(boolean retrasoEntrega) { this.retrasoEntrega = retrasoEntrega; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Reparacion that = (Reparacion) obj;
        return idReparacion == that.idReparacion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReparacion);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Reparacion{");
        sb.append("idReparacion=").append(idReparacion);
        sb.append(", estadoReparacion='").append(estadoReparacion).append('\'');
        sb.append(", retrasoEntrega=").append(retrasoEntrega);
        sb.append('}');
        return sb.toString();
    }
}