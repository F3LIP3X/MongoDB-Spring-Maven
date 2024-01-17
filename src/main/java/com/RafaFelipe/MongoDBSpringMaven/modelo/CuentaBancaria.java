package com.RafaFelipe.MongoDBSpringMaven.modelo;

import java.sql.Date;
import java.util.ArrayList;
import org.bson.Document;

public class CuentaBancaria {

	String nCuenta;
	ArrayList<String> listaTitulares;
	double saldo;
	Date fechaApertura;
	boolean existente;

	public CuentaBancaria(Document cuentaDocumento) {
        this.nCuenta = cuentaDocumento.getString("numeroCuenta");
        this.listaTitulares = (ArrayList<String>) cuentaDocumento.get("titulares");
        this.saldo = cuentaDocumento.get("saldo", Number.class).doubleValue();
        this.fechaApertura = (Date) cuentaDocumento.getDate("fechaApertura");
        this.existente = cuentaDocumento.getBoolean("borrada", false);
        
	}
    
	public String getnCuenta() {
		return nCuenta;
	}

	public void setnCuenta(String nCuenta) {
		this.nCuenta = nCuenta;
	}

	public ArrayList<String> getListaTitulares() {
		return listaTitulares;
	}

	public void setListaTitulares(ArrayList<String> listaTitulares) {
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
