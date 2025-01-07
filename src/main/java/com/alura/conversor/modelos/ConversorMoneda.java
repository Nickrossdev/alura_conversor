package com.alura.conversor.modelos;

public class ConversorMoneda {



    
    public static String obtenerIdentificadorUnico(Object objeto) {
        int hashCode = objeto.hashCode();
        String hashHex = Integer.toHexString(hashCode);
        return hashHex.length() > 8 ? hashHex.substring(hashHex.length() - 8) : hashHex;
    }
}
