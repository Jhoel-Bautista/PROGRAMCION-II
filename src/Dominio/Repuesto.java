package Dominio;

import DAO.ValidadorDuplicados;
import Util.Validador;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Jhoel
 */
// ✅ Cumple con Serializable
public class Repuesto implements ValidadorDuplicados, Serializable {
    private int idRepuesto;
    private String nombreRepuesto;
    private String descripcionRepuesto;
    private int stockDisponible;
    private double costoUnitario;

    public Repuesto() {
        this.idRepuesto = 0;
        this.nombreRepuesto = "Sin nombre";
        this.descripcionRepuesto = "Sin descripcion";
        this.stockDisponible = 0;
        this.costoUnitario = 0.0;
    }

    public Repuesto(int idRepuesto, String nombreRepuesto, String descripcionRepuesto, int stockDisponible, double costoUnitario) {
        this.idRepuesto = idRepuesto;
        this.nombreRepuesto = nombreRepuesto;
        this.descripcionRepuesto = descripcionRepuesto;
        this.stockDisponible = Math.max(stockDisponible, 0);
        this.costoUnitario = Math.max(costoUnitario, 0.0);
    }

    @Override
    public boolean validarDuplicado(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Repuesto otro = (Repuesto) obj;
        return this.idRepuesto == otro.idRepuesto ||
                (this.nombreRepuesto != null && this.nombreRepuesto.equalsIgnoreCase(otro.nombreRepuesto));
    }

    public Repuesto consultarRepuesto() {
        return this;
    }

    public void actualizarStock(int cantidad) {
        this.stockDisponible = Math.max(this.stockDisponible + cantidad, 0);
    }

    public int getIdRepuesto() { return idRepuesto; }
    public void setIdRepuesto(int idRepuesto) { this.idRepuesto = idRepuesto; }

    public String getNombreRepuesto() { return nombreRepuesto; }
    public void setNombreRepuesto(String nombreRepuesto) {
        if (nombreRepuesto != null && !nombreRepuesto.trim().isEmpty()) {
            this.nombreRepuesto = nombreRepuesto;
        } else {
            this.nombreRepuesto = "Sin nombre";
        }
    }

    public String getDescripcionRepuesto() { return descripcionRepuesto; }
    public void setDescripcionRepuesto(String descripcionRepuesto) {
        if (descripcionRepuesto != null && !descripcionRepuesto.trim().isEmpty()) {
            this.descripcionRepuesto = descripcionRepuesto;
        } else {
            this.descripcionRepuesto = "Sin descripcion";
        }
    }

    public int getStockDisponible() { return stockDisponible; }
    public void setStockDisponible(int stockDisponible) {
        if (stockDisponible >= 0) {
            this.stockDisponible = stockDisponible;
        } else {
            this.stockDisponible = 0;
        }
    }

    public double getCostoUnitario() { return costoUnitario; }
    public void setCostoUnitario(double costoUnitario) {
        if (costoUnitario >= 0.0) {
            this.costoUnitario = costoUnitario;
        } else {
            this.costoUnitario = 0.0;
        }
    }

    public boolean hayStock() {
        return stockDisponible > 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Repuesto repuesto = (Repuesto) obj;
        return idRepuesto == repuesto.idRepuesto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRepuesto);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Repuesto{");
        sb.append("idRepuesto=").append(idRepuesto);
        sb.append(", nombreRepuesto='").append(nombreRepuesto).append('\'');
        sb.append(", descripcionRepuesto='").append(descripcionRepuesto).append('\'');
        sb.append(", stockDisponible=").append(stockDisponible);
        sb.append(", costoUnitario=").append(costoUnitario);
        sb.append('}');
        return sb.toString();
    }
}