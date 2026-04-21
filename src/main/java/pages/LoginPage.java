package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Esta clase representa la pantalla principal de SauceDemo. Aquí guardamos cómo encontrar cajas
// de texto de "Username", "Password" y el botón de "Login", además de instrucciones para
// interactuar entre ellos

public class LoginPage {

    // Necesitamos una variable que represente a nuestro navegador (Chrome).
    // Lo llamamos "driver". Es como el mando con el que daremos las órdenes

    private WebDriver driver;

    // Aquí le estamos diciendo a Java cómo encontrar los elementos en la web
    // Usamos "By.id" porque es la forma más rápida y segura de encontrar algo

    private By campoUsuario = By.id("user-name"); // El campo para escribir el usuario
    private By campoPassword = By.id("password"); // El campo para la contraseña
    private By botonLogin = By.id("login-button"); // El botón de login

    // Para el mensaje de error, la web SauceDemo no usa un "id" simple
    // Usa un atributo llamado "data-test". Por eso usamos "cssSelector" para atraparlo

    private By mensajeError = By.cssSelector("[data-test='error']"); // Mensaje de error de la página

    // CONSTRUCTOR: Cuando desde nuestro Test llamemos a esta página, le pasaremos el navegador
    // que hemos abierto para que esta página pueda usarlo

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    // MÉTODOS

    // Escribir nombre de usuario
    public void escribirUsuario(String usuario){

        // Le decimos al navegador (driver) que encuentre el elemento (findElement) usando el
        // localizador "campoUsuario" y envíale este texto (sendKeys)

        driver.findElement(campoUsuario).sendKeys(usuario);

    }

    // Escribir la contraseña
    public void escribirPassword(String password){

        // Misma lógica que la explicada anteriormente

        driver.findElement(campoPassword).sendKeys(password);
    }

    // Pulsar el botón de acceso
    public void pulsarBotonAcceso(){

        // Le decimos que encuentre el botón y haga clic en él (click)

        driver.findElement(botonLogin).click();

    }

    // Realizar el login completo con un solo método

    // Este método llama a los tres anteriores en orden, esto nos ahorra mucho tiempo al escribir
    // los tests
    public void realizarLoginCompleto(String usuario, String password){
        escribirUsuario(usuario);
        escribirPassword(password);
        pulsarBotonAcceso();
    }

    // Método para el test de error, necesitamos poder "leer" el texto rojo que sale en la
    // pantalla para luego comprobarlo

    public String obtenerMensajeDeError(){

        // Encuentra el elemento del error, y en vez de hacer click extrae el texto (getText)

        return driver.findElement(mensajeError).getText();
    }

    }

