package net.uch.mapping.base;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import net.uch.mapping.ClArbolAcademico;
import net.uch.mapping.ClSisEvaluacion;

/**
 * BaseClArbolAperturado generated by MyEclipse Persistence Tools
 */

public abstract class BaseClArbolAperturado implements java.io.Serializable {

	// Fields

	private Integer arbapeId;
	private ClSisEvaluacion clSisEvaluacion;
	private ClArbolAcademico clArbolAcademico;
        private String arbapeDescripcion;
	private Integer arbapeNroHoras;
	private String arbapeAperturado;
	private String arbapeVigente;
	private String arbapeActual;
	private Date arbapeFecha;
	private String arbapeActivo;
        private Set clSisEvaPersonalizados = new HashSet(0);
	private Set clSeccions = new HashSet(0);

    public String getArbapeDescripcion() {
        return arbapeDescripcion;
    }

    public void setArbapeDescripcion(String arbapeDescripcion) {
        this.arbapeDescripcion = arbapeDescripcion;
    }
        
        

    public Set getClSeccions() {
        return clSeccions;
    }

    public void setClSeccions(Set clSeccions) {
        this.clSeccions = clSeccions;
    }

    public Set getClSisEvaPersonalizados() {
        return clSisEvaPersonalizados;
    }

    public void setClSisEvaPersonalizados(Set clSisEvaPersonalizados) {
        this.clSisEvaPersonalizados = clSisEvaPersonalizados;
    }

	// Constructors

	/** default constructor */
	public BaseClArbolAperturado() {
	}
        
        public BaseClArbolAperturado(Integer arbapeId) {
            this.arbapeId = arbapeId;
	}

	/** minimal constructor */
	public BaseClArbolAperturado(Integer arbapeId,
			ClArbolAcademico clArbolAcademico) {
		this.arbapeId = arbapeId;
		this.clArbolAcademico = clArbolAcademico;
	}

	/** full constructor */
	public BaseClArbolAperturado(Integer arbapeId,
			ClSisEvaluacion clSisEvaluacion, ClArbolAcademico clArbolAcademico,
			String arbapeDescripcion, Integer arbapeNroHoras, String arbapeAperturado,
			String arbapeVigente, String arbapeActual, Date arbapeFecha,
			String arbapeActivo, Set clSisEvaPersonalizados, Set clSeccions) {
		this.arbapeId = arbapeId;
		this.clSisEvaluacion = clSisEvaluacion;
		this.clArbolAcademico = clArbolAcademico;
                this.arbapeDescripcion = arbapeDescripcion;
		this.arbapeNroHoras = arbapeNroHoras;
		this.arbapeAperturado = arbapeAperturado;
		this.arbapeVigente = arbapeVigente;
		this.arbapeActual = arbapeActual;
		this.arbapeFecha = arbapeFecha;
		this.arbapeActivo = arbapeActivo;
                this.clSisEvaPersonalizados = clSisEvaPersonalizados;
		this.clSeccions = clSeccions;
	}

	// Property accessors

	public Integer getArbapeId() {
		return this.arbapeId;
	}

	public void setArbapeId(Integer arbapeId) {
		this.arbapeId = arbapeId;
	}

	public ClSisEvaluacion getClSisEvaluacion() {
		return this.clSisEvaluacion;
	}

	public void setClSisEvaluacion(ClSisEvaluacion clSisEvaluacion) {
		this.clSisEvaluacion = clSisEvaluacion;
	}

	public ClArbolAcademico getClArbolAcademico() {
		return this.clArbolAcademico;
	}

	public void setClArbolAcademico(ClArbolAcademico clArbolAcademico) {
		this.clArbolAcademico = clArbolAcademico;
	}

	public Integer getArbapeNroHoras() {
		return this.arbapeNroHoras;
	}

	public void setArbapeNroHoras(Integer arbapeNroHoras) {
		this.arbapeNroHoras = arbapeNroHoras;
	}

	public String getArbapeAperturado() {
		return this.arbapeAperturado;
	}

	public void setArbapeAperturado(String arbapeAperturado) {
		this.arbapeAperturado = arbapeAperturado;
	}

	public String getArbapeVigente() {
		return this.arbapeVigente;
	}

	public void setArbapeVigente(String arbapeVigente) {
		this.arbapeVigente = arbapeVigente;
	}

	public String getArbapeActual() {
		return this.arbapeActual;
	}

	public void setArbapeActual(String arbapeActual) {
		this.arbapeActual = arbapeActual;
	}

	public Date getArbapeFecha() {
		return this.arbapeFecha;
	}

	public void setArbapeFecha(Date arbapeFecha) {
		this.arbapeFecha = arbapeFecha;
	}

	public String getArbapeActivo() {
		return this.arbapeActivo;
	}

	public void setArbapeActivo(String arbapeActivo) {
		this.arbapeActivo = arbapeActivo;
	}

}