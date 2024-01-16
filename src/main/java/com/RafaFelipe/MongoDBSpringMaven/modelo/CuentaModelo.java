package com.RafaFelipe.MongoDBSpringMaven.modelo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CuentaModelo {

    private List<CuentaBancaria> cuentas;

    public CuentaModelo() {
        this.cuentas = new ArrayList<>();
    }

    public List<CuentaBancaria> mostrarTodasLasCuentas() {
   	 return cuentas.stream().filter(CuentaBancaria::isExistente).collect(Collectors.toList());
   }

    public CuentaBancaria mostrarCuentaPorNumero(String nroCuenta) {
    	return cuentas.stream().filter(cuenta -> cuenta.getnCuenta().equals(nroCuenta) && cuenta.isExistente()).findFirst().orElse(null);
    }

    public List<CuentaBancaria> mostrarCuentasPorRangoDeFecha(Date fechaInicio, Date fechaFin) {
    	 return cuentas.stream().filter(cuenta -> cuenta.getFechaApertura().compareTo(fechaInicio) >= 0 && cuenta.getFechaApertura().compareTo(fechaFin) <= 0 && cuenta.isExistente()).collect(Collectors.toList());
    }

    public void insertarNuevaCuenta(CuentaBancaria cuenta) {
        cuentas.add(cuenta);
    }

    public void actualizarCuentaPorNumero(CuentaBancaria cuenta) {
    	int index = -1;
        for (int i = 0; i < cuentas.size(); i++) {
            if (cuentas.get(i).getnCuenta().equals(cuenta.getnCuenta())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            cuentas.set(index, cuenta);
        }
    }

    public void borrarCuentaPorNumero(String nroCuenta) {
    	cuentas.stream().filter(cuenta -> cuenta.getnCuenta().equals(nroCuenta)).findFirst().ifPresent(cuenta -> cuenta.setExistente(false));
    }

    public String ingresarDinero(String nroCuenta, double ingreso) {
    	CuentaBancaria cuenta = cuentas.stream()
                .filter(c -> c.getnCuenta().equals(nroCuenta) && c.isExistente())
                .findFirst()
                .orElse(null);

        if (cuenta != null && ingreso > 0) {
            double saldoAnterior = cuenta.getSaldo();
            cuenta.setSaldo(saldoAnterior + ingreso);
            return "Se ingresaron $" + ingreso + " a la cuenta. Saldo restante: $" + cuenta.getSaldo();
        } else {
            return "No se pudo realizar el ingreso. Verifica la cuenta o el monto ingresado.";
        }
    }
    public String retirarDinero(String nroCuenta, double retiro) {
        CuentaBancaria cuenta = cuentas.stream()
                .filter(c -> c.getnCuenta().equals(nroCuenta) && c.isExistente())
                .findFirst()
                .orElse(null);

        if (cuenta != null && retiro > 0) {
            double saldoAnterior = cuenta.getSaldo();
            double saldoRestante = Math.max(0, saldoAnterior - retiro);
            cuenta.setSaldo(saldoRestante);
            return "Se retiraron $" + retiro + " de la cuenta. Saldo restante: $" + cuenta.getSaldo();
        } else {
            return "No se pudo realizar el retiro. Verifica la cuenta o el monto a retirar.";
        }
    }
}