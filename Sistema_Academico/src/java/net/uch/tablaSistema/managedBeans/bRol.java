/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.tablaSistema.managedBeans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.TbMenu;
import net.uch.mapping.TbMenuRol;
import net.uch.mapping.TbRoles;
import net.uch.tablaSistema.hibernateSpringDao.HSMenuDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSRolDAO;
import net.uch.util.CommonDAO;
import org.richfaces.component.UITree;
import org.richfaces.component.html.HtmlTree;
import org.richfaces.event.NodeSelectedEvent;
import org.richfaces.model.TreeNode;
import org.richfaces.model.TreeNodeImpl;

/**
 *
 * @author LUIS
 */
public class bRol {

    private int rol_id;
    private String rol_tipo;
    private String rol_tipo_aux;
    private String rol_activo;
    private String rol_activo_aux;
    private String rol_activo_desc;
    private String rol_activo_desc_aux;
    private String rol_descripcion;
    private String rol_descripcion_aux;
    private String v_imagen_horario;
    private String oncomplete;

    private SelectItem[] cboRol;
    private SelectItem[] cboActivo;

    private List listaRol;

    private String editable = "false";
    private String view = "true";
    private boolean visible = false;
    private boolean editable2 = false;
    private boolean selected = false;

    /*para el arbol*/
    private TreeNode arbol = null;
    private List<String> selectedNodeChildren = new ArrayList<String>();
    private String nombreNodo;

    private TreeNode root;
    List<TbMenu> lstOpciones = new ArrayList<TbMenu>();

    private SelectItem[] cboMenu01;
    private SelectItem[] cboMenu02;
    private SelectItem[] cboMenu03;
    private SelectItem[] cboMenu04;

    private Integer idMenu01 = 0;
    private Integer idMenu02 = 0;
    private Integer idMenu03 = 0;
    private Integer idMenu04 = 0;

    private boolean vista02 = false;
    private boolean vista03 = false;
    private boolean vista04 = false;

    private void validarIngreso() {
        boolean padre01 = false;
        boolean padre02 = false;

        if (idMenu01 != 0) {
            padre01 = true;
            if (padre01) {
                if (idMenu02 != 0) {
                    if (idMenu03 != 0) {
                        if (idMenu04 != 0) {
                            //inserta padre01,padre02,padre03 y hijo04
                        } else {
                             //inserta padre01, padre 02
                            //inserta padre 03 y todos sus hijos si tubiera
                        }
                    } else {
                        //inserta padre01
                        //insertar padre02 y todos sus hijos si tubiera
                    }

                } else {
                    //insertar padre01 y todos sus hijos si tubiera
                }
            } else {
                //insertar
            }
        } else {
            //debe seleccionar un menu
        }
    }
    
     public void guardarMenuRol() throws Exception {
        HSMenuDAO dao = (HSMenuDAO) ServiceFinder.findBean("SpringHibernateDaoMenu");
        HSRolDAO daoRol = (HSRolDAO) ServiceFinder.findBean("SpringHibernateDaoRol");
        int rolId=this.getRol_id();
        int iNivel;
        if (this.getIdMenu01()!= 0) {
            
            TbMenuRol lstMenuRol = dao.seleccionarMenuRolClase(rolId, this.getIdMenu01());
            if(lstMenuRol == null){
                TbRoles rol= new TbRoles(rolId);
                TbMenu  men = (TbMenu) dao.seleccionarMenuId(this.getIdMenu01()).get(0);
                TbMenuRol menuRol=new TbMenuRol(0, men, rol,"1");
                daoRol.insertarMenuRol(menuRol);
            }
            
            iNivel = 1;
            if (this.getIdMenu02() != 0) {
                 TbMenuRol lstMenuRol02 = dao.seleccionarMenuRolClase(rolId, this.getIdMenu02());
                 if(lstMenuRol02 ==null){
                    TbRoles rol= new TbRoles(rolId);
                    TbMenu  men = (TbMenu) dao.seleccionarMenuId(this.getIdMenu02()).get(0);
                    TbMenuRol menuRol=new TbMenuRol(0, men, rol,"1");
                    daoRol.insertarMenuRol(menuRol);
                 }
                iNivel = 2;
                if (this.getIdMenu03() != 0) {
                    TbMenuRol lstMenuRol03 = dao.seleccionarMenuRolClase(rolId, this.getIdMenu03());
                    if(lstMenuRol03 == null){
                       TbRoles rol= new TbRoles(rolId);
                       TbMenu  men = (TbMenu) dao.seleccionarMenuId(this.getIdMenu03()).get(0);
                       TbMenuRol menuRol=new TbMenuRol(0, men, rol,"1");
                       daoRol.insertarMenuRol(menuRol);
                    }
                    iNivel = 3;
                    if (this.getIdMenu04() != 0) {
                        TbMenuRol lstMenuRol04 = dao.seleccionarMenuRolClase(rolId, this.getIdMenu04());
                        if(lstMenuRol04 ==null){
                            TbRoles rol= new TbRoles(rolId);
                            TbMenu  men = (TbMenu) dao.seleccionarMenuId(this.getIdMenu04()).get(0);
                            TbMenuRol menuRol=new TbMenuRol(0, men, rol,"1");
                            daoRol.insertarMenuRol(menuRol);
                            this.setOncomplete("javascript:alert('Se ingreso correctamente el menu"); 
                        }else{
                            this.setOncomplete("javascript:alert('El menu que quiere asignar ya existe')"); 
                        }
                    }else{
                        dao.listaAgregarMenuParaRol(this.getIdMenu03(), iNivel,rolId );
                        this.setOncomplete("javascript:alert('Se ingreso Menu General de Nivel"+iNivel+"')"); 
                    }
                }else{
                    dao.listaAgregarMenuParaRol(this.getIdMenu02(), iNivel,rolId );
                    this.setOncomplete("javascript:alert('Se ingreso Menu General de Nivel"+iNivel+"')");
                }
            }else{
                dao.listaAgregarMenuParaRol(this.getIdMenu01(), iNivel,rolId );
                this.setOncomplete("javascript:alert('Se ingreso Menu General de Nivel"+iNivel+"')");
            }

        }

    }
    
    public void asignarMenuRol(ActionEvent event) throws Exception {
        if(this.getRol_id()>0){
            guardarMenuRol();
            cargarArbolRol();
        }
    }
    
    public void validarPermisoRol(ActionEvent event) throws Exception {
        if(this.getRol_id()>0 || !lstOpciones.isEmpty()){
            guardarEstadoMenuPorRol( lstOpciones,this.getRol_id());
            cargarArbolRol();
        }
    }

    private void guardarEstadoMenuPorRol(List<TbMenu> lstMenu, int idRol) {
        TbRoles rol = new TbRoles(idRol);
        HSMenuDAO dao = (HSMenuDAO) ServiceFinder.findBean("SpringHibernateDaoMenu");
        HSRolDAO daoRol = (HSRolDAO) ServiceFinder.findBean("SpringHibernateDaoRol");
        for (TbMenu tbMenu : lstMenu) {
            TbMenuRol tbMenuRol = dao.seleccionarMenuRolClase(rol.getId(), tbMenu.getMenId());
            TbMenuRol menuRol;
            if(tbMenuRol.getMenrolId()==0 || tbMenuRol.getMenrolId() == null ){
                menuRol=new TbMenuRol(-1, tbMenu, rol,tbMenu.isSeleccionado()==true?"1":"0");
            }else{
                menuRol=new TbMenuRol(tbMenuRol.getMenrolId(), tbMenu, rol,tbMenu.isSeleccionado()==true?"1":"0");
            }
            if(menuRol.getTbMenu()!=null){
                daoRol.insertarMenuRol(menuRol);
                this.setOncomplete("javascript:alert('se ingresaron correctamente los privilegios')");
            }else{
                this.setOncomplete("javascript:alert('Problemas de ingreso')");
            }
        }
    }

    private void insertarMenu(TbMenu menu_ingresar) {
        //verificar si no esa padre;

    }

    public TreeNode getRoot() {
        return root;
    }

    public void marcar(Object event) {

        System.out.println("recibiendo parametro" + event.toString());
        TbMenu menu = (TbMenu) event;
        for (TbMenu modeloAux : lstOpciones) {
            if (modeloAux.getMenIdPadre() == menu.getMenId()) {
                modeloAux.setSeleccionado(menu.isSeleccionado());
                marcarHijos(lstOpciones, modeloAux);
            }
            System.out.println("nuevo" + modeloAux);
        }
        root = generarArbol(lstOpciones);
    }

    public void marcarHijos(List<TbMenu> lstOpciones, TbMenu padre) {
        for (TbMenu modeloAux : lstOpciones) {
            if (modeloAux.getMenIdPadre() == padre.getMenId()) {
                modeloAux.setSeleccionado(padre.isSeleccionado());
                marcarHijos(lstOpciones, modeloAux);
            }
//                System.out.println("nuevo"+modeloAux);
        }

    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
    }

    public String getRol_activo() {
        return rol_activo;
    }

    public void setRol_activo(String rol_activo) {
        this.rol_activo = rol_activo;
    }

    public String getRol_activo_desc() {
        return rol_activo_desc;
    }

    public void setRol_activo_desc(String rol_activo_desc) {
        this.rol_activo_desc = rol_activo_desc;
    }

    public String getRol_activo_desc_aux() {
        return rol_activo_desc_aux;
    }

    public void setRol_activo_desc_aux(String rol_activo_desc_aux) {
        this.rol_activo_desc_aux = rol_activo_desc_aux;
    }

    public int getRol_id() {
        return rol_id;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }

    public String getRol_tipo() {
        return rol_tipo;
    }

    public void setRol_tipo(String rol_tipo) {
        this.rol_tipo = rol_tipo;
    }

    public String getRol_descripcion() {
        return rol_descripcion;
    }

    public void setRol_descripcion(String rol_descripcion) {
        this.rol_descripcion = rol_descripcion;
    }

    public String getRol_tipo_aux() {
        return rol_tipo_aux;
    }

    public void setRol_tipo_aux(String rol_tipo_aux) {
        this.rol_tipo_aux = rol_tipo_aux;
    }

    public String getRol_activo_aux() {
        return rol_activo_aux;
    }

    public void setRol_activo_aux(String rol_activo_aux) {
        this.rol_activo_aux = rol_activo_aux;
    }

    public String getRol_descripcion_aux() {
        return rol_descripcion_aux;
    }

    public void setRol_descripcion_aux(String rol_descripcion_aux) {
        this.rol_descripcion_aux = rol_descripcion_aux;
    }

    public String getV_imagen_horario() {
        return v_imagen_horario;
    }

    public void setV_imagen_horario(String v_imagen_horario) {
        this.v_imagen_horario = v_imagen_horario;
    }

    /*efecto de editar*/
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

    /*listado general*/
    public List getListaRol() {
        return listaRol;
    }

    public void setListaRol(List listaRol) {
        this.listaRol = listaRol;
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

    public SelectItem[] getCboActivo() {
        cboActivo = new SelectItem[2];
        cboActivo[0] = new SelectItem(0, "Desactivado");
        cboActivo[1] = new SelectItem(1, "Activado");
        return cboActivo;
    }

    public void setCboActivo(SelectItem[] cboActivo) {
        this.cboActivo = cboActivo;
    }

    public void Seleccionar() throws Exception {
        listaRol = new ArrayList();
        HSRolDAO daoRol = (HSRolDAO) ServiceFinder.findBean("SpringHibernateDaoRol");
        int iRolId = this.getRol_id();
        List<TbRoles> lista = daoRol.getListarRol(iRolId);

        bRol bRol;
        if (lista.size() > 0) {
            for (int i = 0; i < lista.size(); i++) {

                TbRoles rol = lista.get(i);
                bRol = new bRol();
                bRol.setRol_id(rol.getId());
                bRol.setRol_tipo(rol.getRolTipo());
                if ("0".equals(rol.getRolActivo())) {
                    bRol.setRol_activo_desc("Desactivado");
                    bRol.setRol_activo("0");
                } else {
                    bRol.setRol_activo_desc("Activado");
                    bRol.setRol_activo("1");
                }
                if (daoRol.numeroMenu(bRol.getRol_id()) == 0) {
                    bRol.setV_imagen_horario("/Imagenes/actions/horario_gris.png");
                } else {
                    bRol.setV_imagen_horario("/Imagenes/actions/horario.png");
                    // sProfesor = daoClHor.seleccionarHorario( tmp_sec.getSecId() ).get( 0 ).getAcDocente().getDocNombre();
                }
                listaRol.add(bRol);
            }
        }

    }

    public void cargarArbolRol() {
        if (this.getRol_id() > 0) {
            iniciar(this.getRol_id());
        }
    }

    public void Nuevo() {
        this.setRol_tipo("");
        this.setRol_tipo_aux("");
    }

    public void EditarFila(ActionEvent event) {
        this.setRol_descripcion(this.getRol_descripcion());
        this.setRol_descripcion_aux(this.getRol_descripcion());
        this.setRol_activo(this.getRol_activo());
        this.setRol_activo_aux(this.getRol_activo());
        if ("0".equals(this.getRol_activo())) {
            this.setRol_activo_desc("Desactivado");
        } else {
            this.setRol_activo_desc("Activado");
        }

        this.setRol_activo_desc(this.getRol_activo_desc());
        this.setRol_activo_desc_aux(this.getRol_activo_desc());
        this.setView("false");
        this.setEditable("true");
        this.setEditable2(true);
        this.setVisible(true);
    }

    public void Enviar1(ActionEvent event) throws Exception {
        if (Grabar().equalsIgnoreCase("ok")) {

        }
    }

    public String Grabar() {

        if ((this.getRol_tipo().trim()).length() == 0) {
            this.setOncomplete("javascript:alert('Debe ingresar una descripcion')");

            return "fail";
        } else {

            HSRolDAO daoRol = (HSRolDAO) ServiceFinder.findBean("SpringHibernateDaoRol");
            TbRoles rol = new TbRoles();
            rol.setId(0);
            rol.setRolTipo(this.getRol_tipo());
            rol.setRolActivo("1");
            daoRol.insertarRol(rol);
            this.setOncomplete("javascript:alert('Se creo un Rol correctamente.');Richfaces.hideModalPanel('mp')");
            return "ok";
        }
    }

    public void EliminarFila(ActionEvent event) throws Exception {
        UIParameter delete = (UIParameter) event.getComponent().findComponent("delete");
        Eliminar(delete.getValue().toString());
    }

    public void Eliminar(String id) throws Exception {
        HSRolDAO dao = (HSRolDAO) ServiceFinder.findBean("SpringHibernateDaoRol");
        dao.eliminarRol(id);
    }

    public void Editar(ActionEvent event) {
        UIParameter id = (UIParameter) event.getComponent().findComponent("id");
        UIParameter descripcion = (UIParameter) event.getComponent().findComponent("desc");
        UIParameter activo = (UIParameter) event.getComponent().findComponent("activo");

        int iId = Integer.parseInt(id.getValue().toString());
        String sDescripcion = descripcion.getValue().toString();
        String sActivo = activo.getValue().toString();

        if ((sDescripcion.trim()).length() == 0) {
            this.setOncomplete("javascript:alert('debe ingresar una descripcion.')");

        } else {
            TbRoles rol = new TbRoles();
            rol.setId(iId);
            rol.setRolTipo(sDescripcion);
            rol.setRolActivo(sActivo);
            if ("0".equals(sActivo)) {
                this.setRol_activo_desc("Desactivado");
            } else {
                this.setRol_activo_desc("Activado");
            }
            HSRolDAO daoRol = (HSRolDAO) ServiceFinder.findBean("SpringHibernateDaoRol");
            daoRol.actualizarMenu(rol);

            this.setView("true");
            this.setEditable("false");
            this.setEditable2(false);
            this.setVisible(false);
            this.setOncomplete("javascript:alert('Se Actualizaron los datos del Rol correctamente.')");
        }
    }

    public void Cancelar(ActionEvent event) {
        this.setRol_descripcion(this.getRol_descripcion_aux());
        this.setRol_activo(this.getRol_activo_aux());
        if ("0".equals(this.getRol_activo_aux())) {
            this.setRol_activo_desc("Desactivado");
        } else {
            this.setRol_activo_desc("Activado");
        }
        this.setView("true");
        this.setEditable("false");
        this.setEditable2(false);
        this.setVisible(false);
    }

    public void iniciar(int idRol) {
        System.out.print(this.getRol_id());
        lstOpciones = new ArrayList<TbMenu>();
        List<TbMenu> lstMenuPadres = CommonDAO.getTbMenuDAO().menuPadre(this.getRol_id());
        List<TbMenu> lstMenuHijos = CommonDAO.getTbMenuDAO().menuHijos(this.getRol_id());
        
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

    private void agregarHijosArbol(TreeNode padre, int idPadre) {
        List<TbMenu> listaArbol = CommonDAO.getTbMenuDAO().listarArbolPorPadre(idPadre, this.getRol_id());
        for (TbMenu tbMenu : listaArbol) {
            TreeNode node1 = new TreeNodeImpl();
            node1.setData(tbMenu);
            padre.addChild(tbMenu.getMenId(), node1);

            agregarHijosArbol(node1, tbMenu.getMenId());
        }
    }

    private void cargarArbol(int idrol) {
        int iCantCat;
        int iCodCat = 0;
        TbMenu cat;
        List<TbMenu> lstCatTipoInst;
        TreeNodeImpl arbCategoria;

        try {
            lstCatTipoInst = CommonDAO.getTbMenuDAO().menuPadre(idrol);
            arbol = new TreeNodeImpl();
            iCantCat = lstCatTipoInst.size();
            for (int i = 0; i < iCantCat; i++) {
                cat = lstCatTipoInst.get(i);
                iCodCat = cat.getMenId();

                arbCategoria = new TreeNodeImpl();
                arbol.addChild(iCodCat, arbCategoria);
                arbol.getChild(iCodCat).setData(cat.getMenDescripcion());

            }

            agregarHijos(arbol, 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void agregarHijos(TreeNode node, int idPadre) {
        int iSizeArb;
        TbMenu clArbol;

        HSRolDAO daoArbolMenu;
        List<TbMenu> listaArbol;

        daoArbolMenu = CommonDAO.getTbMenuDAO();
        System.out.println(idPadre);
        listaArbol = daoArbolMenu.listarArbolPorPadre(idPadre, this.getRol_id());

        iSizeArb = listaArbol.size();

        for (int i = 0; i < iSizeArb; i++) {
            clArbol = listaArbol.get(i);

            if (Integer.parseInt(clArbol.getMenOpc()) > 0 && Integer.parseInt(clArbol.getMenOpc()) <= 3) {
                TreeNodeImpl nodeImpl = new TreeNodeImpl();
                TreeNodeImpl nodeImpl2 = new TreeNodeImpl();
                //nodeImpl.setData(listaArbol.get(i).getArbDescripcion()+"*"+ listaArbol.get(i).getArbId());
                nodeImpl.setData("<div style='float: left;'>" + clArbol.getMenDescripcion() + "</div>" + "<div style='color:#FFFFFF;position: left;'>*" + listaArbol.get(i).getMenId() + "</div>");
                nodeImpl2.setData(clArbol.getMenId());

                if (Integer.parseInt(clArbol.getMenOpc()) == 1) {
                    System.out.println(clArbol.getMenDescripcion());
                    node.getChild(clArbol.getMenIdPadre()).addChild(clArbol.getMenId(), nodeImpl);
                } else {
                    node.addChild(new Integer(i + 1), nodeImpl);
                }
                agregarHijos(nodeImpl, clArbol.getMenId());
            }

        }
    }

    public void seleccionarElemento(NodeSelectedEvent event) {
        HtmlTree tree;
        String id;
        TreeNode currentNode;
        UITree tree2;

        Map.Entry<Object, TreeNode> entry;
        Iterator<Map.Entry<Object, TreeNode>> it;

        tree = (HtmlTree) event.getComponent();
        tree2 = (UITree) event.getComponent();
        id = tree2.getRowKey().toString();

        nombreNodo = ((String) tree.getRowData()) + "  -  " + id;

        devolverId(nombreNodo);

        selectedNodeChildren.clear();

        currentNode = tree.getTreeNode(tree.getRowKey());
        if (currentNode.isLeaf()) {
            selectedNodeChildren.add((String) currentNode.getData());
        } else {
            it = currentNode.getChildren();
            while (it != null && it.hasNext()) {
                entry = it.next();
                selectedNodeChildren.add(entry.getValue().getData().toString());
            }
        }
    }

    public TreeNode getTreeNode() {
        return root;
    }

    public void cargarVista(ActionEvent event) throws Exception {
        UIParameter id = (UIParameter) event.getComponent().findComponent("p_id");
        int iId = Integer.parseInt(id.getValue().toString());
        this.setRol_id(iId);        
        cargarArbolRol();
        this.setOncomplete("javascript:Richfaces.showModalPanel('mpHoraria')");
    }

    public int devolverId(String texto) {
        int id;
        int valor_;
        int valor__;
        String numeroEs;

        valor_ = texto.indexOf("*") + 1;
        valor__ = texto.indexOf("<", valor_);
        numeroEs = texto.substring(valor_, valor__);
        id = Integer.parseInt(numeroEs);

        return id;
    }

    public SelectItem[] getCboMenu01() {
        HSMenuDAO dao = (HSMenuDAO) ServiceFinder.findBean("SpringHibernateDaoMenu");
        List lista = dao.seleccionarMenuI();
        cboMenu01 = new SelectItem[lista.size() + 1];
        cboMenu01[0] = new SelectItem(0, "TODOS");
        for (int i = 0; i < cboMenu01.length - 1; i++) {
            cboMenu01[i + 1] = new SelectItem(((TbMenu) lista.get(i)).getMenId(), ((TbMenu) lista.get(i)).getMenDescripcion());
        }
        List listaHijos = new ArrayList();
        if (this.getIdMenu01() != 0) {
            listaHijos = dao.seleccionarMenuHijo(this.getIdMenu01(), "1");
        }
        if (listaHijos.size() > 0) {
            setVista02(true);
        } else {
            this.setIdMenu02(0);
            this.setIdMenu03(0);
            this.setIdMenu04(0);
            setVista02(false);
            setVista03(false);
            setVista04(false);
        }
        return cboMenu01;
    }

    public void setCboMenu01(SelectItem[] cboMenu01) {
        this.cboMenu01 = cboMenu01;
    }

    public SelectItem[] getCboMenu02() {
        HSMenuDAO dao = (HSMenuDAO) ServiceFinder.findBean("SpringHibernateDaoMenu");
        List lista = new ArrayList();
        if (this.getIdMenu01() != 0) {
            lista = dao.seleccionarMenuHijo(this.getIdMenu01(), "1");
        }
        if (!lista.isEmpty()) {
            cboMenu02 = new SelectItem[lista.size() + 1];
            cboMenu02[0] = new SelectItem(0, "Seleccione");
            for (int i = 0; i < cboMenu02.length - 1; i++) {
                cboMenu02[i + 1] = new SelectItem(((TbMenu) lista.get(i)).getMenId(), ((TbMenu) lista.get(i)).getMenDescripcion());

            }
            if (this.getIdMenu02() != 0 && this.getIdMenu01() != 0) {
                List listaHijos = dao.seleccionarMenuHijo(this.getIdMenu02(), "1");
                if (listaHijos.size() > 0) {

                    setVista03(true);
                } else {
                    this.setIdMenu04(0);
                    setVista03(false);
                }
            }
        } else {
            setVista03(false);
            setVista04(false);
            this.setIdMenu03(0);
            this.setIdMenu04(0);
            cboMenu02 = new SelectItem[1];
            cboMenu02[0] = new SelectItem(0, "[Seleccione2]");
        }
        return cboMenu02;
    }

    public void setCboMenu02(SelectItem[] cboMenu02) {
        this.cboMenu02 = cboMenu02;
    }

    public SelectItem[] getCboMenu03() {
        HSMenuDAO dao = (HSMenuDAO) ServiceFinder.findBean("SpringHibernateDaoMenu");
        List lista = new ArrayList();
        if (this.getIdMenu02() != 0 && this.getIdMenu01() != 0) {
            lista = dao.seleccionarMenuHijo(this.getIdMenu02(), "2");
        }
        if (!lista.isEmpty()) {
            cboMenu03 = new SelectItem[lista.size() + 1];
            cboMenu03[0] = new SelectItem(0, "Seleccione");
            for (int i = 0; i < cboMenu03.length - 1; i++) {
                cboMenu03[i + 1] = new SelectItem(((TbMenu) lista.get(i)).getMenId(), ((TbMenu) lista.get(i)).getMenDescripcion());

            }

        } else {
            cboMenu03 = new SelectItem[1];
            cboMenu03[0] = new SelectItem(0, "[Seleccione3]");
            this.setIdMenu03(0);
            this.setIdMenu04(0);
            setVista03(false);
            setVista04(false);
            System.out.println("estado de combo 3:  " + this.isVista03());

        }
        if (this.getIdMenu03() != 0 && this.getIdMenu01() != 0 && this.getIdMenu02() != 0) {
            List listaHijos = dao.seleccionarMenuHijo(this.getIdMenu03(), "1");
            if (listaHijos.size() > 0) {
                setVista04(true);
            } else {
                setVista04(false);
            }
        }
        return cboMenu03;
    }

    public void setCboMenu03(SelectItem[] cboMenu03) {
        this.cboMenu03 = cboMenu03;
    }

    public SelectItem[] getCboMenu04() {
        HSMenuDAO dao = (HSMenuDAO) ServiceFinder.findBean("SpringHibernateDaoMenu");
        List lista = new ArrayList();
        if (this.getIdMenu03() != 0 && this.getIdMenu02() != 0 && this.getIdMenu01() != 0) {
            lista = dao.seleccionarMenuHijo(this.getIdMenu03(), "3");
        }
        if (!lista.isEmpty()) {
            cboMenu04 = new SelectItem[lista.size() + 1];
            cboMenu04[0] = new SelectItem(0, "Seleccione");
            for (int i = 0; i < cboMenu04.length - 1; i++) {
                cboMenu04[i + 1] = new SelectItem(((TbMenu) lista.get(i)).getMenId(), ((TbMenu) lista.get(i)).getMenDescripcion());
            }
            setVista04(true);
        } else {
            setVista04(false);
            cboMenu04 = new SelectItem[1];
            cboMenu04[0] = new SelectItem(0, "[Seleccione4]");
        }
        return cboMenu04;
    }

    public void setCboMenu04(SelectItem[] cboMenu04) {
        this.cboMenu04 = cboMenu04;
    }

    public Integer getIdMenu01() {
        return idMenu01;
    }

    public void setIdMenu01(Integer idMenu01) {
        this.idMenu01 = idMenu01;
    }

    public Integer getIdMenu02() {
        return idMenu02;
    }

    public void setIdMenu02(Integer idMenu02) {
        this.idMenu02 = idMenu02;
    }

    public Integer getIdMenu03() {
        return idMenu03;
    }

    public void setIdMenu03(Integer idMenu03) {
        this.idMenu03 = idMenu03;
    }

    public Integer getIdMenu04() {
        return idMenu04;
    }

    public void setIdMenu04(Integer idMenu04) {
        this.idMenu04 = idMenu04;
    }

    public boolean isVista02() {
        return vista02;
    }

    public void setVista02(boolean vista02) {
        this.vista02 = vista02;
    }

    public boolean isVista03() {
        return vista03;
    }

    public void setVista03(boolean vista03) {
        this.vista03 = vista03;
    }

    public boolean isVista04() {
        return vista04;
    }

    public void setVista04(boolean vista04) {
        this.vista04 = vista04;
    }

}
