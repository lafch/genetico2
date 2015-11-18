package net.uch.mapping.base;

import java.io.Serializable;

/**
 * This is an object that contains data related to the tb_usuario table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="tb_usuario"
 */
public abstract class BaseTbUsuario implements Serializable {

    public static String REF = "TbUsuario";
    public static String PROP_USU_USUARIO = "UsuUsuario";
    public static String PROP_USU_ACTIVO = "UsuActivo";
    public static String PROP_ROL = "Rol";
    public static String PROP_USU_PASSWORD = "UsuPassword";
    public static String PROP_USU_VIGENTE = "UsuVigente";
    public static String PROP_USU_NIVEL = "UsuNivel";
    public static String PROP_USU_TIPO_ID = "UsuTipoId";
    public static String PROP_USU_TIPO = "UsuTipo";
    public static String PROP_USU_PSW = "UsuPsw";
    public static String PROP_ID = "Id";

    // constructors
    public BaseTbUsuario() {
        initialize();
    }

    /**
     * Constructor for primary key
     */
    public BaseTbUsuario(java.lang.Integer id) {
        this.setId(id);
        initialize();
    }

    protected void initialize() {
    }
    private int hashCode = Integer.MIN_VALUE;
    // primary key
    private java.lang.Integer id;
    // fields
    private java.lang.String usuUsuario;
    private java.lang.String usuPassword;
    private java.lang.String usuTipo;
    private java.lang.Integer usuTipoId;
    private java.lang.String usuVigente;
    private java.lang.String usuNivel;
    private java.lang.String usuPsw;
    private java.lang.String usuActivo;
    // many to one
    private net.uch.mapping.TbRoles rol;

    /**
     * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="sequence"
     *  column="usu_id"
     */
    public java.lang.Integer getId() {
        return id;
    }

    /**
     * Set the unique identifier of this class
     * @param id the new ID
     */
    public void setId(java.lang.Integer id) {
        this.id = id;
        this.hashCode = Integer.MIN_VALUE;
    }

    /**
     * Return the value associated with the column: usu_usuario
     */
    public java.lang.String getUsuUsuario() {
        return usuUsuario;
    }

    /**
     * Set the value related to the column: usu_usuario
     * @param usuUsuario the usu_usuario value
     */
    public void setUsuUsuario(java.lang.String usuUsuario) {
        this.usuUsuario = usuUsuario;
    }

    /**
     * Return the value associated with the column: usu_password
     */
    public java.lang.String getUsuPassword() {
        return usuPassword;
    }

    /**
     * Set the value related to the column: usu_password
     * @param usuPassword the usu_password value
     */
    public void setUsuPassword(java.lang.String usuPassword) {
        this.usuPassword = usuPassword;
    }

    /**
     * Return the value associated with the column: usu_tipo
     */
    public java.lang.String getUsuTipo() {
        return usuTipo;
    }

    /**
     * Set the value related to the column: usu_tipo
     * @param usuTipo the usu_tipo value
     */
    public void setUsuTipo(java.lang.String usuTipo) {
        this.usuTipo = usuTipo;
    }

    /**
     * Return the value associated with the column: usu_tipo_id
     */
    public java.lang.Integer getUsuTipoId() {
        return usuTipoId;
    }

    /**
     * Set the value related to the column: usu_tipo_id
     * @param usuTipoId the usu_tipo_id value
     */
    public void setUsuTipoId(java.lang.Integer usuTipoId) {
        this.usuTipoId = usuTipoId;
    }

    /**
     * Return the value associated with the column: usu_vigente
     */
    public java.lang.String getUsuVigente() {
        return usuVigente;
    }

    /**
     * Set the value related to the column: usu_vigente
     * @param usuVigente the usu_vigente value
     */
    public void setUsuVigente(java.lang.String usuVigente) {
        this.usuVigente = usuVigente;
    }

    /**
     * Return the value associated with the column: usu_vigente
     */
    public java.lang.String getUsuNivel() {
        return usuNivel;
    }

    /**
     * Set the value related to the column: usu_vigente
     * @param usuVigente the usu_vigente value
     */
    public void setUsuNivel(java.lang.String usuNivel) {
        this.usuNivel = usuNivel;
    }

    public String getUsuPsw() {
        return usuPsw;
    }

    public void setUsuPsw(String usuPsw) {
        this.usuPsw = usuPsw;
    }

    /**
     * Return the value associated with the column: usu_activo
     */
    public java.lang.String getUsuActivo() {
        return usuActivo;
    }

    /**
     * Set the value related to the column: usu_activo
     * @param usuActivo the usu_activo value
     */
    public void setUsuActivo(java.lang.String usuActivo) {
        this.usuActivo = usuActivo;
    }

    /**
     * Return the value associated with the column: rol_id
     */
    public net.uch.mapping.TbRoles getRol() {
        return rol;
    }

    /**
     * Set the value related to the column: rol_id
     * @param rol the rol_id value
     */
    public void setRol(net.uch.mapping.TbRoles rol) {
        this.rol = rol;
    }

    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (!(obj instanceof net.uch.mapping.TbUsuario)) {
            return false;
        } else {
            net.uch.mapping.TbUsuario tbUsuario = (net.uch.mapping.TbUsuario) obj;
            if (null == this.getId() || null == tbUsuario.getId()) {
                return false;
            } else {
                return (this.getId().equals(tbUsuario.getId()));
            }
        }
    }

    public int hashCode() {
        if (Integer.MIN_VALUE == this.hashCode) {
            if (null == this.getId()) {
                return super.hashCode();
            } else {
                String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
                this.hashCode = hashStr.hashCode();
            }
        }
        return this.hashCode;
    }

    public String toString() {
        return super.toString();
    }
}
