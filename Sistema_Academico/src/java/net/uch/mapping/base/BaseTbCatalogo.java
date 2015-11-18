package net.uch.mapping.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the tb_catalogo table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="tb_catalogo"
 */

public abstract class BaseTbCatalogo  implements Serializable {

	public static String REF = "TbCatalogo";
	public static String PROP_CAT_ACTIVO = "CatActivo";
	public static String PROP_CAT_CODIGO_ELEMENTO = "CatCodigoElemento";
	public static String PROP_CAT_DESCRIPCION_ELEMENTO = "CatDescripcionElemento";
	public static String PROP_CAT_CODIGO_GRUPO = "CatCodigoGrupo";
	public static String PROP_ID = "Id";
	public static String PROP_CAT_VALOR = "CatValor";


	// constructors
	public BaseTbCatalogo () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTbCatalogo (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String catCodigoGrupo;
	private java.lang.String catCodigoElemento;
	private java.lang.String catDescripcionElemento;
	private java.lang.String catValor;
	private java.lang.String catActivo;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="cat_id"
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
	 * Return the value associated with the column: cat_codigo_grupo
	 */
	public java.lang.String getCatCodigoGrupo () {
		return catCodigoGrupo;
	}

	/**
	 * Set the value related to the column: cat_codigo_grupo
	 * @param catCodigoGrupo the cat_codigo_grupo value
	 */
	public void setCatCodigoGrupo (java.lang.String catCodigoGrupo) {
		this.catCodigoGrupo = catCodigoGrupo;
	}



	/**
	 * Return the value associated with the column: cat_codigo_elemento
	 */
	public java.lang.String getCatCodigoElemento () {
		return catCodigoElemento;
	}

	/**
	 * Set the value related to the column: cat_codigo_elemento
	 * @param catCodigoElemento the cat_codigo_elemento value
	 */
	public void setCatCodigoElemento (java.lang.String catCodigoElemento) {
		this.catCodigoElemento = catCodigoElemento;
	}



	/**
	 * Return the value associated with the column: cat_descripcion_elemento
	 */
	public java.lang.String getCatDescripcionElemento () {
		return catDescripcionElemento;
	}

	/**
	 * Set the value related to the column: cat_descripcion_elemento
	 * @param catDescripcionElemento the cat_descripcion_elemento value
	 */
	public void setCatDescripcionElemento (java.lang.String catDescripcionElemento) {
		this.catDescripcionElemento = catDescripcionElemento;
	}



	/**
	 * Return the value associated with the column: cat_valor
	 */
	public java.lang.String getCatValor () {
		return catValor;
	}

	/**
	 * Set the value related to the column: cat_valor
	 * @param catValor the cat_valor value
	 */
	public void setCatValor (java.lang.String catValor) {
		this.catValor = catValor;
	}



	/**
	 * Return the value associated with the column: cat_activo
	 */
	public java.lang.String getCatActivo () {
		return catActivo;
	}

	/**
	 * Set the value related to the column: cat_activo
	 * @param catActivo the cat_activo value
	 */
	public void setCatActivo (java.lang.String catActivo) {
		this.catActivo = catActivo;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof net.uch.mapping.TbCatalogo)) return false;
		else {
			net.uch.mapping.TbCatalogo tbCatalogo = (net.uch.mapping.TbCatalogo) obj;
			if (null == this.getId() || null == tbCatalogo.getId()) return false;
			else return (this.getId().equals(tbCatalogo.getId()));
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