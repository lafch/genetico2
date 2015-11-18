package net.uch.cursoLibre.managedBeans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIParameter;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import net.uch.cursoLibre.hibernateSpringDao.HSArbolAcademicoClDao;
import net.uch.mapping.ClArbolAcademico;
import net.uch.mapping.TbCatalogo;
import net.uch.tablaSistema.managedBeans.bUsuario;
import net.uch.util.CommonDAO;
import net.uch.util.CommonWeb;

public class bArea {

    private int are_id;
    private String are_codigo;
    private String are_descripcion;
    private String are_activo;
    private String b_descripcion = "";
    private List<bArea> areas = new ArrayList<bArea>();
    private boolean ver;
    private boolean editar;
    private int i_are_id;
    private String i_are_codigo;
    private String i_are_descripcion;
    private String oncomplete;
    private String message;
    private SelectItem[] cboInstitucion;
    private String iInstitucionId;
    private String edit_disable;
    private String img_oculto;
    private String title_img_oculto;
    
    private int usuId;

    public int getUsuId() {
        return usuId;
    }

    public void setUsuId( int usuId ) {
        this.usuId = usuId;
    }

    public void ocultarArbol( ActionEvent event ) {
        int iIdAreaOcul;
        int iVisible;
        ClArbolAcademico arbArea;
        HSArbolAcademicoClDao arbDAO = CommonDAO.getClArbolAcademicoDAO();
        iIdAreaOcul = CommonWeb.parseObjToInt( CommonWeb.getParamFromUIParameterUI( event, "id_ocultar_arbol") );
        try{
            if( iIdAreaOcul > 0 ){
                arbArea = arbDAO.buscarArbolPorId( iIdAreaOcul );
                if(arbArea.getArbVisibleArbol().intValue() == 1){
                    iVisible =  0;
                    message = "alert('Se ocultó correctamente.');";
                }else{
                    iVisible = 1;
                    message = "alert('Ahora será visible en el árbol.');";
                }
                arbArea.setArbVisibleArbol( iVisible );
                arbDAO.actualizarArea( arbArea );
                this.Seleccionar();
            }
        }catch( Exception ex ){
            ex.printStackTrace();
            message = "alert('Error...');";
            return;
        }
    }

    public String getImg_oculto() {
        return img_oculto;
    }

    public void setImg_oculto( String img_oculto ) {
        this.img_oculto = img_oculto;
    }

    public String getTitle_img_oculto() {
        return title_img_oculto;
    }

    public void setTitle_img_oculto( String title_img_oculto ) {
        this.title_img_oculto = title_img_oculto;
    }

    // CONSTRUCTORES
    public bArea() {
        try{
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpSession session = (HttpSession) context.getSession( false );
            bUsuario usu = (bUsuario)session.getAttribute( "usuario" );
            usuId = usu.getId_usu();
        }catch( Exception ex ){
            usuId = 0;
            ex.printStackTrace();
        }
    }

    public bArea( int a ) {
    }

    // METODOS
    public void Nuevo( ActionEvent event ) throws Exception {
        this.setiInstitucionId("0");
        this.setOncomplete( "" );
        this.setI_are_id( 0 );
        this.setI_are_codigo( "" );
        this.setI_are_descripcion( "" );
        this.setEdit_disable("false");
        this.setOncomplete( "Richfaces.showModalPanel('mp1')" );
    }

    public String Seleccionar() throws Exception {
        ClArbolAcademico arbArea;
        HSArbolAcademicoClDao arbAcadClDAO = CommonDAO.getClArbolAcademicoDAO();
        List lstAreas = arbAcadClDAO.seleccionarArea( this.getB_descripcion().trim() );
        this.areas = new ArrayList<bArea>();
        for ( int i = 0; i < lstAreas.size(); i++ ) {
            arbArea = (ClArbolAcademico) lstAreas.get( i );
            bArea area = new bArea( 0 );
            area.setAre_id( arbArea.getArbId() );
            area.setAre_codigo( arbArea.getArbCodigo() );
            area.setAre_descripcion( arbArea.getArbDescripcion() );
            area.setAre_activo( arbArea.getArbActivo() );

            area.setVer( true );
            area.setEditar( false );
            if( arbArea.getArbVisibleArbol().intValue() == 1 ){
                area.setImg_oculto(  "activar.png" );
                area.setTitle_img_oculto( "Ocultar del árbol" );
            }else{
                area.setImg_oculto( "desactivar.png" );
                area.setTitle_img_oculto( "Mostrar en el árbol" );
            }
            areas.add( area );

        }
        return "";

    }

    public void ActualizarArea( ActionEvent event ) throws Exception {
        this.setOncomplete( "" );
        HSArbolAcademicoClDao arbAcadClDAO = CommonDAO.getClArbolAcademicoDAO();
        int id_update = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "id_update" )).getValue().toString() );
        ClArbolAcademico arbAcadArea = arbAcadClDAO.buscarArbolArea( id_update );
        this.setEdit_disable("true");
        this.setiInstitucionId( arbAcadArea.getArbInstitucion() );
        this.setI_are_id( arbAcadArea.getArbId() );
        this.setI_are_codigo( arbAcadArea.getArbCodigo() );
        this.setI_are_descripcion( arbAcadArea.getArbDescripcion() );
        this.setOncomplete( "Richfaces.showModalPanel('mp1')" );

    }

    public void EliminarArea( ActionEvent event ) throws Exception {
        this.setMessage( "" );
        /*
         * this.setMessage("javascript:return (confirm('¿Esta realmente seguro
         * de Eliminar?'));");
         */
        int id_delete = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "id_delete" )).getValue().toString() );
        HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();

        //verificar si el area tiene modulos creados
        List lista = dao.seleccionarModulos( id_delete, "" );
        if ( !lista.isEmpty() ) {
            this.setMessage( "javascript:alert('El area no puede ser eliminada porque contiene modulos!.')" );
        } else { // si no tiene elimnar
            this.setMessage( "javascript:alert('El area se elimino correctamente')" );
            dao.eliminarArbol( id_delete );

        }
    }

    public void GuardarArea( ActionEvent event ) throws Exception {
        this.setOncomplete( "" );
        String nuevo_cod = "";
        HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();
        if ( this.getI_are_codigo().trim().length() > 0 && this.getI_are_descripcion().trim().length() > 0 
                && !"0".equals(this.getiInstitucionId())) {
            if ( this.getI_are_id() > 0 ) { // GUARDAR AREA ACTUALIZADA

                ClArbolAcademico area_arb = new ClArbolAcademico();
                area_arb.setArbId( this.getI_are_id() );
                area_arb.setArbCodigo( this.getI_are_codigo().trim().toUpperCase() );
                area_arb.setArbDescripcion( this.getI_are_descripcion().trim().toUpperCase() );
                area_arb.setArbActivo( "1" );


                dao.actualizarArea( area_arb );
                this.setOncomplete( "javascript:alert('Actualización Satisfactoria.');Richfaces.hideModalPanel('mp1')" );

            } else { //GUARDAR AREA NUEVA

//                List lstArea = dao.obtenerUltimaArea();
//                for ( int i = 0; i < 1; i++ ) {
//                    String cod = ((ClArbolAcademico) lstArea.get( i )).getArbOpcion().substring( 2, 4 );
//                    int num_cod = Integer.parseInt( cod );
//                    num_cod++;
//                    nuevo_cod = "PA" + num_cod;
//                }
                ClArbolAcademico arb_area = new ClArbolAcademico();
                arb_area.setArbId( this.getI_are_id() );
                arb_area.setArbOpcion( nuevo_cod );
                arb_area.setArbCodigo( this.getI_are_codigo().trim().toUpperCase() );
                arb_area.setArbDescripcion( this.getI_are_descripcion().trim().toUpperCase() );
                arb_area.setArbTipo( "069005" );
                arb_area.setArbNivel( 1 );
                arb_area.setArbActivo( "1" );
                arb_area.setArbIdPadre( 0 );
                arb_area.setArbFlag( "0" );
                arb_area.setArbInstitucion(this.getiInstitucionId());
                arb_area.setArbVisible( 1 );

                dao.insertarArea( arb_area );
                this.setOncomplete( "javascript:alert('Registro Satisfactorio.');Richfaces.hideModalPanel('mp1')" );
            }
        } else {
            this.setOncomplete( "javascript:alert('Debe ingresar todos los Datos.')" );
        }

    }

    // GETTERS AND SETTERS

    public SelectItem[] getCboInstitucion () {
        try {
            
            List lstInstituciones = CommonDAO.getTbCatalogoDAO().seleccionarCatalogo( "078" );
            if ( lstInstituciones.size() > 0 ) {
                cboInstitucion = new SelectItem[ lstInstituciones.size() + 1 ];
                cboInstitucion[0] = new SelectItem( 0, "[Seleccione]" );
                for ( int i = 0; i < lstInstituciones.size(); i++ ) {
                    cboInstitucion[i + 1] = new SelectItem(((TbCatalogo) lstInstituciones.get(i)).getCatCodigoGrupo() + ((TbCatalogo) lstInstituciones.get(i)).getCatCodigoElemento(), ((TbCatalogo) lstInstituciones.get(i)).getCatDescripcionElemento());
                }
            } else {
                cboInstitucion = new SelectItem[ 1 ];
                cboInstitucion[0] = new SelectItem( 0, "[Seleccione]" );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return cboInstitucion;
    }

    public void setCboInstitucion ( SelectItem[] cboInstitucion ) {
        this.cboInstitucion = cboInstitucion;
    }
    
    public String getiInstitucionId () {
        return iInstitucionId;
    }

    public void setiInstitucionId ( String iAreaId ) {
        this.iInstitucionId = iAreaId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage( String message ) {
        this.message = message;
    }
    
    public int getAre_id() {
        return are_id;
    }

    public void setAre_id( int are_id ) {
        this.are_id = are_id;
    }

    public String getAre_codigo() {
        return are_codigo;
    }

    public void setAre_codigo( String are_codigo ) {
        this.are_codigo = are_codigo;
    }

    public String getAre_descripcion() {
        return are_descripcion;
    }

    public void setAre_descripcion( String are_descripcion ) {
        this.are_descripcion = are_descripcion;
    }

    public String getAre_activo() {
        return are_activo;
    }

    public void setAre_activo( String are_activo ) {
        this.are_activo = are_activo;
    }

    public String getB_descripcion() {
        return b_descripcion;
    }

    public void setB_descripcion( String b_descripcion ) {
        this.b_descripcion = b_descripcion;
    }

    public List<bArea> getAreas() {
        return areas;
    }

    public void setAreas( List<bArea> areas ) {
        this.areas = areas;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete( String oncomplete ) {
        this.oncomplete = oncomplete;
    }

    public boolean isVer() {
        return ver;
    }

    public void setVer( boolean ver ) {
        this.ver = ver;
    }

    public boolean isEditar() {
        return editar;
    }

    public void setEditar( boolean editar ) {
        this.editar = editar;
    }

    public int getI_are_id() {
        return i_are_id;
    }

    public void setI_are_id( int i_are_id ) {
        this.i_are_id = i_are_id;
    }

    public String getI_are_codigo() {
        return i_are_codigo;
    }

    public void setI_are_codigo( String i_are_codigo ) {
        this.i_are_codigo = i_are_codigo;
    }

    public String getI_are_descripcion() {
        return i_are_descripcion;
    }

    public void setI_are_descripcion( String i_are_descripcion ) {
        this.i_are_descripcion = i_are_descripcion;
    }

    public String getEdit_disable () {
        return edit_disable;
    }

    public void setEdit_disable ( String edit_disable ) {
        this.edit_disable = edit_disable;
    }
    
    
}
