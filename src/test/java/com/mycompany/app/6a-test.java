package com.mycompany.app;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SixATest {

    @Test
    void findsXForP020AndDof6() {
        assertEquals(0.55338, App.findX(6, 0.20), 0.00001);
    }

    @Test
    void findsXForP045AndDof15() {
        assertEquals(1.75305, App.findX(15, 0.45), 0.00001);
    }

    @Test
    void findsXForP0495AndDof4() {
        assertEquals(4.60409, App.findX(4, 0.495), 0.00001);
    }

    @Test
    void computesGammaForIntegerArgument() {
        assertEquals(24.0, new GammaFunction().computeIntGamma(5), 0.00001);
    }

    @Test
    void integratesTheFirstTableCase() {
        assertEquals(0.20, new SimpsonIntegration(6).compute(10000, 0.55338), 0.00001);
    }

    @Test
    void findsXThroughLogic() {
        assertEquals(1.75305, new Logic(15, 0.45).findX(), 0.00001);
    }

    @Test
    void writesOutputFile() throws Exception {
        Path output = Files.createTempFile("6a-test", ".txt");
        new Output().writeData(output.toString(), "1.75305");
        assertTrue(Files.readString(output).contains("1.75305"));
        Files.deleteIfExists(output);
    }
}