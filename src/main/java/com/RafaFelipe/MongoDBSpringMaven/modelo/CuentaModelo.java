package com.RafaFelipe.MongoDBSpringMaven.modelo;

import java.time.LocalDate;

	public class CuentaModelo {

    private String numeroCuenta;
    private double saldo;
    private LocalDate fechaApertura;
    private boolean eliminada;

    // Constructor y getters/setters

    // Método para ingresar dinero
    public void ingresarDinero(double montoIngreso) {
        if (montoIngreso > 0) {
            saldo += montoIngreso;
        }
    }

    // Método para retirar dinero
    public String retirarDinero(double montoRetiro) {
        if (montoRetiro <= 0) {
            return "El monto de retiro debe ser mayor que 0.";
        }

        if (montoRetiro > saldo) {
            montoRetiro = saldo; // Retirar el saldo restante si el monto es mayor al saldo
        }

        saldo -= montoRetiro;
        return String.format("Se ha retirado %.2f. Saldo restante: %.2f", montoRetiro, saldo);
    }

    // Otros métodos para mostrar y actualizar cuentas según los endpoints proporcionados
}