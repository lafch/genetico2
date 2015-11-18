package net.uch.mapping.base;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import net.uch.mapping.AcApoderadoAlumno;

/**
 * This is an object that contains data related to the ac_alumno table. Do not
 * modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class table="ac_alumno"
 */
public abstract class BaseAcAlumno implements Serializable {

    public static String REF = "AcAlumno";
    public static String PROP_ALU_DUPLICADO = "AluDuplicado";
    public static String PROP_ALU_FOTO = "AluFoto";
    public static String PROP_ALU_DNI = "AluDni";
    public static String PROP_ALU_NOMBRES = "AluNombres";
    public static String PROP_ALU_APMATERNO = "AluApmaterno";
    public static String PROP_ALU_TIPO = "AluTipo";
    public static String PROP_ESP = "Esp";
    public static String PROP_PLAN_ID_INGRESO = "PlanIdIngreso";
    public static String PROP_ALU_ACTIVO = "AluActivo";
    public static String PROP_SEM_ID = "SemId";
    public static String PROP_ALU_CODIGO = "AluCodigo";
    public static String PROP_ALU_PASSWORD = "AluPassword";
    public static String PROP_ALU_MODALIDAD = "AluModalidad";
    public static String PROP_ESP_ID_INGRESO = "EspIdIngreso";
    public static String PROP_ALU_MAIL = "AluMail";
    public static String PROP_ALU_ESTADO = "AluEstado";
    public static String PROP_ID = "Id";
    public static String PROP_ALU_APPATERNO = "AluAppaterno";
    public static String PROP_PLAN_ID_ACTUAL = "PlanIdActual";
    public static String PROP_ALU_PROCEDENCIA = "AluProcedencia";
    public static String PROP_ALU_FAMILIAR = "aluFamiliar";
    public static String PROP_ALU_TIPO_FAMILIAR = "aluTipoFamiliar";
    public static String PROP_FORMA_PAGO = "aluFormaPago";
    public static String PROP_UNIDAD = "aluUnidad";
    public static String PROP_MONTO_PAGO = "aluMontoPago";

    public BaseAcAlumno() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseAcAlumno( java.lang.Integer id ) {
        this.setId( id );
        initialize();
    }

    /**
     * Constructor for required fields
     */
    // constructors
    public BaseAcAlumno(
            java.lang.Integer id,
            net.uch.mapping.AcEspecialidad esp ) {

        this.setId( id );
        this.setEsp( esp );
        initialize();
    }

    protected void initialize() {
    }
    private int hashCode = Integer.MIN_VALUE;
    // primary key
    private java.lang.Integer id;
    // fields
    private java.lang.String aluCodigo;
    private java.lang.String aluPassword;
    private java.lang.String aluAppaterno;
    private java.lang.String aluApmaterno;
    private java.lang.String aluNombres;
    private java.lang.String aluDni;
    private java.lang.String aluMail;
    private java.lang.String aluTipo;
    private java.lang.String aluModalidad;
    private java.lang.String aluEstado;
    private java.sql.Blob aluFoto;
    private java.lang.Integer espIdIngreso;
    private java.lang.Integer semId;
    private java.lang.String aluActivo;
    private java.lang.Integer planIdIngreso;
    private java.lang.Integer planIdActual;
    private java.lang.String aluDuplicado;
    private java.lang.String aluProcedencia;
    private java.lang.String aluFamiliar;
    private java.lang.String aluTipoFamiliar;
    private java.lang.String aluFormaPago;
    private java.lang.String aluUnidad;
    private float aluMontoPago;
    // many to one
    private net.uch.mapping.AcEspecialidad esp;
    // collections
    private java.util.Set<net.uch.mapping.AcNota> acNotas;
    private java.util.Set<net.uch.mapping.AdAlumnoTarifa> adAlumnoTarifas;
    private java.util.Set<net.uch.mapping.AcAlumnoDocumento> acAlumnoDocumentos;
    private java.util.Set<net.uch.mapping.AcAsistencia> acAsistencias;
    private java.util.Set<net.uch.mapping.AcMatricula> acMatriculas;
    private java.util.Set<net.uch.mapping.AdSaldo> adSaldos;
    private java.util.Set<net.uch.mapping.AcAmpliaciones> acAmpliaciones;
    private java.util.Set<net.uch.mapping.AcApoderadoAlumno> acApoderadoAlumno;
    //adicionales al margen de mapeo
    private String v_nota;
    private List<String> v_lstNotas;
    private List<Integer> v_lstSisEvaPersIds;
    private String v_nombreCompleto;
    private int v_nro_alu;

    public String getV_nota() {
        return v_nota;
    }

    public void setV_nota( String v_nota ) {
        this.v_nota = v_nota;
    }

    public List<String> getV_lstNotas() {
        return v_lstNotas;
    }

    public void setV_lstNotas( List<String> v_lstNotas ) {
        this.v_lstNotas = v_lstNotas;
    }

    public List<Integer> getV_lstSisEvaPersIds() {
        return v_lstSisEvaPersIds;
    }

    public void setV_lstSisEvaPersIds( List<Integer> v_lstSisEvaPersIds ) {
        this.v_lstSisEvaPersIds = v_lstSisEvaPersIds;
    }
//    <<<<<<< .mine
//=======
//	// fields
//	private java.lang.String aluCodigo;
//    private java.lang.String aluPassword;
//    private java.lang.String aluAppaterno;
//    private java.lang.String aluApmaterno;
//    private java.lang.String aluNombres;
//    private java.lang.String aluDni;
//    private java.lang.String aluMail;
//    private java.lang.String aluTipo;
//    private java.lang.String aluModalidad;
//    private java.lang.String aluEstado;
//    private java.sql.Blob aluFoto;
//    private java.lang.Integer espIdIngreso;
//    private java.lang.Integer semId;
//    private java.lang.String aluActivo;
//    private java.lang.Integer planIdIngreso;
//    private java.lang.Integer planIdActual;
//    private java.lang.String aluDuplicado;
//    private java.lang.String aluProcedencia;
//    private java.lang.String aluFamiliar;
//    private java.lang.String aluTipoFamiliar;
//    private java.lang.String aluFormaPago;
//    private java.lang.String aluUnidad;
//    private float aluMontoPago;
//    // many to one
//    private net.uch.mapping.AcEspecialidad esp;
//    // collections
//    private java.util.Set<net.uch.mapping.AcNota> acNotas;
//    private java.util.Set<net.uch.mapping.AdAlumnoTarifa> adAlumnoTarifas;
//    private java.util.Set<net.uch.mapping.AcAlumnoDocumento> acAlumnoDocumentos;
//    private java.util.Set<net.uch.mapping.AcAsistencia> acAsistencias;
//    private java.util.Set<net.uch.mapping.AcMatricula> acMatriculas;
//    private java.util.Set<net.uch.mapping.AdSaldo> adSaldos;
//    private java.util.Set<net.uch.mapping.AcAmpliaciones> acAmpliaciones;
//    private java.util.Set<net.uch.mapping.AcApoderadoAlumno> acApoderadoAlumno;
//    >>>>>>> .r298

    public Set<AcApoderadoAlumno> getAcApoderadoAlumno() {
        return acApoderadoAlumno;
    }

    public void setAcApoderadoAlumno( Set<AcApoderadoAlumno> acApoderadoAlumno ) {
        this.acApoderadoAlumno = acApoderadoAlumno;
    }

    public String getV_nombreCompleto() {
        return v_nombreCompleto;
    }

    public void setV_nombreCompleto( String completo ) {
        v_nombreCompleto = completo;
    }

    public int getV_nro_alu() {
        return v_nro_alu;
    }

    public void setV_nro_alu( int v_nro_alu ) {
        this.v_nro_alu = v_nro_alu;
    }

    /**
     * Return the unique identifier of this class
     *
     * @hibernate.id generator-class="native" column="alu_id"
     */
    public java.lang.Integer getId() {
        return id;
    }

    /**
     * Set the unique identifier of this class
     *
     * @param id the new ID
     */
    public void setId( java.lang.Integer id ) {
        this.id = id;
        this.hashCode = Integer.MIN_VALUE;
    }

    /**
     * Return the value associated with the column: alu_codigo
     */
    public java.lang.String getAluCodigo() {
        return aluCodigo;
    }

    /**
     * Set the value related to the column: alu_codigo
     *
     * @param aluCodigo the alu_codigo value
     */
    public void setAluCodigo( java.lang.String aluCodigo ) {
        this.aluCodigo = aluCodigo;
    }

    /**
     * Return the value associated with the column: alu_appaterno
     */
    public java.lang.String getAluAppaterno() {
        return aluAppaterno;
    }

    /**
     * Set the value related to the column: alu_appaterno
     *
     * @param aluAppaterno the alu_appaterno value
     */
    public void setAluAppaterno( java.lang.String aluAppaterno ) {
        this.aluAppaterno = aluAppaterno;
    }

    /**
     * Return the value associated with the column: alu_apmaterno
     */
    public java.lang.String getAluApmaterno() {
        return aluApmaterno;
    }

    public java.lang.String getAluPassword() {
        return aluPassword;
    }

    public void setAluApmaterno( java.lang.String aluApmaterno ) {
        this.aluApmaterno = aluApmaterno;
    }

    public void setAluPassword( java.lang.String aluPassword ) {
        this.aluPassword = aluPassword;
    }

    public java.lang.String getAluNombres() {
        return aluNombres;
    }

    public void setAluNombres( java.lang.String aluNombres ) {
        this.aluNombres = aluNombres;
    }

    /**
     * Return the value associated with the column: alu_dni
     */
    public java.lang.String getAluDni() {
        return aluDni;
    }

    /**
     * Set the value related to the column: alu_dni
     *
     * @param aluDni the alu_dni value
     */
    public void setAluDni( java.lang.String aluDni ) {
        this.aluDni = aluDni;
    }

    /**
     * Return the value associated with the column: alu_mail
     */
    public java.lang.String getAluMail() {
        return aluMail;
    }

    /**
     * Set the value related to the column: alu_mail
     *
     * @param aluMail the alu_mail value
     */
    public void setAluMail( java.lang.String aluMail ) {
        this.aluMail = aluMail;
    }

    /**
     * Return the value associated with the column: alu_tipo
     */
    public java.lang.String getAluTipo() {
        return aluTipo;
    }

    /**
     * Set the value related to the column: alu_tipo
     *
     * @param aluTipo the alu_tipo value
     */
    public void setAluTipo( java.lang.String aluTipo ) {
        this.aluTipo = aluTipo;
    }

    /**
     * Return the value associated with the column: alu_modalidad
     */
    public java.lang.String getAluModalidad() {
        return aluModalidad;
    }

    /**
     * Set the value related to the column: alu_modalidad
     *
     * @param aluModalidad the alu_modalidad value
     */
    public void setAluModalidad( java.lang.String aluModalidad ) {
        this.aluModalidad = aluModalidad;
    }

    /**
     * Return the value associated with the column: alu_estado
     */
    public java.lang.String getAluEstado() {
        return aluEstado;
    }

    /**
     * Set the value related to the column: alu_estado
     *
     * @param aluEstado the alu_estado value
     */
    public void setAluEstado( java.lang.String aluEstado ) {
        this.aluEstado = aluEstado;
    }

    /**
     * Return the value associated with the column: alu_foto
     */
    public java.sql.Blob getAluFoto() {
        return aluFoto;
    }

    /**
     * Set the value related to the column: alu_foto
     *
     * @param aluFoto the alu_foto value
     */
    public void setAluFoto( java.sql.Blob aluFoto ) {
        this.aluFoto = aluFoto;
    }

    /**
     * Return the value associated with the column: esp_id_ingreso
     */
    public java.lang.Integer getEspIdIngreso() {
        return espIdIngreso;
    }

    /**
     * Set the value related to the column: esp_id_ingreso
     *
     * @param espIdIngreso the esp_id_ingreso value
     */
    public void setEspIdIngreso( java.lang.Integer espIdIngreso ) {
        this.espIdIngreso = espIdIngreso;
    }

    /**
     * Return the value associated with the column: sem_id
     */
    public java.lang.Integer getSemId() {
        return semId;
    }

    /**
     * Set the value related to the column: sem_id
     *
     * @param semId the sem_id value
     */
    public void setSemId( java.lang.Integer semId ) {
        this.semId = semId;
    }

    /**
     * Return the value associated with the column: alu_activo
     */
    public java.lang.String getAluActivo() {
        return aluActivo;
    }

    /**
     * Set the value related to the column: alu_activo
     *
     * @param aluActivo the alu_activo value
     */
    public void setAluActivo( java.lang.String aluActivo ) {
        this.aluActivo = aluActivo;
    }

    /**
     * Return the value associated with the column: plan_id_ingreso
     */
    public java.lang.Integer getPlanIdIngreso() {
        return planIdIngreso;
    }

    /**
     * Set the value related to the column: plan_id_ingreso
     *
     * @param planIdIngreso the plan_id_ingreso value
     */
    public void setPlanIdIngreso( java.lang.Integer planIdIngreso ) {
        this.planIdIngreso = planIdIngreso;
    }

    /**
     * Return the value associated with the column: plan_id_actual
     */
    public java.lang.Integer getPlanIdActual() {
        return planIdActual;
    }

    /**
     * Set the value related to the column: plan_id_actual
     *
     * @param planIdActual the plan_id_actual value
     */
    public void setPlanIdActual( java.lang.Integer planIdActual ) {
        this.planIdActual = planIdActual;
    }

    /**
     * Return the value associated with the column: alu_duplicado
     */
    public java.lang.String getAluDuplicado() {
        return aluDuplicado;
    }

    /**
     * Set the value related to the column: alu_duplicado
     *
     * @param aluDuplicado the alu_duplicado value
     */
    public void setAluDuplicado( java.lang.String aluDuplicado ) {
        this.aluDuplicado = aluDuplicado;
    }

    /**
     * Return the value associated with the column: alu_procedencia
     */
    public java.lang.String getAluProcedencia() {
        return aluProcedencia;
    }

    /**
     * Set the value related to the column: alu_procedencia
     *
     * @param aluProcedencia the alu_procedencia value
     */
    public void setAluProcedencia( java.lang.String aluProcedencia ) {
        this.aluProcedencia = aluProcedencia;
    }

    public java.lang.String getAluFamiliar() {
        return aluFamiliar;
    }

    public void setAluFamiliar( java.lang.String aluFamiliar ) {
        this.aluFamiliar = aluFamiliar;
    }

    public java.lang.String getAluFormaPago() {
        return aluFormaPago;
    }

    public void setAluFormaPago( java.lang.String aluFormaPago ) {
        this.aluFormaPago = aluFormaPago;
    }

    public String getAluTipoFamiliar() {
        return aluTipoFamiliar;
    }

    public void setAluTipoFamiliar( String aluTipoFamiliar ) {
        this.aluTipoFamiliar = aluTipoFamiliar;
    }

    public String getAluUnidad() {
        return aluUnidad;
    }

    public void setAluUnidad( String aluUnidad ) {
        this.aluUnidad = aluUnidad;
    }

    /**
     * Return the value associated with the column: esp_id
     */
    public net.uch.mapping.AcEspecialidad getEsp() {
        return esp;
    }

    /**
     * Set the value related to the column: esp_id
     *
     * @param esp the esp_id value
     */
    public void setEsp( net.uch.mapping.AcEspecialidad esp ) {
        this.esp = esp;
    }

    /**
     * Return the value associated with the column: AcNotas
     */
    public java.util.Set<net.uch.mapping.AcNota> getAcNotas() {
        return acNotas;
    }

    /**
     * Set the value related to the column: AcNotas
     *
     * @param acNotas the AcNotas value
     */
    public void setAcNotas( java.util.Set<net.uch.mapping.AcNota> acNotas ) {
        this.acNotas = acNotas;
    }

    public void addToAcNotas( net.uch.mapping.AcNota acNota ) {
        if ( null == getAcNotas() ) {
            setAcNotas( new java.util.TreeSet<net.uch.mapping.AcNota>() );
        }
        getAcNotas().add( acNota );
    }

    /**
     * Return the value associated with the column: AdAlumnoTarifas
     */
    public java.util.Set<net.uch.mapping.AdAlumnoTarifa> getAdAlumnoTarifas() {
        return adAlumnoTarifas;
    }

    /**
     * Set the value related to the column: AdAlumnoTarifas
     *
     * @param adAlumnoTarifas the AdAlumnoTarifas value
     */
    public void setAdAlumnoTarifas( java.util.Set<net.uch.mapping.AdAlumnoTarifa> adAlumnoTarifas ) {
        this.adAlumnoTarifas = adAlumnoTarifas;
    }

    public void addToAdAlumnoTarifas( net.uch.mapping.AdAlumnoTarifa adAlumnoTarifa ) {
        if ( null == getAdAlumnoTarifas() ) {
            setAdAlumnoTarifas( new java.util.TreeSet<net.uch.mapping.AdAlumnoTarifa>() );
        }
        getAdAlumnoTarifas().add( adAlumnoTarifa );
    }

    /**
     * Return the value associated with the column: AcAlumnoDocumentos
     */
    public java.util.Set<net.uch.mapping.AcAlumnoDocumento> getAcAlumnoDocumentos() {
        return acAlumnoDocumentos;
    }

    /**
     * Set the value related to the column: AcAlumnoDocumentos
     *
     * @param acAlumnoDocumentos the AcAlumnoDocumentos value
     */
    public void setAcAlumnoDocumentos( java.util.Set<net.uch.mapping.AcAlumnoDocumento> acAlumnoDocumentos ) {
        this.acAlumnoDocumentos = acAlumnoDocumentos;
    }

    public void addToAcAlumnoDocumentos( net.uch.mapping.AcAlumnoDocumento acAlumnoDocumento ) {
        if ( null == getAcAlumnoDocumentos() ) {
            setAcAlumnoDocumentos( new java.util.TreeSet<net.uch.mapping.AcAlumnoDocumento>() );
        }
        getAcAlumnoDocumentos().add( acAlumnoDocumento );
    }

    /**
     * Return the value associated with the column: AcAsistencias
     */
    public java.util.Set<net.uch.mapping.AcAsistencia> getAcAsistencias() {
        return acAsistencias;
    }

    /**
     * Set the value related to the column: AcAsistencias
     *
     * @param acAsistencias the AcAsistencias value
     */
    public void setAcAsistencias( java.util.Set<net.uch.mapping.AcAsistencia> acAsistencias ) {
        this.acAsistencias = acAsistencias;
    }

    public void addToAcAsistencias( net.uch.mapping.AcAsistencia acAsistencia ) {
        if ( null == getAcAsistencias() ) {
            setAcAsistencias( new java.util.TreeSet<net.uch.mapping.AcAsistencia>() );
        }
        getAcAsistencias().add( acAsistencia );
    }

    /**
     * Return the value associated with the column: AcMatriculas
     */
    public java.util.Set<net.uch.mapping.AcMatricula> getAcMatriculas() {
        return acMatriculas;
    }

    /**
     * Set the value related to the column: AcMatriculas
     *
     * @param acMatriculas the AcMatriculas value
     */
    public void setAcMatriculas( java.util.Set<net.uch.mapping.AcMatricula> acMatriculas ) {
        this.acMatriculas = acMatriculas;
    }

    public void addToAcMatriculas( net.uch.mapping.AcMatricula acMatricula ) {
        if ( null == getAcMatriculas() ) {
            setAcMatriculas( new java.util.TreeSet<net.uch.mapping.AcMatricula>() );
        }
        getAcMatriculas().add( acMatricula );
    }

    /**
     * Return the value associated with the column: AdSaldos
     */
    public java.util.Set<net.uch.mapping.AdSaldo> getAdSaldos() {
        return adSaldos;
    }

    /**
     * Set the value related to the column: AdSaldos
     *
     * @param adSaldos the AdSaldos value
     */
    public void setAdSaldos( java.util.Set<net.uch.mapping.AdSaldo> adSaldos ) {
        this.adSaldos = adSaldos;
    }

    public void addToAdSaldos( net.uch.mapping.AdSaldo adSaldo ) {
        if ( null == getAdSaldos() ) {
            setAdSaldos( new java.util.TreeSet<net.uch.mapping.AdSaldo>() );
        }
        getAdSaldos().add( adSaldo );
    }

    /**
     * Return the value associated with the column: AcAmpliaciones
     */
    public java.util.Set<net.uch.mapping.AcAmpliaciones> getAcAmpliaciones() {
        return acAmpliaciones;
    }

    /**
     * Set the value related to the column: AcAmpliaciones
     *
     * @param acAmpliaciones the AcAmpliaciones value
     */
    public void setAcAmpliaciones( java.util.Set<net.uch.mapping.AcAmpliaciones> acAmpliaciones ) {
        this.acAmpliaciones = acAmpliaciones;
    }

    public void addToAcAmpliaciones( net.uch.mapping.AcAmpliaciones acAmpliaciones ) {
        if ( null == getAcAmpliaciones() ) {
            setAcAmpliaciones( new java.util.TreeSet<net.uch.mapping.AcAmpliaciones>() );
        }
        getAcAmpliaciones().add( acAmpliaciones );
    }

    public boolean equals( Object obj ) {
        if ( null == obj ) {
            return false;
        }
        if ( !( obj instanceof net.uch.mapping.AcAlumno ) ) {
            return false;
        } else {
            net.uch.mapping.AcAlumno acAlumno = (net.uch.mapping.AcAlumno)obj;
            if ( null == this.getId() || null == acAlumno.getId() ) {
                return false;
            } else {
                return ( this.getId().equals( acAlumno.getId() ) );
            }
        }
    }

    public int hashCode() {
        if ( Integer.MIN_VALUE == this.hashCode ) {
            if ( null == this.getId() ) {
                return super.hashCode();
            } else {
                String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
                this.hashCode = hashStr.hashCode();
            }
        }
        return this.hashCode;
    }

    public String toString() {
        return super.toString();
    }

    public float getAluMontoPago() {
        return aluMontoPago;
    }

    public void setAluMontoPago( float aluMontoPago ) {
        this.aluMontoPago = aluMontoPago;
    }
}