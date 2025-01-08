package com.alura.conversor.modelos;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

public class Usuario {
    private String codigo;
    private String nombre;
    private String correo;
    private int contadorDeOperaciones;
    private LinkedHashMap<String, Operacion> historialDeOperaciones;

    public Usuario(String nombre, String correo) {
        this.codigo = obtenerIdentificadorUnico();
        this.nombre = nombre;
        this.correo = correo;
        this.contadorDeOperaciones = 0;
        this.historialDeOperaciones = new LinkedHashMap<>();
    }

    public String getCodigo() {
        return this.codigo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getCorreo() {
        return this.correo;
    }

    public int getContadorDeOperaciones() {
        return this.contadorDeOperaciones;
    }

    public LinkedHashMap<String, Operacion> getHistorialDeOperaciones() {
        return this.historialDeOperaciones;
    }

    private void incrementarContadorDeOperaciones() {
        this.contadorDeOperaciones++;
    }

    public void guardarOperacion(Operacion operacion) {
        this.historialDeOperaciones.put(operacion.getCodigo(), operacion);
        incrementarContadorDeOperaciones();
    }

    private String obtenerIdentificadorUnico() {
        String base = this.nombre + LocalDateTime.now().toString() + this.correo;
        return Integer.toHexString(base.hashCode());
    }
}