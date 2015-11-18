/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.asistenciaDocente.managedBeans.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.model.SelectItem;
import net.uch.academica.hibernateSpringDao.HSDocenteDAO;
import net.uch.academica.hibernateSpringDao.HSSeccionDAO;
import net.uch.asistenciaDocente.hibernateSpringDao.HSAsistenciaDocenteDAO;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSArbolAcademicoClDao;
import net.uch.cursoLibre.hibernateSpringDao.HSSeccionCLDAO;
import net.uch.mapping.*;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSSemestreDAO;
import net.uch.util.CommonDAO;

/**
 *
 * @author LUIS
 */
public class NuevaHoraAdicional {

    private int n_doc_id;
    private SelectItem n_doc[];
    private Date n_fsesion;
    private Date n_fsesion_ini;
    private Date n_fsesion_fin;
    private SelectItem n_tipasis[];
    private String n_tipasis_code;//050
    private SelectItem n_tipses[];
    private String n_tipses_code;//047
    private String n_seccion_code;
    private SelectItem[] n_seccion;
    private int n_especialidad_code;
    private SelectItem[] n_especialidad;
    private int n_curso_seccion;
    private SelectItem[] n_curso;
    private int reg_hora;
    private int reg_min;
    private int sal_hora;
    private int sal_min;
    
    private int iIdSec;
    private int iIdArea;
    private int iIdMod;
    private int iIdCur;
    private String sCentro;
    
    private SelectItem[] cboCentros;
    private SelectItem[] cboAreas;
    private SelectItem[] cboModulos;
    private SelectItem[] cboCursos;
    private SelectItem[] cboSecciones;

    public int getReg_hora() {
        return reg_hora;
    }

    public void setReg_hora( int reg_hora ) {
        this.reg_hora = reg_hora;
    }

    public int getReg_min() {
        return reg_min;
    }

    public void setReg_min( int reg_min ) {
        this.reg_min = reg_min;
    }

    public int getSal_hora() {
        return sal_hora;
    }

    public void setSal_hora( int sal_hora ) {
        this.sal_hora = sal_hora;
    }

    public int getSal_min() {
        return sal_min;
    }

    public void setSal_min( int sal_min ) {
        this.sal_min = sal_min;
    }

    public SelectItem[] getN_doc() {
        try {
            HSDocenteDAO dao = (HSDocenteDAO)ServiceFinder.findBean( "SpringHibernateDaoDocente" );
            List l = dao.seleccionarDocente();
            n_doc = new SelectItem[ l.size() + 1 ];

            for ( int i = 0; i < l.size(); i++ ) {
                AcDocente doc = (AcDocente)l.get( i );
                n_doc[i + 1] = new SelectItem( doc.getId(), doc.getDocNombre() );
            }
        } catch ( Exception e ) {
            n_doc = new SelectItem[ 1 ];
        } finally {
            n_doc[0] = new SelectItem( 0, "[Seleccione]" );
        }
        return n_doc;
    }

    public void setN_doc( SelectItem[] n_doc ) {
        this.n_doc = n_doc;
    }

    public int getN_doc_id() {
        return n_doc_id;
    }

    public void setN_doc_id( int n_doc_id ) {
        this.n_doc_id = n_doc_id;
    }

    public SelectItem[] getN_curso() {
        HSSeccionDAO dao = (HSSeccionDAO)ServiceFinder.findBean( "HibernateSpringDaoTargetSeccion" );
        HSSemestreDAO daos = (HSSemestreDAO)ServiceFinder.findBean( "SpringHibernateDaoSemestre" );
        AcSemestre sem_id = (AcSemestre)daos.seleccionarSemestreActual().get( 0 );
        List lista = dao.listarCursosporSeccion( this.getN_seccion_code(), sem_id.getId() );
        if ( lista.size() > 0 ) {
            n_curso = new SelectItem[ lista.size() + 1 ];
            n_curso[0] = new SelectItem( "0", "[Seleccione]" );
            for ( int i = 0; i < ( n_curso.length - 1 ); i++ ) {
                n_curso[i + 1] = new SelectItem( ( (AcSeccion)lista.get( i ) ).getId(), ( (AcSeccion)lista.get( i ) ).getCurape().getPlancur().getCur().getCurNombre() );
            }

        } else {
            n_curso = new SelectItem[ 1 ];
            n_curso[0] = new SelectItem( "0", "[Seleccione]" );

        }
        return n_curso;
    }

    public void setN_curso( SelectItem[] n_curso ) {
        this.n_curso = n_curso;
    }

    public int getN_curso_seccion() {
        return n_curso_seccion;
    }

    public void setN_curso_seccion( int n_curso_seccion ) {
        this.n_curso_seccion = n_curso_seccion;
    }

    public SelectItem[] getN_especialidad() {
        try {
            HSAsistenciaDocenteDAO dao = (HSAsistenciaDocenteDAO)ServiceFinder.findBean( "SpringHibernateDaoAsistenciaDocente" );
            List<AcEspecialidad> lista = dao.listarEspecialidad();
            n_especialidad = new SelectItem[ lista.size() + 1 ];

            for ( int i = 0; i < lista.size(); i++ ) {
                AcEspecialidad esp = lista.get( i );
                n_especialidad[i + 1] = new SelectItem( esp.getId(), esp.getEspNombre() );
            }
        } catch ( Exception e ) {
            n_especialidad = new SelectItem[ 1 ];
        } finally {
            n_especialidad[0] = new SelectItem( 0, "[Seleccione]" );
        }
        return n_especialidad;
    }

    public void setN_especialidad( SelectItem[] n_especialidad ) {
        this.n_especialidad = n_especialidad;
    }

    public int getN_especialidad_code() {
        return n_especialidad_code;
    }

    public void setN_especialidad_code( int n_especialidad_code ) {
        this.n_especialidad_code = n_especialidad_code;
    }

    public Date getN_fsesion() {
        return n_fsesion;
    }

    public void setN_fsesion( Date n_fsesion ) {
        this.n_fsesion = n_fsesion;
    }

    public Date getN_fsesion_fin() {
        return n_fsesion_fin;
    }

    public void setN_fsesion_fin( Date n_fsesion_fin ) {
        this.n_fsesion_fin = n_fsesion_fin;
    }

    public Date getN_fsesion_ini() {
        return n_fsesion_ini;
    }

    public void setN_fsesion_ini( Date n_fsesion_ini ) {
        this.n_fsesion_ini = n_fsesion_ini;
    }

    public SelectItem[] getN_seccion() {
        HSSeccionDAO dao = (HSSeccionDAO)ServiceFinder.findBean( "HibernateSpringDaoSeccion" );
        HSSemestreDAO daos = (HSSemestreDAO)ServiceFinder.findBean( "SpringHibernateDaoSemestre" );
        AcSemestre sem_id = (AcSemestre)daos.seleccionarSemestreActual().get( 0 );

        List lista = dao.listarSeccionesAdional( this.getN_especialidad_code(), sem_id.getId() );
        if ( lista.size() > 0 ) {
            n_seccion = new SelectItem[ lista.size() + 1 ];
            n_seccion[0] = new SelectItem( "0", "[Seleccione]" );
            for ( int i = 0; i < ( n_seccion.length - 1 ); i++ ) {
                n_seccion[i + 1] = new SelectItem( lista.get( i ).toString(), lista.get( i ).toString() );
            }
        } else {
            n_seccion = new SelectItem[ 1 ];
            n_seccion[0] = new SelectItem( "0", "[Seleccione]" );
        }
        return n_seccion;
    }

    public void setN_seccion( SelectItem[] n_seccion ) {
        this.n_seccion = n_seccion;
    }

    public String getN_seccion_code() {
        return n_seccion_code;
    }

    public void setN_seccion_code( String n_seccion_code ) {
        this.n_seccion_code = n_seccion_code;
    }

    public SelectItem[] getN_tipasis() {
        try {
            HSCatalogoDAO catalogoDAO = (HSCatalogoDAO)ServiceFinder.findBean( "SpringHibernateDaoCatalogo" );
            List l = catalogoDAO.seleccionarCatalogo( "050" );
            n_tipasis = new SelectItem[ l.size() + 1 ];

            for ( int i = 0; i < l.size(); i++ ) {
                TbCatalogo cat = (TbCatalogo)l.get( i );
                n_tipasis[i + 1] = new SelectItem( "050" + cat.getCatCodigoElemento(), cat.getCatDescripcionElemento() );
            }
        } catch ( Exception e ) {
            n_tipasis = new SelectItem[ 1 ];
        } finally {
            n_tipasis[0] = new SelectItem( 0, "[Seleccione]" );
        }
        return n_tipasis;
    }

    public void setN_tipasis( SelectItem[] n_tipasis ) {
        this.n_tipasis = n_tipasis;
    }

    /**
     * 050
     *
     * @return
     */
    public String getN_tipasis_code() {
        return n_tipasis_code;
    }

    public void setN_tipasis_code( String n_tipasis_code ) {
        this.n_tipasis_code = n_tipasis_code;
    }

    public SelectItem[] getN_tipses() {
        try {
            HSCatalogoDAO catalogoDAO = (HSCatalogoDAO)ServiceFinder.findBean( "SpringHibernateDaoCatalogo" );
            List l = catalogoDAO.seleccionarCatalogo( "083" );
            n_tipses = new SelectItem[ l.size() + 1 ];

            for ( int i = 0; i < l.size(); i++ ) {
                TbCatalogo cat = (TbCatalogo)l.get( i );
                n_tipses[i + 1] = new SelectItem( "083" + cat.getCatCodigoElemento(), cat.getCatDescripcionElemento() );
            }
        } catch ( Exception e ) {
            n_tipses = new SelectItem[ 1 ];
        } finally {
            n_tipses[0] = new SelectItem( 0, "[Seleccione]" );
        }
        return n_tipses;
    }

    public void setN_tipses( SelectItem[] n_tipses ) {
        this.n_tipses = n_tipses;
    }

    /**
     * 047
     *
     * @return
     */
    public String getN_tipses_code() {
        return n_tipses_code;
    }

    public void setN_tipses_code( String n_tipses_code ) {
        this.n_tipses_code = n_tipses_code;
    }

    @Override
    public String toString() {
        return "id docente: " + this.getN_doc_id()
                + "\ntipo asis 083 " + this.getN_tipasis_code()
                + "\ntipo ses 047 " + this.getN_tipses_code()
                + "\ncurso: " + this.getN_curso_seccion()
                + "\ncurso_seccion: " + this.getN_curso_seccion()
                + "\nsesion: " + this.getN_fsesion()
                + "\nfe ini: " + this.getN_fsesion_ini()
                + "\nfe fin: " + this.getN_fsesion_fin()
                + "\nespecialidad: " + this.getN_especialidad_code();
    }

    public SelectItem[] getCboAreas() {
        ClArbolAcademico arbAcad;
        HSArbolAcademicoClDao arbAcadClDAO;
        List<ClArbolAcademico> lstAreas;
        try {
            if ( "0".equals( sCentro ) ) {
                iIdArea = 0;
                cboAreas = new SelectItem[ 1 ];
            } else {
                arbAcadClDAO = CommonDAO.getClArbolAcademicoDAO();
                lstAreas = arbAcadClDAO.AreasXInstitucion( sCentro );
                cboAreas = new SelectItem[ lstAreas.size() + 1 ];
                for ( int i = 0; i < lstAreas.size(); i++ ) {
                    arbAcad = lstAreas.get( i );
                    cboAreas[i + 1] = new SelectItem( arbAcad.getArbId(), arbAcad.getArbDescripcion() );
                }
            }
            cboAreas[0] = new SelectItem( "0", "[Seleccione]" );
        } catch ( Exception e ) {
            System.err.println( "Error al cargar areas" );
        }
        return cboAreas;
    }

    public void setCboAreas( SelectItem[] cboAreas ) {
        this.cboAreas = cboAreas;
    }

    public SelectItem[] getCboCentros() {
        int iSizeCent;
        TbCatalogo catCent;
        List<TbCatalogo> lstCatCentros;

        cboCentros = new SelectItem[ 1 ];
        cboCentros[0] = new SelectItem( "0", "[Seleccione]" );
        try {
            lstCatCentros = CommonDAO.getTbCatalogoDAO().seleccionarCatalogo( "078" );
            if ( lstCatCentros != null && !lstCatCentros.isEmpty() ) {
                iSizeCent = lstCatCentros.size();
                cboCentros = new SelectItem[ iSizeCent ];
                cboCentros[0] = new SelectItem( "0", "[Seleccione]" );
                for ( int i = 0; i < iSizeCent; i++ ) {
                    catCent = lstCatCentros.get( i );
                    if ( !"UCH".equals( catCent.getCatDescripcionElemento() ) ) {
                        cboCentros[i + 1] = new SelectItem( catCent.getCatCodigoGrupo() + catCent.getCatCodigoElemento(), catCent.getCatDescripcionElemento() );
                    }
                }
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return cboCentros;
    }

    public void setCboCentros( SelectItem[] cboCentros ) {
        this.cboCentros = cboCentros;
    }

    public SelectItem[] getCboCursos() {
        HSArbolAcademicoClDao arbAcadDAO;
        List<ClArbolAcademico> lstCursos;
        if ( this.iIdMod == 0 ) {
            iIdCur = 0;
            cboCursos = new SelectItem[ 1 ];
        } else {
            try {
                arbAcadDAO = CommonDAO.getClArbolAcademicoDAO();
                lstCursos = arbAcadDAO.seleccionarCursos( this.iIdMod );

                cboCursos = new SelectItem[ lstCursos.size() + 1 ];
                for ( int i = 0; i < lstCursos.size(); i++ ) {
                    cboCursos[i + 1] = new SelectItem( lstCursos.get( i ).getArbId(), lstCursos.get( i ).getArbDescripcion() );
                }
            } catch ( Exception e ) {
                System.err.println( "Error al cargar areas" );
            }
        }
        cboCursos[0] = new SelectItem( 0, "[Seleccione]" );
        return cboCursos;
    }

    public void setCboCursos( SelectItem[] cboCursos ) {
        this.cboCursos = cboCursos;
    }

    public SelectItem[] getCboModulos() {
        HSArbolAcademicoClDao arbAcadDAO;
        List<ClArbolAcademico> lstModulos;

        if ( this.iIdArea == 0 ) {
            iIdMod = 0;
            cboModulos = new SelectItem[ 1 ];
        } else {
            arbAcadDAO = CommonDAO.getClArbolAcademicoDAO();
            try {
                lstModulos = arbAcadDAO.seleccionarModulos( this.iIdArea, "" );
                cboModulos = new SelectItem[ lstModulos.size() + 1 ];
                for ( int i = 0; i < lstModulos.size(); i++ ) {
                    cboModulos[i + 1] = new SelectItem( lstModulos.get( i ).getArbId(), lstModulos.get( i ).getArbDescripcion() );
                }
            } catch ( Exception ex ) {
                ex.printStackTrace();
                lstModulos = new ArrayList<ClArbolAcademico>();
            }
        }
        cboModulos[0] = new SelectItem( "0", "[Seleccione]" );
        return cboModulos;
    }

    public void setCboModulos( SelectItem[] cboModulos ) {
        this.cboModulos = cboModulos;
    }

    public SelectItem[] getCboSecciones() {
        HSSeccionCLDAO seccionDAO;
        List<ClSeccion> lstSecciones;

        seccionDAO = (HSSeccionCLDAO)ServiceFinder.findBean( "SpringHibernateDaoCLSeccion" );

        if ( this.iIdCur == 0 ) {
            iIdSec = 0;
            cboSecciones = new SelectItem[ 1 ];
        } else {
            lstSecciones = seccionDAO.listarTodasSeccionesXCurso( this.iIdCur );
            cboSecciones = new SelectItem[ lstSecciones.size() + 1 ];
            for ( int i = 0; i < lstSecciones.size(); i++ ) {
                cboSecciones[i + 1] = new SelectItem( lstSecciones.get( i ).getSecId(), lstSecciones.get( i ).getSecNombre() );
            }
        }
        cboSecciones[0] = new SelectItem( 0, "[Seleccione]" );
        return cboSecciones;
    }

    public void setCboSecciones( SelectItem[] cboSecciones ) {
        this.cboSecciones = cboSecciones;
    }

    public int getIdArea() {
        return iIdArea;
    }

    public void setIdArea( int iIdArea ) {
        this.iIdArea = iIdArea;
    }

    public int getIdCur() {
        return iIdCur;
    }

    public void setIdCur( int iIdCur ) {
        this.iIdCur = iIdCur;
    }

    public int getIdMod() {
        return iIdMod;
    }

    public void setIdMod( int iIdMod ) {
        this.iIdMod = iIdMod;
    }

    public int getIdSec() {
        return iIdSec;
    }

    public void setIdSec( int iIdSec ) {
        this.iIdSec = iIdSec;
    }

    public String getCentro() {
        return sCentro;
    }

    public void setCentro( String sCentro ) {
        this.sCentro = sCentro;
    }
}
