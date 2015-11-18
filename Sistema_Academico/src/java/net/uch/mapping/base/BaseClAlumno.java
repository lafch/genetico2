package net.uch.mapping.base;

import java.sql.Blob;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AbstractClAlumno generated by MyEclipse Persistence Tools
 */
public abstract class BaseClAlumno implements java.io.Serializable {

    // Fields
    private Integer aluId;
    private String aluCodigo;
    private String aluAppaterno;
    private String aluApmaterno;
    private String aluNombres;
    private String aluDni;
    private String aluMail;
    private String aluTipo;
    private Blob aluFoto;
    private String aluActivo;
    private String aluDuplicado;
    private String aluProcedencia;
    private String aluPassword;
    private Integer aluCp;
    private String aluTemporal;
    private String aluSexo;
    private String aluDistrito;
    private String aluDireccion;
    private String aluTelefono;
    private String aluCelular;
    private Date aluFechaNacimiento;
    private String aluUnidad;
    private String aluFormaPago;
    private String aluNombreFamiliar;
    private String aluParentesco;
    private Integer aluIdBk;
    private Integer aluIdCopia;
    private Set clMatriculas = new HashSet( 0 );
    private Set clAlumnoTarifas = new HashSet( 0 );
    private Set clNotas = new HashSet( 0 );
    
    //MODIFICACIONES PARA INFORMES - FORMULARIO DE ALUMNO
    private String detAluTipoNuevo;
    private String detAluUnidNuevo;

    public String getDetAluTipoNuevo() {
        return detAluTipoNuevo;
    }

    public void setDetAluTipoNuevo( String detAluTipoNuevo ) {
        this.detAluTipoNuevo = detAluTipoNuevo;
    }

    public String getDetAluUnidNuevo() {
        return detAluUnidNuevo;
    }

    public void setDetAluUnidNuevo( String detAluUnidNuevo ) {
        this.detAluUnidNuevo = detAluUnidNuevo;
    }
    // Constructors
    /**
     * default constructor
     */
    public BaseClAlumno() {
    }

    /**
     * minimal constructor
     */
    public BaseClAlumno( Integer aluId ) {
        this.aluId = aluId;
    }

    /**
     * full constructor
     */
    public BaseClAlumno( Integer aluId, String aluCodigo,
            String aluAppaterno, String aluApmaterno, String aluNombres,
            String aluDni, String aluMail, String aluTipo, Blob aluFoto,
            String aluActivo, String aluDuplicado, String aluProcedencia,
            String aluPassword, Integer aluCp, String aluTemporal,
            String aluSexo, String aluDistrito, String aluDireccion,
            String aluTelefono, String aluCelular, Date aluFechaNacimiento,
            String aluUnidad, String aluFormaPago, String aluNombreFamiliar,
            String aluParentesco, Integer aluIdBk, Integer aluIdCopia,
            Set clMatriculas, Set clAlumnoTarifas,
            Set clNotas ) {
        this.aluId = aluId;
        this.aluCodigo = aluCodigo;
        this.aluAppaterno = aluAppaterno;
        this.aluApmaterno = aluApmaterno;
        this.aluNombres = aluNombres;
        this.aluDni = aluDni;
        this.aluMail = aluMail;
        this.aluTipo = aluTipo;
        this.aluFoto = aluFoto;
        this.aluActivo = aluActivo;
        this.aluDuplicado = aluDuplicado;
        this.aluProcedencia = aluProcedencia;
        this.aluPassword = aluPassword;
        this.aluCp = aluCp;
        this.aluTemporal = aluTemporal;
        this.aluSexo = aluSexo;
        this.aluDistrito = aluDistrito;
        this.aluDireccion = aluDireccion;
        this.aluTelefono = aluTelefono;
        this.aluCelular = aluCelular;
        this.aluFechaNacimiento = aluFechaNacimiento;
        this.aluUnidad = aluUnidad;
        this.aluFormaPago = aluFormaPago;
        this.aluNombreFamiliar = aluNombreFamiliar;
        this.aluParentesco = aluParentesco;
        this.aluIdBk = aluIdBk;
        this.aluIdCopia = aluIdCopia;
        this.clMatriculas = clMatriculas;
        this.clAlumnoTarifas = clAlumnoTarifas;
        this.clNotas = clNotas;
    }

    // Property accessors
    public Integer getAluId() {
        return this.aluId;
    }

    public void setAluId( Integer aluId ) {
        this.aluId = aluId;
    }

    public String getAluCodigo() {
        return this.aluCodigo;
    }

    public void setAluCodigo( String aluCodigo ) {
        this.aluCodigo = aluCodigo;
    }

    public String getAluAppaterno() {
        return this.aluAppaterno;
    }

    public void setAluAppaterno( String aluAppaterno ) {
        this.aluAppaterno = aluAppaterno;
    }

    public String getAluApmaterno() {
        return this.aluApmaterno;
    }

    public void setAluApmaterno( String aluApmaterno ) {
        this.aluApmaterno = aluApmaterno;
    }

    public String getAluNombres() {
        return this.aluNombres;
    }

    public void setAluNombres( String aluNombres ) {
        this.aluNombres = aluNombres;
    }

    public String getAluDni() {
        return this.aluDni;
    }

    public void setAluDni( String aluDni ) {
        this.aluDni = aluDni;
    }

    public String getAluMail() {
        return this.aluMail;
    }

    public void setAluMail( String aluMail ) {
        this.aluMail = aluMail;
    }

    public String getAluTipo() {
        return this.aluTipo;
    }

    public void setAluTipo( String aluTipo ) {
        this.aluTipo = aluTipo;
    }

    public Blob getAluFoto() {
        return this.aluFoto;
    }

    public void setAluFoto( Blob aluFoto ) {
        this.aluFoto = aluFoto;
    }

    public String getAluActivo() {
        return this.aluActivo;
    }

    public void setAluActivo( String aluActivo ) {
        this.aluActivo = aluActivo;
    }

    public String getAluDuplicado() {
        return this.aluDuplicado;
    }

    public void setAluDuplicado( String aluDuplicado ) {
        this.aluDuplicado = aluDuplicado;
    }

    public String getAluProcedencia() {
        return this.aluProcedencia;
    }

    public void setAluProcedencia( String aluProcedencia ) {
        this.aluProcedencia = aluProcedencia;
    }

    public String getAluPassword() {
        return this.aluPassword;
    }

    public void setAluPassword( String aluPassword ) {
        this.aluPassword = aluPassword;
    }

    public Integer getAluCp() {
        return this.aluCp;
    }

    public void setAluCp( Integer aluCp ) {
        this.aluCp = aluCp;
    }

    public String getAluTemporal() {
        return this.aluTemporal;
    }

    public void setAluTemporal( String aluTemporal ) {
        this.aluTemporal = aluTemporal;
    }

    public String getAluSexo() {
        return this.aluSexo;
    }

    public void setAluSexo( String aluSexo ) {
        this.aluSexo = aluSexo;
    }

    public String getAluDistrito() {
        return this.aluDistrito;
    }

    public void setAluDistrito( String aluDistrito ) {
        this.aluDistrito = aluDistrito;
    }

    public String getAluDireccion() {
        return this.aluDireccion;
    }

    public void setAluDireccion( String aluDireccion ) {
        this.aluDireccion = aluDireccion;
    }

    public String getAluTelefono() {
        return this.aluTelefono;
    }

    public void setAluTelefono( String aluTelefono ) {
        this.aluTelefono = aluTelefono;
    }

    public String getAluCelular() {
        return this.aluCelular;
    }

    public void setAluCelular( String aluCelular ) {
        this.aluCelular = aluCelular;
    }

    public Date getAluFechaNacimiento() {
        return this.aluFechaNacimiento;
    }

    public void setAluFechaNacimiento( Date aluFechaNacimiento ) {
        this.aluFechaNacimiento = aluFechaNacimiento;
    }

    public String getAluUnidad() {
        return this.aluUnidad;
    }

    public void setAluUnidad( String aluUnidad ) {
        this.aluUnidad = aluUnidad;
    }

    public String getAluFormaPago() {
        return this.aluFormaPago;
    }

    public void setAluFormaPago( String aluFormaPago ) {
        this.aluFormaPago = aluFormaPago;
    }

    public String getAluNombreFamiliar() {
        return this.aluNombreFamiliar;
    }

    public void setAluNombreFamiliar( String aluNombreFamiliar ) {
        this.aluNombreFamiliar = aluNombreFamiliar;
    }

    public String getAluParentesco() {
        return this.aluParentesco;
    }

    public void setAluParentesco( String aluParentesco ) {
        this.aluParentesco = aluParentesco;
    }

    public Integer getAluIdBk() {
        return this.aluIdBk;
    }

    public void setAluIdBk( Integer aluIdBk ) {
        this.aluIdBk = aluIdBk;
    }

    public Integer getAluIdCopia() {
        return this.aluIdCopia;
    }

    public void setAluIdCopia( Integer aluIdCopia ) {
        this.aluIdCopia = aluIdCopia;
    }

    public Set getClMatriculas() {
        return this.clMatriculas;
    }

    public void setClMatriculas( Set clMatriculas ) {
        this.clMatriculas = clMatriculas;
    }

    public Set getClAlumnoTarifas() {
        return this.clAlumnoTarifas;
    }

    public void setClAlumnoTarifas( Set clAlumnoTarifas ) {
        this.clAlumnoTarifas = clAlumnoTarifas;
    }

    public Set getClNotas() {
        return this.clNotas;
    }

    public void setClNotas( Set clNotas ) {
        this.clNotas = clNotas;
    }
}