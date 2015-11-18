/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.uch.mapping.base;

/**
 *
 * @author chano
 */
import java.io.Serializable;

public abstract class BaseTbBloqueWeb implements Serializable {

    private java.lang.Integer bloweb_id;
    private java.lang.Integer sec_id;
    private java.lang.String bloweb_estado;
    private java.lang.String bloweb_activo;

    public BaseTbBloqueWeb() {
        super();
    }

    public String getBloweb_activo() {
        return bloweb_activo;
    }

    public void setBloweb_activo(String bloweb_activo) {
        this.bloweb_activo = bloweb_activo;
    }

    public String getBloweb_estado() {
        return bloweb_estado;
    }

    public void setBloweb_estado(String bloweb_estado) {
        this.bloweb_estado = bloweb_estado;
    }

    public Integer getBloweb_id() {
        return bloweb_id;
    }

    public void setBloweb_id(Integer bloweb_id) {
        this.bloweb_id = bloweb_id;
    }

    public Integer getSec_id() {
        return sec_id;
    }

    public void setSec_id(Integer sec_id) {
        this.sec_id = sec_id;
    }
    
    
    
    
}
