/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootar1banco;

/**
 *
 * @author Teo
 */
public class CuentaAhorro extends CuentaBancaria {
    
    // Atributo público 
    public double tasaInteresAnual;

    // Atributos privados
    private int retirosRealizadosMes;
    private double comisionPorExceso;

    // Constructor que invoca a la superclase mediante super(...)
    public CuentaAhorro(String numeroCuenta, String titular, double saldoInicial, double tasaInteresAnual) {
        super(numeroCuenta, titular, saldoInicial);
        this.tasaInteresAnual = tasaInteresAnual;
        this.retirosRealizadosMes = 0;
        this.comisionPorExceso = 20.0; // Tarifa fija por exceso de retiros
    }

    // Método propio para retirar con control de comisiones
    public void retirarConComision(double monto) {
        if (monto <= 0) {
            System.out.println("Error: El monto del retiro debe ser mayor a 0.");
            return;
        }

        // Si se hicieron 3 o más retiros se aplica la comisión por exceso
        if (retirosRealizadosMes >= 3) {
            double montoTotal = monto + comisionPorExceso;
            System.out.println("Aviso: Ha superado el limite de 3 retiros gratuitos este mes.");
            System.out.println("Se aplicara una comision por exceso de L. " + comisionPorExceso);
            
            // Intentamos retirar el monto sumado a la comisión utilizando el método de la clase padre
            if (super.retirar(montoTotal)) {
                retirosRealizadosMes++;
                System.out.println("Retiro con comision exitoso. Retiros realizados este mes: " + retirosRealizadosMes);
            }
        } else {
            // Retiro normal sin recargo
            if (super.retirar(monto)) {
                retirosRealizadosMes++;
                System.out.println("Retiro normal exitoso. Retiros realizados este mes: " + retirosRealizadosMes);
            }
        }
    }

    // Método propio para aplicar el interés mensual
    public void aplicarInteresMensual() {
        double interesMes = (getSaldo() * (tasaInteresAnual / 100)) / 12;
        if (interesMes > 0) {
            System.out.println("Aplicando rendimiento de interes mensual por L. " + interesMes);
            depositar(interesMes);
        } else {
            System.out.println("No hay saldo suficiente para generar intereses este mes.");
        }
    }
    
}
