package com.alura.conversor;

import com.alura.conversor.modelos.ConversorMoneda;
import com.alura.conversor.modelos.Operacion;
import com.alura.conversor.modelos.Usuario;

public class Main {
    public static void main(String[] args) {
        ConversorMoneda programa = new ConversorMoneda();
        Usuario usuario = new Usuario("Nickross", "dixtanime@outlook.es");
        Operacion operacion = new Operacion("PEN", "USD", 100, 0.24, 240, usuario);
        System.out.println(operacion);
    }
}