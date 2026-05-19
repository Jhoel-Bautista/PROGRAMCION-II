package Dominio;

import java.util.Date;

public class Solucion {
    private int idSolucion;
    private Date fechaSolucion;
    private String detalleSolucion;

    public Solucion() {}
    public Solucion(int idSolucion, Date fechaSolucion, String detalleSolucion) {
        this.idSolucion = idSolucion;
        this.fechaSolucion = fechaSolucion;
        this.detalleSolucion = detalleSolucion;
    }

    public int getIdSolucion() {
        return idSolucion; }
    public void setIdSolucion(int idSolucion) {
        this.idSolucion = idSolucion; }
    public Date getFechaSolucion() {
        return fechaSolucion; }
    public void setFechaSolucion(Date fechaSolucion) {
        this.fechaSolucion = fechaSolucion; }
    public String getDetalleSolucion() {
        return detalleSolucion; }
    public void setDetalleSolucion(String detalleSolucion) {
        this.detalleSolucion = detalleSolucion; }

    public String obtenerDetalle() {
        return detalleSolucion; }
}