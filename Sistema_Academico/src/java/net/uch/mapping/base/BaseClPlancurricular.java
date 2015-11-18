package net.uch.mapping.base;

import java.util.HashSet;
import java.util.Set;

import net.uch.mapping.ClArbolAcademico;

/**
 * AbstractClPlancurricular entity provides the base persistence definition of
 * the ClPlancurricular entity. @author MyEclipse Persistence Tools
 */

public abstract class BaseClPlancurricular implements java.io.Serializable {

	// Fields

	private Integer plaId;
	
	private String plaCodigo;
	private String plaDescripcion;
	private String plaVigente;
	private String plaActual;
	private String plaActivo;
	private Set clTallers = new HashSet(0);
        private ClArbolAcademico clArbolAcademico;

	// Constructors

	/** default constructor */
	public BaseClPlancurricular() {
	}

	/** minimal constructor */
	/*public BaseClPlancurricular(ClModulo clModulo) {
		this.clModulo = clModulo;
	}*/
        public BaseClPlancurricular(ClArbolAcademico clArbolAcademico) {
		this.clArbolAcademico = clArbolAcademico;

        }
	/** full constructor */
	public BaseClPlancurricular(String plaCodigo,
			String plaDescripcion, String plaVigente, String plaActual,
			String plaActivo, Set clTallers, ClArbolAcademico clArbolAcademico) {
		
		this.plaCodigo = plaCodigo;
		this.plaDescripcion = plaDescripcion;
		this.plaVigente = plaVigente;
		this.plaActual = plaActual;
		this.plaActivo = plaActivo;
		this.clTallers = clTallers;
                this.clArbolAcademico = clArbolAcademico;
	}

	// Property accessors

	public Integer getPlaId() {
		return this.plaId;
	}

	public void setPlaId(Integer plaId) {
		this.plaId = plaId;
	}

	

	public String getPlaCodigo() {
		return this.plaCodigo;
	}

	public void setPlaCodigo(String plaCodigo) {
		this.plaCodigo = plaCodigo;
	}

	public String getPlaDescripcion() {
		return this.plaDescripcion;
	}

	public void setPlaDescripcion(String plaDescripcion) {
		this.plaDescripcion = plaDescripcion;
	}

	public String getPlaVigente() {
		return this.plaVigente;
	}

	public void setPlaVigente(String plaVigente) {
		this.plaVigente = plaVigente;
	}

	public String getPlaActual() {
		return this.plaActual;
	}

	public void setPlaActual(String plaActual) {
		this.plaActual = plaActual;
	}

	public String getPlaActivo() {
		return this.plaActivo;
	}

	public void setPlaActivo(String plaActivo) {
		this.plaActivo = plaActivo;
	}

	public Set getClTallers() {
		return this.clTallers;
	}

	public void setClTallers(Set clTallers) {
		this.clTallers = clTallers;
	}

    public ClArbolAcademico getClArbolAcademico() {
        return clArbolAcademico;
    }

    public void setClArbolAcademico(ClArbolAcademico ClArbolAcademico) {
        this.clArbolAcademico = ClArbolAcademico;
    }
        

}