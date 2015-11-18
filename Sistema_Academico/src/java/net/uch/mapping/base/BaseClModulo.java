package net.uch.mapping.base;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import net.uch.mapping.ClArea;

/**
 * AbstractClModulo entity provides the base persistence definition of the
 * ClModulo entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseClModulo implements java.io.Serializable {

	// Fields

	private Integer modId;
	private ClArea clArea;
	private String modCodigo;
	private String modDescripcion;
	private Date modCreacion;
	private String modActivo;
	private Set clEstructuraPagoses = new HashSet(0);
	private Set clCursoses = new HashSet(0);
	private Set clPlancurriculars = new HashSet(0);

	// Constructors

	/** default constructor */
	public BaseClModulo() {
	}

	/** minimal constructor */
	public BaseClModulo(ClArea clArea) {
		this.clArea = clArea;
	}

	/** full constructor */
	public BaseClModulo(ClArea clArea, String modCodigo,
			String modDescripcion, Date modCreacion, String modActivo,
			Set clEstructuraPagoses, Set clCursoses, Set clPlancurriculars) {
		this.clArea = clArea;
		this.modCodigo = modCodigo;
		this.modDescripcion = modDescripcion;
		this.modCreacion = modCreacion;
		this.modActivo = modActivo;
		this.clEstructuraPagoses = clEstructuraPagoses;
		this.clCursoses = clCursoses;
		this.clPlancurriculars = clPlancurriculars;
	}

	// Property accessors

	public Integer getModId() {
		return this.modId;
	}

	public void setModId(Integer modId) {
		this.modId = modId;
	}

	public ClArea getClArea() {
		return this.clArea;
	}

	public void setClArea(ClArea clArea) {
		this.clArea = clArea;
	}

	public String getModCodigo() {
		return this.modCodigo;
	}

	public void setModCodigo(String modCodigo) {
		this.modCodigo = modCodigo;
	}

	public String getModDescripcion() {
		return this.modDescripcion;
	}

	public void setModDescripcion(String modDescripcion) {
		this.modDescripcion = modDescripcion;
	}

	public Date getModCreacion() {
		return this.modCreacion;
	}

	public void setModCreacion(Date modCreacion) {
		this.modCreacion = modCreacion;
	}

	public String getModActivo() {
		return this.modActivo;
	}

	public void setModActivo(String modActivo) {
		this.modActivo = modActivo;
	}

	public Set getClEstructuraPagoses() {
		return this.clEstructuraPagoses;
	}

	public void setClEstructuraPagoses(Set clEstructuraPagoses) {
		this.clEstructuraPagoses = clEstructuraPagoses;
	}

	public Set getClCursoses() {
		return this.clCursoses;
	}

	public void setClCursoses(Set clCursoses) {
		this.clCursoses = clCursoses;
	}

	public Set getClPlancurriculars() {
		return this.clPlancurriculars;
	}

	public void setClPlancurriculars(Set clPlancurriculars) {
		this.clPlancurriculars = clPlancurriculars;
	}

}