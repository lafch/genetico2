/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans.beans;

import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.List;
import javax.faces.model.SelectItem;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSPlantillaHorarioCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSSeccionCLDAO;
import net.uch.mapping.*;
import net.uch.metodo.local.MetodosAca;
import net.uch.util.CommonDAO;

/**
 *
 * @author cesardl
 */
public class BeanClSeccion extends ClSeccion {

    //-- CONSULTA ACADEMICA ALUMNO - LISTADO DE SECCIONES
    private int iAreaId;
    private int iModId;
    private int iCurId;
    private int iTallId;
    private float fNotaFinal;
    private String sNomArea;
    private String sNomModulo;
    private String sNomCurso;
    private String sNomTaller;
    //-- FIN CONSULTA ACADEMICA ALUMNO - LISTADO DE SECCIONES
    private SelectItem[] turnos;
    private String v_imagen_horario;
    private int cantMatriculados;
    private int cantPreMatriculados;
    MetodosAca metodo = new MetodosAca();
    private SelectItem[] cboLocal;
    private int w_loc_id;
    private String w_turno;
    private String w_sede;
    private String w_profesor;
    private int iSecGrupoId;
    private SelectItem[] cboSeccionGrupo;

    public SelectItem[] getCboSeccionGrupo() {
        int iSizeSecGrup;
        ClSeccionGrupo secGrup;
        HSSeccionCLDAO seccionDAO;
        List<ClSeccionGrupo> lstSecGrup;
        try {
            seccionDAO = CommonDAO.getClSeccionDAO();
            lstSecGrup = seccionDAO.listarSeccionGrupos( "" );
            if ( lstSecGrup != null && !lstSecGrup.isEmpty() ) {
                iSizeSecGrup = lstSecGrup.size();
                cboSeccionGrupo = new SelectItem[ iSizeSecGrup + 1 ];
                for ( int i = 0; i < iSizeSecGrup; i++ ) {
                    secGrup = lstSecGrup.get( i );
                    cboSeccionGrupo[i + 1] = new SelectItem( secGrup.getSecGrupoId(), secGrup.getNomSecGrupo() );
                }
            }
        } catch ( Exception ex ) {
        } finally {
            if ( cboSeccionGrupo == null ) {
                cboSeccionGrupo = new SelectItem[ 1 ];
            }
            cboSeccionGrupo[0] = new SelectItem( "0", "Seleccione Grupo" );
        }
        return cboSeccionGrupo;
    }

    public void setCboSeccionGrupo( SelectItem[] cboSeccionGrupo ) {
        this.cboSeccionGrupo = cboSeccionGrupo;
    }

    public int getSecGrupoId() {
        return iSecGrupoId;
    }

    public void setSecGrupoId( int iSecGrupoId ) {
        this.iSecGrupoId = iSecGrupoId;
    }

    public String getW_profesor() {
        return w_profesor;
    }

    public void setW_profesor( String w_profesor ) {
        this.w_profesor = w_profesor;
    }

    public String getW_sede() {
        return w_sede;
    }

    public void setW_sede( String w_sede ) {
        this.w_sede = w_sede;
    }

    public String getW_turno() {
        return w_turno;
    }

    public void setW_turno( String w_turno ) {
        this.w_turno = w_turno;
    }

    public SelectItem[] getCboLocal() throws SQLException {
        cboLocal = metodo.listarSedes( "Seleccione" );
        return cboLocal;
    }

    public void setCboLocal( SelectItem[] cboLocal ) {
        this.cboLocal = cboLocal;
    }

    public int getW_loc_id() {
        return w_loc_id;
    }

    public void setW_loc_id( int w_loc_id ) {
        this.w_loc_id = w_loc_id;
    }

    public BeanClSeccion() {
    }

    public BeanClSeccion( int arbape_id ) {
        //this.setClTallerAperturado(new ClTallerAperturado(talape_id));

        this.setSecId( 0 );
        this.setSecCodigo( "" );
        this.setSecNombre( "" );
//        this.setSecTurno( "X" );
        this.setSecVacMax( 0 );
        this.setSecVacMin( 0 );

        this.setSecActivo( "1" );

        this.setSecFfin( null );
        this.setSecFfinMatricula( null );
        this.setSecFinicio( null );
        this.setSecFinicioMatricula( null );
        this.setClArbolAcademico( new ClArbolAcademico() );
        this.setClArbolAperturado( new ClArbolAperturado( arbape_id ) );
    }

    public BeanClSeccion( ClSeccion seccion ) {
        ClArbolAcademico clArbAcadTmp;
        ClSeccionGrupo clSecGrup;
        //this.setClTallerAperturado(seccion.getClTallerAperturado());

        this.setSecId( seccion.getSecId() );
        this.setSecCodigo( seccion.getSecCodigo() );
        this.setSecNombre( seccion.getSecNombre() );
        this.setSecTurno( seccion.getSecTurno() );
        this.setSecTipo( seccion.getSecTipo() );
        this.setSecIdContinuacion( seccion.getSecIdContinuacion() );
        this.setSecVacMax( seccion.getSecVacMax() );
        this.setSecVacMin( seccion.getSecVacMin() );

        this.setSecActivo( seccion.getSecActivo() );

        this.setSecFfin( seccion.getSecFfin() );
        this.setSecFfinMatricula( seccion.getSecFfinMatricula() );
        this.setSecFinicio( seccion.getSecFinicio() );
        this.setSecFinicioMatricula( seccion.getSecFinicioMatricula() );
        this.setLocId( seccion.getLocId() );
        this.setPlaId( seccion.getPlaId() );
        this.setClArbolAcademico( seccion.getClArbolAcademico() );
        this.setClArbolAperturado( seccion.getClArbolAperturado() );
        // System.out.println("la cantidad de horarios -> "+seccion.getClHorarias().size());
        this.setV_imagen_horario( "/Imagenes/actions/calendar_gris.png" );
        if ( seccion.getClHorarias().size() > 0 ) {
            this.setV_imagen_horario( "/Imagenes/actions/calendar.gif" );
        }

//        clSecGrup = CommonDAO.getClSeccionDAO().traerClSeccionGrupo( seccion );
        clSecGrup = seccion.getClSecGrup();
        if ( clSecGrup != null ) {
            this.iSecGrupoId = clSecGrup.getSecGrupoId();
            this.setClSecGrup( clSecGrup );
        }
        clArbAcadTmp = this.getClArbolAcademico();
        if ( clArbAcadTmp != null ) {
            try {
                this.setNomTaller( clArbAcadTmp.getArbDescripcion() );
                clArbAcadTmp = clArbAcadTmp.getArbAcadPadre();
                this.setNomCurso( clArbAcadTmp.getArbDescripcion() );
                clArbAcadTmp = clArbAcadTmp.getArbAcadPadre();
                this.setNomModulo( clArbAcadTmp.getArbDescripcion() );
                clArbAcadTmp = clArbAcadTmp.getArbAcadPadre();
                this.setNomArea( clArbAcadTmp.getArbDescripcion() );
            } catch ( Exception ex ) {
                ex.printStackTrace();
            }
        }
    }

    public SelectItem[] getTurnos() {
        /*
         * if (turnos == null) { turnos = new SelectItem[4]; turnos[0] = new
         * SelectItem("X", "[Seleccione]"); turnos[1] = new SelectItem("M",
         * "MAÑANA"); turnos[2] = new SelectItem("T", "TARDE"); turnos[3] = new
         * SelectItem("N", "NOCHE"); }
         */
        HSPlantillaHorarioCLDAO dao = (HSPlantillaHorarioCLDAO) ServiceFinder.findBean( "SpringHibernateDaoPlantillaHorario" );
        List<ClPlantillaHorario> listaP = dao.listarPlantilla();
        System.out.println( "BeanClSeccion-> cntidaad de la lista -> " + listaP.size() );
        this.turnos = new SelectItem[ listaP.size() + 1 ];
        this.turnos[0] = new SelectItem( 0, "[Seleccione]" );
        for ( int i = 0; i < (turnos.length - 1); i++ ) {
            this.turnos[i + 1] = new SelectItem( listaP.get( i ).getPlaId(), listaP.get( i ).getPlaDescripcion() );
        }
        return turnos;
    }

    public void setTurnos( SelectItem[] turnos ) {
        this.turnos = turnos;
    }

    public String esValido() {
        String sMsjError = "";
        if ( this.getSecCodigo().trim().isEmpty() ) {
            sMsjError = "Ingresar Código.";
        } else if ( this.getSecNombre().trim().isEmpty() ) {
            sMsjError = "Ingresar Nombre.";
        } else if ( this.getSecVacMax().intValue() == 0 ) {
            sMsjError = "Ingresar Cantidad Máxima de Vacantes.";
        } else if ( this.getSecVacMin().intValue() == 0 ) {
            sMsjError = "Ingresar Cantidad Mínima de Vacantes.";
        } else if ( this.getSecFinicio() == null ) {
            sMsjError = "Ingresar Fecha de Inicio.";
        } else if ( this.getSecFfin() == null ) {
            sMsjError = "Ingresar Fecha de Fin.";
        } else if ( this.getSecFinicioMatricula() == null ) {
            sMsjError = "Ingresar Fecha de Inicio de Matrícula.";
        } else if ( this.getSecFfinMatricula() == null ) {
            sMsjError = "Ingresar Fecha de Fin de Matrícula.";
        }
        return sMsjError;
    }

    public int getCantPreMatriculados() {
        return cantPreMatriculados;
    }

    public void setCantPreMatriculados( int cantPreMatriculados ) {
        this.cantPreMatriculados = cantPreMatriculados;
    }

    public String getV_imagen_horario() {
        return v_imagen_horario;
    }

    public void setV_imagen_horario( String v_imagen_horario ) {
        this.v_imagen_horario = v_imagen_horario;
    }

    public int getCantMatriculados() {
        return cantMatriculados;
    }

    public void setCantMatriculados( int cantMatriculados ) {
        this.cantMatriculados = cantMatriculados;
    }

    //--------------------------------------------------------
    public float getNotaFinal() {
        return fNotaFinal;
    }

    public void setNotaFinal( float fNotaFinal ) {
        this.fNotaFinal = fNotaFinal;
    }

    public int getAreaId() {
        return iAreaId;
    }

    public void setAreaId( int iAreaId ) {
        this.iAreaId = iAreaId;
    }

    public int getCurId() {
        return iCurId;
    }

    public void setCurId( int iCurId ) {
        this.iCurId = iCurId;
    }

    public int getModId() {
        return iModId;
    }

    public void setModId( int iModId ) {
        this.iModId = iModId;
    }

    public int getTallId() {
        return iTallId;
    }

    public void setTallId( int iTallId ) {
        this.iTallId = iTallId;
    }

    public String getNomArea() {
        return sNomArea;
    }

    public void setNomArea( String sNomArea ) {
        this.sNomArea = sNomArea;
    }

    public String getNomCurso() {
        return sNomCurso;
    }

    public void setNomCurso( String sNomCurso ) {
        this.sNomCurso = sNomCurso;
    }

    public String getNomModulo() {
        return sNomModulo;
    }

    public void setNomModulo( String sNomModulo ) {
        this.sNomModulo = sNomModulo;
    }

    public String getNomTaller() {
        return sNomTaller;
    }

    public void setNomTaller( String sNomTaller ) {
        this.sNomTaller = sNomTaller;
    }
    //--------------------------------------------------------

    public ClSeccion getClSeccion() {
        ClSeccion sec = new ClSeccion();
        // sec.setClTallerAperturado(this.getClTallerAperturado());
        sec.setClMatriculaTallers( new LinkedHashSet() );
        sec.setClHorarias( new LinkedHashSet() );

        sec.setSecId( this.getSecId() );
        sec.setSecCodigo( this.getSecCodigo() );
        sec.setSecNombre( this.getSecNombre() );
        sec.setSecTurno( this.getSecTurno() );
        sec.setSecVacMax( this.getSecVacMax() );
        sec.setSecVacMin( this.getSecVacMin() );

        sec.setSecActivo( this.getSecActivo() );

        sec.setSecFfin( this.getSecFfin() );
        sec.setSecFfinMatricula( this.getSecFfinMatricula() );
        sec.setSecFinicio( this.getSecFinicio() );
        sec.setSecFinicioMatricula( this.getSecFinicioMatricula() );
        sec.setLocId( this.getLocId() );
        sec.setPlaId( this.getPlaId() );
        sec.setClArbolAcademico( this.getClArbolAcademico() );
        sec.setClArbolAperturado( this.getClArbolAperturado() );
        sec.setSecIdContinuacion( this.getSecIdContinuacion() );
        return sec;
    }

    public BeanClSeccion getCopia() {
        BeanClSeccion bClSecCopy;
        ClArbolAcademico arb;

        arb = this.getClArbolAcademico(); //Taller
        this.sNomTaller = arb.getArbDescripcion();

        arb = arb.getArbAcadPadre();//Curso
        this.sNomCurso = arb.getArbDescripcion();

        arb = arb.getArbAcadPadre();//Modulo
        this.sNomModulo = arb.getArbDescripcion();

        arb = arb.getArbAcadPadre();//Area
        this.sNomArea = arb.getArbDescripcion();


        bClSecCopy = new BeanClSeccion();
        bClSecCopy.setSecId( this.getSecId() );
        bClSecCopy.setSecCodigo( this.getSecCodigo() );
        bClSecCopy.setSecNombre( this.getSecNombre() );
        bClSecCopy.setSecFinicio( this.getSecFinicio() );
        bClSecCopy.setSecFfin( this.getSecFfin() );
        bClSecCopy.setNomArea( this.getNomArea() );
        bClSecCopy.setNomModulo( this.getNomModulo() );
        bClSecCopy.setNomCurso( this.getNomCurso() );
        bClSecCopy.setNomTaller( this.getNomTaller() );
        bClSecCopy.setClSecGrup( this.getClSecGrup() );

        return bClSecCopy;
    }
}
