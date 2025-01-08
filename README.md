# Conversor de Moneda

Este proyecto es un conversor de moneda que utiliza la API de Exchange Rate para obtener las tasas de conversión actuales. Permite convertir entre diferentes monedas de manera rápida y precisa. Este proyecto es parte de un challenge de Alura.

## Descripción

El conversor de moneda es una herramienta útil para viajeros, comerciantes y cualquier persona que necesite conocer el valor de una moneda en términos de otra. La aplicación se conecta a la API de Exchange Rate para obtener las tasas de conversión más recientes y realiza los cálculos necesarios para mostrar el valor convertido.

## Funcionalidades

- **Conversión de Monedas**: Convierte entre diferentes monedas utilizando las tasas de conversión actuales.
- **Historial de Usuario**: Mantiene un registro de las conversiones realizadas por el usuario.
- **Operaciones Top**: Muestra una lista de las conversiones más frecuentes o importantes realizadas por el usuario.

## Requisitos

- Java 11 o superior
- Maven
- Conexión a internet para acceder a la API de Exchange Rate

## Instalación

1. Clona este repositorio:
    ```sh
    git clone https://github.com/tu_usuario/alura_conversor.git
    ```
2. Navega al directorio del proyecto:
    ```sh
    cd alura_conversor
    ```
3. Compila el proyecto usando Maven:
    ```sh
    mvn clean install
    ```

## Uso

1. Ejecuta la aplicación:
    ```sh
    mvn exec:java -Dexec.mainClass="com.alura.conversor.Main"
    ```
2. Sigue las instrucciones en pantalla para convertir entre diferentes monedas.
3. 
## Dependencias

- [Gson](https://github.com/google/gson) - Para el manejo de JSON
- [HttpClient](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpClient.html) - Para realizar solicitudes HTTP

## API Utilizada

Este proyecto utiliza la [Exchange Rate API](https://www.exchangerate-api.com/) para obtener las tasas de conversión actuales. Asegúrate de tener una clave de API válida y configurarla en el proyecto.

## Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue o un pull request para discutir cualquier cambio.
