package com.RafaFelipe.MongoDBSpringMaven.modelo;

import java.util.Date;
import java.util.ArrayList;
import org.bson.Document;

public class CuentaBancaria {

	String nCuenta;
	ArrayList<String> listaTitulares;
	double saldo;
	Date fechaApertura;
	boolean Borrada;

	public CuentaBancaria() {
	}

	public CuentaBancaria(Document cuentaDocumento) {
        this.nCuenta = cuentaDocumento.getString("NumeroCuenta");
        this.listaTitulares = (ArrayList<String>) cuentaDocumento.get("ListaTitulares");
        this.saldo = cuentaDocumento.get("Saldo", Number.class).doubleValue();
        this.fechaApertura = (Date) cuentaDocumento.getDate("Fecha_Apertura");
        this.Borrada = cuentaDocumento.getBoolean("Borrada");
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
		return Borrada;
	}

	public void setExistente(boolean existente) {
		this.Borrada = existente;
	}

}
