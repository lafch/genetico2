/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.utilAdministrativo;

import java.sql.SQLException;
import java.util.List;
import javax.faces.model.SelectItem;
import net.uch.commonService.ServiceFinder;
import net.uch.mapping.AcLocal;
import net.uch.mapping.TbCatalogo;
import net.uch.mapping.TbUsuario;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSLocalDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSUsuarioDAO;
import net.uch.util.CommonDAO;

/**
 *
 * @author Toshiba
 */
public class MetodosAdm {

    public SelectItem[] getCboUsuario( String textoDescriptivo ) {
        SelectItem[] cboUsuario;
        HSUsuarioDAO dao = CommonDAO.getTbUsuarioDAO();
        List<TbUsuario> lista = dao.listarUsuarioPorRoles( 1 );
        cboUsuario = new SelectItem[ lista.size() + 1 ];
        cboUsuario[0] = new SelectItem( "0", textoDescriptivo );
        for ( int i = 0; i < (cboUsuario.length - 1); i++ ) {
            cboUsuario[i + 1] = new SelectItem( lista.get( i ).getId(), lista.get( i ).getUsuUsuario() );
        }

        return cboUsuario;
    }

    public SelectItem[] listarSedes( String textoDescriptivo ) throws SQLException {
        SelectItem[] sede;
        HSLocalDAO dao = CommonDAO.getAcLocalDAO();
        List<AcLocal> lista = dao.seleccionarLocal( "" );
        sede = new SelectItem[ lista.size() + 1 ];
        sede[0] = new SelectItem( 0, "[" + textoDescriptivo + "]" );
        for ( int i = 0; i < sede.length - 1; i++ ) {
            sede[i + 1] = new SelectItem( lista.get( i ).getId(), lista.get( i ).getLocDes() );
        }

        return sede;
    }

    public SelectItem[] listarCentros( String textoDescriptivo ) throws SQLException {
        int iIndx;
        int iSize;
        TbCatalogo catCentro;
        List<TbCatalogo> lstCatCentros;
        SelectItem[] asCentros;
        HSCatalogoDAO catDAO = CommonDAO.getTbCatalogoDAO();
        lstCatCentros = catDAO.seleccionarCatalogo( "078" );
        iSize = lstCatCentros.size();
        asCentros = new SelectItem[ iSize ];
        asCentros[0] = new SelectItem( "0", textoDescriptivo );
        iIndx = 0;
        for ( int i = 0; i < iSize; i++ ) {
            catCentro = lstCatCentros.get( i );
            if ( !"004".equals( catCentro.getCatCodigoElemento() ) ) {
                asCentros[++iIndx] = new SelectItem( catCentro.getCatCodigoGrupo() + catCentro.getCatCodigoElemento(), catCentro.getCatDescripcionElemento() );
            }
        }

        return asCentros;
    }
}
