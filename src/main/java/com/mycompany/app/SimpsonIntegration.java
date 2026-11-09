package com.mycompany.app;

public class SimpsonIntegration {

    private int intNumSeg;
    private int intDOF;
    private double dblX;
    private double dblE;
    private double[] dblTotXi;
    private double[] dblFirstBaseTerms;
    private double dblExponent;
    private double[] dblCoeff;
    private double[] dblFxi;
    private double[] dblFinalTerms;
    private double dblFinalValue;
    private final GammaFunction gammaFunction;

    public SimpsonIntegration(int dof) {
        if (dof <= 0) {
            throw new IllegalArgumentException("dof debe ser positivo");
        }
        intDOF = dof;
        gammaFunction = new GammaFunction();
    }

    public double compute(int numSeg, double x) {
        if (numSeg <= 0 || numSeg % 2 != 0 || x < 0.0) {
            throw new IllegalArgumentException("numSeg debe ser par y x no puede ser negativo");
        }
        intNumSeg = numSeg;
        dblX = x;
        computeExponent(numSeg);
        computeFirstBaseTerms(numSeg, x, intDOF);
        computeCoeff(numSeg);
        computeFxi(numSeg);
        computeFinalTerms(numSeg);
        return computeFinalValue(numSeg);
    }

    public void computeFirstBaseTerms(int numSeg, double totalX, int dof) {
        intDOF = dof;
        dblTotXi = new double[numSeg + 1];
        dblFirstBaseTerms = new double[numSeg + 1];
        double step = totalX / numSeg;
        for (int i = 0; i <= numSeg; i++) {
            dblTotXi[i] = i * step;
            dblFirstBaseTerms[i] = tDensity(dblTotXi[i]);
        }
    }

    public void computeExponent(int numSeg) {
        dblExponent = -(intDOF + 1.0) / 2.0;
    }

    public void computeCoeff(int numSeg) {
        dblCoeff = new double[numSeg + 1];
        for (int i = 0; i <= numSeg; i++) {
            dblCoeff[i] = i == 0 || i == numSeg ? 1.0 : (i % 2 == 0 ? 2.0 : 4.0);
        }
    }

    public void computeFxi(int numSeg) {
        dblFxi = new double[numSeg + 1];
        for (int i = 0; i <= numSeg; i++) {
            dblFxi[i] = dblFirstBaseTerms[i];
        }
    }

    public void computeFinalTerms(int numSeg) {
        dblFinalTerms = new double[numSeg + 1];
        for (int i = 0; i <= numSeg; i++) {
            dblFinalTerms[i] = dblCoeff[i] * dblFxi[i];
        }
    }

    public double computeFinalValue(int numSeg) {
        double sum = 0.0;
        for (double term : dblFinalTerms) {
            sum += term;
        }
        dblFinalValue = sum * (dblX / numSeg) / 3.0;
        return dblFinalValue;
    }

    private double tDensity(double value) {
        double degrees = intDOF;
        double coefficient = gammaFunction.computeDblGamma((degrees + 1.0) / 2.0)
                / (Math.sqrt(degrees * Math.PI) * gammaFunction.computeDblGamma(degrees / 2.0));
        return coefficient * Math.pow(1.0 + value * value / degrees, dblExponent);
    }
}