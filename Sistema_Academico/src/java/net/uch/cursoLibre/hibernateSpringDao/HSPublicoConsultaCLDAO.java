package net.uch.cursoLibre.hibernateSpringDao;

import java.util.Date;
import java.util.List;
import net.uch.cursoLibre.managedBeans.beans.BeanInfoMatricula;
import net.uch.mapping.ClPublicoConsulta;

public interface HSPublicoConsultaCLDAO {

    public List<ClPublicoConsulta> listarPublicoConsulta(int alu_id);

    public void agregarPublicoConsulta(ClPublicoConsulta consulta);

    public void modificarPublicoConsulta(Integer consultaId, String nuevaObs);

    public ClPublicoConsulta traerConsultaXConsultaId(int consultaId);

    public List traerUltimaConsultaXPubId(int alu_id);

    public List<ClPublicoConsulta> traerConsultasPorContactar(String area, String modulo, Integer taller, String horario, Date fechContactoIni, Date fechContactoFin,String procedencia);

    public void cambiarFechaContactoConsulta(ClPublicoConsulta consulta)throws Exception;

    public void modificarMatricula(int consultaId);

    public List cantidadesMatriculadosPorUsuario(int usu_id);

    public List<ClPublicoConsulta> listadoMatriculadosPorUsuario(int usu_id, Date fechaIni, Date fechaFin);
    public List<ClPublicoConsulta> listadoInformesPorUsuario(int usu_id, Date fechaIni, Date fechaFin);

    public List<BeanInfoMatricula> cantidadDematriculadosPorUsuario(Date fechaIni, Date fechaFin);


}
