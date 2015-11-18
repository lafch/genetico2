/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.academica.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.AcAlumnoEstado;
import net.uch.mapping.AcEstadoSemestre;
import net.uch.util.BeanParametros;

/**
 *
 * @author biblioteca
 * 
 */
public interface AcAlumnoEstadoDAO {

    void attachClean(AcAlumnoEstado instance);

    void attachDirty(AcAlumnoEstado instance);

    void delete(AcAlumnoEstado persistentInstance);

    List findAll();

    List findByExample(AcAlumnoEstado instance);

    AcAlumnoEstado findById(Integer id);

    List findByProperty(String propertyName, Object value);

    AcAlumnoEstado merge(AcAlumnoEstado detachedInstance);

    void save(AcAlumnoEstado transientInstance);
    
    public AcAlumnoEstado findAlumnoEstado(AcAlumnoEstado acAlumnoEstado);
    public List findByProperties(List<BeanParametros> listaParametros );
    
    public List<AcEstadoSemestre> seleccionarEstado(Integer aluId, Integer SemId);
    public void eliminarEstado(Integer aluEstId);
    
}
