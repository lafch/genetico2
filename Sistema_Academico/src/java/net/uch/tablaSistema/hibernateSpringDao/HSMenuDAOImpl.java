/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.tablaSistema.hibernateSpringDao;

import java.util.ArrayList;
import java.util.List;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcPagina;
import net.uch.mapping.TbMenu;
import net.uch.mapping.TbMenuRol;
import net.uch.mapping.TbRoles;
import net.uch.util.CommonWeb;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author cesardl
 */
public class HSMenuDAOImpl extends HibernateDaoSupport implements HSMenuDAO {

    @Override
    public List<TbMenu> seleccionToolBar(int rol_id) {
        return this.getSession().createCriteria(TbMenu.class, "men").
                createCriteria("men.tbMenuRols", "menrol").
                add(Restrictions.eq("men.menActivo", "1")).
                add(Restrictions.eq("menrol.menrolActivo", "1")).
                add(Restrictions.eq("men.menOpc", "0")).
                add(Restrictions.eq("menrol.tbRoles.Id", rol_id)).
                addOrder(Order.asc("men.menCodigo")).
                list();
    }

    @Override
    public List<TbMenu> seleccionHijos(int men_id, int rol_id) {
        return this.getSession().createCriteria(TbMenu.class, "men").
                createCriteria("men.tbMenuRols", "menrol").
                add(Restrictions.eq("men.menActivo", "1")).
                add(Restrictions.eq("menrol.menrolActivo", "1")).
                add(Restrictions.eq("menrol.tbRoles.Id", rol_id)).
                add(Restrictions.eq("men.menIdPadre", men_id)).
                addOrder(Order.asc("men.menCodigo")).
                list();
    }

    @Override
    public List seleccionarMenuI() {
        return this.getSession().createCriteria(TbMenu.class, "men").
                add(Restrictions.eq("men.menActivo", "1")).
                add(Restrictions.eq("men.menOpc", "0")).
                addOrder(Order.asc("men.menCodigo")).
                list();
    }

    @Override
    public List seleccionarMenuHijo(int men_id, String nivel) {
        return this.getSession().createCriteria(TbMenu.class, "men").
                add(Restrictions.eq("men.menActivo", "1")).
                add(Restrictions.eq("men.menIdPadre", men_id)).
                addOrder(Order.asc("men.menOpc")).
                list();
    }

    @Override
    public List seleccionarMenuHijos(int men_id, String nivel) {
        List lista = new ArrayList();
        List listaHijos = new ArrayList();

        lista.add(this.getSession().createCriteria(TbMenu.class, "men").
                add(Restrictions.eq("men.menActivo", "1")).
                add(Restrictions.eq("men.menId", men_id)).
                addOrder(Order.asc("men.menCodigo")).uniqueResult());

        int tamaño = (seleccionarMenuHijo(men_id, nivel)).size();
        if (tamaño != 0) {
            listaHijos = seleccionarMenuHijo(men_id, nivel);
            for (Object object : listaHijos) {
                lista.add(object);
            }
        }

        return lista;

    }

    @Override
    public List paginas() {
        return this.getSession().createCriteria(AcPagina.class, "pag").
                add(Restrictions.eq("pag.PagActivo", "1")).
                list();
    }

    @Override
    public List<TbMenu> menuPadres() {
        List lstMenuAux = new ArrayList();
        List lstMenu = new ArrayList();
        String sQuery;
        Object[] objFila;

        sQuery = "select * from tb_menu \n"
                + "where men_id in ( \n"
                + "select men_id_padre \n"
                + "from tb_menu \n"
                + "where men_id_padre in (select men_id from tb_menu where men_activo=1) \n"
                + "union \n"
                + "select men_id \n"
                + "from tb_menu \n"
                + "where (men_accion='' or men_accion is null) and men_activo=1 \n"
                + "GROUP BY 1 \n"
                + ") \n"
                + "ORDER BY men_opc";
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
            lstMenu.add(men);
        }
        return lstMenu;
    }

    @Override
    public List<TbMenu> niveles(int idPadre) {
        List lstNivel = new ArrayList<Integer>();
        String sQuery;
        Object[] objFila;
        sQuery = "SELECT cast(men_opc+1 as DECIMAL) numero,cast(men_opc+1 as DECIMAL) numero2 \n"
                + "FROM tb_menu \n"
                + "WHERE men_id=" + idPadre + ";";
        Query q2 = this.getSession().createSQLQuery(sQuery);
        lstNivel = q2.list();
        List niveles = new ArrayList();
        for (Object objAux : lstNivel) {
            objFila = (Object[]) objAux;
            TbMenu men = new TbMenu();
            men.setMenOpc(CommonWeb.parseObjToString(objFila[1]));
            niveles.add(men);
        }

        return niveles;

    }

    @Override
    public String buscarMenu(int men_id) {
        String sQuery;
        String sDescMenu = "";
        try {
            sQuery = "SELECT men_descripcion \n"
                    + "FROM tb_menu \n"
                    + "WHERE men_id =" + men_id + ";";
            Query q2 = this.getSession().createSQLQuery(sQuery);
            sDescMenu = String.valueOf(q2.uniqueResult());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sDescMenu;
    }

    @Override
    public String hijosMenu(int men_id) {
        String sQuery;
        String sEstado = "";
        int iNumHijos = 0;
        try {
            sQuery = "select count(*) "
                    + "from tb_menu "
                    + "where men_id_padre=" + men_id + ";";
            Query q2 = this.getSession().createSQLQuery(sQuery);
            iNumHijos = Integer.parseInt(q2.uniqueResult().toString());
            if (iNumHijos > 0) {
                sEstado = "Tiene SubMenus";
            } else {
                sEstado = "No Tiene SubMenus";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sEstado;
    }

    @Override
    public String hijosMenuEliminar(String men_id) {
        String sQuery;
        String sEstado = "";
        int iNumHijos = 0;
        try {
            sQuery = "select count(*) "
                    + "from tb_menu "
                    + "where men_id_padre=" + men_id + " and men_activo='1';";
            Query q2 = this.getSession().createSQLQuery(sQuery);
            iNumHijos = Integer.parseInt(q2.uniqueResult().toString());
            if (iNumHijos > 0) {
                sEstado = "1";
            } else {
                sEstado = "0";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sEstado;
    }

    @Override
    public String codigoMenu() {
        String sQuery;
        String sCodigoMenu = "";
        int iNumHijos = 0;
        try {
            sQuery = "select concat('m',max(codigo)+1) codigo_n \n"
                    + " from( \n"
                    + " select SUBSTRING( men_codigo,2,LENGTH(men_codigo)) "
                    + " codigo from tb_menu) menu_codigo ";
            Query q2 = this.getSession().createSQLQuery(sQuery);
            sCodigoMenu = q2.uniqueResult().toString();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sCodigoMenu;
    }

    @Override
    public void insertarMenu(TbMenu menu) throws DataAccessException {
        getHibernateTemplate().save(menu);
    }
    
     @Override
    public void insertarActualizarMenu(TbMenu menu) throws DataAccessException {
        getHibernateTemplate().saveOrUpdate(menu);
    }

    @Override
    public void eliminarMenu(String id) throws DataAccessException {
        String hqlUpdate = "update TbMenu set menActivo = :activo where men_id = :id";
        this.getSession().createQuery(hqlUpdate).setString("activo", "0").setString("id", id).executeUpdate();
    }

    @Override
    public void actualizarMenu(TbMenu menu)
            throws DataAccessException {
        getHibernateTemplate().update(menu);
    }
    
    @Override
    public boolean listaAgregarMenuParaRol(int menId,int nivel, int rolId) {
        String sQuery;
        List<TbMenu> lstMenu;
        List<TbMenuRol> lstMenuRol = new ArrayList<TbMenuRol>();
        HSRolDAO daoRol = (HSRolDAO) ServiceFinder.findBean("SpringHibernateDaoRol");
        sQuery = "call usp_trae_listado_menu_por_asignar (:v_men_id,:v_nivel,:v_rol_id);";
        lstMenu=this.getSession().createSQLQuery(sQuery).addEntity(TbMenu.class).setInteger("v_men_id", menId).setInteger("v_nivel", nivel).setInteger("v_rol_id", rolId).list();
        int contador=-1;
        for (TbMenu tbMenu : lstMenu) {
            TbRoles roles =new TbRoles(rolId);
            TbMenuRol rol= new TbMenuRol(0,tbMenu,roles,"1");
            lstMenuRol.add(rol);
            contador--;
        }
        for (TbMenuRol tbMenuRol : lstMenuRol) {
            daoRol.insertarMenuRol(tbMenuRol);
        }
        return true;
    }
    
    @Override
    public List seleccionarMenuRol(int rolId,int menuId) {
        return this.getSession().createCriteria(TbMenuRol.class, "rol").
                add(Restrictions.eq("rol.menrolActivo", "1"))
               .add(Restrictions.eq("rol.tbMenu.menId", menuId))
               .add(Restrictions.eq("rol.tbRoles.Id", rolId)).list();
    }
    
    @Override
    public TbMenuRol  seleccionarMenuRolClase( int rolId,int menuId) {
        return (TbMenuRol)this.getSession().createCriteria( TbMenuRol.class, "rol" )
               .add(Restrictions.eq("rol.tbMenu.menId", menuId))
               .add(Restrictions.eq("rol.tbRoles.Id", rolId)).uniqueResult();
    }
    
    @Override
    public List seleccionarMenuId(int idMenu) {
        DetachedCriteria criteria = DetachedCriteria.forClass(TbMenu.class);
        criteria.add(Expression.eq("menId",idMenu));
        criteria.add(Expression.eq("menActivo", "1"));
        List lista = getHibernateTemplate().findByCriteria(criteria);
        return lista;
    }
    
   

}
