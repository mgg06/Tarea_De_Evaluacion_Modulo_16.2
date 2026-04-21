<h1 align="center">🌸🎀 Automatización de Pruebas con Selenium y JUnit 5 - SauceDemo 🎀🌸</h1>

<p align="center">
  <strong>🌷 Autora:</strong> Marta González González<br>
  <strong>🌷 Proyecto:</strong> Tarea de Evaluación - Módulo 16.2
</p>

<br>

## 💖 📝 Descripción del Proyecto

Este proyecto consiste en la automatización exhaustiva de un conjunto de pruebas funcionales para la aplicación web de demostración **[SauceDemo](https://www.saucedemo.com/)**. El objetivo principal de esta tarea es simular y validar el comportamiento de un usuario real al interactuar con la plataforma: desde el inicio de sesión con diferentes credenciales, hasta la navegación por el catálogo y la gestión dinámica del carrito de compras.

Para el desarrollo arquitectónico de esta tarea, he implementado estrictamente el patrón de diseño **Page Object Model (POM)**. Este patrón garantiza un código altamente mantenible, modular y escalable, ya que separa por completo la lógica de las pruebas (las aserciones y los pasos) de la definición física de la interfaz de usuario (los localizadores web). Si la página web cambia en el futuro, solo tendré que actualizar las clases "Page", manteniendo los "Tests" intactos. ✨

---

## 🛍️ 🏗️ Estructura del Código y Arquitectura

El código fuente está dividido lógicamente en dos paquetes principales que se comunican entre sí: el paquete de páginas (`pages`) y el paquete de pruebas (`tests`).

### 💻 Clases de Interfaz (Pages)

> Estas clases actúan como el "mapa" o "diccionario" de la página web. Selenium las utiliza para saber exactamente dónde y cómo hacer clic o escribir.

🎀 **`LoginPage.java`**
Esta clase representa la pantalla inicial de inicio de sesión de SauceDemo.
*   **Localizadores:** He priorizado el uso de `By.id` por ser la estrategia más rápida, estable y segura en Selenium para encontrar el usuario y contraseña. Para el mensaje de error, como carece de ID estático, he empleado `By.cssSelector("[data-test='error']")` para asegurar una localización precisa.
*   **Métodos:** Proporciona métodos de bajo nivel para interactuar atómicamente con los elementos (`escribirUsuario()`, `pulsarBotonAcceso()`) y, lo más importante, un método consolidado de alto nivel (`realizarLoginCompleto()`) que encapsula el flujo de entrada de una sola vez, optimizando enormemente la limpieza visual de las clases de test.

🎀 **`InventoryPage.java`**
Representa el catálogo de productos (inventory) al que se accede tras un login exitoso.
*   **Localizadores:** Mapea botones dinámicos. Por ejemplo, define tanto el botón de "Add to cart" de la mochila como su estado posterior de "Remove", permitiendo validar cambios visuales. También mapea el globo contador del carrito usando `By.className`.
*   **Métodos Lógicos:** Incluye métodos avanzados como `obtenerNumeroProductosCarrito()`, el cual no solo lee el DOM (HTML), sino que captura el texto (`String`) y lo transforma internamente a un número entero (`Integer.parseInt()`). Esto permite que en el test se puedan hacer aserciones matemáticas estrictas sobre la cantidad de productos reales.

### 🧪 Clases de Prueba (Tests)

> Aquí es donde ocurre la magia. Estas clases envían instrucciones a las Pages y comprueban (assert) que el resultado sea el correcto. Todas las pruebas usan `Duration.ofSeconds(5)` como espera implícita para asegurar que la página cargue antes de interactuar.

🌷 **`LoginTest.java`**
Clase encargada de validar el flujo de seguridad y autenticación.
*   **Gestión de Ciclo de Vida:** Utiliza fervientemente las anotaciones de JUnit 5: `@BeforeEach` para levantar un navegador de Chrome limpio antes de cada prueba y `@AfterEach` para invocar el método `.quit()`, evitando procesos zombis en el ordenador.
*   **Tests:**
    *   `loginCorrecto`: Confirma que, tras introducir credenciales válidas, el sistema redirige al usuario validando que `driver.getCurrentUrl()` contiene "inventory".
    *   `loginIncorrecto`: Valida el manejo de errores capturando el texto de advertencia y usando un `assertTrue` para asegurar que las contraseñas no coinciden.

🌷 **`InventoryTest.java`**
Batería de pruebas enfocada en el *Customer Journey* (el viaje de compra del usuario).
*   **Tests:**
    *   `anadirUnProductoAlCarrito`: Agrega un ítem y exige con `assertEquals` que el contador valga exactamente `1`.
    *   `anadirDosProductosAlCarrito`: Ejecuta flujos múltiples, añadiendo la mochila y la luz de bicicleta, para verificar que el contador asciende a `2`.
    *   `botonCambiaTrasAnadirProducto`: Prueba esencial de experiencia de usuario (UX). Verifica que el botón de añadir se transforma en un botón rojo de borrar usando `isDisplayed()` y `assertTrue()`.
    *   `pruebaAdicional_verificarProductoDentroDelCarrito`: Prueba de **ampliación (bonus)** que navega a través de las páginas. Tras añadir el producto, hace clic en el carrito, extrae el texto de la etiqueta del ítem añadido y valida mediante `assertEquals` que es literalmente "Sauce Labs Backpack".

---

## 📸 ✨ Capturas de Tests Validados

*En este apartado se adjuntan las evidencias de la ejecución exitosa de las baterías de pruebas en el IDE.*

<!-- Sustituye las siguientes rutas por las de tus capturas reales -->
<p align="center">
  <img src="URL_DE_TU_CAPTURA_LOGIN_TEST" alt="Login Tests Pasados" width="600">
  <br>
  <em>Figura 1: Ejecución exitosa y en verde de la batería de pruebas de <code>LoginTest</code>.</em>
</p>

<p align="center">
  <img src="URL_DE_TU_CAPTURA_INVENTORY_TEST" alt="Inventory Tests Pasados" width="600">
  <br>
  <em>Figura 2: Ejecución exitosa de <code>InventoryTest</code>, incluyendo la prueba adicional de ampliación.</em>
</p>

---

## 💡 🧠 Apreciación y Reflexión Personal

> 🌸 **Reflexión como alumna:**
>
> La realización de esta tarea me ha permitido comprender en la práctica la enorme importancia y utilidad real del patrón de diseño **Page Object Model (POM)**. Cuando empecé a programar automatizaciones, tendía a mezclarlo todo en un solo archivo. Ahora veo la luz: separar claramente las responsabilidades entre las clases que mapean la interfaz web (`Pages`) y las clases que ejecutan las pruebas lógicas (`Tests`) hace que todo mi código sea inmensamente más profesional, limpio, legible y, sobre todo, fácil de mantener a largo plazo. Si mañana el equipo de desarrollo de SauceDemo decide cambiar la estructura de su web, entiendo perfectamente que solo tendría que actualizar el archivo Page correspondiente y todos mis Tests seguirían funcionando sin tener que reescribirlos.
>
> Trabajar codo a codo con **Selenium WebDriver** y **JUnit 5** me ha proporcionado una base espectacular. Al principio me parecía un desafío encontrar los selectores, pero he aprendido a utilizar y priorizar estrategias eficientes: buscar primero un identificador único con `By.id`, y, si no existe, apoyarme en `By.cssSelector` o `By.className` para asegurar la máxima estabilidad en la prueba. La implementación de las anotaciones `@BeforeEach` y `@AfterEach` ha sido un descubrimiento vital para mí; es una práctica excelente para evitar "ventanas fantasma" en Chrome y garantizar que cada prueba empiece con el entorno y la memoria completamente en limpio, asegurando así resultados veraces.
>
> Finalmente, aprender a aplicar correctamente las aserciones como `assertTrue` y `assertEquals` me ha enseñado que probar no es solo hacer que un robot haga clics, sino exigir explícitamente a la aplicación que demuestre que está en el estado correcto. Ver cómo mi código toma el control del navegador, navega a velocidad de la luz, rellena formularios, procesa datos numéricos extraídos del carrito de compras y, finalmente, me devuelve todo el árbol de JUnit en verde (`Passed`), es algo sumamente satisfactorio. Definitivamente, considero que esta tarea ha sido un ejercicio muy realista y que me deja mucho más preparada para afrontar los entornos de calidad (QA) y Testing en el sector laboral real. 💖🚀

---

## 🎀 ⚙️ Tecnologías y Dependencias Utilizadas

*(Toda la configuración y dependencias están gestionadas en el archivo `pom.xml`)*

<p align="center">
  <img src="https://img.shields.io/badge/Java-25-ff69b4?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 25">
  <img src="https://img.shields.io/badge/Maven-ff69b4?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven">
  <img src="https://img.shields.io/badge/Selenium-4.42.0-ff69b4?style=for-the-badge&logo=selenium&logoColor=white" alt="Selenium">
  <img src="https://img.shields.io/badge/JUnit5-5.10.1-ff69b4?style=for-the-badge&logo=junit5&logoColor=white" alt="JUnit 5">
</p>

*   💖 **Java 25**: Lenguaje de programación principal sobre el que se ha construido toda la lógica.
*   💖 **Maven**: Sistema de construcción y gestor de las dependencias externas del proyecto.
*   💖 **Selenium WebDriver (v. 4.42.0)**: Librería principal encargada de levantar y controlar de manera autónoma el navegador web.
*   💖 **WebDriverManager (v. 6.1.0)**: Librería de Bonigarcia que facilita la inyección automática del binario correcto del navegador, sin tener que descargar *chromedriver.exe* manualmente.
*   💖 **JUnit Jupiter (v. 5.10.1)**: Framework sobre el que orquestamos la ejecución ordenada de las pruebas y las validaciones/aserciones.