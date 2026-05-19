package Dominio;

public class Equipo {
    private int idEquipo;
    private String numeroSerie;
    private String modelo;
    private String tipoEquipo;

    public Equipo() {}
    public Equipo(int idEquipo, String numeroSerie, String modelo, String tipoEquipo) {
        this.idEquipo = idEquipo;
        this.numeroSerie = numeroSerie;
        this.modelo = modelo;
        this.tipoEquipo = tipoEquipo;
    }

    public int getIdEquipo() {
        return idEquipo; }
    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo; }
    public String getNumeroSerie() {
        return numeroSerie; }
    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie; }
    public String getModelo() {
        return modelo; }
    public void setModelo(String modelo) {
        this.modelo = modelo; }
    public String getTipoEquipo() {
        return tipoEquipo; }
    public void setTipoEquipo(String tipoEquipo) {
        this.tipoEquipo = tipoEquipo; }

    public String obtenerDatosEquipo() {
        return modelo + " - " + numeroSerie; }
}