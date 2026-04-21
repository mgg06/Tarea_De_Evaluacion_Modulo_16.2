package tests;

// Anotaciones: Palabras que empiezan por @

// @BeforeEach (antes de cada): Instrucciones para preparar todo (abrir Chrome, minimizar, ir a la
// web). Se ejecuta automáticamente antes de cada prueba para que cada prueba parta de un
// navegador limpio

// @AfterEach (después de cada): Instrucciones para limpiar (cerrar Chrome)

// Herramientas necesarias de Selenium y JUnit
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

// Aquí escribiremos los casos de prueba relaccionados exclusivamente con entrar a la web

public class LoginTest {

    private WebDriver driver; // Nuestro navegador Chrome
    private LoginPage loginPage; // El objeto que representa nuestra página login

    // PREPARACIÓN

    @BeforeEach
    public void prepararTest(){
        // 1. Abre un navegador Chrome vacío
        driver = new ChromeDriver();

        // 2. Lo pone en pantalla completa
        driver.manage().window().maximize();

        // 3. Le da unos segundos de paciencia (5) por si la web tarda en cargar
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // 4. Navega a la dirección web
        driver.get("https://www.saucedemo.com/");

        // 5. Inicializa la página pasándole el navegador que acabamos de abrir
        loginPage = new LoginPage(driver);
    }

    // LIMPIEZA

    @AfterEach
    public void limpiarTest(){

        // Le dice que si el navegador sigue abierto que lo cierre
        // Así no colapsamos el ordenador con diez mil ventanas de Chrome fantasmas

        if(driver != null){
            driver.quit();
        }
    }

    // CASO DE PRUEBA 1 - loginCorrecto

    @Test
    public void loginCorrecto(){

        // 1. Acciones

        // Usamos el método que creamos antes con las credenciales que se dan en la actividad
        loginPage.realizarLoginCompleto("standard_user", "secret_sauce");

        // 2. Comprobación (Aserción)

        // Para comprobar que el URL contiene inventory, le pedimos al driver que nos diga en
        // qué URL estamos ahora mismo
        String urlActual = driver.getCurrentUrl();

        // Usamos assertTrue (afirma que esto es verdad)
        // Si la url actual, contiene "Inventory" es verdadero, el test pasa (verde)
        // Si es falso, el test falla (rojo) y muestra el mensaje que hemos escrito al final
        assertTrue(urlActual.contains("inventory"), "Error: La URL después de entrar no contiene inventory");

    }

    // CASO DE PRUEBA 2: loginIncorrecto

    @Test
    public void loginIncorrecto(){

        // 1. Acciones

        // Intentamos entrar con el usuario correcto pero una contraseña inventada
        loginPage.realizarLoginCompleto("standard_user", "contraseñaIncorrecta");

        // 2. Comprobación (Aserción)

        // Se pide en la actividad que comprobemos que aparece un mensaje de error
        // Así que obtenemos el texto rojo usando el método que creamos en el LoginPage
        String textoDeErrorObtenido = loginPage.obtenerMensajeDeError();

        // Comprobamos que el texto que hemos "atrapado" contenga la frase de error que da la web
        assertTrue(textoDeErrorObtenido.contains("Username and password do not match"),
                "Error: No ha aparecido el mensaje indicando que las contraseñas no coinciden");

    }

    // RESUMEN: El test dice "Realiza el login completo, coge la URL actual y comprueba que
    // contenga inventory"

}
