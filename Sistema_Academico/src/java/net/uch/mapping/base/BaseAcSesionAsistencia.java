package net.uch.mapping.base;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import net.uch.mapping.AcSeccion;


/**
 * AbstractAcSesionAsistencia entity provides the base persistence definition of the AcSesionAsistencia entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseAcSesionAsistencia  implements java.io.Serializable {


    // Fields    

     private Integer sesId;
     private AcSeccion acSeccion;
     private Date sesFechaSesion;
     private Date sesFechaRegistro;
     private Timestamp sesFechaInicio;
     private Timestamp sesFechaFin;
     private String sesActivo;
     private Integer sesTimeTolerancia;
     private Integer sesTimeAntes;
     private Integer sesTimeTope;
     private Integer aulId;
     private String sesEstado;
     private Integer sesFechaSalida;
     private Integer docId;
     private Integer docIdBk;
     private String sesTipo;
     private String sesTipoAsistencia;
     private String sesEstadoDocTipo;
     private String sesObservacion;
     private Set acSesionEfectivaAsistencias = new HashSet(0);
     private Set acSesionEfectivaAsisDocs = new HashSet(0);


    // Constructors

    /** default constructor */
    public BaseAcSesionAsistencia() {
    }

	/** minimal constructor */
    public BaseAcSesionAsistencia(AcSeccion acSeccion, Integer docId, Integer docIdBk) {
        this.acSeccion = acSeccion;
        this.docId = docId;
        this.docIdBk = docIdBk;
    }
    
    /** full constructor */
    public BaseAcSesionAsistencia(AcSeccion acSeccion, Date sesFechaSesion, Date sesFechaRegistro, Timestamp sesFechaInicio, Timestamp sesFechaFin, String sesActivo, Integer sesTimeTolerancia, Integer sesTimeAntes, Integer sesTimeTope, Integer aulId, String sesEstado, Integer sesFechaSalida, Integer docId, Integer docIdBk, String sesTipo, String sesTipoAsistencia,String sesEstadoDocTipo,String sesObservacion, Set acSesionEfectivaAsistencias, Set acSesionEfectivaAsisDocs) {
        this.acSeccion = acSeccion;
        this.sesFechaSesion = sesFechaSesion;
        this.sesFechaRegistro = sesFechaRegistro;
        this.sesFechaInicio = sesFechaInicio;
        this.sesFechaFin = sesFechaFin;
        this.sesActivo = sesActivo;
        this.sesTimeTolerancia = sesTimeTolerancia;
        this.sesTimeAntes = sesTimeAntes;
        this.sesTimeTope = sesTimeTope;
        this.aulId = aulId;
        this.sesEstado = sesEstado;
        this.sesFechaSalida = sesFechaSalida;
        this.docId = docId;
        this.docIdBk = docIdBk;
        this.sesTipo = sesTipo;
        this.sesTipoAsistencia = sesTipoAsistencia;
        this.sesEstadoDocTipo=sesEstadoDocTipo;
        this.sesObservacion=sesObservacion;
        this.acSesionEfectivaAsistencias = acSesionEfectivaAsistencias;
        this.acSesionEfectivaAsisDocs = acSesionEfectivaAsisDocs;
    }

   
    // Property accessors

    public Integer getSesId() {
        return this.sesId;
    }
    
    public void setSesId(Integer sesId) {
        this.sesId = sesId;
    }

    public AcSeccion getAcSeccion() {
        return this.acSeccion;
    }
    
    public void setAcSeccion(AcSeccion acSeccion) {
        this.acSeccion = acSeccion;
    }

    public Date getSesFechaSesion() {
        return this.sesFechaSesion;
    }
    
    public void setSesFechaSesion(Date sesFechaSesion) {
        this.sesFechaSesion = sesFechaSesion;
    }

    public Date getSesFechaRegistro() {
        return this.sesFechaRegistro;
    }
    
    public void setSesFechaRegistro(Date sesFechaRegistro) {
        this.sesFechaRegistro = sesFechaRegistro;
    }

    public Timestamp getSesFechaInicio() {
        return this.sesFechaInicio;
    }
    
    public void setSesFechaInicio(Timestamp sesFechaInicio) {
        this.sesFechaInicio = sesFechaInicio;
    }

    public Timestamp getSesFechaFin() {
        return this.sesFechaFin;
    }
    
    public void setSesFechaFin(Timestamp sesFechaFin) {
        this.sesFechaFin = sesFechaFin;
    }

    public String getSesActivo() {
        return this.sesActivo;
    }
    
    public void setSesActivo(String sesActivo) {
        this.sesActivo = sesActivo;
    }

    public Integer getSesTimeTolerancia() {
        return this.sesTimeTolerancia;
    }
    
    public void setSesTimeTolerancia(Integer sesTimeTolerancia) {
        this.sesTimeTolerancia = sesTimeTolerancia;
    }

    public Integer getSesTimeAntes() {
        return this.sesTimeAntes;
    }
    
    public void setSesTimeAntes(Integer sesTimeAntes) {
        this.sesTimeAntes = sesTimeAntes;
    }

    public Integer getSesTimeTope() {
        return this.sesTimeTope;
    }
    
    public void setSesTimeTope(Integer sesTimeTope) {
        this.sesTimeTope = sesTimeTope;
    }

    public Integer getAulId() {
        return this.aulId;
    }
    
    public void setAulId(Integer aulId) {
        this.aulId = aulId;
    }

    public String getSesEstado() {
        return this.sesEstado;
    }
    
    public void setSesEstado(String sesEstado) {
        this.sesEstado = sesEstado;
    }

    public Integer getSesFechaSalida() {
        return this.sesFechaSalida;
    }
    
    public void setSesFechaSalida(Integer sesFechaSalida) {
        this.sesFechaSalida = sesFechaSalida;
    }

    public Integer getDocId() {
        return this.docId;
    }
    
    public void setDocId(Integer docId) {
        this.docId = docId;
    }

    public Integer getDocIdBk() {
        return this.docIdBk;
    }
    
    public void setDocIdBk(Integer docIdBk) {
        this.docIdBk = docIdBk;
    }

    public String getSesTipo() {
        return this.sesTipo;
    }
    
    public void setSesTipo(String sesTipo) {
        this.sesTipo = sesTipo;
    }

    public String getSesTipoAsistencia() {
        return this.sesTipoAsistencia;
    }
    
    public void setSesTipoAsistencia(String sesTipoAsistencia) {
        this.sesTipoAsistencia = sesTipoAsistencia;
    }

    public String getSesEstadoDocTipo() {
        return sesEstadoDocTipo;
    }

    public void setSesEstadoDocTipo(String sesEstadoDocTipo) {
        this.sesEstadoDocTipo = sesEstadoDocTipo;
    }

    public String getSesObservacion() {
        return sesObservacion;
    }

    public void setSesObservacion(String sesObservacion) {
        this.sesObservacion = sesObservacion;
    }

    public Set getAcSesionEfectivaAsistencias() {
        return this.acSesionEfectivaAsistencias;
    }
    
    public void setAcSesionEfectivaAsistencias(Set acSesionEfectivaAsistencias) {
        this.acSesionEfectivaAsistencias = acSesionEfectivaAsistencias;
    }

    public Set getAcSesionEfectivaAsisDocs() {
        return this.acSesionEfectivaAsisDocs;
    }
    
    public void setAcSesionEfectivaAsisDocs(Set acSesionEfectivaAsisDocs) {
        this.acSesionEfectivaAsisDocs = acSesionEfectivaAsisDocs;
    }
   








}