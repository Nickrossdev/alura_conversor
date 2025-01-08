package com.alura.conversor.modelos;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ConversorMoneda {
    private final Scanner scanner = new Scanner(System.in);
    private static final String API_KEY = "498d1eac3560c538052b5528";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY;
    private Usuario usuario;
    private String isoDivisaOrigen;
    private String isoDivisaDestino;
    private double monto;
    private double tasa;
    private double resultado;

    private LinkedHashMap<String, String> divisasDisponibles;
    private ArrayList<OperacionCorta> operacionesFrecuentes;
    private HashMap<String, Divisa> divisas;

    public ConversorMoneda() {
        this.divisasDisponibles = new LinkedHashMap<>();
        this.operacionesFrecuentes = new ArrayList<>();
        this.divisas = new HashMap<>();
    }

    public void iniciar(Usuario usuario) {
        this.usuario = usuario;
        mostrarSaludo();
        cargarDivisasDisponibles();

        while (true) {
            mostrarMenuInicial();
            int opcion = obtenerEntradaOpcion();
            switch (opcion) {
                case 1:
                    mostrarOperacionesFrecuentes();
                    break;
                case 2:
                    mostrarDivisasDisponibles();
                    break;
                case 3:
                    this.isoDivisaOrigen = obtenerEntradaCodigoIso("Origen");
                    this.isoDivisaDestino = obtenerEntradaCodigoIso("Destino");
                    cargarDivisas();
                    this.monto = obtenerEntradaMonto();
                    this.tasa = obtenerTasa();
                    this.resultado = this.monto * this.tasa;
                    System.out.println("1 " + this.isoDivisaOrigen + " -> " + this.tasa + " " + this.isoDivisaDestino);
                    System.out.println(this.monto + " " + this.isoDivisaOrigen + " -> " + this.resultado + " " + this.isoDivisaDestino);
                    procesarOperacion();
                    break;
                case 4:
                    this.usuario.getHistorialDeOperaciones();
                    break;
                case 5:
                    salirPrograma();
                    return;
                default:
                    break;
            }
        }
    }

    private Operacion crearOperacion(String isoDivisaOrigen, String isoDivisaDestino, double monto, double tasa,
            double resultado, Usuario usuario) {
        Operacion operacion = new Operacion(isoDivisaOrigen, isoDivisaDestino, monto, tasa, resultado, usuario);
        return operacion;
    }

    private OperacionCorta crearOperacionCorta(String isoDivisaOrigen, String isoDivisaDestino) {
        OperacionCorta operacionCorta = new OperacionCorta(isoDivisaOrigen, isoDivisaDestino);
        return operacionCorta;
    }

    private Divisa crearDivisa(String iso, String nombre, double tasa) {
        Divisa divisa = new Divisa(iso, nombre, tasa);
        return divisa;
    }

    private void procesarOperacion() {
        Operacion operacion = crearOperacion(this.isoDivisaOrigen, this.isoDivisaDestino, this.monto, this.tasa,
                this.resultado, this.usuario);
        OperacionCorta operacionCorta = crearOperacionCorta(this.isoDivisaOrigen, this.isoDivisaDestino);
        guardarOperacion(operacion);
        guardarOperacionCorta(operacionCorta);
    }

    private void guardarOperacion(Operacion operacion) {
        this.usuario.guardarOperacion(operacion);
    }

    private void guardarOperacionCorta(OperacionCorta operacionCorta) {
        boolean operacionCortaExistente = false;
        for (OperacionCorta opc : this.operacionesFrecuentes) {
            if (opc.getIsoDivisaOrigen().equals(operacionCorta.getIsoDivisaOrigen())
                    && opc.getIsoDivisaDestino().equals(operacionCorta.getIsoDivisaDestino())) {
                opc.incrementarContadorDeOperaciones();
                operacionCortaExistente = true;
                break;
            }
        }
        if (!operacionCortaExistente) {
            this.operacionesFrecuentes.add(operacionCorta);
        }
    }

    private void mostrarOperacionesFrecuentes() {
        int indice = 0;
        System.out.println("""
                RANKING - ORIGEN - DESTINO
                ----------------------------------------------""");
        for (OperacionCorta operacionCorta : this.operacionesFrecuentes) {
            String divisaIsoOrigen = operacionCorta.getIsoDivisaOrigen();
            String divisaIsoDestino = operacionCorta.getIsoDivisaDestino();
            System.out.println(String.format("%d. %s -> %s", indice, divisaIsoOrigen, divisaIsoDestino));
            indice++;
        }
    }

    private void cargarDivisasDisponibles() {
        String url = API_URL + "/codes";
        String json = conectarAPI(url);
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        JsonArray divisasAdmitidas = jsonObject.getAsJsonArray("supported_codes");

        for (JsonElement elemento : divisasAdmitidas) {
            JsonArray divisaArray = elemento.getAsJsonArray();
            String divisaIso = divisaArray.get(0).getAsString();
            String divisaNombre = divisaArray.get(1).getAsString();

            if (!this.divisasDisponibles.containsKey(divisaIso)) {
                this.divisasDisponibles.putIfAbsent(divisaIso, divisaNombre);
            }
        }
    }

    private void mostrarDivisasDisponibles() {
        int indice = 1;
        System.out.println("""
                N° - CODIGO - NOMBRE DE MONEDA
                ----------------------------------------------""");
        if (!divisasDisponibles.isEmpty()) {
            for (Map.Entry<String, String> entry : this.divisasDisponibles.entrySet()) {
                System.out.println(String.format("%d. %s - %s", indice, entry.getKey(), entry.getValue()));
                indice++;
            }
        } else {
            System.out.println("LinkedMap<String, String> divisasDisponibles esta vacía");
        }
        System.out.println("----------------------------------------------");
    }

    private void cargarDivisas() {
        String url = API_URL + "/latest/" + this.isoDivisaOrigen;
        String json = conectarAPI(url);

        Gson gson = new Gson();
        JsonObject object = gson.fromJson(json, JsonObject.class);
        JsonObject tasasConversion = object.getAsJsonObject("conversion_rates");

        for (String divisaIso : tasasConversion.keySet()) {
            JsonElement tasa = tasasConversion.get(divisaIso);
            double divisaTasa = tasa.getAsDouble();
            String divisaNombre = divisasDisponibles.get(divisaIso);
            Divisa divisa = crearDivisa(divisaIso, divisaNombre, divisaTasa);
            this.divisas.put(divisaIso, divisa);
        }

    }

    private String conectarAPI(String url) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(URI.create(url)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            e.getMessage();
            return null;
        }
    }

    private void mostrarSaludo() {
        System.out.println("""
                ----------------------------------------------
                Bienvenido a ExchangeRate Conversor de Divisas
                ----------------------------------------------""");
    }

    private void mostrarMenuInicial() {
        System.out.println("""
                Elija una opción
                ----------------------------------------------
                1. Conversiones Populares
                2. Divisas Disponibles
                3. Convertir
                4. Historial
                5. Salir
                ----------------------------------------------""");
    }

    private String obtenerEntradaCodigoIso(String tipo) {
        String iso = null;
        while (iso == null) {
            System.out.print("Ingrese el Codigo ISO de la moneda de " + tipo + " :");
            iso = leerLinea().toUpperCase();
            iso = validarEntradaCodigoIso(iso);
            if(iso == null){
                System.out.println("El código ingresado no es válido. Por favor, intenta de nuevo.");
            }
        }
        System.out.println("----------------------------------------------");
        return iso;
    }

    private String validarEntradaCodigoIso(String iso) {
        if (this.divisasDisponibles.containsKey(iso)) {
            return iso;
        }
        return null;
    }

    private double obtenerEntradaMonto(){
        double monto = 0;
        while (monto == 0) {
            System.out.println("Ingrese el monto a convertir (" + this.isoDivisaOrigen + " -> " + this.isoDivisaDestino + ")" );
            monto = validarEntradaMonto();
            if(monto == 0){
                System.out.println("Por favor, Intente denuevo");
            }
        }
        System.out.println("----------------------------------------------");
        return monto;
    }
    
    private double validarEntradaMonto(){
        try {
            double monto = Double.parseDouble(leerLinea());
            if (monto <= 0) {
                System.out.println("*Error: No puede ingresar valores 0 o negativos");
                return 0;
            } else {
                return monto;
            }
        } catch (NumberFormatException e) {
            System.out.println("*Error: Ingrese un monto numerico");
            return 0;
        }
    }

    private double obtenerTasa(){
        Divisa divisa = divisas.get(isoDivisaDestino);
        double tasa = divisa.getTasa();
        return tasa;
    }

    private int obtenerEntradaOpcion() {
        System.out.print("Opcion:");
        int opcion = validarEntradaOpcion();
        System.out.println("----------------------------------------------");
        return opcion;
    }

    private int validarEntradaOpcion() {
        try {
            int opcion = Integer.parseInt(leerLinea());
            if (opcion <= 0 || opcion >= 6) {
                System.out.println("*Error: Ingrese una opción correcta entre (1-5)");
                return 0;
            } else {
                return opcion;
            }
        } catch (NumberFormatException e) {
            System.out.println("*Error: Ingrese un opcion númerica existente (1-5)");
            return 0;
        }
    }

    private void salirPrograma() {
        System.out.println("Finalizando el programa...");
        System.exit(0);
    }

    private String leerLinea() {
        return scanner.nextLine();
    }
}
