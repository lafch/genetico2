/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSArbolAcademicoClDao;
import net.uch.cursoLibre.hibernateSpringDao.HSConsultaPublicoDAO;
import net.uch.cursoLibre.managedBeans.beans.BeanCLPublico;
import net.uch.cursoLibre.managedBeans.beans.BeanInfoMatricula;
import net.uch.mapping.ClConsultaPublico;
import net.uch.mapping.TbCatalogo;
import net.uch.tablaSistema.hibernateSpringDao.HSUsuarioDAO;
import net.uch.util.CommonDAO;
import net.uch.util.CommonWeb;

/**
 *
 * @author Richa R B
 */
public class bInfoMatriculados {

    private List<bInfoMatriculados> w_listausuarios = new ArrayList<bInfoMatriculados>();
    private Date w_fechaIni = new Date();
    private Date w_fechaFin = new Date();
    private String w_cantiMatriculado;
    private String w_cantiInformes;
    private int w_cantiAlumnos;
    private int w_usuId;
    private String w_usuUsuario;
    private int w_contador;
    private List<BeanCLPublico> listadoAlumnos = new ArrayList<BeanCLPublico>();
    private String oncomplete;
    private String sCentroId = "0";
    private int iAreaId = 0;
    private int iModId = 0;
    private int iCursoId = 0;
    private SelectItem[] cboCentro;
    private SelectItem[] cboArea;
    private SelectItem[] cboModulo;
    private SelectItem[] cboCurso;

    //SETTER Y GETTER
    public SelectItem[] getCboCentro() {
        int i;
        List<TbCatalogo> lstCentros;

        lstCentros = CommonDAO.getTbCatalogoDAO().seleccionarCatalogo( "078" );

        cboCentro = new SelectItem[ lstCentros.size() ];
        cboCentro[0] = new SelectItem( "0", "Seleccione Centro" );
        i = 0;
        for ( TbCatalogo catCentro : lstCentros ) {
            if ( !"004".equals( catCentro.getCatCodigoElemento() ) ) {
                cboCentro[ ++i] = new SelectItem( catCentro.getCatCodigoGrupo() + catCentro.getCatCodigoElemento(), catCentro.getCatDescripcionElemento() );
            }
        }

        return cboCentro;
    }

    public void setCboCentro( SelectItem[] cboCentro ) {
        this.cboCentro = cboCentro;
    }

    public SelectItem[] getCboArea() {

        if ( !"0".equals( sCentroId ) ) {
            cboArea = CommonWeb.getCboArbolAreaInf( 0, sCentroId );
        } else {
            cboArea = new SelectItem[ 1 ];
            cboArea[0] = new SelectItem( "0", "[Seleccionar área]" );
        }
        return cboArea;
    }

    public void setCboArea( SelectItem[] cboArea ) {
        this.cboArea = cboArea;
    }

    public SelectItem[] getCboCurso() {
        return CommonWeb.getCboArbolCursoInf( iModId );
    }

    public void setCboCurso( SelectItem[] cboCurso ) {
        this.cboCurso = cboCurso;
    }

    public SelectItem[] getCboModulo() {
        return CommonWeb.getCboArbolModInf( iAreaId );
    }

    public void setCboModulo( SelectItem[] cboModulo ) {
        this.cboModulo = cboModulo;
    }

    public String getCentroId() {
        return sCentroId;
    }

    public void setCentroId( String sCentroId ) {
        this.sCentroId = sCentroId;
    }

    public int getiAreaId() {
        return iAreaId;
    }

    public void setiAreaId( int iAreaId ) {
        this.iAreaId = iAreaId;
    }

    public int getiCursoId() {
        return iCursoId;
    }

    public void setiCursoId( int iCursoId ) {
        this.iCursoId = iCursoId;
    }

    public int getiModId() {
        return iModId;
    }

    public void setiModId( int iModId ) {
        this.iModId = iModId;
    }

    public int getW_cantiAlumnos() {
        return w_cantiAlumnos;
    }

    public void setW_cantiAlumnos( int w_cantiAlumnos ) {
        this.w_cantiAlumnos = w_cantiAlumnos;
    }

    public String getW_cantiInformes() {
        return w_cantiInformes;
    }

    public void setW_cantiInformes( String w_cantiInformes ) {
        this.w_cantiInformes = w_cantiInformes;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete( String oncomplete ) {
        this.oncomplete = oncomplete;
    }

    public List<BeanCLPublico> getListadoAlumnos() {
        return listadoAlumnos;
    }

    public void setListadoAlumnos( List<BeanCLPublico> listadoAlumnos ) {
        this.listadoAlumnos = listadoAlumnos;
    }

    public int getW_contador() {
        return w_contador;
    }

    public void setW_contador( int w_contador ) {
        this.w_contador = w_contador;
    }

    public String getW_cantiMatriculado() {
        return w_cantiMatriculado;
    }

    public void setW_cantiMatriculado( String w_cantiMatriculado ) {
        this.w_cantiMatriculado = w_cantiMatriculado;
    }

    public int getW_usuId() {
        return w_usuId;
    }

    public void setW_usuId( int w_usuId ) {
        this.w_usuId = w_usuId;
    }

    public String getW_usuUsuario() {
        return w_usuUsuario;
    }

    public void setW_usuUsuario( String w_usuUsuario ) {
        this.w_usuUsuario = w_usuUsuario;
    }

    public List<bInfoMatriculados> getW_listausuarios() {

        return w_listausuarios;
    }

    public void setW_listausuarios( List<bInfoMatriculados> w_listausuarios ) {
        this.w_listausuarios = w_listausuarios;
    }

    public Date getW_fechaFin() {
        return w_fechaFin;
    }

    public void setW_fechaFin( Date w_fechaFin ) {
        this.w_fechaFin = w_fechaFin;
    }

    public Date getW_fechaIni() {
        return w_fechaIni;
    }

    public void setW_fechaIni( Date w_fechaIni ) {
        this.w_fechaIni = w_fechaIni;
    }

    //METODOS
    public void verMatriculados( ActionEvent event ) {
        try {
            this.setW_listausuarios( new ArrayList<bInfoMatriculados>() );
            HSConsultaPublicoDAO dao;
            dao = CommonDAO.getClConsultaPublicoDAO();

            HSUsuarioDAO daoU = (HSUsuarioDAO)ServiceFinder.findBean( "SpringHibernateDaoUsuario" );

            List<BeanInfoMatricula> lista = dao.cantidadMatriculadosUsuario( w_fechaIni, w_fechaFin, this.sCentroId, this.iAreaId, this.iModId, this.iCursoId );

            int con = 1;
            for ( int i = 0; i < lista.size(); i++ ) {
                bInfoMatriculados inf = new bInfoMatriculados();
                String usuario = "Desconocido";
                if ( lista.get( i ).getUsu_id() != 0 ) {
                    usuario = daoU.verUsuario( lista.get( i ).getUsu_id() );
                }

                inf.setW_cantiMatriculado( String.valueOf( lista.get( i ).getInf_matriculados() ) );
                inf.setW_usuUsuario( usuario );
                inf.setW_usuId( lista.get( i ).getUsu_id() );
                inf.setW_contador( lista.get( i ).getInf_contador() );
                inf.setW_cantiInformes( String.valueOf( lista.get( i ).getInf_informes() ) );
                inf.setW_cantiAlumnos( lista.get( i ).getInf_matriculados() + lista.get( i ).getInf_informes() );
                this.getW_listausuarios().add( inf );
                con++;
            }


        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

//    public void listadoMatriculados( ActionEvent event ) {
//        HSConsultaPublicoDAO dao = (HSConsultaPublicoDAO) ServiceFinder.findBean( "SpringHibernateDaoConsultaPublico" );
//        int pUsu_id = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "p_usuId" )).getValue().toString() );
//        List<ClConsultaPublico> lista = dao.listadoInscritosPorUsuario( pUsu_id, w_fechaIni, w_fechaFin, "2", iAreaId, iModId, iCursoId, sCentroId );
//        llenarListadoAlumno( lista );
//    }
    public void listadoMatriculados( ActionEvent event ) {
        HSConsultaPublicoDAO dao = (HSConsultaPublicoDAO)ServiceFinder.findBean( "SpringHibernateDaoConsultaPublico" );
        int pUsu_id = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_usuId" ) ).getValue().toString() );
        List<BeanCLPublico> lista = dao.lstMatUsu( pUsu_id, w_fechaIni, w_fechaFin, "2", iAreaId, iModId, iCursoId, sCentroId );
        this.setListadoAlumnos( lista );
        this.setOncomplete( "javascript:Richfaces.showModalPanel('mpListaAlumno')" );
    }

//    public void listadoInformes( ActionEvent event ) {
//        HSConsultaPublicoDAO dao = (HSConsultaPublicoDAO)ServiceFinder.findBean( "SpringHibernateDaoConsultaPublico" );
//        int pUsu_id = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_usuId" ) ).getValue().toString() );
//        List<ClConsultaPublico> lista = dao.listadoInscritosPorUsuario( pUsu_id, w_fechaIni, w_fechaFin, "0", iAreaId, iModId, iCursoId, sCentroId );
//        llenarListadoAlumno( lista );
//    }
    public void listadoInformes( ActionEvent event ) {
        HSConsultaPublicoDAO dao = (HSConsultaPublicoDAO)ServiceFinder.findBean( "SpringHibernateDaoConsultaPublico" );
        int pUsu_id = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_usuId" ) ).getValue().toString() );
        List<BeanCLPublico> lista = dao.lstInfUsu(pUsu_id, w_fechaIni, w_fechaFin, "2", iAreaId, iModId, iCursoId, sCentroId );
        this.setListadoAlumnos( lista );
        this.setOncomplete( "javascript:Richfaces.showModalPanel('mpListaAlumno')" );
    }

//    public void llenarListadoAlumno( List<ClConsultaPublico> lista ) {
//        this.setOncomplete( "" );
//        HSArbolAcademicoClDao daoArb = CommonDAO.getClArbolAcademicoDAO();
//        try {
//            this.setListadoAlumnos( new ArrayList<BeanCLPublico>() );
//            for ( int i = 0; i < lista.size(); i++ ) {
//                BeanCLPublico publico = new BeanCLPublico();
//                publico.setClAlumno( lista.get( i ).getClAlumno() );
//                publico.setDes_modulo( lista.get( i ).getClArbolAcademico().getArbDescripcion() );
//
//                publico.setsDesArea( daoArb.buscarArbolPorId( lista.get( i ).getClArbolAcademico().getArbIdPadre() ).getArbDescripcion() );
//
//                if ( lista.get( i ).getCurId() != null ) {
//                    publico.setsDesCurso( daoArb.buscarArbolPorId( lista.get( i ).getCurId() ).getArbDescripcion() );
//                } else {
//                    publico.setsDesCurso( "No Determinado" );
//                }
//
//                publico.setFechaContacto( lista.get( i ).getConFechaRegistro() );
//                publico.setContador( i + 1 );
//                this.getListadoAlumnos().add( publico );
//            }
//            this.setOncomplete( "javascript:Richfaces.showModalPanel('mpListaAlumno')" );
//        } catch ( Exception e ) {
//            e.printStackTrace();
//        }
//    }
    
}
