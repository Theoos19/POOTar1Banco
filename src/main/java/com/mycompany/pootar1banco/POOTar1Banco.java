/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.pootar1banco;

/**
 *
 * @author Teo
 */
public class POOTar1Banco {

    public static void main(String[] args) {
        System.out.println("=====================");
        System.out.println("   SIMULADOR TRANACCIONAL - CORE BANCARIO ");
        System.out.println("======================\n");

        
        // 1. PRUEBA DE ENCAPSULAMIENTO
        
        System.out.println("--- 1. PRUEBA DE ENCAPSULAMIENTO ---");
        CuentaBancaria cuentaBase = new CuentaBancaria("CB-001", "Carlos Rosacruz", 5000.0);
        
        // La siguiente linea generaria un error de compilacion porque 'saldo' es private:
        // cuentaBase.saldo = 50000.0; 
        System.out.println("Explicacion: Intentar modificar 'cuentaBase.saldo = 50000.0;' directamente");
        System.out.println("genera un error de compilacion ya que el atributo es privado (private).");
        System.out.println("Se debe proteger y modificar exclusivamente a traves de metodos validados.\n");

     
        // 2. ACCESO DIRECTO A ATRIBUTOS PÚBLICOS
     
        System.out.println("--- 2. ACCESO DIRECTO A ATRIBUTOS PUBLICOS ---");
        System.out.println("Titular (acceso directo publico): " + cuentaBase.titular);
        System.out.println("Numero de Cuenta (acceso directo publico): " + cuentaBase.numeroCuenta);
        System.out.println("Saldo actual mediante getter protegido: L. " + cuentaBase.getSaldo() + "\n");

        
        // 3. PRUEBA DE CUENTA DE AHORRO (4 Retiros consecutivos)
        
        System.out.println("--- 3. PRUEBA DE CUENTA DE AHORRO (Comision por exceso) ---");
        CuentaAhorro cuentaAhorro = new CuentaAhorro("CA-999", "Rivaldo Dominguez", 3000.0, 5.0);
        cuentaAhorro.mostrarDatos();
        
        System.out.println("\nRealizando 4 retiros consecutivos de L. 200 cada uno:");
        System.out.println("Retiro 1:");
        cuentaAhorro.retirarConComision(200.0);
        System.out.println("Retiro 2:");
        cuentaAhorro.retirarConComision(200.0);
        System.out.println("Retiro 3:");
        cuentaAhorro.retirarConComision(200.0);
        System.out.println("Retiro 4 (Aqui debe aplicar la comision fija de L. 20):");
        cuentaAhorro.retirarConComision(200.0);
        System.out.println();

        
        // 4. RENDIMIENTO DE AHORRO (Interés Mensual)
        
        System.out.println("--- 4. RENDIMIENTO DE INTERES MENSUAL ---");
        cuentaAhorro.aplicarInteresMensual();
        System.out.println("Saldo final de ahorro despues de intereses:");
        cuentaAhorro.mostrarDatos();
        System.out.println();

       
        // 5. PRUEBA DE CUENTA CORRIENTE (Sobregiro y recargo 3%)
        
        System.out.println("--- 5. PRUEBA DE CUENTA CORRIENTE (Sobregiro) ---");
        CuentaCorriente cuentaCorriente = new CuentaCorriente("CC-777", "Distribuidora Sula", 1000.0, "S. de R.L", 5000.0);
        cuentaCorriente.mostrarDatos();
        
        System.out.println("\nIntentando retirar L. 3,500 (Saldo disponible: L. 1,000 + Sobregiro de L. 2,500 + 3% recargo):");
        cuentaCorriente.retirarConSobregiro(3500.0);
        System.out.println();

        
        // 6. AMORTIZACIÓN DE DEUDA (Pagar Sobregiro)
        
        System.out.println("--- 6. AMORTIZACION DE SOBREGIRO ---");
        System.out.println("Deuda de sobregiro actual: L. " + cuentaCorriente.getSobregiroUtilizado());
        
        // Abonamos para pagar el sobregiro y dejar un sobrante
        cuentaCorriente.pagarSobregiro(3000.0);
        
        System.out.println("\nEstado final de la Cuenta Corriente:");
        cuentaCorriente.mostrarDatos();
        System.out.println("Sobregiro utilizado restante: L. " + cuentaCorriente.getSobregiroUtilizado());
        System.out.println("========================================");
    }
}
