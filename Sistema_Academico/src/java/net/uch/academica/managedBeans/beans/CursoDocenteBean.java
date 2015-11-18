/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.academica.managedBeans.beans;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.uch.academica.hibernateSpringDao.HSCursoDAO;
import net.uch.academica.hibernateSpringDao.HSDocenteDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcCurso;
import net.uch.mapping.AcDocente;
import net.uch.mapping.AcDocenteCurso;
import net.uch.mapping.AcHorarioDispDocente;
import net.uch.mapping.TbCatalogo;
import net.uch.metodo.local.MetodosAca;
import net.uch.tablaSistema.hibernateSpringDao.HSAulaDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.util.CommonDAO;

/**
 *
 * @author LUIS
 */
public class CursoDocenteBean extends AcDocenteCurso {
    private boolean blSeleccionado;

    public AcDocente getAcDocente() {
        return super.getAcDocente();
    }
    public AcCurso getAcCurso() {
        return super.getAcCurso();
    }
    private int posicion;
    private int doc_id;
    private int esp_id;
    private SelectItem[] cursos;
    
    private SelectItem[] cmbAsignacion;
    private List<String> lstCurso;
 

    private String editable = "false";
    private String view = "true";
    private boolean editable_bool = false;
    private boolean view_bool = true;
    private boolean visible = false;
    private boolean edit_active = false;
    private List<String> lstCursoDoc;
    //Temporales
    public int auxDoc;
    public int auxCurso;
    public String axuAsignacion;
    
    private String v_curso;
    private String v_asignacion;
    

    private String sOncomplete;
    
    private int ParametroCurso;
    private String ParametroAsignacion;

    public int getParametroCurso() {
        return ParametroCurso;
    }

    public void setParametroCurso(int ParametroCurso) {
        this.ParametroCurso = ParametroCurso;
    }

    public String getParametroAsignacion() {
        return ParametroAsignacion;
    }

    public void setParametroAsignacion(String ParametroAsignacion) {
        this.ParametroAsignacion = ParametroAsignacion;
    }
    
    

    public String getV_curso() {
        return v_curso;
    }

    public void setV_curso(String v_curso) {
        this.v_curso = v_curso;
    }

    public String getV_asignacion() {
        return v_asignacion;
    }

    public void setV_asignacion(String v_asignacion) {
        this.v_asignacion = v_asignacion;
    }

    public List<String> getLstCurso() {
        return lstCurso;
    }

    public void setLstCurso( List<String> lstCurso ) {
        this.lstCurso = lstCurso;
    }

    public CursoDocenteBean() {
    }

    public CursoDocenteBean( Integer curDocId ) {
        this.setDoc_id( curDocId );
        this.setEstadoAsignacion("000000" );
        this.setAcCurso(new AcCurso() );
        this.setAcDocente( new AcDocente() );
    }

    public CursoDocenteBean( AcDocenteCurso cursosDocente ) {
        this.setDoc_id(cursosDocente.getAcDocente().getId());
        this.setCurdoc_id(cursosDocente.getCurdoc_id());
        this.setAcDocente(cursosDocente.getAcDocente());
        this.setAcCurso(cursosDocente.getAcCurso());
        this.setEstadoAsignacion(cursosDocente.getEstadoAsignacion() );
        this.setV_asignacion(this.getV_asignacion());
        this.setActivo(cursosDocente.getActivo() );
        this.setEsp_id(cursosDocente.getAcCurso().getEsp().getId());
    }
    
    public AcDocenteCurso getCursoDocenteBean() {
        AcDocenteCurso horaria = new AcDocenteCurso();
        horaria.setCurdoc_id(this.getCurdoc_id());
        horaria.setAcDocente( this.getAcDocente() );
        horaria.setAcCurso(this.getAcCurso());
        horaria.setActivo(this.getActivo());
        horaria.setFechaCreacion(new Timestamp( new Date().getTime()));
        horaria.setEstadoAsignacion(this.getV_asignacion());
        return horaria;
    }
    

    
    public void actualizarDatos( int docId ) {
        HSDocenteDAO daoD = (HSDocenteDAO) ServiceFinder.findBean( "SpringHibernateDaoDocente" );
        HSCursoDAO daoA = (HSCursoDAO) ServiceFinder.findBean( "SpringHibernateDaoCurso" );
        HSCatalogoDAO daoC = (HSCatalogoDAO) ServiceFinder.findBean( "SpringHibernateDaoCatalogo" );

        this.setAcDocente(daoD.seleccionarDocente( docId ));
        this.setAcCurso(daoA.seleccionarCursoID(this.getAcCurso().getId()) );
        this.setEstadoAsignacion(daoC.seleccionarDescripcion(this.getV_asignacion()));
        this.setActivo(1);
        this.setFechaCreacion(new Timestamp( new Date().getTime()));
    }
    
    

    public AcDocenteCurso getAcDocenteCurso() {
        AcDocenteCurso docenteCurso = new AcDocenteCurso();

        return docenteCurso;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion( int posicion ) {
        this.posicion = posicion;
    }

    public int getDoc_id() {
        return doc_id;
    }

    public void setDoc_id( int doc_id ) {
        this.doc_id = doc_id;
    }

    public int getEsp_id() {
        return esp_id;
    }

    public void setEsp_id(int esp_id) {
        this.esp_id = esp_id;
    }

    public SelectItem[] getCursos() {
        if ( cursos == null ) {
            try {
                HSCursoDAO dao = (HSCursoDAO)ServiceFinder.findBean( "SpringHibernateDaoCurso" );
                List<AcCurso> lstCursos = dao.seleccionarCurso(this.getEsp_id());
                cursos = new SelectItem[ lstCursos.size() + 1 ];
                for ( int i = 0; i < lstCursos.size(); i++ ) {
                    cursos[i + 1] = new SelectItem( lstCursos.get( i ).getId(), lstCursos.get( i ).getCurNombre());
                }
            } catch ( Exception e ) {
                System.err.println( "Error al cargar los cursos " + e.getMessage() );
                cursos = new SelectItem[ 1 ];
            } finally {
                cursos[0] = new SelectItem( 0, "[Seleccione]" );
            }
        }
        return cursos;
    }

    public void setCursos( SelectItem[] cursos ) {
        this.cursos = cursos;
    }

    public SelectItem[] getCmbAsignacion() {
        if ( cmbAsignacion == null ) {
            try {
                HSCatalogoDAO dao = (HSCatalogoDAO)ServiceFinder.findBean( "SpringHibernateDaoCatalogo" );
                List<TbCatalogo> lstCatAsignacion = dao.seleccionarCatalogo( "097" );
                cmbAsignacion = new SelectItem[ lstCatAsignacion.size() + 1 ];
                cmbAsignacion[0] = new SelectItem( "000000", "[Seleccione]" );
                for ( int i = 0; i < lstCatAsignacion.size(); i++ ) {
                    cmbAsignacion[i + 1] = new SelectItem( lstCatAsignacion.get( i ).getCatCodigoGrupo() + lstCatAsignacion.get( i ).getCatCodigoElemento(), lstCatAsignacion.get( i ).getCatDescripcionElemento() );
                }
            } catch ( Exception e ) {
                System.err.println( "Error al cargar los asignacion " + e.getMessage() );
                cmbAsignacion = new SelectItem[ 1 ];
                cmbAsignacion[0] = new SelectItem( "000000", "[Seleccione]" );
            }
        }
        return cmbAsignacion;
    }

    public void setCmbAsignacion( SelectItem[] cmbAsignacion ) {
        this.cmbAsignacion = cmbAsignacion;
    }


    public String getEditable() {
        return editable;
    }

    public void setEditable( String editable ) {
        this.editable = editable;
    }

    public boolean isEditable_bool() {
        return editable_bool;
    }

    public void setEditable_bool( boolean editable_bool ) {
        this.editable_bool = editable_bool;
    }
    
    

    public String getView() {
        return view;
    }

    public void setView( String view ) {
        this.view = view;
    }

    public boolean isView_bool() {
        return view_bool;
    }

    public void setView_bool( boolean view_bool ) {
        this.view_bool = view_bool;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible( boolean visible ) {
        this.visible = visible;
    }

    public boolean isEdit_active() {
        return edit_active;
    }

    public void setEdit_active( boolean edit_active ) {
        this.edit_active = edit_active;
    }
    
    public int getAuxDoc() {
        return auxDoc;
    }

    public void setAuxDoc( int auxDoc ) {
        this.auxDoc = auxDoc;
    }

    public int getAuxCurso() {
        return auxCurso;
    }

    public void setAuxCurso(int auxCurso) {
        this.auxCurso = auxCurso;
    }

    public String getAxuAsignacion() {
        return axuAsignacion;
    }

    public void setAxuAsignacion(String axuAsignacion) {
        this.axuAsignacion = axuAsignacion;
    }
    
    public void edit( ActionEvent event ) {
        HSCatalogoDAO daoC = (HSCatalogoDAO) ServiceFinder.findBean( "SpringHibernateDaoCatalogo" );
        this.setAuxDoc( this.getAcDocente().getId().intValue() );
        this.setAuxCurso( this.getAcCurso().getId().intValue() );
        this.setAxuAsignacion(this.getEstadoAsignacion());
        this.setView( "false" );
        this.setEditable( "true" );
        this.setView_bool( false );
        this.setEditable_bool( true );
        this.setVisible( true );
        this.setEdit_active( true );
    }

    public void aceptar( ActionEvent event ) {
        HSCatalogoDAO daoC = (HSCatalogoDAO) ServiceFinder.findBean( "SpringHibernateDaoCatalogo" );
        HSCursoDAO daoCurso = (HSCursoDAO) ServiceFinder.findBean( "SpringHibernateDaoCurso" );
        String sMsg;
        sMsg = this.esValido();
        if ( sMsg.isEmpty() ) {
            this.setEstadoAsignacion(daoC.seleccionarDescripcion( this.getV_asignacion()));
            this.setAcCurso(daoCurso.seleccionarCursoID(this.getAcCurso().getId()));
            this.setView( "true" );
            this.setEditable( "false" );
            this.setView_bool( true );
            this.setEditable_bool( false );
            this.setVisible( false );
            this.setEdit_active( false );
            sOncomplete = "";
        } else {
            sOncomplete = "alert('" + sMsg + "')";
        }
    }

    public void cancelar( ActionEvent event ) {

        this.getAcDocente().setId( getAuxDoc());
        this.setEstadoAsignacion(getAxuAsignacion());
        this.setView( "true" );
        this.setEditable( "false" );
        this.setView_bool( true );
        this.setEditable_bool( false );
        this.setVisible( false );
        this.setEdit_active( false );
    }


    public String esValido() {
        String sMsg = "";
        if ( getDoc_id() == 0) {
            sMsg = "Seleccionar Docente";
        } 
        return sMsg;
    }

    public boolean isSeleccionado() {
        return this.blSeleccionado;
    }

    public void setSeleccionado( boolean blSeleccionado ) {
        this.blSeleccionado = blSeleccionado;
    }

    public String getOncomplete() {
        return sOncomplete;
    }

    public void setOncomplete( String sOncomplete ) {
        this.sOncomplete = sOncomplete;
    }

}
