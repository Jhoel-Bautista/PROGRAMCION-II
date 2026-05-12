package Dominio;

public class Cliente extends Usuario {
    private String numeroCliente;
    private String telefono;
    private String direccion;

    public Cliente() {}

    public Cliente(int idUsuario, String nombre, String email, String password, String numeroCliente, String telefono, String direccion) {
        super(idUsuario, nombre, email, password);
        this.numeroCliente = numeroCliente;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getNumeroCliente() { return numeroCliente; }
    public void setNumeroCliente(String numeroCliente) { this.numeroCliente = numeroCliente; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String toString() {
        return super.toString() + " | Tel: " + telefono + " | Dir: " + direccion;
    }
}