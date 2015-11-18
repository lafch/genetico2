/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.solicitud.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.TsRequisitoSolicitud;

/**
 *
 * @author biblioteca
 */
public interface TsRequisitoSolicitudDAO {

    void attachClean(TsRequisitoSolicitud instance);

    void attachDirty(TsRequisitoSolicitud instance);

    void delete(TsRequisitoSolicitud persistentInstance);

    List findAll();

    List findByExample(TsRequisitoSolicitud instance);

    TsRequisitoSolicitud findById(Integer id);

    List findByProperty(String propertyName, Object value);

    TsRequisitoSolicitud merge(TsRequisitoSolicitud detachedInstance);

    void save(TsRequisitoSolicitud transientInstance);
    
}
