/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans.beans;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSAlumnoTarifaCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSConsultaPublicoDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSEstructuraPagoDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSHorarioDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSMatriculaCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSModuloDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSPublicoConsultaCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSSeccionCLDAO;
import net.uch.cursoLibre.managedBeans.bMatriculaCL;
import net.uch.mapping.ClAlumno;
import net.uch.mapping.ClAlumnoTarifa;
import net.uch.mapping.ClEstructuraPagosDetalle;
import net.uch.mapping.ClMatricula;
import net.uch.mapping.ClMatriculaTaller;
import net.uch.mapping.ClSeccion;
import net.uch.mapping.TbCatalogo;
import net.uch.mapping.TbDistrito;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.tablaSistema.hibernateSpringDao.HSUbigeoDAO;
import net.uch.tablaSistema.managedBeans.bUsuario;
import net.uch.util.CommonDAO;

/**
 *
 * @author richard
 */
public class MetodosCL {

    HSUbigeoDAO ubi = CommonDAO.getUbigeoDAO();
    HSCatalogoDAO dao = CommonDAO.getTbCatalogoDAO();

    public SelectItem[] listarDepartamento() {
        SelectItem[] departamento = null;
        try {
            List<TbDistrito> listaDepa = ubi.seleccionarDepartamento();
            departamento = new SelectItem[ listaDepa.size()+1 ];
            departamento[0] = new SelectItem( "000000", "[Seleccione]" );
            for ( int i = 0; i < (departamento.length-1); i++ ) {
                departamento[i+1] = new SelectItem( listaDepa.get( i ).getId(), listaDepa.get( i ).getDisDes() );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return departamento;
    }

    public SelectItem[] listarProvincia( String cod_depa ) {
        SelectItem[] provincia = null;
        try {


            List<TbDistrito> lista = ubi.seleccionarProvincia( cod_depa );
            if ( !lista.isEmpty() ) {
                provincia = new SelectItem[ lista.size()+1 ];
                provincia[0] = new SelectItem( "000000", "[Seleccione]" );
                for ( int i = 0; i < (provincia.length-1); i++ ) {
                    provincia[i+1] = new SelectItem( lista.get( i ).getId(), lista.get( i ).getDisDes() );
                }
            } else {
                provincia = new SelectItem[ 1 ];
                provincia[0] = new SelectItem( "0000", "[Seleccione]" );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return provincia;
    }

    public SelectItem[] listarDistrito( String cod_depa, String cod_prov ) {
        SelectItem[] distrito = null;
        try {
            List<TbDistrito> lista = ubi.seleccionarDistrito( cod_depa, cod_prov );
            if ( !lista.isEmpty() ) {
                distrito = new SelectItem[ lista.size()+1 ];
                distrito[0] = new SelectItem( "000000", "[Seleccione]" );
                for ( int i = 0; i < (distrito.length-1); i++ ) {
                    distrito[i+1] = new SelectItem( lista.get( i ).getId(), lista.get( i ).getDisDes() );
                }
            } else {
                distrito = new SelectItem[ 1 ];
                distrito[0] = new SelectItem( "000000", "[Seleccione]" );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return distrito;
    }

    public SelectItem[] listarCatalogoGrupo( String cod_grupo ) {
        SelectItem[] catalogo = null;
        try {
            List<TbCatalogo> listaPro = dao.seleccionarCatalogo( cod_grupo );
            catalogo = new SelectItem[ listaPro.size() ];
            for ( int i = 0; i < catalogo.length; i++ ) {
                catalogo[i] = new SelectItem( listaPro.get( i ).getCatCodigoGrupo() + listaPro.get( i ).getCatCodigoElemento(), listaPro.get( i ).getCatDescripcionElemento() );
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }
        return catalogo;
    }

    public String matricularCursosLibres( int alu_id, List<BeanCLMatricula> secciones, List<Integer> i_detalles, HttpSession session1, String mensajeDeuda, int consultaId ) throws Exception {
        String mensaje = "";

        if ( mensajeDeuda.trim().length() == 0 ) {  // verify si tiene emensaje de Deuda
            if ( alu_id != 0 ) {
                HSHorarioDAO daoH = CommonDAO.getClHorarioDAO();
                if ( secciones.size() > 0 ) {
                    int flag = 0;
                    for ( int i = 0; i < secciones.size(); i++ ) {
                        if ( secciones.get( i ).isI_agregar() ) {
                            flag++;
                        }
                    }
                    if ( flag > 0 ) {
                        int flag2 = 0;
                        for ( int i = 0; i < secciones.size(); i++ ) {
                            if ( secciones.get( i ).isI_agregar() ) {
                                if ( secciones.get( i ).getI_sec_vac_max() <= daoH.matriculadosSeccion( secciones.get( i ).getI_sec_id() ).size() ) {
                                    flag2++;
                                }
                            }
                        }
                        if ( flag2 == 0 ) {
                            HSMatriculaCLDAO dao = CommonDAO.getClMatriculaDAO();

                            bUsuario usu = (bUsuario) session1.getAttribute( "usuario" );

                            ClMatricula matricula = new ClMatricula();

                            ClAlumno alu = new ClAlumno( alu_id );
                            matricula.setClAlumno( alu );

                            matricula.setMatCodigo( "MAT-SA" );
                            matricula.setMatTipo( "022005" );
                            matricula.setMatActivo( "1" );
                            matricula.setUsuId( usu.getId_usu() );

                            Date fec = new Date();
                            Timestamp time = new Timestamp( fec.getTime() );
                            matricula.setMatFecha( time );

                            Set<ClMatriculaTaller> cursos = new LinkedHashSet<ClMatriculaTaller>();

                            List<ClAlumnoTarifa> alumnosTarifa = new ArrayList<ClAlumnoTarifa>();
                            boolean matriculado = false;
                            String seccion = "";

                            for ( int i = 0; i < secciones.size(); i++ ) {
                                if ( secciones.get( i ).isI_agregar() ) {
                                    // if(dao.estaMatriculado(alu_id, alu_id)==false){
                                    matriculado = dao.estaMatriculado( alu_id, secciones.get( i ).getI_sec_id() );
                                    seccion = secciones.get( i ).getI_seccion();

                                    //secciones
                                    ClMatriculaTaller mattal = new ClMatriculaTaller();
                                    mattal.setClMatricula( matricula );
                                    mattal.setMattalActivo( "1" );
                                    mattal.setMattalEstado( "" );
                                    mattal.setMattalObs( "" );

                                    mattal.setClSeccion( new ClSeccion( secciones.get( i ).getI_sec_id() ) );

                                    cursos.add( mattal );

                                    for ( Integer estpagdet_id : i_detalles ) {
                                        ClAlumnoTarifa alumnoTarifa = new ClAlumnoTarifa();
                                        if ( estpagdet_id == secciones.get( i ).getCl_estpagdet_id() ) {
                                            alumnoTarifa.setClEstructuraPagosDetalle( new ClEstructuraPagosDetalle( estpagdet_id ) );
                                            alumnoTarifa.setClAlumno( alu );
                                            alumnoTarifa.setAlutarMonto( secciones.get( i ).getI_monto_pagar() );
                                            alumnoTarifa.setConpagId( secciones.get( i ).getCl_conpag_id() );
                                            alumnoTarifa.setAlutarFechaPago( secciones.get( i ).getCl_fecha_pago() );
                                            alumnoTarifa.setAlutarFechaProrroga( secciones.get( i ).getCl_fecha_prorroga() );
                                            alumnoTarifa.setAlutarEstado( "0" );
                                            alumnoTarifa.setAlutarActivo( "1" );
                                            alumnoTarifa.setAlutarAluTipo( secciones.get( i ).getCl_alutar_tipo() );
                                            alumnoTarifa.setSecId( secciones.get( i ).getI_sec_id() );
                                            alumnoTarifa.setAlutarAluTipo( "014003" );
                                            alumnoTarifa.setAlutarObservacionMonto( secciones.get( i ).getCl_observacion_monto() );
                                            alumnoTarifa.setUsuId( secciones.get( i ).getUsu_id() );
                                            alumnosTarifa.add( alumnoTarifa );
                                        }
                                    }
                                    // } //fin de la verificacion ps
                                }
                            }
                            System.out.println( "el valor de matriculado es ->" + matriculado );
                            if ( !matriculado ) {
                                matricula.setClMatriculaTallers( cursos );
                                dao.insertarMatricula( matricula );

                                HSAlumnoTarifaCLDAO daoAlutar = CommonDAO.getClAlumnoTarifaDAO();
                                for ( int i = 0; i < alumnosTarifa.size(); i++ ) {
                                    alumnosTarifa.get( i ).setMatId( matricula.getMatId() );
                                    daoAlutar.generarAlumnoTarifa( alumnosTarifa.get( i ) );
                                }
                                HSPublicoConsultaCLDAO daoC = CommonDAO.getClPublicoConsultaDAO();
                                daoC.modificarMatricula( consultaId );
                                mensaje = "javascript:alert('Matrícula Satisfactoria.');Richfaces.hideModalPanel('mpMatricula')";

                            } else {
                                mensaje = "javascript:alert('Ya es matriculado en la seccion: " + seccion + ".');";
                            }
                        } else {
                            mensaje = "javascript:alert('Una de las secciones ya llegó al máximo de vacantes. Es necesario generar una nueva sección.')";
                        }
                    } else {
                        mensaje = "javascript:alert('Debe seleccionar cursos para proceder a Matricular')";
                    }
                }// Fin del IF de las secciones
            } else {
                mensaje = "javascript:alert('Seleccione un alumno')";
            }

        } else {
            mensaje = "javascript:alert('No puede matricular a este ALumno, Tiene deudas pendientes...!')";
        }
        return mensaje;
    }

    public String matricularCursoLibre( int alu_id, List<BeanCLMatricula> secciones, List<ClEstructuraPagosDetalle> lstEstPagDet, HttpSession session1, String mensajeDeuda, int consultaId, Date fecha_inicio, int dia_cobrar, int secId, int jsp ) throws Exception {
        int iFlag;
        String mensaje = "";
        bUsuario usu;
        Calendar fecha = Calendar.getInstance();
        ClAlumno alu;
        ClEstructuraPagosDetalle estr;
        ClMatricula clMat;
        HSHorarioDAO horarioDAO;
        HSMatriculaCLDAO matCLDAO;
        int IdPadre ;

        List<ClAlumnoTarifa> tarifa = new ArrayList<ClAlumnoTarifa>();
        int contador = 0;
        int flag_est = 0;
        System.out.println( "lista tamaño est_det: " + lstEstPagDet.size() );
        /*
        for ( int i = 0; i < lstEstPagDet.size(); i++ ) {
            if ( lstEstPagDet.get( i ).isEst_verificar() ) {
                flag_est++;
                break;
            }
        }
        if ( lstEstPagDet.size() > 0 ) {
        */
        
        
        for ( int i = 0; i < lstEstPagDet.size(); i++ ) {
            
                flag_est++;
                break;
            
        }
        if ( lstEstPagDet.size() == 1 ) {
            if ( flag_est > 0 ) {
                //int flag_est = 0;
                for ( int i = 0; i < lstEstPagDet.size(); i++ ) {
                    /*
                     * if (lista.get(i).isEst_verificar()) { flag_est++; }
                     */

                    if ( !lstEstPagDet.isEmpty()) {
                        ClAlumnoTarifa tari = new ClAlumnoTarifa();
                        alu = new ClAlumno();
                        estr = new ClEstructuraPagosDetalle();
                        
                        tari.setAlutarActivo( "1" );
                        tari.setAlutarAluTipo( "014003" );
                        tari.setAlutarEstado( "0" );
                        tari.setAlutarFechaPago( new Date() );
                        //System.out.println("fecha inicios -> " + fecha_inicio + " ---dia -> " + fecha_inicio.getDay());
                        //System.out.println("el aumento de dias es -> " + dia_cobrar * (i + 1));
                        fecha.setTime( fecha_inicio );
                        fecha.add( fecha.DAY_OF_MONTH, (dia_cobrar * (contador)) );
                        //fecha.add(. , (this.getW_diaCobrar()*(i+1

                        //Date prorroga=fecha;
                        tari.setAlutarFechaProrroga( fecha.getTime() );
                        tari.setAlutarMonto( lstEstPagDet.get( i ).getEstpagdetMonto() );
                        tari.setAlutarMora( 0f );
                        tari.setAlutarObservacionMonto( "" );
                        alu.setAluId( alu_id );
                        estr.setEstpagdetId( lstEstPagDet.get( i ).getEstpagdetId() );
                        tari.setClAlumno( alu );
                        tari.setClEstructuraPagosDetalle( estr );
                        tari.setConpagId( lstEstPagDet.get( i ).getAdConceptoPago().getId() );
                        //tari.setModId(mod_id);
                        tari.setSecId( secciones.get( 0 ).getI_sec_id() );
                        tarifa.add( tari );
                        contador++;
                    }
                }

                if ( mensajeDeuda.trim().length() == 0 ) {  // verify si tiene emensaje de Deuda
                    if ( alu_id != 0 ) {
//                        HSHorarioDAO daoH = (HSHorarioDAO) ServiceFinder.findBean( "SpringHibernateDaoCLHorario" );
                        horarioDAO = CommonDAO.getClHorarioDAO();
                        if ( secciones.size() > 0 ) {
                            iFlag = 0;
                            for ( int i = 0; i < secciones.size(); i++ ) {
                                if ( secciones.get( i ).isI_agregar() ) {
                                    iFlag++;
                                    break;
                                }
                            }
                            if ( iFlag == 1 ) {
                                int flag2 = 0;
                                for ( int i = 0; i < secciones.size(); i++ ) {
                                    if ( secciones.get( i ).isI_agregar() ) {
                                        if ( secciones.get( i ).getI_sec_vac_max() <= horarioDAO.matriculadosSeccion( secciones.get( i ).getI_sec_id() ).size() ) {
                                            flag2++;
                                            break;
                                        }
                                    }
                                }
                                if ( flag2 == 0 ) {
                                    matCLDAO = CommonDAO.getClMatriculaDAO();

                                    usu = (bUsuario) session1.getAttribute( "usuario" );

                                    clMat = new ClMatricula();
                                    alu = new ClAlumno( alu_id );

                                    clMat.setClAlumno( alu );

                                    clMat.setMatCodigo( "MAT-SA" );
                                    clMat.setMatTipo( "022005" );
                                    clMat.setMatActivo( "1" );
                                    clMat.setUsuId( usu.getId_usu() );

                                    Date fec = new Date();
                                    Timestamp time = new Timestamp( fec.getTime() );
                                    clMat.setMatFecha( time );

                                    Set<ClMatriculaTaller> cursos = new LinkedHashSet<ClMatriculaTaller>();
                                    boolean matriculado = false;
                                    String seccion = "";
                                    int sec_id = 0;

                                    for ( int i = 0; i < secciones.size(); i++ ) {
                                        if ( secciones.get( i ).isI_agregar() ) {
                                            // if(dao.estaMatriculado(alu_id, alu_id)==false){
                                            matriculado = matCLDAO.estaMatriculado( alu_id, secciones.get( i ).getI_sec_id() );
                                            seccion = secciones.get( i ).getI_seccion();
                                            sec_id = secciones.get( i ).getI_sec_id();
                                            //secciones
                                            ClMatriculaTaller mattal = new ClMatriculaTaller();
                                            mattal.setClMatricula( clMat );
                                            mattal.setMattalActivo( "1" );
                                            mattal.setMattalEstado( "1" );
                                            mattal.setMattalObs( "" );

                                            mattal.setClSeccion( new ClSeccion( secciones.get( i ).getI_sec_id() ) );


                                            cursos.add( mattal );

                                        }
                                    }
                                    System.out.println( "el valor de matriculado es ->" + matriculado );
                                    if ( !matriculado ) {
                                        clMat.setClMatriculaTallers( cursos );
                                        matCLDAO.insertarMatricula( clMat );
                                        IdPadre = clMat.getMatId();
                                        HSAlumnoTarifaCLDAO daoAlutar = CommonDAO.getClAlumnoTarifaDAO();
                                        for ( int i = 0; i < tarifa.size(); i++ ) {
                                            tarifa.get( i ).setUsuId( usu.getId_usu() );
                                            tarifa.get( i ).setMatId( clMat.getMatId() );
                                            tarifa.get( i ).setSecId( sec_id );
                                            daoAlutar.generarAlumnoTarifa( tarifa.get( i ) );
                                        }
                                        HSConsultaPublicoDAO daoCon = CommonDAO.getClConsultaPublicoDAO();
                                        daoCon.modificarCampoMatricula( consultaId,IdPadre );
                                        
                                        
                                        
                                        if ( jsp == 1 ) {
                                            mensaje = "javascript:alert('Matrícula Satisfactoria.');Richfaces.hideModalPanel('mpRegistroMatricula');";
                                        } else if ( jsp == 2 ) {
                                            mensaje = "javascript:alert('Matrícula Satisfactoria.');Richfaces.hideModalPanel('mpMatricula');";
                                        }
                                    } else {
                                        mensaje = "javascript:alert('Ya es matriculado en la seccion: " + seccion + ".');";
                                    }
                                } else {
                                    mensaje = "javascript:alert('La seccion ya llegó al máximo de vacantes. Es necesario generar una nueva sección.')";
                                }
                            } else {
                                mensaje = "javascript:alert('Debe seleccionar 1 Curso para Matricular')";
                            }
                        }// Fin del IF de las secciones
                    } else {
                        mensaje = "javascript:alert('Seleccione un alumno')";
                    }

                } else {
                    mensaje = "javascript:alert('No puede matricular a este ALumno, Tiene deudas pendientes...!')";
                    //System.out.println("NO TIENE MENSAJE DEUDA -->"+this.getDeu_mensaje());
                    
                }
                System.out.println( "seleccionados est_det: " + flag_est );
            } else {
                //mensaje = "javascript:alert('Debe seleccionar minimo 1 monto de pago!')";
                mensaje = "javascript:alert('no se cuenta con un estructura asignada para dicha seccion!')";
            }
        } else {
            mensaje = "javascript:alert('Debe seleccionar una estructura de pago')";
        }
        return mensaje;
    }

    public Object[] mostrarCursosLibres( int mod_id, int estpag_id, String mod_descri, List<bMatriculaCL> secciones, List<Integer> i_detalles ) throws Exception {
        Object[] dato = new Object[ 3 ];
        dato[0] = ""; //mensaje error
        dato[1] = true; //mensaje vercurso
        dato[3] = true; //mensaje mensaje

        HSHorarioDAO daoH = CommonDAO.getClHorarioDAO();
//        HSEstructuraPagoDAO daoEP = (HSEstructuraPagoDAO) ServiceFinder.findBean( "SpringHibernateDaoCLEstructuraPago" );
        HSEstructuraPagoDAO daoEP = CommonDAO.getClEstructuraPagoDAO();
        HSModuloDAO daoM = (HSModuloDAO) ServiceFinder.findBean( "SpringHibernateDaoCLModulo" );
        HSSeccionCLDAO daoS = CommonDAO.getClSeccionDAO();

        List<ClSeccion> lista_sec_modulo = daoS.listarSeccionesXModulo( mod_id );
        if ( !lista_sec_modulo.isEmpty() ) {
            List<ClEstructuraPagosDetalle> detalles = daoEP.seleccionarDetalle( estpag_id );

            if ( detalles.isEmpty() && estpag_id != 0 ) {
                dato[0] = "No existen estructuras de pago generadas.";
                dato[1] = false;
                dato[2] = true;
            } else {
                mod_descri = daoM.buscarModulo( mod_id ).getModDescripcion();
                secciones = new ArrayList<bMatriculaCL>();

                for ( int i = 0; i < lista_sec_modulo.size(); i++ ) {
                    bMatriculaCL sec = new bMatriculaCL( 0 );
                    if ( lista_sec_modulo.get( i ).getClHorarias() != null && !lista_sec_modulo.get( i ).getClHorarias().isEmpty() ) {
                        int matriculados_seccion = daoH.matriculadosSeccion( lista_sec_modulo.get( i ).getSecId() ).size();

                        sec.setI_sec_id( lista_sec_modulo.get( i ).getSecId() );

                        sec.setI_codigo_curso( lista_sec_modulo.get( i ).getClArbolAcademico().getArbDescripcion() );
                        sec.setI_modulo( lista_sec_modulo.get( i ).getClArbolAcademico().getArbDescripcion() );
                        sec.setI_curso( lista_sec_modulo.get( i ).getClArbolAcademico().getArbDescripcion() );
                        sec.setI_seccion( lista_sec_modulo.get( i ).getSecNombre() );
                        sec.setI_inicio( lista_sec_modulo.get( i ).getSecFinicio() );
                        sec.setI_nro_mat( matriculados_seccion );
                        sec.setI_tt_message( "" );
                        sec.setI_ver_message( false );
                        if ( matriculados_seccion >= (lista_sec_modulo.get( i ).getSecVacMax() - 10) ) {
                            sec.setI_nro_mat_style( "font-size: 17px; font-weight: bold; color: red;" );
                            sec.setI_tt_message( "La sección se encuentra cerca del máximo de vacantes(" + lista_sec_modulo.get( i ).getSecVacMax() + "). Es necesario generar una nueva sección." );
                            sec.setI_ver_message( true );
                        } else {
                            sec.setI_nro_mat_style( "font-size: 17px; font-weight: bold;" );
                        }
                        sec.setI_sec_vac_max( lista_sec_modulo.get( i ).getSecVacMax() );
                        sec.setI_monto_editar( 0 );
                        sec.setI_agregar( true );

                        boolean encontro = false;
                        i_detalles = new ArrayList<Integer>();
                        for ( ClEstructuraPagosDetalle estpagdet : detalles ) {
                            i_detalles.add( estpagdet.getEstpagdetId() );
                        }
                        for ( int j = 0; j < detalles.size(); j++ ) {
                            int det_tal_id = detalles.get( j ).getTalId();

                            //se esta modificando por el nuevo
                            int sec_modulo_talape_tal_id = 5;
                            encontro = false;

                            if ( det_tal_id == sec_modulo_talape_tal_id ) {
                                sec.setCl_estpagdet_id( detalles.get( j ).getEstpagdetId() );
                                sec.setCl_conpag_id( detalles.get( j ).getAdConceptoPago().getId() );
                                sec.setCl_fecha_pago( lista_sec_modulo.get( i ).getSecFinicio() );
                                sec.setCl_fecha_prorroga( lista_sec_modulo.get( i ).getSecFinicio() );
                                sec.setI_monto_base( detalles.get( j ).getEstpagdetMonto() );
                                sec.setI_monto_pagar( detalles.get( j ).getEstpagdetMonto() );
                                sec.setCl_alutar_tipo( "014003" );

                                j = detalles.size();
                                encontro = true;
                            }
                        }

                        if ( encontro ) {
                            sec.setI_ver( true );
                            sec.setI_editar( false );
                            secciones.add( sec );
                        }
                    }
                }
                dato[1] = true;
                dato[2] = false;
            }
        } else {
            dato[0] = "No hay secciones abiertas o con horario asignado para proceder a matricular.";
            dato[1] = false;
            dato[2] = true;
        }

        return dato;
    }

    public String[] convertirTimeString( Time time ) {
        String[] fecha = new String[ 3 ];
        SimpleDateFormat sdf = new SimpleDateFormat( "hh:mm a" );
        Date fec = new Date( time.getTime() );
        String var = sdf.format( fec );
        fecha[0] = var.substring( 0, 2 );
        fecha[1] = var.substring( 3, 5 );
        fecha[2] = var.substring( 6, 8 );
        return fecha;
    }

    public Time convertirFechaTime( String hora, String minuto, String meridiano ) {
        Time time;
        try {
            Date fecha;
            String fec = hora.trim() + ":" + minuto.trim() + " " + meridiano.trim();
            SimpleDateFormat sdf = new SimpleDateFormat( "hh:mm a" );
            fecha = sdf.parse( fec );

            time = new Time( fecha.getTime() );

            return time;
        } catch ( Exception e ) {
            System.err.println( "Hubo un problema en la conversion a Time: " + e );
        }
        return null;
    }

    public List<BeanCLMatricula> mostrarCursosLibresAperturador( List<ClSeccion> listaSecciones ) {
        List<BeanCLMatricula> secciones = new ArrayList<BeanCLMatricula>();
        HSHorarioDAO daoH = CommonDAO.getClHorarioDAO();
        for ( int i = 0; i < listaSecciones.size(); i++ ) {
            BeanCLMatricula sec = new BeanCLMatricula( 0 );
            //if (lista_sec_modulo.get(i).getClHorarias() != null && !lista_sec_modulo.get(i).getClHorarias().isEmpty()) {
            int matriculados_seccion = daoH.matriculadosSeccion( listaSecciones.get( i ).getSecId() ).size();
            int pre_matriculados = daoH.prematriculadosSeccion( listaSecciones.get( i ).getSecId() ).size();

            sec.setI_sec_id( listaSecciones.get( i ).getSecId() );
            sec.setI_seccion_cod(listaSecciones.get( i ).getSecCodigo());
            sec.setI_codigo_curso( listaSecciones.get( i ).getClArbolAcademico().getArbDescripcion() );
            sec.setI_modulo( listaSecciones.get( i ).getClArbolAcademico().getArbDescripcion() );
            sec.setI_curso( listaSecciones.get( i ).getClArbolAcademico().getArbDescripcion() );
            sec.setI_tal_id( listaSecciones.get( i ).getClArbolAcademico().getArbId() );
            sec.setI_seccion( listaSecciones.get( i ).getSecNombre() );
            sec.setI_inicio( listaSecciones.get( i ).getSecFinicio() );
            sec.setI_nro_mat( matriculados_seccion );
            sec.setPre_i_nro_mat( pre_matriculados );
            sec.setI_tt_message( "" );
            sec.setI_ver_message( false );
            if ( matriculados_seccion >= (listaSecciones.get( i ).getSecVacMax() - 10) ) {
                sec.setI_nro_mat_style( "font-size: 17px; font-weight: bold; color: red;" );
                sec.setI_tt_message( "La sección se encuentra cerca del máximo de vacantes(" + listaSecciones.get( i ).getSecVacMax() + "). Es necesario generar una nueva sección." );
                sec.setI_ver_message( true );
            } else {
                sec.setI_nro_mat_style( "font-size: 17px; font-weight: bold;" );
            }
            sec.setI_sec_vac_max( listaSecciones.get( i ).getSecVacMax() );


            secciones.add( sec );
        }
        return secciones;
    }
}
