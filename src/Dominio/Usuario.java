package Dominio;

import Util.Validador;
import java.io.Serializable;
import java.util.Objects;

/** Entidad base de usuarios del sistema. */
public class Usuario implements Serializable, Comparable<Usuario> {
    private int idUsuario;
    private String nombre;
    private String email;
    private String password;
    // ✅ EL ÚNICO LUGAR DONDE SE DEFINE EL TELÉFONO (Como pide el diagrama)
    private String telefono;

    public Usuario() {
        this.idUsuario = 0;
        this.nombre = "Sin nombre";
        this.email = "Sin correo";
        this.password = "Sin contrasena";
        this.telefono = "Sin telefono";
    }

    // Constructor existente INTACTO
    public Usuario(int idUsuario, String nombre, String email, String password) {
        this();
        this.idUsuario = idUsuario;
        setNombre(nombre);
        setEmail(email);
        setPassword(password);
    }

    // Constructor adicional para el teléfono
    public Usuario(int idUsuario, String nombre, String email, String password, String telefono) {
        this(idUsuario, nombre, email, password);
        setTelefono(telefono);
    }

    public boolean login() {
        return Validador.validarEmail(email) && Validador.validarPassword(password);
    }

    public void actualizarDatos() {
        setNombre(nombre);
        setEmail(email);
        setPassword(password);
    }

    public void actualizarDatos(String nombre, String email, String password) {
        setNombre(nombre);
        setEmail(email);
        setPassword(password);
    }

    public String obtenerRol() {
        return "ROL: Usuario del sistema";
    }

    public final String obtenerDatosCredenciales() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(idUsuario);
        sb.append(" | Email: ").append(email);
        return sb.toString();
    }

    public void cambiarPassword(String nuevaPassword) {
        setPassword(nuevaPassword);
    }

    public void cambiarPassword(String nuevaPassword, String confirmacion) {
        if (nuevaPassword != null && nuevaPassword.equals(confirmacion)) {
            setPassword(nuevaPassword);
        } else {
            System.out.println("Error: Las contraseñas no coinciden.");
        }
    }

    @Override
    public int compareTo(Usuario otro) {
        return this.nombre.compareToIgnoreCase(otro.nombre);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario usuario = (Usuario) obj;
        return this.idUsuario == usuario.idUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario);
    }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty() && !nombre.matches(".*\\d.*")) {
            this.nombre = nombre;
        } else {
            this.nombre = "Sin nombre";
            System.out.println("Error: Nombre invalido");
        }
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (email != null && !email.trim().isEmpty() && email.contains("@")) {
            this.email = email;
        } else {
            this.email = "Sin correo";
            System.out.println("Error: Correo invalido");
        }
    }

    public String getPassword() { return password; }
    public void setPassword(String password) {
        if (password != null && !password.trim().isEmpty() && password.length() > 3) {
            this.password = password;
        } else {
            this.password = "Sin contrasena";
            System.out.println("Error: Contrasena muy corta o invalida");
        }
    }

    // ✅ GETTER Y SETTER RESPECTIVOS DEL TELÉFONO
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) {
        if(telefono != null && !telefono.trim().isEmpty()) {
            this.telefono = telefono;
        } else {
            this.telefono = "Sin telefono";
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario{");
        sb.append("idUsuario=").append(idUsuario);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", telefono='").append(telefono).append('\'');
        sb.append('}');
        return sb.toString();
    }
}