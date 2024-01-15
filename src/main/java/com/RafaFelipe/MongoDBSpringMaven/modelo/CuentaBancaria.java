package com.RafaFelipe.MongoDBSpringMaven.modelo;

import java.sql.Date;

public class CuentaBancaria {

	private int nCuenta;
	private String listaTitulareString;
	private double saldo;
	private Date fechaApertura;
	private boolean existente;
	
	public CuentaBancaria() {
		this.nCuenta = 0;
		this.listaTitulareString = "";
		this.saldo = 0.0;
		this.fechaApertura = null;
		this.existente = true;
	}
	
	public CuentaBancaria(int nCuenta, String listaTitulareString, double saldo, Date fechaApertura, boolean existente) {
		this.nCuenta = nCuenta;
		this.listaTitulareString = listaTitulareString;
		this.saldo = saldo;
		this.fechaApertura = fechaApertura;
		this.existente = existente;
	}

	public int getnCuenta() {
		return nCuenta;
	}

	public void setnCuenta(int nCuenta) {
		this.nCuenta = nCuenta;
	}

	public String getListaTitulareString() {
		return listaTitulareString;
	}

	public void setListaTitulareString(String listaTitulareString) {
		this.listaTitulareString = listaTitulareString;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Date getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public boolean isExistente() {
		return existente;
	}

	public void setExistente(boolean existente) {
		this.existente = existente;
	}
	
	
}
