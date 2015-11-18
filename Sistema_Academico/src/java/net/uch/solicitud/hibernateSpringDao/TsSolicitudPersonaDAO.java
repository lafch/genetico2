/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.solicitud.hibernateSpringDao;

import java.io.File;
import java.util.Date;
import java.util.List;
import net.uch.mapping.TsSolicitudPersona;

/**
 *
 * @author biblioteca
 */
public interface TsSolicitudPersonaDAO {

    void attachClean(TsSolicitudPersona instance);

    void attachDirty(TsSolicitudPersona instance);

    void delete(TsSolicitudPersona persistentInstance);

    List findAll();

    List findByExample(TsSolicitudPersona instance);

    TsSolicitudPersona findById(Integer id);

    List findByProperty(String propertyName, Object value);

    TsSolicitudPersona merge(TsSolicitudPersona detachedInstance);

    void save(TsSolicitudPersona transientInstance);
    List findFechasEstado(Date fecha_ini, Date fecha_fin,String w_estado);
    
    public List<File> listarArchivos(String ruta_archivo);
}
