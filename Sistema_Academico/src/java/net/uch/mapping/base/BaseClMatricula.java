package net.uch.mapping.base;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import net.uch.mapping.ClAlumno;

public abstract class BaseClMatricula implements java.io.Serializable {

	// Fields

	private Integer matId;
	private ClAlumno clAlumno;
	private String matCodigo;
	private Timestamp matFecha;
	private String matTipo;
	private String matActivo;
	private Integer usuId;
	private Set clMatriculaTallers = new HashSet(0);

	// Constructors

	/** default constructor */
	public BaseClMatricula() {
	}

	/** minimal constructor */
	public BaseClMatricula(ClAlumno clAlumno) {
		this.clAlumno = clAlumno;
	}

	/** full constructor */
	public BaseClMatricula(ClAlumno clAlumno, String matCodigo,
			Timestamp matFecha, String matTipo, String matActivo,
			Integer usuId, Set clMatriculaTallers) {
		this.clAlumno = clAlumno;
		this.matCodigo = matCodigo;
		this.matFecha = matFecha;
		this.matTipo = matTipo;
		this.matActivo = matActivo;
		this.usuId = usuId;
		this.clMatriculaTallers = clMatriculaTallers;
	}

	// Property accessors

	public Integer getMatId() {
		return this.matId;
	}

	public void setMatId(Integer matId) {
		this.matId = matId;
	}

	public ClAlumno getClAlumno() {
		return this.clAlumno;
	}

	public void setClAlumno(ClAlumno clAlumno) {
		this.clAlumno = clAlumno;
	}

	public String getMatCodigo() {
		return this.matCodigo;
	}

	public void setMatCodigo(String matCodigo) {
		this.matCodigo = matCodigo;
	}

	public Timestamp getMatFecha() {
		return this.matFecha;
	}

	public void setMatFecha(Timestamp matFecha) {
		this.matFecha = matFecha;
	}

	public String getMatTipo() {
		return this.matTipo;
	}

	public void setMatTipo(String matTipo) {
		this.matTipo = matTipo;
	}

	public String getMatActivo() {
		return this.matActivo;
	}

	public void setMatActivo(String matActivo) {
		this.matActivo = matActivo;
	}

	public Integer getUsuId() {
		return this.usuId;
	}

	public void setUsuId(Integer usuId) {
		this.usuId = usuId;
	}

	public Set getClMatriculaTallers() {
		return this.clMatriculaTallers;
	}

	public void setClMatriculaTallers(Set clMatriculaTallers) {
		this.clMatriculaTallers = clMatriculaTallers;
	}

}