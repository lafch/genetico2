package net.uch.tablaSistema.managedBeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;

import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcPagina;
import net.uch.tablaSistema.hibernateSpringDao.HSPaginaDAO;

public class bPagina {
    public int b_id;
    public String b_des;
    public String b_des_i;
    public String b_des_pagina;
    public String b_des_auxiliar;
    public String b_url;
    public String b_url_auxiliar;
    
    public String b_activo;
    private List listaPagina;
    private String editable = "false";
    private String view = "true";
    private boolean visible = false;

    private String oncomplete;
    private boolean editable2 = false;
    

    public void Nuevo() {
        this.setB_des_auxiliar( "" );
        this.setB_des_i( "" );
        this.setB_url("");
        this.setB_url_auxiliar("");
    }

    public bPagina() {
        //aula = new bAula();
    }
    
    public String getEditable() {
        return editable;
    }

    public void setEditable( String editable ) {
        this.editable = editable;
    }

    public String getView() {
        return view;
    }

    public void setView( String view ) {
        this.view = view;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible( boolean visible ) {
        this.visible = visible;
    }

    public List getListaPagina() {
        return listaPagina;
    }

    public void setListaPagina( List listaPagina ) {
        this.listaPagina = listaPagina;
    }

    public int getB_id() {
        return b_id;
    }

    public void setB_id( int b_id ) {
        this.b_id = b_id;
    }

    public String getB_des() {
        return b_des;
    }

    public void setB_des( String b_des ) {
        this.b_des = b_des;
    }

    public String getB_des_pagina() {
        return b_des_pagina;
    }

    public void setB_des_pagina(String b_des_pagina) {
        this.b_des_pagina = b_des_pagina;
    }
    
    public String getB_url() {
        return b_url;
    }

    public void setB_url( String b_url ) {
        this.b_url = b_url;
    }

    public String getB_activo() {
        return b_activo;
    }

    public void setB_activo( String b_activo ) {
        this.b_activo = b_activo;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete( String oncomplete ) {
        this.oncomplete = oncomplete;
    }

    public boolean isEditable2() {
        return editable2;
    }

    public void setEditable2( boolean editable2 ) {
        this.editable2 = editable2;
    }

    public String Grabar() {
        if ( ( this.getB_des_i().trim() ).length() == 0 || ( this.getB_url().trim() ).length() <= 0 ) {
            this.setOncomplete( "javascript:alert('Ingrese todos los datos Requeridos.')" );
            return "fail";
        } else {
            HSPaginaDAO dao = (HSPaginaDAO)ServiceFinder.findBean( "SpringHibernateDaoPagina" );
            AcPagina pag = new AcPagina();
            pag.setId( this.getB_id() );
            pag.setPagDes( this.getB_des_i() );
            pag.setPagUrl(this.getB_url() );
            pag.setPagActivo( "1" );
            dao.insertarPagina( pag );
            this.setOncomplete( "javascript:alert('Se creo un Pagina correctamente.');Richfaces.hideModalPanel('mp')" );
            return "ok";
        }
    }

    public void Seleccionar() throws Exception {
        listaPagina = new ArrayList();
        
        HSPaginaDAO dao = (HSPaginaDAO)ServiceFinder.findBean( "SpringHibernateDaoPagina" );
        List<AcPagina> lista = dao.seleccionarPagina(this.getB_des(), this.getB_des_pagina());
        bPagina bA;
        for ( int i = 0; i < lista.size(); i++ ) {

            AcPagina pagina = lista.get( i );
            bA = new bPagina();
            bA.setB_id( pagina.getId().intValue() );
            bA.setB_des( pagina.getPagDes());
            bA.setB_url( pagina.getPagUrl());
            listaPagina.add( bA );
        }
    }

    
    public String getB_des_auxiliar() {
        return b_des_auxiliar;
    }

    public void setB_des_auxiliar( String b_des_auxiliar ) {
        this.b_des_auxiliar = b_des_auxiliar;
    }

    public String getB_url_auxiliar() {
        return b_url_auxiliar;
    }

    public void setB_url_auxiliar( String b_url_auxiliar ) {
        this.b_url_auxiliar = b_url_auxiliar;
    }

   

    public void Cancelar( ActionEvent event ) {
        this.setB_des( this.getB_des_auxiliar() );
        this.setB_url( this.getB_url_auxiliar() );
        this.setView( "true" );
        this.setEditable( "false" );
        this.setEditable2( false );
        this.setVisible( false );
    }

    public void EditarFila( ActionEvent event ) {
        this.setB_des_auxiliar( this.getB_des() );
        this.setB_url_auxiliar( this.getB_url() );
        this.setView( "false" );
        this.setEditable( "true" );
        this.setEditable2( true );
        this.setVisible( true );
    }

    public void Editar( ActionEvent event ) {
        UIParameter id = (UIParameter)event.getComponent().findComponent( "id" );
        UIParameter descripcion = (UIParameter)event.getComponent().findComponent( "descripcion" );
        UIParameter url = (UIParameter)event.getComponent().findComponent( "url" );
       

        if ( ( descripcion.getValue().toString().trim() ).length() < 5 || ( url.getValue().toString().trim() ).length() < 5) {
            this.setOncomplete( "javascript:alert('Ingrese Datos.')" );
        } else {
            int pagId = Integer.parseInt( id.getValue().toString() );
            AcPagina pagina = new AcPagina();
            pagina.setId( pagId );
            pagina.setPagDes( descripcion.getValue().toString() );
            pagina.setPagUrl(url.getValue().toString());
            pagina.setPagActivo( "1" );

            HSPaginaDAO dao = (HSPaginaDAO)ServiceFinder.findBean( "SpringHibernateDaoPagina" );
            dao.actualizarPagina(pagina );

            this.setView( "true" );
            this.setEditable( "false" );
            this.setEditable2( false );
            this.setVisible( false );
            this.setOncomplete( "javascript:alert('Se Actualizaron los datos del Pagina correctamente.')" );
        }
    }

    public void EliminarFila( ActionEvent event ) throws Exception {
        UIParameter delete = (UIParameter)event.getComponent().findComponent( "delete" );
        Eliminar( delete.getValue().toString() );
    }

    public void Eliminar( String id ) throws Exception {
        HSPaginaDAO dao = (HSPaginaDAO)ServiceFinder.findBean( "SpringHibernateDaoPagina" );
        dao.eliminarPagina( id );
    }

    public String getB_des_i() {
        return b_des_i;
    }

    public void setB_des_i( String b_des_i ) {
        this.b_des_i = b_des_i;
    }

    public void Enviar1( ActionEvent event ) throws Exception {
        if ( Grabar().equalsIgnoreCase( "ok" ) ) {
            Seleccionar();
        }
    }
}
