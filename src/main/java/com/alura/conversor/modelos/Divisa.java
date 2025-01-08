package com.alura.conversor.modelos;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Divisa {
    private String codigo;
    private String iso;
    private String nombre;
    private double tasa;
    private LocalDateTime fechaHora;

    public Divisa(String iso, String nombre, double tasa) {
        this.codigo = obtenerIdentificadorUnico();
        this.iso = iso;
        this.nombre = nombre;
        this.tasa = tasa;
        this.fechaHora = LocalDateTime.now();
    }

    public String getCodigo() {
        return this.codigo;
    }

    public String getIso() {
        return this.iso;
    }

    public String getNombre() {
        return nombre;
    }

    public double getTasa() {
        return tasa;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setTasa(double tasa) {
        this.tasa = tasa;
        this.fechaHora = LocalDateTime.now();
    }

    @Override
    public String toString() {
        String sTasa = formatearDecimal(this.tasa);
        String sFechaHora = formatearFechaHora(this.fechaHora);
        return String.format(
                "Codigo: %s | Iso: %s | Nombre: %s | Tasa: %s | Actualización: %s",
                this.codigo, this.iso, this.nombre, sTasa, sFechaHora);
    }

    private String obtenerIdentificadorUnico() {
        String base = this.iso + LocalDateTime.now().toString() + this.nombre;
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
