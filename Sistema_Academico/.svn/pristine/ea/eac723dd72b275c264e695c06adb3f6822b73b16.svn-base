package net.uch.mapping.base;

import java.sql.Blob;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import net.uch.mapping.AcCalendario;

/**
 * AbstractAcCalendarioActividades entity provides the base persistence
 * definition of the AcCalendarioActividades entity. @author MyEclipse
 * Persistence Tools
 */
public abstract class BaseAcCalendarioActividades implements
        java.io.Serializable {

    // Fields
    private Integer calactId;
    private AcCalendario acCalendario;
    private String calactDesc;
    private String calactActivo;
    private Blob calactImg;
    private Timestamp calactCreacion;
    private String calactTipo;
    private Integer usuId;
    private String calactTitulo;
    private String calactPublicado;
    private Timestamp calactFechaInicio;
    private Timestamp calactFechaFin;
    private Set acActividadAlcances = new HashSet(0);

    // Constructors
    /** default constructor */
    public BaseAcCalendarioActividades() {
    }

    /** minimal constructor */
    public BaseAcCalendarioActividades(AcCalendario acCalendario,
            Integer usuId) {
        this.acCalendario = acCalendario;
        this.usuId = usuId;
    }

    /** full constructor */
    public BaseAcCalendarioActividades(AcCalendario acCalendario,
            String calactDesc, String calactActivo, Blob calactImg,
            Timestamp calactCreacion, String calactTipo, Integer usuId,
            String calactTitulo, String calactPublicado,
            Timestamp calactFechaInicio, Timestamp calactFechaFin,
            Set acActividadAlcances) {
        this.acCalendario = acCalendario;
        this.calactDesc = calactDesc;
        this.calactActivo = calactActivo;
        this.calactImg = calactImg;
        this.calactCreacion = calactCreacion;
        this.calactTipo = calactTipo;
        this.usuId = usuId;
        this.calactTitulo = calactTitulo;
        this.calactPublicado = calactPublicado;
        this.calactFechaInicio = calactFechaInicio;
        this.calactFechaFin = calactFechaFin;
        this.acActividadAlcances = acActividadAlcances;
    }

    // Property accessors
    public Integer getCalactId() {
        return this.calactId;
    }

    public void setCalactId(Integer calactId) {
        this.calactId = calactId;
    }

    public AcCalendario getAcCalendario() {
        return this.acCalendario;
    }

    public void setAcCalendario(AcCalendario acCalendario) {
        this.acCalendario = acCalendario;
    }

    public String getCalactDesc() {
        return this.calactDesc;
    }

    public void setCalactDesc(String calactDesc) {
        this.calactDesc = calactDesc;
    }

    public String getCalactActivo() {
        return this.calactActivo;
    }

    public void setCalactActivo(String calactActivo) {
        this.calactActivo = calactActivo;
    }

    public Blob getCalactImg() {
        return calactImg;
    }

    public void setCalactImg(Blob calactImg) {
        this.calactImg = calactImg;
    }

    public Timestamp getCalactCreacion() {
        return this.calactCreacion;
    }

    public void setCalactCreacion(Timestamp calactCreacion) {
        this.calactCreacion = calactCreacion;
    }

    public String getCalactTipo() {
        return this.calactTipo;
    }

    public void setCalactTipo(String calactTipo) {
        this.calactTipo = calactTipo;
    }

    public Integer getUsuId() {
        return this.usuId;
    }

    public void setUsuId(Integer usuId) {
        this.usuId = usuId;
    }

    public String getCalactTitulo() {
        return this.calactTitulo;
    }

    public void setCalactTitulo(String calactTitulo) {
        this.calactTitulo = calactTitulo;
    }

    public String getCalactPublicado() {
        return this.calactPublicado;
    }

    public void setCalactPublicado(String calactPublicado) {
        this.calactPublicado = calactPublicado;
    }

    public Timestamp getCalactFechaInicio() {
        return this.calactFechaInicio;
    }

    public void setCalactFechaInicio(Timestamp calactFechaInicio) {
        this.calactFechaInicio = calactFechaInicio;
    }

    public Timestamp getCalactFechaFin() {
        return this.calactFechaFin;
    }

    public void setCalactFechaFin(Timestamp calactFechaFin) {
        this.calactFechaFin = calactFechaFin;
    }

    public Set getAcActividadAlcances() {
        return this.acActividadAlcances;
    }

    public void setAcActividadAlcances(Set acActividadAlcances) {
        this.acActividadAlcances = acActividadAlcances;
    }

    @Override
    public String toString() {
        return "\tcalactId: " + calactId
                + "\tacCalendario: " + acCalendario.getClass()
                + "\tcalactDesc: " + calactDesc
                + "\tcalactActivo: " + calactActivo
                + "\tcalactImg: " + calactImg
                + "\tcalactCreacion: " + calactCreacion
                + "\tcalactTipo: " + calactTipo
                + "\tusuId: " + usuId
                + "\tcalactTitulo: " + calactTitulo
                + "\tcalactPublicado: " + calactPublicado
                + "\tcalactFechaInicio: " + calactFechaInicio
                + "\tcalactFechaFin: " + calactFechaFin
                + "\tacActividadAlcancesSet: " + acActividadAlcances.size();
    }
}
