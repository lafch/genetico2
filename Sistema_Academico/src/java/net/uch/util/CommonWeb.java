/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.util;

import java.util.List;
import javax.faces.component.UIParameter;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import net.uch.mapping.ClArbolAcademico;
import net.uch.tablaSistema.managedBeans.bUsuario;

/**
 *
 * @author USUARIO
 */
public class CommonWeb {

    public static String getParamFromUIFacesContext( ActionEvent event, String sNomParam ) {
        String sParam;
        try {
//            sParam = ((UIParameter) event.getComponent().findComponent( sNomParam )).getValue().toString();
            FacesContext context = FacesContext.getCurrentInstance();
            sParam = context.getExternalContext().getRequestParameterMap().get( sNomParam );

        } catch ( Exception ex ) {
            sParam = "";
            ex.printStackTrace();
        }

        return sParam;
    }

    public static String getParamFromUIParameterUI( ActionEvent event, String sNomParam ) {
        String sParam;
        try {
            sParam = ((UIParameter) event.getComponent().findComponent( sNomParam )).getValue().toString();
        } catch ( Exception ex ) {
            sParam = "";
            ex.printStackTrace();
        }

        return sParam;
    }

    public static SelectItem[] getCboArbolAreaInf( int iArbIdPadre, String sCentroId ) {
        SelectItem[] cboArea;
        List<ClArbolAcademico> lista;
        if ( sCentroId.isEmpty() ) {
            lista = CommonDAO.getClArbolAcademicoDAO().listarArbolPorPadre( 0 );
        } else {
            lista = CommonDAO.getClArbolAcademicoDAO().AreasXInstitucion( sCentroId );
        }
        cboArea = new SelectItem[ lista.size() + 1 ];
        cboArea[0] = new SelectItem( "0", "[Seleccionar área]" );
        for ( int i = 0; i < (cboArea.length - 1); i++ ) {
            cboArea[i + 1] = new SelectItem( lista.get( i ).getArbId(), lista.get( i ).getArbDescripcion() );
        }
        return cboArea;
    }

    public static SelectItem[] getCboArbolModInf( int iArbIdPadre ) {
        SelectItem[] cboArbol;

        cboArbol = new SelectItem[ 1 ];
        cboArbol[0] = new SelectItem( "0", "[Seleccione]" );

        if ( iArbIdPadre != 0 ) {
            List<ClArbolAcademico> lstModulo = CommonDAO.getClArbolAcademicoDAO().listarArbolPorPadre( iArbIdPadre );
            if ( lstModulo != null && !lstModulo.isEmpty() ) {


                cboArbol = new SelectItem[ lstModulo.size() + 1 ];
                cboArbol[0] = new SelectItem( "0", "[Seleccione]" );
                for ( int i = 0; i < (cboArbol.length - 1); i++ ) {
                    cboArbol[i + 1] = new SelectItem( lstModulo.get( i ).getArbId(), lstModulo.get( i ).getArbDescripcion() );
                }
            }
        }
        return cboArbol;
    }

    public static SelectItem[] getCboArbolCursoInf( int iArbIdPadre ) {
        SelectItem[] cboArbol;

        cboArbol = new SelectItem[ 1 ];
        cboArbol[0] = new SelectItem( "0", "[Seleccione]" );

        if ( iArbIdPadre != 0 ) {
            List<ClArbolAcademico> lstModulo = CommonDAO.getClArbolAcademicoDAO().listarArbolPorPadre( iArbIdPadre );
            if ( lstModulo != null && !lstModulo.isEmpty() ) {

                cboArbol = new SelectItem[ lstModulo.size() + 2 ];
                cboArbol[0] = new SelectItem( "0", "[Seleccione]" );
                cboArbol[1] = new SelectItem( "-1", "NO DETERMINADO" );
                for ( int i = 0; i < (cboArbol.length - 2); i++ ) {
                    cboArbol[i + 2] = new SelectItem( lstModulo.get( i ).getArbId(), lstModulo.get( i ).getArbDescripcion() );
                }
            }
        }
        return cboArbol;
    }

    public static double calcularPromedioSimple( Double[] adNotas ) {
        double dPromSimp = 0.0;
        double dSumNot = 0;

        if ( adNotas != null ) {
            for ( Double dNota : adNotas ) {
                dSumNot += dNota;
            }
            dPromSimp = dSumNot / (double) adNotas.length;
        }

        return dPromSimp;
    }

    public static String parseObjToString( Object objStr ) {
        String sRet;
        try {
            if ( objStr != null ) {
                sRet = objStr.toString();
            } else {
                sRet = ConstantesWeb.COD_PARSE_STR_ERROR;
            }
        } catch ( Exception ex ) {
            System.out.println( "NO SE PUEDE PARSEAR : " + objStr );
            ex.printStackTrace();
            sRet = ConstantesWeb.COD_PARSE_STR_ERROR;
        }
        return sRet;
    }
    
    public static String parseObjToString2( Object objStr ) {
        String sRet;
        try {
            if ( objStr != null ) {
                sRet = objStr.toString();
            } else {
                sRet = ConstantesWeb.COD_PARSE_STR_ERROR2;
            }
        } catch ( Exception ex ) {
            System.out.println( "NO SE PUEDE PARSEAR : " + objStr );
            ex.printStackTrace();
            sRet = ConstantesWeb.COD_PARSE_STR_ERROR2;
        }
        return sRet;
    }

    public static int parseObjToInt( Object objInt ) {
        int iRet;
        try {
            iRet = Integer.parseInt( parseObjToString( objInt ) );
        } catch ( Exception ex ) {
            System.out.println( "NO SE PUEDE PARSEAR : " + objInt );
            ex.printStackTrace();
            iRet = ConstantesWeb.COD_PARSE_INT_ERROR;
        }
        return iRet;
    }
    
    public static double parseObjToDouble( Object objInt ) {
        double iRet;
        try {
            iRet = Double.parseDouble( parseObjToString( objInt ) );
        } catch ( Exception ex ) {
            System.out.println( "NO SE PUEDE PARSEAR : " + objInt );
            ex.printStackTrace();
            iRet = ConstantesWeb.COD_PARSE_INT_ERROR;
        }
        return iRet;
    }

    public static bUsuario traerUsuarioLogeado() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession) context.getSession( false );
        bUsuario usu = (bUsuario) session1.getAttribute( "usuario" );
        return usu;
    }

    public static String traerNombreMes( int iMesPeriodo ) {
        String[] asMeses = { "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SETIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE" };
        try {
            return asMeses[iMesPeriodo];
        } catch ( Exception ex ) {
            return "";
        }
    }
}
