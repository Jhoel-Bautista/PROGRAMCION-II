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
public class Solucion implements ValidadorDuplicados, Serializable {
    private int idSolucion;
    private Date fechaSolucion;
    private String detalleSolucion;

    public Solucion() {
        this.idSolucion = 0;
        this.fechaSolucion = new Date();
        this.detalleSolucion = "Sin detalle";
    }

    public Solucion(int idSolucion, Date fechaSolucion, String detalleSolucion) {
        this.idSolucion = idSolucion;
        this.fechaSolucion = fechaSolucion != null ? fechaSolucion : new Date();
        this.detalleSolucion = detalleSolucion;
    }

    public String obtenerDetalle() {
        return detalleSolucion;
    }

    @Override
    public boolean validarDuplicado(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Solucion otro = (Solucion) obj;
        return this.idSolucion == otro.idSolucion ||
                (this.detalleSolucion != null && this.detalleSolucion.equalsIgnoreCase(otro.detalleSolucion));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solucion solucion = (Solucion) o;
        return idSolucion == solucion.idSolucion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSolucion);
    }

    public int getIdSolucion() { return idSolucion; }
    public void setIdSolucion(int idSolucion) { this.idSolucion = idSolucion; }

    public Date getFechaSolucion() { return fechaSolucion; }
    public void setFechaSolucion(Date fechaSolucion) {
        if (fechaSolucion != null) {
            this.fechaSolucion = fechaSolucion;
        } else {
            this.fechaSolucion = new Date();
        }
    }

    public String getDetalleSolucion() { return detalleSolucion; }
    public void setDetalleSolucion(String detalleSolucion) {
        if (Validador.validarDescripcion(detalleSolucion)) {
            this.detalleSolucion = detalleSolucion;
        } else {
            this.detalleSolucion = "Sin detalle";
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Solucion{");
        sb.append("idSolucion=").append(idSolucion);
        sb.append(", fechaSolucion=").append(fechaSolucion);
        sb.append(", detalleSolucion='").append(detalleSolucion).append('\'');
        sb.append('}');
        return sb.toString();
    }
}