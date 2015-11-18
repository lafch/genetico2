/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.tablaSistema.hibernateSpringDao;

import java.util.ArrayList;
import java.util.List;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.TbMenu;
import net.uch.mapping.TbMenuRol;
import net.uch.mapping.TbRoles;
import net.uch.mapping.TbUsuario;
import net.uch.tablaSistema.managedBeans.bRol;
import net.uch.util.CommonWeb;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author LUIS
 */
public class HSRolDAOImpl extends HibernateDaoSupport implements HSRolDAO {

    @Override
    public List<TbRoles> getListarRolPorId2(int rol_id) {
        String hql = "select rol.Id, rol.RolTipo "
                + "from TbRoles rol "
                + " where rol.Id= " + rol_id
                + " order by rol.RolTipo";

        Query q = this.getSession().createQuery(hql);

        List tmp = q.list();
        List roles = new ArrayList();
        for (int i = 0; i < tmp.size(); i++) {
            Object[] objs = (Object[]) tmp.get(i);
            TbRoles rol = new TbRoles();
            rol.setId(Integer.valueOf(objs[0].toString()));
            rol.setRolTipo(objs[1].toString().toUpperCase());
            roles.add(i, rol);
        }
        return roles;

    }

    /*@Override
     public String getListarRolPorId(int rol_id){
     String sQuery;
     SQLQuery sqlQuery;
     String reporte = "";

     sQuery = "SELECT rol.rol_tipo FROM tb_roles rol "
     + "WHERE rol.rol_id= :rol_id";
     sqlQuery = this.getSession().createSQLQuery( sQuery );
     sqlQuery.setInteger( "rol_id", rol_id );
     reporte = ( sqlQuery.uniqueResult().toString() );
     return reporte;
     }*/
    @Override
    public List<TbRoles> getListarRol(int iRolId) {
        String sCondicion = " ";
        if (0 != iRolId) {
            sCondicion = "where rol.Id=" + iRolId;
        }
        String hql = "select rol.Id, rol.RolTipo, rol.RolActivo "
                + "from TbRoles rol "
                + sCondicion
                + " order by rol.RolTipo";

        Query q = this.getSession().createQuery(hql);

        List tmp = q.list();
        List roles = new ArrayList();
        for (int i = 0; i < tmp.size(); i++) {
            Object[] objs = (Object[]) tmp.get(i);
            TbRoles rol = new TbRoles();
            rol.setId(Integer.valueOf(objs[0].toString()));
            rol.setRolTipo(objs[1].toString().toUpperCase());
            roles.add(i, rol);
        }
        return roles;

    }

    @Override
    public TbRoles traerRolPorId(int iRolId) {
        TbRoles tbRoles;
        try {
            tbRoles = (TbRoles) this.getSession().
                    createCriteria(TbRoles.class).
                    add(Restrictions.eq("Id", iRolId)).
                    uniqueResult();
        } catch (Exception ex) {
            ex.printStackTrace();
            tbRoles = null;
        }
        return tbRoles;
    }

    @Override
    public List<TbRoles> getRoles() {
        String hql = "select rol.Id, rol.RolTipo "
                + "from TbRoles rol where rol.RolActivo = '1' "
                + "order by rol.RolTipo";

        Query q = this.getSession().createQuery(hql);

        List tmp = q.list();
        List<TbRoles> roles = new ArrayList<TbRoles>();
        for (int i = 0; i < tmp.size(); i++) {
            Object[] objs = (Object[]) tmp.get(i);
            TbRoles rol = new TbRoles();
            rol.setId(Integer.valueOf(objs[0].toString()));
            rol.setRolTipo(objs[1].toString().toUpperCase());

            roles.add(i, rol);
        }
        return roles;
    }
    
    @Override
    public List<TbMenu> menuPadre(int idrol) {
        List lstMenuAux = new ArrayList();
        List lstMenu = new ArrayList();
        String sQuery;
        Object[] objFila;

        sQuery = "  select tb_menu.men_id,tb_menu.men_codigo,tb_menu.men_descripcion,tb_menu.men_opc,tb_menu.men_accion,tb_menu_rol.menrol_activo,tb_menu.men_id_padre"
                + " from tb_menu \n"
                + " INNER JOIN tb_menu_rol ON tb_menu.men_id=tb_menu_rol.men_id \n"
                + " where tb_menu_rol.rol_id='"+idrol+"' and men_opc=0";
        Query q2 = this.getSession().createSQLQuery(sQuery);
        lstMenuAux = q2.list();
        for (Object object : lstMenuAux) {
            objFila = (Object[]) object;
            TbMenu men = new TbMenu();
            men.setMenId(CommonWeb.parseObjToInt(objFila[0]));
            men.setMenCodigo(CommonWeb.parseObjToString(objFila[1]));
            men.setMenDescripcion(CommonWeb.parseObjToString(objFila[2]));
            men.setMenOpc(CommonWeb.parseObjToString(objFila[3]));
            men.setMenActivo(CommonWeb.parseObjToString(objFila[5]));
            men.setMenIdPadre(CommonWeb.parseObjToInt(objFila[6]));
            if("1".equals(men.getMenActivo())){
                men.setSeleccionado(true);
            }else{
                men.setSeleccionado(false);
            }
            lstMenu.add(men);
        }
        return lstMenu;
    }
    
    @Override
    public List<TbMenu> menuHijos(int idrol) {
        List lstMenuAux = new ArrayList();
        List lstMenu = new ArrayList();
        String sQuery;
        Object[] objFila;

        sQuery =  " select tb_menu.men_id,tb_menu.men_codigo,tb_menu.men_descripcion,tb_menu.men_opc,tb_menu.men_accion,tb_menu_rol.menrol_activo,tb_menu.men_id_padre "
                + " from tb_menu \n"
                + " INNER JOIN tb_menu_rol ON tb_menu.men_id=tb_menu_rol.men_id \n"
                + " where tb_menu_rol.rol_id='"+idrol+"' and men_opc<>0";
        Query q2 = this.getSession().createSQLQuery(sQuery);
        lstMenuAux = q2.list();
        for (Object object : lstMenuAux) {
            objFila = (Object[]) object;
            TbMenu men = new TbMenu();
            men.setMenId(CommonWeb.parseObjToInt(objFila[0]));
            men.setMenCodigo(CommonWeb.parseObjToString(objFila[1]));
            men.setMenDescripcion(CommonWeb.parseObjToString(objFila[2]));
            men.setMenOpc(CommonWeb.parseObjToString(objFila[3]));
            men.setMenActivo(CommonWeb.parseObjToString(objFila[5]));
            men.setMenIdPadre(CommonWeb.parseObjToInt(objFila[6]));
            if("1".equals(men.getMenActivo())){
                men.setSeleccionado(true);
            }else{
                men.setSeleccionado(false);
            }
            lstMenu.add(men);
        }
        return lstMenu;
    }
    
    @Override
    public List<TbMenu> menuPadreActivo(int idrol) {
        List lstMenuAux = new ArrayList();
        List lstMenu = new ArrayList();
        String sQuery;
        Object[] objFila;

        sQuery = "  select tb_menu.men_id,tb_menu.men_codigo,tb_menu.men_descripcion,tb_menu.men_opc,tb_menu.men_accion,tb_menu_rol.menrol_activo,tb_menu.men_id_padre"
                + " from tb_menu \n"
                + " INNER JOIN tb_menu_rol ON tb_menu.men_id=tb_menu_rol.men_id \n"
                + " where tb_menu_rol.rol_id='"+idrol+"' and men_opc=0 and menrol_activo=1 and men_activo=1";
        Query q2 = this.getSession().createSQLQuery(sQuery);
        lstMenuAux = q2.list();
        for (Object object : lstMenuAux) {
            objFila = (Object[]) object;
            TbMenu men = new TbMenu();
            men.setMenId(CommonWeb.parseObjToInt(objFila[0]));
            men.setMenCodigo(CommonWeb.parseObjToString(objFila[1]));
            men.setMenDescripcion(CommonWeb.parseObjToString(objFila[2]));
            men.setMenOpc(CommonWeb.parseObjToString(objFila[3]));
            men.setMenActivo(CommonWeb.parseObjToString(objFila[5]));
            men.setMenIdPadre(CommonWeb.parseObjToInt(objFila[6]));
            if("1".equals(men.getMenActivo())){
                men.setSeleccionado(true);
            }else{
                men.setSeleccionado(false);
            }
            lstMenu.add(men);
        }
        return lstMenu;
    }
    
    @Override
    public List<TbMenu> menuHijosActivo(int idrol) {
        List lstMenuAux = new ArrayList();
        List lstMenu = new ArrayList();
        String sQuery;
        Object[] objFila;

        sQuery =  " select tb_menu.men_id,tb_menu.men_codigo,tb_menu.men_descripcion,tb_menu.men_opc,tb_menu.men_accion,tb_menu_rol.menrol_activo,tb_menu.men_id_padre "
                + " from tb_menu \n"
                + " INNER JOIN tb_menu_rol ON tb_menu.men_id=tb_menu_rol.men_id \n"
                + " where tb_menu_rol.rol_id='"+idrol+"' and men_opc<>0 and menrol_activo=1 and men_activo=1";
        Query q2 = this.getSession().createSQLQuery(sQuery);
        lstMenuAux = q2.list();
        for (Object object : lstMenuAux) {
            objFila = (Object[]) object;
            TbMenu men = new TbMenu();
            men.setMenId(CommonWeb.parseObjToInt(objFila[0]));
            men.setMenCodigo(CommonWeb.parseObjToString(objFila[1]));
            men.setMenDescripcion(CommonWeb.parseObjToString(objFila[2]));
            men.setMenOpc(CommonWeb.parseObjToString(objFila[3]));
            men.setMenActivo(CommonWeb.parseObjToString(objFila[5]));
            men.setMenIdPadre(CommonWeb.parseObjToInt(objFila[6]));
            if("1".equals(men.getMenActivo())){
                men.setSeleccionado(true);
            }else{
                men.setSeleccionado(false);
            }
            lstMenu.add(men);
        }
        return lstMenu;
    }
    
    @Override
    public void insertarMenuRol(TbMenuRol menu) throws DataAccessException {
        getHibernateTemplate().saveOrUpdate(menu);
    }
    
    @Override
    public List<TbMenu> listarArbolPorPadre(int idPadre,int idRol) {
        List lstMenuAux = new ArrayList();
        List lstMenu = new ArrayList();
        String sQuery;
        Object[] objFila;
        
        if(idPadre==0){
           sQuery = "select tb_menu.men_id,tb_menu.men_codigo,tb_menu.men_descripcion,tb_menu.men_opc,tb_menu.men_accion,tb_menu_rol.menrol_activo,tb_menu.men_id_padre "
                + " from tb_menu \n"
                + " INNER JOIN tb_menu_rol ON tb_menu.men_id=tb_menu_rol.men_id \n"
                + " where men_opc ='1' and rol_id='"+idRol+"'"; 
        }else{
           sQuery = "select tb_menu.men_id,tb_menu.men_codigo,tb_menu.men_descripcion,tb_menu.men_opc,tb_menu.men_accion,tb_menu_rol.menrol_activo,tb_menu.men_id_padre "
                + " from tb_menu \n"
                + " INNER JOIN tb_menu_rol ON tb_menu.men_id=tb_menu_rol.men_id \n"
                + " where tb_menu.men_id_padre='"+idPadre+"'"
                + " and rol_id='"+idRol+"'"; 
        }
        
        Query q2 = this.getSession().createSQLQuery(sQuery);
        lstMenuAux = q2.list();
        for (Object object : lstMenuAux) {
            objFila = (Object[]) object;
            TbMenu men = new TbMenu();
            men.setMenId(CommonWeb.parseObjToInt(objFila[0]));
            men.setMenCodigo(CommonWeb.parseObjToString(objFila[1]));
            men.setMenDescripcion(CommonWeb.parseObjToString(objFila[2]));
            men.setMenOpc(CommonWeb.parseObjToString(objFila[3]));
            men.setMenActivo(CommonWeb.parseObjToString(objFila[5]));
            men.setMenIdPadre(CommonWeb.parseObjToInt(objFila[6]));
            if("1".equals(men.getMenActivo())){
                men.setSeleccionado(true);
            }else{
                men.setSeleccionado(false);
            }
            lstMenu.add(men);
        }
        return lstMenu;
    }

    @Override
    public List seleccionarRoles() {
        return this.getSession().createCriteria(TbRoles.class, "rol").
                add(Restrictions.eq("rol.RolActivo", "1")).
                addOrder(Order.asc("RolTipo")).list();
    }
    
    @Override
    public void insertarRol(TbRoles rol) throws DataAccessException {
        getHibernateTemplate().save(rol);
    }

    @Override
    public void actualizarMenu(TbRoles rol) throws DataAccessException {
        getHibernateTemplate().update(rol);
    }

    @Override
    public void eliminarRol(String id) throws DataAccessException {
        String hqlUpdate = "update TbRoles set RolActivo = :activo where Id = :id";
        this.getSession().createQuery(hqlUpdate).setString("activo", "0").setString("id", id).executeUpdate();
    }

    @Override
    public int numeroMenu(int idRol) {
        int iNumMenus = 0;

        String sQuery;
        Object obj;

        sQuery = "select count(*) contar from tb_menu_rol where rol_id='" + idRol + "'";
        Query q2 = this.getSession().createSQLQuery(sQuery);
        obj = q2.uniqueResult();
        if (obj != null) {
            iNumMenus = CommonWeb.parseObjToInt(obj);
        }

        return iNumMenus;
    }

}
