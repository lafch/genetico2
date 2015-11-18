/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.cursoLibre.managedBeans;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.imageio.ImageIO;
import net.uch.academica.hibernateSpringDao.HSAlumnoDAO;
import net.uch.cursoLibre.hibernateSpringDao.HSAlumnoCLDAO;
import net.uch.cursoLibre.managedBeans.beans.MetodosCL;
import net.uch.mapping.AcAlumno;
import net.uch.mapping.ClAlumno;
import net.uch.tablaSistema.hibernateSpringDao.HSCatalogoDAO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.uch.mapping.TbCatalogo;
import net.uch.mapping.TbDistrito;
import net.uch.tablaSistema.hibernateSpringDao.HSUbigeoDAO;
import net.uch.util.CommonDAO;

/**
 *
 * @author richard, cesardl
 */
public class bAlumnoCL {

    private String b_codigo = "";
    private String b_paterno = "";
    private String b_materno = "";
    private String b_nombre = "";
    /**
     * ********************************
     */
    private String alu_codigo = "";
    private String alu_appaterno = "";
    private String alu_apmaterno = "";
    private String alu_nombres = "";
    private int alu_id;
    private String alu_dni = "";
    private String alu_mail = "";
    private String alu_tipo = "085001";
    private String detAluExterno = "0";
    private SelectItem[] detallesAluExterno;
    private String aluInterUnidad = "0";
    private SelectItem[] aluInterUnidades;
    private String detInterUnidad = "0";
    private SelectItem[] detInterUnidades;
    private Blob alu_foto;
    private String alu_activo = "1";
    private String alu_duplicado = "";
    private String alu_procedencia = "";
    private String alu_password = "";
    private int alu_cp = 0;
    private String alu_temporal = "";
    private String alu_sexo = "";
    private String alu_distrito = "";
    private String alu_direccion = "";
    private String alu_telefono = "";
    private String alu_celular = "";
    private Date alu_fecha_nacimiento = new Date();
    private String alu_unidad = "";
    private String alu_forma_pago = "";
    private String alu_nombre_familiar = "";
    private String alu_parentesco = "";
    private String b_sexo = "";
    private String b_distrito = "";
    private String b_unidad = "";
    private String b_forma_pago = "";
    private String b_procedencia = "";
    private String b_parentesco = "";
    /**
     * *****************************
     */
    private String provincia = "000000";
    private String departamento = "000000";
    private String distrito = "000000";
    private SelectItem[] cboProvincia;
    private SelectItem[] cboDepartamento;
    private SelectItem[] cboDistrito;
    //*******************************
    private SelectItem[] cbosexo;
    private SelectItem[] cboUnidad;
    private SelectItem[] cboFormaPago;
    private SelectItem[] cboParentesco;
    private SelectItem[] cboProcedencia;
    private SelectItem[] cboTipo;
    private String titulo_ver_campos;
    private String imagen_ver_campos;
    private boolean mostrar;
    private boolean ocultar;
    private String oncomplete;
    private List listaAlumno;
    //*******************************
    private String univ_codigo;
    private AcAlumno univ_alumno;
    private int find_image;
    //*******************************
    private List<AcAlumno> listaEnlace;
    private ClAlumno enl_alumno;

    public bAlumnoCL( int param ) {
        //System.out.println( "[bAlumnoCL(int)] << ENTER" );
        //System.out.println( "[bAlumnoCL(int)] >> EXIT" );
    }

    public bAlumnoCL() throws Exception {
        //System.out.println( "[bAlumnoCL] << ENTER" );
        MetodosCL metodos = new MetodosCL();

//        cboDepartamento = metodos.listarDepartamento();
        cboUnidad = metodos.listarCatalogoGrupo( "042" );

        cboFormaPago = metodos.listarCatalogoGrupo( "041" );

        cboParentesco = metodos.listarCatalogoGrupo( "040" );

        cboProcedencia = metodos.listarCatalogoGrupo( "023" );

//        cboTipo = metodos.listarCatalogoGrupo( "057" );//TIPO DE ALUMNO LIBRE
        cboTipo = metodos.listarCatalogoGrupo( "085" );//TIPO DE ALUMNO LIBRE - INFO 2013
        this.find_image = 0;
        this.enl_alumno = null;
        //System.out.println( "[bAlumnoCL] >> EXIT" );
    }

    public String getB_codigo() {
        //System.out.println( "[getB_codigo] << ENTER" );
       // System.out.println( "[getB_codigo] >> EXIT" );
        return b_codigo;
    }

    public void setB_codigo( String b_codigo ) {
       // System.out.println( "[setB_codigo] << ENTER" );
        this.b_codigo = b_codigo;
        //System.out.println( "[setB_codigo] >> EXIT" );
    }

    public String getB_paterno() {
        //System.out.println( "[getB_paterno] << ENTER" );
        //System.out.println( "[getB_paterno] >> EXIT" );
        return b_paterno;
    }

    public void setB_paterno( String b_paterno ) {
        //System.out.println( "[setB_paterno] << ENTER" );
        this.b_paterno = b_paterno;
        //System.out.println( "[setB_paterno] >> EXIT" );
    }

    public String getB_materno() {
        //System.out.println( "[getB_materno] << ENTER" );
        //System.out.println( "[getB_materno] >> EXIT" );
        return b_materno;
    }

    public void setB_materno( String b_materno ) {
        //System.out.println( "[setB_materno] << ENTER" );
        this.b_materno = b_materno;
        //System.out.println( "[setB_materno] >> EXIT" );
    }

    public String getB_nombre() {
        //System.out.println( "[getB_nombre] << ENTER" );
        //System.out.println( "[getB_nombre] >> EXIT" );
        return b_nombre;
    }

    public void setB_nombre( String b_nombre ) {
        //System.out.println( "[setB_nombre] << ENTER" );
        this.b_nombre = b_nombre;
        //System.out.println( "[setB_nombre] >> EXIT" );
    }

    public List getListaAlumno() {
        //System.out.println( "[getListaAlumno] << ENTER" );
        //System.out.println( "[getListaAlumno] >> EXIT" );
        return listaAlumno;
    }

    public void setListaAlumno( List listaAlumno ) {
       // System.out.println( "[setListaAlumno] << ENTER" );
        this.listaAlumno = listaAlumno;
        //System.out.println( "[setListaAlumno] >> EXIT" );
    }

    public int getAlu_id() {
        //System.out.println( "[getAlu_id] << ENTER" );
        //System.out.println( "[getAlu_id] >> EXIT" );
        return alu_id;
    }

    public void setAlu_id( int alu_id ) {
        //System.out.println( "[setAlu_id] << ENTER" );
        this.alu_id = alu_id;
        //System.out.println( "[setAlu_id] >> EXIT" );
    }

    public String getAlu_dni() {
        //System.out.println( "[getAlu_dni] << ENTER" );
        //System.out.println( "[getAlu_dni] >> EXIT" );
        return alu_dni;
    }

    public void setAlu_dni( String alu_dni ) {
        //System.out.println( "[setAlu_dni] << ENTER" );
        this.alu_dni = alu_dni;
        //System.out.println( "[setAlu_dni] >> EXIT" );
    }

    public String getAlu_mail() {
        //System.out.println( "[getAlu_mail] << ENTER" );
        //System.out.println( "[getAlu_mail] >> EXIT" );
        return alu_mail;
    }

    public void setAlu_mail( String alu_mail ) {
        //System.out.println( "[setAlu_mail] << ENTER" );
        this.alu_mail = alu_mail;
        //System.out.println( "[setAlu_mail] >> EXIT" );
    }

    public String getAlu_tipo() {
        //System.out.println( "[getAlu_tipo] << ENTER" );
        //System.out.println( "[getAlu_tipo] >> EXIT" );
        return alu_tipo;
    }

    public void setAlu_tipo( String alu_tipo ) {
        //System.out.println( "[setAlu_tipo] << ENTER" );
        this.alu_tipo = alu_tipo;
        //System.out.println( "[setAlu_tipo] >> EXIT" );
    }

    public String getDetAluExterno() {
        return detAluExterno;
    }

    public void setDetAluExterno( String detAluExterno ) {
        this.detAluExterno = detAluExterno;
    }

    public SelectItem[] getDetallesAluExterno() {
        int iSize;
        TbCatalogo catDetAluExt;
        List<TbCatalogo> lstCatDetAluExte;
        try {
            if ( "085001".equals( alu_tipo ) ) {
                lstCatDetAluExte = CommonDAO.getTbCatalogoDAO().seleccionarCatalogo( "086" );
                iSize = lstCatDetAluExte.size();
                detallesAluExterno = new SelectItem[ iSize + 1 ];
                for ( int i = 0; i < iSize; i++ ) {
                    catDetAluExt = lstCatDetAluExte.get( i );
                    detallesAluExterno[i + 1] = new SelectItem( catDetAluExt.getCatCodigoGrupo() + catDetAluExt.getCatCodigoElemento(), catDetAluExt.getCatDescripcionElemento() );
                }
            } else {
                detallesAluExterno = new SelectItem[ 1 ];
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
            detallesAluExterno = new SelectItem[ 1 ];
        }
        detallesAluExterno[0] = new SelectItem( "", "[Seleccione]" );
        return detallesAluExterno;
    }

    public void setDetallesAluExterno( SelectItem[] detallesAluExterno ) {
        this.detallesAluExterno = detallesAluExterno;
    }

    public String getAluInterUnidad() {
        return aluInterUnidad;
    }

    public void setAluInterUnidad( String aluInterUnidad ) {
        this.aluInterUnidad = aluInterUnidad;
    }

    public SelectItem[] getAluInterUnidades() {
        int iSize;
        TbCatalogo catDetAluExt;
        List<TbCatalogo> lstCatDetAluExte;
        try {
            if ( "085002".equals( alu_tipo ) ) {
                lstCatDetAluExte = CommonDAO.getTbCatalogoDAO().seleccionarCatalogo( "087" );
                iSize = lstCatDetAluExte.size();
                aluInterUnidades = new SelectItem[ iSize + 1 ];
                for ( int i = 0; i < iSize; i++ ) {
                    catDetAluExt = lstCatDetAluExte.get( i );
                    aluInterUnidades[i + 1] = new SelectItem( catDetAluExt.getCatCodigoGrupo() + catDetAluExt.getCatCodigoElemento(), catDetAluExt.getCatDescripcionElemento() );
                }
            } else {
                aluInterUnidades = new SelectItem[ 1 ];
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
            aluInterUnidades = new SelectItem[ 1 ];
        }
        aluInterUnidades[0] = new SelectItem( "", "[Seleccione]" );
        return aluInterUnidades;
    }

    public void setAluInterUnidades( SelectItem[] aluInterUnidades ) {
        this.aluInterUnidades = aluInterUnidades;
    }

    public String getDetInterUnidad() {
        return detInterUnidad;
    }

    public void setDetInterUnidad( String detInterUnidad ) {
        this.detInterUnidad = detInterUnidad;
    }

    public SelectItem[] getDetInterUnidades() {
        int iSize;
        TbCatalogo catDetAluExt;
        List<TbCatalogo> lstCatDetAluExte;
        try {
            if ( "085002".equals( alu_tipo ) ) {
                lstCatDetAluExte = CommonDAO.getTbCatalogoDAO().seleccionarCatalogo( "088" );
                iSize = lstCatDetAluExte.size();
                detInterUnidades = new SelectItem[ iSize + 1 ];
                for ( int i = 0; i < iSize; i++ ) {
                    catDetAluExt = lstCatDetAluExte.get( i );
                    detInterUnidades[i + 1] = new SelectItem( catDetAluExt.getCatCodigoGrupo() + catDetAluExt.getCatCodigoElemento(), catDetAluExt.getCatDescripcionElemento() );
                }
            } else {
                detInterUnidades = new SelectItem[ 1 ];
            }
        } catch ( Exception ex ) {
            ex.printStackTrace();
            detInterUnidades = new SelectItem[ 1 ];
        }
        detInterUnidades[0] = new SelectItem( "", "[Seleccione]" );
        return detInterUnidades;
    }

    public void setDetInterUnidades( SelectItem[] detInterUnidades ) {
        this.detInterUnidades = detInterUnidades;
    }

    public Blob getAlu_foto() {
        //System.out.println( "[getAlu_foto] << ENTER" );
        //System.out.println( "[getAlu_foto] >> EXIT" );
        return alu_foto;
    }

    public void setAlu_foto( Blob alu_foto ) {
        //System.out.println( "[setAlu_foto] << ENTER" );
        this.alu_foto = alu_foto;
        //System.out.println( "[setAlu_foto] >> EXIT" );
    }

    public String getAlu_activo() {
        //System.out.println( "[getAlu_activo] << ENTER" );
        //System.out.println( "[getAlu_activo] >> EXIT" );
        return alu_activo;
    }

    public void setAlu_activo( String alu_activo ) {
        //System.out.println( "[setAlu_activo] << ENTER" );
        this.alu_activo = alu_activo;
        //System.out.println( "[setAlu_activo] >> EXIT" );
    }

    public String getAlu_duplicado() {
       // System.out.println( "[getAlu_duplicado] << ENTER" );
        //System.out.println( "[getAlu_duplicado] >> EXIT" );
        return alu_duplicado;
    }

    public void setAlu_duplicado( String alu_duplicado ) {
        //System.out.println( "[setAlu_duplicado] << ENTER" );
        //this.alu_duplicado = alu_duplicado;
        System.out.println( "[setAlu_duplicado] >> EXIT" );
    }

    public String getAlu_procedencia() {
        //System.out.println( "[getAlu_procedencia] << ENTER" );
        //System.out.println( "[getAlu_procedencia] >> EXIT" );
        return alu_procedencia;
    }

    public void setAlu_procedencia( String alu_procedencia ) {
        //System.out.println( "[setAlu_procedencia] << ENTER" );
        this.alu_procedencia = alu_procedencia;
        //System.out.println( "[setAlu_procedencia] >> EXIT" );
    }

    public String getAlu_password() {
        //System.out.println( "[getAlu_password] << ENTER" );
        //System.out.println( "[getAlu_password] >> EXIT" );
        return alu_password;
    }

    public void setAlu_password( String alu_password ) {
        //System.out.println( "[setAlu_password] << ENTER" );
        this.alu_password = alu_password;
        //System.out.println( "[setAlu_password] >> EXIT" );
    }

    public int getAlu_cp() {
        //System.out.println( "[getAlu_cp] << ENTER" );
        //System.out.println( "[getAlu_cp] >> EXIT" );
        return alu_cp;
    }

    public void setAlu_cp( int alu_cp ) {
        //System.out.println( "[setAlu_cp] << ENTER" );
        this.alu_cp = alu_cp;
       // System.out.println( "[setAlu_cp] >> EXIT" );
    }

    public String getAlu_temporal() {
        //System.out.println( "[getAlu_temporal] << ENTER" );
        //System.out.println( "[getAlu_temporal] >> EXIT" );
        return alu_temporal;
    }

    public void setAlu_temporal( String alu_temporal ) {
        //System.out.println( "[setAlu_temporal] << ENTER" );
        this.alu_temporal = alu_temporal;
        //System.out.println( "[setAlu_temporal] >> EXIT" );
    }

    public String getAlu_sexo() {
        //System.out.println( "[getAlu_sexo] << ENTER" );
        //System.out.println( "[getAlu_sexo] >> EXIT" );
        return alu_sexo;
    }

    public void setAlu_sexo( String alu_sexo ) {
        //System.out.println( "[setAlu_sexo] << ENTER" );
        this.alu_sexo = alu_sexo;
        //System.out.println( "[setAlu_sexo] >> EXIT" );
    }

    public String getAlu_distrito() {
        //System.out.println( "[getAlu_distrito] << ENTER" );
        //System.out.println( "[getAlu_distrito] >> EXIT" );
        return alu_distrito;
    }

    public void setAlu_distrito( String alu_distrito ) {
        //System.out.println( "[setAlu_distrito] << ENTER" );
        this.alu_distrito = alu_distrito;
        //System.out.println( "[setAlu_distrito] >> EXIT" );
    }

    public String getAlu_direccion() {
        //System.out.println( "[getAlu_direccion] << ENTER" );
        //System.out.println( "[getAlu_direccion] >> EXIT" );
        return alu_direccion;
    }

    public void setAlu_direccion( String alu_direccion ) {
        //System.out.println( "[setAlu_direccion] << ENTER" );
        //System.out.println( "[setAlu_direccion] >> EXIT" );
        this.alu_direccion = alu_direccion;
    }

    public String getAlu_telefono() {
        //System.out.println( "[getAlu_telefono] << ENTER" );
        //System.out.println( "[getAlu_telefono] >> EXIT" );
        return alu_telefono;
    }

    public void setAlu_telefono( String alu_telefono ) {
        //System.out.println( "[setAlu_telefono] << ENTER" );
        this.alu_telefono = alu_telefono;
        //System.out.println( "[setAlu_telefono] >> EXIT" );
    }

    public String getAlu_celular() {
        //System.out.println( "[getAlu_celular] << ENTER" );
        //System.out.println( "[getAlu_celular] >> EXIT" );
        return alu_celular;
    }

    public void setAlu_celular( String alu_celular ) {
        //System.out.println( "[setAlu_celular] << ENTER" );
        this.alu_celular = alu_celular;
        //System.out.println( "[setAlu_celular] >> EXIT" );
    }

    public Date getAlu_fecha_nacimiento() {
        //System.out.println( "[getAlu_fecha_nacimiento] << ENTER" );
        //System.out.println( "[getAlu_fecha_nacimiento] >> EXIT" );
        return alu_fecha_nacimiento;
    }

    public void setAlu_fecha_nacimiento( Date alu_fecha_nacimiento ) {
        //System.out.println( "[setAlu_fecha_nacimiento] << ENTER" );
        this.alu_fecha_nacimiento = alu_fecha_nacimiento;
        //System.out.println( "[setAlu_fecha_nacimiento] >> EXIT" );
    }

    public String getAlu_unidad() {
        //System.out.println( "[getAlu_unidad] << ENTER" );
        //System.out.println( "[getAlu_unidad] >> EXIT" );
        return alu_unidad;
    }

    public void setAlu_unidad( String alu_unidad ) {
        //System.out.println( "[setAlu_unidad] << ENTER" );
        this.alu_unidad = alu_unidad;
        //System.out.println( "[setAlu_unidad] >> EXIT" );
    }

    public String getAlu_forma_pago() {
        //System.out.println( "[getAlu_forma_pago] << ENTER" );
        //System.out.println( "[getAlu_forma_pago] >> EXIT" );
        return alu_forma_pago;
    }

    public void setAlu_forma_pago( String alu_forma_pago ) {
        //System.out.println( "[setAlu_forma_pago] << ENTER" );
        this.alu_forma_pago = alu_forma_pago;
        //System.out.println( "[setAlu_forma_pago] >> EXIT" );
    }

    public String getAlu_nombre_familiar() {
        //System.out.println( "[getAlu_nombre_familiar] << ENTER" );
        //System.out.println( "[getAlu_nombre_familiar] >> EXIT" );
        return alu_nombre_familiar;
    }

    public void setAlu_nombre_familiar( String alu_nombre_familiar ) {
        //System.out.println( "[setAlu_nombre_familiar] << ENTER" );
        //System.out.println( "[setAlu_nombre_familiar] >> EXIT" );
        this.alu_nombre_familiar = alu_nombre_familiar;
    }

    public String getAlu_parentesco() {
        //System.out.println( "[getAlu_parentesco] << ENTER" );
        //System.out.println( "[getAlu_parentesco] >> EXIT" );
        return alu_parentesco;
    }

    public void setAlu_parentesco( String alu_parentesco ) {
        //System.out.println( "[setAlu_parentesco] << ENTER" );
        this.alu_parentesco = alu_parentesco;
        //System.out.println( "[setAlu_parentesco] >> EXIT" );
    }

    public String getB_sexo() {
        //System.out.println( "[getB_sexo] << ENTER" );
       // System.out.println( "[getB_sexo] >> EXIT" );
        return b_sexo;
    }

    public void setB_sexo( String b_sexo ) {
        //System.out.println( "[setB_sexo] << ENTER" );
        this.b_sexo = b_sexo;
        //System.out.println( "[setB_sexo] >> EXIT" );
    }

    public String getB_distrito() {
        //System.out.println( "[getB_distrito] << ENTER" );
        //System.out.println( "[getB_distrito] >> EXIT" );
        return b_distrito;
    }

    public void setB_distrito( String b_distrito ) {
        //System.out.println( "[setB_distrito] << ENTER" );
        this.b_distrito = b_distrito;
        //System.out.println( "[setB_distrito] >> EXIT" );
    }

    public String getB_unidad() {
        //System.out.println( "[getB_unidad] << ENTER" );
        //System.out.println( "[getB_unidad] >> EXIT" );
        return b_unidad;
    }

    public void setB_unidad( String b_unidad ) {
        //System.out.println( "[setB_unidad] << ENTER" );
        this.b_unidad = b_unidad;
        //System.out.println( "[setB_unidad] >> EXIT" );
    }

    public String getB_forma_pago() {
        //System.out.println( "[getB_forma_pago] << ENTER" );
        //System.out.println( "[getB_forma_pago] >> EXIT" );
        return b_forma_pago;
    }

    public void setB_forma_pago( String b_forma_pago ) {
        //System.out.println( "[setB_forma_pago] << ENTER" );
        this.b_forma_pago = b_forma_pago;
        //System.out.println( "[setB_forma_pago] >> EXIT" );
    }

    public String getB_procedencia() {
        //System.out.println( "[getB_procedencia] << ENTER" );
        //System.out.println( "[getB_procedencia] >> EXIT" );
        return b_procedencia;
    }

    public void setB_procedencia( String b_procedencia ) {
        //System.out.println( "[setB_procedencia] << ENTER" );
        this.b_procedencia = b_procedencia;
        //System.out.println( "[setB_procedencia] >> EXIT" );
    }

    public String getB_parentesco() {
        //System.out.println( "[getB_parentesco] << ENTER" );
        //System.out.println( "[getB_parentesco] >> EXIT" );
        return b_parentesco;
    }

    public void setB_parentesco( String b_parentesco ) {
        //System.out.println( "[setB_parentesco] << ENTER" );
        this.b_parentesco = b_parentesco;
        //System.out.println( "[setB_parentesco] >> EXIT" );
    }

    public String getAlu_codigo() {
        //System.out.println( "[getAlu_codigo] << ENTER" );
        //System.out.println( "[getAlu_codigo] >> EXIT" );
        return alu_codigo;
    }

    public void setAlu_codigo( String alu_codigo ) {
        //System.out.println( "[setAlu_codigo] << ENTER" );
        this.alu_codigo = alu_codigo;
        //System.out.println( "[setAlu_codigo] >> EXIT" );
    }

    public String getAlu_appaterno() {
        //System.out.println( "[getAlu_appaterno] << ENTER" );
        //System.out.println( "[getAlu_appaterno] >> EXIT" );
        return alu_appaterno;
    }

    public void setAlu_appaterno( String alu_appaterno ) {
        //System.out.println( "[setAlu_appaterno] << ENTER" );
        this.alu_appaterno = alu_appaterno;
        //System.out.println( "[setAlu_appaterno] >> EXIT" );
    }

    public String getAlu_apmaterno() {
        //System.out.println( "[getAlu_apmaterno] << ENTER" );
        //System.out.println( "[getAlu_apmaterno] >> EXIT" );
        return alu_apmaterno;
    }

    public void setAlu_apmaterno( String alu_apmaterno ) {
        //System.out.println( "[setAlu_apmaterno] << ENTER" );
        this.alu_apmaterno = alu_apmaterno;
        //System.out.println( "[setAlu_apmaterno] >> EXIT" );
    }

    public String getAlu_nombres() {
        //System.out.println( "[getAlu_nombres] << ENTER" );
        //System.out.println( "[getAlu_nombres] >> EXIT" );
        return alu_nombres;
    }

    public void setAlu_nombres( String alu_nombres ) {
        //System.out.println( "[setAlu_nombres] << ENTER" );
        this.alu_nombres = alu_nombres;
        //System.out.println( "[setAlu_nombres] >> EXIT" );
    }

    public String getProvincia() {
        //System.out.println( "[getProvincia] << ENTER" );
        //System.out.println( "[getProvincia] >> EXIT" );
        return provincia;
    }

    public void setProvincia( String provincia ) {
        //System.out.println( "[setProvincia] << ENTER" );
        this.provincia = provincia;
        //System.out.println( "[setProvincia] >> EXIT" );
    }

    public String getDepartamento() {
        //System.out.println( "[getDepartamento] << ENTER" );
        //System.out.println( "[getDepartamento] >> EXIT" );
        return departamento;
    }

    public void setDepartamento( String departamento ) {
       // System.out.println( "[setDepartamento] << ENTER" );
        this.departamento = departamento;
        //System.out.println( "[setDepartamento] >> EXIT" );
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito( String distrito ) {
        //System.out.println( "[setDistrito] << ENTER" );
        this.distrito = distrito;
        //System.out.println( "[setDistrito] >> EXIT" );
    }

    public SelectItem[] getCboProvincia() throws Exception {
        //System.out.println( "[getCboProvincia] << ENTER" );

        MetodosCL metodo = new MetodosCL();
        cboProvincia = metodo.listarProvincia( this.departamento );

        //System.out.println( "[getCboProvincia] >> EXIT" );
        return cboProvincia;
    }

    public void setCboProvincia( SelectItem[] cboProvincia ) {
       // System.out.println( "[setCboProvincia] << ENTER" );
        this.cboProvincia = cboProvincia;
       // System.out.println( "[setCboProvincia] >> EXIT" );
    }

    public SelectItem[] getCboDepartamento() {
        //System.out.println( "[getCboDepartamento] << ENTER" );
        try {
            HSUbigeoDAO dao = CommonDAO.getUbigeoDAO();
            List listaDe = dao.seleccionarDepartamento();
            cboDepartamento = new SelectItem[ listaDe.size() + 1 ];
            cboDepartamento[0] = new SelectItem( "000000", "[Seleccione]" );
            for ( int i = 0; i < (cboDepartamento.length - 1); i++ ) {
                cboDepartamento[i + 1] = new SelectItem( ((TbDistrito) listaDe.get( i )).getId(), ((TbDistrito) listaDe.get( i )).getDisDes() );
                //System.out.println( "Departamento: " + cboDepartamento[i + 1] );
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }
        //System.out.println( "[getCboDepartamento] >> EXIT" );
        return cboDepartamento;
    }

    public void setCboDepartamento( SelectItem[] cboDepartamento ) {
        //System.out.println( "[setCboDepartamento] << ENTER" );
        this.cboDepartamento = cboDepartamento;
        //System.out.println( "[setCboDepartamento] >> EXIT" );
    }

    public SelectItem[] getCboDistrito() throws Exception {
        //System.out.println( "[getCboDistrito] << ENTER" );
        MetodosCL metodo = new MetodosCL();
        cboDistrito = metodo.listarDistrito( this.getDepartamento(), this.getProvincia() );
        //System.out.println( "[getCboDistrito] >> EXIT" );
        return cboDistrito;
    }

    public void setCboDistrito( SelectItem[] cboDistrito ) {
        //System.out.println( "[setCboDistrito] << ENTER" );
        //System.out.println( "[setCboDistrito] >> EXIT" );
        this.cboDistrito = cboDistrito;
    }

    public SelectItem[] getCbosexo() {
        //System.out.println( "[getCbosexo] << ENTER" );
        MetodosCL metodo = new MetodosCL();
        cbosexo = metodo.listarCatalogoGrupo( "004" );

        //System.out.println( "[getCbosexo] >> EXIT" );
        return cbosexo;
    }

    public void setCbosexo( SelectItem[] cbosexo ) {
        //System.out.println( "[setCbosexo] << ENTER" );
        this.cbosexo = cbosexo;
        //System.out.println( "[setCbosexo] >> EXIT" );
    }

    public SelectItem[] getCboUnidad() {
        //System.out.println( "[getCboUnidad] << ENTER" );
        //System.out.println( "[getCboUnidad] >> EXIT" );
        return cboUnidad;
    }

    public void setCboUnidad( SelectItem[] cboUnidad ) {
        //System.out.println( "[setCboUnidad] << ENTER" );
        this.cboUnidad = cboUnidad;
        //System.out.println( "[setCboUnidad] >> EXIT" );
    }

    public SelectItem[] getCboFormaPago() {
        //System.out.println( "[getCboFormaPago] << ENTER" );
        //System.out.println( "[getCboFormaPago] >> EXIT" );
        return cboFormaPago;
    }

    public void setCboFormaPago( SelectItem[] cboFormaPago ) {
        //System.out.println( "[setCboFormaPago] << ENTER" );
        this.cboFormaPago = cboFormaPago;
        //System.out.println( "[setCboFormaPago] >> EXIT" );
    }

    public SelectItem[] getCboParentesco() {
        //System.out.println( "[getCboParentesco] << ENTER" );
        //System.out.println( "[getCboParentesco] >> EXIT" );
        return cboParentesco;
    }

    public void setCboParentesco( SelectItem[] cboParentesco ) {
        //System.out.println( "[setCboParentesco] << ENTER" );
        this.cboParentesco = cboParentesco;
        //System.out.println( "[setCboParentesco] >> EXIT" );
    }

    public SelectItem[] getCboProcedencia() {
        //System.out.println( "[getCboProcedencia] << ENTER" );
        //System.out.println( "[getCboProcedencia] >> EXIT" );
        return cboProcedencia;
    }

    public void setCboProcedencia( SelectItem[] cboProcedencia ) {
        //System.out.println( "[setCboProcedencia] << ENTER" );
        this.cboProcedencia = cboProcedencia;
        //System.out.println( "[setCboProcedencia] >> EXIT" );
    }

    public SelectItem[] getCboTipo() {
        //System.out.println( "[getCboTipo] << ENTER" );
        //System.out.println( "[getCboTipo] >> EXIT" );
        return cboTipo;
    }

    public void setCboTipo( SelectItem[] cboTipo ) {
        //System.out.println( "[setCboTipo] << ENTER" );
        this.cboTipo = cboTipo;
        //System.out.println( "[setCboTipo] >> EXIT" );
    }

    public String getOncomplete() {
        //System.out.println( "[getOncomplete] << ENTER" );
        //System.out.println( "[getOncomplete] >> EXIT" );
        return oncomplete;
    }

    public void setOncomplete( String oncomplete ) {
        //System.out.println( "[setOncomplete] << ENTER" );
        this.oncomplete = oncomplete;
        //System.out.println( "[setOncomplete] >> EXIT" );
    }

    public String getTitulo_ver_campos() {
        //System.out.println( "[getTitulo_ver_campos] << ENTER" );
        //System.out.println( "[getTitulo_ver_campos] >> EXIT" );
        return titulo_ver_campos;
    }

    public void setTitulo_ver_campos( String titulo_ver_campos ) {
        //System.out.println( "[setTitulo_ver_campos] << ENTER" );
        this.titulo_ver_campos = titulo_ver_campos;
        //System.out.println( "[setTitulo_ver_campos] >> EXIT" );
    }

    public String getImagen_ver_campos() {
        //System.out.println( "[getImagen_ver_campos] << ENTER" );
        //System.out.println( "[getImagen_ver_campos] >> EXIT" );
        return imagen_ver_campos;
    }

    public void setImagen_ver_campos( String imagen_ver_campos ) {
        //System.out.println( "[setImagen_ver_campos] << ENTER" );
        this.imagen_ver_campos = imagen_ver_campos;
        //System.out.println( "[setImagen_ver_campos] >> EXIT" );
    }

    public boolean isMostrar() {
        //System.out.println( "[isMostrar] << ENTER" );
        //System.out.println( "[isMostrar] >> EXIT" );
        return mostrar;
    }

    public void setMostrar( boolean mostrar ) {
        //System.out.println( "[setMostrar] << ENTER" );
        this.mostrar = mostrar;
        //System.out.println( "[setMostrar] >> EXIT" );
    }

    public boolean isOcultar() {
        //System.out.println( "[isOcultar] << ENTER" );
        //System.out.println( "[isOcultar] >> EXIT" );
        return ocultar;
    }

    public void setOcultar( boolean ocultar ) {
       // System.out.println( "[setOcultar] << ENTER" );
        this.ocultar = ocultar;
        //System.out.println( "[setOcultar] >> EXIT" );
    }

    /**
     * 1 AcAlumno | 2 ClAlumno
     *
     * @return
     */
    public int getFind_image() {
        //System.out.println( "[getFind_image] << ENTER" );
        //System.out.println( "[getFind_image] >> EXIT" );
        return find_image;
    }

    /**
     * 1 AcAlumno | 2 ClAlumno
     *
     * @param find_image
     */
    public void setFind_image( int find_image ) {
        //System.out.println( "[setFind_image] << ENTER" );
        this.find_image = find_image;
        //System.out.println( "[setFind_image] >> EXIT" );
    }

    public AcAlumno getUniv_alumno() {
        //System.out.println( "[getUniv_alumno] << ENTER" );
        if ( this.univ_alumno == null ) {
            this.univ_alumno = new AcAlumno();
        }
        //System.out.println( "[getUniv_alumno] >> EXIT" );
        return univ_alumno;
    }

    public void setUniv_alumno( AcAlumno univ_alumno ) {
        //System.out.println( "[setUniv_alumno(AcAlumno)] << ENTER" );
        this.univ_alumno = univ_alumno;
        //System.out.println( "[setUniv_alumno(AcAlumno)] >> EXIT" );
    }

    public String getUniv_codigo() {
        //System.out.println( "[getUniv_codigo] << ENTER" );
        //System.out.println( "[getUniv_codigo] >> EXIT" );
        return univ_codigo;
    }

    public void setUniv_codigo( String univ_codigo ) {
        //System.out.println( "[setUniv_codigo(String)] << ENTER" );
        //System.out.println( "[setUniv_codigo(String)] >> EXIT" );
        this.univ_codigo = univ_codigo;
    }

    public void seleccionar() throws Exception {
        //System.out.println( "[seleccionar] << ENTER" );
        bAlumnoCL ba;
        HSAlumnoCLDAO alumnoDAO;
        HSCatalogoDAO catalogoDAO;
        List<ClAlumno> lstAlumLibres;

        this.setOncomplete( "" );
        if ( this.b_paterno.trim().length() == 0
                && this.b_materno.trim().length() == 0
                && this.b_nombre.trim().length() == 0
                && this.b_codigo.trim().length() == 0 ) {
            this.setOncomplete( "javascript:alert('Ingrese algun parametro para buscar');" );
        } else {
            setListaAlumno( new ArrayList() );
            catalogoDAO = CommonDAO.getTbCatalogoDAO();
            alumnoDAO = CommonDAO.getClAlumnoDAO();
            lstAlumLibres = alumnoDAO.seleccionarAlumnosLibres( this.getB_codigo(), this.getB_paterno(), this.getB_materno(), this.getB_nombre() );

            for ( int i = 0; i < lstAlumLibres.size(); i++ ) {
                ba = new bAlumnoCL( 0 );
                ClAlumno alumno = lstAlumLibres.get( i );

                ba.setAlu_activo( alumno.getAluActivo() );
                ba.setAlu_celular( alumno.getAluCelular() );
                ba.setAlu_direccion( alumno.getAluDireccion() );
                ba.setAlu_distrito( alumno.getAluDistrito() );
                ba.setAlu_dni( alumno.getAluDni() );
                ba.setAlu_duplicado( alumno.getAluDuplicado() );
                ba.setAlu_fecha_nacimiento( alumno.getAluFechaNacimiento() );
                ba.setAlu_forma_pago( alumno.getAluFormaPago() );
                ba.setAlu_foto( alumno.getAluFoto() );
                ba.setAlu_id( alumno.getAluId() );
                ba.setAlu_mail( alumno.getAluMail() );
                ba.setAlu_nombre_familiar( alumno.getAluNombreFamiliar() );
                ba.setAlu_parentesco( alumno.getAluParentesco() );
                ba.setAlu_password( alumno.getAluPassword() );
                ba.setAlu_procedencia( alumno.getAluProcedencia() );
                ba.setAlu_sexo( alumno.getAluSexo() );
                ba.setAlu_telefono( alumno.getAluTelefono() );
                ba.setAlu_temporal( alumno.getAluTemporal() );
                ba.setAlu_tipo( alumno.getAluTipo() );
                ba.setAlu_unidad( alumno.getAluUnidad() );
                ba.setAlu_apmaterno( alumno.getAluApmaterno() );
                ba.setAlu_appaterno( alumno.getAluAppaterno() );
                ba.setAlu_nombres( alumno.getAluNombres() );
                ba.setAlu_codigo( alumno.getAluCodigo() );
                ba.setB_materno( alumno.getAluApmaterno() );
                ba.setB_paterno( alumno.getAluAppaterno() );
                ba.setB_nombre( alumno.getAluNombres() );
                ba.setB_codigo( alumno.getAluCodigo() );
                try {
                    ba.setB_forma_pago( catalogoDAO.seleccionarDescripcion( alumno.getAluFormaPago() ) );
                    ba.setB_unidad( catalogoDAO.seleccionarDescripcion( alumno.getAluUnidad() ) );
                    ba.setB_procedencia( catalogoDAO.seleccionarDescripcion( alumno.getAluProcedencia() ) );
                    ba.setB_parentesco( catalogoDAO.seleccionarDescripcion( alumno.getAluParentesco() ) );

                } catch ( Exception e ) {
                    ba.setB_forma_pago( "" );
                    ba.setB_unidad( "" );
                    ba.setB_procedencia( "" );
                    ba.setB_parentesco( "" );
                }
                listaAlumno.add( ba );
            }
        }
        //System.out.println( "[seleccionar] >> EXIT" );
    }

    public void nuevo() {
        //System.out.println( "[nuevo] << ENTER" );
        limpiar();

        this.setFind_image( 0 );
        this.setTitulo_ver_campos( "Adicionar Datos" );
        this.setImagen_ver_campos( "" );
        this.setMostrar( false );
        //System.out.println( "[nuevo] >> EXIT" );

    }

    public void mostrarDatos( ActionEvent event ) throws Exception {
        //System.out.println( "[mostrarDatos] << ENTER" );
        if ( this.isMostrar() ) {
            this.setTitulo_ver_campos( "Adicionar Datos" );
            this.setImagen_ver_campos( "/Imagenes/actions/down.png" );
            this.setMostrar( false );
        } else {
            this.setTitulo_ver_campos( "Ocultar Datos" );
            this.setImagen_ver_campos( "/Imagenes/actions/up.png" );
            this.setMostrar( true );
        }
        //System.out.println( "[mostrarDatos] >> EXIT" );
    }

    public void limpiar() {
       // System.out.println( "[limpiar] << ENTER" );
        /*
         * b_codigo = ""; b_paterno = ""; b_materno = ""; b_nombre = "";
         */
        /**
         * ********************************
         */
        alu_codigo = "";
        alu_appaterno = "";
        alu_apmaterno = "";
        alu_nombres = "";
        alu_id = 0;
        alu_dni = "";
        alu_mail = "";
        alu_tipo = "085001";
        alu_foto = null;
        alu_activo = "1";
        alu_duplicado = "";
        alu_procedencia = "023009";
        alu_password = "";
        alu_cp = 0;
        alu_temporal = "";
        alu_sexo = "";
        alu_distrito = "";
        alu_direccion = "";
        alu_telefono = "";
        alu_celular = "";
        alu_fecha_nacimiento = new Date();
        alu_unidad = "042006";
        alu_forma_pago = "";
        alu_nombre_familiar = "";
        alu_parentesco = "040001";
        b_sexo = "";
        b_distrito = "";
        b_unidad = "";
        b_forma_pago = "";
        b_procedencia = "";
        b_parentesco = "";
        //System.out.println( "[limpiar] >> EXIT" );
    }

    public void guardarDatos( ActionEvent event ) throws Exception {
        //System.out.println( "[guardarDatos] << ENTER" );
        this.setOncomplete( "" );
        HSAlumnoCLDAO aluClDAO = CommonDAO.getClAlumnoDAO();
        HSAlumnoCLDAO dao = CommonDAO.getClAlumnoDAO();
        if ( this.getAlu_codigo() == null || this.getAlu_codigo().isEmpty() ) {
            this.setAlu_codigo( dao.maximoCodigo() );
        }
        if ( !this.getAlu_appaterno().trim().isEmpty()
                && !this.getAlu_apmaterno().trim().isEmpty()
                && !this.getAlu_nombres().trim().isEmpty() ) {
            if ( !this.getAlu_telefono().trim().isEmpty() || !this.getAlu_celular().trim().isEmpty() ) {
                if ( isEmail( this.getAlu_mail().trim() ) ) {
                    if ( !this.getAlu_distrito().equals( "000000" ) ) {

                        ClAlumno aluCl = new ClAlumno();

                        aluCl.setAluActivo( "1" );
                        aluCl.setAluApmaterno( this.getAlu_apmaterno().trim().toUpperCase() );
                        aluCl.setAluAppaterno( this.getAlu_appaterno().trim().toUpperCase() );
                        aluCl.setAluCelular( this.getAlu_celular() );
                        aluCl.setAluCodigo( this.getAlu_codigo() );
                        aluCl.setAluCp( this.getAlu_cp() );
                        aluCl.setAluDireccion( this.getAlu_direccion().trim().toUpperCase() );
                        aluCl.setAluDistrito( this.getAlu_distrito() );
                        aluCl.setAluDni( this.getAlu_dni() );
                        aluCl.setAluDuplicado( this.getAlu_duplicado() );
                        aluCl.setAluFechaNacimiento( this.getAlu_fecha_nacimiento() );
                        aluCl.setAluFormaPago( this.getAlu_forma_pago() );
                        aluCl.setAluFoto( this.getAlu_foto() );
                        aluCl.setAluMail( this.getAlu_mail().trim().toUpperCase() );
                        aluCl.setAluNombreFamiliar( this.getAlu_nombre_familiar().trim().toUpperCase() );
                        aluCl.setAluNombres( this.getAlu_nombres().trim().toUpperCase() );
                        aluCl.setAluParentesco( this.getAlu_parentesco() );
                        aluCl.setAluPassword( this.getAlu_password() );
                        aluCl.setAluProcedencia( this.getAlu_procedencia() );
                        aluCl.setAluSexo( this.getAlu_sexo() );
                        aluCl.setAluTelefono( this.getAlu_telefono() );
                        aluCl.setAluTemporal( this.getAlu_temporal() );
                        aluCl.setAluTipo( this.getAlu_tipo() );
                        aluCl.setDetAluTipoNuevo( "085001".equals( this.getAlu_tipo() ) ? this.getDetAluExterno() : this.getAluInterUnidad() );
                        aluCl.setDetAluUnidNuevo( this.getDetInterUnidad() );
                        aluCl.setAluUnidad( this.getAlu_unidad() );
                        aluCl.setAluId( this.getAlu_id() );
                        if ( this.getAlu_id() == 0 ) {
                            try {
                                aluCl.setAluPassword( this.getAlu_codigo() );
                                aluClDAO.insertarAlumnocl( aluCl );
                                this.setOncomplete( "javascript:alert('Se creo una Alumno correctamente.');Richfaces.hideModalPanel('mpUpdate')" );
                            } catch ( Exception e ) {
                                this.setOncomplete( "javascript:alert(' No se pudo Agregar.')" );
                            }
                        } else {
                            try {
                                aluClDAO.modificarAlumnocl( aluCl );
                                this.setOncomplete( "javascript:alert('Se Modifica Alumno correctamente.');Richfaces.hideModalPanel('mpUpdate')" );
                            } catch ( Exception e ) {
                                this.setOncomplete( "javascript:alert(' No se pudo Modificar.')" );
                            }
                        }
                        limpiar();
                    } else {
                        this.setOncomplete( "javascript:alert('Debe ingresar el distrito.')" );
                    }
                } else {
                    this.setOncomplete( "javascript:alert('Debe ingresar el email correctamente.')" );
                }
            } else {
                this.setOncomplete( "javascript:alert('Debe ingresar minimo un número telefonico.')" );
            }
        } else {
            this.setOncomplete( "javascript:alert('Debe ingresar los campos obligatorios.')" );
        }
        //System.out.println( "[guardarDatos] >> EXIT" );
    }

    public void editarAlumno( ActionEvent event ) throws Exception {
       // System.out.println( "[editarAlumno] << ENTER" );
        limpiar();
        this.setFind_image( 2 );
        int v_alu_id = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "p_alu_id" )).getValue().toString() );
//        System.out.println("entro al editarAlumno el alu_id -> " + v_alu_id);
        HSAlumnoCLDAO aluClDAO = CommonDAO.getClAlumnoDAO();
//        List alumno = dao.buscarAlumnoPorAluId(v_alu_id);
//        ClAlumno alu = (ClAlumno) alumno.get(0);
        ClAlumno aluCl = aluClDAO.buscarAlumnoPorAluId( v_alu_id );
        this.setAlu_activo( aluCl.getAluActivo() );
        this.setAlu_apmaterno( aluCl.getAluApmaterno() );
        this.setAlu_appaterno( aluCl.getAluAppaterno() );
        this.setAlu_celular( aluCl.getAluCelular() );
        if ( aluCl.getAluCodigo() == null || aluCl.getAluCodigo().trim().length() == 0 ) {
            this.setAlu_codigo( aluClDAO.maximoCodigo() );
        } else {
            this.setAlu_codigo( aluCl.getAluCodigo() );
        }
        //this.setAlu_cp(alu.getAluCp());
        this.setAlu_direccion( aluCl.getAluDireccion() );
        this.setAlu_dni( aluCl.getAluDni() );
        this.setAlu_duplicado( aluCl.getAluDuplicado() );
        this.setAlu_fecha_nacimiento( aluCl.getAluFechaNacimiento() );
        this.setAlu_forma_pago( aluCl.getAluFormaPago() );
        this.setAlu_foto( aluCl.getAluFoto() );
        this.setAlu_id( aluCl.getAluId() );
        this.setAlu_mail( aluCl.getAluMail() );
        this.setAlu_nombre_familiar( aluCl.getAluNombreFamiliar() );
        this.setAlu_nombres( aluCl.getAluNombres() );
        this.setAlu_parentesco( aluCl.getAluParentesco() );
        this.setAlu_password( aluCl.getAluPassword() );
        this.setAlu_procedencia( aluCl.getAluProcedencia() );
        this.setAlu_sexo( aluCl.getAluSexo() );
        this.setAlu_telefono( aluCl.getAluTelefono() );
        this.setAlu_temporal( aluCl.getAluTemporal() );
        this.setAlu_tipo( aluCl.getAluTipo() );
        this.setAlu_unidad( aluCl.getAluUnidad() );
        try {
            this.setAlu_distrito( aluCl.getAluDistrito() );
            this.setDepartamento( (aluCl.getAluDistrito().substring( 0, 2 ) + "0000") );
            this.setProvincia( aluCl.getAluDistrito().substring( 0, 4 ) );
        } catch ( Exception e ) {
            this.setAlu_distrito( "000000" );
            this.setDepartamento( "00" );
            this.setProvincia( "0000" );
        }

        this.setB_paterno( b_paterno );
        this.setB_materno( b_materno );
        this.setB_nombre( b_nombre );
        this.setB_codigo( b_codigo );
        //System.out.println( "[editarAlumno] >> EXIT" );
    }

    public void eliminarAlumno( ActionEvent event ) throws Exception {
       // System.out.println( "[eliminarAlumno] << ENTER" );
        String v_alu_id = ((UIParameter) event.getComponent().findComponent( "p_alu_id" )).getValue().toString();
        HSAlumnoCLDAO dao = CommonDAO.getClAlumnoDAO();
        dao.eliminarAlumnocl( v_alu_id );
        //System.out.println( "[eliminarAlumno] >> EXIT" );
    }

    public void nuevoUniv() {
        //System.out.println( "[nuevoUniv] << ENTER" );
        this.setUniv_codigo( "" );
        this.setFind_image( 1 );
        this.setUniv_alumno( new AcAlumno() );
        //System.out.println( "[nuevoUniv] >> EXIT" );
    }

    public void buscarUniversidad() {
        //System.out.println( "[buscarUniversidad] << ENTER" );
        AcAlumno alu;
        HSAlumnoDAO aluDAO;
        try {
            this.setOncomplete( "" );
            aluDAO = CommonDAO.getAcAlumnoDAO();
            alu = aluDAO.buscarAlumnoCodigo( this.getUniv_codigo() );
            if ( alu == null ) {
                this.setOncomplete( "showErrorDiv();hideOkDiv();" );
            } else {
                this.setUniv_alumno( alu );
                this.setOncomplete( "showOkDiv();hideErrorDiv();" );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        //System.out.println( "[buscarUniversidad] >> EXIT" );
    }

    public long getTimeStamp() {
        //System.out.println( "[getTimeStamp] << ENTER" );
        //System.out.println( "[getTimeStamp] >> EXIT" );
        return System.currentTimeMillis();
    }

    public void imagen( OutputStream out, Object data ) throws Exception {
        //System.out.println( "[imagen] << ENTER" );
        AcAlumno acAlu;
        Blob imagen;
        BufferedImage bufferedImage;
        ClAlumno clAlu;
        HSAlumnoDAO aluDAO;
        HSAlumnoCLDAO daoCl;
        if ( data != null ) {
            try {
                imagen = null;

                switch ( this.getFind_image() ) {
                    case 1:
                        aluDAO = CommonDAO.getAcAlumnoDAO();
                        acAlu = aluDAO.buscarAlumnoCodigo( String.valueOf( data ) );
                        if ( acAlu != null ) {
                            imagen = acAlu.getAluFoto();
                        }
                        break;
                    case 2:
                        daoCl = CommonDAO.getClAlumnoDAO();
                        clAlu = daoCl.buscarAlumnoPorAluId( new Integer( String.valueOf( data ) ) );
                        if ( clAlu != null ) {
                            imagen = clAlu.getAluFoto();
                        }
                        break;
                }

                if ( imagen == null ) {
                    bufferedImage = ImageIO.read( FacesContext.getCurrentInstance().
                            getExternalContext().getResourceAsStream( "/Imagenes/actions/user-info.png" ) );
                } else {
                    bufferedImage = ImageIO.read( imagen.getBinaryStream() );
                }
                ImageIO.write( bufferedImage, "png", out );
            } catch ( Exception e ) {
//                System.err.println("Error al pintar la imagen " + e.getMessage());
                bufferedImage = ImageIO.read( FacesContext.getCurrentInstance().
                        getExternalContext().getResourceAsStream( "/Imagenes/actions/user-info.png" ) );
                ImageIO.write( bufferedImage, "png", out );
            }
        }
        //System.out.println( "[imagen] >> EXIT" );
    }

    public void salvarAlumnoUniversidad() {
        //System.out.println( "[salvarAlumnoUniversidad] << ENTER" );
        ClAlumno alu;
        HSAlumnoCLDAO aluClDAO;
        this.setOncomplete( "" );
        try {
            aluClDAO = CommonDAO.getClAlumnoDAO();
            if ( !aluClDAO.existeAlumnoUnivRegistrado( this.getUniv_alumno().getId() ) ) {
                alu = new ClAlumno();

                alu.setAluCodigo( this.getUniv_alumno().getAluCodigo() );
                alu.setAluCp( this.getUniv_alumno().getId() );
                alu.setAluTemporal( this.getUniv_alumno().getId().toString() );
                alu.setAluAppaterno( this.getUniv_alumno().getAluAppaterno() );
                alu.setAluApmaterno( this.getUniv_alumno().getAluApmaterno() );
                alu.setAluNombres( this.getUniv_alumno().getAluNombres() );
                alu.setAluActivo( "1" );

                alu.setAluDni( this.getUniv_alumno().getAluDni() );
                alu.setAluProcedencia( this.getUniv_alumno().getAluProcedencia() );
                alu.setAluDireccion( "" );
                alu.setAluDistrito( "" );

                alu.setAluDuplicado( "00" );
                alu.setAluFechaNacimiento( new Date() );
                alu.setAluFormaPago( "041001" );
                alu.setAluFoto( this.getUniv_alumno().getAluFoto() );
                alu.setAluMail( this.getUniv_alumno().getAluMail() );
                alu.setAluNombreFamiliar( this.getUniv_alumno().getAluFamiliar() );

                alu.setAluParentesco( "040001" );
                alu.setAluPassword( this.getUniv_alumno().getAluCodigo() );
                alu.setAluSexo( "" );
                alu.setAluTelefono( "" );
                alu.setAluCelular( "" );

                alu.setAluTipo( "057002" );
                alu.setAluUnidad( "042002" );

                aluClDAO.insertarAlumnocl( alu );
                this.setOncomplete( "javascript:alert('Se guardo correctamente la informacion');"
                        + "Richfaces.hideModalPanel('mpUniv');" );
            } else {
                this.setOncomplete( "javascript:alert('Ya esta registrado el alumno');" );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        //System.out.println( "[salvarAlumnoUniversidad] >> EXIT" );
    }

    public List<AcAlumno> getListaEnlace() {
        //System.out.println( "[getListaEnlace] << ENTER" );
        if ( listaEnlace == null ) {
            listaEnlace = new ArrayList<AcAlumno>();
        }
       // System.out.println( "[getListaEnlace] >> EXIT" );
        return listaEnlace;
    }

    public void setListaEnlace( List<AcAlumno> listaEnlace ) {
        //System.out.println( "[setListaEnlace] << ENTER" );
        this.listaEnlace = listaEnlace;
        //System.out.println( "[setListaEnlace] >> EXIT" );
    }

    public ClAlumno getEnl_alumno() {
        //System.out.println( "[getEnl_alumno] << ENTER" );
        //System.out.println( "[getEnl_alumno] >> EXIT" );
        return enl_alumno;
    }

    public void setEnl_alumno( ClAlumno enl_alumno ) {
        //System.out.println( "[setEnl_alumno] << ENTER" );
        this.enl_alumno = enl_alumno;
        //System.out.println( "[setEnl_alumno] >> EXIT" );
    }

    public void enlaceUniversidad( ActionEvent event ) {
        //System.out.println( "[enlaceUniversidad] << ENTER" );
        int iAluIdParam;
        String sAluPaternoParam;
        String sAluMaternoParam;
        String sAluNombresParam;
        HSAlumnoDAO aluDAO;
        HSAlumnoCLDAO aluClDAO;
        List<AcAlumno> lstAlumnos;
        List<AcAlumno> lstAlumnosTmp;

        iAluIdParam = Integer.parseInt( ((UIParameter) event.getComponent().findComponent( "p_alu_id" )).getValue().toString() );
        sAluPaternoParam = ((UIParameter) event.getComponent().findComponent( "p_alu_paterno" )).getValue().toString();
        sAluMaternoParam = ((UIParameter) event.getComponent().findComponent( "p_alu_materno" )).getValue().toString();
        sAluNombresParam = ((UIParameter) event.getComponent().findComponent( "p_alu_nombres" )).getValue().toString();

        aluDAO = CommonDAO.getAcAlumnoDAO();
        lstAlumnos = aluDAO.listaCoincidencias( "", sAluPaternoParam, sAluMaternoParam, sAluNombresParam );

        lstAlumnosTmp = new ArrayList<AcAlumno>();
        for ( AcAlumno alu : lstAlumnos ) {
            lstAlumnosTmp.add( alu );
        }

        aluClDAO = CommonDAO.getClAlumnoDAO();
        this.setEnl_alumno( aluClDAO.buscarAlumnoPorAluId( iAluIdParam ) );
        this.setFind_image( 1 );
        this.setListaEnlace( new ArrayList<AcAlumno>( lstAlumnosTmp ) );
        //System.out.println( "[enlaceUniversidad] >> EXIT" );
    }

    public void relacionarUniv( ActionEvent event ) {
        //System.out.println( "[relacionarUniv] << ENTER" );
        try {
            int enl_alu_id = Integer.parseInt( (((UIParameter) event.getComponent().findComponent( "enl_alu_id" )).getValue().toString()) );
            HSAlumnoDAO daoAc = CommonDAO.getAcAlumnoDAO();
            AcAlumno acAlumno = daoAc.seleccionarAlumno( enl_alu_id );

            HSAlumnoCLDAO daoCl = CommonDAO.getClAlumnoDAO();
            ClAlumno alu = new ClAlumno();
            alu.setAluId( this.getEnl_alumno().getAluId() );
            if ( this.getEnl_alumno().getAluCodigo() == null
                    || this.getEnl_alumno().getAluCodigo().trim().length() == 0 ) {
                alu.setAluCodigo( acAlumno.getAluCodigo() );
            } else {
                alu.setAluCodigo( this.getEnl_alumno().getAluCodigo() );
            }
            alu.setAluAppaterno( this.getEnl_alumno().getAluAppaterno() );
            alu.setAluApmaterno( this.getEnl_alumno().getAluApmaterno() );

            if ( numeroCaracteres( this.getEnl_alumno().getAluNombres() ) < numeroCaracteres( acAlumno.getAluNombres() ) ) {
                alu.setAluNombres( acAlumno.getAluNombres() );
            } else {
                alu.setAluNombres( this.getEnl_alumno().getAluNombres() );
            }

            if ( numeroCaracteres( this.getEnl_alumno().getAluDni() ) < numeroCaracteres( acAlumno.getAluDni() ) ) {
                alu.setAluDni( acAlumno.getAluDni() );
            } else {
                alu.setAluDni( this.getEnl_alumno().getAluDni() );
            }

            if ( numeroCaracteres( this.getEnl_alumno().getAluMail() ) < numeroCaracteres( acAlumno.getAluMail() ) ) {
                alu.setAluMail( acAlumno.getAluMail() );
            } else {
                alu.setAluMail( this.getEnl_alumno().getAluMail() );
            }
            alu.setAluTipo( this.getEnl_alumno().getAluTipo() );

            alu.setAluActivo( "1" );
            alu.setAluFoto( acAlumno.getAluFoto() );
            alu.setAluDuplicado( this.getEnl_alumno().getAluDuplicado() );

            if ( numeroCaracteres( this.getEnl_alumno().getAluProcedencia() ) < numeroCaracteres( acAlumno.getAluProcedencia() ) ) {
                alu.setAluProcedencia( acAlumno.getAluProcedencia() );
            } else {
                alu.setAluProcedencia( this.getEnl_alumno().getAluProcedencia() );
            }
            alu.setAluPassword( this.getEnl_alumno().getAluPassword() );

            alu.setAluCp( acAlumno.getId() );
            alu.setAluTemporal( acAlumno.getId().toString() );
            alu.setAluSexo( this.getEnl_alumno().getAluSexo() );

            alu.setAluDistrito( this.getEnl_alumno().getAluDistrito() );
            alu.setAluDireccion( this.getEnl_alumno().getAluDireccion() );
            alu.setAluTelefono( this.getEnl_alumno().getAluTelefono() );
            alu.setAluCelular( this.getEnl_alumno().getAluCelular() );

            alu.setAluFechaNacimiento( this.getEnl_alumno().getAluFechaNacimiento() );
            alu.setAluUnidad( this.getEnl_alumno().getAluUnidad() );
            alu.setAluFormaPago( this.getEnl_alumno().getAluFormaPago() );
            alu.setAluNombreFamiliar( this.getEnl_alumno().getAluNombreFamiliar() );
            alu.setAluParentesco( this.getEnl_alumno().getAluParentesco() );

            daoCl.modificarAlumnocl( alu );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        //System.out.println( "[relacionarUniv] >> EXIT" );
    }

    private int numeroCaracteres( String cadena ) {
        //System.out.println( "[numeroCaracteres] << ENTER" );
        if ( cadena == null ) {
            //System.out.println( "[numeroCaracteres] >> (cadena==null)EXIT" );
            return 0;
        } else {
            //System.out.println( "[numeroCaracteres] >> (cadena!=null)EXIT" );
            return cadena.trim().length();
        }
    }

    public boolean isEmail( String correo ) {
        //System.out.println( "[isEmail] << ENTER" );
        if ( correo.isEmpty() ) {
            //System.out.println( "[isEmail] >> (correo.isEmpty = true)EXIT" );
            return true;
        } else {
            Pattern pat;
            Matcher mat;
            //pat = Pattern.compile( "^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z]{2,9}.)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,9})$" );
              pat = Pattern.compile( "^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z]{0,9}.)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,9})$" );
            mat = pat.matcher( correo );
            if ( mat.find() ) {
                //System.out.println( "[isEmail] >> (mat.find = true)EXIT" );
                //System.out.println( "[" + mat.group() + "]" );
                return true;
            } else {
                //System.out.println( "[isEmail] >> (mat.find = false)EXIT" );
                return false;
            }
        }

    }
}
