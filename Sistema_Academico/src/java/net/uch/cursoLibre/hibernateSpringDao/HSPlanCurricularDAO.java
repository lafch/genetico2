package net.uch.cursoLibre.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.ClArbolAcademico;
import net.uch.mapping.ClPlancurricular;

public interface HSPlanCurricularDAO {

    public List seleccionarPlanCurricular(int mod_id, String descripcion) throws Exception;

    public void insertarPlanCurricular(ClPlancurricular planCurricular) throws Exception;

    public void actualizarPlanCurricular(ClPlancurricular planCurricular) throws Exception;

    public void eliminarPlanCurricular(int id) throws Exception;

    public ClPlancurricular buscarPlanCurricular(int id) throws Exception;

    public void desactualizarPlanes(int mod_id) throws Exception;

    public ClPlancurricular buscarPlanActual(int mod_id) throws Exception;

    public ClArbolAcademico buscarModulo(int mod_id) throws Exception;
    
    public ClPlancurricular buscarPlan(int cur_id) throws Exception;
}
