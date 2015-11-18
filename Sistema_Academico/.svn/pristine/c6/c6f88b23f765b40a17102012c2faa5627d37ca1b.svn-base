/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import net.uch.asistenciaDocente.hibernateSpringDao.HSAsistenciaDocenteDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSSeccionCLDAO;
import net.uch.cursoLibre.managedBeans.beans.BeanReporte;
import net.uch.mapping.*;
import net.uch.util.CommonDAO;

/**
 *
 * @author LUIS
 */
public class bProrrogaIngresoNotasCL {

    private int docId;
    private int secId;
    private String sCurNombre;
    private String b_doc_nombre;
    private String sEspNombre;
    private String sSecNombre;
    private String docCodigo;//esto
    private String docNombre;//esto
    private String oncomplete;//esto
    private String suggestion;//esto
    private String sObsProrroga;
    private String fProrroga;
    private Date fechaProrroga = new Date();
    private List<BeanReporte> lstSecciones;

    public String getCurNombre() {
        return sCurNombre;
    }

    public void setCurNombre( String b_cur_nombre ) {
        this.sCurNombre = b_cur_nombre;
    }

    public String getB_doc_nombre() {
        return b_doc_nombre;
    }

    public void setB_doc_nombre( String b_doc_nombre ) {
        this.b_doc_nombre = b_doc_nombre;
    }

    public String getEspNombre() {
        return sEspNombre;
    }

    public void setEspNombre( String sEspNombre ) {
        this.sEspNombre = sEspNombre;
    }

    public String getSecNombre() {
        return sSecNombre;
    }

    public void setSecNombre( String sSecNombre ) {
        this.sSecNombre = sSecNombre;
    }

    public String getsObsProrroga() {
        return sObsProrroga;
    }

    public void setsObsProrroga( String sObsProrroga ) {
        this.sObsProrroga = sObsProrroga;
    }

    public Date getFechaProrroga() {
        return fechaProrroga;
    }

    public void setFechaProrroga( Date fechaProrroga ) {
        this.fechaProrroga = fechaProrroga;
    }

    public int getSecId() {
        return secId;
    }

    public void setSecId( int secId ) {
        this.secId = secId;
    }

    /**
     * Creates a new instance of bAsistenciaDocente
     */
    //-- SETTERS Y GETTERS
    public int getDocId() {
        return docId;
    }

    public void setDocId( int docId ) {
        this.docId = docId;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete( String oncomplete ) {
        this.oncomplete = oncomplete;
    }
    /*esto*/

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion( String suggestion ) {
        this.suggestion = suggestion;
    }

    public String getDocCodigo() {
        return docCodigo;
    }

    public void setDocCodigo( String docCodigo ) {
        this.docCodigo = docCodigo;
    }

    public String getDocNombre() {
        return docNombre;
    }

    public void setDocNombre( String docNombre ) {
        this.docNombre = docNombre;
    }

    public List<BeanReporte> getLstSecciones() {
        return lstSecciones;
    }

    public void setLstSesiones( List<BeanReporte> lstSecciones ) {
        this.lstSecciones = lstSecciones;
    }

    public String getfProrroga() {
        return fProrroga;
    }

    public void setfProrroga( String fProrroga ) {
        this.fProrroga = fProrroga;
    }

    public void mostrarSecciones( ActionEvent event ) throws Exception {
        int DocID;
        //iAluId = Integer.parseInt( ((UIParameter) event.getComponent().findComponent("idAL" )).getValue().toString() );  
        DocID = Integer.parseInt( (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get( "form1:idAL" ) );
        HSSeccionCLDAO daoSeccion;
        try {
            daoSeccion = CommonDAO.getClSeccionDAO();
            lstSecciones = daoSeccion.listarSeccionesXDocente( DocID );
//            if(lstSecciones.get(10)){
//                
//            }

        } catch ( Exception e ) {
            lstSecciones = new ArrayList<BeanReporte>();
            e.printStackTrace();
        }
        // iAluId= CommonWeb.parseObjToInt( CommonWeb.getParamFromUIFacesContext( event, "idAL" ) );
    }

    //--ACTIONS 
    /*esto*/
    public List<AcDocente> autocomplete( Object suggestion ) {
        AcDocente acDocente;
        List<AcDocente> listaEmpleados = new ArrayList<AcDocente>();
        String ref = (String)suggestion;
        HSAsistenciaDocenteDAO dao = CommonDAO.getAsistenciaDocenteDAO();
        List<AcDocente> lstDocentes = dao.buscarDocente_x_dato( ref );
//        System.out.println("entro al auto");
        for ( int i = 0; i < lstDocentes.size(); i++ ) {
            acDocente = new AcDocente( lstDocentes.get( i ).getId() );
            acDocente.setDocCodigo( lstDocentes.get( i ).getDocCodigo() );
            acDocente.setDocNombre( ( lstDocentes.get( i ) ).getDocNombre() );
            listaEmpleados.add( acDocente );
        }
        return listaEmpleados;
    }

    public void abrirProrroga( ActionEvent event ) throws ParseException {
        int iSecId;
        String sFechaPro;
        long lFechaPro;
        Date fecha = null;
        iSecId = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "sec_id" ) ).getValue().toString() );
        sFechaPro = ( (UIParameter)event.getComponent().findComponent( "sec_fprorroga" ) ).getValue().toString();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat( "yyyy-MM-dd" );
        if ( sFechaPro.equals( "" ) ) {
            this.setFechaProrroga( new Date() );
        }else{
        fecha = formatoDelTexto.parse( sFechaPro );
        this.setFechaProrroga( fecha );
        }
        this.setsObsProrroga( "" );
        this.setSecId( iSecId );
        this.setOncomplete( "Richfaces.showModalPanel('mpEdicion');" );
    }

    public void guardarProrroga( ActionEvent event ) throws Exception {

        HSSeccionCLDAO secProClDAO = (HSSeccionCLDAO)ServiceFinder.findBean( "SpringHibernateDaoCLSeccion" );
        this.setOncomplete( "" );
        if ( this.getsObsProrroga().trim().length() > 0 ) {
            secProClDAO.actualizarFechaProrroga( this.getFechaProrroga(), this.getsObsProrroga(), this.getSecId() );
            this.setOncomplete( "Richfaces.hideModalPanel('mpEdicion');" );
        } else {
            this.setOncomplete( "javascript:alert('Ingrese la observacion por el cambio de fecha')" );
        }

    }
}
