package net.uch.cursoLibre.managedBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.component.UIParameter;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import net.uch.cursoLibre.hibernateSpringDao.HSArbolAcademicoClDao;
import net.uch.mapping.ClArbolAcademico;
import net.uch.tablaSistema.managedBeans.bUsuario;
import net.uch.util.CommonDAO;
import net.uch.util.CommonWeb;
import net.uch.utilAdministrativo.MetodosAdm;

public class bModulo {

    private int mod_id;
    private String mod_codigo;
    private String mod_descripcion;
    private Date mod_creacion;
    private String mod_activo;
    private int are_id;
    private String are_descripcion;
    private SelectItem[] asCentros;
    private SelectItem[] b_areas;
    private int b_are_id;
    private String m_sCentroId;
    private String b_descripcion = "";
    private List<bModulo> modulos = new ArrayList<bModulo>();
    private List<bModulo> cursos = new ArrayList<bModulo>();
    private String oncomplete;
    private int i_mod_id;
    private String i_mod_codigo;
    private String i_mod_descripcion;
    private Date i_mod_creacion;
    private SelectItem[] i_areas;
    private int i_are_id;
    //curso
    private int cur_id;
    private String cur_codigo;
    private String cur_nombre;
    private String cur_activo;
    private int cur_id_edit;
    private String cur_codigo_edit;
    private String cur_nombre_edit;
    private boolean verDetalle;
    private String imagenDetalle;
    private String tituloDetalle;
    private int i_cur_id;
    private String i_cur_codigo;
    private String i_cur_nombre;
    private String i_cur_activo;
    private List<bModulo> i_cursos = new ArrayList<bModulo>();
    private String message;
    private boolean verAgregar = false;
    private String imagenAgregar = "/Imagenes/actions/down.png";
    private String tituloAgregar = "Ingresar Cursos";
    private int e_cur_id;
    private String e_cur_codigo;
    private String e_cur_nombre;
    private boolean cursoVer;
    private boolean cursoEditar;
    int cont1 = 0;
    private List<Integer> ids_cur_delete = new ArrayList<Integer>();
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
        int iIdModOcul;
        int iVisible;
        ClArbolAcademico arbMod;
        HSArbolAcademicoClDao arbDAO = CommonDAO.getClArbolAcademicoDAO();
        iIdModOcul = CommonWeb.parseObjToInt( CommonWeb.getParamFromUIParameterUI( event, "id_ocultar_arbol" ) );
        try {
            if ( iIdModOcul > 0 ) {
                arbMod = arbDAO.buscarArbolPorId( iIdModOcul );
                if ( arbMod.getArbVisibleArbol().intValue() == 1 ) {
                    iVisible = 0;
                    message = "alert('Se ocultó correctamente.');";
                } else {
                    iVisible = 1;
                    message = "alert('Ahora será visible en el árbol.');";
                }
                arbMod.setArbVisibleArbol( iVisible );
                arbDAO.actualizarModulo( arbMod );
                this.Seleccionar();
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
            message = "alert('Error...');";
            return;
        }
    }

    public void ocultarArbolCurso( ActionEvent event ) {
        int iIdCurOcul;
        int iVisible;
        ClArbolAcademico arbCur;
        HSArbolAcademicoClDao arbDAO = CommonDAO.getClArbolAcademicoDAO();
        iIdCurOcul = CommonWeb.parseObjToInt( CommonWeb.getParamFromUIParameterUI( event, "id_ocultar_arbol_cur" ) );
        try {
            if ( iIdCurOcul > 0 ) {
                arbCur = arbDAO.buscarArbolPorId( iIdCurOcul );
                if ( arbCur.getArbVisibleArbol().intValue() == 1 ) {
                    iVisible = 0;
                    message = "alert('Se ocultó correctamente.');";
                } else {
                    iVisible = 1;
                    message = "alert('Ahora será visible en el árbol.');";
                }
                arbCur.setArbVisibleArbol( iVisible );
                arbDAO.actualizarCurso( arbCur );
                this.Seleccionar();
            }
        } catch ( Exception ex ) {
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

    //CONSTRUCTORES
    public bModulo() {
        try {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpSession session = (HttpSession) context.getSession( false );
            bUsuario usu = (bUsuario) session.getAttribute( "usuario" );
            usuId = usu.getId_usu();
        } catch ( Exception ex ) {
            usuId = 0;
            ex.printStackTrace();
        }
    }

    public bModulo( int p ) {
    }

    //METODOS
    public String Seleccionar() throws Exception {
        int iSizeCursos;
        int iSizeMod;
        String sDesc;
        bModulo mod;
        ClArbolAcademico arbCur;
        ClArbolAcademico arbMod;
        List lstModulos;
        List<ClArbolAcademico> lstAreaDesc;
        List<ClArbolAcademico> lstCursos;
        HSArbolAcademicoClDao arbAcadClDAO;

        arbAcadClDAO = CommonDAO.getClArbolAcademicoDAO();

        this.setModulos( new ArrayList<bModulo>() );
        lstModulos = arbAcadClDAO.seleccionarModulos( this.getB_are_id(), this.getB_descripcion() );
        if ( lstModulos != null && !lstModulos.isEmpty() ) {
            iSizeMod = lstModulos.size();

            for ( int i = 0; i < iSizeMod; i++ ) {
                arbMod = (ClArbolAcademico) lstModulos.get( i );
                mod = new bModulo( i );
                mod.setMod_id( arbMod.getArbId() );
                mod.setMod_codigo( arbMod.getArbCodigo() );
                mod.setMod_descripcion( arbMod.getArbDescripcion() );
                mod.setMod_activo( arbMod.getArbActivo() );
                mod.setAre_id( arbMod.getArbIdPadre() );

                lstAreaDesc = arbAcadClDAO.obtenerDescripcionArea( this.getB_are_id() );
                sDesc = lstAreaDesc.isEmpty() ? "" : lstAreaDesc.get( 0 ).getArbDescripcion();
                mod.setAre_descripcion( sDesc );

                lstCursos = arbAcadClDAO.seleccionarCursos( mod.getMod_id() );
                iSizeCursos = lstCursos.size();
                for ( int j = 0; j < iSizeCursos; j++ ) {
                    arbCur = lstCursos.get( j );
                    bModulo cur = new bModulo( j );
                    cur.setCur_id( lstCursos.get( j ).getArbId() );
                    cur.setCur_codigo( arbCur.getArbCodigo() );
                    cur.setCur_nombre( arbCur.getArbDescripcion() );
                    cur.setCur_activo( arbCur.getArbActivo() );

                    cur.setCur_id_edit( 0 );
                    cur.setCur_codigo_edit( "" );
                    cur.setCur_nombre_edit( "" );
                    if ( arbCur.getArbVisibleArbol().intValue() == 1 ) {
                        cur.setImg_oculto( "activar.png" );
                        cur.setTitle_img_oculto( "Ocultar del árbol" );
                    } else {
                        cur.setImg_oculto( "desactivar.png" );
                        cur.setTitle_img_oculto( "Mostrar en el árbol" );
                    }
                    cur.setCursoVer( true );
                    cur.setCursoEditar( false );

                    mod.getCursos().add( cur );
                }

                mod.setVerDetalle( false );
                if ( arbMod.getArbVisibleArbol().intValue() == 1 ) {
                    mod.setImg_oculto( "activar.png" );
                    mod.setTitle_img_oculto( "Ocultar del árbol" );
                } else {
                    mod.setImg_oculto( "desactivar.png" );
                    mod.setTitle_img_oculto( "Mostrar en el árbol" );
                }
                mod.setImagenDetalle( "/Imagenes/actions/down.png" );
                mod.setTituloDetalle( "Ver Cursos" );

                this.getModulos().add( mod );
            }
        }
        return "";
    }

    public void EditarDetalle( ActionEvent event ) throws Exception {
        int iSizeModulos;
        int iSizeCursos;
        int iIdDetalleUpdate = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "id_detalle_update" )).getValue().toString() );
        int iFlag = 0;
        List<bModulo> lstCursos;
        List<bModulo> lstModulos;

        lstModulos = this.getModulos();
        iSizeModulos = lstModulos.size();
        for ( int i = 0; i < iSizeModulos; i++ ) {
            lstCursos = this.getModulos().get( i ).getCursos();
            iSizeCursos = lstCursos.size();
            for ( int j = 0; j < iSizeCursos; j++ ) {
                if ( lstCursos.get( j ).getCur_id() == iIdDetalleUpdate ) {
                    lstCursos.get( j ).setCur_id_edit( lstCursos.get( j ).getCur_id() );
                    lstCursos.get( j ).setCur_codigo_edit( lstCursos.get( j ).getCur_codigo() );
                    lstCursos.get( j ).setCur_nombre_edit( lstCursos.get( j ).getCur_nombre() );
                    lstCursos.get( j ).setCursoVer( false );
                    lstCursos.get( j ).setCursoEditar( true );

                    j = iSizeCursos;
                    iFlag++;
                }
            }
            if ( iFlag > 0 ) {
                i = iSizeModulos;
            }
        }
    }

    public void GuardarDetalle( ActionEvent event ) throws Exception {
        int iIdDetalleUpdate;
        int iFlag;
        int iSizeCur;
        int iSizeMod;
        ClArbolAcademico arbCurso;
        ClArbolAcademico arbMod;
        List<bModulo> lstCursos;
        List<bModulo> lstModulos;

        HSArbolAcademicoClDao arbAcadClDAO;

        arbAcadClDAO = CommonDAO.getClArbolAcademicoDAO();

        iIdDetalleUpdate = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "id_detalle_update" )).getValue().toString() );

        iFlag = 0;
        this.setMessage( "" );

        lstModulos = this.getModulos();
        iSizeMod = lstModulos.size();
        for ( int i = 0; i < lstModulos.size(); i++ ) {
            lstModulos = this.getModulos();
            lstCursos = lstModulos.get( i ).getCursos();
            iSizeCur = lstModulos.get( i ).getCursos().size();
            for ( int j = 0; j < iSizeCur; j++ ) {
                if ( lstCursos.get( j ).getCur_id() == iIdDetalleUpdate ) {
                    if ( lstCursos.get( j ).getCur_codigo_edit().trim().length() > 0 && lstCursos.get( j ).getCur_nombre_edit().trim().length() > 0 ) {
                        arbMod = new ClArbolAcademico();
                        arbMod.setArbIdPadre( lstModulos.get( i ).getMod_id() );

                        arbCurso = new ClArbolAcademico();
                        arbCurso.setArbId( lstCursos.get( j ).getCur_id() );
                        arbCurso.setArbCodigo( lstCursos.get( j ).getCur_codigo_edit().trim().toUpperCase() );
                        arbCurso.setArbDescripcion( lstCursos.get( j ).getCur_nombre_edit().trim().toUpperCase() );
                        arbCurso.setArbActivo( "1" );
                        arbCurso.setArbIdPadre( arbMod.getArbId() );
//                        arbCurso.setClArbolAcademico( arbMod );

                        arbAcadClDAO.actualizarCurso( arbCurso );

                        lstCursos.get( j ).setCur_id( lstCursos.get( j ).getCur_id_edit() );
                        lstCursos.get( j ).setCur_codigo( lstCursos.get( j ).getCur_codigo_edit().trim().toUpperCase() );
                        lstCursos.get( j ).setCur_nombre( lstCursos.get( j ).getCur_nombre_edit().trim().toUpperCase() );

                        lstCursos.get( j ).setCursoVer( true );
                        lstCursos.get( j ).setCursoEditar( false );
                    } else {
                        this.setMessage( "javascript:alert('Debe ingresar un codigo y un nombre para el curso.')" );
                    }

                    j = iSizeCur;
                    iFlag++;
                }
            }
            if ( iFlag > 0 ) {
                i = iSizeMod;
            }
        }
    }

    public void CancelarDetalle( ActionEvent event ) throws Exception {
        int iIdDetalleUpdate;
        int flag = 0;
        int iSizeCursos;
        int iSizeModulos;
        List<bModulo> lstCursos;
        List<bModulo> lstModulos;

        iIdDetalleUpdate = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "id_detalle_update" )).getValue().toString() );

        lstModulos = this.getModulos();
        iSizeModulos = lstModulos.size();
        for ( int i = 0; i < iSizeModulos; i++ ) {
            lstCursos = this.getModulos().get( i ).getCursos();
            iSizeCursos = lstCursos.size();
            for ( int j = 0; j < iSizeCursos; j++ ) {
                if ( lstCursos.get( j ).getCur_id() == iIdDetalleUpdate ) {
                    lstCursos.get( j ).setCursoVer( true );
                    lstCursos.get( j ).setCursoEditar( false );

                    j = iSizeCursos;
                    flag++;
                }
            }
            if ( flag > 0 ) {
                i = iSizeModulos;
            }
        }
    }

    public void EliminarDetalle( ActionEvent event ) throws Exception {
        int iIdDetalleDelete;
        this.setMessage( "" );

        iIdDetalleDelete = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "id_detalle_delete" )).getValue().toString() );
        HSArbolAcademicoClDao arbAcadClDAO = CommonDAO.getClArbolAcademicoDAO();

        if ( arbAcadClDAO.verificarEliminarCurso( iIdDetalleDelete ).size() > 0 ) {
            this.setMessage( "javascript:alert('El curso no se puede Eliminar debido a que posee Talleres.')" );
        } else {
            arbAcadClDAO.eliminarArbol( iIdDetalleDelete );
            this.setMessage( "javascript:alert('El curso fue eliminado correctamente')" );
            Seleccionar();
        }
    }

    public void MostrarCursos( ActionEvent event ) throws Exception {
        int iIdDetalle;
        int iSizeModulos;
        bModulo mod;
        List<bModulo> lstModulos;

        iIdDetalle = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "id_detalle" )).getValue().toString() );
        lstModulos = this.getModulos();
        iSizeModulos = lstModulos.size();
        for ( int i = 0; i < iSizeModulos; i++ ) {
            mod = lstModulos.get( i );
            if ( mod.getMod_id() == iIdDetalle ) {
                if ( mod.isVerDetalle() ) {
                    mod.setVerDetalle( false );
                    mod.setImagenDetalle( "/Imagenes/actions/down.png" );
                    mod.setTituloDetalle( "Ver Cursos" );
                } else {
                    mod.setVerDetalle( true );
                    mod.setImagenDetalle( "/Imagenes/actions/up.png" );
                    mod.setTituloDetalle( "Ocultar Cursos" );
                }
                i = iSizeModulos;
            }
        }
    }

    public void MostrarAgregarCursos( ActionEvent event ) throws Exception {
        if ( this.isVerAgregar() ) {
            this.setVerAgregar( false );
            this.setImagenAgregar( "/Imagenes/actions/down.png" );
            this.setTituloAgregar( "Ingresar Cursos" );

            this.setI_cur_id( 0 );
            this.setI_cur_codigo( "" );
            this.setI_cur_nombre( "" );
            this.setI_cur_activo( "1" );
            this.setI_cursos( new ArrayList<bModulo>() );
            this.setIds_cur_delete( new ArrayList<Integer>() );
            this.cont1 = 0;
        } else {
            this.setI_cur_id( 0 );
            this.setI_cur_codigo( "" );
            this.setI_cur_nombre( "" );
            this.setI_cur_activo( "1" );
            this.setI_cursos( new ArrayList<bModulo>() );
            this.setIds_cur_delete( new ArrayList<Integer>() );
            this.cont1 = 0;

            this.setVerAgregar( true );
            this.setImagenAgregar( "/Imagenes/actions/up.png" );
            this.setTituloAgregar( "No Ingresar Cursos" );
        }
    }

    public void AgregarCurso( ActionEvent event ) throws Exception {
        this.setMessage( "" );
        if ( this.getI_cur_codigo().trim().length() > 0 && this.getI_cur_nombre().trim().length() > 0 ) {
            bModulo curso = new bModulo( 0 );
            curso.setI_cur_id( cont1 );
            curso.setI_cur_codigo( this.getI_cur_codigo().trim().toUpperCase() );
            curso.setI_cur_nombre( this.getI_cur_nombre().trim().toUpperCase() );
            curso.setI_cur_activo( "1" );

            curso.setE_cur_id( 0 );
            curso.setE_cur_codigo( "" );
            curso.setE_cur_nombre( "" );
            curso.setCursoVer( true );
            curso.setCursoEditar( false );

            this.getI_cursos().add( curso );
            cont1--;

            this.setI_cur_codigo( "" );
            this.setI_cur_nombre( "" );
        } else {
            this.setMessage( "javascript:alert('Debe ingresar un codigo y un nombre para el curso.')" );
        }
    }

    public void EditarCurso( ActionEvent event ) throws Exception {
        int iIdCurUpdate;
        int iSizeCursos;
        List<bModulo> lstICursos;

        iIdCurUpdate = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "id_cur_update" )).getValue().toString() );
        lstICursos = this.getI_cursos();
        iSizeCursos = lstICursos.size();

        for ( int i = 0; i < iSizeCursos; i++ ) {
            if ( lstICursos.get( i ).getI_cur_id() == iIdCurUpdate ) {

                lstICursos.get( i ).setE_cur_id( lstICursos.get( i ).getI_cur_id() );
                lstICursos.get( i ).setE_cur_codigo( lstICursos.get( i ).getI_cur_codigo() );
                lstICursos.get( i ).setE_cur_nombre( lstICursos.get( i ).getI_cur_nombre() );

                lstICursos.get( i ).setCursoVer( false );
                lstICursos.get( i ).setCursoEditar( true );

                i = iSizeCursos;
            }
        }
    }

    public void CancelarCurso( ActionEvent event ) throws Exception {
        int iIdCurUpdate;
        int iSizeCursos;
        List<bModulo> lstICursos;

        iIdCurUpdate = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "id_cur_update" )).getValue().toString() );
        lstICursos = this.getI_cursos();
        iSizeCursos = lstICursos.size();

        for ( int i = 0; i < iSizeCursos; i++ ) {
            if ( lstICursos.get( i ).getI_cur_id() == iIdCurUpdate ) {
                lstICursos.get( i ).setCursoVer( true );
                lstICursos.get( i ).setCursoEditar( false );

                i = iSizeCursos;
            }
        }
    }

    public void GuardarCurso( ActionEvent event ) throws Exception {
        int iIdCurUpdate;
        int iSizeCursos;
        List<bModulo> lstICursos;

        this.setMessage( "" );

        iIdCurUpdate = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "id_cur_update" )).getValue().toString() );
        lstICursos = this.getI_cursos();
        iSizeCursos = lstICursos.size();

        for ( int i = 0; i < iSizeCursos; i++ ) {
            if ( lstICursos.get( i ).getI_cur_id() == iIdCurUpdate ) {
                if ( lstICursos.get( i ).getE_cur_codigo().trim().length() > 0 && lstICursos.get( i ).getE_cur_nombre().trim().length() > 0 ) {

                    lstICursos.get( i ).setI_cur_id( lstICursos.get( i ).getE_cur_id() );
                    lstICursos.get( i ).setI_cur_codigo( lstICursos.get( i ).getE_cur_codigo().trim().toUpperCase() );
                    lstICursos.get( i ).setI_cur_nombre( lstICursos.get( i ).getE_cur_nombre().trim().toUpperCase() );

                    lstICursos.get( i ).setCursoVer( true );
                    lstICursos.get( i ).setCursoEditar( false );
                } else {
                    this.setMessage( "javascript:alert('Debe ingresar un codigo y un nombre para el curso.')" );
                }
                i = iSizeCursos;
            }
        }
    }

    public void QuitarCurso( ActionEvent event ) throws Exception {
        int iIdCurDelete;
        int iSizeCursos;
        List<bModulo> lstICursos;

        iIdCurDelete = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "id_cur_delete" )).getValue().toString() );
        HSArbolAcademicoClDao arbAcadClDAO = CommonDAO.getClArbolAcademicoDAO();

        System.out.println( "id_delete" + iIdCurDelete );
        this.setMessage( "" );

        if ( iIdCurDelete > 0 && arbAcadClDAO.verificarEliminarCurso( iIdCurDelete ).size() > 0 ) {
            System.out.println( "cantidad de talleres que posee el curso: " + arbAcadClDAO.verificarEliminarCurso( iIdCurDelete ).size() );
            this.setMessage( "javascript:alert('El curso no se puede Eliminar debido a que posee Talleres.')" );
        } else {
            lstICursos = this.getI_cursos();
            iSizeCursos = lstICursos.size();
            for ( int i = 0; i < iSizeCursos; i++ ) {
                if ( lstICursos.get( i ).getI_cur_id() == iIdCurDelete ) {
                    if ( lstICursos.get( i ).getI_cur_id() > 0 ) {
                        this.getIds_cur_delete().add( lstICursos.get( i ).getI_cur_id() );
                    }

                    lstICursos.remove( i );
                    i = iSizeCursos;
                }
            }
        }
    }

    public void Nuevo( ActionEvent event ) throws Exception {
        this.setOncomplete( "" );
        this.setI_mod_id( 0 );
        this.setI_mod_codigo( "" );
        this.setI_mod_descripcion( "" );
        this.setI_mod_creacion( new Date() );
        this.setI_areas( getI_areas() );
        this.setI_are_id( 0 );
        this.setVerAgregar( false );
        this.setImagenAgregar( "/Imagenes/actions/down.png" );
        this.setTituloAgregar( "Ingresar Cursos" );
        this.setOncomplete( "Richfaces.showModalPanel('mp1')" );
    }

    public void ActualizarModulo( ActionEvent event ) throws Exception {
        int iIdUpdate;
        int iSizeCursos;
        bModulo cur;
        ClArbolAcademico arbMod;
        HSArbolAcademicoClDao arbAcadClDAO;
        List<ClArbolAcademico> lstArbCursos;

        this.setOncomplete( "" );
        iIdUpdate = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "id_update" )).getValue().toString() );
        arbAcadClDAO = CommonDAO.getClArbolAcademicoDAO();

        arbMod = arbAcadClDAO.buscarModulo( iIdUpdate );
        this.setI_mod_id( arbMod.getArbId() );
        this.setI_mod_codigo( arbMod.getArbCodigo() );
        this.setI_mod_descripcion( arbMod.getArbDescripcion() );
        /*
         * this.setI_mod_creacion(modulo.getModCreacion());
         */
        /*
         * this.setI_areas(getI_areas()); this.setI_areas(getB_areas());
         * this.setB_areas(getI_areas());
         */
        this.setI_areas( getB_areas() );
        this.setB_areas( getB_areas() );
        this.setI_are_id( getB_are_id() );

        lstArbCursos = arbAcadClDAO.seleccionarCursos( arbMod.getArbId() );
        if ( !lstArbCursos.isEmpty() ) {
            this.setI_cur_id( 0 );
            this.setI_cur_codigo( "" );
            this.setI_cur_nombre( "" );
            this.setI_cur_activo( "1" );
            this.cont1 = 0;

            this.setVerAgregar( true );
            this.setImagenAgregar( "/Imagenes/actions/up.png" );
            this.setTituloAgregar( "No Ingresar Cursos" );

            this.setI_cursos( new ArrayList<bModulo>() );
            this.setIds_cur_delete( new ArrayList<Integer>() );

            iSizeCursos = lstArbCursos.size();
            for ( int i = 0; i < iSizeCursos; i++ ) {
                cur = new bModulo( i );
                cur.setI_cur_id( lstArbCursos.get( i ).getArbId() );
                cur.setI_cur_codigo( lstArbCursos.get( i ).getArbCodigo() );
                cur.setI_cur_nombre( lstArbCursos.get( i ).getArbDescripcion() );
                cur.setI_cur_activo( lstArbCursos.get( i ).getArbActivo() );

                cur.setE_cur_id( 0 );
                cur.setE_cur_codigo( "" );
                cur.setE_cur_nombre( "" );
                cur.setCursoVer( true );
                cur.setCursoEditar( false );

                this.getI_cursos().add( cur );
            }
        } else {
            this.setVerAgregar( false );
            this.setImagenAgregar( "/Imagenes/actions/down.png" );
            this.setTituloAgregar( "Ingresar Cursos" );

            this.setI_cur_id( 0 );
            this.setI_cur_codigo( "" );
            this.setI_cur_nombre( "" );
            this.setI_cur_activo( "1" );
            this.setI_cursos( new ArrayList<bModulo>() );
            this.cont1 = 0;
        }
        this.setOncomplete( "Richfaces.showModalPanel('mp1')" );
    }

    public void GuardarModulo( ActionEvent event ) throws Exception {
        String sModCod = "";
        String sNuevoCod;
        String sCurOpc = "";
        String sCod, sCod2;
        String sOpcionArea;
        ClArbolAcademico arbModOpc;
        ClArbolAcademico arbMod;
        ClArbolAcademico modPadre;
        ClArbolAcademico arbCur;
        List<ClArbolAcademico> arbAreaOpc;
        HSArbolAcademicoClDao arbAcadClDAO;
        List lstUltCur;
        List lstUltMod;
        ClArbolAcademico areaPadre;

        this.setOncomplete( "" );

        arbAcadClDAO = CommonDAO.getClArbolAcademicoDAO();

        if ( this.getI_are_id() > 0 && this.getI_mod_codigo().trim().length() > 0 && this.getI_mod_descripcion().trim().length() > 0 ) {

            if ( this.getI_mod_id() > 0 ) { // PARA ACTUALIZAR MODULO
                arbMod = new ClArbolAcademico();
                arbMod.setArbId( this.getI_mod_id() );
                arbMod.setArbCodigo( this.getI_mod_codigo().trim().toUpperCase() );
                arbMod.setArbDescripcion( this.getI_mod_descripcion().trim().toUpperCase() );
                arbMod.setArbActivo( "1" );

                if ( this.getI_cursos().size() > 0 ) {// GESTION CURSO
                    for ( int i = 0; i < this.getI_cursos().size(); i++ ) {
                        if ( this.getI_cursos().get( i ).getI_cur_id() > 0 ) {// PARA ACTUALIZAR CURSO
                            arbCur = new ClArbolAcademico();
                            arbCur.setArbId( this.getI_cursos().get( i ).getI_cur_id() );
                            arbCur.setArbCodigo( this.getI_cursos().get( i ).getI_cur_codigo().trim().toUpperCase() );
                            arbCur.setArbDescripcion( this.getI_cursos().get( i ).getI_cur_nombre().trim().toUpperCase() );
                            arbCur.setArbActivo( "1" );

                            arbAcadClDAO.actualizarCurso( arbCur );
                        } else {// PARA AGREGAR CURSO
//                            lstUltCur = arbAcadClDAO.obtenerUltimoCurso( this.getI_mod_id() );
                            modPadre = arbAcadClDAO.buscarArbolPorId( this.getI_mod_id() );

//                            if ( !lstUltCur.isEmpty() ) {// SI YA TIENE CURSO CREADOS
//
//                                for ( int j = 0; j < 1; j++ ) {
//                                    sCod = ((ClArbolAcademico) lstUltCur.get( j )).getArbOpcion().substring( 0, 6 );
//                                    sCod2 = ((ClArbolAcademico) lstUltCur.get( j )).getArbOpcion().substring( 6, 8 );
//                                    int num_cod = Integer.parseInt( sCod2 );
//                                    num_cod++;
//                                    if ( num_cod < 10 ) {
//                                        sCurOpc = sCod + "0" + num_cod;
//                                    } else {
//                                        sCurOpc = sCod + num_cod;
//                                    }
//                                }
//                            } else {//SI NO TIENE NINGUN CURSO CREADO
//                                arbModOpc = arbAcadClDAO.buscarModulo( this.getI_mod_id() );
//                                String opcion_area = (arbModOpc.getArbOpcion());
//                                sCurOpc = opcion_area + "01";
//                            }

                            arbCur = new ClArbolAcademico();
                            arbCur.setArbId( this.getI_cur_id() );
                            arbCur.setArbOpcion( sCurOpc );
                            arbCur.setArbCodigo( this.getI_cursos().get( i ).getI_cur_codigo().trim().toUpperCase() );
                            arbCur.setArbDescripcion( this.getI_cursos().get( i ).getI_cur_nombre().trim().toUpperCase() );
                            arbCur.setArbTipo( "069004" );
                            arbCur.setArbNivel( 3 );
                            arbCur.setArbActivo( "1" );
                            arbCur.setArbIdPadre( this.getI_mod_id() );
                            arbCur.setArbFlag( "0" );
                            arbCur.setArbInstitucion( modPadre.getArbInstitucion() );
                            arbCur.setArbVisible( 1 );

                            arbAcadClDAO.insertarCurso( arbCur );
                        }
                    }
                }
                if ( this.getIds_cur_delete().size() > 0 ) {
                    for ( int i = 0; i
                            < this.getIds_cur_delete().size(); i++ ) {
                        arbAcadClDAO.eliminarArbol( this.getIds_cur_delete().get( i ) );
                    }
                }

                arbAcadClDAO.actualizarModulo( arbMod );
                this.setOncomplete( "javascript:alert('Actualización Satisfactoria.');Richfaces.hideModalPanel('mp1')" );

            } else { // AGREGAR NUEVO MODULO    
                lstUltMod = arbAcadClDAO.obtenerUltimoModulo( this.getI_are_id() );
                areaPadre = arbAcadClDAO.buscarArbolPorId( this.getI_are_id() );
                sNuevoCod = "";
//                if ( !lstUltMod.isEmpty() ) {// SI YA TIENE MODULOS CREADOS
//
//                    for ( int i = 0; i < 1; i++ ) {
//                        String cod = ((ClArbolAcademico) lstUltMod.get( i )).getArbOpcion().substring( 0, 4 );
//                        String cod2 = ((ClArbolAcademico) lstUltMod.get( i )).getArbOpcion().substring( 4, 6 );
//                        int num_cod = Integer.parseInt( cod2 );
//                        num_cod++;
//                        if ( num_cod < 10 ) {
//                            sNuevoCod = cod + "0" + num_cod;
//                        } else {
//                            sNuevoCod = cod + num_cod;
//                        }
//                    }
//                } else {//SI NO TIENE NINGUN MODULO CREADO
//                    arbAreaOpc = arbAcadClDAO.obtenerDescripcionArea( this.getI_are_id() );
//                    sOpcionArea = (arbAreaOpc.get( 0 ).getArbOpcion());
//                    sNuevoCod = sOpcionArea + "01";
//                }

                ClArbolAcademico modulo = new ClArbolAcademico();
                modulo.setArbId( this.getI_mod_id() );
                modulo.setArbOpcion( sNuevoCod );
                modulo.setArbCodigo( this.getI_mod_codigo().trim().toUpperCase() );
                modulo.setArbDescripcion( this.getI_mod_descripcion().trim().toUpperCase() );
                modulo.setArbTipo( "069001" );
                modulo.setArbNivel( 2 );
                modulo.setArbActivo( "1" );
                modulo.setArbIdPadre( this.getI_are_id() );
                modulo.setArbFlag( "0" );
                modulo.setArbInstitucion( areaPadre.getArbInstitucion() );
                modulo.setArbVisible( 1 );

                arbAcadClDAO.insertarModulo( modulo );


                if ( this.getI_cursos().size() > 0 ) {
                    List<ClArbolAcademico> lstArbCursos =
                            new ArrayList<ClArbolAcademico>();
                    for ( int i = 0; i < this.getI_cursos().size(); i++ ) {
                        ClArbolAcademico cur = new ClArbolAcademico();

                        //arbModOpc = arbAcadClDAO.buscarModulo( modulo.getArbId() ); //GENERAR CODIGO DE CURSOS NUEVOS
                        //String opcion_area = (arbModOpc.getArbOpcion());
//                        String opcion_area = modulo.getArbOpcion();
//                        if ( i < 10 ) {
//                            sCurOpc = opcion_area + "0" + (i + 1);
//                        } else {
//                            sCurOpc = opcion_area + (i + 1);
//                        }

                        cur.setArbOpcion( sCurOpc );
                        cur.setArbCodigo( this.getI_cursos().get( i ).getI_cur_codigo().trim().toUpperCase() );
                        cur.setArbDescripcion( this.getI_cursos().get( i ).getI_cur_nombre().trim().toUpperCase() );
                        cur.setArbActivo( "1" );
                        cur.setArbIdPadre( modulo.getArbId() );
                        cur.setArbTipo( "069004" );
                        cur.setArbNivel( 3 );
                        cur.setArbFlag( "0" );
                        cur.setArbInstitucion( modulo.getArbInstitucion() );
                        //cur.setClTallerAperturados( new LinkedHashSet<ClTallerAperturado>() );

                        lstArbCursos.add( cur );
                    }
                    arbAcadClDAO.insertarListaArbol( lstArbCursos );
                } else {
//                    modulo.setClCursoses( new LinkedHashSet<ClArbolAcademico>() );
                }

                this.setOncomplete( "javascript:alert('Registro Satisfactorio.');Richfaces.hideModalPanel('mp1')" );

            }
        } else {
            this.setOncomplete( "javascript:alert('Todos los campos son obligatorios.')" );
        }
    }

    public void EliminarModulo( ActionEvent event ) throws Exception {
        this.setMessage( "" );
        /*
         * this.setMessage("javascript:return (confirm('¿Esta realmente seguro
         * de Eliminar?'));");
         */

        int id_delete = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "id_delete" )).getValue().toString() );
        HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();

        List lista = dao.seleccionarCursos( id_delete );
        if ( !lista.isEmpty() ) {
            this.setMessage( "javascript:alert('El Modulo no puede ser eliminada porque contiene cursos!.')" );
        } else { // si no tiene elimnar
            this.setMessage( "javascript:alert('El Modulo se elimino correctamente')" );
            dao.eliminarModulo( id_delete );
        }
    }

    //GETTERS AND SETTERS
    public int getMod_id() {
        return mod_id;
    }

    public void setMod_id( int mod_id ) {
        this.mod_id = mod_id;
    }

    public String getMod_codigo() {
        return mod_codigo;
    }

    public void setMod_codigo( String mod_codigo ) {
        this.mod_codigo = mod_codigo;
    }

    public String getMod_descripcion() {
        return mod_descripcion;
    }

    public void setMod_descripcion( String mod_descripcion ) {
        this.mod_descripcion = mod_descripcion;
    }

    public Date getMod_creacion() {
        return mod_creacion;
    }

    public void setMod_creacion( Date mod_creacion ) {
        this.mod_creacion = mod_creacion;
    }

    public String getMod_activo() {
        return mod_activo;
    }

    public void setMod_activo( String mod_activo ) {
        this.mod_activo = mod_activo;
    }

    public int getAre_id() {
        return are_id;
    }

    public void setAre_id( int are_id ) {
        this.are_id = are_id;
    }

    public SelectItem[] getCentros() {
        MetodosAdm metodo = new MetodosAdm();
        try {
            asCentros = metodo.listarCentros( "[Seleccione]" );
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return asCentros;
    }

    public void setCentros( SelectItem[] asCentros ) {
        this.asCentros = asCentros;
    }

    public SelectItem[] getB_areas() {
        HSArbolAcademicoClDao clArbDAO;
        List<ClArbolAcademico> lstArbAreas;
        try {
            if ( m_sCentroId != null && !"0".equals( m_sCentroId ) ) {
                clArbDAO = CommonDAO.getClArbolAcademicoDAO();
                lstArbAreas = clArbDAO.AreasXInstitucion( m_sCentroId );
                if ( lstArbAreas.size() > 0 ) {
                    b_areas = new SelectItem[ lstArbAreas.size() + 1 ];
                    b_areas[0] = new SelectItem( 0, "[Seleccione]" );
                    for ( int i = 0; i < lstArbAreas.size(); i++ ) {
                        b_areas[i + 1] = new SelectItem( ((ClArbolAcademico) lstArbAreas.get( i )).getArbId(), ((ClArbolAcademico) lstArbAreas.get( i )).getArbDescripcion() );
                    }
                } else {
                    b_areas = new SelectItem[ 1 ];
                    b_areas[0] = new SelectItem( 0, "[Seleccione]" );
                }
            } else {
                b_areas = new SelectItem[ 1 ];
                b_areas[0] = new SelectItem( 0, "[Seleccione]" );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return b_areas;
    }

    public void setB_areas( SelectItem[] b_areas ) {
        this.b_areas = b_areas;
    }

    public int getB_are_id() {
        return b_are_id;
    }

    public void setB_are_id( int b_are_id ) {
        this.b_are_id = b_are_id;
    }

    public String getCentroId() {
        return m_sCentroId;
    }

    public void setCentroId( String sCentroId ) {
        this.m_sCentroId = sCentroId;
    }

    public String getB_descripcion() {
        return b_descripcion;
    }

    public void setB_descripcion( String b_descripcion ) {
        this.b_descripcion = b_descripcion;
    }

    public List<bModulo> getModulos() {
        return modulos;
    }

    public void setModulos( List<bModulo> modulos ) {
        this.modulos = modulos;
    }

    public String getAre_descripcion() {
        return are_descripcion;
    }

    public void setAre_descripcion( String are_descripcion ) {
        this.are_descripcion = are_descripcion;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete( String oncomplete ) {
        this.oncomplete = oncomplete;
    }

    public int getI_mod_id() {
        return i_mod_id;
    }

    public void setI_mod_id( int i_mod_id ) {
        this.i_mod_id = i_mod_id;
    }

    public String getI_mod_codigo() {
        return i_mod_codigo;
    }

    public void setI_mod_codigo( String i_mod_codigo ) {
        this.i_mod_codigo = i_mod_codigo;
    }

    public String getI_mod_descripcion() {
        return i_mod_descripcion;
    }

    public void setI_mod_descripcion( String i_mod_descripcion ) {
        this.i_mod_descripcion = i_mod_descripcion;
    }

    public Date getI_mod_creacion() {
        return i_mod_creacion;
    }

    public void setI_mod_creacion( Date i_mod_creacion ) {
        this.i_mod_creacion = i_mod_creacion;
    }

    public SelectItem[] getI_areas() {
        try {
            HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();
            List lista = dao.seleccionarArea( "" );
            if ( lista.size() > 0 ) {
                i_areas = new SelectItem[ lista.size() + 1 ];
                i_areas[0] = new SelectItem( 0, "[Seleccione]" );
                for ( int i = 0; i < lista.size(); i++ ) {
                    i_areas[i + 1] = new SelectItem( ((ClArbolAcademico) lista.get( i )).getArbId(), ((ClArbolAcademico) lista.get( i )).getArbDescripcion() );
                }
            } else {
                i_areas = new SelectItem[ 1 ];
                i_areas[0] = new SelectItem( 0, "[Seleccione]" );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return i_areas;

        /*
         * try { HSAreaDAO dao = (HSAreaDAO)
         * ServiceFinder.findBean("SpringHibernateDaoCLArea"); List lista =
         * dao.seleccionarArea(""); if (lista.size() > 0) { i_areas = new
         * SelectItem[lista.size() + 1]; i_areas[0] = new SelectItem(0,
         * "[Seleccione]"); for (int i = 0; i < lista.size(); i++) { i_areas[i +
         * 1] = new SelectItem(((ClArea) lista.get(i)).getAreId(), ((ClArea)
         * lista.get(i)).getAreDescripcion()); } } else { i_areas = new
         * SelectItem[1]; i_areas[0] = new SelectItem(0, "[Seleccione]"); } }
         * catch (Exception e) { e.printStackTrace(); } return i_areas;
         */
    }

    public void setI_areas( SelectItem[] i_areas ) {
        this.i_areas = i_areas;
    }

    public int getI_are_id() {
        return i_are_id;
    }

    public void setI_are_id( int i_are_id ) {
        this.i_are_id = i_are_id;
    }

    public List<bModulo> getCursos() {
        return cursos;
    }

    public void setCursos( List<bModulo> cursos ) {
        this.cursos = cursos;
    }

    public int getCur_id() {
        return cur_id;
    }

    public void setCur_id( int cur_id ) {
        this.cur_id = cur_id;
    }

    public String getCur_codigo() {
        return cur_codigo;
    }

    public void setCur_codigo( String cur_codigo ) {
        this.cur_codigo = cur_codigo;
    }

    public String getCur_nombre() {
        return cur_nombre;
    }

    public void setCur_nombre( String cur_nombre ) {
        this.cur_nombre = cur_nombre;
    }

    public String getCur_activo() {
        return cur_activo;
    }

    public void setCur_activo( String cur_activo ) {
        this.cur_activo = cur_activo;
    }

    public boolean isVerDetalle() {
        return verDetalle;
    }

    public void setVerDetalle( boolean verDetalle ) {
        this.verDetalle = verDetalle;
    }

    public String getImagenDetalle() {
        return imagenDetalle;
    }

    public void setImagenDetalle( String imagenDetalle ) {
        this.imagenDetalle = imagenDetalle;
    }

    public String getTituloDetalle() {
        return tituloDetalle;
    }

    public void setTituloDetalle( String tituloDetalle ) {
        this.tituloDetalle = tituloDetalle;
    }

    public int getI_cur_id() {
        return i_cur_id;
    }

    public void setI_cur_id( int i_cur_id ) {
        this.i_cur_id = i_cur_id;
    }

    public String getI_cur_codigo() {
        return i_cur_codigo;
    }

    public void setI_cur_codigo( String i_cur_codigo ) {
        this.i_cur_codigo = i_cur_codigo;
    }

    public String getI_cur_nombre() {
        return i_cur_nombre;
    }

    public void setI_cur_nombre( String i_cur_nombre ) {
        this.i_cur_nombre = i_cur_nombre;
    }

    public String getI_cur_activo() {
        return i_cur_activo;
    }

    public void setI_cur_activo( String i_cur_activo ) {
        this.i_cur_activo = i_cur_activo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage( String message ) {
        this.message = message;
    }

    public List<bModulo> getI_cursos() {
        return i_cursos;
    }

    public void setI_cursos( List<bModulo> i_cursos ) {
        this.i_cursos = i_cursos;
    }

    public boolean isVerAgregar() {
        return verAgregar;
    }

    public void setVerAgregar( boolean verAgregar ) {
        this.verAgregar = verAgregar;
    }

    public String getImagenAgregar() {
        return imagenAgregar;
    }

    public void setImagenAgregar( String imagenAgregar ) {
        this.imagenAgregar = imagenAgregar;
    }

    public String getTituloAgregar() {
        return tituloAgregar;
    }

    public void setTituloAgregar( String tituloAgregar ) {
        this.tituloAgregar = tituloAgregar;
    }

    public boolean isCursoVer() {
        return cursoVer;
    }

    public void setCursoVer( boolean cursoVer ) {
        this.cursoVer = cursoVer;
    }

    public boolean isCursoEditar() {
        return cursoEditar;
    }

    public void setCursoEditar( boolean cursoEditar ) {
        this.cursoEditar = cursoEditar;
    }

    public int getE_cur_id() {
        return e_cur_id;
    }

    public void setE_cur_id( int e_cur_id ) {
        this.e_cur_id = e_cur_id;
    }

    public String getE_cur_codigo() {
        return e_cur_codigo;
    }

    public void setE_cur_codigo( String e_cur_codigo ) {
        this.e_cur_codigo = e_cur_codigo;
    }

    public String getE_cur_nombre() {
        return e_cur_nombre;
    }

    public void setE_cur_nombre( String e_cur_nombre ) {
        this.e_cur_nombre = e_cur_nombre;
    }

    public int getCur_id_edit() {
        return cur_id_edit;
    }

    public void setCur_id_edit( int cur_id_edit ) {
        this.cur_id_edit = cur_id_edit;
    }

    public String getCur_codigo_edit() {
        return cur_codigo_edit;
    }

    public void setCur_codigo_edit( String cur_codigo_edit ) {
        this.cur_codigo_edit = cur_codigo_edit;
    }

    public String getCur_nombre_edit() {
        return cur_nombre_edit;
    }

    public void setCur_nombre_edit( String cur_nombre_edit ) {
        this.cur_nombre_edit = cur_nombre_edit;
    }

    public List<Integer> getIds_cur_delete() {
        return ids_cur_delete;
    }

    public void setIds_cur_delete( List<Integer> ids_cur_delete ) {
        this.ids_cur_delete = ids_cur_delete;
    }
}
