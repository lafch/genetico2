package net.uch.banco.managedBeans.beans;

/**
 * Clase que hace referencia a los detalles de pago
 * Indetificador <b>D</b>
 * @author cesardl
 */
public class Detalle {

    private String tipo_registro;
    private String cuenta_empresa;
    private String secuencia;
    private String codigo_usuario;
    private String numero_recibo;
    private String nombre_usuario;
    private String moneda_cobro;
    private String importe1;
    private String importe2;
    private String importe3;
    private String importe4;
    private String importe5;
    private String importe6_mora;
    private String fecha_vencimiento;
    private String fecha_pago;
    private String tipo_pago;
    private String medio_pago;
    private String numero_operacion;
    private String referencia_cobro;

    public Detalle(String cadena) {
        this.tipo_registro = cadena.substring(0, 1);
        this.cuenta_empresa = cadena.substring(1, 15);
        this.secuencia = cadena.substring(15, 18);
        this.codigo_usuario = cadena.substring(18, 33);
        this.numero_recibo = cadena.substring(33, 48);
        this.nombre_usuario = cadena.substring(48, 68);
        this.moneda_cobro = cadena.substring(68, 72);
        this.importe1 = cadena.substring(72, 81);
        this.importe2 = cadena.substring(81, 90);
        this.importe3 = cadena.substring(90, 99);
        this.importe4 = cadena.substring(99, 108);
        this.importe5 = cadena.substring(108, 117);
        this.importe6_mora = cadena.substring(117, 126);
        this.fecha_vencimiento = cadena.substring(126, 134);
        this.fecha_pago = cadena.substring(134, 142);
        this.tipo_pago = cadena.substring(142, 143);
        this.medio_pago = cadena.substring(143, 144);
        this.numero_operacion = cadena.substring(144, 157);
        this.referencia_cobro = cadena.substring(157, 177);
    }

    public String getTipo_registro() {
        return tipo_registro;
    }

    public void setTipo_registro(String tipoRegistro) {
        this.tipo_registro = tipoRegistro;
    }

    public String getCuenta_empresa() {
        return cuenta_empresa;
    }

    public void setCuenta_empresa(String cuentaEmpresa) {
        this.cuenta_empresa = cuentaEmpresa;
    }

    public String getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    public String getCodigo_usuario() {
        return codigo_usuario;
    }

    public void setCodigo_usuario(String codigoUsuario) {
        this.codigo_usuario = codigoUsuario;
    }

    /**
     * Devuelve el numero de recibo.
     * @return el <i>alutar_id</i>
     */
    public String getNumero_recibo() {
        return numero_recibo;
    }

    /**
     * Setea el numero de recibo
     * @param numeroRecibo, que es el <i>alutar_id</i>
     */
    public void setNumero_recibo(String numeroRecibo) {
        this.numero_recibo = numeroRecibo;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombreUsuario) {
        this.nombre_usuario = nombreUsuario;
    }

    public String getMoneda_cobro() {
        return moneda_cobro;
    }

    public void setMoneda_cobro(String monedaCobro) {
        this.moneda_cobro = monedaCobro;
    }

    public String getImporte1() {
        return importe1;
    }

    public void setImporte1(String importe1) {
        this.importe1 = importe1;
    }

    public String getImporte2() {
        return importe2;
    }

    public void setImporte2(String importe2) {
        this.importe2 = importe2;
    }

    public String getImporte3() {
        return importe3;
    }

    public void setImporte3(String importe3) {
        this.importe3 = importe3;
    }

    public String getImporte4() {
        return importe4;
    }

    public void setImporte4(String importe4) {
        this.importe4 = importe4;
    }

    public String getImporte5() {
        return importe5;
    }

    public void setImporte5(String importe5) {
        this.importe5 = importe5;
    }

    public String getImporte6_mora() {
        return importe6_mora;
    }

    public void setImporte6_mora(String importe6Mora) {
        this.importe6_mora = importe6Mora;
    }

    public String getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(String fechaVencimiento) {
        this.fecha_vencimiento = fechaVencimiento;
    }

    public String getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(String fechaPago) {
        this.fecha_pago = fechaPago;
    }

    /**
     * Devuelve el tipo de pago
     * <ul type='circle'>
     * <li><b>1</b> = Efectivo</li>
     * <li><b>2</b> = Cheque mismo banco</li>
     * <li><b>3</b> = Tarjeta de credito</li>
     * <li><b>4</b> = Cheque otro banco</li>
     * </ul>
     * @return el tipo de <i>tb_catalogo</i>
     */
    public String getTipo_pago() {
        return tipo_pago;
    }

    /**
     * Setea el tipo de pago
     * <ul type='circle'>
     * <li><b>1</b> = Efectivo</li>
     * <li><b>2</b> = Cheque mismo banco</li>
     * <li><b>3</b> = Tarjeta de credito</li>
     * <li><b>4</b> = Cheque otro banco</li>
     * </ul>
     * @param tipoPago
     */
    public void setTipo_pago(String tipoPago) {
        this.tipo_pago = tipoPago;
    }

    public String getMedio_pago() {
        return medio_pago;
    }

    public void setMedio_pago(String medioPago) {
        this.medio_pago = medioPago;
    }

    /**
     * Devuelve el numero de operacion otorgado por el banco
     * @return el numero de operacion
     */
    public String getNumero_operacion() {
        return numero_operacion;
    }

    /**
     * Setea el numero de operacion
     * @param numeroOperacion
     */
    public void setNumero_operacion(String numeroOperacion) {
        this.numero_operacion = numeroOperacion;
    }

    public String getReferencia_cobro() {
        return referencia_cobro;
    }

    public void setReferencia_cobro(String referenciaCobro) {
        this.referencia_cobro = referenciaCobro;
    }

    /**
     * Devuelve todos los pagos en order: 1, 2, 3, 4, 5 y la mora
     * @return
     */
    public String[] getImportes() {
        return new String[]{this.importe1, this.importe2, this.importe3,
                    this.importe4, this.importe5, this.importe6_mora
                };
    }

    public String[] getImportessinMora() {
        return new String[]{this.importe1, this.importe2, this.importe3,
                    this.importe4, this.importe5
                };
    }

    @Override
    public String toString() {
        return this.tipo_registro + "\t" + this.cuenta_empresa + "\t"
                + this.secuencia + "\t" + this.codigo_usuario + "\t"
                + this.numero_recibo + "\t" + this.nombre_usuario + "\t"
                + this.moneda_cobro + "\t" + this.importe1 + "\t"
                + this.importe2 + "\t" + this.importe3 + "\t" + this.importe4 + "\t"
                + this.importe5 + "\t" + this.importe6_mora + "\t"
                + this.fecha_vencimiento + "\t" + this.fecha_pago + "\t"
                + this.tipo_pago + "\t" + this.medio_pago + "\t"
                + this.numero_operacion + "\t" + this.referencia_cobro;
    }
}
