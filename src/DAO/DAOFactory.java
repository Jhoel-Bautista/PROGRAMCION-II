package DAO;

import Dominio.Tecnico;

/**
 * @author Jhoel
 */
public class  DAOFactory {
    private static DAOFactory instancia;

    private DAOFactory() {
    }

    public static synchronized DAOFactory getInstancia() {
        if (instancia == null) {
            instancia = new DAOFactory();
        }
        return instancia;
    }

    public ClienteDAO crearClienteDAO() { return ClienteDAO.getInstancia(); }
    public TecnicoDAO crearTecnicoDAO() { return TecnicoDAO.getInstancia(); }
    public TicketDAO crearTicketDAO() { return TicketDAO.getInstancia(); }
    public EquipoDAO crearEquipoDAO() { return EquipoDAO.getInstancia(); }
    public RepuestoDAO crearRepuestoDAO() { return RepuestoDAO.getInstancia(); }
    public ReparacionDAO crearReparacionDAO() { return ReparacionDAO.getInstancia(); }
    public DiagnosticoDAO crearDiagnosticoDAO() { return DiagnosticoDAO.getInstancia(); }
    public SolucionDAO crearSolucionDAO() { return SolucionDAO.getInstancia(); }


}