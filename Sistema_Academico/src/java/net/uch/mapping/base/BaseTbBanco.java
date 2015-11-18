package net.uch.mapping.base;

import java.io.Serializable;

/**
 * BaseTbBanco entity provides the base persistence definition of the
 * TbBanco entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public abstract class BaseTbBanco implements Serializable {

    // Fields
    private Integer banId;
    private String banCodigo;
    private String banNombre;
    private String banCuentaEmpresa;
    private String banSecServicio;
    private String banCantiRegistro;
    private String banRucEmpresa;
    private String banDiaMora;
    private String banTipoMora;
    private String banMoraFlat;
    private String banPorcenMora;
    private String banTipoDescuento;
    private String banPorcenDescuento;
    private String banDiasDescuento;
    private String banCuentaAbono;
    // Constructors

    /** default constructor */
    public BaseTbBanco() {
    }

    /** minimal constructor */
    public BaseTbBanco(Integer banId) {
        this.banId = banId;
    }

    /** full constructor */
    public BaseTbBanco(Integer banId, String banCodigo, String banNombre,
            String banCuentaEmpresa, String banSecServicio,
            String banCantiRegistro, String banRucEmpresa, String banDiaMora,
            String banTipoMora, String banMoraFlat, String banPorcenMora,
            String banTipoDescuento, String banPorcenDescuento,
            String banDiasDescuento, String banCuentaAbono) {
        this.banId = banId;
        this.banCodigo = banCodigo;
        this.banNombre = banNombre;
        this.banCuentaEmpresa = banCuentaEmpresa;
        this.banSecServicio = banSecServicio;
        this.banCantiRegistro = banCantiRegistro;
        this.banRucEmpresa = banRucEmpresa;
        this.banDiaMora = banDiaMora;
        this.banTipoMora = banTipoMora;
        this.banMoraFlat = banMoraFlat;
        this.banPorcenMora = banPorcenMora;
        this.banTipoDescuento = banTipoDescuento;
        this.banPorcenDescuento = banPorcenDescuento;
        this.banDiasDescuento = banDiasDescuento;
        this.banCuentaAbono = banCuentaAbono;
    }

    // Property accessors
    public Integer getBanId() {
        return this.banId;
    }

    public void setBanId(Integer banId) {
        this.banId = banId;
    }

    public String getBanCodigo() {
        return this.banCodigo;
    }

    public void setBanCodigo(String banCodigo) {
        this.banCodigo = banCodigo;
    }

    public String getBanNombre() {
        return this.banNombre;
    }

    public void setBanNombre(String banNombre) {
        this.banNombre = banNombre;
    }

    public String getBanCuentaEmpresa() {
        return this.banCuentaEmpresa;
    }

    public void setBanCuentaEmpresa(String banCuentaEmpresa) {
        this.banCuentaEmpresa = banCuentaEmpresa;
    }

    public String getBanSecServicio() {
        return this.banSecServicio;
    }

    public void setBanSecServicio(String banSecServicio) {
        this.banSecServicio = banSecServicio;
    }

    public String getBanCantiRegistro() {
        return this.banCantiRegistro;
    }

    public void setBanCantiRegistro(String banCantiRegistro) {
        this.banCantiRegistro = banCantiRegistro;
    }

    public String getBanRucEmpresa() {
        return this.banRucEmpresa;
    }

    public void setBanRucEmpresa(String banRucEmpresa) {
        this.banRucEmpresa = banRucEmpresa;
    }

    public String getBanDiaMora() {
        return this.banDiaMora;
    }

    public void setBanDiaMora(String banDiaMora) {
        this.banDiaMora = banDiaMora;
    }

    public String getBanTipoMora() {
        return this.banTipoMora;
    }

    public void setBanTipoMora(String banTipoMora) {
        this.banTipoMora = banTipoMora;
    }

    public String getBanMoraFlat() {
        return this.banMoraFlat;
    }

    public void setBanMoraFlat(String banMoraFlat) {
        this.banMoraFlat = banMoraFlat;
    }

    public String getBanPorcenMora() {
        return this.banPorcenMora;
    }

    public void setBanPorcenMora(String banPorcenMora) {
        this.banPorcenMora = banPorcenMora;
    }

    public String getBanTipoDescuento() {
        return this.banTipoDescuento;
    }

    public void setBanTipoDescuento(String banTipoDescuento) {
        this.banTipoDescuento = banTipoDescuento;
    }

    public String getBanPorcenDescuento() {
        return this.banPorcenDescuento;
    }

    public void setBanPorcenDescuento(String banPorcenDescuento) {
        this.banPorcenDescuento = banPorcenDescuento;
    }

    public String getBanDiasDescuento() {
        return this.banDiasDescuento;
    }

    public void setBanDiasDescuento(String banDiasDescuento) {
        this.banDiasDescuento = banDiasDescuento;
    }

    public String getBanCuentaAbono() {
        return banCuentaAbono;
    }

    public void setBanCuentaAbono(String banCuentaAbono) {
        this.banCuentaAbono = banCuentaAbono;
    }
}
