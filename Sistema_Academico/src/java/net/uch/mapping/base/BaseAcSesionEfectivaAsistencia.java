package net.uch.mapping.base;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import net.uch.mapping.AcSesionAsistencia;


/**
 * AbstractAcSesionEfectivaAsistencia entity provides the base persistence definition of the AcSesionEfectivaAsistencia entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseAcSesionEfectivaAsistencia  implements java.io.Serializable {


    // Fields    

     private Integer sesefeasisId;
     private AcSesionAsistencia acSesionAsistencia;
     private Timestamp sesefeasisRegistro;
     private String sesefeasisActivo;
     private Set acSesionEfectivaAsisDets = new HashSet(0);


    // Constructors

    /** default constructor */
    public BaseAcSesionEfectivaAsistencia() {
    }

	/** minimal constructor */
    public BaseAcSesionEfectivaAsistencia(AcSesionAsistencia acSesionAsistencia) {
        this.acSesionAsistencia = acSesionAsistencia;
    }
    
    /** full constructor */
    public BaseAcSesionEfectivaAsistencia(AcSesionAsistencia acSesionAsistencia, Timestamp sesefeasisRegistro, String sesefeasisActivo, Set acSesionEfectivaAsisDets) {
        this.acSesionAsistencia = acSesionAsistencia;
        this.sesefeasisRegistro = sesefeasisRegistro;
        this.sesefeasisActivo = sesefeasisActivo;
        this.acSesionEfectivaAsisDets = acSesionEfectivaAsisDets;
    }

   
    // Property accessors

    public Integer getSesefeasisId() {
        return this.sesefeasisId;
    }
    
    public void setSesefeasisId(Integer sesefeasisId) {
        this.sesefeasisId = sesefeasisId;
    }

    public AcSesionAsistencia getAcSesionAsistencia() {
        return this.acSesionAsistencia;
    }
    
    public void setAcSesionAsistencia(AcSesionAsistencia acSesionAsistencia) {
        this.acSesionAsistencia = acSesionAsistencia;
    }

    public Timestamp getSesefeasisRegistro() {
        return this.sesefeasisRegistro;
    }
    
    public void setSesefeasisRegistro(Timestamp sesefeasisRegistro) {
        this.sesefeasisRegistro = sesefeasisRegistro;
    }

    public String getSesefeasisActivo() {
        return this.sesefeasisActivo;
    }
    
    public void setSesefeasisActivo(String sesefeasisActivo) {
        this.sesefeasisActivo = sesefeasisActivo;
    }

    public Set getAcSesionEfectivaAsisDets() {
        return this.acSesionEfectivaAsisDets;
    }
    
    public void setAcSesionEfectivaAsisDets(Set acSesionEfectivaAsisDets) {
        this.acSesionEfectivaAsisDets = acSesionEfectivaAsisDets;
    }
   








}