package net.uch.administrativa.hibernateSpringDao;

import java.util.List;
import org.springframework.dao.DataAccessException;
import net.uch.mapping.AdAlumnoTarifa;

public interface HSAlumnoTarifaDAO {

    public void insertarAlumno(AdAlumnoTarifa alu) throws DataAccessException;

    public List seleccionarAlumnoTarifa(String codAlu, String idEstado, int idEsp, int idFac, int idConPag, String apPaterno, String apMaterno, int sem_id) throws DataAccessException, java.sql.SQLException;

    public List seleccionarUnSaldo(int id) throws DataAccessException;

    public void insertarTotal(List<AdAlumnoTarifa> listaT) throws DataAccessException;

    public void cambiarEstado(int id, String estado) throws DataAccessException;

    public void cambiarActivoRetiro(int id,int sem_id);
    public void cambiarActivoReserva(int id);

    public List verificarPago(int alu_id, int con_pag, int sem_id);

    public void actualizarAlumnoTarifa(AdAlumnoTarifa alu) throws DataAccessException;

    public String seleccionarEstructuraPagosAlumno(int alu_id) throws DataAccessException;

    public AdAlumnoTarifa seleccionarUnAlumnoTarifa(int alutar_id) throws DataAccessException;

    public List seleccionarAlumnoTarifa(int alu_id) throws DataAccessException;
    public List<AdAlumnoTarifa> seleccionarAlumnoTarifa(int alu_id, int sem_id) throws DataAccessException;
    public List<AdAlumnoTarifa> seleccionarAlumnoTarifaGeneral(int alu_id, int sem_id) throws DataAccessException;
    public List seleccionarAlumnoTarifaBanco(int alu_id) throws DataAccessException;
}
