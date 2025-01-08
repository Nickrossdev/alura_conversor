package com.alura.conversor.modelos;

public class OperacionCorta {
    private String isoDivisaOrigen;
    private String isoDivisaDestino;
    private int contadorDeOperaciones;

    public OperacionCorta(String isoDivisaOrigen, String isoDivisaDestino) {
        this.isoDivisaOrigen = isoDivisaOrigen;
        this.isoDivisaDestino = isoDivisaDestino;
    }

    public String getIsoDivisaOrigen() {
        return this.isoDivisaOrigen;
    }

    public String getIsoDivisaDestino() {
        return this.isoDivisaDestino;
    }

    public int getContadorDeOperaciones() {
        return this.contadorDeOperaciones;
    }

    public void incrementarContadorDeOperaciones() {
        this.contadorDeOperaciones++;
    }
}
