package com.RafaFelipe.MongoDBSpringMaven.modelo;

import java.sql.Date;
import java.util.List;

public class CuentaBancaria {

	String nCuenta;
	List<String> listaTitulares;
	double saldo;
	Date fechaApertura;
	boolean existente;

	public CuentaBancaria(List<String> listaTitulares) {
		this.nCuenta = "";
		this.listaTitulares = listaTitulares;
		this.saldo = 0;
		this.fechaApertura = null;
		this.existente = false;
	}

	public CuentaBancaria(String nCuenta, List<String> listaTitulares, double saldo, Date fechaApertura,
			boolean existente) {
		this.nCuenta = nCuenta;
		this.listaTitulares = listaTitulares;
		this.saldo = saldo;
		this.fechaApertura = fechaApertura;
		this.existente = existente;
	}

	public String getnCuenta() {
		return nCuenta;
	}

	public void setnCuenta(String nCuenta) {
		this.nCuenta = nCuenta;
	}

	public List<String> getListaTitulares() {
		return listaTitulares;
	}

	public void setListaTitulares(List<String> listaTitulares) {
		this.listaTitulares = listaTitulares;
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
