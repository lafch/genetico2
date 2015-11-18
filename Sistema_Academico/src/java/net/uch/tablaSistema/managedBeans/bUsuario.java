package net.uch.tablaSistema.managedBeans;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import javax.faces.component.UIParameter;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.TbMenu;
import net.uch.mapping.TbRoles;
import net.uch.mapping.TbUsuario;
import net.uch.tablaSistema.hibernateSpringDao.HSRolDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSUsuarioDAO;
import net.uch.util.CommonDAO;
import org.richfaces.model.TreeNode;
import org.richfaces.model.TreeNodeImpl;

public class bUsuario {

    private int id_usu;
//    private String devuelveUsuario;
    private String usu_usu;
    private String usu_psw;
    private String usu_psw_aux;
    private String mensajes;
    private int usu_tipo_id;
//    private String usu_tipo;
    private boolean caja;
    private boolean admin;
    private boolean valida;
    private boolean academica;
    private boolean operador;
    private boolean admincl;
    private String usuario_u;
    private String password_u;
    private int rol_id;
    private int rol_id_aux;
    private int usu_id;
    private String rol_nombre;
    private String rol_nombre_aux;
    private String usu_activo_id;
    private String usu_activo_desc;
    private String usu_activo_desc_aux;
    private Integer usu_rol_id;
    private Integer usu_rol_id_aux;
    private SelectItem[] usu_rol_descripcion;
    private String usu_rol_desc;
    private String usu_rol_aux;
    private String nivel;
    private List<bUsuario> listUsuarios;
    private String bus_usuario;
    private String oncomplete;
    private bMenu menu;
    
    private SelectItem[] cboRol;
    private SelectItem[] cboRolE;
    private SelectItem[] cboActivo;
    private SelectItem[] cboActivoE;
    private int rol_id_f;
    private int act_id_f;
    
    
    private String editable = "false";
    private String view = "true";
    private boolean visible = false;
    private boolean editable2 = false;
    private boolean selected = false;
    
    private TreeNode root;
    List<TbMenu> lstOpciones = new ArrayList<TbMenu>();
    
    

    public bUsuario() {
        /*ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession 	session = (HttpSession)context.getSession(false);
        session.setAttribute("usuario", this);*/
    }
    
    public TreeNode getRoot() {
        return root;
    }
    
    public void cargarVista(ActionEvent event) throws Exception {
        UIParameter id = (UIParameter) event.getComponent().findComponent("p_id");
        int iId = Integer.parseInt(id.getValue().toString());
        this.setRol_id(iId);        
        cargarArbolRol();
        this.setOncomplete("javascript:Richfaces.showModalPanel('mpHoraria')");
    }
    
    public void cargarArbolRol() {
        if (this.getRol_id() > 0) {
            iniciar(this.getRol_id());
        }
    }
    
    public void iniciar(int idRol) {
        System.out.print(this.getRol_id());
        lstOpciones = new ArrayList<TbMenu>();
        List<TbMenu> lstMenuPadres = CommonDAO.getTbMenuDAO().menuPadreActivo(this.getRol_id());
        List<TbMenu> lstMenuHijos = CommonDAO.getTbMenuDAO().menuHijosActivo(this.getRol_id());
        
        for (TbMenu tbMenuPadre : lstMenuPadres) {
            lstOpciones.add(tbMenuPadre);
        }

        for (TbMenu tbMenuHijo : lstMenuHijos) {
            lstOpciones.add(tbMenuHijo);
        }

        root = generarArbol(lstOpciones);
    }
    
    private TreeNode generarArbol(List<TbMenu> lstOpciones) {
        TreeNode root = new TreeNodeImpl<TbMenu>();
        Iterator<TbMenu> it = lstOpciones.iterator();
        while (it.hasNext()) {
            TbMenu tbMenu = it.next();
            if (tbMenu.getMenIdPadre() == 0) {//ES PADRE
                TreeNode padreNode = new TreeNodeImpl();
                padreNode.setData(tbMenu);
                root.addChild(tbMenu.getMenId(), padreNode);
                agregarHijosArbol(padreNode, lstOpciones);
            }
        }
        return root;
    }
    
     private void agregarHijosArbol(TreeNode<TbMenu> padreNode, List<TbMenu> lstOpciones) {
        TbMenu padre = padreNode.getData();
        for (TbMenu tbMenu : lstOpciones) {
            if (padre.getMenId() == tbMenu.getMenIdPadre()) {
                TreeNode hijoNode = new TreeNodeImpl();
                hijoNode.setData(tbMenu);
                padreNode.addChild(tbMenu.getMenId(), hijoNode);
                agregarHijosArbol(hijoNode, lstOpciones);
            }
        }
    }
    
    //para poder editar
    public int getUsu_id() {
        return usu_id;
    }

    public void setUsu_id(int usu_id) {
        this.usu_id = usu_id;
    }
     
     
    public String getEditable() {
        return editable;
    }

    public void setEditable(String editable) {
        this.editable = editable;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isEditable2() {
        return editable2;
    }

    public void setEditable2(boolean editable2) {
        this.editable2 = editable2;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getUsu_psw_aux() {
        return usu_psw_aux;
    }

    public void setUsu_psw_aux(String usu_psw_aux) {
        this.usu_psw_aux = usu_psw_aux;
    }

    public int getRol_id_aux() {
        return rol_id_aux;
    }

    public void setRol_id_aux(int rol_id_aux) {
        this.rol_id_aux = rol_id_aux;
    }

    public String getRol_nombre_aux() {
        return rol_nombre_aux;
    }

    public void setRol_nombre_aux(String rol_nombre_aux) {
        this.rol_nombre_aux = rol_nombre_aux;
    }

    public String getUsu_activo_desc() {
        return usu_activo_desc;
    }

    public void setUsu_activo_desc(String usu_activo_desc) {
        this.usu_activo_desc = usu_activo_desc;
    }

    public String getUsu_activo_desc_aux() {
        return usu_activo_desc_aux;
    }

    public void setUsu_activo_desc_aux(String usu_activo_desc_aux) {
        this.usu_activo_desc_aux = usu_activo_desc_aux;
    }

    public String getUsu_activo_id() {
        return usu_activo_id;
    }

    public void setUsu_activo_id(String usu_activo_id) {
        this.usu_activo_id = usu_activo_id;
    }

    public String getUsu_rol_desc() {
        return usu_rol_desc;
    }

    public void setUsu_rol_desc(String usu_rol_desc) {
        this.usu_rol_desc = usu_rol_desc;
    }

    public String getUsu_rol_aux() {
        return usu_rol_aux;
    }

    public void setUsu_rol_aux(String usu_rol_aux) {
        this.usu_rol_aux = usu_rol_aux;
    }
    
    
    public void EditarFila(ActionEvent event) {
        this.setUsu_psw(this.getUsu_psw());
        this.setUsu_psw_aux(this.getUsu_psw());
        
        if ("0".equals(this.getUsu_activo_id())) {
            this.setUsu_activo_desc("Desactivado");
        } else {
            this.setUsu_activo_desc("Activado");
        }
        this.setUsu_rol_desc(this.getUsu_rol_desc());
        this.setUsu_rol_aux(this.getUsu_rol_aux());
        this.setUsu_activo_desc(this.getUsu_activo_desc());
        this.setUsu_activo_desc_aux(this.getUsu_activo_desc());
        this.setView("false");
        this.setEditable("true");
        this.setEditable2(true);
        this.setVisible(true);
    }
    
      public void Editar(ActionEvent event) {
        UIParameter id = (UIParameter) event.getComponent().findComponent("id");
        UIParameter pass = (UIParameter) event.getComponent().findComponent("pass");
        UIParameter rol = (UIParameter) event.getComponent().findComponent("rol");
        UIParameter activo = (UIParameter) event.getComponent().findComponent("activo");

        int iId = this.getUsu_id();
        
        String sPass = this.getUsu_psw();
        String sActivo = this.getUsu_activo_id();
        int rol_id=this.getRol_id();

        if ((sPass.trim()).length() == 0) {
            this.setOncomplete("javascript:alert('debe ingresar una contraseña.')");

        } else {
            HSUsuarioDAO daoUsu = (HSUsuarioDAO) ServiceFinder.findBean("SpringHibernateDaoUsuario");
            TbUsuario usu = daoUsu.traerUsuarioPorId(iId);
            usu.setRol(new TbRoles(rol_id));
            usu.setUsuPassword(sPass);
            usu.setUsuActivo(sActivo);
            usu.setUsuPsw(sPass);
            if ("0".equals(sActivo)) {
                this.setUsu_activo_desc("Desactivado");
            } else {
                this.setUsu_activo_desc("Activado");
            }
            daoUsu.editarUsuario(usu);

            this.setView("true");
            this.setEditable("false");
            this.setEditable2(false);
            this.setVisible(false);
            this.setOncomplete("javascript:alert('Se Actualizaron los datos del Rol correctamente.')");
        }
    }

    public void Cancelar(ActionEvent event) {
        this.setUsu_psw(this.getUsu_psw_aux());
        this.setUsu_rol_id(this.getUsu_rol_id_aux());
        if ("0".equals(this.getUsu_activo_id())) {
            this.setUsu_activo_desc("Desactivado");
        } else {
            this.setUsu_activo_desc("Activado");
        }
        this.setView("true");
        this.setEditable("false");
        this.setEditable2(false);
        this.setVisible(false);
    }
    
     //fin editar
    
     public String getRol_nombre() {
        return rol_nombre;
    }

    public void setRol_nombre(String rol_nombre) {
        this.rol_nombre = rol_nombre;
    }
    
     public int getRol_id_f() {
        return rol_id_f;
    }

    public void setRol_id_f(int rol_id_f) {
        this.rol_id_f = rol_id_f;
    }

   

    public int getAct_id_f() {
        return act_id_f;
    }

    public void setAct_id_f(int act_id_f) {
        this.act_id_f = act_id_f;
    }
    
    public SelectItem[] getCboActivo() {
        cboActivo = new SelectItem[2];
        cboActivo[0] = new SelectItem(0, "Desactivado");
        cboActivo[1] = new SelectItem(1, "Activado");
        return cboActivo;
    }

    public void setCboActivo(SelectItem[] cboActivo) {
        this.cboActivo = cboActivo;
    }
    
    public SelectItem[] getCboActivoE() {
        cboActivoE = new SelectItem[2];
        cboActivoE[0] = new SelectItem(0, "Desactivado");
        cboActivoE[1] = new SelectItem(1, "Activado");
        return cboActivoE;
    }

    public void setCboActivoE(SelectItem[] cboActivo) {
        this.cboActivoE = cboActivo;
    }
    
    
     public SelectItem[] getCboRol() {
        HSRolDAO dao = (HSRolDAO) ServiceFinder.findBean("SpringHibernateDaoRol");
        List<TbRoles> roles = dao.getRoles();
        if (!roles.isEmpty()) {
            cboRol = new SelectItem[roles.size() + 1];
            cboRol[0] = new SelectItem(0, "Seleccione");
            for (int i = 0; i < roles.size(); i++) {
                TbRoles rol = roles.get(i);
                cboRol[i + 1] = new SelectItem(rol.getId(), rol.getRolTipo());
            }
        } else {
            cboRol = new SelectItem[1];
            cboRol[0] = new SelectItem(0, "[Seleccione]");
        }
        return cboRol;
    }

    public void setCboRol(SelectItem[] cboRol) {
        this.cboRol = cboRol;
    }
    
    public SelectItem[] getCboRolE() {
        HSRolDAO dao = (HSRolDAO) ServiceFinder.findBean("SpringHibernateDaoRol");
        List<TbRoles> roles = dao.getRoles();
        if (!roles.isEmpty()) {
            cboRolE = new SelectItem[roles.size() + 1];
            cboRolE[0] = new SelectItem(0, "Seleccione");
            for (int i = 0; i < roles.size(); i++) {
                TbRoles rol = roles.get(i);
                cboRolE[i + 1] = new SelectItem(rol.getId(), rol.getRolTipo());
            }
        } else {
            cboRolE = new SelectItem[1];
            cboRolE[0] = new SelectItem(0, "[Seleccione]");
        }
        return cboRolE;
    }

    public void setCboRolE(SelectItem[] cboRolE) {
        this.cboRolE = cboRolE;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public bMenu getMenu() {
        return menu;
    }

    public void setMenu(bMenu menu) {
        this.menu = menu;
    }

    public List<bUsuario> getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(List<bUsuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public String getBus_usuario() {
        return bus_usuario;
    }

    public void setBus_usuario(String bus_usuario) {
        this.bus_usuario = bus_usuario;
    }

    public int getId_usu() {
        return id_usu;
    }

    public void setId_usu(int id_usu) {
        this.id_usu = id_usu;
    }

    public String getUsu_usu() {
        return usu_usu;
    }

    public void setUsu_usu(String usu_usu) {
        this.usu_usu = usu_usu;
    }

    public String getUsu_psw() {
        return usu_psw;
    }

    public void setUsu_psw(String usu_psw) {
        this.usu_psw = usu_psw;
    }

    public String checkUser() throws SQLException, IOException {
        //System.out.println("chaeck");
        HSUsuarioDAO dao = (HSUsuarioDAO) ServiceFinder.findBean("SpringHibernateDaoUsuario");
        TbUsuario usua;
        if ((usua = dao.validateUser(getUsu_usu(), getUsu_psw())) != null) {
            this.usu_usu = usua.getUsuUsuario();
            this.usu_psw = usua.getUsuPassword();
            this.id_usu = usua.getId();
            this.rol_id = usua.getRol().getId();
            this.setNivel(usua.getUsuNivel());
            this.usu_tipo_id = usua.getUsuTipoId();
            this.admin = false;
            this.caja = false;
            this.academica = false;
            this.operador = false;
            this.admincl = false;
            if (usua.getRol().getRolTipo().equals("admin") || usua.getRol().getRolTipo().equals("recursos_humanos")) {
                if(usua.getRol().getRolTipo().equals("admin")){
                this.admin = true;
                this.valida=true;
                }else{
                    this.valida=true;
                }
                
            } else if (usua.getRol().getRolTipo().equals("caja")) {
                this.caja = true;
            } else if (usua.getRol().getRolTipo().equals("academica")) {
                this.academica = true;
            } else if (usua.getRol().getRolTipo().equals("operador")) {
                this.operador = true;
            } else if (usua.getRol().getRolTipo().equals("admin_cursos_libres")) {
                this.admincl = true;
            }

            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpSession session = (HttpSession) context.getSession(false);
            session.setAttribute("usuario", this);
            if (usua.getRol().getRolTipo().equals("admin")) {
                session.setMaxInactiveInterval( 60 * 60 * 8 );
            }
            //System.out.println("ok");
            this.setMenu(new bMenu(usua.getRol().getId(), usua.getUsuUsuario()));

            return "ok";
        } else {
            this.setMensajes("Usuario o Password es incorrecto");
           //System.out.println("error");
            return "error";
        }
    }

    public boolean validar() {
        if (this.usuario_u.trim().length() == 0) {
            return false;
        } else if (this.password_u.trim().length() == 0) {
            return false;
        } else if (this.usu_rol_id == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void crearUsuario(ActionEvent event) throws Exception {
        this.setOncomplete("");
        if (validar()) {
            HSUsuarioDAO dao = (HSUsuarioDAO) ServiceFinder.findBean("SpringHibernateDaoUsuario");
            TbUsuario usuario = new TbUsuario();
            usuario.setUsuActivo("1");
            usuario.setUsuUsuario(this.getUsuario_u());
            usuario.setUsuPassword(this.getPassword_u());
            usuario.setUsuVigente("1");
            usuario.setUsuTipo("1");
            usuario.setUsuTipoId(1);
            usuario.setUsuNivel("1");
            usuario.setUsuPsw(this.getPassword_u());
            usuario.setRol(new TbRoles(this.getUsu_rol_id()));
            dao.crearUsuario(usuario);
            this.setOncomplete("alert('Usuario creado correctamente');"
                    + "Richfaces.hideModalPanel('mp')");
        } else {
            this.setOncomplete("alert('Ingrese todos los campos');");
        }
    }

    public String getMensajes() {
        return mensajes;
    }

    public String ExitSis() {
        String volver = "";
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
        volver = "exit";
        return volver;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }

    public int getUsu_tipo_id() {
        return usu_tipo_id;
    }

    public void setUsu_tipo_id(int usu_tipo_id) {
        this.usu_tipo_id = usu_tipo_id;
    }

    public String getDevuelveUsuario() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession) context.getSession(false);
        bUsuario usu = (bUsuario) session1.getAttribute("usuario");
        return usu.getUsu_usu();
    }

    public boolean isCaja() {
        return caja;
    }

    public boolean isAdmin() {
        return admin;
    }
    public boolean isValida() {
        return valida;
    }

    public String getUsuario_u() {
        return usuario_u;
    }

    public void setUsuario_u(String usuario_u) {
        this.usuario_u = usuario_u;
    }

    public String getPassword_u() {
        return password_u;
    }

    public void setPassword_u(String password_u) {
        this.password_u = password_u;
    }

    public void Nuevo() {
        usuario_u = "";
        password_u = "";
        usu_rol_id = 0;
    }

    public String cerrar() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        //session.invalidate();
        //session.removeAttribute("usuario");

        Enumeration atributos = session.getAttributeNames();
        while (atributos.hasMoreElements()) {
            session.removeAttribute((String) atributos.nextElement());
        }
        session.invalidate();

        return "index";
    }

    public boolean isAcademica() {
        return academica;
    }

    public void setAcademica(boolean academica) {
        this.academica = academica;
    }

    public int getRol_id() {
        return rol_id;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }

    public SelectItem[] getUsu_rol_descripcion() {
        HSUsuarioDAO dao = (HSUsuarioDAO) ServiceFinder.findBean("SpringHibernateDaoUsuario");

        List<TbRoles> roles = dao.getRoles();
        usu_rol_descripcion = new SelectItem[roles.size() + 1];
        usu_rol_descripcion[0] = new SelectItem(0, "[Seleccione]");

        for (int i = 0; i < roles.size(); i++) {
            TbRoles rol = roles.get(i);
            usu_rol_descripcion[i + 1] = new SelectItem(rol.getId(), rol.getRolTipo());
        }
        return usu_rol_descripcion;
    }

    public void setUsu_rol_descripcion(SelectItem[] usu_rol_descripcion) {
        this.usu_rol_descripcion = usu_rol_descripcion;
    }

    public Integer getUsu_rol_id() {
        return usu_rol_id;
    }

    public void setUsu_rol_id(Integer usu_rol_id) {
        this.usu_rol_id = usu_rol_id;
    }

    public Integer getUsu_rol_id_aux() {
        return usu_rol_id_aux;
    }

    public void setUsu_rol_id_aux(Integer usu_rol_id_aux) {
        this.usu_rol_id_aux = usu_rol_id_aux;
    }
    

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public boolean isOperador() {
        return operador;
    }

    public void setOperador(boolean operador) {
        this.operador = operador;
    }

    public String Seleccionar() throws Exception, SQLException, IOException {
        String status;
        status = "ok";
        HSUsuarioDAO dao = (HSUsuarioDAO) ServiceFinder.findBean("SpringHibernateDaoUsuario");

        List<TbUsuario> ltbu;
        ltbu = dao.getListUsuarios(this.bus_usuario,this.getRol_id_f(),this.getAct_id_f());
        this.setListUsuarios(new ArrayList());
        if (ltbu != null) {
            for (int g = 0; g < ltbu.size(); g++) {
                //if((usua= dao.validateUser(getUsu_usu(),getUsu_psw()))!=null){
                TbUsuario usua = ltbu.get(g);
                bUsuario bU = new bUsuario();
                bU.usu_id = usua.getId();
                bU.usu_usu = usua.getUsuUsuario();
                bU.usu_activo_id= usua.getUsuActivo();
                if ("0".equals(usua.getUsuActivo())) {
                    bU.usu_activo_desc="Desactivado";
                } else {
                    bU.usu_activo_desc="Activado";
                }
                bU.usu_psw = usua.getUsuPassword();
                bU.id_usu = usua.getId();
                bU.rol_id = usua.getRol().getId();
                bU.rol_nombre=usua.getRol().getRolTipo();
                bU.nivel = usua.getUsuNivel();
                bU.usu_tipo_id = usua.getUsuTipoId();
                bU.admin = false;
                bU.caja = false;
                bU.academica = false;
                bU.operador = false;
                bU.admincl = false;
                if (usua.getRol().getRolTipo().equals("admin")) {
                    bU.admin = true;
                } else if (usua.getRol().getRolTipo().equals("caja")) {
                    bU.caja = true;
                } else if (usua.getRol().getRolTipo().equals("academica")) {
                    bU.academica = true;
                } else if (usua.getRol().getRolTipo().equals("operador")) {
                    bU.operador = true;
                } else if (usua.getRol().getRolTipo().equals("admin_cursos_libres")) {
                    bU.admincl = true;
                }
                this.listUsuarios.add(bU);
            }
        }

        return status;
    }

    public boolean isAdmincl() {
        return admincl;
    }

    public void setAdmincl(boolean admincl) {
        this.admincl = admincl;
    }
}
