package Dominio;

public class Tecnico extends Usuario {
    private String numeroEmpleado;
    private String especialidad;
    private int nivelAcceso;

    public Tecnico() {}

    public Tecnico(int idUsuario, String nombre, String email, String password, String numeroEmpleado, String especialidad, int nivelAcceso) {
        super(idUsuario, nombre, email, password);
        this.numeroEmpleado = numeroEmpleado;
        this.especialidad = especialidad;
        this.nivelAcceso = nivelAcceso;
    }

    public String getNumeroEmpleado() { return numeroEmpleado; }
    public void setNumeroEmpleado(String numeroEmpleado) { this.numeroEmpleado = numeroEmpleado; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public int getNivelAcceso() { return nivelAcceso; }
    public void setNivelAcceso(int nivelAcceso) { this.nivelAcceso = nivelAcceso; }

    public String toString() {
        return super.toString() + " | Especialidad: " + especialidad + " | Nivel: " + nivelAcceso;
    }
}