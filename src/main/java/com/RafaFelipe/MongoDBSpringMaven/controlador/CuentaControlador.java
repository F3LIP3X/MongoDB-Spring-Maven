package com.RafaFelipe.MongoDBSpringMaven.controlador;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RafaFelipe.MongoDBSpringMaven.modelo.CuentaBancaria;
import com.RafaFelipe.MongoDBSpringMaven.modelo.CuentaModelo;

@RestController
public class CuentaControlador {

	CuentaModelo cuentitasCuentaModelo = new CuentaModelo();
	
	//Primer Metodo (Funciona)
	@GetMapping("/banco/cuenta")
	public ArrayList<CuentaBancaria> mostrarCuentas(){
		return cuentitasCuentaModelo.mostrarTodasLasCuentas();
	}
	
	//Segundo Metodo (Funciona)
	@GetMapping("/banco/cuenta/{nCuenta}")
	public ArrayList<CuentaBancaria> mostrarPorNumeroCuenta(@PathVariable String nCuenta){
		return cuentitasCuentaModelo.mostrarPorNumeroCuenta(nCuenta);
	}
	
	//Tercer Metodo (Funciona)
	@GetMapping("/banco/cuenta/{fecha_ini}/{fecha_fin}")
	public ArrayList<CuentaBancaria> mostrarPorFechaApertura(@PathVariable String fechaApertura, @PathVariable String fechaCierre){
		return cuentitasCuentaModelo.mostrarPorFecha(fechaApertura, fechaCierre);
	}
	
	//Cuarto Metodo (Funciona)
	@PutMapping("banco/cuenta/new")
	public boolean insertNewCuentaController(@RequestParam String nCuentas, @RequestParam ArrayList<String> titulares, @RequestParam double saldo) {
		return cuentitasCuentaModelo.insertarCuenta(nCuentas, titulares, saldo);
	}
	
	//Quinto Metodo (Funciona)
	@PutMapping("/banco/cuenta/update/{nCuentas}")
    public void actualizarCuentaPorNumero(@PathVariable String nCuentas,@RequestBody CuentaBancaria nueva) {
		cuentitasCuentaModelo.actualizarCuenta(nCuentas, nueva);
    }
	
	//Sexto Metodo (Funciona)
	@PutMapping("banco/cuenta/{nCuentas}")
	public boolean borrarCuenta(@RequestParam String nCuentas) {
		return cuentitasCuentaModelo.borrarCuenta(nCuentas);
	}
	
	//Septimo Metodo (Funciona)
	@PutMapping("banco/cuenta/ingresar")
	public String ingresarDinero(@RequestParam String nCuentas, @RequestParam double ingreso) {
		return cuentitasCuentaModelo.ingresarDinero(nCuentas, ingreso);
	}
	
	//Octavo Metodo (Funciona)
	@PutMapping("banco/cuenta/retirar")
	public String retirarDinero(@RequestParam String nCuentas, @RequestParam double retiro) {

		return cuentitasCuentaModelo.retirarDinero(nCuentas, retiro) ;
	}
	
}