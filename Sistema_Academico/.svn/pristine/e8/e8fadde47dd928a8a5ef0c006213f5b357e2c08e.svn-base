/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import javax.faces.component.UIParameter;
import javax.faces.event.ActionEvent;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSSistemaEvaluacionCLDAO;
import net.uch.cursoLibre.managedBeans.beans.BeanClSisEvaDetalle;
import net.uch.cursoLibre.managedBeans.beans.BeanClSisEvaluacion;
import net.uch.mapping.ClSisEvaDetalle;
import net.uch.mapping.ClSisEvaPersonalizado;
import net.uch.mapping.ClSisEvaluacion;
import net.uch.util.CommonDAO;

/**
 *
 * @author cesardl
 */
public class bSistemaEvaluacionCL {

    private int pos;
    private String p_descripcion;
    private BeanClSisEvaluacion sis_eva;
    private BeanClSisEvaDetalle sis_eva_det;
    private List<BeanClSisEvaluacion> sistemas_evaluacion;
    private List<BeanClSisEvaDetalle> n_sis_eva_detalles;
    private List<BeanClSisEvaDetalle> q_sis_eva_detalles;
    private List<ClSisEvaPersonalizado> lstClSisEvaPerPlant;
    private String oncomplete;

    /**
     * Creates a new instance of bSistemaEvaluacionCL
     */
    public bSistemaEvaluacionCL() {
        sis_eva_det = new BeanClSisEvaDetalle();
    }

    public List<ClSisEvaPersonalizado> getLstClSisEvaPerPlant() {
        return lstClSisEvaPerPlant;
    }

    public void setLstClSisEvaPerPlant( List<ClSisEvaPersonalizado> lstClSisEvaPerPlant ) {
        this.lstClSisEvaPerPlant = lstClSisEvaPerPlant;
    }

    public void nuevo( ActionEvent event ) {
        pos = 0;
        this.sis_eva = new BeanClSisEvaluacion();
        this.sis_eva_det = new BeanClSisEvaDetalle();
        this.n_sis_eva_detalles = new ArrayList<BeanClSisEvaDetalle>();
        this.q_sis_eva_detalles = new ArrayList<BeanClSisEvaDetalle>();
    }

    public void buscarSistemaEvaluacion( ActionEvent event ) {
        HSSistemaEvaluacionCLDAO daoSE =
                (HSSistemaEvaluacionCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLSistemaEvaluacion" );

        List<ClSisEvaluacion> l_tmp = daoSE.seleccionarSistemasEvaluacion( this.p_descripcion );
        this.sistemas_evaluacion = new ArrayList<BeanClSisEvaluacion>();
        for ( ClSisEvaluacion siseva : l_tmp ) {
            this.sistemas_evaluacion.add( new BeanClSisEvaluacion( siseva ) );
        }
    }

    public boolean edicionActivada() {
        for ( BeanClSisEvaDetalle bean : n_sis_eva_detalles ) {
            if ( bean.isEdit_active() ) {
                return true;
            }
        }
        return false;
    }

    public void guardarSisEvaluacion( ActionEvent event ) {
        this.setOncomplete( "" );
        if ( !this.sis_eva.esValido() ) {
            this.setOncomplete( "javascript:alert('Campos obligatorios.');" );
        } else if ( this.n_sis_eva_detalles.isEmpty() ) {
            this.setOncomplete( "javascript:alert('Ingrese al menos un detalle.');" );
        } else if ( edicionActivada() ) {
            this.setOncomplete( "javascript:alert('Termine de editar los Detalles.')" );
        } else {
            ClSisEvaluacion siseva = this.sis_eva.getClSisEvaluacion();
            if ( siseva.getSisevaId().intValue() == 0 ) {
                siseva.setSisevaId( null );
            }
            LinkedHashSet<ClSisEvaDetalle> detalles = new LinkedHashSet<ClSisEvaDetalle>();
            for ( BeanClSisEvaDetalle bDetalle : n_sis_eva_detalles ) {
                ClSisEvaDetalle siseva_det = bDetalle.getClSisEvaDetalle();
                siseva_det.setClSisEvaluacion( siseva );

                detalles.add( siseva_det );
            }
            siseva.setClSisEvaDetalles( detalles );

            HSSistemaEvaluacionCLDAO daoSE =
                    (HSSistemaEvaluacionCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLSistemaEvaluacion" );
            if ( !q_sis_eva_detalles.isEmpty() ) {
                List<Integer> siseva_det_ids = new ArrayList<Integer>();
                for ( BeanClSisEvaDetalle btmp : q_sis_eva_detalles ) {
                    if ( btmp.getSisevaDetalleId().intValue() != 0 ) {
                        siseva_det_ids.add( btmp.getSisevaDetalleId() );
                    }
                }
                daoSE.eliminarSistemasEvaluacionDetalle( siseva_det_ids );
            }

            daoSE.insertarActualizarSistemaEvaluacion( siseva );


            buscarSistemaEvaluacion( event );
            this.setOncomplete( "javascript:alert('Actualizacion satisfactoria.');"
                    + "Richfaces.hideModalPanel('mpUpdate');" );
        }
    }

    public void seleccionarSistemaEvaluacion( ActionEvent event ) {
        int id_siseva_tmp = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "id_siseva_tmp" )).getValue().toString() );
        HSSistemaEvaluacionCLDAO daoSE =
                (HSSistemaEvaluacionCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLSistemaEvaluacion" );
        ClSisEvaluacion siseva = daoSE.seleccionarSistemaEvaluacion( id_siseva_tmp );
        pos = 0;
        this.sis_eva = new BeanClSisEvaluacion( siseva );
        this.sis_eva_det = new BeanClSisEvaDetalle();
        this.n_sis_eva_detalles = new ArrayList<BeanClSisEvaDetalle>();
        Iterator<ClSisEvaDetalle> it = siseva.getClSisEvaDetalles().iterator();
        while ( it.hasNext() ) {
            ClSisEvaDetalle tmp_sisevadet = it.next();
            BeanClSisEvaDetalle tmp = new BeanClSisEvaDetalle( tmp_sisevadet );
            tmp.setPosicion( pos );
            n_sis_eva_detalles.add( tmp );
            pos++;
        }
        this.q_sis_eva_detalles = new ArrayList<BeanClSisEvaDetalle>();
    }

    public void seleccionarSistemaEvaluacionDet( ActionEvent event ){
        int iIdSisEvaDet;
        ClSisEvaDetalle sed;
        iIdSisEvaDet = -1;
        try{
            iIdSisEvaDet = Integer.parseInt(((UIParameter)event.getComponent().findComponent( "id_sisevadet_tmp" )).getValue().toString());
            sed = CommonDAO.getClSistemaEvaluacionDAO().seleccionarSisEvaDetetallePorId( iIdSisEvaDet );
            if ( sed != null ) {
                this.sis_eva_det = new BeanClSisEvaDetalle();
                this.sis_eva_det.setAux_codigo( sed.getSisevaDetalleCodigo() );
                this.sis_eva_det.setAux_nombre( sed.getSisevaDetalleNombre() );
                this.sis_eva_det.setAux_peso( sed.getSisevaDetallePeso() );
            }
        }catch(Exception ex){
            System.out.println( "ID SED : " + iIdSisEvaDet );
            ex.printStackTrace();
        }
    }
    
    public void mostrarPlantilla( ActionEvent event ) {
        int id_siseva_tmp = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "id_siseva_tmp" )).getValue().toString() );
        if ( id_siseva_tmp != 0 ) {
            lstClSisEvaPerPlant = CommonDAO.getClSistemaEvaluacionDAO().listarSisEvaPerPlantilla( id_siseva_tmp, 0 );
        } else {
            lstClSisEvaPerPlant = new ArrayList<ClSisEvaPersonalizado>();
        }
        oncomplete = "Richfaces.showModalPanel('mpClSisEvaPerPlantilla')";
    }

    public void mostrarPlantillaDetalle( ActionEvent event ) {
        int id_siseva_tmp = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "id_siseva_tmp2" )).getValue().toString() );
        int id_sisevadet_tmp = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "id_sisevadet_tmp" )).getValue().toString() );
        if ( id_siseva_tmp != 0 && id_sisevadet_tmp != 0 ) {
            lstClSisEvaPerPlant = CommonDAO.getClSistemaEvaluacionDAO().listarSisEvaPerPlantilla( id_siseva_tmp, id_sisevadet_tmp );
        } else {
            lstClSisEvaPerPlant = new ArrayList<ClSisEvaPersonalizado>();
        }
        oncomplete = "Richfaces.showModalPanel('mpClSisEvaPerPlantilla')";
    }

    public void mostrarDetalle( ActionEvent event ) {
        int id_siseva_tmp = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "id_siseva_tmp" )).getValue().toString() );
        if ( !this.sistemas_evaluacion.isEmpty() ) {
            for ( BeanClSisEvaluacion bSiseva : this.sistemas_evaluacion ) {
                if ( id_siseva_tmp == bSiseva.getSisevaId() ) {
                    if ( bSiseva.isVerDetalle() ) {
                        bSiseva.setVerDetalle( false );
                        bSiseva.setM_imagen_mostrar( "/Imagenes/actions/down.png" );
                        bSiseva.setM_texto_mostrar( "Mostrar Detalle" );
                    } else {
                        bSiseva.setVerDetalle( true );
                        bSiseva.setM_imagen_mostrar( "/Imagenes/actions/up.png" );
                        bSiseva.setM_texto_mostrar( "Ocultar Detalle" );
                    }
                    break;
                }
            }
        }
    }

    public void quitarSisEvaPerPlant( ActionEvent event ) {
        int i = 0;
        int iIndex = -1;
        int iIdSisEvaPerPlant = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "id_siseva_per_plant" )).getValue().toString() );
        if ( 1 == CommonDAO.getClSistemaEvaluacionDAO().eliminarSistemaEvaluacionPerPlant( iIdSisEvaPerPlant ) ) {
            for ( ClSisEvaPersonalizado sepp : lstClSisEvaPerPlant ) {
                if ( iIdSisEvaPerPlant == sepp.getSisevaPerId().intValue() ) {
                    iIndex = i;
                    break;
                }
                i++;
            }
            if ( iIndex >= 0 ) {
                lstClSisEvaPerPlant.remove( iIndex );
            }
            oncomplete = "alert('Se elimino correctamente')";
        } else {
            oncomplete = "alert('Error al eliminar')";
        }
    }

    public void agregarDetalle( ActionEvent event ) {
        this.setOncomplete( "" );
        if ( this.sis_eva_det.esValido() ) {
            this.sis_eva_det.setPosicion( pos++ );
            this.sis_eva_det.completarDatos();
            this.n_sis_eva_detalles.add( this.sis_eva_det );

            this.sis_eva_det = new BeanClSisEvaDetalle();
        } else {
            this.setOncomplete( "javascript:alert('Campos obligatorios.')" );
        }
    }

    public void quitarDetalle( ActionEvent event ) {
        int p_pos_detalle = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "p_pos_detalle" )).getValue().toString() );
        this.q_sis_eva_detalles.add( this.n_sis_eva_detalles.remove( p_pos_detalle ) );
        for ( int i = 0; i < n_sis_eva_detalles.size(); i++ ) {
            n_sis_eva_detalles.get( i ).setPosicion( i );
        }
        pos--;
    }

    public String getP_descripcion() {
        return p_descripcion;
    }

    public void setP_descripcion( String p_descripcion ) {
        this.p_descripcion = p_descripcion;
    }

    public BeanClSisEvaluacion getSis_eva() {
        return sis_eva;
    }

    public void setSis_eva( BeanClSisEvaluacion sis_eva ) {
        this.sis_eva = sis_eva;
    }

    public BeanClSisEvaDetalle getSis_eva_det() {
        return sis_eva_det;
    }

    public void setSis_eva_det( BeanClSisEvaDetalle sis_eva_det ) {
        this.sis_eva_det = sis_eva_det;
    }

    public List<BeanClSisEvaluacion> getSistemas_evaluacion() {
        return sistemas_evaluacion;
    }

    public void setSistemas_evaluacion( List<BeanClSisEvaluacion> sistemas_evaluacion ) {
        this.sistemas_evaluacion = sistemas_evaluacion;
    }

    public List<BeanClSisEvaDetalle> getN_sis_eva_detalles() {
        return n_sis_eva_detalles;
    }

    public void setN_sis_eva_detalles( List<BeanClSisEvaDetalle> n_sis_eva_detalles ) {
        this.n_sis_eva_detalles = n_sis_eva_detalles;
    }

    public List<BeanClSisEvaDetalle> getQ_sis_eva_detalles() {
        return q_sis_eva_detalles;
    }

    public void setQ_sis_eva_detalles( List<BeanClSisEvaDetalle> q_sis_eva_detalles ) {
        this.q_sis_eva_detalles = q_sis_eva_detalles;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete( String oncomplete ) {
        this.oncomplete = oncomplete;
    }

    public void eliminarSistemaEvaluacion( ActionEvent event ) {
        int iIdSisEva;
        int iRet;

        oncomplete = "alert('Error al eliminar.');";
        try {
            iIdSisEva = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "id_siseva_tmp" )).getValue().toString() );
        } catch ( Exception ex ) {
            iIdSisEva = 0;
            ex.printStackTrace();
        }
        if ( iIdSisEva != 0 ) {
            try {
                iRet = CommonDAO.getClSistemaEvaluacionDAO().eliminarSistemaEvaluacion( iIdSisEva );
                if ( iRet == 1 ) {
                    oncomplete = "alert('Se eliminó correctamente');";
                    buscarSistemaEvaluacion( event );
                }
            } catch ( Exception ex ) {
                ex.printStackTrace();
            }
        }
    }
}
