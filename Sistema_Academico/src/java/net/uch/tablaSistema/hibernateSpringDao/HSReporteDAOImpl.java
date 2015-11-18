/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.tablaSistema.hibernateSpringDao;

import java.util.ArrayList;
import java.util.List;
import net.uch.mapping.TbReporte;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author LUIS
 */
public class HSReporteDAOImpl extends HibernateDaoSupport implements HSReporteDAO{
    
    @Override
    public List<TbReporte> getListarReporte(){
    String hql = "select rep.repId, rep.repDescripcion,rep.rol "
                + "from TbReporte rep "
                + " where rep.repActivo = '1' "
                + " order by rep.repDescripcion";
        Query q = this.getSession().createQuery( hql );
        List tmp = q.list();
        List<TbReporte> reportes = new ArrayList<TbReporte>();
        for ( int i = 0; i < tmp.size(); i++ ) {
            Object[] objs = (Object[]) tmp.get( i );
            TbReporte rep = new TbReporte();
            rep.setRepDescripcion( objs[1].toString().toUpperCase() );
            rep.setRepId( Integer.valueOf( objs[0].toString() ) );
            rep.setRol(Integer.valueOf( objs[2].toString() ));
            reportes.add( i, rep );
        }
        return reportes;
    }
       
    @Override
    public List<TbReporte> getListarReporteUsu(int usu_id){
    SQLQuery sqlQuery;
        List lista;
        
        String hqlQuery = "SELECT rep.rep_id, rep.rep_descripcion,rep.rol FROM tb_reporte rep "
                + "INNER JOIN tb_reporte_rol repRol ON(rep.rep_id = repRol.rep_id) "
                + "WHERE rep.rep_activo "
                + "AND repRol.repor_rol_activo='1' "
                + "and repRol.usu_id=:usu_id ";
 //       sqlQuery = this.getSession().createSQLQuery( hqlQuery + " GROUP BY a.alu_id ORDER BY a.alu_appaterno, a.alu_apmaterno, a.alu_nombres" );
         sqlQuery = this.getSession().createSQLQuery( hqlQuery + " ORDER BY rep.rep_descripcion" );
         sqlQuery.setParameter("usu_id", usu_id );

        lista = sqlQuery.list();
        List<TbReporte> reportes = new ArrayList<TbReporte>();
        for ( int i = 0; i < lista.size(); i++ ) {
            Object[] objs = (Object[]) lista.get( i );
            TbReporte rep = new TbReporte();
            rep.setRepDescripcion( objs[1].toString().toUpperCase() );
            rep.setRepId( Integer.valueOf( objs[0].toString() ) );
            rep.setRol(Integer.valueOf( objs[2].toString() ));
            reportes.add( i, rep );
        }
        return reportes;
    }
    
    /*@Override
    public List<TbReporte> getListarReporteUsu(int usu_id){
        System.out.println("xxxxx" + usu_id);
        //FROM Usuario u left outer join u.direccion as d left outer join u.permisos as p GROUP BY u.nombre
    String hql = "select rep.repId, rep.repDescripcion,rep.rol "
                + "from TbReporte rep inner join rep.repId "
                + " where rep.repActivo = '1' and rep.usuId=:usu_id"
                + " order by rep.repDescripcion";
        System.out.println("hql" + hql);
        Query q = this.getSession().createQuery( hql );
        q.setParameter("usu_id", usu_id );
        List tmp = q.list();
        System.out.println("la cantidad es " + tmp.size());
        List<TbReporte> reportes = new ArrayList<TbReporte>();
        
        for ( int i = 0; i < tmp.size(); i++ ) {
            Object[] objs = (Object[]) tmp.get( i );
            TbReporte rep = new TbReporte();
            rep.setRepDescripcion( objs[1].toString().toUpperCase() );
            rep.setRepId( Integer.valueOf( objs[0].toString() ) );
            rep.setRol(Integer.valueOf( objs[2].toString() ));
            reportes.add( i, rep );
        }
        return reportes;
    }*/
}
    
    

