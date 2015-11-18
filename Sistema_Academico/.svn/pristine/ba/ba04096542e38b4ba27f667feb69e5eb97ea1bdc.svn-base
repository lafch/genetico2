/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.solicitud.hibernateSpringDao;

import java.util.List;
import net.uch.mapping.TsRequisito;

/**
 *
 * @author biblioteca
 */
public interface TsRequisitoDAO {

    void attachClean(TsRequisito instance);

    void attachDirty(TsRequisito instance);

    boolean delete(TsRequisito persistentInstance);

    List findAll();

    List findByExample(TsRequisito instance);

    TsRequisito findById(Integer id);

    List findByProperty(String propertyName, Object value);

    TsRequisito merge(TsRequisito detachedInstance);

    void save(TsRequisito transientInstance);
    
}
