package Dominio;

public class Repuesto  {
    private int idRepuesto;
    private String nombreRepuesto;
    private String descripcionRepuesto;
    private int stockDisponible;
    private double costoUnitario;

    public Repuesto() {}
    public Repuesto(int idRepuesto, String nombreRepuesto, String descripcionRepuesto, int stockDisponible, double costoUnitario) {
        this.idRepuesto = idRepuesto;
        this.nombreRepuesto = nombreRepuesto;
        this.descripcionRepuesto = descripcionRepuesto;
        this.stockDisponible = stockDisponible;
        this.costoUnitario = costoUnitario;
    }

    public int getIdRepuesto() {
        return idRepuesto; }
    public void setIdRepuesto(int idRepuesto) {
        this.idRepuesto = idRepuesto; }
    public String getNombreRepuesto() {
        return nombreRepuesto; }
    public void setNombreRepuesto(String nombreRepuesto) {
        this.nombreRepuesto = nombreRepuesto; }
    public String getDescripcionRepuesto() {
        return descripcionRepuesto; }
    public void setDescripcionRepuesto(String descripcionRepuesto) {
        this.descripcionRepuesto = descripcionRepuesto; }
    public int getStockDisponible() {
        return stockDisponible; }
    public void setStockDisponible(int stockDisponible) {
        this.stockDisponible = stockDisponible; }
    public double getCostoUnitario() {
        return costoUnitario; }
    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario; }

    public Repuesto consultarRepuesto() {
        return this; }
    public void actualizarStock(int cantidad) {
        this.stockDisponible = cantidad; }
}