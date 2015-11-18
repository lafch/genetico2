/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.tablaSistema.managedBeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcPagina;
import net.uch.mapping.TbMenu;
import net.uch.tablaSistema.hibernateSpringDao.HSAulaDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSMenuDAO;

/**
 *
 * @author Alumno
 */
public class bMenuMantenimiento {

    private int iIdMenu01;
    private int iIdMenu02;
    private int iIdMenu03;
    private int iIdMenu04;
    
    private boolean vista02 = false;
    private boolean vista03 = false;
    private boolean vista04 = false;

    private SelectItem[] cboMenu01;
    private SelectItem[] cboMenu02;
    private SelectItem[] cboMenu03;
    private SelectItem[] cboMenu04;

    public int b_id;
    public String bMenCodigo;
    public String bMenDesc;
    public String bMenAccion;
    public String sDesPagina;
    public String sMenNivel;
    public String sEstado;
    public int bIdPadreMenu = 0;
    public String bDesPadreMenu;
    public String bColorNivel;

    public String bMenCodigoAux;
    public String bMenDescAux;
    public String bMenAccionAux;
    public int bIdPadreMenuAux;
    public String sMenNivelAux;
    public String sEstadoAux;

    private String oncomplete;

    private SelectItem[] cboPagina;
    private SelectItem[] cboPadreMenu;
    private SelectItem[] cboOpcion;

    private List listaMenu;

    private String editable = "false";
    private String view = "true";
    private boolean visible = false;
    private boolean editable2 = false;

    public int getiIdMenu01() {
        return iIdMenu01;
    }

    public void setiIdMenu01(int iIdMenu01) {
        this.iIdMenu01 = iIdMenu01;
    }

    public int getiIdMenu02() {
        return iIdMenu02;
    }

    public void setiIdMenu02(int iIdMenu02) {
        this.iIdMenu02 = iIdMenu02;
    }

    public int getiIdMenu03() {
        return iIdMenu03;
    }

    public void setiIdMenu03(int iIdMenu03) {
        this.iIdMenu03 = iIdMenu03;
    }

    public int getiIdMenu04() {
        return iIdMenu04;
    }

    public void setiIdMenu04(int iIdMenu04) {
        this.iIdMenu04 = iIdMenu04;
    }

    //para la tabla
    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    public String getbMenCodigo() {
        return bMenCodigo;
    }

    public void setbMenCodigo(String bMenCodigo) {
        this.bMenCodigo = bMenCodigo;
    }

    public String getbMenDesc() {
        return bMenDesc;
    }

    public void setbMenDesc(String bMenDesc) {
        this.bMenDesc = bMenDesc;
    }

    public String getbMenAccion() {
        return bMenAccion;
    }

    public void setbMenAccion(String bMenAccion) {
        this.bMenAccion = bMenAccion;
    }

    public String getsDesPagina() {
        return sDesPagina;
    }

    public void setsDesPagina(String sDesPagina) {
        this.sDesPagina = sDesPagina;
    }

    public String getsMenNivel() {
        return sMenNivel;
    }

    public void setsMenNivel(String sMenNivel) {
        this.sMenNivel = sMenNivel;
    }

    public String getsEstado() {
        return sEstado;
    }

    public void setsEstado(String sEstado) {
        this.sEstado = sEstado;
    }

    public int getbIdPadreMenu() {
        return bIdPadreMenu;
    }

    public void setbIdPadreMenu(int bIdPadreMenu) {
        this.bIdPadreMenu = bIdPadreMenu;
    }

    public String getbDesPadreMenu() {
        return bDesPadreMenu;
    }

    public void setbDesPadreMenu(String bDesPadreMenu) {
        this.bDesPadreMenu = bDesPadreMenu;
    }

    public List getListaMenu() {
        return listaMenu;
    }

    public void setListaMenu(List listaMenu) {
        this.listaMenu = listaMenu;
    }

    public String getbColorNivel() {
        return bColorNivel;
    }

    public void setbColorNivel(String bColorNivel) {
        this.bColorNivel = bColorNivel;
    }

    //efecto de edicion
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

    //almacenar temporalmente
    public String getbMenCodigoAux() {
        return bMenCodigoAux;
    }

    public void setbMenCodigoAux(String bMenCodigoAux) {
        this.bMenCodigoAux = bMenCodigoAux;
    }

    public String getbMenDescAux() {
        return bMenDescAux;
    }

    public void setbMenDescAux(String bMenDescAux) {
        this.bMenDescAux = bMenDescAux;
    }

    public String getbMenAccionAux() {
        return bMenAccionAux;
    }

    public void setbMenAccionAux(String bMenAccionAux) {
        this.bMenAccionAux = bMenAccionAux;
    }

    public int getbIdPadreMenuAux() {
        return bIdPadreMenuAux;
    }

    public void setbIdPadreMenuAux(int bIdPadreMenuAux) {
        this.bIdPadreMenuAux = bIdPadreMenuAux;
    }

    public String getsMenNivelAux() {
        return sMenNivelAux;
    }

    public void setsMenNivelAux(String sMenNivelAux) {
        this.sMenNivelAux = sMenNivelAux;
    }

    public String getsEstadoAux() {
        return sEstadoAux;
    }

    public void setsEstadoAux(String sEstadoAux) {
        this.sEstadoAux = sEstadoAux;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete(String oncomplete) {
        this.oncomplete = oncomplete;
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
    
    

    public SelectItem[] getCboMenu01() {
        HSMenuDAO dao = (HSMenuDAO) ServiceFinder.findBean("SpringHibernateDaoMenu");
        List lista = dao.seleccionarMenuI();
        cboMenu01 = new SelectItem[lista.size() + 1];
        cboMenu01[0] = new SelectItem(0, "TODOS");
        for (int i = 0; i < cboMenu01.length - 1; i++) {
            cboMenu01[i + 1] = new SelectItem(((TbMenu) lista.get(i)).getMenId(), ((TbMenu) lista.get(i)).getMenDescripcion());
                    }
        List listaHijos = new ArrayList();
        if(this.getiIdMenu01() != 0){
            listaHijos = dao.seleccionarMenuHijo(this.getiIdMenu01(), "1");
        }
        if(listaHijos.size()>0){
                setVista02(true);
        }else{  
                this.setiIdMenu02(0);
                this.setiIdMenu03(0);
                this.setiIdMenu04(0);
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
        if(this.getiIdMenu01() != 0){
            lista = dao.seleccionarMenuHijo(this.getiIdMenu01(), "1");
        }
        if (!lista.isEmpty()) {
            cboMenu02 = new SelectItem[lista.size() + 1];
            cboMenu02[0] = new SelectItem(0, "Seleccione");
            for (int i = 0; i < cboMenu02.length - 1; i++) {
                cboMenu02[i + 1] = new SelectItem(((TbMenu) lista.get(i)).getMenId(), ((TbMenu) lista.get(i)).getMenDescripcion());
               
            }
            if(this.getiIdMenu02()!=0 && this.getiIdMenu01() != 0){
            List listaHijos = dao.seleccionarMenuHijo(this.getiIdMenu02(), "1");
                if(listaHijos.size()>0){
                    
                    setVista03(true);
                } else{
                    this.setiIdMenu04(0);
                    setVista03(false);
                } 
            }
        } else {
            setVista03(false);
            setVista04(false);
            this.setiIdMenu03(0);
            this.setiIdMenu04(0);
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
        System.out.println("menu02::::" + this.getiIdMenu02());
        List lista = new ArrayList();
        if(this.getiIdMenu02() != 0 && this.getiIdMenu01() != 0){
         lista = dao.seleccionarMenuHijo(this.getiIdMenu02(), "2");
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
            this.setiIdMenu03(0);
            this.setiIdMenu04(0);
            setVista03(false);
            setVista04(false);
            System.out.println("estado de combo 3:  "+this.isVista03());
            
        }
        if(this.getiIdMenu03()!=0 && this.getiIdMenu01()!=0 && this.getiIdMenu02()!=0){
            List listaHijos = dao.seleccionarMenuHijo(this.getiIdMenu03(), "1");
            if(listaHijos.size()>0){
                 setVista04(true);
            }else{
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
        if(this.getiIdMenu03() != 0  && this.getiIdMenu02()!=0 && this.getiIdMenu01()!=0){
            lista = dao.seleccionarMenuHijo(this.getiIdMenu03(), "3");
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

    public SelectItem[] getCboPagina() {
        HSMenuDAO dao = (HSMenuDAO) ServiceFinder.findBean("SpringHibernateDaoMenu");
        List lista = dao.paginas();
        if (!lista.isEmpty()) {
            cboPagina = new SelectItem[lista.size() + 1];
            cboPagina[0] = new SelectItem(0, "Seleccione");
            for (int i = 0; i < cboPagina.length - 1; i++) {
                cboPagina[i + 1] = new SelectItem(((AcPagina) lista.get(i)).getPagUrl(), ((AcPagina) lista.get(i)).getPagUrl());
            }
        } else {
            cboMenu04 = new SelectItem[1];
            cboMenu04[0] = new SelectItem(0, "[Seleccione]");
        }
        return cboPagina;
    }

    public void setCboPagina(SelectItem[] cboPagina) {
        this.cboPagina = cboPagina;
    }

    public SelectItem[] getCboPadreMenu() {
        HSMenuDAO dao = (HSMenuDAO) ServiceFinder.findBean("SpringHibernateDaoMenu");
        List lista = dao.menuPadres();
        if (!lista.isEmpty()) {
            cboPadreMenu = new SelectItem[lista.size() + 1];
            cboPadreMenu[0] = new SelectItem(0, "Seleccione");
            for (int i = 0; i < cboPadreMenu.length - 1; i++) {
                cboPadreMenu[i + 1] = new SelectItem(((TbMenu) lista.get(i)).getMenId(), ((TbMenu) lista.get(i)).getMenDescripcion() + "-" + ((TbMenu) lista.get(i)).getMenOpc());
            }
        } else {
            cboPadreMenu = new SelectItem[1];
            cboPadreMenu[0] = new SelectItem(0, "[Seleccione]");
        }
        return cboPadreMenu;
    }

    public void setCboPadreMenu(SelectItem[] cboPadreMenu) {
        this.cboPadreMenu = cboPadreMenu;
    }

    public SelectItem[] getCboOpcion() {

        int sIdPadreMenu
                = this.getbIdPadreMenu();
        HSMenuDAO dao = (HSMenuDAO) ServiceFinder.findBean("SpringHibernateDaoMenu");
        List lista = dao.niveles(sIdPadreMenu);
        if (!lista.isEmpty()) {
            cboOpcion = new SelectItem[lista.size() + 1];
            cboOpcion[0] = new SelectItem(0, "Seleccione");
            for (int i = 0; i < cboOpcion.length - 1; i++) {
                TbMenu men = (TbMenu) lista.get(i);
                cboOpcion[i + 1] = new SelectItem(men.getMenOpc(), men.getMenOpc());
            }
        } else {
            cboOpcion = new SelectItem[1];
            cboOpcion[0] = new SelectItem(0, "0");
        }
        return cboOpcion;
    }

    public void setCboOpcion(SelectItem[] cboOpcion) {
        this.cboOpcion = cboOpcion;
    }

    public void Seleccionar() throws Exception {
        listaMenu = new ArrayList();

        HSMenuDAO dao = (HSMenuDAO) ServiceFinder.findBean("SpringHibernateDaoMenu");
        int iIdMenuPadre = 0;
        String iNivel = "99";
        if (this.getiIdMenu01() != 0) {
            iIdMenuPadre = this.getiIdMenu01();
            iNivel = "0";
            if (this.getiIdMenu02() != 0) {
                iIdMenuPadre = this.getiIdMenu02();
                iNivel = "1";
                if (this.getiIdMenu03() != 0) {
                    iIdMenuPadre = this.getiIdMenu03();
                    iNivel = "2";
                    if (this.getiIdMenu04() != 0) {
                        iIdMenuPadre = this.getiIdMenu04();
                        iNivel = "3";
                    }
                }
            }

        }
        if (!iNivel.equals("99")) {
            List<TbMenu> lista = dao.seleccionarMenuHijos(iIdMenuPadre, iNivel);
            bMenuMantenimiento bA;
            if (lista.size() > 0) {
                for (int i = 0; i < lista.size(); i++) {

                    TbMenu menu = lista.get(i);
                    bA = new bMenuMantenimiento();
                    bA.setB_id(menu.getMenId());
                    bA.setbMenCodigo(menu.getMenCodigo());
                    bA.setbMenDesc(menu.getMenDescripcion());
                    if (menu.getMenIdPadre() == 0) {
                        bA.setbIdPadreMenu(0);
                        bA.setbDesPadreMenu("");
                    } else {
                        bA.setbIdPadreMenu(menu.getMenIdPadre());
                        bA.setbDesPadreMenu(menuDescripcion(menu.getMenIdPadre()));
                    }
                    if ("0".equals(menu.getMenAccion())) {
                        bA.setbMenAccion("");
                    } else {
                        bA.setbMenAccion(menu.getMenAccion());
                    }

                    bA.setsEstado(menuEstado(menu.getMenId()));
                    bA.setsMenNivel(menu.getMenOpc());
                    bA.setbColorNivel(colorMenu(menu.getMenOpc(), bA.getsEstado()));
                    listaMenu.add(bA);
                }
            }
        }
    }

    public String menuDescripcion(int iMenuId) {
        String menuDesc = "";
        if (iMenuId != 0) {
            HSMenuDAO dao = (HSMenuDAO) ServiceFinder.findBean("SpringHibernateDaoMenu");
            menuDesc = dao.buscarMenu(iMenuId);
        }
        return menuDesc;
    }

    public String menuEstado(int iMenuId) {
        HSMenuDAO dao = (HSMenuDAO) ServiceFinder.findBean("SpringHibernateDaoMenu");
        String Estado = dao.hijosMenu(iMenuId);
        return Estado;
    }

    public void Nuevo() {
        String codigo = generarId();
        this.setbMenCodigoAux(codigo);
        this.setbMenCodigo(codigo);
        this.setbMenDescAux("");
        this.setbMenDesc("");
        this.setbMenAccionAux("0");
        this.setbMenAccion("0");
        this.setbIdPadreMenuAux(0);
        this.setbIdPadreMenu(0);
        this.setsMenNivelAux("0");
        this.setsMenNivel("0");
    }

    public void EditarFila(ActionEvent event) {
        this.setbMenCodigoAux(this.getbMenCodigo());
        this.setbMenDescAux(this.getbMenDesc());
        this.setbMenAccionAux(this.getbMenAccion());
        this.setbIdPadreMenuAux(this.getbIdPadreMenu());
        this.setsMenNivelAux(this.getsMenNivel());
        this.setsEstadoAux(this.getsEstado());
        this.setView("false");
        this.setEditable("true");
        this.setEditable2(true);
        this.setVisible(true);
    }

    private String colorMenu(String menOpc, String estado) {
        String color = "";
        if ("0".equals(menOpc) && "Tiene SubMenus".equals(estado)) {
            color = "background-color:#6F97CE; border-color:#6F97CE; color:#FFF;";
        } else if ("1".equals(menOpc) && "Tiene SubMenus".equals(estado)) {
            color = "background-color:#94B1DA; border-color:#94B1DA; color:#FFF";
        } else {
            color = "background-color:#FFF";
        }
        return color;
    }

    public void Enviar1(ActionEvent event) throws Exception {
        if (Grabar().equalsIgnoreCase("ok")) {
            Seleccionar();
        }
    }

    public String Grabar() {

        if ((this.getbMenCodigo().trim()).length() == 0
                || (this.getbMenDesc().trim()).length() == 0) {
            if ((this.getbMenDesc().trim()).length() == 0) {
                this.setOncomplete("javascript:alert('Debe ingresar una descripcion')");
            }
            return "fail";
        } else {
            if (this.getbIdPadreMenu() != 0 && "0".equals(this.getsMenNivel())) {
                this.setOncomplete("javascript:alert('Al elegir un menu padre debe elegir el nivel sugerido')");
                return "fail";
            } else {
                HSMenuDAO dao = (HSMenuDAO) ServiceFinder.findBean("SpringHibernateDaoMenu");
                TbMenu menu = new TbMenu();
                menu.setMenId(this.getB_id());
                menu.setMenCodigo(this.getbMenCodigo());
                menu.setMenDescripcion(this.getbMenDesc());
                menu.setMenOpc(this.getsMenNivel());
                if ("0".equals(this.getbMenAccion())) {
                    menu.setMenAccion("");
                } else {
                    menu.setMenAccion(this.getbMenAccion());
                }

                menu.setMenActivo("1");
                menu.setMenIdPadre(this.getbIdPadreMenu());
                dao.insertarMenu(menu);
                this.setOncomplete("javascript:alert('Se creo un Menu correctamente.');Richfaces.hideModalPanel('mp')");
                return "ok";
            }
        }
    }

    public void EliminarFila(ActionEvent event) throws Exception {
        UIParameter delete = (UIParameter) event.getComponent().findComponent("delete");
        Eliminar(delete.getValue().toString());
    }

    public void Eliminar(String id) throws Exception {
        HSMenuDAO dao = (HSMenuDAO) ServiceFinder.findBean("SpringHibernateDaoMenu");
        String verificar = dao.hijosMenuEliminar(id);
        if (!"1".equals(verificar)) {
            dao.eliminarMenu(id);
            this.setOncomplete("javascript:alert('Se elimino correctamente');");
        } else {
            this.setOncomplete("javascript:alert('El registro que desea eliminar tiene submenus asignados');");
        }
    }

    public void Editar(ActionEvent event) {
        UIParameter id = (UIParameter) event.getComponent().findComponent("id");
        UIParameter codigo = (UIParameter) event.getComponent().findComponent("codigo");
        UIParameter descripcion = (UIParameter) event.getComponent().findComponent("desc");
        UIParameter nivel = (UIParameter) event.getComponent().findComponent("nivel");
        UIParameter accion = (UIParameter) event.getComponent().findComponent("accion");
        UIParameter idPadre = (UIParameter) event.getComponent().findComponent("idpadre");
        UIParameter estado2 = (UIParameter) event.getComponent().findComponent("estado");
        int iId = Integer.parseInt(id.getValue().toString());
        String sCodigo = codigo.getValue().toString();
        String sDescripcion = descripcion.getValue().toString();

        String sNivel = nivel.getValue().toString();
        String sAccion = accion.getValue().toString();
        int iIdPadre = Integer.parseInt(idPadre.getValue().toString());
        String estado = estado2.getValue().toString();

        if ((sCodigo.trim()).length() == 0
                || (sDescripcion.trim()).length() == 0) {
            this.setOncomplete("javascript:alert('Ingrese Datos.')");
        } else if (!"0".equals(sAccion) && "Tiene SubMenus".equals(estado)) {
            this.setOncomplete("javascript:alert('No se puede asignar una accion mientras tenga submenus a su cargo')");
        } else {
            if ("0".equals(sAccion)) {
                sAccion = "";
            }

            TbMenu menu = new TbMenu();
            menu.setMenId(iId);
            menu.setMenCodigo(sCodigo);
            menu.setMenDescripcion(sDescripcion);

            menu.setMenOpc(sNivel);
            menu.setMenAccion(sAccion);
            menu.setMenActivo("1");
            menu.setMenIdPadre(iIdPadre);

            HSMenuDAO dao = (HSMenuDAO) ServiceFinder.findBean("SpringHibernateDaoMenu");
            dao.actualizarMenu(menu);
            this.setView("true");
            this.setEditable("false");
            this.setEditable2(false);
            this.setVisible(false);
            this.setOncomplete("javascript:alert('Se Actualizaron los datos del Menu correctamente.')");
        }
    }

    public String generarId() {
        HSMenuDAO dao = (HSMenuDAO) ServiceFinder.findBean("SpringHibernateDaoMenu");
        String codigo = dao.codigoMenu();
        return codigo;
    }

    public void Cancelar(ActionEvent event) {
        this.setbMenDesc(this.getbMenDescAux());
        this.setbIdPadreMenu(this.getbIdPadreMenuAux());
        this.setsMenNivel(this.getsMenNivelAux());
        this.setbMenAccion(this.getbMenAccionAux());
        this.setView("true");
        this.setEditable("false");
        this.setEditable2(false);
        this.setVisible(false);
    }
}
