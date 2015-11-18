package net.uch.cursoLibre.logicreport;

public class Pago {

	private String idpago;
	private String decspago;
	private String pagocaja;
	private double monto;
	private double pagado;
	private double saldo;
	private double recargo;
	
	public Pago() {
		
	}
	
	public Pago( String decspago, double monto, String pagocaja) {
		this.decspago = decspago;
		this.pagocaja = pagocaja;
		this.monto = monto;
	}

	public String getIdpago() {
		return idpago;
	}
	
	public void setIdpago(String idpago) {
		this.idpago = idpago;
	}
	public String getDecspago() {
		return decspago;
	}
	public void setDecspago(String decspago) {
		this.decspago = decspago;
	}
	public String getPagocaja() {
		return pagocaja;
	}
	public void setPagocaja(String pagocaja) {
		this.pagocaja = pagocaja;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public double getPagado() {
		return pagado;
	}
	public void setPagado(double pagado) {
		this.pagado = pagado;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public double getRecargo() {
		return recargo;
	}
	public void setRecargo(double recargo) {
		this.recargo = recargo;
	}
	
	
}
