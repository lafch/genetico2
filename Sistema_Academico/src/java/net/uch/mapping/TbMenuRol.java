package net.uch.mapping;

import net.uch.mapping.base.BaseTbMenuRol;

// Generated by MyEclipse Persistence Tools

/**
 * TbMenuRol generated by MyEclipse Persistence Tools
 */
public class TbMenuRol extends BaseTbMenuRol implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public TbMenuRol() {
	}

	/** minimal constructor */
	public TbMenuRol(Integer menrolId, TbMenu tbMenu, TbRoles tbRoles) {
		super(menrolId, tbMenu, tbRoles);
	}

	/** full constructor */
	public TbMenuRol(Integer menrolId, TbMenu tbMenu, TbRoles tbRoles,
			String menrolActivo) {
		super(menrolId, tbMenu, tbRoles, menrolActivo);
	}

}