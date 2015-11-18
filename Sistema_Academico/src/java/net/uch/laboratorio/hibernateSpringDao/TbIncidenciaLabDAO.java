/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.laboratorio.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.TbIncidenciaLab;

/**
 *
 * @author biblioteca
 */
public interface TbIncidenciaLabDAO {

    void attachClean(TbIncidenciaLab instance);

    void attachDirty(TbIncidenciaLab instance);

    void delete(TbIncidenciaLab persistentInstance);

    List findAll();

    List findByExample(TbIncidenciaLab instance);

    TbIncidenciaLab findById(Integer id);

    List findByProperty(String propertyName, Object value);

    TbIncidenciaLab merge(TbIncidenciaLab detachedInstance);

    void save(TbIncidenciaLab transientInstance);
    
    public List<TbIncidenciaLab> getListarIncidenciaTipo(String tipo, String desc);
    
}
