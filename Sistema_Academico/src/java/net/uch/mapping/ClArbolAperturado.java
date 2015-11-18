package net.uch.mapping;

// Generated by MyEclipse Persistence Tools

import java.util.Date;
import java.util.Set;
import net.uch.mapping.base.BaseClArbolAperturado;

/**
 * ClArbolAperturado generated by MyEclipse Persistence Tools
 */
public class ClArbolAperturado extends BaseClArbolAperturado implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ClArbolAperturado() {
	}
        
        public ClArbolAperturado(Integer arbapeId) {
            super(arbapeId);
	}

	/** minimal constructor */
	public ClArbolAperturado(Integer arbapeId, ClArbolAcademico clArbolAcademico) {
		super(arbapeId, clArbolAcademico);
	}

	/** full constructor */
	public ClArbolAperturado(Integer arbapeId, ClSisEvaluacion clSisEvaluacion,
			ClArbolAcademico clArbolAcademico, String arbapeDescripcion ,Integer arbapeNroHoras,
			String arbapeAperturado, String arbapeVigente, String arbapeActual,
			Date arbapeFecha, String arbapeActivo ,
                        Set clSisEvaPersonalizados, Set clSeccions) {
		super(arbapeId, clSisEvaluacion, clArbolAcademico, arbapeDescripcion,arbapeNroHoras,
				arbapeAperturado, arbapeVigente, arbapeActual, arbapeFecha,
				arbapeActivo, clSisEvaPersonalizados, clSeccions);
	}

}