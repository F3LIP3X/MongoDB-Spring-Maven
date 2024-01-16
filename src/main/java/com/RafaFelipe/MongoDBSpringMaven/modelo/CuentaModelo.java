package com.RafaFelipe.MongoDBSpringMaven.modelo;

import java.time.LocalDate;

	public class CuentaModelo {

    private String numeroCuenta;
    private double saldo;
    private LocalDate fechaApertura;
    private boolean eliminada;

    public void ingresarDinero(double montoIngreso) {
        if (montoIngreso > 0) {
            saldo += montoIngreso;
        }
    }

    public String retirarDinero(double montoRetiro) {
        if (montoRetiro <= 0) {
            return "El monto de retiro debe ser mayor que 0.";
        }

        if (montoRetiro > saldo) {
            montoRetiro = saldo;
        }

        saldo -= montoRetiro;
        return String.format("Se ha retirado %.2f. Saldo restante: %.2f", montoRetiro, saldo);
    }

}