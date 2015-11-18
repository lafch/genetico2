package net.uch.cursoLibre.managedBeans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.component.UIParameter;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import net.uch.commonService.ServiceFinder;
import net.uch.cursoLibre.hibernateSpringDao.HSAlumnoCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSAlumnoTarifaCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSArbolAcademicoClDao;
import net.uch.cursoLibre.hibernateSpringDao.HSConsultaPublicoDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSEstructuraPagoDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSMatriculaCLDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSSeccionCLDAO;
import net.uch.cursoLibre.managedBeans.beans.BeanCLMatricula;
import net.uch.cursoLibre.managedBeans.beans.BeanClAlumnosTarifa;
import net.uch.cursoLibre.managedBeans.beans.MetodosCL;
import net.uch.mapping.AdPago;
import net.uch.mapping.ClAlumno;
import net.uch.mapping.ClAlumnoTarifa;
import net.uch.mapping.ClArbolAcademico;
import net.uch.mapping.ClEstructuraPagos;
import net.uch.mapping.ClEstructuraPagosDetalle;
import net.uch.mapping.ClMatricula;
import net.uch.mapping.ClMatriculaTaller;
import net.uch.mapping.ClSeccion;
import net.uch.mapping.TbCatalogo;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import net.uch.util.CommonDAO;
import net.uch.util.CommonWeb;

public class bMatriculaCL {

    // CAMPOS
    private String b_codigo;
    private String b_appaterno;
    private String b_apmaterno;
    private String b_nombres;
    private Date b_inicio;
    private List<bMatriculaCL> matriculas = new ArrayList<bMatriculaCL>();
    private int m_mat_id;
    private float m_monto_total;
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
    int nivelEliminar;
    int e_mat_id;
    int e_alu_id;
    private String mensajeEliminar;
    private String oncomplete;
    private boolean verDetalle;
    private String m_imagen_mostrar;
    private String m_texto_mostrar;
    private String suggestion;
    private List<bMatriculaCL> alumnos = new ArrayList<bMatriculaCL>();
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
    private float i_monto_base;
    private float i_monto_pagar;
    private boolean i_agregar;
    private boolean i_ver;
    private boolean i_editar;
    private float i_monto_editar;
    private int i_institucion_id = 0;
    private SelectItem[] i_institucion;
    private int i_are_id = 0;
    private SelectItem[] i_areas;
    private int i_mod_id = 0;
    private SelectItem[] i_modulos;
    private int i_estpag_id;
    private SelectItem[] i_cursos;
    private int i_cur_id = 0;
    private SelectItem[] i_taller;
    private int i_taller_id = 0;
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
    private double promedio;
    private String mensaje_error;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy/MM/dd" );
    // variables puestas x mi..
    private List<bMatriculaCL> listDeudas = new ArrayList<bMatriculaCL>();
    private String deu_alu_codigo;
    private String deu_name_lumno;
    private String deu_modulo;
    private String deu_curso;
    private double deu_montopagado;
    private double deu_montopagar;
    private double deu_saldo;
    private String deu_mensaje;
    private String deu_mostrarmensajedeu;
    private String m_imagen_deuda;
    private int cantCursoDeuda;
    private List<ClEstructuraPagosDetalle> listaEstructuraDetalle = new ArrayList<ClEstructuraPagosDetalle>();
    private String w_textoPago = "Mostrar detalle de pagos";
    private String w_imgPago = "/Imagenes/actions/down.png";
    private String w_ocultar_estructura = "false";
    private String w_ocultar_boton = "true";
    private String style_tarifa = "";
    private int w_diaCobrar = 30;
    private Date fecha_inicio = new Date();
    private List<BeanClAlumnosTarifa> listaDeudaAlumno = new ArrayList<BeanClAlumnosTarifa>();
    private Date w_fechaProrroga = new Date();
    private String w_observacionFecha = "";
    private int w_alutarId;
    private int w_alu_id;
    private String w_estructurapago;
    boolean ind = false;
    private int f_alutarId;
    private int f_mat_tarId;
    private float w_montoNuevo;
    private Date m_fechaPago = new Date();
    private Date m_fechaProrroga = new Date();
    private int m_alutarId;
    private String sModificable = "";
    private String sImagenMod = "/Imagenes/actions/editpaste2.png";
    private boolean m_blCerrable;
    private boolean m_blHabilitar;
    /*PRORROGA PAGO*/
    private Date modal_pago;
    private Date modal_prorroga;
    private float modal_monto;
    private int tmpAlutar = 0;
    
    private int m_usuarioID;
    private String m_usuarioName;

    // CONSTRUCTORES
    public bMatriculaCL() {
    }

    public bMatriculaCL( int p ) {
    }

    // GETTERS AND SETTERS
    public double getPromedio() {
        return promedio;
    }

    public void setPromedio( double promedio ) {
        this.promedio = promedio;
    }

    public String getsImagenMod() {
        return sImagenMod;
    }

    public void setsImagenMod( String sImagenMod ) {
        this.sImagenMod = sImagenMod;
    }

    public String getsModificable() {
        return sModificable;
    }

    public void setsModificable( String sModificable ) {
        this.sModificable = sModificable;
    }

    public Date getM_fechaPago() {
        return m_fechaPago;
    }

    public void setM_fechaPago( Date m_fechaPago ) {
        this.m_fechaPago = m_fechaPago;
    }

    public int getM_alutarId() {
        return m_alutarId;
    }

    public void setM_alutarId( int m_alutarId ) {
        this.m_alutarId = m_alutarId;
    }

    public float getW_montoNuevo() {
        return w_montoNuevo;
    }

    public void setW_montoNuevo( float w_montoNuevo ) {
        this.w_montoNuevo = w_montoNuevo;
    }

    public int getF_alutarId() {
        return f_alutarId;
    }

    public void setF_alutarId( int f_alutarId ) {
        this.f_alutarId = f_alutarId;
    }

    public int getF_mat_tarId() {
        return f_mat_tarId;
    }

    public void setF_mat_tarId( int f_mat_tarId ) {
        this.f_mat_tarId = f_mat_tarId;
    }

    public int getI_cur_id() {
        return i_cur_id;
    }

    public void setI_cur_id( int i_cur_id ) {
        this.i_cur_id = i_cur_id;
    }
    
    public int getI_taller_id() {
        return i_taller_id;
    }

    public void setI_taller_id( int i_taller_id ) {
        this.i_taller_id = i_taller_id;
    }

    public String getW_estructurapago() {
        return w_estructurapago;
    }

    public void setW_estructurapago( String w_estructurapago ) {
        this.w_estructurapago = w_estructurapago;
    }

    public int getW_alu_id() {
        return w_alu_id;
    }

    public void setW_alu_id( int w_alu_id ) {
        this.w_alu_id = w_alu_id;
    }

    public int getW_alutarId() {
        return w_alutarId;
    }

    public void setW_alutarId( int w_alutarId ) {
        this.w_alutarId = w_alutarId;
    }

    public Date getW_fechaProrroga() {
        return w_fechaProrroga;
    }

    public void setW_fechaProrroga( Date w_fechaProrroga ) {
        this.w_fechaProrroga = w_fechaProrroga;
    }

    public String getW_observacionFecha() {
        return w_observacionFecha;
    }

    public void setW_observacionFecha( String w_observacionFecha ) {
        this.w_observacionFecha = w_observacionFecha;
    }

    public List<BeanClAlumnosTarifa> getListaDeudaAlumno() {
        return listaDeudaAlumno;
    }

    public void setListaDeudaAlumno( List<BeanClAlumnosTarifa> listaDeudaAlumno ) {
        this.listaDeudaAlumno = listaDeudaAlumno;
    }

    public String getStyle_tarifa() {
        return style_tarifa;
    }

    public void setStyle_tarifa( String style_tarifa ) {
        this.style_tarifa = style_tarifa;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio( Date fecha_inicio ) {
        this.fecha_inicio = fecha_inicio;
    }

    public int getW_diaCobrar() {
        return w_diaCobrar;
    }

    public void setW_diaCobrar( int w_diaCobrar ) {
        this.w_diaCobrar = w_diaCobrar;
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

    public List<ClEstructuraPagosDetalle> getListaEstructuraDetalle() {
        return listaEstructuraDetalle;
    }

    public void setListaEstructuraDetalle( List<ClEstructuraPagosDetalle> listaEstructuraDetalle ) {
        this.listaEstructuraDetalle = listaEstructuraDetalle;
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

    public void addMatriculas( bMatriculaCL tmp ) {
        this.matriculas.add( tmp );
    }

    public List<bMatriculaCL> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas( List<bMatriculaCL> matriculas ) {
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

    public float getM_monto_total() {
        return m_monto_total;
    }

    public void setM_monto_total( float m_monto_total ) {
        this.m_monto_total = m_monto_total;
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

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion( String suggestion ) {
        this.suggestion = suggestion;
    }

    public List<bMatriculaCL> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos( List<bMatriculaCL> alumnos ) {
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

    public int getI_institucion_id() {
        return i_institucion_id;
    }

    public void setI_institucion_id( int i_institucion_id ) {
        this.i_institucion_id = i_institucion_id;
    }

    public SelectItem[] getI_institucion() throws Exception {
           try
        {
                         HSCatalogoDAO smCatalogo = (HSCatalogoDAO) ServiceFinder.findBean("SpringHibernateDaoCatalogo");
                        List aDocumentosListados = smCatalogo.seleccionarCatalogo("078");
                        int tam=0;
                       
                        for(int i = 0; i < aDocumentosListados.size(); i++)
                {
                    String codigo = ((TbCatalogo)aDocumentosListados.get(i)).getCatCodigoGrupo() + ((TbCatalogo)aDocumentosListados.get(i)).getCatCodigoElemento();
                                        if ( "078004".equals( codigo ) || "078005".equals( codigo ) ) {
                                            System.out.println("NO agrega al combo ");
                                            }else{
                                        tam++;
                                        }
                }
                       
            if(tam>0)
            {
                                i_institucion = new SelectItem[tam + 1];
                                i_institucion[0] = new SelectItem(0, "[Seleccione]");
                for(int i = 0; i < tam; i++)
                {
                    String codigo = ((TbCatalogo)aDocumentosListados.get(i)).getCatCodigoGrupo() + ((TbCatalogo)aDocumentosListados.get(i)).getCatCodigoElemento();
                                        if ( "078004".equals( codigo ) || "078005".equals( codigo ) ) {
                                            //comboInstitucion[i+1] = new SelectItem(codigo,((TbCatalogo)aDocumentosListados.get(i)).getCatDescripcionElemento());
                                            System.out.println("NO agrega al combo ");
                                            }else{
                                        i_institucion[i+1] = new SelectItem(codigo,((TbCatalogo)aDocumentosListados.get(i)).getCatDescripcionElemento());
                                        }
                    //comboInstitucion[i+1] = new SelectItem(codigo,((TbCatalogo)aDocumentosListados.get(i)).getCatDescripcionElemento());                   
                }
            }
            else
            {
                return null;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return i_institucion;

    }

    public void setI_institucion( SelectItem[] i_institucion ) {
        this.i_institucion = i_institucion;
    }

    public int getI_are_id() {
        return i_are_id;
    }

    public void setI_are_id( int i_are_id ) {
        this.i_are_id = i_are_id;
    }

    public SelectItem[] getI_areas() throws Exception {
        ClArbolAcademico arbAcad;
        HSArbolAcademicoClDao arbAcadClDAO;
        List<ClArbolAcademico> lstAreas;

        if ( this.i_institucion_id == 0 ) {
            i_areas = new SelectItem[ 1 ];
        } else {
            arbAcadClDAO = CommonDAO.getClArbolAcademicoDAO();
            lstAreas = arbAcadClDAO.AreasXInstitucion( String.valueOf( i_institucion_id ) );
            i_areas = new SelectItem[ lstAreas.size() + 1 ];
            //             i_areas[0] = new SelectItem( Integer.MAX_VALUE, "[Seleccione]" );

            for ( int i = 0; i < lstAreas.size(); i++ ) {
                arbAcad = lstAreas.get( i );
                i_areas[i + 1] = new SelectItem( arbAcad.getArbId(), arbAcad.getArbDescripcion() );
            }

        }
        i_areas[0] = new SelectItem( 0, "[Seleccione]" );
        return i_areas;



        /*
         * if (i_areas == null) { HSAreaDAO dao = (HSAreaDAO)
         * ServiceFinder.findBean("SpringHibernateDaoCLArea"); List<ClArea>
         * areas = dao.seleccionarArea(""); i_areas = new
         * SelectItem[areas.size() + 1]; i_areas[0] = new SelectItem(0,
         * "[Seleccione]"); for (int i = 0; i < areas.size(); i++) { ClArea a =
         * areas.get(i); i_areas[i + 1] = new SelectItem(a.getAreId(),
         * a.getAreDescripcion()); } } return i_areas;
         */
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
        HSArbolAcademicoClDao arbAcadDAO;
        List<ClArbolAcademico> lstModulos;

        if ( this.i_are_id == 0 ) {
            i_modulos = new SelectItem[ 1 ];
        } else {
            arbAcadDAO = CommonDAO.getClArbolAcademicoDAO();
            lstModulos = arbAcadDAO.seleccionarModulos( this.i_are_id, "" );

            i_modulos = new SelectItem[ lstModulos.size() + 1 ];
            for ( int i = 0; i < lstModulos.size(); i++ ) {
                i_modulos[i + 1] = new SelectItem( lstModulos.get( i ).getArbId(), lstModulos.get( i ).getArbDescripcion() );
            }
        }
        i_modulos[0] = new SelectItem( 0, "[Seleccione]" );
        return i_modulos;
    }

    public void setI_modulos( SelectItem[] i_modulos ) {
        this.i_modulos = i_modulos;
    }

    public SelectItem[] getI_cursos() {
        HSArbolAcademicoClDao arbAcadDAO;
        List<ClArbolAcademico> lstCursos;
        if ( this.i_mod_id == 0 ) {
            i_cursos = new SelectItem[ 1 ];
        } else {
            try {
                arbAcadDAO = CommonDAO.getClArbolAcademicoDAO();
                lstCursos = arbAcadDAO.seleccionarCursos( this.i_mod_id );

                i_cursos = new SelectItem[ lstCursos.size() + 1 ];
                for ( int i = 0; i < lstCursos.size(); i++ ) {
                    i_cursos[i + 1] = new SelectItem( lstCursos.get( i ).getArbId(), lstCursos.get( i ).getArbDescripcion() );
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
    

    public SelectItem[] getI_taller() {
        HSArbolAcademicoClDao arbAcadDAO;
        List<ClArbolAcademico> lstTalleres;
        if ( this.i_cur_id == 0 ) {
            i_taller = new SelectItem[ 1 ];
        } else {
            try {
                arbAcadDAO = CommonDAO.getClArbolAcademicoDAO();
                lstTalleres = arbAcadDAO.seleccionarTalleres(this.i_cur_id );

                i_taller = new SelectItem[ lstTalleres.size() + 1 ];
                for ( int i = 0; i < lstTalleres.size(); i++ ) {
                    i_taller[i + 1] = new SelectItem( lstTalleres.get( i ).getArbId(), lstTalleres.get( i ).getArbDescripcion() );
                }
            } catch ( Exception e ) {
                System.err.println( "Error al cargar areas" );
            }
        }
        i_taller[0] = new SelectItem( 0, "[Seleccione]" );
        return i_taller;
    }

    public void setI_taller( SelectItem[] icursos ) {
        this.i_cursos = icursos;
    }

    public int getI_estpag_id() {
        return i_estpag_id;
    }

    public void setI_estpag_id( int i_estpag_id ) {
        this.i_estpag_id = i_estpag_id;
    }

    /*
    public SelectItem[] getI_estructuras() {
        HSEstructuraPagoDAO estPagoDAP;
        List lstEstPagos;
        try {
            if ( this.i_taller_id == 0 ) {
                i_estructuras = new SelectItem[ 1 ];
            } else {
                estPagoDAP = (HSEstructuraPagoDAO)ServiceFinder.findBean( "SpringHibernateDaoCLEstructuraPago" );
                //List lista = dao.seleccionarEstructurasXModulo(this.i_mod_id);
                lstEstPagos = estPagoDAP.seleccionarEstructurasPagos( this.i_taller_id );
                i_estructuras = new SelectItem[ lstEstPagos.size() + 1 ];
                for ( int i = 0; i < lstEstPagos.size(); i++ ) {
                    i_estructuras[i + 1] = new SelectItem( ( (ClEstructuraPagos)lstEstPagos.get( i ) ).getEstpagId(), ( (ClEstructuraPagos)lstEstPagos.get( i ) ).getEstpagNombre() );
                }
            }
            i_estructuras[0] = new SelectItem( 0, "[Seleccione]" );
        } catch ( Exception e ) {
            System.err.println( "Error al cargar las estructuras de pago en matricula Cursos Libres " + e.getMessage() );
        }
        return i_estructuras;
    }*/

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

    // a partir de aca tengo mis metodos ps...
    public String getM_imagen_deuda() {
        return m_imagen_deuda;
    }

    public void setM_imagen_deuda( String m_imagen_deuda ) {
        this.m_imagen_deuda = m_imagen_deuda;
    }

    public String getDeu_alu_codigo() {
        return deu_alu_codigo;
    }

    public void setDeu_alu_codigo( String deu_alu_codigo ) {
        this.deu_alu_codigo = deu_alu_codigo;
    }

    public List<bMatriculaCL> getListDeudas() {
        return listDeudas;
    }

    public void setListDeudas( List<bMatriculaCL> listDeudas ) {
        this.listDeudas = listDeudas;
    }

    public String getDeu_curso() {
        return deu_curso;
    }

    public void setDeu_curso( String deu_curso ) {
        this.deu_curso = deu_curso;
    }

    public String getDeu_modulo() {
        return deu_modulo;
    }

    public void setDeu_modulo( String deu_modulo ) {
        this.deu_modulo = deu_modulo;
    }

    public double getDeu_montopagado() {
        return deu_montopagado;
    }

    public void setDeu_montopagado( double deu_montopagado ) {
        this.deu_montopagado = deu_montopagado;
    }

    public double getDeu_montopagar() {
        return deu_montopagar;
    }

    public void setDeu_montopagar( double deu_montopagar ) {
        this.deu_montopagar = deu_montopagar;
    }

    public double getDeu_saldo() {
        return deu_saldo;
    }

    public void setDeu_saldo( double deu_saldo ) {
        this.deu_saldo = deu_saldo;
    }

    public String getDeu_name_lumno() {
        return deu_name_lumno;
    }

    public void setDeu_name_lumno( String deu_name_lumno ) {
        this.deu_name_lumno = deu_name_lumno;
    }

    public String getDeu_mensaje() {
        return deu_mensaje;
    }

    public void setDeu_mensaje( String deu_mensaje ) {
        this.deu_mensaje = deu_mensaje;
    }

    public String getDeu_mostrarmensajedeu() {
        return deu_mostrarmensajedeu;
    }

    public void setDeu_mostrarmensajedeu( String deu_mostrarmensajedeu ) {
        this.deu_mostrarmensajedeu = deu_mostrarmensajedeu;
    }

    public int getCantCursoDeuda() {
        return cantCursoDeuda;
    }

    public void setCantCursoDeuda( int cantCursoDeuda ) {
        this.cantCursoDeuda = cantCursoDeuda;
    }

    public int getM_usuarioID() {
        return m_usuarioID;
    }

    public void setM_usuarioID( int m_usuarioID ) {
        this.m_usuarioID = m_usuarioID;
    }

    public String getM_usuarioName() {
        return m_usuarioName;
    }

    public void setM_usuarioName( String m_usuarioName ) {
        this.m_usuarioName = m_usuarioName;
    }
    
    

    // METODOS
    public void AbrirMatricula( ActionEvent event ) throws Exception {
        int iAluId;
        int iCantAlutarDeu;
        ClAlumno alumno;
        HSAlumnoCLDAO aluClDAO;


        this.setOncomplete( "" );
        this.setSuggestion( "" );

        iAluId = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "m_alu_id2" ) ).getValue().toString() );
        aluClDAO = (HSAlumnoCLDAO)ServiceFinder.findBean( "SpringHibernateDaoCLAlumno" );
        alumno = aluClDAO.buscarAlumnoPorAluId( iAluId );

        iCantAlutarDeu = cantidadDeuda( iAluId );
        System.out.println( "cantidad de deuda  es -> " + iCantAlutarDeu );
        if ( iCantAlutarDeu != 0 ) {
            this.setDeu_mensaje( "Tiene deudas   " );
            this.setDeu_mostrarmensajedeu( "Tiene Deuda" );
        } else {
            this.setDeu_mensaje( "" );
            this.setDeu_mostrarmensajedeu( "" );
        }

        this.setI_alu_id( iAluId );
        this.setI_alu_codigo( alumno.getAluCodigo() );
        this.setI_alu_nombre( alumno.getAluAppaterno() + " " + alumno.getAluApmaterno() + " " + alumno.getAluNombres() );

        this.setSecciones( new ArrayList<BeanCLMatricula>() );
        this.setI_modulos( getI_modulos() );
        this.setI_cursos( getI_cursos() );
        this.setI_are_id( 0 );
        this.setI_mod_id( 0 );
        this.setI_cur_id( 0 );
       // this.setI_estructuras( getI_estructuras() );
        this.setI_estpag_id( 0 );
        this.setI_modulo( "" );
        this.setVerMensaje( false );
        this.setVerCursos( false );

        //ocultarEstructura( event );
        //this.setListaEstructuraDetalle( new ArrayList<ClEstructuraPagosDetalle>() );
        this.setOncomplete( "Richfaces.showModalPanel('mpMatricula')" );

    }

    public List<bMatriculaCL> autocomplete( Object suggest ) throws Exception {
        int iCantAlutarDeu;
        String sPref;
        bMatriculaCL alu;
        ClAlumno clAlu;
        HSAlumnoCLDAO aluClDAO;
        List<bMatriculaCL> lstAlumnos;
        List<ClAlumno> alus;

        sPref = (String)suggest;
        lstAlumnos = new ArrayList<bMatriculaCL>();
        aluClDAO = (HSAlumnoCLDAO)ServiceFinder.findBean( "SpringHibernateDaoCLAlumno" );
        if ( sPref.matches( "^\\d*" ) ) {
            alus = aluClDAO.seleccionarAlumnosPorCodigo( sPref );
            if ( alus.size() > 0 ) {
                for ( int i = 0; i < alus.size(); i++ ) {
                    clAlu = alus.get( i );

                    alu = new bMatriculaCL( 0 );
                    iCantAlutarDeu = aluClDAO.consultDeudaALumno( clAlu.getAluId() ).size();

                    if ( iCantAlutarDeu != 0 ) {
                        alu.setDeu_mensaje( "Tiene deudas   " );
                        this.setDeu_mensaje( "Tiene deudas   " );
                    } else {
                        alu.setDeu_mensaje( "" );
                        this.setDeu_mensaje( "" );
                    }

                    alu.setC_alu_id( clAlu.getAluId() );
                    alu.setC_alu_codigo( clAlu.getAluCodigo() );
                    alu.setC_alu_nombre( clAlu.getAluAppaterno().toUpperCase().trim() + " "
                            + clAlu.getAluApmaterno().toUpperCase().trim() + " "
                            + clAlu.getAluNombres().toUpperCase().trim() );
                    lstAlumnos.add( alu );
                }
            }
        } else {
            alus = aluClDAO.seleccionarAlumnosPorApellidos( sPref );
            if ( alus.size() > 0 ) {

                for ( int i = 0; i < alus.size(); i++ ) {
                    clAlu = alus.get( i );
                    alu = new bMatriculaCL( 0 );
                    iCantAlutarDeu = aluClDAO.consultDeudaALumno( clAlu.getAluId() ).size();
                    if ( iCantAlutarDeu != 0 ) {
                        alu.setDeu_mensaje( "Tiene deudas   " );
                        this.setDeu_mensaje( "Tiene deudas   " );

                    } else {
                        alu.setDeu_mensaje( "" );
                        this.setDeu_mensaje( "" );
                    }

                    alu.setC_alu_id( clAlu.getAluId() );
                    alu.setC_alu_codigo( clAlu.getAluCodigo() );
                    alu.setC_alu_nombre( clAlu.getAluAppaterno().toUpperCase().trim() + " "
                            + clAlu.getAluApmaterno().toUpperCase().trim() + " "
                            + clAlu.getAluNombres().toUpperCase().trim() );
                    lstAlumnos.add( alu );
                }
            }
        }

        return lstAlumnos;

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

    public void Seleccionar() throws Exception {
        String sTmpTaller;
        String sTmpSeccion;
        String sTmpInicio;
        String sMensaje;
        String sTipo;
        bMatriculaCL master;
        bMatriculaCL sub_master;
        ClAlumno aluCl;
        ClMatricula matCl;
        ClMatriculaTaller mattal;
        Date fInicio;
        HSAlumnoCLDAO aluDAO;
        HSCatalogoDAO catalogoDAO;
        HSAlumnoTarifaCLDAO alutarClDAO;
        Iterator<ClMatricula> itMat;
        List<ClAlumno> lstAlumnosCl;
        List<ClAlumnoTarifa> lstAlutarCl;

        this.setMatriculas( new ArrayList<bMatriculaCL>() );
        if ( !casillasVacias() ) {
            catalogoDAO = (HSCatalogoDAO)ServiceFinder.findBean( "SpringHibernateDaoCatalogo" );
            aluDAO = (HSAlumnoCLDAO)ServiceFinder.findBean( "SpringHibernateDaoCLAlumno" );
            alutarClDAO = (HSAlumnoTarifaCLDAO)ServiceFinder.findBean( "SpringHibernateDaoCLAlumnoTarifa" );
            lstAlumnosCl = aluDAO.seleccionarAlumnosLibres( this.getB_codigo(), this.getB_appaterno(), this.getB_apmaterno(), this.getB_nombres() );

            if ( !lstAlumnosCl.isEmpty() ) {
                for ( int i = 0; i < lstAlumnosCl.size(); i++ ) {
                    aluCl = lstAlumnosCl.get( i );
                    master = new bMatriculaCL( 0 );

                    master.setM_alu_id( aluCl.getAluId() );

                    sTipo = aluCl.getAluTipo();
                    if ( "057001".equals( sTipo ) ) {
                        master.setM_alu_codigo( "X" + aluCl.getAluCodigo() ); // externo
                    } else if ( "057003".equals( sTipo ) ) {
                        master.setM_alu_codigo( "PRO" + aluCl.getAluCodigo() ); // docente  ..
                    } else {
                        master.setM_alu_codigo( aluCl.getAluCodigo() );
                    }
                    master.setM_alu_nombres( aluCl.getAluNombres().toUpperCase().trim() );
                    master.setM_alu_appaterno( aluCl.getAluAppaterno().toUpperCase().trim() );
                    master.setM_alu_apmaterno( aluCl.getAluApmaterno().toUpperCase().trim() );

                    if ( !aluCl.getClMatriculas().isEmpty() ) {
                        itMat = aluCl.getClMatriculas().iterator();
                        while ( itMat.hasNext() ) {
                            matCl = itMat.next();
                            sub_master = new bMatriculaCL( 1 );
                            if ( "1".equals( matCl.getMatActivo() ) && !"022010".equals( matCl.getMatTipo() ) ) {
                                sub_master.setM_mat_id( matCl.getMatId() );
                                sub_master.setM_mat_fecha( matCl.getMatFecha() );
                                sub_master.setM_mat_tipo( matCl.getMatTipo() );
                                sub_master.setM_tipo( catalogoDAO.seleccionarDescripcion( matCl.getMatTipo() ) );
                                sTmpTaller = "";
                                sTmpSeccion = "";
                                sTmpInicio = "";
                                lstAlutarCl = alutarClDAO.seleccionarTarifasXMatricula( matCl.getMatId(), matCl.getClAlumno().getAluId() );
                                sMensaje = "";
                                if ( lstAlutarCl.size() > 0 ) { // CON ALUMNO_TARIFA OBTENEMOS LOS PAGOS

                                    sub_master.setM_fechaPago( lstAlutarCl.get( 0 ).getAlutarFechaPago() );
                                    sub_master.setM_fechaProrroga( lstAlutarCl.get( 0 ).getAlutarFechaProrroga() );
                                    sub_master.setM_alutarId( lstAlutarCl.get( 0 ).getAlutarId() );
//                                    sMensaje = lstAlutarCl.get( 0 ).getClEstructuraPagosDetalle().getClEstructuraPagos().getEstpagNombre();
                                    if(lstAlutarCl.get( 0 ).getUsuId()!=null){
                                    sub_master.setM_usuarioName(alutarClDAO.usuarioMatricula(lstAlutarCl.get( 0 ).getUsuId()));
                                    }
                                    sMensaje = lstAlutarCl.get( 0 ).getClEstructuraPagosDetalle().getEstpagdetNombre();
                                    sub_master.setM_monto_total( lstAlutarCl.get( 0 ).getAlutarMonto() + lstAlutarCl.get( 0 ).getAlutarMora() );

                                }
                                System.out.println( "mensaje -> " + sMensaje );
                                sub_master.setW_estructurapago( sMensaje );
                                // sub
                                int iIdSec = 0;
                                double promedioTmp;
                                if ( !matCl.getClMatriculaTallers().isEmpty() ) {
                                    Iterator<ClMatriculaTaller> itMatTal = matCl.getClMatriculaTallers().iterator();
                                    while ( itMatTal.hasNext() ) {
                                        mattal = itMatTal.next();
                                        iIdSec = mattal.getClSeccion().getSecId();
                                        sTmpTaller = sTmpTaller.concat( "- " + mattal.getClSeccion().getClArbolAcademico().getArbDescripcion() + "<br>" );
                                        sTmpSeccion = sTmpSeccion.concat( "- " + mattal.getClSeccion().getSecNombre() + "<br>" );
                                        fInicio = mattal.getClSeccion().getSecFinicio();
                                        if ( fInicio != null ) {
                                            sTmpInicio = sTmpInicio.concat( "- " + dateFormat.format( fInicio ) + "<br>" );
                                        } else {
                                            sTmpInicio = sTmpInicio.concat( "- No especifica<br>" );
                                        }
                                    }
                                }
                                try {
                                    promedioTmp = CommonDAO.getClNotasDAO().obtenerPormedio( iIdSec, matCl.getClAlumno().getAluId() );
                                } catch ( Exception ex ) {
                                    promedioTmp = 0;
                                }
                                sub_master.setPromedio( promedioTmp );
                                sub_master.setM_taller( sTmpTaller );
                                sub_master.setM_seccion( sTmpSeccion );
                                sub_master.setM_inicio( sTmpInicio );
                                sub_master.setCerrable( puedeCerrar( matCl.getMatId() ) );
                                sub_master.setHabilitar( puedeHabilitar( matCl.getMatId() ) );
                                master.addMatriculas( sub_master );
                            }
                        }//FIN FOR SUB-TABLE MATRICULAS
                    }//FIN CONDICIONAL DE SUB-TABLE

                    master.setVerDetalle( false );
                    master.setM_imagen_mostrar( "/Imagenes/actions/down.png" );
                    master.setM_imagen_deuda( "/Imagenes/actions/carnet.gif" );
                    master.setM_texto_mostrar( "Mostrar Detalle" );
//                    this.setHabilitaboton(false);
                    this.addMatriculas( master );
                }//FIN FOR TABLE ALUMNOS
            }

        }
    }

    public void SeleccionarMatriculas() throws Exception {
        String sTmpTaller;
        String sTmpSeccion;
        String sTmpInicio;
        String sMensaje;
        String sTipo;
        bMatriculaCL master;
        bMatriculaCL sub_master;
        ClAlumno aluCl;
        ClMatricula matCl;
        ClMatriculaTaller mattal;
        Date fInicio;
        HSAlumnoCLDAO aluDAO;
        HSCatalogoDAO catalogoDAO;
        HSAlumnoTarifaCLDAO alutarClDAO;
        Iterator<ClMatricula> itMat;
        List<ClAlumno> lstAlumnosCl;
        List<ClAlumnoTarifa> lstAlutarCl;

        this.setMatriculas( new ArrayList<bMatriculaCL>() );
        if ( !casillasVacias() ) {
            catalogoDAO = (HSCatalogoDAO)ServiceFinder.findBean( "SpringHibernateDaoCatalogo" );
            aluDAO = (HSAlumnoCLDAO)ServiceFinder.findBean( "SpringHibernateDaoCLAlumno" );
            alutarClDAO = (HSAlumnoTarifaCLDAO)ServiceFinder.findBean( "SpringHibernateDaoCLAlumnoTarifa" );
            lstAlumnosCl = aluDAO.seleccionarAlumnosLibres( this.getB_codigo(), this.getB_appaterno(), this.getB_apmaterno(), this.getB_nombres() );

            if ( !lstAlumnosCl.isEmpty() ) {
                for ( int i = 0; i < lstAlumnosCl.size(); i++ ) {
                    aluCl = lstAlumnosCl.get( i );
                    master = new bMatriculaCL( 0 );

                    master.setM_alu_id( aluCl.getAluId() );

                    sTipo = aluCl.getAluTipo();
                    if ( "057001".equals( sTipo ) ) {
                        master.setM_alu_codigo( "X" + aluCl.getAluCodigo() ); // externo
                    } else if ( "057003".equals( sTipo ) ) {
                        master.setM_alu_codigo( "PRO" + aluCl.getAluCodigo() ); // docente  ..
                    } else {
                        master.setM_alu_codigo( aluCl.getAluCodigo() );
                    }
                    master.setM_alu_nombres( aluCl.getAluNombres().toUpperCase().trim() );
                    master.setM_alu_appaterno( aluCl.getAluAppaterno().toUpperCase().trim() );
                    master.setM_alu_apmaterno( aluCl.getAluApmaterno().toUpperCase().trim() );

                    if ( !aluCl.getClMatriculas().isEmpty() ) {
                        itMat = aluCl.getClMatriculas().iterator();
                        while ( itMat.hasNext() ) {
                            matCl = itMat.next();
                            sub_master = new bMatriculaCL( 1 );
                            if ( "1".equals( matCl.getMatActivo() ) && ( "022001".equals( matCl.getMatTipo() ) || "022010".equals( matCl.getMatTipo() ) ) ) {
                                sub_master.setM_mat_id( matCl.getMatId() );
                                sub_master.setM_mat_fecha( matCl.getMatFecha() );
                                sub_master.setM_mat_tipo( matCl.getMatTipo() );
                                sub_master.setM_tipo( catalogoDAO.seleccionarDescripcion( matCl.getMatTipo() ) );
                                sTmpTaller = "";
                                sTmpSeccion = "";
                                sTmpInicio = "";
                                lstAlutarCl = alutarClDAO.seleccionarTarifasXMatricula( matCl.getMatId(), matCl.getClAlumno().getAluId() );
                                sMensaje = "";
                                if ( lstAlutarCl.size() > 0 ) { // CON ALUMNO_TARIFA OBTENEMOS LOS PAGOS

                                    sub_master.setM_fechaPago( lstAlutarCl.get( 0 ).getAlutarFechaPago() );
                                    sub_master.setM_fechaProrroga( lstAlutarCl.get( 0 ).getAlutarFechaProrroga() );
                                    sub_master.setM_alutarId( lstAlutarCl.get( 0 ).getAlutarId() );
                                    sub_master.setM_usuarioName(alutarClDAO.usuarioMatricula(lstAlutarCl.get( 0).getUsuId()));
//                                    sMensaje = lstAlutarCl.get( 0 ).getClEstructuraPagosDetalle().getClEstructuraPagos().getEstpagNombre();
                                    sMensaje = lstAlutarCl.get( 0 ).getClEstructuraPagosDetalle().getEstpagdetNombre();
                                    sub_master.setM_monto_total( lstAlutarCl.get( 0 ).getAlutarMonto() + lstAlutarCl.get( 0 ).getAlutarMora() );

                                }
                                System.out.println( "mensaje -> " + sMensaje );
                                sub_master.setW_estructurapago( sMensaje );
                                // sub
                                int iIdSec = 0;
                                double promedioTmp;
                                if ( !matCl.getClMatriculaTallers().isEmpty() ) {
                                    Iterator<ClMatriculaTaller> itMatTal = matCl.getClMatriculaTallers().iterator();
                                    while ( itMatTal.hasNext() ) {
                                        mattal = itMatTal.next();
                                        iIdSec = mattal.getClSeccion().getSecId();
                                        sTmpTaller = sTmpTaller.concat( "- " + mattal.getClSeccion().getClArbolAcademico().getArbDescripcion() + "<br>" );
                                        sTmpSeccion = sTmpSeccion.concat( "- " + mattal.getClSeccion().getSecNombre() + "<br>" );
                                        fInicio = mattal.getClSeccion().getSecFinicio();
                                        if ( fInicio != null ) {
                                            sTmpInicio = sTmpInicio.concat( "- " + dateFormat.format( fInicio ) + "<br>" );
                                        } else {
                                            sTmpInicio = sTmpInicio.concat( "- No especifica<br>" );
                                        }
                                    }
                                }
                                try {
                                    promedioTmp = CommonDAO.getClNotasDAO().obtenerPormedio( iIdSec, matCl.getClAlumno().getAluId() );
                                } catch ( Exception ex ) {
                                    promedioTmp = 0;
                                }
                                sub_master.setPromedio( promedioTmp );
                                sub_master.setM_taller( sTmpTaller );
                                sub_master.setM_seccion( sTmpSeccion );
                                sub_master.setM_inicio( sTmpInicio );
                                sub_master.setCerrable( puedeCerrar( matCl.getMatId() ) );
                                sub_master.setHabilitar( puedeHabilitar( matCl.getMatId() ) );
                                master.addMatriculas( sub_master );
                            }
                        }//FIN FOR SUB-TABLE MATRICULAS
                    }//FIN CONDICIONAL DE SUB-TABLE

                    master.setVerDetalle( false );
                    master.setM_imagen_mostrar( "/Imagenes/actions/down.png" );
                    master.setM_imagen_deuda( "/Imagenes/actions/carnet.gif" );
                    master.setM_texto_mostrar( "Mostrar Detalle" );
//                    this.setHabilitaboton(false);
                    this.addMatriculas( master );
                }//FIN FOR TABLE ALUMNOS
            }

        }
    }

    private boolean puedeCerrar( Integer iMatId ) {
        boolean blPuede;
        blPuede = false;
        try {
            blPuede = !CommonDAO.getClAlumnoTarifaDAO().traerListaPagosPorCerrar( iMatId ).isEmpty();
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return blPuede;
    }

    private boolean puedeHabilitar( Integer iMatId ) {
        boolean blPuede;
        blPuede = false;
        try {

            if ( CommonDAO.getClAlumnoTarifaDAO().traerListaPagosPorHabilitar( iMatId ).isEmpty() ) {
                //System.out.println("false");
                blPuede = false;

            } else {
                //System.out.println("true");
                blPuede = true;
            }
            //blPuede = !CommonDAO.getClAlumnoTarifaDAO().traerListaPagosPorHabilitar( iMatId ).isEmpty();
            //blPuede = CommonDAO.getClAlumnoTarifaDAO().traerListaPagosPorHabilitar( iMatId ).iterator().next().getAlutarActivo().equals("0");
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        return blPuede;
    }

    public void SeleccionarEdicionPago() throws Exception {
        String sTmpTaller;
        String sTmpSeccion;
        String sTmpInicio;
        String sMensaje;
        String sTipo;
        bMatriculaCL master;
        bMatriculaCL sub_master;
        ClAlumno aluCl;
        ClMatricula matCl;
        ClMatriculaTaller mattal;
        Date fInicio;
        HSAlumnoCLDAO aluDAO;
        HSCatalogoDAO catalogoDAO;
        HSAlumnoTarifaCLDAO alutarClDAO;
        Iterator<ClMatricula> itMat;
        List<ClAlumno> lstAlumnosCl;
        List<ClAlumnoTarifa> lstAlutarCl;

        this.setMatriculas( new ArrayList<bMatriculaCL>() );
        if ( !casillasVacias() ) {
            catalogoDAO = (HSCatalogoDAO)ServiceFinder.findBean( "SpringHibernateDaoCatalogo" );
            aluDAO = (HSAlumnoCLDAO)ServiceFinder.findBean( "SpringHibernateDaoCLAlumno" );
            alutarClDAO = (HSAlumnoTarifaCLDAO)ServiceFinder.findBean( "SpringHibernateDaoCLAlumnoTarifa" );
            lstAlumnosCl = aluDAO.seleccionarAlumnosLibres( this.getB_codigo(), this.getB_appaterno(), this.getB_apmaterno(), this.getB_nombres() );

            if ( !lstAlumnosCl.isEmpty() ) {
                for ( int i = 0; i < lstAlumnosCl.size(); i++ ) {
                    aluCl = lstAlumnosCl.get( i );
                    master = new bMatriculaCL( 0 );

                    master.setM_alu_id( aluCl.getAluId() );

                    sTipo = aluCl.getAluTipo();
                    if ( "057001".equals( sTipo ) ) {
                        master.setM_alu_codigo( "X" + aluCl.getAluCodigo() ); // externo
                    } else if ( "057003".equals( sTipo ) ) {
                        master.setM_alu_codigo( "PRO" + aluCl.getAluCodigo() ); // docente  ..
                    } else {
                        master.setM_alu_codigo( aluCl.getAluCodigo() );
                    }
                    master.setM_alu_nombres( aluCl.getAluNombres().toUpperCase().trim() );
                    master.setM_alu_appaterno( aluCl.getAluAppaterno().toUpperCase().trim() );
                    master.setM_alu_apmaterno( aluCl.getAluApmaterno().toUpperCase().trim() );

                    if ( !aluCl.getClMatriculas().isEmpty() ) {
                        itMat = aluCl.getClMatriculas().iterator();
                        while ( itMat.hasNext() ) {
                            matCl = itMat.next();
                            sub_master = new bMatriculaCL( 1 );
//                            if ( "1".equals( matCl.getMatActivo() ) && !"022001".equals( matCl.getMatTipo() ) ) {
                            if ( "1".equals( matCl.getMatActivo() ) ) {
//                                sub_master.setsModificable( "true");
//                                sub_master.setsImagenMod("/Imagenes/actions/editpaste2.png");
//                                if(!"022001".equals( matCl.getMatTipo())){
                                sub_master.setsModificable( "false" );
                                sub_master.setsImagenMod( "/Imagenes/actions/editpaste.png" );
//                                }
                                sub_master.setM_mat_id( matCl.getMatId() );
                                sub_master.setM_mat_fecha( matCl.getMatFecha() );
                                sub_master.setM_mat_tipo( matCl.getMatTipo() );
                                sub_master.setM_tipo( catalogoDAO.seleccionarDescripcion( matCl.getMatTipo() ) );
                                sTmpTaller = "";
                                sTmpSeccion = "";
                                sTmpInicio = "";
                                lstAlutarCl = alutarClDAO.seleccionarTarifasXMatricula( matCl.getMatId(), matCl.getClAlumno().getAluId() );

                                sMensaje = "";
                                if ( lstAlutarCl.size() > 0 ) { // CON ALUMNO_TARIFA OBTENEMOS LOS PAGOS

                                    sub_master.setM_fechaPago( lstAlutarCl.get( 0 ).getAlutarFechaPago() );
                                    sub_master.setM_fechaProrroga( lstAlutarCl.get( 0 ).getAlutarFechaProrroga() );
                                    sub_master.setM_alutarId( lstAlutarCl.get( 0 ).getAlutarId() );
                                    sMensaje = lstAlutarCl.get( 0 ).getClEstructuraPagosDetalle().getClEstructuraPagos().getEstpagNombre();
                                    sub_master.setM_monto_total( lstAlutarCl.get( 0 ).getAlutarMonto() + lstAlutarCl.get( 0 ).getAlutarMora() );

                                }
                                sub_master.setW_estructurapago( sMensaje );
                                // sub
                                if ( !matCl.getClMatriculaTallers().isEmpty() ) {
                                    Iterator<ClMatriculaTaller> itMatTal = matCl.getClMatriculaTallers().iterator();
                                    while ( itMatTal.hasNext() ) {
                                        mattal = itMatTal.next();
                                        sTmpTaller = sTmpTaller.concat( "- " + mattal.getClSeccion().getClArbolAcademico().getArbDescripcion() + "<br>" );
                                        sTmpSeccion = sTmpSeccion.concat( "- " + mattal.getClSeccion().getSecNombre() + "<br>" );
                                        fInicio = mattal.getClSeccion().getSecFinicio();
                                        if ( fInicio != null ) {
                                            sTmpInicio = sTmpInicio.concat( "- " + dateFormat.format( fInicio ) + "<br>" );
                                        } else {
                                            sTmpInicio = sTmpInicio.concat( "- No especifica<br>" );
                                        }
                                    }
                                }
                                sub_master.setM_taller( sTmpTaller );
                                sub_master.setM_seccion( sTmpSeccion );
                                sub_master.setM_inicio( sTmpInicio );
                                sub_master.setCerrable( puedeCerrar( matCl.getMatId() ) );
                                sub_master.setHabilitar( puedeHabilitar( matCl.getMatId() ) );
                                master.addMatriculas( sub_master );
                            }
                        }//FIN FOR SUB-TABLE MATRICULAS
                    }//FIN CONDICIONAL DE SUB-TABLE

                    master.setVerDetalle( false );
                    master.setM_imagen_mostrar( "/Imagenes/actions/down.png" );
                    master.setM_imagen_deuda( "/Imagenes/actions/carnet.gif" );
                    master.setM_texto_mostrar( "Mostrar Detalle" );
//                    this.setHabilitaboton(false);
                    this.addMatriculas( master );
                }//FIN FOR TABLE ALUMNOS
            }

        }
    }

    public void mostrarDetalle( ActionEvent event ) throws Exception {
        int iAluIdParam = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "m_alu_id" ) ).getValue().toString() );
        if ( !this.getMatriculas().isEmpty() ) {
            for ( bMatriculaCL bmat : this.getMatriculas() ) {
                if ( iAluIdParam == bmat.getM_alu_id() ) {
                    if ( bmat.isVerDetalle() ) {
                        bmat.setVerDetalle( false );
                        bmat.setM_imagen_mostrar( "/Imagenes/actions/down.png" );
                        bmat.setM_texto_mostrar( "Mostrar Detalle" );
                    } else {
                        bmat.setVerDetalle( true );
                        bmat.setM_imagen_mostrar( "/Imagenes/actions/up.png" );
                        bmat.setM_texto_mostrar( "Ocultar Detalle" );
                    }
                    break;
                }
            }
        }
    }

    public void cambiarMonto( ActionEvent event ) throws Exception {
        this.setOncomplete( "" );
        e_mat_id = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_mat_id_delete" ) ).getValue().toString() );
        e_alu_id = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_alu_id_delete" ) ).getValue().toString() );

        this.setF_alutarId( e_alu_id );
        this.setF_mat_tarId( e_mat_id );

        this.setOncomplete( "Richfaces.showModalPanel('mpEdicionMonto');" );
    }

    public void cerrarPago( ActionEvent event ) throws Exception {
        int iMatId;
        oncomplete = "javascript:alert('El pago se cerró correctamente.');";
        mensajeEliminar = "";

        iMatId = CommonWeb.parseObjToInt( CommonWeb.getParamFromUIParameterUI( event, "p_mat_id_cerrar" ) );
        CommonDAO.getClAlumnoTarifaDAO().cerrarPago( iMatId );
        SeleccionarEdicionPago();

    }

    public void HabilitarMatricula( ActionEvent event ) throws Exception {
        int iMatId;
        oncomplete = "javascript:alert('La matricula habilitada correctamente.');";
        mensajeEliminar = "";

        iMatId = CommonWeb.parseObjToInt( CommonWeb.getParamFromUIParameterUI( event, "p_mat_id_habilitar" ) );
        CommonDAO.getClAlumnoTarifaDAO().HabilitarMatricula( iMatId );
        SeleccionarEdicionPago();

    }

    public void VerificarPagosMatricula( ActionEvent event ) throws Exception {
        int iFlag;
        ClAlumnoTarifa alutar;
        HSAlumnoTarifaCLDAO aluTarClDAO;
        List<ClAlumnoTarifa> lstAlutars;

        this.setOncomplete( "" );
        this.setMensajeEliminar( "" );

        e_mat_id = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_mat_id_delete" ) ).getValue().toString() );
        e_alu_id = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_alu_id_delete" ) ).getValue().toString() );
        nivelEliminar = 0;

        iFlag = 0;
        aluTarClDAO = (HSAlumnoTarifaCLDAO)ServiceFinder.findBean( "SpringHibernateDaoCLAlumnoTarifa" );
        lstAlutars = aluTarClDAO.seleccionarTarifasXMatricula( e_mat_id, e_alu_id );
        if ( !lstAlutars.isEmpty() ) {
            for ( int i = 0; i < lstAlutars.size(); i++ ) {
                alutar = lstAlutars.get( i );
                if ( aluTarClDAO.existenPagosMatricula( alutar.getClAlumno().getAluId(), alutar.getAlutarId() ) ) {
                    iFlag++;
                }
            }
            if ( iFlag > 0 ) {
                this.setMensajeEliminar( "La matrícula tiene un pago válido asociado, SOLO se eliminará la matrícula. ¿Desea continuar?" );
                nivelEliminar = 1;
            } else {
                this.setMensajeEliminar( "¿Está seguro de Eliminar la matrícula?" );
                nivelEliminar = 2;
            }
        } else {
            this.setMensajeEliminar( "¿Está seguro de Eliminar la matrícula?" );
            nivelEliminar = 1;
        }
        this.setOncomplete( "Richfaces.showModalPanel('mp2')" );

    }

    public void VerificarPagosMatriculaAnulacion( ActionEvent event ) throws Exception {
        int iFlag;
        ClAlumnoTarifa alutar;
        HSAlumnoTarifaCLDAO aluTarClDAO;
        List<ClAlumnoTarifa> lstAlutars;

        this.setOncomplete( "" );
        this.setMensajeEliminar( "" );

        e_mat_id = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_mat_id_delete" ) ).getValue().toString() );
        e_alu_id = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_alu_id_delete" ) ).getValue().toString() );
        nivelEliminar = 0;

        iFlag = 0;
        aluTarClDAO = (HSAlumnoTarifaCLDAO)ServiceFinder.findBean( "SpringHibernateDaoCLAlumnoTarifa" );
        lstAlutars = aluTarClDAO.seleccionarTarifasXMatricula( e_mat_id, e_alu_id );
        if ( !lstAlutars.isEmpty() ) {
            for ( int i = 0; i < lstAlutars.size(); i++ ) {
                alutar = lstAlutars.get( i );
                if ( aluTarClDAO.existenPagosMatricula( alutar.getClAlumno().getAluId(), alutar.getAlutarId() ) ) {
                    iFlag++;
                }
            }
            if ( iFlag > 0 ) {
                this.setMensajeEliminar( "La matrícula tiene un pago válido asociado, Se procedera a anular la matricula. ¿Desea continuar?" );
                nivelEliminar = 1;
            } else {
                this.setMensajeEliminar( "¿Está seguro de Anular la matrícula?" );
                nivelEliminar = 2;
            }
        } else {
            this.setMensajeEliminar( "¿Está seguro de Anular la matrícula?" );
            nivelEliminar = 1;
        }
        this.setOncomplete( "Richfaces.showModalPanel('mp2')" );

    }

    public void AnularMatricula( ActionEvent event ) throws Exception {
        HSMatriculaCLDAO matClDAO;
        HSAlumnoTarifaCLDAO aluTarDAO;
        HSConsultaPublicoDAO conPubDAO = CommonDAO.getClConsultaPublicoDAO();

        List<ClAlumnoTarifa> lstAlutars;
        List<Integer> lstAlutarIDs;

        this.setOncomplete( "" );
        matClDAO = (HSMatriculaCLDAO)ServiceFinder.findBean( "SpringHibernateDaoCLMatricula" );
        aluTarDAO = (HSAlumnoTarifaCLDAO)ServiceFinder.findBean( "SpringHibernateDaoCLAlumnoTarifa" );
        matClDAO.anularMatricula( e_mat_id );
        conPubDAO.eliminarMatriculacl( e_mat_id );
        if ( nivelEliminar != 1 ) {
            lstAlutars = aluTarDAO.seleccionarTarifasXMatricula( e_mat_id, e_alu_id );
            lstAlutarIDs = new ArrayList<Integer>( lstAlutars.size() );
            for ( ClAlumnoTarifa alutar : lstAlutars ) {
                lstAlutarIDs.add( alutar.getAlutarId() );
            }
            aluTarDAO.eliminarAlumnosTarifa( lstAlutarIDs );
        }
        this.setOncomplete( "javascript:alert('Proceso de Eliminación Exitoso.');"
                + "Richfaces.hideModalPanel('mp2')" );
    }

    public void AnularMatriculaDevolucion( ActionEvent event ) throws Exception {
        HSMatriculaCLDAO matClDAO;
        HSConsultaPublicoDAO conPubDAO;

        this.setOncomplete( "" );
        matClDAO = (HSMatriculaCLDAO)ServiceFinder.findBean( "SpringHibernateDaoCLMatricula" );
        conPubDAO = (HSConsultaPublicoDAO)ServiceFinder.findBean( "SpringHibernateDaoConsultaPublico" );
        matClDAO.anularMatriculaDevolucion( e_mat_id );
        conPubDAO.delMatriculaConsulta( e_mat_id );

        this.setOncomplete( "javascript:alert('Proceso de anulacion de matricula ha sido exitoso.');"
                + "Richfaces.hideModalPanel('mp2')" );
    }

    public void MostrarCursos( ActionEvent event ) throws Exception {
        MetodosCL metodoCl;
        HSSeccionCLDAO seccionDAO;
        List<ClSeccion> lstSecModulo;


        seccionDAO = (HSSeccionCLDAO)ServiceFinder.findBean( "SpringHibernateDaoCLSeccion" );
        lstSecModulo = seccionDAO.listarSeccionesXCurso( this.getI_taller_id());

        if ( !lstSecModulo.isEmpty() ) {
            metodoCl = new MetodosCL();
            this.setSecciones( metodoCl.mostrarCursosLibresAperturador( lstSecModulo ) );
            this.setVerCursos( true );
            this.setVerMensaje( false );
            cargarEstructuraPagoDetalle();

        } else {
            this.setMensaje_error( "No hay secciones abiertas o con horario asignado para proceder a matricular." );
            this.setVerCursos( false );
            this.setVerMensaje( true );
        }
    }

    public void DeseleccionarTodos( ActionEvent event ) throws Exception {
        if ( this.getSecciones().size() > 0 ) {
            for ( int i = 0; i < this.getSecciones().size(); i++ ) {
                if ( this.getSecciones().get( i ).isI_agregar() ) {
                    this.getSecciones().get( i ).setI_agregar( false );
                }
            }
        }
    }

    public void SeleccionarTodos( ActionEvent event ) throws Exception {
        if ( this.getSecciones().size() > 0 ) {
            for ( int i = 0; i < this.getSecciones().size(); i++ ) {
                if ( !( this.getSecciones().get( i ).isI_agregar() ) ) {
                    this.getSecciones().get( i ).setI_agregar( true );
                }
            }
        }
    }

    public void EditarMonto( ActionEvent event ) throws Exception {
        int iSecIdParam = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_sec_id" ) ).getValue().toString() );
        if ( this.getSecciones().size() > 0 ) {
            for ( int i = 0; i < this.getSecciones().size(); i++ ) {
                if ( this.getSecciones().get( i ).getI_sec_id() == iSecIdParam ) {
                    this.getSecciones().get( i ).setI_monto_editar( this.getSecciones().get( i ).getI_monto_pagar() );
                    this.getSecciones().get( i ).setI_ver( false );
                    this.getSecciones().get( i ).setI_editar( true );
                    i = this.getSecciones().size();
                }
            }
        }
    }

    public void GuardarMonto( ActionEvent event ) throws Exception {
        int iSecIdParam = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_sec_id" ) ).getValue().toString() );
        if ( this.getSecciones().size() > 0 ) {
            for ( int i = 0; i < this.getSecciones().size(); i++ ) {
                if ( this.getSecciones().get( i ).getI_sec_id() == iSecIdParam ) {
                    this.getSecciones().get( i ).setI_monto_pagar( this.getSecciones().get( i ).getI_monto_editar() );
                    this.getSecciones().get( i ).setI_ver( true );
                    this.getSecciones().get( i ).setI_editar( false );
                    i = this.getSecciones().size();
                }
            }
        }
    }

    public void CancelarMonto( ActionEvent event ) throws Exception {
        int iSecIdParam = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "p_sec_id" ) ).getValue().toString() );
        if ( this.getSecciones().size() > 0 ) {
            for ( int i = 0; i < this.getSecciones().size(); i++ ) {
                if ( this.getSecciones().get( i ).getI_sec_id() == iSecIdParam ) {
                    this.getSecciones().get( i ).setI_monto_pagar( this.getSecciones().get( i ).getI_monto_pagar() );
                    this.getSecciones().get( i ).setI_ver( true );
                    this.getSecciones().get( i ).setI_editar( false );
                    i = this.getSecciones().size();
                }
            }
        }
    }

    public void MatricularSeleccionados( ActionEvent event ) throws Exception {

        String sMensaje;

        ExternalContext context;
        HttpSession session1;
        MetodosCL metodo;

        this.setOncomplete( "" );


        context = FacesContext.getCurrentInstance().getExternalContext();
        session1 = (HttpSession)context.getSession( false );
        metodo = new MetodosCL();
        sMensaje = "javascript:alert('El alumno tiene deuda; No se puede Matricula')";
        if ( cantidadDeuda( this.getI_alu_id() ) == 0 ) {
            if ( this.getI_mod_id() > 0 ) {
                sMensaje = metodo.matricularCursoLibre( this.getI_alu_id(), this.getSecciones(), this.listaEstructuraDetalle, session1, "", 0, this.getFecha_inicio(), this.getW_diaCobrar(), this.getI_sec_id(), 2 );
            } else {
                sMensaje = "javascript:alert('Seleccione Estructura de Pago')";
            }
        }
        this.setOncomplete( sMensaje );

    }

    public void mostrarDeudaAlumno( ActionEvent event ) throws Exception {
        int iAluId;
        String sNomAlumno;
        ClAlumno alumno;
        HSAlumnoCLDAO aluClDAO;
        List<BeanClAlumnosTarifa> lstBAluTar;

        this.setOncomplete( "" );
        this.setListaDeudaAlumno( new ArrayList<BeanClAlumnosTarifa>() );
        iAluId = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "mmm" ) ).getValue().toString() );
        this.setW_alu_id( iAluId );
        aluClDAO = (HSAlumnoCLDAO)ServiceFinder.findBean( "SpringHibernateDaoCLAlumno" );
        alumno = aluClDAO.buscarAlumnoPorAluId( iAluId );
        this.setDeu_alu_codigo( alumno.getAluCodigo() );
        sNomAlumno = alumno.getAluAppaterno() + " " + alumno.getAluApmaterno() + " " + alumno.getAluNombres();
        this.setDeu_name_lumno( sNomAlumno.toUpperCase() );
        lstBAluTar = listarDeudadAlumnos( iAluId );
        this.setListaDeudaAlumno( lstBAluTar );
        this.setOncomplete( "Richfaces.showModalPanel('mpDeuda');" );

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

//    public void cargarEstructuraPagoDetalle( ActionEvent event ) {
//        HSEstructuraPagoDAO estPagDAO = (HSEstructuraPagoDAO)ServiceFinder.findBean( "SpringHibernateDaoCLEstructuraPago" );
//        if ( this.i_estpag_id > 0 ) {
//            this.setListaEstructuraDetalle( estPagDAO.listarEstructuraDetalle( this.i_estpag_id ) );
//        }
//
//    }
    
    public void cargarEstructuraPagoDetalle() {
        HSEstructuraPagoDAO estPagDAO = (HSEstructuraPagoDAO)ServiceFinder.findBean( "SpringHibernateDaoCLEstructuraPago" );
        if ( this.i_taller_id > 0 ) {
            this.setListaEstructuraDetalle( estPagDAO.listarEstructuraDetalle( this.i_taller_id ) );
        }

    }

    public void abrirProrroga( ActionEvent event ) {
        int iAlutarId;
        String sAlutarPro;
        long lAlutarPro;

        iAlutarId = Integer.parseInt( ( (UIParameter)event.getComponent().findComponent( "alutarId" ) ).getValue().toString() );
        sAlutarPro = ( (UIParameter)event.getComponent().findComponent( "alutarPro" ) ).getValue().toString();
        lAlutarPro = Long.parseLong( sAlutarPro );

        this.setW_fechaProrroga( new Date( lAlutarPro ) );
        this.setW_observacionFecha( "" );
        this.setW_alutarId( iAlutarId );
        this.setOncomplete( "Richfaces.showModalPanel('mpEdicion');" );
    }

    public void guardarAlutarMonto( ActionEvent event ) throws Exception {
        HSAlumnoTarifaCLDAO alutarClDAO = (HSAlumnoTarifaCLDAO)ServiceFinder.findBean( "SpringHibernateDaoCLAlumnoTarifa" );
        this.setOncomplete( "" );

        if ( this.getW_montoNuevo() > -100 ) {
            System.out.println( "alutarId: " + this.getF_alutarId() );
            System.out.println( "matId: " + this.getF_mat_tarId() );
            System.out.println( "nuevo monto: " + this.getW_montoNuevo() );
            alutarClDAO.EditarMonto( this.getF_alutarId(), this.getF_mat_tarId(), this.getW_montoNuevo() );

            this.setOncomplete( "javascript:alert('Actualizacion Satisfactoria.');"
                    + "Richfaces.hideModalPanel('mpEdicionMonto');" );
        } else {
            this.setOncomplete( "javascript:alert('Ingrese monto')" );
        }
    }

    public void guardarProrroga( ActionEvent event ) throws Exception {
        HSAlumnoTarifaCLDAO alutarClDAO = (HSAlumnoTarifaCLDAO)ServiceFinder.findBean( "SpringHibernateDaoCLAlumnoTarifa" );
        this.setOncomplete( "" );
        if ( this.getW_observacionFecha().trim().length() > 0 ) {
            alutarClDAO.actualizarFechaProrroga( this.getW_fechaProrroga(), this.getW_observacionFecha(), this.getW_alutarId() );
            this.setOncomplete( "Richfaces.hideModalPanel('mpEdicion');" );
            this.setListaDeudaAlumno( listarDeudadAlumnos( this.getW_alu_id() ) );
            if ( cantidadDeuda( this.getW_alu_id() ) != 0 ) {
                this.setDeu_mensaje( "Tiene deudas   " );
                this.setDeu_mostrarmensajedeu( "Tiene Deuda" );
            } else {
                this.setDeu_mensaje( "" );
                this.setDeu_mostrarmensajedeu( "" );
            }
        } else {
            this.setOncomplete( "javascript:alert('Ingrese la observacion por el cambio de fecha')" );
        }

    }

    public List<BeanClAlumnosTarifa> listarDeudadAlumnos( int iAluId ) throws Exception {
        int i;
        double dSaldo;
        String sNomModulo;
        BeanClAlumnosTarifa bMontoDeuda;
        HSAlumnoCLDAO aluClDAO;
        HSArbolAcademicoClDao arbDAO;
        List<ClAlumnoTarifa> lstAlutarIds;
        List<BeanClAlumnosTarifa> lstBAlutar;
        List<AdPago> lstPagos;
        List<String> lstXMod;

        aluClDAO = (HSAlumnoCLDAO)ServiceFinder.findBean( "SpringHibernateDaoCLAlumno" );
        lstBAlutar = new ArrayList<BeanClAlumnosTarifa>();
        lstAlutarIds = (ArrayList<ClAlumnoTarifa>)aluClDAO.consultDeudaALumno( iAluId );

        this.setCantCursoDeuda( lstAlutarIds.size() );
        i = 1;
        for ( ClAlumnoTarifa alumnotar : lstAlutarIds ) {
            bMontoDeuda = new BeanClAlumnosTarifa();
            bMontoDeuda.setAlutar_orden( i + 1 );
            arbDAO = CommonDAO.getClArbolAcademicoDAO();
            lstXMod = (ArrayList<String>)arbDAO.buscarXSeccion( alumnotar.getSecId() );
            sNomModulo = "No Definido";
            if ( !lstXMod.isEmpty() ) {
                sNomModulo = lstXMod.get( 0 );
            }
            bMontoDeuda.setAlumnoTarifa( alumnotar );
            bMontoDeuda.setAlutar_style( "font-size: 10px;" );
            bMontoDeuda.setAlutar_fecha( alumnotar.getAlutarFechaProrroga().getTime() );
            if ( alumnotar.getAlutarFechaProrroga().before( new Date() ) ) {
                bMontoDeuda.setAlutar_style( "font-size: 10px; font-weight: bold; color: red;" );
            }
            // ESTA 0 YA NO SE UTILIZA PORQUE SOLO ES PREMATRICULADO
            /*if ( alumnotar.getAlutarEstado().equals( "0" ) ) {
             System.out.println( "cero deuda" );
             bMontoDeuda.setDeu_modulo( sNomModulo );
             bMontoDeuda.setDeu_curso( alumnotar.getClEstructuraPagosDetalle().getEstpagdetNombre() );//nombre de la deuda
             bMontoDeuda.setDeu_montopagar( bMontoDeuda.getAlumnoTarifa().getAlutarMonto() + bMontoDeuda.getAlumnoTarifa().getAlutarMora() );
             bMontoDeuda.setDeu_montopagado( 0.00 );
             bMontoDeuda.setDeu_saldo( alumnotar.getAlutarMonto() + alumnotar.getAlutarMora() );
             }*/ if ( alumnotar.getAlutarEstado().equals( "1" ) ) { // caudno es 1
                lstPagos = aluClDAO.listaPagosAlumno( alumnotar.getAlutarId() );
                for ( AdPago pag : lstPagos ) {

                    System.out.println( "Pagó Parcialmente" );
                    bMontoDeuda.setDeu_modulo( sNomModulo );
                    bMontoDeuda.setDeu_curso( alumnotar.getClEstructuraPagosDetalle().getEstpagdetNombre() );//nombre de la deuda
                    bMontoDeuda.setDeu_montopagar( alumnotar.getAlutarMonto() + alumnotar.getAlutarMora() );
                    bMontoDeuda.setDeu_montopagado( pag.getPagMonto() );
                    dSaldo = ( alumnotar.getAlutarMonto() + alumnotar.getAlutarMora() ) - pag.getPagMonto();
                    bMontoDeuda.setDeu_saldo( dSaldo );
                }
            }
            lstBAlutar.add( bMontoDeuda );
        }
        return lstBAlutar;
    }

    public int cantidadDeuda( int alu_id ) {
        int iCantDeuda;
        HSAlumnoCLDAO aluClDAO;
        List<ClAlumnoTarifa> lstAlutarIds;

        aluClDAO = CommonDAO.getClAlumnoDAO();
        lstAlutarIds = (ArrayList<ClAlumnoTarifa>)aluClDAO.consultDeudaALumno( alu_id );

        iCantDeuda = 0;
        for ( ClAlumnoTarifa alumno : lstAlutarIds ) {
            if ( alumno.getAlutarFechaProrroga().before( new Date() ) ) {
                iCantDeuda++;
            }
        }
        return iCantDeuda;
    }

    public void SeleccionMultiple( ActionEvent event ) throws Exception {
        if ( this.getSecciones().size() > 0 ) {
            for ( int i = 0; i < this.getSecciones().size(); i++ ) {
                this.getSecciones().get( i ).setI_agregar( ind ); //id del combobox
            }
        }
        ind = !ind;
    }

    public boolean isCerrable() {
        return this.m_blCerrable;
    }

    public void setCerrable( boolean blCerrable ) {
        this.m_blCerrable = blCerrable;
    }

    public boolean isHabilitar() {
        return this.m_blHabilitar;
    }

    public void setHabilitar( boolean blHabilitar ) {
        this.m_blHabilitar = blHabilitar;
    }

    public Date getM_fechaProrroga() {
        return m_fechaProrroga;
    }

    public void setM_fechaProrroga( Date m_fechaProrroga ) {
        this.m_fechaProrroga = m_fechaProrroga;
    }

    public void salvaEditar( ActionEvent event ) {

        Object alutar_monto = ( (UIParameter)event.getComponent().findComponent( "alutar_monto" ) ).getValue();
        Object alutar_pago = ( (UIParameter)event.getComponent().findComponent( "alutar_pago" ) ).getValue();
        Object alutar_prorroga = ( (UIParameter)event.getComponent().findComponent( "alutar_prorroga" ) ).getValue();
        Object alutar_id = ( (UIParameter)event.getComponent().findComponent( "alutar_id" ) ).getValue();
        Object mat_tipo = ( (UIParameter)event.getComponent().findComponent( "matTipo" ) ).getValue();

        this.setModal_monto( Float.parseFloat( String.valueOf( alutar_monto ) ) );
        this.setModal_pago( (Date)alutar_pago );
        this.setModal_prorroga( (Date)alutar_prorroga );
        this.setTmpAlutar( Integer.parseInt( String.valueOf( alutar_id ) ) );
        this.setM_mat_tipo( String.valueOf( mat_tipo ) );

    }

    public void editar() {
        this.setOncomplete( "" );
        try {

            HSAlumnoTarifaCLDAO alutarClDAO = (HSAlumnoTarifaCLDAO)ServiceFinder.findBean( "SpringHibernateDaoCLAlumnoTarifa" );
            this.setOncomplete( "" );
            if ( this.getM_mat_tipo().equals( "022001" ) ) {
                alutarClDAO.actualizarAlumnoTarifa( this.getModal_pago(), this.getModal_prorroga(), this.getModal_monto(), this.getTmpAlutar(), this.getM_mat_tipo() );
            } else {
                alutarClDAO.actualizarAlumnoTarifa( this.getModal_pago(), this.getModal_prorroga(), this.getModal_monto(), this.getTmpAlutar(), "022005" );
            }
            //this.setOncomplete( "Richfaces.hideModalPanel('mpEdicion');" );
            this.setListaDeudaAlumno( listarDeudadAlumnos( this.getW_alu_id() ) );
            this.setOncomplete( "javascript:alert('SE ACTUALIZO CORRECTAMENTE LOS DATOS');"
                    + "Richfaces.hideModalPanel('mpEditar')" );
            SeleccionarEdicionPago();
        } catch ( Exception e ) {
            System.err.println( "error en el update de hibernate" );
        }
    }

    public float getModal_monto() {
        return modal_monto;
    }

    public void setModal_monto( float modal_monto ) {
        this.modal_monto = modal_monto;
    }

    public Date getModal_pago() {
        return modal_pago;
    }

    public void setModal_pago( Date modal_pago ) {
        this.modal_pago = modal_pago;
    }

    public Date getModal_prorroga() {
        return modal_prorroga;
    }

    public void setModal_prorroga( Date modal_prorroga ) {
        this.modal_prorroga = modal_prorroga;
    }

    public int getTmpAlutar() {
        return tmpAlutar;
    }

    public void setTmpAlutar( int tmpAlutar ) {
        this.tmpAlutar = tmpAlutar;
    }
}