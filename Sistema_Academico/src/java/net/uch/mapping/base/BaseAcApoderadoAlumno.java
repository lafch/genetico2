package net.uch.mapping.base;

import java.io.Serializable;
import net.uch.mapping.AcAlumno;
import net.uch.mapping.AcApoderado;

/**
 * AbstractAcApoderadoAlumno entity provides the base persistence definition of
 * the AcApoderadoAlumno entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class BaseAcApoderadoAlumno implements Serializable {

	// Fields

	private Integer apoaluId;
	private AcAlumno acAlumno;
	private AcApoderado acApoderado;
	private String apoaluActivo;

	// Constructors

	/** default constructor */
	public BaseAcApoderadoAlumno() {
	}

	/** minimal constructor */
	public BaseAcApoderadoAlumno(Integer apoaluId) {
		this.apoaluId = apoaluId;
	}

	/** full constructor */
	public BaseAcApoderadoAlumno(Integer apoaluId, AcAlumno acAlumno,
			AcApoderado acApoderado, String apoaluActivo) {
		this.apoaluId = apoaluId;
		this.acAlumno = acAlumno;
		this.acApoderado = acApoderado;
		this.apoaluActivo = apoaluActivo;
	}

	// Property accessors

	public Integer getApoaluId() {
		return this.apoaluId;
	}

	public void setApoaluId(Integer apoaluId) {
		this.apoaluId = apoaluId;
	}

	public AcAlumno getAcAlumno() {
		return this.acAlumno;
	}

	public void setAcAlumno(AcAlumno acAlumno) {
		this.acAlumno = acAlumno;
	}

	public AcApoderado getAcApoderado() {
		return this.acApoderado;
	}

	public void setAcApoderado(AcApoderado acApoderado) {
		this.acApoderado = acApoderado;
	}

	public String getApoaluActivo() {
		return this.apoaluActivo;
	}

	public void setApoaluActivo(String apoaluActivo) {
		this.apoaluActivo = apoaluActivo;
	}

}