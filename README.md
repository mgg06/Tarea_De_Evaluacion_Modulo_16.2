<div style="background-color: #FFF0F5; border: 2px solid #FFC0CB; border-radius: 15px; padding: 25px; font-family: 'Helvetica', sans-serif;">

<h1 align="center" style="color: #FF69B4;">🌸 Automatización de Pruebas con Selenium y JUnit 5 - SauceDemo 🌸</h1>

<p align="center">
  <strong style="color: #DB7093;">Autora:</strong> Marta González González<br>
  <strong style="color: #DB7093;">Proyecto:</strong> Tarea de Evaluación - Módulo 16.2
</p>

<hr style="border: 1px dashed #FFC0CB;">

<h2 style="color: #FF69B4;">📝 Descripción del Proyecto 📝</h2>

<p style="color: #4B0082;">Este proyecto consiste en la automatización de un conjunto de pruebas funcionales para la aplicación web de demostración <a href="https://www.saucedemo.com/" style="color: #DA70D6;">SauceDemo</a>. El objetivo principal es simular y validar el comportamiento de un usuario real al iniciar sesión y gestionar el carrito de compras, asegurando que la interfaz responde correctamente a diversas interacciones.</p>

<p style="color: #4B0082;">Para el desarrollo de esta tarea se ha implementado el patrón de diseño <strong>Page Object Model (POM)</strong>, garantizando así un código mantenible, modular y escalable, separando la lógica de las pruebas de la definición de la interfaz de usuario.</p>

<hr style="border: 1px dashed #FFC0CB;">

<h2 style="color: #FF69B4;">🏗️ Estructura del Código y Archivos 🏗️</h2>

<p style="color: #4B0082;">El código está dividido lógicamente en dos paquetes principales: las páginas (que modelan la interfaz web) y los tests (que ejecutan las acciones y comprobaciones).</p>

<div style="background-color: #FFFFFF; border: 1px solid #FFDAE9; border-radius: 10px; padding: 15px; margin-top: 15px;">
<h3 style="color: #DA70D6;">Clases de Interfaz (Pages) 💻</h3>

<ul>
    <li>
        <p><strong><code>LoginPage.java</code></strong></p>
        <p style="color: #4B0082;">Esta clase representa la pantalla inicial de inicio de sesión. Contiene los localizadores (<code>By.id</code>, <code>By.cssSelector</code>) necesarios para identificar el campo de usuario, el de contraseña, el botón de acceso y el mensaje de error. Proporciona métodos de bajo nivel para interactuar con estos elementos (<code>escribirUsuario</code>, <code>escribirPassword</code>, <code>pulsarBotonAcceso</code>) y un método de servicio de alto nivel (<code>realizarLoginCompleto</code>) que encapsula el flujo completo, optimizando la escritura de los tests.</p>
    </li>
    <li>
        <p><strong><code>InventoryPage.java</code></strong></p>
        <p style="color: #4B0082;">Representa el catálogo de productos al que se accede tras un login exitoso. Define los localizadores para interactuar con productos específicos, sus botones de "Add to cart" y "Remove", y el indicador del carrito. Incluye métodos para realizar acciones como añadir productos (<code>anadirMochilaAlCarrito</code>) y obtener datos de la UI, como el número de productos en el carrito (<code>obtenerNumeroProductosCarrito</code>) o verificar cambios de estado en los botones (<code>botonRemoveApareceEnPantalla</code>).</p>
    </li>
</ul>
</div>

<div style="background-color: #FFFFFF; border: 1px solid #FFDAE9; border-radius: 10px; padding: 15px; margin-top: 15px;">
<h3 style="color: #DA70D6;">Clases de Prueba (Tests) 🧪</h3>
<ul>
    <li>
        <p><strong><code>LoginTest.java</code></strong></p>
        <p style="color: #4B0082;">Clase encargada de validar el flujo de autenticación. Utiliza las anotaciones <code>@BeforeEach</code> para la configuración del entorno (instanciar WebDriver, navegar a la URL) y <code>@AfterEach</code> para el desmontaje (cerrar el navegador), asegurando la atomicidad de cada prueba. Contiene dos casos de prueba:</p>
        <ul>
            <li style="color: #4B0082;"><code>loginCorrecto</code>: Valida un acceso exitoso con credenciales válidas, comprobando mediante una aserción que la URL final contiene la subcadena "inventory".</li>
            <li style="color: #4B0082;"><code>loginIncorrecto</code>: Verifica el comportamiento del sistema ante credenciales erróneas, asegurando que se muestre el mensaje de error esperado.</li>
        </ul>
    </li>
    <li>
        <p><strong><code>InventoryTest.java</code></strong></p>
        <p style="color: #4B0082;">Contiene la batería de pruebas enfocadas en la interacción con el inventario y el carrito de compras. Cada test comienza con un inicio de sesión correcto para establecer el estado necesario.</p>
        <ul>
            <li style="color: #4B0082;"><code>anadirUnProductoAlCarrito</code>: Comprueba que el contador del carrito se actualiza a 1 tras añadir un ítem.</li>
            <li style="color: #4B0082;"><code>anadirDosProductosAlCarrito</code>: Verifica que el contador muestra 2 al agregar un segundo producto.</li>
            <li style="color: #4B0082;"><code>botonCambiaTrasAnadirProducto</code>: Valida que el estado del botón cambia de "Add to cart" a "Remove" después de la acción, usando <code>assertTrue</code> sobre un método que comprueba la visibilidad del nuevo botón.</li>
            <li style="color: #4B0082;"><code>pruebaAdicional_verificarProductoDentroDelCarrito</code>: Prueba de ampliación que navega hasta la página del carrito y afirma con <code>assertEquals</code> que el producto mostrado coincide con el que fue añadido.</li>
        </ul>
    </li>
</ul>
</div>

<hr style="border: 1px dashed #FFC0CB;">

<h2 style="color: #FF69B4;">📸 Capturas de Tests Validados 📸</h2>

<p style="color: #4B0082;"><i>En este apartado se adjuntan las evidencias de la ejecución exitosa de las baterías de pruebas.</i></p>

<!-- Sustituye las siguientes rutas por las de tus capturas reales -->
<p align="center">
  <img src="URL_DE_TU_CAPTURA_LOGIN_TEST" alt="Login Tests Pasados" width="600">
  <br>
  <em style="color: #DB7093;">Figura 1: Ejecución exitosa de la batería de pruebas de <code>LoginTest</code>.</em>
</p>

<p align="center">
  <img src="URL_DE_TU_CAPTURA_INVENTORY_TEST" alt="Inventory Tests Pasados" width="600">
  <br>
  <em style="color: #DB7093;">Figura 2: Ejecución exitosa de la batería de pruebas de <code>InventoryTest</code>.</em>
</p>

<hr style="border: 1px dashed #FFC0CB;">

<h2 style="color: #FF69B4;">💡 Apreciación y Reflexión Personal 💡</h2>

<div style="background-color: #FFFAFA; border-left: 5px solid #FFB6C1; padding: 15px;">
<p style="color: #4B0082;">La realización de esta tarea me ha permitido comprender en la práctica la importancia y utilidad del patrón de diseño <strong>Page Object Model (POM)</strong>. La separación clara de responsabilidades entre las clases que modelan las páginas y las clases que contienen las pruebas hace que el código sea mucho más legible, mantenible y reutilizable. Entiendo que, ante un cambio en la interfaz de usuario, solo necesitaría actualizar el localizador en la clase Page correspondiente, sin tener que modificar ninguna lógica de prueba.</p>
<p style="color: #4B0082;">El uso de <strong>Selenium WebDriver</strong> para la interacción con el navegador y <strong>JUnit 5</strong> para la estructuración y ejecución de las pruebas me ha proporcionado una base sólida en automatización. He aprendido a seleccionar estrategias de localización de elementos eficientes (priorizando `id` sobre otros selectores más frágiles) y a implementar flujos de usuario complejos de manera programática. Las anotaciones <code>@BeforeEach</code> y <code>@AfterEach</code> son fundamentales para garantizar que cada test se ejecute en un estado aislado y predecible, lo cual es una buena práctica esencial.</p>
<p style="color: #4B0082;">Finalmente, la aplicación de aserciones como <code>assertTrue</code> y <code>assertEquals</code> me ha enseñado a no solo ejecutar acciones, sino a validar de forma explícita que el estado de la aplicación es el esperado. Ver las pruebas ejecutarse de forma autónoma y obtener un reporte afirmativo me da confianza en la calidad del código y en las habilidades que estoy desarrollando. Considero que esta tarea ha sido un ejercicio muy completo y representativo de los desafíos que se presentan en un entorno de testing profesional.</p>
</div>

<hr style="border: 1px dashed #FFC0CB;">

<h2 style="color: #FF69B4;">⚙️ Tecnologías Utilizadas ⚙️</h2>
<ul style="list-style-type: '💖 '; color: #4B0082;">
    <li><strong>Java 25</strong> (Lenguaje de programación)</li>
    <li><strong>Maven</strong> (Gestor de dependencias y construcción del proyecto)</li>
    <li><strong>Selenium WebDriver (4.42.0)</strong></li>
    <li><strong>WebDriverManager (6.1.0)</strong> (Gestión automática de los binarios del driver)</li>
    <li><strong>JUnit 5 (5.10.1)</strong> (Framework para la ejecución de pruebas)</li>
</ul>

</div>