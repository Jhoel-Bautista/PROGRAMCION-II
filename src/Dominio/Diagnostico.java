package Dominio;

import java.util.Date;

public class Diagnostico  {
    private int idDiagnostico;
    private Date fechaDiagnostico;
    private String detalleDiagnostico;

    public Diagnostico() {}
    public Diagnostico(int idDiagnostico, Date fechaDiagnostico, String detalleDiagnostico) {
        this.idDiagnostico = idDiagnostico;
        this.fechaDiagnostico = fechaDiagnostico;
        this.detalleDiagnostico = detalleDiagnostico;
    }

    public int getIdDiagnostico() {
        return idDiagnostico; }
    public void setIdDiagnostico(int idDiagnostico) {
        this.idDiagnostico = idDiagnostico; }
    public Date getFechaDiagnostico() {
        return fechaDiagnostico; }
    public void setFechaDiagnostico(Date fechaDiagnostico) {
        this.fechaDiagnostico = fechaDiagnostico; }
    public String getDetalleDiagnostico() {
        return detalleDiagnostico; }
    public void setDetalleDiagnostico(String detalleDiagnostico) {
        this.detalleDiagnostico = detalleDiagnostico; }

    public String obtenerDetalle() {
        return detalleDiagnostico; }
}