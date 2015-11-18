/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.solicitud.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.TsFut;

/**
 *
 * @author biblioteca
 */
public interface TsFutDAO {

    void attachClean(TsFut instance);

    void attachDirty(TsFut instance);

    void delete(TsFut persistentInstance);

    List findAll();

    List findByExample(TsFut instance);

    TsFut findById(Integer id);

    List findByProperty(String propertyName, Object value);

    TsFut merge(TsFut detachedInstance);

    void save(TsFut transientInstance);
    
}
