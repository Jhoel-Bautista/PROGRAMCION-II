package Dominio;

/** Estados permitidos para un ticket. */
public enum Estado {
    PENDIENTE(1, "Pendiente"),
    EN_PROCESO(2, "En proceso"),
    RESUELTO(3, "Resuelto"),
    CERRADO(4, "Cerrado");

    private final int idEstado;
    private final String nombreEstado;

    Estado(int idEstado, String nombreEstado) {
        this.idEstado = idEstado;
        this.nombreEstado = nombreEstado;
    }

    public int getId() { return idEstado; }
    public String getNombre() { return nombreEstado; }
    public int getIdEstado() { return idEstado; }
    public String getNombreEstado() { return nombreEstado; }

    public String obtenerNombreEstado() {
        return nombreEstado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nombreEstado);
        return sb.toString();
    }
}
