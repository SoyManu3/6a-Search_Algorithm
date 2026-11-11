package com.mycompany.app;

import java.util.Locale;

public class App {

    public static void main(String[] args) {
        if (args.length < 2 || args.length > 3) {
            System.out.println("Uso: java ... <dof> <p> [archivo-salida]");
            return;
        }

        int dof = Integer.parseInt(args[0]);
        double p = Double.parseDouble(args[1]);
        double x = findX(dof, p);
        System.out.printf(Locale.US, "Entrada: dof=%d, p=%.3f%n", dof, p);
        System.out.printf(Locale.US, "Resultado: x=%.5f%n", x);
        String result = String.format(Locale.US, "%.5f%n", x);
        if (args.length == 3) {
            new Output().writeData(args[2], result);
        }
    }

    public static double findX(int dof, double p) {
        return new Logic(dof, p).findX();
    }
}
