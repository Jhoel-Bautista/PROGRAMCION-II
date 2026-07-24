package Dominio;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class DiagramaClasesAssertEqualsTest {

    @Test
    void usuarioTieneAtributosYMetodosDelDiagrama() {
        Usuario usuario = new Usuario(1, "Ana Lopez", "ana@mail.com", "Ana123");

        assertEquals(1, usuario.getIdUsuario());
        assertEquals("Ana Lopez", usuario.getNombre());
        assertEquals("ana@mail.com", usuario.getEmail());
        assertEquals("Ana123", usuario.getPassword());
        assertEquals(true, usuario.login());

        usuario.actualizarDatos("Luis Perez", "luis@mail.com", "Luis123");
        assertEquals("Luis Perez", usuario.getNombre());
        assertEquals("luis@mail.com", usuario.getEmail());
        assertEquals("Luis123", usuario.getPassword());
    }

    @Test
    void clienteCreaYConsultaTicketsSegunDiagrama() {
        Cliente cliente = new Cliente(2, "Juan Perez", "juan@mail.com", "Juan123", "CLI-01", "0999999999", "Quito", new Ticket[0]);

        Ticket ticket = cliente.crearTicket();

        assertEquals("CLI-01", cliente.getNumeroCliente());
        assertEquals("0999999999", cliente.getTelefono());
        assertEquals("Quito", cliente.getDireccion());
        assertEquals(cliente, ticket.getClienteAsociado());
        assertEquals(ticket, cliente.consultarTicket(ticket.getIdTicket()));
    }

    @Test
    void tecnicoGestionaTicketDiagnosticoSolucionYReparacion() {
        Tecnico tecnico = new Tecnico(3, "Maria Vera", "maria@mail.com", "Maria123", "TEC-01", "Hardware", 2);
        Ticket ticket = new Ticket(10, "Equipo no enciende correctamente", Estado.PENDIENTE);
        Diagnostico diagnostico = new Diagnostico(5, new Date(), "Falla detectada en la placa principal");
        Solucion solucion = new Solucion(6, new Date(), "Cambio de componente dañado realizado");
        Reparacion reparacion = new Reparacion(7, new Date(), new Date(), "Revisión completa del equipo");

        tecnico.asignarTicket(ticket);
        tecnico.registrarDiagnostico(diagnostico);
        tecnico.registrarSolucion(solucion);
        tecnico.asignarReparacion(reparacion);
        tecnico.actualizarEstadoReparacion(reparacion);

        assertEquals("TEC-01", tecnico.getNumeroEmpleado());
        assertEquals("Hardware", tecnico.getEspecialidad());
        assertEquals(2, tecnico.getNivelAcceso());
        assertEquals(tecnico, ticket.getTecnicoAsignado());
        assertEquals(Estado.EN_PROCESO, ticket.getEstadoActual());
        assertEquals(diagnostico, tecnico.getDiagnosticosRegistrados().get(0));
        assertEquals(solucion, tecnico.getSolucionesRegistradas().get(0));
        assertEquals("En revisión técnica", reparacion.getEstadoReparacion());
    }

    @Test
    void ticketTieneRelacionesListasPrioridadYEstado() {
        Cliente cliente = new Cliente(4, "Pedro Mora", "pedro@mail.com", "Pedro123", "CLI-02", "0988888888", "Cuenca", new Ticket[0]);
        Tecnico tecnico = new Tecnico(5, "Lucia Rios", "lucia@mail.com", "Lucia123", "TEC-02", "Software", 3);
        Equipo equipo = new Equipo(8, "SN-98765", "Dell Inspiron", "Laptop");
        Date fecha = new Date();
        Ticket ticket = new Ticket(20, fecha, "Pantalla presenta fallas intermitentes", 3, cliente, tecnico, equipo, Estado.PENDIENTE);
        Diagnostico diagnostico = new Diagnostico(30, fecha, "Conector interno de pantalla flojo");
        Solucion solucion = new Solucion(40, fecha, "Reajuste de flex y prueba final correcta");

        ticket.agregarDiagnostico(diagnostico);
        ticket.agregarSolucion(solucion);
        ticket.actualizarEstado(Estado.RESUELTO);

        assertEquals(20, ticket.getIdTicket());
        assertEquals(fecha, ticket.getFechaCreacion());
        assertEquals("Pantalla presenta fallas intermitentes", ticket.getDescripcion());
        assertEquals(3, ticket.getPrioridad());
        assertEquals(cliente, ticket.getClienteAsociado());
        assertEquals(tecnico, ticket.getTecnicoAsignado());
        assertEquals(equipo, ticket.getEquipoAfectado());
        assertEquals(Estado.RESUELTO, ticket.getEstadoActual());
        assertEquals(diagnostico, ticket.getListaDiagnosticos().get(0));
        assertEquals(solucion, ticket.getListaSoluciones().get(0));
    }

    @Test
    void reparacionYRepuestoCumplenMetodosDelDiagrama() {
        Date inicio = new Date(1000);
        Date estimada = new Date(2000);
        Date finalizacion = new Date(3000);
        Reparacion reparacion = new Reparacion(9, inicio, estimada, finalizacion,
                "Problema con disco duro", "Terminado", "Cambio inicial", false);
        Repuesto repuesto = new Repuesto(11, "Disco SSD", "Unidad de almacenamiento", 4, 35.5);

        reparacion.controlarTiemposEntrega();
        reparacion.enviarAvisoRetraso();
        reparacion.verificarReparacionesRealizadas();
        repuesto.actualizarStock(3);

        assertEquals(9, reparacion.getIdReparacion());
        assertEquals(inicio, reparacion.getFechaInicio());
        assertEquals(estimada, reparacion.getFechaEntregaEstimada());
        assertEquals(finalizacion, reparacion.getFechaFinalizacion());
        assertEquals("Problema con disco duro", reparacion.getDescripcionProblema());
        assertEquals("Terminado", reparacion.getEstadoReparacion());
        assertEquals(true, reparacion.isRetrasoEntrega());
        assertEquals(true, reparacion.generarResumenServicio().contains("Reparacion 9"));
        assertEquals(repuesto, repuesto.consultarRepuesto());
        assertEquals(7, repuesto.getStockDisponible());
    }

    @Test
    void estadoEquipoDiagnosticoSolucionYTiendaTienenMetodosDelDiagrama() {
        Tienda tienda = Tienda.getInstancia();
        Usuario usuario = new Usuario(12, "Rosa Lima", "rosa@mail.com", "Rosa123");
        Ticket ticket = new Ticket(13, "Problema general del equipo", Estado.PENDIENTE);
        Equipo equipo = new Equipo(14, "ABC12", "HP Pavilion", "Laptop");
        Diagnostico diagnostico = new Diagnostico(15, new Date(), "Diagnóstico completo realizado");
        Solucion solucion = new Solucion(16, new Date(), "Solución aplicada correctamente");

        tienda.setNombre("Servicio Técnico Central");
        tienda.setDireccion("Quito Norte");
        tienda.setUsuario(usuario);
        tienda.setTicket(ticket);

        assertEquals(Estado.PENDIENTE.getNombreEstado(), Estado.PENDIENTE.obtenerNombreEstado());
        assertEquals(true, equipo.obtenerDatosEquipo().contains("HP Pavilion"));
        assertEquals("Diagnóstico completo realizado", diagnostico.obtenerDetalle());
        assertEquals("Solución aplicada correctamente", solucion.obtenerDetalle());
        assertEquals("Servicio Técnico Central", tienda.getNombre());
        assertEquals("Quito Norte", tienda.getDireccion());
        assertEquals(usuario, tienda.getUsuario());
        assertEquals(ticket, tienda.getTicket());
    }
}
