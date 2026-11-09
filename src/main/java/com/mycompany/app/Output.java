package com.mycompany.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Output {

    public void writeData(String outFile, String outText) {
        try {
            Files.writeString(Path.of(outFile), outText);
        } catch (IOException exception) {
            throw new IllegalStateException("No se pudo escribir el archivo de salida", exception);
        }
    }
}