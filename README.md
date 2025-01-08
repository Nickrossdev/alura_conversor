# Conversor de Moneda

Este proyecto es un conversor de moneda que utiliza una API para obtener las tasas de conversión actuales.

## Requisitos

- Java 11 o superior
- Maven
- Conexión a internet para acceder a la API de tasas de conversión

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

## Estructura del Proyecto

-  - Código fuente principal
- `src/test/java/com/alura/conversor/` - Pruebas unitarias

## Dependencias

- [Gson](https://github.com/google/gson) - Para el manejo de JSON
- [HttpClient](https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpClient.html) - Para realizar solicitudes HTTP

## Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue o un pull request para discutir cualquier cambio.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.
