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
        return this.isoDivisaOrigen;
    }

    public String getIsoDivisaDestino() {
        return this.isoDivisaDestino;
    }

    public double getMonto() {
        return this.monto;
    }

    public double getTasa() {
        return this.tasa;
    }

    public double getResultado() {
        return this.resultado;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public LocalDateTime getFechaHora() {
        return this.fechaHora;
    }

    @Override
    public String toString() {
        String sMonto = formatearDecimal(this.monto);
        String sTasa = formatearDecimal(this.tasa);
        String sResultado = formatearDecimal(this.resultado);
        String sUsuario = this.usuario.getNombre();
        String sFechaHora = formatearFechaHora(this.fechaHora);

        return String.format(
                "Codigo: %s | Operacion: %s -> %s | Monto: %s | Tasa: %s | Resultado: %s | Usuario: %s | Fecha y Hora: %s",
                this.codigo, this.isoDivisaOrigen, this.isoDivisaDestino, sMonto, sTasa, sResultado, sUsuario,
                sFechaHora);
    }

    private String obtenerIdentificadorUnico() {
        String base = this.isoDivisaOrigen + LocalDateTime.now().toString() + this.isoDivisaDestino;
        return Integer.toHexString(base.hashCode());
    }

    private String formatearDecimal(double numero) {
        DecimalFormat formatoDecimal = new DecimalFormat("#,##0.00");
        return formatoDecimal.format(numero);
    }

    private String formatearFechaHora(LocalDateTime fechaHora) {
        DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatoFechaHora.format(fechaHora);
    }
}
