package DAO;

import Dominio.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DAOProyectoFinalTest {
    private DAOFactory fabrica;

    @BeforeEach
    void preparar() {
        fabrica = DAOFactory.getInstancia();
        fabrica.crearClienteDAO().limpiar();
        fabrica.crearTecnicoDAO().limpiar();
        fabrica.crearTicketDAO().limpiar();
        fabrica.crearEquipoDAO().limpiar();
        fabrica.crearRepuestoDAO().limpiar();
        fabrica.crearReparacionDAO().limpiar();
    }

    @Test
    void clienteDAOHaceCRUDCompleto() {
        ClienteDAO dao = fabrica.crearClienteDAO();
        Cliente cliente = new Cliente(1, "Juan Perez", "juan@mail.com", "Juan123", "CLI-1", "0999999999", "Quito", new Ticket[0]);

        dao.nuevo(cliente);
        assertEquals(1, dao.listar().length);
        assertEquals("Juan Perez", dao.buscarPorID(1).getNombre());

        Cliente editado = new Cliente(1, "Juan Perez", "juan@mail.com", "Juan123", "CLI-1", "0988888888", "Guayaquil", new Ticket[0]);
        dao.editar(editado);
        assertEquals("Guayaquil", dao.buscarPorID(1).getDireccion());

        dao.eliminar(1);
        assertEquals(0, dao.listar().length);
    }

    @Test
    void ticketDAOEvitaDuplicadosPorEquals() {
        TicketDAO dao = fabrica.crearTicketDAO();
        dao.nuevo(new Ticket(1, "Equipo no enciende", Estado.PENDIENTE));
        dao.nuevo(new Ticket(1, "Otro texto", Estado.EN_PROCESO));

        assertEquals(1, dao.listar().length);
        assertEquals(Estado.PENDIENTE, dao.buscarPorID(1).getEstadoActual());
    }

    @Test
    void tiendaDelegaEnDAOs() {
        Tienda tienda = Tienda.getInstancia();
        tienda.limpiarRegistros();
        tienda.agregarTecnico(new Tecnico(1, "Ana Lopez", "ana@mail.com", "Ana123", "EMP-1", "Hardware", 2));

        assertEquals(1, tienda.getTecnicosRegistrados().length);
        assertEquals("EMP-1", tienda.buscarTecnicoPorId(1).getNumeroEmpleado());
    }
}
