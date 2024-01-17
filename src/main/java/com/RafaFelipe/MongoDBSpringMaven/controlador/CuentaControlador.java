package com.RafaFelipe.MongoDBSpringMaven.controlador;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.RafaFelipe.MongoDBSpringMaven.modelo.CuentaBancaria;
import com.RafaFelipe.MongoDBSpringMaven.modelo.CuentaModelo;

@RestController
public class CuentaControlador {

	CuentaModelo cuentitasCuentaModelo = new CuentaModelo();
	@GetMapping("/banco/cuenta")
	public ArrayList<CuentaBancaria> multiplicacion(){
		return cuentitasCuentaModelo.mostrarTodasLasCuentas();
	}
}