package net.uch.mapping.base;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import net.uch.mapping.TsSolicitud;

/**
 * AbstractTsSolicitudPersona generated by MyEclipse Persistence Tools
 */

public abstract class BaseTsSolicitudPersona implements
		java.io.Serializable {

	// Fields

	private Integer solperId;
	private TsSolicitud tsSolicitud;
	private Integer solperPersoId;
	private String solperPersoTipo;
	private String solperAprovada;
	private Date solperFechaRegistro;
	private Date solperFechaRevision;
	private String solperActivo;
	private String solperDescripcion;
	private String solperObservacion;
	private Set tsFuts = new HashSet(0);
        private Date solperFechaAprovacion;

	// Constructors

	/** default constructor */
	public BaseTsSolicitudPersona() {
	}

	/** minimal constructor */
	public BaseTsSolicitudPersona(Integer solperId) {
		this.solperId = solperId;
	}

	/** full constructor */
	public BaseTsSolicitudPersona(Integer solperId,
			TsSolicitud tsSolicitud, Integer solperPersoId,
			String solperPersoTipo, String solperAprovada,
			Date solperFechaRegistro, Date solperFechaRevision,
			String solperActivo, String solperDescripcion,
			String solperObservacion, Set tsFuts,Date solperFechaAprovacion) {
		this.solperId = solperId;
		this.tsSolicitud = tsSolicitud;
		this.solperPersoId = solperPersoId;
		this.solperPersoTipo = solperPersoTipo;
		this.solperAprovada = solperAprovada;
		this.solperFechaRegistro = solperFechaRegistro;
		this.solperFechaRevision = solperFechaRevision;
		this.solperActivo = solperActivo;
		this.solperDescripcion = solperDescripcion;
		this.solperObservacion = solperObservacion;
		this.tsFuts = tsFuts;
                this.solperFechaAprovacion=solperFechaAprovacion;
	}

	// Property accessors

	public Integer getSolperId() {
		return this.solperId;
	}

	public void setSolperId(Integer solperId) {
		this.solperId = solperId;
	}

	public TsSolicitud getTsSolicitud() {
		return this.tsSolicitud;
	}

	public void setTsSolicitud(TsSolicitud tsSolicitud) {
		this.tsSolicitud = tsSolicitud;
	}

	public Integer getSolperPersoId() {
		return this.solperPersoId;
	}

	public void setSolperPersoId(Integer solperPersoId) {
		this.solperPersoId = solperPersoId;
	}

	public String getSolperPersoTipo() {
		return this.solperPersoTipo;
	}

	public void setSolperPersoTipo(String solperPersoTipo) {
		this.solperPersoTipo = solperPersoTipo;
	}

	public String getSolperAprovada() {
		return this.solperAprovada;
	}

	public void setSolperAprovada(String solperAprovada) {
		this.solperAprovada = solperAprovada;
	}

	public Date getSolperFechaRegistro() {
		return this.solperFechaRegistro;
	}

	public void setSolperFechaRegistro(Date solperFechaRegistro) {
		this.solperFechaRegistro = solperFechaRegistro;
	}

	public Date getSolperFechaRevision() {
		return this.solperFechaRevision;
	}

	public void setSolperFechaRevision(Date solperFechaRevision) {
		this.solperFechaRevision = solperFechaRevision;
	}

	public String getSolperActivo() {
		return this.solperActivo;
	}

	public void setSolperActivo(String solperActivo) {
		this.solperActivo = solperActivo;
	}

	public String getSolperDescripcion() {
		return this.solperDescripcion;
	}

	public void setSolperDescripcion(String solperDescripcion) {
		this.solperDescripcion = solperDescripcion;
	}

	public String getSolperObservacion() {
		return this.solperObservacion;
	}

	public void setSolperObservacion(String solperObservacion) {
		this.solperObservacion = solperObservacion;
	}

	public Set getTsFuts() {
		return this.tsFuts;
	}

	public void setTsFuts(Set tsFuts) {
		this.tsFuts = tsFuts;
	}

    public Date getSolperFechaAprovacion() {
        return solperFechaAprovacion;
    }

    public void setSolperFechaAprovacion(Date solperFechaAprovacion) {
        this.solperFechaAprovacion = solperFechaAprovacion;
    }
        
        

}