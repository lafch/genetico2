package net.uch.academica.hibernateSpringDao;

import java.util.ArrayList;
import org.springframework.dao.DataAccessException;
import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import net.uch.mapping.AcSisEvaluacion;
import net.uch.mapping.AcSisEvaDetalle;
import net.uch.mapping.AcSisEvaPersonalizado;
import net.uch.util.CommonDAO;
import net.uch.util.CommonWeb;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;

public class HSSistemaEvaluacionDAOImpl extends HibernateDaoSupport implements HSSistemaEvaluacionDAO {

    @Override
    public void insertarSistemaEvaluacion( AcSisEvaluacion siseva ) throws DataAccessException {
        getHibernateTemplate().save( siseva );
    }

    @Override
    public List seleccionarSistemaEvaluacion( String descripcion ) throws DataAccessException {
        return this.getSession().createCriteria( AcSisEvaluacion.class ).
                add( Expression.like( "SisevaNombre", "%" + descripcion + "%" ) ).
                add( Expression.eq( "SisevaActivo", "1" ) ).list();
    }

    @Override
    public List seleccionarSistemaEvaluacion( int id ) throws DataAccessException {
        return this.getSession().createCriteria( AcSisEvaluacion.class ).
                add( Expression.eq( "Id", id ) ).list();
    }

    @Override
    public void actualizarSistemaEvaluacion( AcSisEvaluacion siseva ) throws DataAccessException {
        getHibernateTemplate().saveOrUpdate( siseva );
    }

    @Override
    public void eliminarSistemaEvaluacion( int id ) throws DataAccessException {
        String query = "update AcSisEvaluacion set SisevaActivo=:activo where Id= :id";
        this.getSession().createQuery( query ).setString( "id", "" + id ).setString( "activo", "0" ).executeUpdate();
    }

    @Override
    public List seleccionarComboSistemaEvaluacion() throws DataAccessException {
        return this.getSession().createCriteria( AcSisEvaluacion.class ).
                add( Expression.eq( "SisevaActivo", "1" ) ).list();
    }

    @Override
    public List<AcSisEvaPersonalizado> listarSisEvaPerPlantilla( int iSisevaId, int iSisevaDetId ) {
        String sQuery;
        SQLQuery query;
        AcSisEvaDetalle sed;
        AcSisEvaPersonalizado sep;
        Object[] aobjFila;
        List lstSisEvaPerPlantObj;
        List<AcSisEvaPersonalizado> lstSisEvaPerPlantilla;
        lstSisEvaPerPlantilla = new ArrayList<AcSisEvaPersonalizado>();
        try {
            sQuery = "SELECT sepp.* FROM ac_sis_evaluacion se \n"
                    + " INNER JOIN ac_sis_eva_detalle sed ON(se.siseva_id=sed.siseva_id) \n"
                    + " INNER JOIN ac_sis_eva_personalizado_plantilla sepp ON(sed.siseva_detalle_id=sepp.siseva_detalle_id) \n"
                    + " WHERE se.siseva_id=:siseva_id AND sepp.siseva_per_activo=1 ";
            if ( iSisevaDetId != 0 ) {
                sQuery += "AND sed.siseva_detalle_id=:siseva_det_id ";
            }
            sQuery += "ORDER BY sepp.siseva_per_orden;";
            query = this.getSession().createSQLQuery( sQuery );
            query.setInteger( "siseva_id", iSisevaId );
            if ( iSisevaDetId != 0 ) {
                query.setInteger( "siseva_det_id", iSisevaDetId );
            }
            lstSisEvaPerPlantObj = query.list();
            for ( Object obj : lstSisEvaPerPlantObj ) {
                aobjFila = (Object[])obj;
                sep = new AcSisEvaPersonalizado();
                sep.setId(CommonWeb.parseObjToInt( aobjFila[0] ) );
                sep.setSisevaPerNombre(CommonWeb.parseObjToString( aobjFila[1] ) );
                sep.setSisevaPerExaSemana( CommonWeb.parseObjToString( aobjFila[2] ) );
                sed = CommonDAO.getSistemaEvaluacionDAO().seleccionarSisEvaDetetallePorId( CommonWeb.parseObjToInt( aobjFila[3] ) );
                sep.setSisevaDetalle( sed );
                sep.setSisevaCodigo( CommonWeb.parseObjToString( aobjFila[4] ) );
                sep.setSisevaPerPeso( aobjFila[5] + "");
                sep.setSisevaPerOrden( CommonWeb.parseObjToInt( aobjFila[6] ) );
                sep.setSisevaPerActivo( "1" );
                lstSisEvaPerPlantilla.add( sep );
            }

        } catch ( Exception ex ) {
            ex.printStackTrace();
            lstSisEvaPerPlantilla = new ArrayList<AcSisEvaPersonalizado>();
        }

        return lstSisEvaPerPlantilla;
    }
    @Override
    public AcSisEvaDetalle seleccionarSisEvaDetetallePorId( int iSisEvaDetId ) {//iSisEvaDetId
        Criteria c = this.getSession().createCriteria( AcSisEvaDetalle.class ).
                add( Restrictions.eq( "SisevaDetalleActivo", "1" ) ).
                add( Restrictions.eq( "Id", iSisEvaDetId ) );
        return (AcSisEvaDetalle) c.uniqueResult();
    }
}
