package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the tb_asistencia table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="tb_asistencia"
 */

public abstract class BaseTbAsistencia  implements Serializable {

	public static String REF = "TbAsistencia";
	public static String PROP_HORA_SALIDA = "HoraSalida";
	public static String PROP_ASIS_CODIGO = "AsisCodigo";
	public static String PROP_HORA_ENTRADA = "HoraEntrada";
	public static String PROP_ASIS_DIA = "AsisDia";
	public static String PROP_ID = "Id";


	// constructors
	public BaseTbAsistencia () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTbAsistencia (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseTbAsistencia (
		java.lang.Integer id,
		java.lang.String asisCodigo,
		java.util.Date asisDia,
		java.util.Date horaEntrada) {

		this.setId(id);
		this.setAsisCodigo(asisCodigo);
		this.setAsisDia(asisDia);
		this.setHoraEntrada(horaEntrada);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String asisCodigo;
	private java.util.Date asisDia;
	private java.util.Date horaEntrada;
	private java.util.Date horaSalida;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="asis_id"
     */
	public java.lang.Integer getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: asis_codigo
	 */
	public java.lang.String getAsisCodigo () {
		return asisCodigo;
	}

	/**
	 * Set the value related to the column: asis_codigo
	 * @param asisCodigo the asis_codigo value
	 */
	public void setAsisCodigo (java.lang.String asisCodigo) {
		this.asisCodigo = asisCodigo;
	}



	/**
	 * Return the value associated with the column: asis_dia
	 */
	public java.util.Date getAsisDia () {
		return asisDia;
	}

	/**
	 * Set the value related to the column: asis_dia
	 * @param asisDia the asis_dia value
	 */
	public void setAsisDia (java.util.Date asisDia) {
		this.asisDia = asisDia;
	}



	/**
	 * Return the value associated with the column: hora_entrada
	 */
	public java.util.Date getHoraEntrada () {
		return horaEntrada;
	}

	/**
	 * Set the value related to the column: hora_entrada
	 * @param horaEntrada the hora_entrada value
	 */
	public void setHoraEntrada (java.util.Date horaEntrada) {
		this.horaEntrada = horaEntrada;
	}



	/**
	 * Return the value associated with the column: hora_salida
	 */
	public java.util.Date getHoraSalida () {
		return horaSalida;
	}

	/**
	 * Set the value related to the column: hora_salida
	 * @param horaSalida the hora_salida value
	 */
	public void setHoraSalida (java.util.Date horaSalida) {
		this.horaSalida = horaSalida;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.TbAsistencia)) return false;
		else {
			net.uch.mapping.TbAsistencia tbAsistencia = (net.uch.mapping.TbAsistencia) obj;
			if (null == this.getId() || null == tbAsistencia.getId()) return false;
			else return (this.getId().equals(tbAsistencia.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}