package net.uch.cursoLibre.managedBeans;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.faces.component.UIParameter;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSAlumnoTarifaCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSArbolAcademicoClDao;
import net.uch.cursoLibre.hibernateSpringDao.HSEstructuraPagoDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSHorarioDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSMatriculaCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSSeccionCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSArbolAperturadoClDAO;
import net.uch.cursoLibre.managedBeans.beans.BeanCLMatricula;
import net.uch.cursoLibre.managedBeans.beans.BeanClAlumno;
import net.uch.cursoLibre.managedBeans.beans.BeanClSeccion;
import net.uch.cursoLibre.managedBeans.beans.MetodosCL;
import net.uch.mapping.ClAlumno;
import net.uch.mapping.ClAlumnoTarifa;
import net.uch.mapping.ClArbolAcademico;
import net.uch.mapping.ClEstructuraPagos;
import net.uch.mapping.ClEstructuraPagosDetalle;
import net.uch.mapping.ClMatricula;
import net.uch.mapping.ClMatriculaTaller;
import net.uch.mapping.ClSeccion;
import net.uch.tablaSistema.managedBeans.bUsuario;
import net.uch.util.CommonDAO;

/**
 *
 * @author luis
 */
public class bMatriculaSeccionCL {

    // CAMPOS
    private BeanClSeccion seccion;
    private int talape_id;
    private String talape_nombre;
    private int cur_id;
    private String cur_nombre;
    private int mod_id;
    private String mod_nombre;
    private String b_codigo;
    private String b_appaterno;
    private String b_apmaterno;
    private String b_nombres;
    private String sec_detalle;
    private List<BeanClSeccion> listaSecciones;
    private List<BeanClAlumno> listaAlumnosMatriculados;
    private Date b_inicio;
    private String style;
    private int sec_id;
    private int sec_dest_id;
    private int estpag_dest_id;
    private List<bMatriculaSeccionCL> matriculas = new ArrayList<bMatriculaSeccionCL>();
    private int m_mat_id;
    private int m_alu_id;
    private String m_mat_codigo;
    private Date m_mat_fecha;
    private String m_mat_tipo;
    private String m_mat_activo;
    private int m_usu_id;
    private String m_tipo;
    private String m_alu_codigo;
    private String m_alu_nombres;
    private String m_alu_appaterno;
    private String m_alu_apmaterno;
    private String m_taller;
    private String m_inicio;
    private String m_seccion;
    int e_mat_id;
    int e_alu_id;
    private String mensajeEliminar;
    private String oncomplete;
    private boolean verDetalle;
    private String m_imagen_mostrar;
    private String m_texto_mostrar;
//    private String suggestion;
    private List<bMatriculaSeccionCL> alumnos = new ArrayList<bMatriculaSeccionCL>();
    private int c_alu_id;
    private String c_alu_codigo;
    private String c_alu_nombre;
    private int i_alu_id;
    private String i_alu_codigo;
    private String i_alu_nombre;
    private List<BeanCLMatricula> secciones = new ArrayList<BeanCLMatricula>();
    private boolean verMensaje;
    private boolean verCursos;
    private int i_sec_id;
    private String i_codigo_curso;
    private String i_modulo;
    private String i_curso;
    private String i_seccion;
    private int i_nro_mat;
    private String i_nro_mat_style;
    private int i_sec_vac_max;
    private String i_tt_message;
    private boolean i_ver_message;
    private Date i_inicio;
    private Date i_fin;
    private float i_monto_base;
    private float i_monto_pagar;
    private boolean i_agregar;
    private boolean i_ver;
    private boolean i_editar;
    private float i_monto_editar;
    private int i_are_id = 0;
    private SelectItem[] i_areas;
    private int i_mod_id = 0;
    private SelectItem[] i_modulos;
    private int i_estpag_id;
    private SelectItem[] i_cursos;
    private int i_cur_id = 0;
    private SelectItem[] i_estructuras;
    //campos para cl_alumno_tarifa//
    private List<Integer> i_detalles;
    private int cl_estpagdet_id;
    private int cl_alu_id;
    private float cl_alutar_monto;
    private int cl_conpag_id;
    private Date cl_fecha_pago;
    private Date cl_fecha_prorroga;
    private String cl_alutar_estado;
    private String cl_alutar_activo;
    private String cl_alutar_tipo;
    private int cl_sec_id;
    private int cl_mat_id;
    private String mensaje_error;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy/MM/dd" );
    private List<bMatriculaSeccionCL> deudas_al = new ArrayList<bMatriculaSeccionCL>();
    private int de_alu_id;
    private String de_alu_codigo;
    private String de_taller;
    private String de_seccion;
    // otros
    private int tal_dest_id;
    private SelectItem[] tal_dest_desc;
    private SelectItem[] sec_dest_desc;
    private SelectItem[] estpag_dest_desc;
    //Constantes
    private final int EXCEDE_CANTIDAD_ALUMNOS = 1;
    private final int PERTENECE_AL_MISMO_CURSO = 2;
    private int tal_id;
    private List<ClEstructuraPagosDetalle> listaEstructuraDetalle = new ArrayList<ClEstructuraPagosDetalle>();
    /*
     * variables para ocultar el pago
     */
    private String w_textoPago = "Mostrar detalle de pagos";
    private String w_imgPago = "/Imagenes/actions/down.png";
    private String w_ocultar_estructura = "false";
    private String w_ocultar_boton = "true";
    private float i_alutar_monto_aumento;
    boolean ind = false;

    public List<ClEstructuraPagosDetalle> getListaEstructuraDetalle() {
        return listaEstructuraDetalle;
    }

    public void setListaEstructuraDetalle( List<ClEstructuraPagosDetalle> listaEstructuraDetalle ) {
        this.listaEstructuraDetalle = listaEstructuraDetalle;
    }

    public String getW_imgPago() {
        return w_imgPago;
    }

    public void setW_imgPago( String w_imgPago ) {
        this.w_imgPago = w_imgPago;
    }

    public String getW_ocultar_boton() {
        return w_ocultar_boton;
    }

    public void setW_ocultar_boton( String w_ocultar_boton ) {
        this.w_ocultar_boton = w_ocultar_boton;
    }

    public String getW_ocultar_estructura() {
        return w_ocultar_estructura;
    }

    public void setW_ocultar_estructura( String w_ocultar_estructura ) {
        this.w_ocultar_estructura = w_ocultar_estructura;
    }

    public String getW_textoPago() {
        return w_textoPago;
    }

    public void setW_textoPago( String w_textoPago ) {
        this.w_textoPago = w_textoPago;
    }
    /*
     * fin oculatr
     */

    public int getTal_id() {
        return tal_id;
    }

    public void setTal_id( int tal_id ) {
        this.tal_id = tal_id;
    }

    public boolean casillasVacias() {
        if ( this.getB_codigo().trim().isEmpty()
                && this.getB_appaterno().trim().isEmpty()
                && this.getB_apmaterno().trim().isEmpty()
                && this.getB_nombres().trim().isEmpty() ) {
            return true;
        } else {
            return false;
        }
    }

    public void seleccionarTaller( int talape_id ) {
        BeanClSeccion bSeccionCl;
        HSHorarioDAO horarioDAO;
        HSSeccionCLDAO secClDAO;
        List<ClSeccion> lstSecciones;
        try {
            secClDAO = (HSSeccionCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLSeccion" );
            horarioDAO = (HSHorarioDAO) ServiceFinder.findBean( "SpringHibernateDaoCLHorario" );

            lstSecciones = secClDAO.seleccionarSecciones( talape_id );
            this.listaSecciones = new ArrayList<BeanClSeccion>();
            for ( ClSeccion tmp_sec : lstSecciones ) {
//                System.err.println("LOAD OF TALLERS ................");
                bSeccionCl = new BeanClSeccion( tmp_sec );
                bSeccionCl.setCantMatriculados( horarioDAO.matriculadosSeccion( tmp_sec.getSecId() ).size() );
                this.addListaSecciones( bSeccionCl );
            }
        } catch ( Exception e ) {
            System.err.println( "Error al seleccionar los talleres " + e.getMessage() );
        }
    }

    public void seleccionarSeccion( ActionEvent event ) {
        if ( this.talape_id != 0 ) {
            this.setOncomplete( "" );
            try {
                int p_sec_id2 = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "p_sec_id" )).getValue().toString() );
                HSSeccionCLDAO daoSec = (HSSeccionCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLSeccion" );
//                System.out.println("valorrrrrrrrrrrrrr q tomaaaaaaaaa--->"+p_sec_id2);
                this.seccion = new BeanClSeccion( daoSec.seleccionarSeccion( p_sec_id2 ) );
            } catch ( NullPointerException npe ) {
                this.seccion = new BeanClSeccion( this.talape_id );
            }

            this.setOncomplete( "Richfaces.showModalPanel('mpSeccion',{top:100})" );
        }
    }

    public void cargarMatriculados( ActionEvent event ) throws Exception {
        int p_sec_id = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "p_sec_id" )).getValue().toString() );
        HSSeccionCLDAO daoSec = (HSSeccionCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLSeccion" );
        //ClSeccion secciond = daoSec.seleccionarSeccion(p_sec_id);
        //ClTallerAperturado objTaller = secciond.getClTallerAperturado();
        // this.setTal_dest_id(objTaller.getClTaller().getTalId());
       /*
         * this.setTal_id(secciond); this.setTal_dest_id(0);
         * this.setTalape_nombre(objTaller.getTalapeDescripcion()); //ClCurso
         * curso = objTaller.getClTaller().getClCurso(); ClCurso curso =
         * objTaller.getClCurso(); this.setCur_id(curso.getCurId());
         * this.setCur_nombre(curso.getCurNombre());
         * this.setMod_nombre(curso.getClModulo().getModDescripcion());
         * this.setMod_id(curso.getClModulo().getModId());
         * this.setSec_detalle(curso.getCurCodigo() + " | " +
         * curso.getCurNombre()); //
         * this.seleccionarTaller(objTaller.getTalapeId());
         * this.listaAlumnosMatriculados = new ArrayList<BeanClAlumno>();
         */

        HSHorarioDAO daoH = (HSHorarioDAO) ServiceFinder.findBean( "SpringHibernateDaoCLHorario" );
        System.out.println( "seccion_id: " + p_sec_id );
        List<ClAlumno> lista = daoH.matriculadosSeccion( p_sec_id );
        if ( lista.isEmpty() ) {
            this.setStyle( "background-color: gray;" );
            System.out.println( "vacio" );
        } else {

            System.out.println( "cant_lista: " + lista.size() );
            this.setStyle( "" );
            this.sec_id = p_sec_id;
            this.sec_dest_id = 0;
            this.estpag_dest_id = 0;
            for ( int i = 0; i < lista.size(); i++ ) {

                ClAlumno tmp_alu = lista.get( i );
                BeanClAlumno alu = new BeanClAlumno( tmp_alu );
                alu.setAluContador( i + 1 );

                this.addListaAlumnosMatriculados( alu );
            }
        }

    }

    public void cargarPreMatriculados( ActionEvent event ) throws Exception {
        List<ClMatricula> listMat;
        List<ClAlumnoTarifa> listTarifa;
        float monto = 0f;
        float mora = 0f;
        HSMatriculaCLDAO daoM = (HSMatriculaCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLMatricula" );
        int pre_sec_id = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "p_sec_id" )).getValue().toString() );
        HSSeccionCLDAO daoSec = (HSSeccionCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLSeccion" );
        HSAlumnoTarifaCLDAO daoAT = (HSAlumnoTarifaCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLAlumnoTarifa" );
        ClSeccion secciond = daoSec.seleccionarSeccion( pre_sec_id );
        String p_sec_detalle = ((UIParameter) event.getComponent().findComponent( "p_sec_detalle" )).getValue().toString();
        this.setSec_detalle( p_sec_detalle );
        // ClTallerAperturado objTaller = secciond.getClTallerAperturado();
        // this.setTal_dest_id(objTaller.getClTaller().getTalId());
        this.setTal_dest_id( 0 );
        this.setTalape_nombre( secciond.getClArbolAcademico().getArbDescripcion() );
        //ClCurso curso = objTaller.getClCurso();
        this.setCur_id( secciond.getClArbolAcademico().getArbId() );
        this.setCur_nombre( secciond.getClArbolAcademico().getArbDescripcion() );
        this.setMod_nombre( secciond.getClArbolAcademico().getArbDescripcion() );
        this.setMod_id( secciond.getClArbolAcademico().getArbId() );

        this.listaAlumnosMatriculados = new ArrayList<BeanClAlumno>();

        HSHorarioDAO daoH = (HSHorarioDAO) ServiceFinder.findBean( "SpringHibernateDaoCLHorario" );
        List<ClAlumno> lista = daoH.prematriculadosSeccion( pre_sec_id );
        if ( lista.isEmpty() ) {
            this.setStyle( "background-color: gray;" );
        } else {

            this.setStyle( "" );
            this.sec_id = pre_sec_id;
            this.sec_dest_id = 0;
            this.estpag_dest_id = 0;
            for ( int i = 0; i < lista.size(); i++ ) {

                ClAlumno tmp_alu = lista.get( i );
                monto = 0;
                mora = 0;
                BeanClAlumno alu = new BeanClAlumno( tmp_alu );
                alu.setAluContador( i + 1 );

                int mat = daoM.BuscarMatricula( sec_id, alu.getAluId() ).getMatId();
                listTarifa = daoAT.alumnoMonto( alu.getAluId(), mat );

                for ( int z = 0; z < listTarifa.size(); z++ ) {
                    monto += listTarifa.get( z ).getAlutarMonto();
                    mora += listTarifa.get( z ).getAlutarMora();
                }
                alu.setTotal( monto + mora );
                this.addListaAlumnosMatriculados( alu );


                /*
                 * int mat = daoM.BuscarMatricula(sec_id,
                 * alu.getAluId()).getMatId();
                 * alu.setTotal(daoAT.alumnoMonto(alu.getAluId(),
                 * mat).getAlutarMonto()+ daoAT.alumnoMonto(alu.getAluId(),
                 * mat).getAlutarMora());
                 *
                 * this.addListaAlumnosMatriculados(alu);
                 */
            }
        }

    }

    public void guardarAumento( ActionEvent event ) throws Exception {
        //this.setB_sec_id(((UIParameter) event.getComponent().findComponent("p_sec_id")).getValue().toString());
        int alutarid;
        HSAlumnoTarifaCLDAO daoAT = (HSAlumnoTarifaCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLAlumnoTarifa" );
        HSMatriculaCLDAO daoM = (HSMatriculaCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLMatricula" );
        int flag = 0;
        this.setOncomplete( "" );
        if ( listaAlumnosMatriculados.size() > 0 ) {
            for ( int i = 0; i < listaAlumnosMatriculados.size(); i++ ) {
                if ( listaAlumnosMatriculados.get( i ).isAluMatriculado() ) {
                    flag++;
                }
            }
        }
        if ( flag == 0 ) {
            this.setOncomplete( "javascript:alert('Seleccione minimo un alumno');" );
        } else {
            for ( BeanClAlumno bean_alumno : listaAlumnosMatriculados ) {
                alutarid = 0;
                if ( bean_alumno.isAluMatriculado() ) {
                    int mat = daoM.BuscarMatricula( sec_id, bean_alumno.getAluId() ).getMatId();
                    List<ClAlumnoTarifa> alu = daoAT.seleccionarTarifasXMatricula( mat, bean_alumno.getAluId() );
                    daoAT.aumentarMora( alu.get( 0 ).getAlutarId(), i_alutar_monto_aumento );
                    //daoAT.agregarMora(bean_alumno.getAluId(), mat, i_alutar_monto_aumento);
                }
            }
            this.setOncomplete( "javascript:alert('Se ingreso el Aumento correctamente');"
                    + " Richfaces.hideModalPanel('mpMatriculados');" );
        }
    }

    public void MostrarCursos( ActionEvent event ) throws Exception {
        //HSHorarioDAO daoH = (HSHorarioDAO) ServiceFinder.findBean("SpringHibernateDaoCLHorario");
        HSEstructuraPagoDAO daoEP = (HSEstructuraPagoDAO) ServiceFinder.findBean( "SpringHibernateDaoCLEstructuraPago" );
        //HSModuloDAO daoM = (HSModuloDAO) ServiceFinder.findBean("SpringHibernateDaoCLModulo");
        HSSeccionCLDAO daoS = (HSSeccionCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLSeccion" );

        //List<ClSeccion> lista_sec_modulo = daoS.listarSeccionesXModulo(this.getI_mod_id());
        List<ClSeccion> lista_sec_modulo = daoS.listarSeccionesXCurso( this.getI_cur_id() );
        if ( !lista_sec_modulo.isEmpty() ) {
            List<ClEstructuraPagosDetalle> detalles = daoEP.seleccionarDetalle( this.getI_estpag_id() );

            MetodosCL metodo = new MetodosCL();
            this.setSecciones( metodo.mostrarCursosLibresAperturador( lista_sec_modulo ) );
            this.setVerCursos( true );
            this.setVerMensaje( false );
            //}
        } else {
            this.setMensaje_error( "No hay secciones abiertas o con horario asignado para proceder a matricular." );
            this.setVerCursos( false );
            this.setVerMensaje( true );
        }
    }

    public void MatricularSeleccionados( ActionEvent event ) throws Exception {
        this.setOncomplete( "" );

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session1 = (HttpSession) context.getSession( false );
        MetodosCL metodo = new MetodosCL();
        String mensaje = metodo.matricularCursosLibres( this.getI_alu_id(), this.getSecciones(), i_detalles, session1, "", 0 );
        // String mensaje=metodo.matricularCursosLibres(this.getI_alu_id(), null, i_detalles, session1);

        this.setOncomplete( mensaje );
    }

    public String getB_codigo() {
        return b_codigo;
    }

    public void setB_codigo( String b_codigo ) {
        this.b_codigo = b_codigo;
    }

    public String getB_appaterno() {
        return b_appaterno;
    }

    public void setB_appaterno( String b_appaterno ) {
        this.b_appaterno = b_appaterno;
    }

    public String getB_apmaterno() {
        return b_apmaterno;
    }

    public void setB_apmaterno( String b_apmaterno ) {
        this.b_apmaterno = b_apmaterno;
    }

    public String getB_nombres() {
        return b_nombres;
    }

    public void setB_nombres( String b_nombres ) {
        this.b_nombres = b_nombres;
    }

    public Date getB_inicio() {
        return b_inicio;
    }

    public void setB_inicio( Date b_inicio ) {
        this.b_inicio = b_inicio;
    }

    public void addMatriculas( bMatriculaSeccionCL tmp ) {
        this.matriculas.add( tmp );
    }

    public List<bMatriculaSeccionCL> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas( List<bMatriculaSeccionCL> matriculas ) {
        this.matriculas = matriculas;
    }

    public int getM_mat_id() {
        return m_mat_id;
    }

    public void setM_mat_id( int m_mat_id ) {
        this.m_mat_id = m_mat_id;
    }

    public int getM_alu_id() {
        return m_alu_id;
    }

    public void setM_alu_id( int m_alu_id ) {
        this.m_alu_id = m_alu_id;
    }

    public String getM_mat_codigo() {
        return m_mat_codigo;
    }

    public void setM_mat_codigo( String m_mat_codigo ) {
        this.m_mat_codigo = m_mat_codigo;
    }

    public Date getM_mat_fecha() {
        return m_mat_fecha;
    }

    public void setM_mat_fecha( Date m_mat_fecha ) {
        this.m_mat_fecha = m_mat_fecha;
    }

    public String getM_mat_tipo() {
        return m_mat_tipo;
    }

    public void setM_mat_tipo( String m_mat_tipo ) {
        this.m_mat_tipo = m_mat_tipo;
    }

    public String getM_mat_activo() {
        return m_mat_activo;
    }

    public void setM_mat_activo( String m_mat_activo ) {
        this.m_mat_activo = m_mat_activo;
    }

    public int getM_usu_id() {
        return m_usu_id;
    }

    public void setM_usu_id( int m_usu_id ) {
        this.m_usu_id = m_usu_id;
    }

    public String getM_alu_codigo() {
        return m_alu_codigo;
    }

    public void setM_alu_codigo( String m_alu_codigo ) {
        this.m_alu_codigo = m_alu_codigo;
    }

    public String getM_alu_nombres() {
        return m_alu_nombres;
    }

    public void setM_alu_nombres( String m_alu_nombres ) {
        this.m_alu_nombres = m_alu_nombres;
    }

    public String getM_alu_appaterno() {
        return m_alu_appaterno;
    }

    public void setM_alu_appaterno( String m_alu_appaterno ) {
        this.m_alu_appaterno = m_alu_appaterno;
    }

    public String getM_alu_apmaterno() {
        return m_alu_apmaterno;
    }

    public void setM_alu_apmaterno( String m_alu_apmaterno ) {
        this.m_alu_apmaterno = m_alu_apmaterno;
    }

    public String getM_tipo() {
        return m_tipo;
    }

    public void setM_tipo( String m_tipo ) {
        this.m_tipo = m_tipo;
    }

    public String getOncomplete() {
        return oncomplete;
    }

    public void setOncomplete( String oncomplete ) {
        this.oncomplete = oncomplete;
    }

    public String getM_taller() {
        return m_taller;
    }

    public void setM_taller( String m_taller ) {
        this.m_taller = m_taller;
    }

    public String getM_inicio() {
        return m_inicio;
    }

    public void setM_inicio( String m_inicio ) {
        this.m_inicio = m_inicio;
    }

    public boolean isVerDetalle() {
        return verDetalle;
    }

    public void setVerDetalle( boolean verDetalle ) {
        this.verDetalle = verDetalle;
    }

    public String getM_imagen_mostrar() {
        return m_imagen_mostrar;
    }

    public void setM_imagen_mostrar( String m_imagen_mostrar ) {
        this.m_imagen_mostrar = m_imagen_mostrar;
    }

    public String getM_texto_mostrar() {
        return m_texto_mostrar;
    }

    public void setM_texto_mostrar( String m_texto_mostrar ) {
        this.m_texto_mostrar = m_texto_mostrar;
    }
//
//    public String getSuggestion() {
//        return suggestion;
//    }
//
//    public void setSuggestion(String suggestion) {
//        this.suggestion = suggestion;
//    }

    public List<bMatriculaSeccionCL> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos( List<bMatriculaSeccionCL> alumnos ) {
        this.alumnos = alumnos;
    }

    public int getC_alu_id() {
        return c_alu_id;
    }

    public void setC_alu_id( int c_alu_id ) {
        this.c_alu_id = c_alu_id;
    }

    public String getC_alu_codigo() {
        return c_alu_codigo;
    }

    public void setC_alu_codigo( String c_alu_codigo ) {
        this.c_alu_codigo = c_alu_codigo;
    }

    public String getC_alu_nombre() {
        return c_alu_nombre;
    }

    public void setC_alu_nombre( String c_alu_nombre ) {
        this.c_alu_nombre = c_alu_nombre;
    }

    public int getI_alu_id() {
        return i_alu_id;
    }

    public void setI_alu_id( int i_alu_id ) {
        this.i_alu_id = i_alu_id;
    }

    public String getI_alu_codigo() {
        return i_alu_codigo;
    }

    public void setI_alu_codigo( String i_alu_codigo ) {
        this.i_alu_codigo = i_alu_codigo;
    }

    public String getI_alu_nombre() {
        return i_alu_nombre;
    }

    public void setI_alu_nombre( String i_alu_nombre ) {
        this.i_alu_nombre = i_alu_nombre;
    }

    public List<BeanCLMatricula> getSecciones() {
        return secciones;
    }

    public void setSecciones( List<BeanCLMatricula> secciones ) {
        this.secciones = secciones;
    }

    public boolean isVerMensaje() {
        return verMensaje;
    }

    public void setVerMensaje( boolean verMensaje ) {
        this.verMensaje = verMensaje;
    }

    public boolean isVerCursos() {
        return verCursos;
    }

    public void setVerCursos( boolean verCursos ) {
        this.verCursos = verCursos;
    }

    public int getI_sec_id() {
        return i_sec_id;
    }

    public void setI_sec_id( int i_sec_id ) {
        this.i_sec_id = i_sec_id;
    }

    public String getI_codigo_curso() {
        return i_codigo_curso;
    }

    public void setI_codigo_curso( String i_codigo_curso ) {
        this.i_codigo_curso = i_codigo_curso;
    }

    public String getI_modulo() {
        return i_modulo;
    }

    public void setI_modulo( String i_modulo ) {
        this.i_modulo = i_modulo;
    }

    public String getI_curso() {
        return i_curso;
    }

    public void setI_curso( String i_curso ) {
        this.i_curso = i_curso;
    }

    public Date getI_inicio() {
        return i_inicio;
    }

    public void setI_inicio( Date i_inicio ) {
        this.i_inicio = i_inicio;
    }

    /**
     * @return the i_fin
     */
    public Date getI_fin() {
        return i_fin;
    }

    public void setI_fin( Date i_fin ) {
        this.i_fin = i_fin;
    }

    public float getI_monto_base() {
        return i_monto_base;
    }

    public void setI_monto_base( float i_monto_base ) {
        this.i_monto_base = i_monto_base;
    }

    public float getI_monto_pagar() {
        return i_monto_pagar;
    }

    public void setI_monto_pagar( float i_monto_pagar ) {
        this.i_monto_pagar = i_monto_pagar;
    }

    public boolean isI_agregar() {
        return i_agregar;
    }

    public void setI_agregar( boolean i_agregar ) {
        this.i_agregar = i_agregar;
    }

    public boolean isI_ver() {
        return i_ver;
    }

    public void setI_ver( boolean i_ver ) {
        this.i_ver = i_ver;
    }

    public boolean isI_editar() {
        return i_editar;
    }

    public void setI_editar( boolean i_editar ) {
        this.i_editar = i_editar;
    }

    public float getI_monto_editar() {
        return i_monto_editar;
    }

    public void setI_monto_editar( float i_monto_editar ) {
        this.i_monto_editar = i_monto_editar;
    }

    public String getI_seccion() {
        return i_seccion;
    }

    public void setI_seccion( String i_seccion ) {
        this.i_seccion = i_seccion;
    }

    public String getM_seccion() {
        return m_seccion;
    }

    public void setM_seccion( String m_seccion ) {
        this.m_seccion = m_seccion;
    }

    public int getI_nro_mat() {
        return i_nro_mat;
    }

    public void setI_nro_mat( int i_nro_mat ) {
        this.i_nro_mat = i_nro_mat;
    }

    public String getI_nro_mat_style() {
        return i_nro_mat_style;
    }

    public void setI_nro_mat_style( String i_nro_mat_style ) {
        this.i_nro_mat_style = i_nro_mat_style;
    }

    public int getI_sec_vac_max() {
        return i_sec_vac_max;
    }

    public void setI_sec_vac_max( int i_sec_vac_max ) {
        this.i_sec_vac_max = i_sec_vac_max;
    }

    public float getI_alutar_monto_aumento() {
        return i_alutar_monto_aumento;
    }

    public void setI_alutar_monto_aumento( float i_alutar_monto_aumento ) {
        this.i_alutar_monto_aumento = i_alutar_monto_aumento;
    }

    public String getI_tt_message() {
        return i_tt_message;
    }

    public void setI_tt_message( String i_tt_message ) {
        this.i_tt_message = i_tt_message;
    }

    public boolean isI_ver_message() {
        return i_ver_message;
    }

    public void setI_ver_message( boolean i_ver_message ) {
        this.i_ver_message = i_ver_message;
    }

    public int getI_are_id() {
        return i_are_id;
    }

    public void setI_are_id( int i_are_id ) {
        this.i_are_id = i_are_id;
    }

    public SelectItem[] getI_areas() throws Exception {
        if ( i_areas == null ) {
            try {
                HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();
                List<ClArbolAcademico> areas = dao.seleccionarArea( "" );
                i_areas = new SelectItem[ areas.size() + 1 ];
                i_areas[0] = new SelectItem( Integer.MAX_VALUE, "[Seleccione]" );
                for ( int i = 0; i < areas.size(); i++ ) {
                    ClArbolAcademico a = areas.get( i );
                    i_areas[i + 1] = new SelectItem( a.getArbId(), a.getArbDescripcion() );
                }
            } catch ( Exception e ) {
                System.err.println( "Error al cargar areas" );
            }
        }
        return i_areas;
    }

    public void setI_areas( SelectItem[] i_areas ) {
        this.i_areas = i_areas;
    }

    public int getI_mod_id() {
        return i_mod_id;
    }

    public void setI_mod_id( int i_mod_id ) {
        this.i_mod_id = i_mod_id;
    }

    public SelectItem[] getI_modulos() throws Exception {
        if ( this.i_are_id == 0 ) {
            i_modulos = new SelectItem[ 1 ];
        } else {
            HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();
            List<ClArbolAcademico> modulos = dao.seleccionarModulos( this.i_are_id, "" );

            i_modulos = new SelectItem[ modulos.size() + 1 ];
            for ( int i = 0; i < modulos.size(); i++ ) {
                i_modulos[i + 1] = new SelectItem( modulos.get( i ).getArbId(), modulos.get( i ).getArbDescripcion() );
            }
        }
        i_modulos[0] = new SelectItem( 0, "[Seleccione]" );
        return i_modulos;
    }

    public SelectItem[] getI_cursos() {
        if ( this.i_mod_id == 0 ) {
            i_cursos = new SelectItem[ 1 ];
        } else {
            try {
                HSArbolAcademicoClDao dao = CommonDAO.getClArbolAcademicoDAO();
                List<ClArbolAcademico> cursos = dao.seleccionarCursos( this.i_mod_id );

                i_cursos = new SelectItem[ cursos.size() + 1 ];
                for ( int i = 0; i < cursos.size(); i++ ) {
                    i_cursos[i + 1] = new SelectItem( cursos.get( i ).getArbId(), cursos.get( i ).getArbDescripcion() );
                }
            } catch ( Exception e ) {
                System.err.println( "Error al cargar areas" );
            }
        }
        i_cursos[0] = new SelectItem( 0, "[Seleccione]" );
        return i_cursos;
    }

    public void setI_cursos( SelectItem[] icursos ) {
        this.i_cursos = icursos;
    }

    public void setI_modulos( SelectItem[] i_modulos ) {
        this.i_modulos = i_modulos;
    }

    public int getI_estpag_id() {
        return i_estpag_id;
    }

    public void setI_estpag_id( int i_estpag_id ) {
        this.i_estpag_id = i_estpag_id;
    }

    public SelectItem[] getI_estructuras() {
        try {
            if ( this.i_mod_id == 0 ) {
                i_estructuras = new SelectItem[ 1 ];
            } else {
                HSEstructuraPagoDAO dao = (HSEstructuraPagoDAO) ServiceFinder.findBean( "SpringHibernateDaoCLEstructuraPago" );
                List lista = dao.seleccionarEstructurasXModulo( this.i_mod_id );
                i_estructuras = new SelectItem[ lista.size() + 1 ];
                for ( int i = 0; i < lista.size(); i++ ) {
                    i_estructuras[i + 1] = new SelectItem( ((ClEstructuraPagos) lista.get( i )).getEstpagId(), ((ClEstructuraPagos) lista.get( i )).getEstpagNombre() );
                }
            }
            i_estructuras[0] = new SelectItem( 0, "[Seleccione]" );
        } catch ( Exception e ) {
            System.err.println( "Error al cargar las estructuras de pago en matricula Cursos Libres " + e.getMessage() );
        }
        return i_estructuras;
    }

    public void setI_estructuras( SelectItem[] i_estructuras ) {
        this.i_estructuras = i_estructuras;
    }

    public List<Integer> getI_detalles() {
        return i_detalles;
    }

    public void setI_detalles( List<Integer> i_detalles ) {
        this.i_detalles = i_detalles;
    }

    public int getCl_estpagdet_id() {
        return cl_estpagdet_id;
    }

    public void setCl_estpagdet_id( int cl_estpagdet_id ) {
        this.cl_estpagdet_id = cl_estpagdet_id;
    }

    public int getCl_alu_id() {
        return cl_alu_id;
    }

    public void setCl_alu_id( int cl_alu_id ) {
        this.cl_alu_id = cl_alu_id;
    }

    public float getCl_alutar_monto() {
        return cl_alutar_monto;
    }

    public void setCl_alutar_monto( float cl_alutar_monto ) {
        this.cl_alutar_monto = cl_alutar_monto;
    }

    public int getCl_conpag_id() {
        return cl_conpag_id;
    }

    public void setCl_conpag_id( int cl_conpag_id ) {
        this.cl_conpag_id = cl_conpag_id;
    }

    public Date getCl_fecha_pago() {
        return cl_fecha_pago;
    }

    public void setCl_fecha_pago( Date cl_fecha_pago ) {
        this.cl_fecha_pago = cl_fecha_pago;
    }

    public Date getCl_fecha_prorroga() {
        return cl_fecha_prorroga;
    }

    public void setCl_fecha_prorroga( Date cl_fecha_prorroga ) {
        this.cl_fecha_prorroga = cl_fecha_prorroga;
    }

    public String getCl_alutar_estado() {
        return cl_alutar_estado;
    }

    public void setCl_alutar_estado( String cl_alutar_estado ) {
        this.cl_alutar_estado = cl_alutar_estado;
    }

    public String getCl_alutar_activo() {
        return cl_alutar_activo;
    }

    public void setCl_alutar_activo( String cl_alutar_activo ) {
        this.cl_alutar_activo = cl_alutar_activo;
    }

    public String getCl_alutar_tipo() {
        return cl_alutar_tipo;
    }

    public void setCl_alutar_tipo( String cl_alutar_tipo ) {
        this.cl_alutar_tipo = cl_alutar_tipo;
    }

    public int getCl_sec_id() {
        return cl_sec_id;
    }

    public void setCl_sec_id( int cl_sec_id ) {
        this.cl_sec_id = cl_sec_id;
    }

    public int getCl_mat_id() {
        return cl_mat_id;
    }

    public void setCl_mat_id( int cl_mat_id ) {
        this.cl_mat_id = cl_mat_id;
    }

    public String getMensajeEliminar() {
        return mensajeEliminar;
    }

    public void setMensajeEliminar( String mensajeEliminar ) {
        this.mensajeEliminar = mensajeEliminar;
    }

    public String getMensaje_error() {
        return mensaje_error;
    }

    public void setMensaje_error( String mensaje_error ) {
        this.mensaje_error = mensaje_error;
    }

    /**
     * @return the deudas_al
     */
    public List<bMatriculaSeccionCL> getDeudas_al() {
        return deudas_al;
    }

    /**
     * @param deudas_al the deudas_al to set
     */
    public void setDeudas_al( List<bMatriculaSeccionCL> deudas_al ) {
        this.deudas_al = deudas_al;
    }

    /**
     * @return the de_alu_id
     */
    public int getDe_alu_id() {
        return de_alu_id;
    }

    /**
     * @param de_alu_id the de_alu_id to set
     */
    public void setDe_alu_id( int de_alu_id ) {
        this.de_alu_id = de_alu_id;
    }

    /**
     * @return the de_alu_codigo
     */
    public String getDe_alu_codigo() {
        return de_alu_codigo;
    }

    /**
     * @param de_alu_codigo the de_alu_codigo to set
     */
    public void setDe_alu_codigo( String de_alu_codigo ) {
        this.de_alu_codigo = de_alu_codigo;
    }

    /**
     * @return the de_taller
     */
    public String getDe_taller() {
        return de_taller;
    }

    /**
     * @param de_taller the de_taller to set
     */
    public void setDe_taller( String de_taller ) {
        this.de_taller = de_taller;
    }

    /**
     * @return the de_seccion
     */
    public String getDe_seccion() {
        return de_seccion;
    }

    public int getI_cur_id() {
        return i_cur_id;
    }

    public void setI_cur_id( int i_cur_id ) {
        this.i_cur_id = i_cur_id;
    }

    /**
     * @param de_seccion the de_seccion to set
     */
    public void setDe_seccion( String de_seccion ) {
        this.de_seccion = de_seccion;
    }

    public String getSec_detalle() {
        return sec_detalle;
    }

    public void setSec_detalle( String sec_detalle ) {
        this.sec_detalle = sec_detalle;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle( String style ) {
        this.style = style;
    }

    public int getSec_id() {
        return sec_id;
    }

    public void setSec_id( int sec_id ) {
        this.sec_id = sec_id;
    }

    public void addListaAlumnosMatriculados( BeanClAlumno tmp ) {
        this.listaAlumnosMatriculados.add( tmp );
    }

    public List<BeanClAlumno> getListaAlumnosMatriculados() {
        return listaAlumnosMatriculados;
    }

    public void setListaAlumnosMatriculados( List<BeanClAlumno> listaAlumnosMatriculados ) {
        this.listaAlumnosMatriculados = listaAlumnosMatriculados;
    }

    public int getSec_dest_id() {
        return sec_dest_id;
    }

    public void setSec_dest_id( int sec_dest_id ) {
        this.sec_dest_id = sec_dest_id;
    }

    public int getEstpag_dest_id() {
        return estpag_dest_id;
    }

    public int getTalape_id() {
        return talape_id;
    }

    public void setTalape_id( int talape_id ) {
        this.talape_id = talape_id;
    }

    public void setEstpag_dest_id( int estpag_dest_id ) {
        this.estpag_dest_id = estpag_dest_id;
    }

    public void addListaSecciones( BeanClSeccion tmp ) {
        this.listaSecciones.add( tmp );
    }

    public BeanClSeccion getSeccion() {
        return seccion;
    }

    public void setSeccion( BeanClSeccion seccion ) {
        this.seccion = seccion;
    }

    public List<BeanClSeccion> getListaSecciones() {
        return listaSecciones;
    }

    public void setListaSecciones( List<BeanClSeccion> listaSecciones ) {
        this.listaSecciones = listaSecciones;
    }

    public int getCur_id() {
        return cur_id;
    }

    public void setCur_id( int cur_id ) {
        this.cur_id = cur_id;
    }

    public String getCur_nombre() {
        return cur_nombre;
    }

    public void setCur_nombre( String cur_nombre ) {
        this.cur_nombre = cur_nombre;
    }

    public int getMod_id() {
        return mod_id;
    }

    public void setMod_id( int mod_id ) {
        this.mod_id = mod_id;
    }

    public String getMod_nombre() {
        return mod_nombre;
    }

    public void setMod_nombre( String mod_nombre ) {
        this.mod_nombre = mod_nombre;
    }

    public String getTalape_nombre() {
        return talape_nombre;
    }

    public void setTalape_nombre( String talape_nombre ) {
        this.talape_nombre = talape_nombre;
    }

    public SelectItem[] getTal_dest_desc() throws Exception {
        if ( this.getMod_id() == 0 ) {
            tal_dest_desc = new SelectItem[ 1 ];
        } else {
            HSArbolAperturadoClDAO daoTA = CommonDAO.getClArbolAperturadoClDAO();
            //List<ClTallerAperturado> talapes = daoTA.seleccionarTalleresAperturadosPorModulo(this.getMod_id(), this.getTal_id());
            System.out.println( "Modulo: " + this.getMod_id() );
            System.out.println( "Curso: " + this.getCur_id() );
            System.out.println( "Taller: " + this.getTal_id() );
            List<ClArbolAcademico> listaTal = daoTA.findArbTallerXModulo( this.getMod_id(), this.getTal_id() );
            tal_dest_desc = new SelectItem[ listaTal.size() + 1 ];
            for ( int i = 0; i < listaTal.size(); i++ ) {
                ClArbolAcademico talape = listaTal.get( i );
                tal_dest_desc[i + 1] = new SelectItem( talape.getArbId(), talape.getArbDescripcion() );
//                System.out.println("TALLERES APERTURADOS ..... !!!!!!!!!!!!!");
            }
        }
        tal_dest_desc[0] = new SelectItem( 0, "[Seleccione]" );
        return tal_dest_desc;
    }

    public int getTal_dest_id() {
        return tal_dest_id;
    }

    public void setTal_dest_id( int tal_dest_id ) {
//        System.out.println("uevo valor .. --->"+tal_dest_id);
        this.tal_dest_id = tal_dest_id;
    }

    public int getTotalMatriculados() {
        if ( this.listaAlumnosMatriculados == null ) {
            return 0;
        } else {
            return this.listaAlumnosMatriculados.size();
        }
    }

    public void setTal_dest_desc( SelectItem[] tal_dest_desc ) {
        this.tal_dest_desc = tal_dest_desc;
    }

    public SelectItem[] getSec_dest_desc() throws Exception {

//        System.out.println("SECIONEs RCUEPRADAS .... ..... ... "+this.getTal_dest_id());
        if ( this.getTal_dest_id() == 0 ) {
            sec_dest_desc = new SelectItem[ 1 ];
        } else {
            HSSeccionCLDAO daoSec = (HSSeccionCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLSeccion" );
            List<ClSeccion> secs = daoSec.listarSeccionXTaller( this.getTal_dest_id() );
//            System.out.println("SECIONEs RCUEPRADAS .... ..... ... "+this.getTal_dest_id());
            sec_dest_desc = new SelectItem[ secs.size() + 1 ];
            for ( int i = 0; i < secs.size(); i++ ) {
                ClSeccion sec = secs.get( i );
                sec_dest_desc[i + 1] = new SelectItem( sec.getSecId(), sec.getSecCodigo() + " | " + sec.getSecNombre() );
            }
        }
        this.sec_dest_id = 0;
        sec_dest_desc[0] = new SelectItem( 0, "[Seleccione]" );
        return sec_dest_desc;
    }

    public void setSec_dest_desc( SelectItem[] sec_dest_desc ) {
        this.sec_dest_desc = sec_dest_desc;
    }

    public int getTotalParaMatricular() {
        int total = 0;
        for ( BeanClAlumno tmp : listaAlumnosMatriculados ) {
            if ( tmp.isAluMatriculado() ) {
                total++;
            }
        }
        return total;
    }

    public SelectItem[] getEstpag_dest_desc() {
        if ( this.getTal_dest_id() == 0 ) {
            estpag_dest_desc = new SelectItem[ 1 ];
        } else {
            HSEstructuraPagoDAO daoEP = (HSEstructuraPagoDAO) ServiceFinder.findBean( "SpringHibernateDaoCLEstructuraPago" );
            List<ClEstructuraPagos> estpags = daoEP.seleccionarEstructurasXTaller( this.getTal_dest_id() );
//            System.out.println(this.getTal_dest_id());
            estpag_dest_desc = new SelectItem[ estpags.size() + 1 ];
            for ( int i = 0; i < estpags.size(); i++ ) {
                ClEstructuraPagos estpag = estpags.get( i );
                estpag_dest_desc[i + 1] = new SelectItem( estpag.getEstpagId(), estpag.getEstpagNombre() );
            }
        }
        this.estpag_dest_id = 0;
        estpag_dest_desc[0] = new SelectItem( 0, "[Seleccione]" );
        return estpag_dest_desc;
    }

    public void setEstpag_dest_desc( SelectItem[] estpag_dest_desc ) {
        this.estpag_dest_desc = estpag_dest_desc;
    }

    public void guardarMatriculados( ActionEvent event ) throws Exception {
        this.setOncomplete( "" );
        HSSeccionCLDAO daoSec = (HSSeccionCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLSeccion" );
        ClSeccion tmp_sec = daoSec.seleccionarSeccion( this.getSec_dest_id() );

        if ( this.getSec_dest_id() == 0 ) {
            this.setOncomplete( "javascript:alert('Seleccione una seccion.');" );
        } else if ( this.getEstpag_dest_id() == 0 ) {
            this.setOncomplete( "javascript:alert('Seleccione una estructura de pago.');" );
        } else if ( validaciones( EXCEDE_CANTIDAD_ALUMNOS, tmp_sec ) ) {
            this.setOncomplete( "javascript:alert('Muchos alumnos para matricular.');" );
        } else if ( validaciones( PERTENECE_AL_MISMO_CURSO, tmp_sec ) ) {
            this.setOncomplete( "javascript:alert('La seccion pertenece al mismo curso.');" );
        } else {
            List<ClMatricula> matriculas = new ArrayList<ClMatricula>();
            HSMatriculaCLDAO daoM = (HSMatriculaCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLMatricula" );
            for ( BeanClAlumno bean_alumno : listaAlumnosMatriculados ) {
                if ( bean_alumno.isAluMatriculado() && !daoM.estaMatriculado( bean_alumno.getAluId(), tmp_sec.getSecId() ) ) {
                    ClAlumno alu = new ClAlumno( bean_alumno.getAluId() );

                    ClMatricula mat = new ClMatricula( alu );
                    mat.setMatActivo( "1" );
                    mat.setMatCodigo( "MAT-BL" );
                    mat.setMatFecha( new Timestamp( new Date().getTime() ) );
                    mat.setMatTipo( "022005" );
                    mat.setUsuId( capturarUsuario() );

                    Set<ClMatriculaTaller> mattals = new LinkedHashSet<ClMatriculaTaller>( 1 );
                    ClMatriculaTaller mattal = new ClMatriculaTaller();
                    mattal.setMattalActivo( "1" );
                    mattal.setMattalEstado( "1" );
                    mattal.setMattalObs( "Alumno matriculado por bloque" );
                    mattal.setClMatricula( mat );
                    mattal.setClSeccion( tmp_sec );
                    mattals.add( mattal );

                    mat.setClMatriculaTallers( mattals );

                    matriculas.add( mat );
                }
            }

            daoM.insertarMatriculas( matriculas );

            HSEstructuraPagoDAO daoEP = (HSEstructuraPagoDAO) ServiceFinder.findBean( "SpringHibernateDaoCLEstructuraPago" );
            List<ClEstructuraPagosDetalle> detalles = daoEP.seleccionarDetalle( this.getEstpag_dest_id() );
            List<ClAlumnoTarifa> alumnoTarifas = new ArrayList<ClAlumnoTarifa>();
            int tmp_tal_id = tmp_sec.getClArbolAcademico().getArbId();
            for ( ClMatricula mat : matriculas ) {
                for ( ClEstructuraPagosDetalle estpagdet : detalles ) {
                    if ( estpagdet.getTalId() == tmp_tal_id ) {
                        ClAlumnoTarifa alutar = new ClAlumnoTarifa();
                        alutar.setAlutarActivo( "1" );
                        alutar.setAlutarAluTipo( "014003" );
                        alutar.setAlutarEstado( "0" );
                        alutar.setAlutarFechaPago( tmp_sec.getSecFinicio() );
                        alutar.setAlutarFechaProrroga( tmp_sec.getSecFinicio() );
                        alutar.setAlutarMonto( estpagdet.getEstpagdetMonto() );
                        alutar.setClAlumno( mat.getClAlumno() );
                        alutar.setClEstructuraPagosDetalle( estpagdet );
                        alutar.setConpagId( estpagdet.getAdConceptoPago().getId() );
                        alutar.setMatId( mat.getMatId() );
                        alutar.setSecId( tmp_sec.getSecId() );

                        alumnoTarifas.add( alutar );
                        break;
                    }
                }
            }
            HSAlumnoTarifaCLDAO daoAT = (HSAlumnoTarifaCLDAO) ServiceFinder.findBean( "SpringHibernateDaoCLAlumnoTarifa" );
            daoAT.generarAlumnosTarifa( alumnoTarifas );
            this.setOncomplete( "javascript:alert('Alumnos matriculados con exito en el taller " + tmp_sec.getClArbolAcademico().getArbDescripcion() + ".');"
                    + "Richfaces.hideModalPanel('mpMatriculados');" );
        }
    }

    private int capturarUsuario() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession( false );
        int id_usu = ((bUsuario) session.getAttribute( "usuario" )).getId_usu();
        return id_usu;
    }

    private boolean validaciones( int caso, ClSeccion sec ) {
        switch ( caso ) {
            case EXCEDE_CANTIDAD_ALUMNOS:
                HSHorarioDAO daoH = (HSHorarioDAO) ServiceFinder.findBean( "SpringHibernateDaoCLHorario" );

                int alumnos_matriculados = daoH.matriculadosSeccion( this.getSec_dest_id() ).size();
                int total_alumnos = getTotalParaMatricular() + alumnos_matriculados;
                return sec.getSecVacMax().intValue() < total_alumnos
                        ? true : false;

            case PERTENECE_AL_MISMO_CURSO:
                int tmp_tal_id = sec.getClArbolAcademico().getArbIdPadre();
                return tmp_tal_id == this.getTal_id()
                        ? true : false;

            default:
                return false;
        }
    }

    public bMatriculaSeccionCL() {
        this.talape_id = 0;

    }

    public bMatriculaSeccionCL( int p ) {
    }

    public void mostrarEstructura( ActionEvent event ) {
        this.setW_textoPago( "Ocultar detalle de pagos" );
        this.setW_imgPago( "/Imagenes/actions/down.png" );
        this.setW_ocultar_estructura( "true" );
        this.setW_ocultar_boton( "false" );
        System.out.println( "ocultar" );
    }

    public void ocultarEstructura( ActionEvent event ) {
        this.setW_textoPago( "Mostrar detalle de pagos" );
        this.setW_imgPago( "/Imagenes/actions/up.png" );
        this.setW_ocultar_estructura( "false" );
        this.setW_ocultar_boton( "true" );
        System.out.println( "mostrar" );

    }

    public void SeleccionMultiple( ActionEvent event ) throws Exception {
        if ( this.listaAlumnosMatriculados.size() > 0 ) {
            for ( int i = 0; i < this.listaAlumnosMatriculados.size(); i++ ) {
                this.listaAlumnosMatriculados.get( i ).setAluMatriculado( ind ); //id del combobox
            }
        }
        ind = !ind;
    }
}
