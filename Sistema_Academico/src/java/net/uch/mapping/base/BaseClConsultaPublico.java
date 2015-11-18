package net.uch.mapping.base;

import java.util.Date;
import net.uch.mapping.AcLocal;
import net.uch.mapping.ClAlumno;
import net.uch.mapping.ClArbolAcademico;

/**
 * AbstractClConsultaPublico generated by MyEclipse Persistence Tools
 */
public abstract class BaseClConsultaPublico implements java.io.Serializable {

    // Fields
    private Integer matId;
    private Integer espId;
    private Integer conId;
    private ClAlumno clAlumno;
    private AcLocal acLocal;
    private Integer areaId;
    private Integer curId;
    private String medId;
    private String medIdCentros;
    private String medIdPeriodicos;
    private Integer detmedId;
    private String conObservacionMedio;
    private Date conFechaContacto;
    private String conProcedencia;
    private String conObservacionRegistro;
    private Integer usuId;
    private String conActivo;
    private Integer plaId;
    private String conEstadoMatricula;
    private Date conFechaRegistro;
    private String sConPrioridad;
    private ClArbolAcademico clArbolAcademico;

    // Constructors
    /** default constructor */
    public BaseClConsultaPublico () {
    }

    public BaseClConsultaPublico ( Integer conId ) {
        this.conId = conId;
    }

    /** full constructor */
    public BaseClConsultaPublico ( ClAlumno clAlumno, AcLocal acLocal, Integer areaId , Integer curId, String medId,String medIdCentros,String medIdPeriodicos, Integer detmedId,
            String conObservacionMedio, Date conFechaContacto,
            String conProcedencia, String conObservacionRegistro,
            Integer usuId, String conActivo, Integer plaId,
            String conEstadoMatricula, Date conFechaRegistro, String sConPrioridad, ClArbolAcademico clArbolAcademico, Integer espId, Integer matId ) {
        this.clAlumno = clAlumno;
        this.acLocal = acLocal;
        this.areaId = areaId;
        this.curId = curId;
        this.medId = medId;
        this.detmedId = detmedId;
        this.medIdCentros = medIdCentros;
        this.medIdPeriodicos = medIdPeriodicos;
        this.conObservacionMedio = conObservacionMedio;
        this.conFechaContacto = conFechaContacto;
        this.conProcedencia = conProcedencia;
        this.conObservacionRegistro = conObservacionRegistro;
        this.usuId = usuId;
        this.conActivo = conActivo;
        this.plaId = plaId;
        this.conEstadoMatricula = conEstadoMatricula;
        this.conFechaRegistro = conFechaRegistro;
        this.sConPrioridad = sConPrioridad;
        this.clArbolAcademico = clArbolAcademico;
        this.espId = espId;
        this.matId = matId;
    }

    // Property accessors

    public Integer getMatId() {
        return matId;
    }

    public void setMatId(Integer matId) {
        this.matId = matId;
    }
    

    public Integer getEspId () {
        return espId;
    }

    public void setEspId ( Integer espId ) {
        this.espId = espId;
    }
    public Integer getAreaId () {
        return areaId;
    }

    public void setAreaId ( Integer areaId ) {
        this.areaId = areaId;
    }

    public Integer getCurId () {
        return curId;
    }

    public void setCurId ( Integer curId ) {
        this.curId = curId;
    }
    
    public String getsConPrioridad () {
        return sConPrioridad;
    }

    public void setsConPrioridad ( String sConPrioridad ) {
        this.sConPrioridad = sConPrioridad;
    }

    public Integer getConId () {
        return this.conId;
    }

    public void setConId ( Integer conId ) {
        this.conId = conId;
    }

    public ClAlumno getClAlumno () {
        return this.clAlumno;
    }

    public void setClAlumno ( ClAlumno clAlumno ) {
        this.clAlumno = clAlumno;
    }

    public AcLocal getAcLocal () {
        return this.acLocal;
    }

    public void setAcLocal ( AcLocal acLocal ) {
        this.acLocal = acLocal;
    }

    public String getMedId () {
        return this.medId;
    }

    public void setMedId ( String medId ) {
        this.medId = medId;
    }
    
     public String getMedIdCentros () {
        return this.medIdCentros;
    }

    public void setMedIdCentros ( String medIdCentros ) {
        this.medIdCentros = medIdCentros;
    }
    
     public String getMedIdPeriodicos () {
        return this.medIdPeriodicos;
    }

    public void setMedIdPeriodicos ( String medIdPeriodicos ) {
        this.medIdPeriodicos = medIdPeriodicos;
    }

    public Integer getDetmedId () {
        return this.detmedId;
    }

    public void setDetmedId ( Integer detmedId ) {
        this.detmedId = detmedId;
    }

    public String getConObservacionMedio () {
        return this.conObservacionMedio;
    }

    public void setConObservacionMedio ( String conObservacionMedio ) {
        this.conObservacionMedio = conObservacionMedio;
    }

    public Date getConFechaContacto () {
        return this.conFechaContacto;
    }

    public void setConFechaContacto ( Date conFechaContacto ) {
        this.conFechaContacto = conFechaContacto;
    }

    public String getConProcedencia () {
        return this.conProcedencia;
    }

    public void setConProcedencia ( String conProcedencia ) {
        this.conProcedencia = conProcedencia;
    }

    public String getConObservacionRegistro () {
        return this.conObservacionRegistro;
    }

    public void setConObservacionRegistro ( String conObservacionRegistro ) {
        this.conObservacionRegistro = conObservacionRegistro;
    }

    public Integer getUsuId () {
        return this.usuId;
    }

    public void setUsuId ( Integer usuId ) {
        this.usuId = usuId;
    }

    public String getConActivo () {
        return this.conActivo;
    }

    public void setConActivo ( String conActivo ) {
        this.conActivo = conActivo;
    }

    public Integer getPlaId () {
        return this.plaId;
    }

    public void setPlaId ( Integer plaId ) {
        this.plaId = plaId;
    }

    public String getConEstadoMatricula () {
        return this.conEstadoMatricula;
    }

    public void setConEstadoMatricula ( String conEstadoMatricula ) {
        this.conEstadoMatricula = conEstadoMatricula;
    }

    public Date getConFechaRegistro () {
        return this.conFechaRegistro;
    }

    public void setConFechaRegistro ( Date conFechaRegistro ) {
        this.conFechaRegistro = conFechaRegistro;
    }

    /**
     * @return the clArbolAcademico
     */
    public ClArbolAcademico getClArbolAcademico () {
        return clArbolAcademico;
    }

    /**
     * @param clArbolAcademico the clArbolAcademico to set
     */
    public void setClArbolAcademico ( ClArbolAcademico clArbolAcademico ) {
        this.clArbolAcademico = clArbolAcademico;
    }
}