package net.uch.mapping.base;

import java.sql.Timestamp;
import java.util.Date;
import net.uch.mapping.AdAlumnoTarifa;
import net.uch.mapping.TbUsuario;

/**
 * AbstractAdProrroga entity provides the base persistence definition of the
 * AdProrroga entity. @author MyEclipse Persistence Tools
 */
public abstract class BaseAdProrroga implements java.io.Serializable {

    // Fields
    private Integer proId;
    private AdAlumnoTarifa adAlumnoTarifa;
    private TbUsuario tbUsuario;
    private String proNumero;
    private Date proFecha;
    private String proComentario;
    private Timestamp proCreacion;
    private String proActivo;

    // Constructors
    /** default constructor */
    public BaseAdProrroga() {
    }

    /** minimal constructor */
    public BaseAdProrroga(TbUsuario tbUsuario) {
        this.tbUsuario = tbUsuario;
    }

    /** full constructor */
    public BaseAdProrroga(AdAlumnoTarifa adAlumnoTarifa,
            TbUsuario tbUsuario, String proNumero, Date proFecha,
            String proComentario, Timestamp proCreacion, String proActivo) {
        this.adAlumnoTarifa = adAlumnoTarifa;
        this.tbUsuario = tbUsuario;
        this.proNumero = proNumero;
        this.proFecha = proFecha;
        this.proComentario = proComentario;
        this.proCreacion = proCreacion;
        this.proActivo = proActivo;
    }

    // Property accessors
    public Integer getProId() {
        return this.proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public AdAlumnoTarifa getAdAlumnoTarifa() {
        return this.adAlumnoTarifa;
    }

    public void setAdAlumnoTarifa(AdAlumnoTarifa adAlumnoTarifa) {
        this.adAlumnoTarifa = adAlumnoTarifa;
    }

    public TbUsuario getTbUsuario() {
        return this.tbUsuario;
    }

    public void setTbUsuario(TbUsuario tbUsuario) {
        this.tbUsuario = tbUsuario;
    }

    public String getProNumero() {
        return this.proNumero;
    }

    public void setProNumero(String proNumero) {
        this.proNumero = proNumero;
    }

    public Date getProFecha() {
        return this.proFecha;
    }

    public void setProFecha(Date proFecha) {
        this.proFecha = proFecha;
    }

    public String getProComentario() {
        return this.proComentario;
    }

    public void setProComentario(String proComentario) {
        this.proComentario = proComentario;
    }

    public Timestamp getProCreacion() {
        return this.proCreacion;
    }

    public void setProCreacion(Timestamp proCreacion) {
        this.proCreacion = proCreacion;
    }

    public String getProActivo() {
        return this.proActivo;
    }

    public void setProActivo(String proActivo) {
        this.proActivo = proActivo;
    }
}
