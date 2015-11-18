package net.uch.mapping;

import java.sql.Timestamp;
import java.util.Set;

import net.uch.mapping.base.BaseClMatricula;

/**
 * ClMatricula entity. @author MyEclipse Persistence Tools
 */
public class ClMatricula extends BaseClMatricula implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ClMatricula() {
	}

	/** minimal constructor */
	public ClMatricula(ClAlumno clAlumno) {
		super(clAlumno);
	}

	/** full constructor */
	public ClMatricula(ClAlumno clAlumno, String matCodigo, Timestamp matFecha,
			String matTipo, String matActivo, Integer usuId,
			Set clMatriculaTallers) {
		super(clAlumno, matCodigo, matFecha, matTipo, matActivo, usuId,
				clMatriculaTallers);
	}

}
