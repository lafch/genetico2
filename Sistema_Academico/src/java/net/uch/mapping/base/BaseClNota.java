package net.uch.mapping.base;

import java.util.Date;
import net.uch.mapping.ClAlumno;
import net.uch.mapping.ClSeccion;
import net.uch.mapping.ClSisEvaPersonalizado;

/**
 * BaseClNota generated by MyEclipse Persistence Tools
 */

public abstract class BaseClNota implements java.io.Serializable {

	// Fields

	private Integer notId;
	private ClAlumno clAlumno;
	private ClSisEvaPersonalizado clSisEvaPersonalizado;
	private ClSeccion clSeccion;
	private Float notNota;
	private String notObservacion;
	private Date notCreacion;
	private String notActivo;
	private String notTipo;

	// Constructors

	/** default constructor */
	public BaseClNota() {
	}

	/** minimal constructor */
	public BaseClNota(Integer notId, ClAlumno clAlumno,
			ClSisEvaPersonalizado clSisEvaPersonalizado) {
		this.notId = notId;
		this.clAlumno = clAlumno;
		this.clSisEvaPersonalizado = clSisEvaPersonalizado;
	}

	/** full constructor */
	public BaseClNota(Integer notId, ClAlumno clAlumno,
			ClSisEvaPersonalizado clSisEvaPersonalizado, ClSeccion clSeccion,
			Float notNota, String notObservacion, Date notCreacion,
			String notActivo, String notTipo) {
		this.notId = notId;
		this.clAlumno = clAlumno;
		this.clSisEvaPersonalizado = clSisEvaPersonalizado;
		this.clSeccion = clSeccion;
		this.notNota = notNota;
		this.notObservacion = notObservacion;
		this.notCreacion = notCreacion;
		this.notActivo = notActivo;
		this.notTipo = notTipo;
	}

	// Property accessors

	public Integer getNotId() {
		return this.notId;
	}

	public void setNotId(Integer notId) {
		this.notId = notId;
	}

	public ClAlumno getClAlumno() {
		return this.clAlumno;
	}

	public void setClAlumno(ClAlumno clAlumno) {
		this.clAlumno = clAlumno;
	}

	public ClSisEvaPersonalizado getClSisEvaPersonalizado() {
		return this.clSisEvaPersonalizado;
	}

	public void setClSisEvaPersonalizado(
			ClSisEvaPersonalizado clSisEvaPersonalizado) {
		this.clSisEvaPersonalizado = clSisEvaPersonalizado;
	}

	public ClSeccion getClSeccion() {
		return this.clSeccion;
	}

	public void setClSeccion(ClSeccion clSeccion) {
		this.clSeccion = clSeccion;
	}

	public Float getNotNota() {
		return this.notNota;
	}

	public void setNotNota(Float notNota) {
		this.notNota = notNota;
	}

	public String getNotObservacion() {
		return this.notObservacion;
	}

	public void setNotObservacion(String notObservacion) {
		this.notObservacion = notObservacion;
	}

	public Date getNotCreacion() {
		return this.notCreacion;
	}

	public void setNotCreacion(Date notCreacion) {
		this.notCreacion = notCreacion;
	}

	public String getNotActivo() {
		return this.notActivo;
	}

	public void setNotActivo(String notActivo) {
		this.notActivo = notActivo;
	}

	public String getNotTipo() {
		return this.notTipo;
	}

	public void setNotTipo(String notTipo) {
		this.notTipo = notTipo;
	}

}