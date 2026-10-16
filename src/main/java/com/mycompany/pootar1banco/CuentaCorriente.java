/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pootar1banco;

/**
 *
 * @author Teo
 */
public class CuentaCorriente extends CuentaBancaria {
    
    // Atributo público especificado
    public String nombreEmpresa;

    // Atributos privados
    private double limiteSobregiro;
    private double sobregiroUtilizado;

    // Constructor que invoca a la superclase mediante super(...)
    public CuentaCorriente(String numeroCuenta, String titular, double saldoInicial, String nombreEmpresa, double limiteSobregiro) {
        super(numeroCuenta, titular, saldoInicial);
        this.nombreEmpresa = nombreEmpresa;
        this.limiteSobregiro = limiteSobregiro;
        this.sobregiroUtilizado = 0.0;
    }

    // Métodos getters propios
    public double getLimiteSobregiro() {
        return limiteSobregiro;
    }

    public double getSobregiroUtilizado() {
        return sobregiroUtilizado;
    }

    // Método propio para retirar con sobregiro y recargo del 3%
    public boolean retirarConSobregiro(double monto) {
        if (monto <= 0) {
            System.out.println("Error: El monto del retiro debe ser mayor a 0.");
            return false;
        }

        double saldoActual = getSaldo();
        // Crédito disponible total (línea de sobregiro restante)
        double creditoDisponible = limiteSobregiro - sobregiroUtilizado;

        // Caso 1: El saldo actual cubre completamente el monto
        if (saldoActual >= monto) {
            return super.retirar(monto);
        } 
        // Caso 2: El saldo no alcanza, pero se puede usar parte o todo el sobregiro
        else if (saldoActual + creditoDisponible >= monto) {
            double deficit = monto - saldoActual; // Lo que falta por cubrir con crédito
            double recargoServicio = deficit * 0.03; // 3% de recargo sobre el crédito usado
            double totalADebitar = deficit + recargoServicio;

            // Vaciamos el saldo actual usando el método retirar por completo
            super.retirar(saldoActual);

            // Aumentamos el sobregiro utilizado con el déficit más el recargo
            sobregiroUtilizado += totalADebitar;

            System.out.println("Sobregiro aplicado. Se utilizaron L. " + deficit + " de credito mas 3% de recargo (L. " + recargoServicio + ").");
            System.out.println("Sobregiro total utilizado actualmente: L. " + sobregiroUtilizado);
            return true;
        } 
        // Caso 3: Excede el cupo permitido
        else {
            System.out.println("Error: El retiro excede el saldo disponible y el limite de sobregiro autorizado.");
            return false;
        }
    }

    // Método propio para abonar y pagar la deuda de sobregiro
    public void pagarSobregiro(double monto) {
        if (monto <= 0) {
            System.out.println("Error: El monto a pagar debe ser mayor a 0.");
            return;
        }

        System.out.println("Intentando abonar L. " + monto + " para amortizar sobregiro...");
        
        if (sobregiroUtilizado > 0) {
            if (monto >= sobregiroUtilizado) {
                double sobrante = monto - sobregiroUtilizado;
                System.out.println("¡Sobregiro pagado en su totalidad! Se cubrieron L. " + sobregiroUtilizado);
                sobregiroUtilizado = 0.0;
                
                // Si sobra dinero después de pagar la deuda, se abona al saldo de la cuenta
                if (sobrante > 0) {
                    System.out.println("El sobrante de L. " + sobrante + " se abonara al saldo de la cuenta.");
                    depositar(sobrante);
                }
            } else {
                sobregiroUtilizado -= monto;
                System.out.println("Abono aplicado al sobregiro. Deuda de sobregiro restante: L. " + sobregiroUtilizado);
            }
        } else {
            System.out.println("No hay deuda de sobregiro pendiente. El dinero se deposita directamente a la cuenta.");
            depositar(monto);
        }
    }
    
}
