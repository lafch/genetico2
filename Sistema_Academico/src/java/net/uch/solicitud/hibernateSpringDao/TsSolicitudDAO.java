/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.solicitud.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.TsSolicitud;

/**
 *
 * @author biblioteca
 */
public interface TsSolicitudDAO {

    void attachClean(TsSolicitud instance);

    void attachDirty(TsSolicitud instance);

    boolean delete(TsSolicitud instance);

    List findAll();

    List findByExample(TsSolicitud instance);

    TsSolicitud findById(Integer id);

    List findByProperty(String propertyName, Object value);

    TsSolicitud merge(TsSolicitud detachedInstance);

    void save(TsSolicitud transientInstance);
    
}
