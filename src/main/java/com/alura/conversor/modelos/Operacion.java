package com.alura.conversor.modelos;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Operacion {
    String codigo;
    String isoDivisaOrigen;
    String isoDivisaDestino;
    double monto;
    double tasa;
    double resultado;
    Usuario usuario;
    LocalDateTime fechaHora;

    public Operacion(String isoDivisaOrigen, String isoDivisaDestino, double monto, double tasa, double resultado,
            Usuario usuario) {
        this.codigo = obtenerIdentificadorUnico();
        this.isoDivisaOrigen = isoDivisaOrigen;
        this.isoDivisaDestino = isoDivisaDestino;
        this.monto = monto;
        this.tasa = tasa;
        this.resultado = resultado;
        this.usuario = usuario;
        this.fechaHora = LocalDateTime.now();
    }

    public String getCodigo() {
        return this.codigo;
    }

    public String getIsoDivisaOrigen() {
        return isoDivisaOrigen;
    }

    public String getIsoDivisaDestino() {
        return isoDivisaDestino;
    }

    public double getMonto() {
        return monto;
    }

    public double getTasa() {
        return tasa;
    }

    public double getResultado() {
        return resultado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    @Override
    public String toString() {
        String sMonto = formatearDecimal(this.monto);
        String sTasa = formatearDecimal(this.tasa);
        String sResultado = formatearDecimal(this.resultado);
        String sUsuario = this.usuario.getNombre();
        String sFechaHora = formatearFechaHora(this.fechaHora);
        return String.format("Codigo: %s | Operacion: %s -> %s | Monto: %s | Tasa: %s | Resultado: %s | Usuario: %s | Fecha y Hora: %s",this.codigo, this.isoDivisaOrigen, this, isoDivisaDestino, sMonto,
                sTasa, sResultado, sUsuario, sFechaHora);
    }

    private String obtenerIdentificadorUnico() {
        int hashCode = this.hashCode();
        String hashHex = Integer.toHexString(hashCode);
        return hashHex.length() > 8 ? hashHex.substring(hashHex.length() - 8) : hashHex;
    }

    private String formatearDecimal(double numero) {
        DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
        return formatoDecimal.format(numero);
    }

    private String formatearFechaHora(LocalDateTime fechaHora){
        DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatoFechaHora.format(fechaHora);
    }
}
