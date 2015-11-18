/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.uch.util;

/**
 *
 * @author cesardl
 */
public class ObjNodeCLGnral {

    private String id;
    private int talape_id;
    private String talape_nombre;
    private int cur_id;
    private String cur_nombre;
    private int mod_id;
    private String mod_nombre;

    public ObjNodeCLGnral(String id,
            int talape_id, String talape_nombre,
            int cur_id, String cur_nombre,
            int mod_id, String mod_nombre) {
        this.id = id;
        this.talape_id = talape_id;
        this.talape_nombre = talape_nombre;
        this.cur_id = cur_id;
        this.cur_nombre = cur_nombre;
        this.mod_id = mod_id;
        this.mod_nombre = mod_nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMod_id() {
        return mod_id;
    }

    public void setMod_id(int mod_id) {
        this.mod_id = mod_id;
    }

    public String getMod_nombre() {
        return mod_nombre;
    }

    public void setMod_nombre(String mod_nombre) {
        this.mod_nombre = mod_nombre;
    }

    public int getCur_id() {
        return cur_id;
    }

    public void setCur_id(int cur_id) {
        this.cur_id = cur_id;
    }

    public String getCur_nombre() {
        return cur_nombre;
    }

    public void setCur_nombre(String cur_nombre) {
        this.cur_nombre = cur_nombre;
    }

    public int getTalape_id() {
        return talape_id;
    }

    public void setTalape_id(int talape_id) {
        this.talape_id = talape_id;
    }

    public String getTalape_nombre() {
        return talape_nombre;
    }

    public void setTalape_nombre(String talape_nombre) {
        this.talape_nombre = talape_nombre;
    }
}
