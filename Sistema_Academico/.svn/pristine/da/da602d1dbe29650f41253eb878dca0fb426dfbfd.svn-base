/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.solicitud.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.TsResponsable;

/**
 *
 * @author biblioteca
 */
public interface TsResponsableDAO {

    void attachClean(TsResponsable instance);

    void attachDirty(TsResponsable instance);

    void delete(TsResponsable persistentInstance);

    List findAll();

    List findByExample(TsResponsable instance);

    TsResponsable findById(Integer id);

    List findByProperty(String propertyName, Object value);

    TsResponsable merge(TsResponsable detachedInstance);

    void save(TsResponsable transientInstance);
    
}
