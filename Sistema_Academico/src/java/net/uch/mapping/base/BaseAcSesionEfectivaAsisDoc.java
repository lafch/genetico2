package net.uch.mapping.base;

import java.sql.Timestamp;

import net.uch.mapping.AcDocente;
import net.uch.mapping.AcSesionAsistencia;


/**
 * AbstractAcSesionEfectivaAsisDoc entity provides the base persistence definition of the AcSesionEfectivaAsisDoc entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseAcSesionEfectivaAsisDoc  implements java.io.Serializable {


    // Fields    

     private Integer sesefeasisdocId;
     private AcSesionAsistencia acSesionAsistencia;
     private AcDocente acDocente;
     private Timestamp sesefeasisdocRegistro;
     private String sesefeasisdocObs;
     private Integer sesefeasisdocTole;
     private String sesefeasisdocActivo;
     private String sesefeasisdocTipo;
     private Timestamp sesefeasisdocSalida;


    // Constructors

    /** default constructor */
    public BaseAcSesionEfectivaAsisDoc() {
    }

	/** minimal constructor */
    public BaseAcSesionEfectivaAsisDoc(AcSesionAsistencia acSesionAsistencia, AcDocente acDocente) {
        this.acSesionAsistencia = acSesionAsistencia;
        this.acDocente = acDocente;
    }
    
    /** full constructor */
    public BaseAcSesionEfectivaAsisDoc(AcSesionAsistencia acSesionAsistencia, AcDocente acDocente, Timestamp sesefeasisdocRegistro, String sesefeasisdocObs, Integer sesefeasisdocTole, String sesefeasisdocActivo, String sesefeasisdocTipo, Timestamp sesefeasisdocSalida) {
        this.acSesionAsistencia = acSesionAsistencia;
        this.acDocente = acDocente;
        this.sesefeasisdocRegistro = sesefeasisdocRegistro;
        this.sesefeasisdocObs = sesefeasisdocObs;
        this.sesefeasisdocTole = sesefeasisdocTole;
        this.sesefeasisdocActivo = sesefeasisdocActivo;
        this.sesefeasisdocTipo = sesefeasisdocTipo;
        this.sesefeasisdocSalida = sesefeasisdocSalida;
    }

   
    // Property accessors

    public Integer getSesefeasisdocId() {
        return this.sesefeasisdocId;
    }
    
    public void setSesefeasisdocId(Integer sesefeasisdocId) {
        this.sesefeasisdocId = sesefeasisdocId;
    }

    public AcSesionAsistencia getAcSesionAsistencia() {
        return this.acSesionAsistencia;
    }
    
    public void setAcSesionAsistencia(AcSesionAsistencia acSesionAsistencia) {
        this.acSesionAsistencia = acSesionAsistencia;
    }

    public AcDocente getAcDocente() {
        return this.acDocente;
    }
    
    public void setAcDocente(AcDocente acDocente) {
        this.acDocente = acDocente;
    }

    public Timestamp getSesefeasisdocRegistro() {
        return this.sesefeasisdocRegistro;
    }
    
    public void setSesefeasisdocRegistro(Timestamp sesefeasisdocRegistro) {
        this.sesefeasisdocRegistro = sesefeasisdocRegistro;
    }

    public String getSesefeasisdocObs() {
        return this.sesefeasisdocObs;
    }
    
    public void setSesefeasisdocObs(String sesefeasisdocObs) {
        this.sesefeasisdocObs = sesefeasisdocObs;
    }

    public Integer getSesefeasisdocTole() {
        return this.sesefeasisdocTole;
    }
    
    public void setSesefeasisdocTole(Integer sesefeasisdocTole) {
        this.sesefeasisdocTole = sesefeasisdocTole;
    }

    public String getSesefeasisdocActivo() {
        return this.sesefeasisdocActivo;
    }
    
    public void setSesefeasisdocActivo(String sesefeasisdocActivo) {
        this.sesefeasisdocActivo = sesefeasisdocActivo;
    }

    public String getSesefeasisdocTipo() {
        return this.sesefeasisdocTipo;
    }
    
    public void setSesefeasisdocTipo(String sesefeasisdocTipo) {
        this.sesefeasisdocTipo = sesefeasisdocTipo;
    }

    public Timestamp getSesefeasisdocSalida() {
        return this.sesefeasisdocSalida;
    }
    
    public void setSesefeasisdocSalida(Timestamp sesefeasisdocSalida) {
        this.sesefeasisdocSalida = sesefeasisdocSalida;
    }
   








}