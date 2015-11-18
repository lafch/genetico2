package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ac_vida_docente table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ac_vida_docente"
 */

public abstract class BaseAcVidaDocente  implements Serializable {

	public static String REF = "AcVidaDocente";
	public static String PROP_DOC = "Doc";
	public static String PROP_VID_ACTIVO = "VidActivo";
	public static String PROP_VID_FECHA = "VidFecha";
	public static String PROP_ID = "Id";
	public static String PROP_VID_DESCRIPCION = "VidDescripcion";


	// constructors
	public BaseAcVidaDocente () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseAcVidaDocente (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseAcVidaDocente (
		java.lang.Integer id,
		net.uch.mapping.AcDocente doc) {

		this.setId(id);
		this.setDoc(doc);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String vidDescripcion;
	private java.util.Date vidFecha;
	private java.lang.String vidActivo;

	// many to one
	private net.uch.mapping.AcDocente doc;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="vid_id"
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
	 * Return the value associated with the column: vid_descripcion
	 */
	public java.lang.String getVidDescripcion () {
		return vidDescripcion;
	}

	/**
	 * Set the value related to the column: vid_descripcion
	 * @param vidDescripcion the vid_descripcion value
	 */
	public void setVidDescripcion (java.lang.String vidDescripcion) {
		this.vidDescripcion = vidDescripcion;
	}



	/**
	 * Return the value associated with the column: vid_fecha
	 */
	public java.util.Date getVidFecha () {
		return vidFecha;
	}

	/**
	 * Set the value related to the column: vid_fecha
	 * @param vidFecha the vid_fecha value
	 */
	public void setVidFecha (java.util.Date vidFecha) {
		this.vidFecha = vidFecha;
	}



	/**
	 * Return the value associated with the column: vid_activo
	 */
	public java.lang.String getVidActivo () {
		return vidActivo;
	}

	/**
	 * Set the value related to the column: vid_activo
	 * @param vidActivo the vid_activo value
	 */
	public void setVidActivo (java.lang.String vidActivo) {
		this.vidActivo = vidActivo;
	}



	/**
	 * Return the value associated with the column: doc_id
	 */
	public net.uch.mapping.AcDocente getDoc () {
		return doc;
	}

	/**
	 * Set the value related to the column: doc_id
	 * @param doc the doc_id value
	 */
	public void setDoc (net.uch.mapping.AcDocente doc) {
		this.doc = doc;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.AcVidaDocente)) return false;
		else {
			net.uch.mapping.AcVidaDocente acVidaDocente = (net.uch.mapping.AcVidaDocente) obj;
			if (null == this.getId() || null == acVidaDocente.getId()) return false;
			else return (this.getId().equals(acVidaDocente.getId()));
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