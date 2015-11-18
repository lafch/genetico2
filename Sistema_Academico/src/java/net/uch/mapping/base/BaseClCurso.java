package net.uch.mapping.base;

import java.util.HashSet;
import java.util.Set;
import net.uch.mapping.ClModulo;

/**
 * AbstractClCurso generated by MyEclipse Persistence Tools
 */

public abstract class BaseClCurso implements java.io.Serializable {

	// Fields

	private Integer curId;
	private ClModulo clModulo;
	private String curCodigo;
	private String curNombre;
	private String curActivo;
	private Set clTallerAperturados = new HashSet(0);

	// Constructors

	/** default constructor */
	public BaseClCurso() {
	}

	/** minimal constructor */
	public BaseClCurso(Integer curId) {
		this.curId = curId;
	}

	/** full constructor */
	public BaseClCurso(Integer curId, ClModulo clModulo, String curCodigo,
			String curNombre, String curActivo, Set clTallerAperturados) {
		this.curId = curId;
		this.clModulo = clModulo;
		this.curCodigo = curCodigo;
		this.curNombre = curNombre;
		this.curActivo = curActivo;
		this.clTallerAperturados = clTallerAperturados;
	}

	// Property accessors

	public Integer getCurId() {
		return this.curId;
	}

	public void setCurId(Integer curId) {
		this.curId = curId;
	}

	public ClModulo getClModulo() {
		return this.clModulo;
	}

	public void setClModulo(ClModulo clModulo) {
		this.clModulo = clModulo;
	}

	public String getCurCodigo() {
		return this.curCodigo;
	}

	public void setCurCodigo(String curCodigo) {
		this.curCodigo = curCodigo;
	}

	public String getCurNombre() {
		return this.curNombre;
	}

	public void setCurNombre(String curNombre) {
		this.curNombre = curNombre;
	}

	public String getCurActivo() {
		return this.curActivo;
	}

	public void setCurActivo(String curActivo) {
		this.curActivo = curActivo;
	}

	public Set getClTallerAperturados() {
		return this.clTallerAperturados;
	}

	public void setClTallerAperturados(Set clTallerAperturados) {
		this.clTallerAperturados = clTallerAperturados;
	}

}