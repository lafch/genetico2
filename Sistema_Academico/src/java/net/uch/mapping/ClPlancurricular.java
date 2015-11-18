package net.uch.mapping;

import java.util.Set;

import net.uch.mapping.base.BaseClPlancurricular;

/**
 * ClPlancurricular entity. @author MyEclipse Persistence Tools
 */
public class ClPlancurricular extends BaseClPlancurricular implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public ClPlancurricular() {
	}

	/** minimal constructor */
	/*public ClPlancurricular(ClModulo clModulo) {
		super(clModulo);
	}*/
        public ClPlancurricular(ClArbolAcademico clArbolAcademico) {
		super(clArbolAcademico);
	}

	/** full constructor */
	public ClPlancurricular( String plaCodigo,
			String plaDescripcion, String plaVigente, String plaActual,
			String plaActivo, Set clTallers, ClArbolAcademico clArbolAcademico) {
		super( plaCodigo, plaDescripcion, plaVigente, plaActual,
				plaActivo, clTallers, clArbolAcademico);
	}

}
