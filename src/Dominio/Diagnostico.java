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
public class Diagnostico implements ValidadorDuplicados, Serializable {
    private int idDiagnostico;
    private Date fechaDiagnostico;
    private String detalleDiagnostico;

    public Diagnostico() {
        this.idDiagnostico = 0;
        this.fechaDiagnostico = new Date();
        this.detalleDiagnostico = "Sin detalle";
    }

    public Diagnostico(int idDiagnostico, Date fechaDiagnostico, String detalleDiagnostico) {
        this.idDiagnostico = idDiagnostico;
        this.fechaDiagnostico = fechaDiagnostico != null ? fechaDiagnostico : new Date();
        this.detalleDiagnostico = detalleDiagnostico;
    }

    @Override
    public boolean validarDuplicado(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Diagnostico otro = (Diagnostico) obj;
        return this.idDiagnostico == otro.idDiagnostico ||
                (this.detalleDiagnostico != null && this.detalleDiagnostico.equalsIgnoreCase(otro.detalleDiagnostico));
    }

    public String obtenerDetalle() {
        return detalleDiagnostico;
    }

    public int getIdDiagnostico() { return idDiagnostico; }
    public void setIdDiagnostico(int idDiagnostico) { this.idDiagnostico = idDiagnostico; }

    public Date getFechaDiagnostico() { return fechaDiagnostico; }
    public void setFechaDiagnostico(Date fechaDiagnostico) {
        if (fechaDiagnostico != null) {
            this.fechaDiagnostico = fechaDiagnostico;
        } else {
            this.fechaDiagnostico = new Date();
        }
    }

    public String getDetalleDiagnostico() { return detalleDiagnostico; }
    public void setDetalleDiagnostico(String detalleDiagnostico) {
        if (Validador.validarDescripcion(detalleDiagnostico)) {
            this.detalleDiagnostico = detalleDiagnostico;
        } else {
            this.detalleDiagnostico = "Sin detalle";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Diagnostico that = (Diagnostico) obj;
        return idDiagnostico == that.idDiagnostico;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDiagnostico);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Diagnostico{");
        sb.append("idDiagnostico=").append(idDiagnostico);
        sb.append(", fechaDiagnostico=").append(fechaDiagnostico);
        sb.append(", detalleDiagnostico='").append(detalleDiagnostico).append('\'');
        sb.append('}');
        return sb.toString();
    }
}