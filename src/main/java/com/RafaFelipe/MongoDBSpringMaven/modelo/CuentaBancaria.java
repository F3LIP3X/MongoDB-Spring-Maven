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
	
	
}
