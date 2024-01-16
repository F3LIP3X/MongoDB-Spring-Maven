package com.RafaFelipe.MongoDBSpringMaven.controlador;

import com.RafaFelipe.MongoDBSpringMaven.modelo.CuentaBancaria;
import com.RafaFelipe.MongoDBSpringMaven.modelo.CuentaModelo;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/banco/cuenta")
public class CuentaControlador {

    private final CuentaModelo cuentaModelo;

    public CuentaControlador(CuentaModelo cuentaModelo) {
        this.cuentaModelo = cuentaModelo;
    }

    @GetMapping
    public List<CuentaBancaria> mostrarTodasLasCuentas() {
        return cuentaModelo.mostrarTodasLasCuentas();
    }

    @GetMapping("/{nro_cuenta}")
    public CuentaBancaria mostrarCuentaPorNumero(@PathVariable("nro_cuenta") String nroCuenta) {
        return cuentaModelo.mostrarCuentaPorNumero(nroCuenta);
    }

    @GetMapping("/{fecha_ini}/{fecha_fin}")
    public List<CuentaBancaria> mostrarCuentasPorRangoDeFecha(@PathVariable("fecha_ini") Date fechaInicio,@PathVariable("fecha_fin") Date fechaFin) {
        return cuentaModelo.mostrarCuentasPorRangoDeFecha(fechaInicio, fechaFin);
    }

    @PutMapping("/new")
    public void insertarNuevaCuenta(@RequestBody CuentaBancaria cuenta) {
        cuentaModelo.insertarNuevaCuenta(cuenta);
    }

    @PutMapping("/update")
    public void actualizarCuentaPorNumero(@RequestBody CuentaBancaria cuenta) {
        cuentaModelo.actualizarCuentaPorNumero(cuenta);
    }

    @DeleteMapping("/{nro_cuenta}")
    public void borrarCuentaPorNumero(@PathVariable("nro_cuenta") String nroCuenta) {
        cuentaModelo.borrarCuentaPorNumero(nroCuenta);
    }

    @PutMapping("/ingresar")
    public String ingresarDinero(@RequestBody IngresoRequest ingresoRequest) {
        return cuentaModelo.ingresarDinero(ingresoRequest.getNroCuenta(), ingresoRequest.getIngreso());
    }

    @PutMapping("/retirar")
    public String retirarDinero(@RequestBody RetiroRequest retiroRequest) {
        return cuentaModelo.retirarDinero(retiroRequest.getNroCuenta(), retiroRequest.getRetiro());
    }
}