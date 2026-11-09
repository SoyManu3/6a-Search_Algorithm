package com.mycompany.app;

public class Logic {

    private static final double ERROR = 0.00001;
    private static final int NUM_SEGMENTS = 10000;
    private int intNumSeg;
    private double dblE;
    private int intDOF;
    private double dblX;
    private final double probability;

    public Logic(int dof, double probability) {
        if (dof <= 0 || probability < 0.0 || probability >= 0.5) {
            throw new IllegalArgumentException("dof debe ser positivo y p debe estar entre 0 y 0.5");
        }
        intDOF = dof;
        this.probability = probability;
        intNumSeg = NUM_SEGMENTS;
    }

    public void logic1a() {
        double lower = 0.0;
        double upper = 1.0;
        while (integral(upper) < probability) {
            upper *= 2.0;
        }
        while (upper - lower > ERROR) {
            double trial = (lower + upper) / 2.0;
            if (integral(trial) < probability) {
                lower = trial;
            } else {
                upper = trial;
            }
        }
        dblX = (lower + upper) / 2.0;
        dblE = upper - lower;
    }

    public double findX() {
        logic1a();
        return dblX;
    }

    private double integral(double upper) {
        return new SimpsonIntegration(intDOF).compute(intNumSeg, upper);
    }
}