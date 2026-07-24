package Dominio;

import DAO.ValidadorDuplicados;
import Util.Validador;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Jhoel
 */
// ✅ Cumple con Serializable
public class Equipo implements ValidadorDuplicados, Serializable {
    private int idEquipo;
    private String numeroSerie;
    private String modelo;
    private String tipoEquipo;

    public Equipo() {
        this.idEquipo = 0;
        this.numeroSerie = "Sin serie";
        this.modelo = "Sin modelo";
        this.tipoEquipo = "Sin tipo";
    }

    public Equipo(int idEquipo, String numeroSerie, String modelo, String tipoEquipo) {
        this.idEquipo = idEquipo;
        this.numeroSerie = numeroSerie;
        this.modelo = modelo;
        this.tipoEquipo = tipoEquipo;
    }

    @Override
    public boolean validarDuplicado(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Equipo otro = (Equipo) obj;
        return this.idEquipo == otro.idEquipo ||
                (this.numeroSerie != null && this.numeroSerie.equalsIgnoreCase(otro.numeroSerie));
    }

    public String obtenerDatosEquipo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Equipo ").append(idEquipo);
        sb.append(" | Serie: ").append(numeroSerie);
        sb.append(" | Modelo: ").append(modelo);
        sb.append(" | Tipo: ").append(tipoEquipo);
        return sb.toString();
    }

    public int getIdEquipo() { return idEquipo; }
    public void setIdEquipo(int idEquipo) { this.idEquipo = idEquipo; }

    public String getNumeroSerie() { return numeroSerie; }
    public void setNumeroSerie(String numeroSerie) {
        if (Validador.validarNumeroSerie(numeroSerie)) {
            this.numeroSerie = numeroSerie;
        } else {
            this.numeroSerie = "Sin serie";
        }
    }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) {
        if (modelo != null && !modelo.trim().isEmpty()) {
            this.modelo = modelo;
        } else {
            this.modelo = "Sin modelo";
        }
    }

    public String getTipoEquipo() { return tipoEquipo; }
    public void setTipoEquipo(String tipoEquipo) {
        if (tipoEquipo != null && !tipoEquipo.trim().isEmpty()) {
            this.tipoEquipo = tipoEquipo;
        } else {
            this.tipoEquipo = "Sin tipo";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Equipo equipo = (Equipo) obj;
        return idEquipo == equipo.idEquipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEquipo);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Equipo{");
        sb.append("idEquipo=").append(idEquipo);
        sb.append(", numeroSerie='").append(numeroSerie).append('\'');
        sb.append(", modelo='").append(modelo).append('\'');
        sb.append(", tipoEquipo='").append(tipoEquipo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}