package tests;

// Importamos nuestras dos páginas
import pages.InventoryPage;
import pages.LoginPage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

// Importamos las aserciones
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Aquí se escriben los casos de prueba relaccionados con comprar
public class InventoryTest {

    private WebDriver driver; // Explicado anteriormente

    // En este test necesitamos interactuar con dos páginas distintas
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    // 1. Preparación (@BeforeEach)

    // Explicado anteriormente en LoginTest
    @BeforeEach
    public void prepararTest(){

        driver = new ChromeDriver(); // Abrimos Chrome
        driver.manage().window().maximize(); // Maximizar pantalla
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // Esperar
        driver.get("https://www.saucedemo.com/"); // Vamos a la web

        // Incializamos las dos páginas dándoles el mando (driver)
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);

    }

    // 2. Limpieza (@AfterEach)

    @AfterEach
    public void limpiarTest(){
        if(driver != null){
            driver.quit(); // Cerramos Chrome al terminar cada prueba
        }
    }

    // 3. Métodos de prueba

    // Método para probar añadir un producto
    @Test
    public void anadirUnProductoAlCarrito(){

        // 1. Iniciar sesión correctamente (usando la LoginPage)
        loginPage.realizarLoginCompleto("standard_user", "secret_sauce");

        // 2. Añade un producto al carrito (usando la InventoryPage)
        inventoryPage.anadirMochilaAlCarrito();

        // 3. Comprueba que el contador del carrito muestra 1
        int cantidadEnCarrito = inventoryPage.obtenerNumeroProductosCarrito();

        // De nuevo usamos assertEquals (esperamos que haya 1)
        assertEquals(1, cantidadEnCarrito, "Error: El contador del carrito no muestra 1 producto");
    }

    // Método para probar a añadir dos productos
    @Test
    public void anadirDosProductosAlCarrito() {

        // 1. Iniciar sesión correctamente
        loginPage.realizarLoginCompleto("standard_user", "secret_sauce");

        // 2. Añadir dos productos al carrito
        inventoryPage.anadirMochilaAlCarrito();
        inventoryPage.anadirLuzAlCarrito(); // Aquí añadimos el segundo

        // 3. Comprueba que el contador del carrito muestra 2
        int cantidadEnCarrito = inventoryPage.obtenerNumeroProductosCarrito();
        assertEquals(2, cantidadEnCarrito, "Error: El contador del carrito no muestra 2 productos.");
    }

    // Método para probar que el botón cambie a "remove"
    @Test
    public void botonCambiaTrasAnadirProducto(){

        // 1. Iniciar sesión correctamente
        loginPage.realizarLoginCompleto("standard_user","secret_sauce");

        // 2. Añade un producto al carrito
        inventoryPage.anadirMochilaAlCarrito();

        //3. Comprueba que el botón cambia a "remove"

        // El método "botónRemoveApareceEnPantalla()" devuelve verdadero o falso
        // Así que usamos assertTrue para exigir que sea verdadero
        boolean apareceBotonRemove = inventoryPage.botonRemoveApareceEnPantalla();

        assertTrue(apareceBotonRemove,"Error: El botón rojo para eliminar no ha aparecido tras añadir el producto");

    }

    @Test
    public void pruebaAdicional_verificarProductoDentroDelCarrito() {

        // 1. Iniciamos sesión y añadimos la mochila

        loginPage.realizarLoginCompleto("standard_user", "secret_sauce");
        inventoryPage.anadirMochilaAlCarrito();

        // 2. Hacemos clic en el icono para entrar a la pantalla del carrito

        inventoryPage.hacerClicEnElCarrito();

        // 3. Obtenemos el nombre del producto que aparece en el carrito

        String nombreProducto = inventoryPage.obtenerNombreProductoEnCarrito();

        // 4. Comprobamos que el nombre es exactamente el de la mochila

        assertEquals("Sauce Labs Backpack", nombreProducto,
                "Error: El producto dentro del carrito no es la mochila esperada.");
    }


}
