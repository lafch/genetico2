/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.utilsAcademico;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.model.SelectItem;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSArbolAcademicoClDao;
import net.uch.mapping.ClArbolAcademico;
import net.uch.mapping.ClSeccion;
import net.uch.mapping.TbCatalogo;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.util.CommonDAO;
import net.uch.util.CommonWeb;

/**
 *
 * @author Toshiba
 */
public class Metodos {

    HSCatalogoDAO dao = (HSCatalogoDAO) ServiceFinder.findBean( "SpringHibernateDaoCatalogo" );
    HSArbolAcademicoClDao daoArbol = CommonDAO.getClArbolAcademicoDAO();
    private SelectItem[] cboArea;

    public SelectItem[] getCboArea() {
        return CommonWeb.getCboArbolAreaInf( 0, "" );
    }

    public SelectItem[] listarCatalogoGrupo( String cod_grupo, String descripcionInicio ) {
        SelectItem[] catalogo = null;
        try {
            List<TbCatalogo> listaPro = dao.seleccionarCatalogo( cod_grupo );
            catalogo = new SelectItem[ listaPro.size() + 1 ];
            catalogo[0] = new SelectItem( "000000", descripcionInicio );
            for ( int i = 0; i < (catalogo.length - 1); i++ ) {
                //System.out.println("el valor es -> "+listaPro.get(i).getCatCodigoGrupo() + listaPro.get(i).getCatCodigoElemento());
                catalogo[i + 1] = new SelectItem( listaPro.get( i ).getCatCodigoGrupo() + listaPro.get( i ).getCatCodigoElemento(), listaPro.get( i ).getCatDescripcionElemento() );
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return catalogo;
    }

    public SelectItem[] cboCursoxModulo( int arb_id, String tipo ) {
        SelectItem[] cboCurso = null;
        try {

            //List<ClArbolAcademico> lista = daoArbol.listarArbolAcademico(arb_id, tipo);
            List<ClArbolAcademico> lista = new ArrayList<ClArbolAcademico>();
            cboCurso = new SelectItem[ lista.size() + 1 ];
            cboCurso[0] = new SelectItem( "0", "[[--Seleccione--]])" );
            for ( int i = 0; i < (cboCurso.length - 1); i++ ) {
                cboCurso[i + 1] = new SelectItem( lista.get( i ).getArbId(), lista.get( i ).getArbDescripcion() );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return cboCurso;
    }
    public SelectItem[] cboSeccionesXCurso( int iCurId ){
        int iSize;
        ClSeccion clSec;
        SelectItem[] cboSecciones;
        List<ClSeccion> lstSecciones;
        
        try {
            lstSecciones = CommonDAO.getClSeccionDAO().listarTodasSeccionesXCurso( iCurId );
            iSize = lstSecciones.size();
            cboSecciones = new SelectItem[ iSize + 1 ];
            
            for ( int i = 0; i < iSize; i++ ) {
                clSec = lstSecciones.get( i );
                cboSecciones[i + 1] = new SelectItem( clSec.getSecId(), clSec.getSecNombre() );
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
            cboSecciones = new SelectItem[ 1 ];
        }
        cboSecciones[0] = new SelectItem( "0", "[Seleccione]" );
        
        return cboSecciones;
    }
    public SelectItem[] cboAnio() {
        SelectItem[] cboAnio;
        int anio = Calendar.getInstance().get( Calendar.YEAR );
        cboAnio = new SelectItem[ 15 ];
        cboAnio[0] = new SelectItem( 0, "[[--Seleccione--]]" );
        for ( int i = 0; i < (cboAnio.length - 1); i++ ) {
            cboAnio[i + 1] = new SelectItem( anio - i, String.valueOf( anio - i ) );
        }
        return cboAnio;
    }

    public SelectItem[] cboMes() {
        SelectItem[] cboMes;
        cboMes = new SelectItem[ 13 ];
        cboMes[0] = new SelectItem( 0, "[[--TODOS--]]" );
        cboMes[1] = new SelectItem( 1, "[[--ENERO--]]" );
        cboMes[2] = new SelectItem( 2, "[[--FEBRERO--]]" );
        cboMes[3] = new SelectItem( 3, "[[--MARZO--]]" );
        cboMes[4] = new SelectItem( 4, "[[--ABRIL--]]" );
        cboMes[5] = new SelectItem( 5, "[[--MAYO--]]" );
        cboMes[6] = new SelectItem( 6, "[[--JUNIO--]]" );
        cboMes[7] = new SelectItem( 7, "[[--JULIO--]]" );
        cboMes[8] = new SelectItem( 8, "[[--AGOSTO--]]" );
        cboMes[9] = new SelectItem( 9, "[[--SETIEMBRE--]]" );
        cboMes[10] = new SelectItem( 10, "[[--OCTUBRE--]]" );
        cboMes[11] = new SelectItem( 11, "[[--NOVIEMBRE--]]" );
        cboMes[12] = new SelectItem( 12, "[[--DICIEMBRE--]]" );
        return cboMes;
    }

    public SelectItem[] cboListarArea( int idPadre, String tipo ) {
        SelectItem[] listarArea;
        //List<ClArbolAcademico> lista=daoArbol.listarArbolAcademicoArea(idPadre, tipo);
        List<ClArbolAcademico> lista = new ArrayList<ClArbolAcademico>();
        listarArea = new SelectItem[ lista.size() + 1 ];
        listarArea[0] = new SelectItem( 0, "[[Seleccione]]" );
        for ( int i = 0; i < (listarArea.length - 1); i++ ) {
            listarArea[i + 1] = new SelectItem( lista.get( i ).getArbId(), lista.get( i ).getArbDescripcion() );
        }
        return listarArea;

    }

    public List<ClArbolAcademico> agregarHijo( int idPadre, String tipo, List<ClArbolAcademico> listaArbol ) {

        List<ClArbolAcademico> lista = CommonDAO.getClArbolAcademicoDAO().listarArbolPorPadre( idPadre );
        System.out.println( "el ultimo padre -> " + idPadre );
        for ( int i = 0; i < lista.size(); i++ ) {
            if ( lista.get( i ).getArbTipo().equals( tipo ) ) {
                listaArbol.add( lista.get( i ) );
            } else {
                agregarHijo( lista.get( i ).getArbId(), tipo, listaArbol );
            }
        }

        return listaArbol;
    }

    public SelectItem[] cboCentros() {
        int iIndex;
        int iSize;
        TbCatalogo catCent;
        List<TbCatalogo> lstCatCentros;
        SelectItem[] seCentros;
        try {
            lstCatCentros = CommonDAO.getTbCatalogoDAO().seleccionarCatalogo( "078" );
            iSize = lstCatCentros.size();
            iIndex = 0;
            seCentros = new SelectItem[ iSize ];
            for ( int i = 0; i < iSize; i++ ) {
                catCent = lstCatCentros.get( i );
                if ( !"004".equals( catCent.getCatCodigoElemento() ) ) {
                    seCentros[++iIndex] = new SelectItem( catCent.getCatCodigoGrupo() + catCent.getCatCodigoElemento(), catCent.getCatDescripcionElemento() );
                }
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
            seCentros = new SelectItem[ 1 ];
        }
        seCentros[ 0] = new SelectItem( "0", "[Seleccione]" );
        return seCentros;
    }
}
