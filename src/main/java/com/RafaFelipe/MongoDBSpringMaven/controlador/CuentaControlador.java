package com.RafaFelipe.MongoDBSpringMaven.controlador;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RafaFelipe.MongoDBSpringMaven.modelo.CuentaBancaria;
import com.RafaFelipe.MongoDBSpringMaven.modelo.CuentaModelo;

@RestController
public class CuentaControlador {

	CuentaModelo cuentitasCuentaModelo = new CuentaModelo();
	@GetMapping("/banco/cuenta")
	public ArrayList<CuentaBancaria> mostrarCuentas(){
		return cuentitasCuentaModelo.mostrarTodasLasCuentas();
	}
	

	@GetMapping("/banco/cuenta/{nCuenta}")
	public ArrayList<CuentaBancaria> mostrarPorNumeroCuenta(@PathVariable String nCuenta){
		return cuentitasCuentaModelo.mostrarPorNumeroCuenta(nCuenta);
	}
	
	@GetMapping("/banco/cuenta/{fecha_ini}/{fecha_fin}")
	public ArrayList<CuentaBancaria> mostrarPorFechaApertura(@PathVariable String fechaApertura, @PathVariable String fechaCierre){
		return cuentitasCuentaModelo.mostrarPorFecha(fechaApertura, fechaCierre);
	}
	
	@PutMapping("banco/cuenta/new")
	public boolean insertNewCuentaController(@RequestParam String nCuentas, @RequestParam ArrayList<String> titulares, @RequestParam double saldo) {
		return cuentitasCuentaModelo.insertarCuenta(nCuentas, titulares, saldo);
	}
	
}