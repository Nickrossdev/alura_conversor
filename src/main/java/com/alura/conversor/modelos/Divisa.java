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
    }

    @Override
    public String toString() {
        System.out.println(String.format("",));
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
