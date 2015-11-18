/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.tablaSistema.managedBeans;

import java.util.ArrayList;
import java.util.List;
import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcPagina;
import net.uch.mapping.TbMenu;
import net.uch.tablaSistema.hibernateSpringDao.HSMenuDAO;
import org.richfaces.component.html.HtmlDropDownMenu;
import org.richfaces.component.html.HtmlMenuGroup;
import org.richfaces.component.html.HtmlMenuItem;
import org.richfaces.component.html.HtmlToolBar;

/**
 *
 * @author cesardl
 */
public class bMenu {

    private HtmlToolBar toolBar = new HtmlToolBar();
    private int rol_id;
    private String usu_usuario;

    public bMenu(int rol_id, String usu_usuario) {
        this.rol_id = rol_id;
        this.usu_usuario = usu_usuario;
        this.armarMenu();
    }

    public int getRol_id() {
        return rol_id;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }

    public String getUsu_usuario() {
        return usu_usuario;
    }

    public void setUsu_usuario(String usu_usuario) {
        this.usu_usuario = usu_usuario;
    }

    public HtmlToolBar getToolBar() {
        return toolBar;
    }

    public void setToolBar(HtmlToolBar toolBar) {
        this.toolBar = toolBar;
    }

    public void armarMenu() {
        HSMenuDAO daoMenu = (HSMenuDAO) ServiceFinder.findBean("SpringHibernateDaoMenu");
        List<TbMenu> menus = daoMenu.seleccionToolBar(this.getRol_id());

        List<UIComponent> childrenToolBar = getToolBar().getChildren();
        childrenToolBar.clear();

        Application app = FacesContext.getCurrentInstance().getApplication();
        HtmlMenuGroup hmg = (HtmlMenuGroup) app.createComponent(HtmlMenuGroup.COMPONENT_TYPE);
        HtmlDropDownMenu hddm;

        List<UIComponent> childrenMenuGroup = hmg.getChildren();
        childrenMenuGroup.clear();

        HtmlGraphicImage hgi;
        HtmlOutputText hot;
        HtmlPanelGroup hpg;
        for (TbMenu menu : menus) {
            hddm = (HtmlDropDownMenu) app.createComponent(HtmlDropDownMenu.COMPONENT_TYPE);

            List<UIComponent> childrenDropDownMenu = hddm.getChildren();
            childrenDropDownMenu.clear();

            hpg = (HtmlPanelGroup) app.createComponent(HtmlPanelGroup.COMPONENT_TYPE);

            hgi = (HtmlGraphicImage) app.createComponent(HtmlGraphicImage.COMPONENT_TYPE);
            hgi.setValue(menu.getMenAccion());
            hpg.getChildren().add(hgi);

            hot = (HtmlOutputText) app.createComponent(HtmlOutputText.COMPONENT_TYPE);
            hot.setValue(menu.getMenDescripcion());
            hpg.getChildren().add(hot);

            hddm.getFacets().put("label", hpg);

            childrenDropDownMenu.addAll(generarHijos(app, menu.getMenId(), this.getRol_id(), childrenMenuGroup));

            childrenToolBar.add(hddm);
        }
        hot = (HtmlOutputText) app.createComponent(HtmlOutputText.COMPONENT_TYPE);
        hot.setEscape(false);
        hot.setValue("&nbsp;Usuario: &nbsp;&nbsp;" + this.getUsu_usuario());
        childrenToolBar.add(hot);
    }

    public List<UIComponent> generarHijos(Application app, int padre_id, int rol_id, List<UIComponent> children) {
        HSMenuDAO dao = (HSMenuDAO) ServiceFinder.findBean("SpringHibernateDaoMenu");
        List<TbMenu> h = dao.seleccionHijos(padre_id, rol_id);

        for (TbMenu m : h) {
            if (m.getMenAccion().equalsIgnoreCase("") ) {
                HtmlMenuGroup hmg = (HtmlMenuGroup) app.createComponent(HtmlMenuGroup.COMPONENT_TYPE);
                hmg.setValue(m.getMenDescripcion());

                List<UIComponent> l = generarHijos(app, m.getMenId(), rol_id, hmg.getChildren());
                hmg.getChildren().addAll(l);

                children.add(hmg);
            } else if (!m.getMenAccion().equalsIgnoreCase("")) {
                HtmlMenuItem hmi = (HtmlMenuItem) app.createComponent(HtmlMenuItem.COMPONENT_TYPE);
                hmi.setActionExpression(crearMetodo(app, m.getMenAccion()));
                
                hmi.setSubmitMode("server");
                hmi.setValue(m.getMenDescripcion());
                children.add(hmi);
            }
        }
        return children;
    }

    private MethodExpression crearMetodo(Application application, String action) {
        return application.getExpressionFactory().
                createMethodExpression(FacesContext.getCurrentInstance().getELContext(),
                action, null, new Class<?>[0]);
    }
    
}
