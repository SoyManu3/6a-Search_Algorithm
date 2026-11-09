package com.mycompany.app;

public class GammaFunction {

    private double gammaValue;

    public double computeIntGamma(int value) {
        return computeDblGamma(value);
    }

    public double computeDblGamma(double value) {
        if (value <= 0.0) {
            throw new IllegalArgumentException("El argumento de Gamma debe ser positivo");
        }
        gammaValue = Math.exp(logGamma(value));
        return gammaValue;
    }

    private double logGamma(double value) {
        double[] coefficients = {
                676.5203681218851, -1259.1392167224028, 771.32342877765313,
                -176.61502916214059, 12.507343278686905, -0.13857109526572012,
                9.9843695780195716e-6, 1.5056327351493116e-7
        };
        if (value < 0.5) {
            return Math.log(Math.PI) - Math.log(Math.sin(Math.PI * value)) - logGamma(1.0 - value);
        }

        double sum = 0.99999999999980993;
        double shifted = value - 1.0;
        for (int i = 0; i < coefficients.length; i++) {
            sum += coefficients[i] / (shifted + i + 1.0);
        }
        double t = shifted + coefficients.length - 0.5;
        return 0.5 * Math.log(2.0 * Math.PI) + (shifted + 0.5) * Math.log(t) - t + Math.log(sum);
    }
}