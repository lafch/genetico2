package net.uch.mapping.base;

import net.uch.mapping.ClMatricula;
import net.uch.mapping.ClSeccion;

/**
 * AbstractClMatriculaTaller entity provides the base persistence definition of
 * the ClMatriculaTaller entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseClMatriculaTaller implements java.io.Serializable {

	// Fields

	private Integer mattalId;
	private ClSeccion clSeccion;
	private ClMatricula clMatricula;
	private String mattalActivo;
	private String mattalObs;
	private String mattalEstado;

	// Constructors

	/** default constructor */
	public BaseClMatriculaTaller() {
	}

	/** minimal constructor */
	public BaseClMatriculaTaller(ClSeccion clSeccion,
			ClMatricula clMatricula) {
		this.clSeccion = clSeccion;
		this.clMatricula = clMatricula;
	}

	/** full constructor */
	public BaseClMatriculaTaller(ClSeccion clSeccion,
			ClMatricula clMatricula, String mattalActivo, String mattalObs,
			String mattalEstado) {
		this.clSeccion = clSeccion;
		this.clMatricula = clMatricula;
		this.mattalActivo = mattalActivo;
		this.mattalObs = mattalObs;
		this.mattalEstado = mattalEstado;
	}

	// Property accessors

	public Integer getMattalId() {
		return this.mattalId;
	}

	public void setMattalId(Integer mattalId) {
		this.mattalId = mattalId;
	}

	public ClSeccion getClSeccion() {
		return this.clSeccion;
	}

	public void setClSeccion(ClSeccion clSeccion) {
		this.clSeccion = clSeccion;
	}

	public ClMatricula getClMatricula() {
		return this.clMatricula;
	}

	public void setClMatricula(ClMatricula clMatricula) {
		this.clMatricula = clMatricula;
	}

	public String getMattalActivo() {
		return this.mattalActivo;
	}

	public void setMattalActivo(String mattalActivo) {
		this.mattalActivo = mattalActivo;
	}

	public String getMattalObs() {
		return this.mattalObs;
	}

	public void setMattalObs(String mattalObs) {
		this.mattalObs = mattalObs;
	}

	public String getMattalEstado() {
		return this.mattalEstado;
	}

	public void setMattalEstado(String mattalEstado) {
		this.mattalEstado = mattalEstado;
	}

}