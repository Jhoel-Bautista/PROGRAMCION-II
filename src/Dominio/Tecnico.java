package Dominio;

import DAO.ValidadorDuplicados;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Jhoel
 */
// ✅ HEREDA DE USUARIO (extends Usuario)
public class Tecnico extends Usuario implements ValidadorDuplicados, Serializable {

    // ATRIBUTOS EXISTENTES INTACTOS
    private String numeroEmpleado;
    private String especialidad;
    private int nivelAcceso;
    private final List<Ticket> ticketsAsignados;
    private final List<Diagnostico> diagnosticosRegistrados;
    private final List<Solucion> solucionesRegistradas;
    private final List<Reparacion> reparacionesAsignadas;

    public Tecnico() {
        super();
        this.numeroEmpleado = "Sin numero";
        this.especialidad = "Sin especialidad";
        this.nivelAcceso = 0;
        this.ticketsAsignados = new ArrayList<>();
        this.diagnosticosRegistrados = new ArrayList<>();
        this.solucionesRegistradas = new ArrayList<>();
        this.reparacionesAsignadas = new ArrayList<>();
    }

    // CONSTRUCTOR EXISTENTE INTACTO
    public Tecnico(int idUsuario, String nombre, String email, String password,
                   String numeroEmpleado, String especialidad) {
        super(idUsuario, nombre, email, password, "Sin telefono");
        this.numeroEmpleado = (numeroEmpleado != null && !numeroEmpleado.trim().isEmpty()) ? numeroEmpleado : "Sin numero";
        this.especialidad = (especialidad != null && !especialidad.trim().isEmpty()) ? especialidad : "Sin especialidad";
        this.nivelAcceso = 1;
        this.ticketsAsignados = new ArrayList<>();
        this.diagnosticosRegistrados = new ArrayList<>();
        this.solucionesRegistradas = new ArrayList<>();
        this.reparacionesAsignadas = new ArrayList<>();
    }

    // ✅ CONSTRUCTOR QUE RECIBE EL TELÉFONO Y LO HEREDA
    public Tecnico(int idUsuario, String nombre, String email, String password, String telefono,
                   String numeroEmpleado, String especialidad, int nivelAcceso) {
        super(idUsuario, nombre, email, password, telefono); // Lo pasa a Usuario
        this.numeroEmpleado = (numeroEmpleado != null && !numeroEmpleado.trim().isEmpty()) ? numeroEmpleado : "Sin numero";
        this.especialidad = (especialidad != null && !especialidad.trim().isEmpty()) ? especialidad : "Sin especialidad";
        this.nivelAcceso = Math.max(nivelAcceso, 0);
        this.ticketsAsignados = new ArrayList<>();
        this.diagnosticosRegistrados = new ArrayList<>();
        this.solucionesRegistradas = new ArrayList<>();
        this.reparacionesAsignadas = new ArrayList<>();
    }

    @Override
    public boolean validarDuplicado(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tecnico otro = (Tecnico) obj;
        return this.getIdUsuario() == otro.getIdUsuario() ||
                (this.getNombre() != null && this.getNombre().equalsIgnoreCase(otro.getNombre())) ||
                (this.getEmail() != null && this.getEmail().equalsIgnoreCase(otro.getEmail())) ||
                (this.numeroEmpleado != null && this.numeroEmpleado.equalsIgnoreCase(otro.numeroEmpleado));
    }

    public void asignarTicket(Ticket ticket) {
        if (ticket != null && !ticketsAsignados.contains(ticket)) {
            ticket.asignarTecnico(this);
            ticketsAsignados.add(ticket);
        }
    }

    public void registrarDiagnostico(Diagnostico diagnostico) {
        if (diagnostico != null && !diagnosticosRegistrados.contains(diagnostico)) {
            diagnosticosRegistrados.add(diagnostico);
        }
    }

    public void registrarSolucion(Solucion solucion) {
        if (solucion != null && !solucionesRegistradas.contains(solucion)) {
            solucionesRegistradas.add(solucion);
        }
    }

    public void asignarReparacion(Reparacion reparacion) {
        if (reparacion != null && !reparacionesAsignadas.contains(reparacion)) {
            reparacionesAsignadas.add(reparacion);
            reparacion.setEstadoReparacion("Asignada");
        }
    }

    public void actualizarEstadoReparacion(Reparacion reparacion) {
        if (reparacion != null) {
            reparacion.setEstadoReparacion("En revisión técnica");
        }
    }

    @Override
    public String obtenerRol() {
        return "ROL: Agente Especialista del Sistema";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        Tecnico tecnico = (Tecnico) obj;
        return Objects.equals(numeroEmpleado, tecnico.numeroEmpleado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numeroEmpleado);
    }

    public String getNumeroEmpleado() { return numeroEmpleado; }
    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = (numeroEmpleado != null && !numeroEmpleado.trim().isEmpty()) ? numeroEmpleado : "Sin numero";
    }

    public String getCodigoTecnico() { return getNumeroEmpleado(); }
    public void setCodigoTecnico(String codigoTecnico) { setNumeroEmpleado(codigoTecnico); }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) {
        this.especialidad = (especialidad != null && !especialidad.trim().isEmpty()) ? especialidad : "Sin especialidad";
    }

    public int getNivelAcceso() { return nivelAcceso; }
    public void setNivelAcceso(int nivelAcceso) {
        this.nivelAcceso = Math.max(nivelAcceso, 0);
    }

    public List<Ticket> getTicketsAsignados() { return new ArrayList<>(ticketsAsignados); }
    public List<Diagnostico> getDiagnosticosRegistrados() { return new ArrayList<>(diagnosticosRegistrados); }
    public List<Solucion> getSolucionesRegistradas() { return new ArrayList<>(solucionesRegistradas); }
    public List<Reparacion> getReparacionesAsignadas() { return new ArrayList<>(reparacionesAsignadas); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tecnico{");
        sb.append("numeroEmpleado='").append(numeroEmpleado).append('\'');
        sb.append(", especialidad='").append(especialidad).append('\'');
        sb.append(", nivelAcceso=").append(nivelAcceso);
        sb.append("} ").append(super.toString());
        return sb.toString();
    }
}