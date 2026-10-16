/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootar1banco;

/**
 *
 * @author Teo
 */
public class CuentaBancaria {
    
    // Atributos Públicos 
    public String numeroCuenta;
    public String titular;

    // Atributos Privados (Encapsulados)
    private double saldo;
    private int totalTransacciones;

    // Constructor
    public CuentaBancaria(String numeroCuenta, String titular, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        if (saldoInicial < 0) {
            this.saldo = 0.0;
        } else {
            this.saldo = saldoInicial;
        }
        this.totalTransacciones = 0;
    }

    // Métodos Getters para atributos privados
    public double getSaldo() {
        return saldo;
    }

    public int getTotalTransacciones() {
        return totalTransacciones;
    }

    // Método para depositar
    public void depositar(double monto) {
        if (monto > 0) {
            saldo += monto;
            totalTransacciones++;
            System.out.println("Deposito exitoso por L. " + monto + ". Nuevo saldo: L. " + saldo);
        } else {
            System.out.println("Error: El monto a depositar debe ser mayor a 0.");
        }
    }

    // Método para retirar
    public boolean retirar(double monto) {
        if (monto > 0 && saldo >= monto) {
            saldo -= monto;
            totalTransacciones++;
            System.out.println("Retiro exitoso por L. " + monto + ". Nuevo saldo: L. " + saldo);
            return true;
        } else {
            System.out.println("Error en retiro: Fondos insuficientes o monto invalido (Monto solicitado: L. " + monto + ", Saldo disponible: L. " + saldo + ").");
            return false;
        }
    }

    // Método para mostrar datos
    public void mostrarDatos() {
        System.out.println("----------------------------------------");
        System.out.println("TITULAR: " + titular);
        System.out.println("NUMERO DE CUENTA: " + numeroCuenta);
        System.out.println("SALDO ACTUAL: L. " + saldo);
        System.out.println("TOTAL TRANSACCIONES: " + totalTransacciones);
        System.out.println("----------------------------------------");
    }
    
}
