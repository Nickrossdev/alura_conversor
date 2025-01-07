package com.alura.conversor.modelos;

import java.util.LinkedHashMap;
import java.util.Map;

public class Usuario {
    private String codigo;
    private String nombre;
    private String correo;
    private int contadorDeOperaciones;
    private Map<String, Operacion> historialDeOperaciones;

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

    public Map<String, Operacion> getHistorialDeOperaciones() {
        return historialDeOperaciones;
    }

    private void incrementarContador() {
        this.contadorDeOperaciones++;
    }

    public void guardarOperacion(Operacion operacion) {
        this.historialDeOperaciones.put(operacion.getCodigo(), operacion);
        incrementarContador();
    }

    private String obtenerIdentificadorUnico() {
        int hashCode = this.hashCode();
        String hashHex = Integer.toHexString(hashCode);
        return hashHex.length() > 8 ? hashHex.substring(hashHex.length() - 8) : hashHex;
    }
}