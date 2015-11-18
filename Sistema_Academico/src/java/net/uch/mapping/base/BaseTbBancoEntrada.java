package net.uch.mapping.base;

import java.sql.Timestamp;
import java.util.Date;

/**
 * AbstractTbBancoEntrada entity provides the base persistence definition of the
 * TbBancoEntrada entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public abstract class BaseTbBancoEntrada implements java.io.Serializable {

    // Fields
    private Integer banentId;
    private Float banentMonto1;
    private Float banentMonto2;
    private Float banentMonto3;
    private Float banentMonto4;
    private Float banentMonto5;
    private Float banentMora;
    private Date banentFechaMovimiento;
    private Date banentFechaPago;
    private Timestamp banentFechaCreacion;
    private String banentNroOperacion;
    private String banentProcesado;
    private String banentActivo;
    private Integer banentNroRecibo;

    // Constructors
    /** default constructor */
    public BaseTbBancoEntrada() {
    }

    /** minimal constructor */
    public BaseTbBancoEntrada(Integer banentId, Integer banentNroRecibo) {
        this.banentId = banentId;
        this.banentNroRecibo = banentNroRecibo;
    }

    /** full constructor */
    public BaseTbBancoEntrada(Integer banentId, Float banentMonto1, Float banentMonto2,
            Float banentMonto3, Float banentMonto4, Float banentMonto5,
            Float banentMora, Date banentFechaMovimiento, Date banentFechaPago,
            Timestamp banentFechaCreacion,
            String banentNroOperacion, String banentProcesado,
            String banentActivo, Integer banentNroRecibo) {
        this.banentId = banentId;
        this.banentMonto1 = banentMonto1;
        this.banentMonto2 = banentMonto2;
        this.banentMonto3 = banentMonto3;
        this.banentMonto4 = banentMonto4;
        this.banentMonto5 = banentMonto5;
        this.banentMora = banentMora;
        this.banentFechaMovimiento = banentFechaMovimiento;
        this.banentFechaPago = banentFechaPago;
        this.banentFechaCreacion = banentFechaCreacion;
        this.banentNroOperacion = banentNroOperacion;
        this.banentProcesado = banentProcesado;
        this.banentActivo = banentActivo;
        this.banentNroRecibo = banentNroRecibo;
    }

    // Property accessors
    public Integer getBanentId() {
        return this.banentId;
    }

    public void setBanentId(Integer banentId) {
        this.banentId = banentId;
    }

    public Float getBanentMonto1() {
        return banentMonto1;
    }

    public void setBanentMonto1(Float banentMonto1) {
        this.banentMonto1 = banentMonto1;
    }

    public Float getBanentMonto2() {
        return banentMonto2;
    }

    public void setBanentMonto2(Float banentMonto2) {
        this.banentMonto2 = banentMonto2;
    }

    public Float getBanentMonto3() {
        return banentMonto3;
    }

    public void setBanentMonto3(Float banentMonto3) {
        this.banentMonto3 = banentMonto3;
    }

    public Float getBanentMonto4() {
        return banentMonto4;
    }

    public void setBanentMonto4(Float banentMonto4) {
        this.banentMonto4 = banentMonto4;
    }

    public Float getBanentMonto5() {
        return banentMonto5;
    }

    public void setBanentMonto5(Float banentMonto5) {
        this.banentMonto5 = banentMonto5;
    }

    public Float getBanentMora() {
        return this.banentMora;
    }

    public void setBanentMora(Float banentMora) {
        this.banentMora = banentMora;
    }

    public Date getBanentFechaMovimiento() {
        return this.banentFechaMovimiento;
    }

    public void setBanentFechaMovimiento(Date banentFechaMovimiento) {
        this.banentFechaMovimiento = banentFechaMovimiento;
    }

    public Date getBanentFechaPago() {
        return this.banentFechaPago;
    }

    public void setBanentFechaPago(Date banentFechaPago) {
        this.banentFechaPago = banentFechaPago;
    }

    public Timestamp getBanentFechaCreacion() {
        return banentFechaCreacion;
    }

    public void setBanentFechaCreacion(Timestamp banentFechaCreacion) {
        this.banentFechaCreacion = banentFechaCreacion;
    }

    public String getBanentNroOperacion() {
        return banentNroOperacion;
    }

    public void setBanentNroOperacion(String banentNroOperacion) {
        this.banentNroOperacion = banentNroOperacion;
    }

    public String getBanentProcesado() {
        return this.banentProcesado;
    }

    public void setBanentProcesado(String banentProcesado) {
        this.banentProcesado = banentProcesado;
    }

    public String getBanentActivo() {
        return this.banentActivo;
    }

    public void setBanentActivo(String banentActivo) {
        this.banentActivo = banentActivo;
    }

    public Integer getBanentNroRecibo() {
        return banentNroRecibo;
    }

    public void setBanentNroRecibo(Integer banentNroRecibo) {
        this.banentNroRecibo = banentNroRecibo;
    }
}
