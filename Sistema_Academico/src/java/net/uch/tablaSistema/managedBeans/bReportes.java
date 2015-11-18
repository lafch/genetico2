package net.uch.tablaSistema.managedBeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import net.uch.commonService.ServiceFinder;
import javax.faces.event.ActionEvent;
import net.uch.mapping.TbReporte;
import net.uch.mapping.TbRoles;
import net.uch.mapping.TbUsuario;
import net.uch.tablaSistema.hibernateSpringDao.HSReporteDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSRolDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSUsuarioDAO;

public class bReportes {
    
    private int rep_id;
    private String rep_descripcion;
    private String rep_link;
    private String rep_tipo;
    private int rol;
    private String rep_activo;
    private String rep_grupo;
    private int usu_id;
    private SelectItem b_usu[];
    private SelectItem b_rol[];
    private SelectItem b_listar_rol[];
    private int b_usu_id;
    private int b_rol_id;
    private String b_rol_des;
    private List<bReportes> b_listar_reportes = new ArrayList<bReportes>();

    public void mostrarReportesUsuario(ActionEvent event) {
        
        try {

            HSReporteDAO daoReporte = (HSReporteDAO) ServiceFinder.findBean("SpringHibernateDaoReporte");
            HSRolDAO daoRol = (HSRolDAO) ServiceFinder.findBean("SpringHibernateDaoRol");
            List<bReportes> listaReportes = new ArrayList<bReportes>();
            //List<TbReporte> reportesUsu = daoReporte.getListarReporte() ;
            List<TbReporte> reportesUsu2 = daoReporte.getListarReporteUsu(this.getB_usu_id()) ;
            for (int i = 0; i < reportesUsu2.size(); i++) {
                bReportes repo = new bReportes();
                repo.setRep_descripcion(reportesUsu2.get(i).getRepDescripcion());
                repo.setRep_id(reportesUsu2.get(i).getRepId());
                repo.setRol(reportesUsu2.get(i).getRol());
                TbRoles rep = daoRol.traerRolPorId(repo.getRol());
                this.setB_rol_des(rep.getRolTipo().toUpperCase());
                listaReportes.add(repo);
            }
            this.setB_listar_reportes(listaReportes);
        } catch (Exception e) {
        }
    }
    
    public SelectItem[] getB_usu() {
        try {
            
            HSUsuarioDAO usuarioDAO =(HSUsuarioDAO) ServiceFinder.findBean("SpringHibernateDaoUsuario");
            List l = usuarioDAO.getListarUsuario();
            b_usu = new SelectItem[l.size() + 1];
            for (int i = 0; i < l.size(); i++) {
                TbUsuario usu = (TbUsuario) l.get(i);
                b_usu[i + 1] = new SelectItem(usu.getId(), usu.getUsuUsuario());
            }
        } catch (Exception e) {
            b_usu = new SelectItem[1];
        } finally {
            b_usu[0] = new SelectItem(0, "[Seleccione]");
        }
        return b_usu;
    }

    public void setB_usu(SelectItem[] b_usu) {
        this.b_usu = b_usu;
    }

    public String getRep_activo() {
        return rep_activo;
    }

    public void setRep_activo(String rep_activo) {
        this.rep_activo = rep_activo;
    }

    public String getRep_descripcion() {
        return rep_descripcion;
    }

    public void setRep_descripcion(String rep_descripcion) {
        this.rep_descripcion = rep_descripcion;
    }

    public String getRep_grupo() {
        return rep_grupo;
    }

    public void setRep_grupo(String rep_grupo) {
        this.rep_grupo = rep_grupo;
    }

    public int getRep_id() {
        return rep_id;
    }

    public void setRep_id(int rep_id) {
        this.rep_id = rep_id;
    }

    public String getRep_link() {
        return rep_link;
    }

    public void setRep_link(String rep_link) {
        this.rep_link = rep_link;
    }

    public String getRep_tipo() {
        return rep_tipo;
    }

    public void setRep_tipo(String rep_tipo) {
        this.rep_tipo = rep_tipo;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public int getUsu_id() {
        return usu_id;
    }

    public void setUsu_id(int usu_id) {
        this.usu_id = usu_id;
    }

    public int getB_usu_id() {
        return b_usu_id;
    }

    public void setB_usu_id(int b_usu_id) {
        this.b_usu_id = b_usu_id;
    }

    public List<bReportes> getB_listar_reportes() {
        return b_listar_reportes;
    }

    public void setB_listar_reportes(List<bReportes> b_listar_reportes) {
        this.b_listar_reportes = b_listar_reportes;
    }

    public int getB_rol_id() {
        return b_rol_id;
    }

    public void setB_rol_id(int b_rol_id) {
        this.b_rol_id = b_rol_id;
    }
    
    public SelectItem[] getB_rol() {
        try {
            HSRolDAO rolDAO=(HSRolDAO) ServiceFinder.findBean("SpringHibernateDaoRol");
            HSUsuarioDAO usuarioDAO=(HSUsuarioDAO) ServiceFinder.findBean("SpringHibernateDaoUsuario");
            TbUsuario rep = usuarioDAO.traerUsuarioPorId(this.getB_usu_id());
            List l = rolDAO.getListarRolPorId2(rep.getRol().getId());
            b_rol = new SelectItem[l.size() + 1];
            for (int i = 0; i < l.size(); i++) {
                TbRoles rol = (TbRoles) l.get(i);
                b_rol[i + 1] = new SelectItem(rol.getId(), rol.getRolTipo());
                this.setB_rol_des(rol.getRolTipo());
            }
        } catch (Exception e) {
            b_rol = new SelectItem[1];
        } finally {
            b_rol[0] = new SelectItem(0, "[Seleccione]");
        }
        return b_rol;
    }

    public void setB_rol(SelectItem[] b_rol) {
        this.b_rol = b_rol;
    }
    
    public SelectItem[] getB_listar_rol() {
        try {
            HSRolDAO rolDAO=(HSRolDAO) ServiceFinder.findBean("SpringHibernateDaoRol");            
            List l = rolDAO.getListarRol(0);
            b_listar_rol = new SelectItem[l.size() + 1];
            for (int i = 0; i < l.size(); i++) {
                TbRoles rol = (TbRoles) l.get(i);
                b_listar_rol[i + 1] = new SelectItem(rol.getId(), rol.getRolTipo());
                this.setB_rol_des(rol.getRolTipo());
            }
        } catch (Exception e) {
            b_listar_rol = new SelectItem[1];
        } finally {
            b_listar_rol[0] = new SelectItem(0, "[Seleccione]");
        }
        return b_listar_rol;
    }

    public void setB_listar_rol(SelectItem[] b_listar_rol) {
        this.b_listar_rol = b_listar_rol;
    }

    public String getB_rol_des() {
        return b_rol_des;
    }

    public void setB_rol_des(String b_rol_des) {
        this.b_rol_des = b_rol_des;
    }
    
}