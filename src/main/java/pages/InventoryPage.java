package pages;

// Siguiendo la lógica del patrón Page Object Model (POM), no podemos mezclar los botones del
// inventory con los del login. Por eso tenemos que crear el InventoryPage

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// Esta clase representa la pantalla que aparece después de hacer login (el escaparate de
// productos) Aquí guardaremos cómo encontrar los botones "add to cart" y el icono del carrito

public class InventoryPage {

    // 1. El webDriver (explicado en el LoginPage)
    private WebDriver driver;

    // 2. Los localizadores

    // Aquí mapeamos los botones de los dos productos que se mencionan en la actividad
    // Usamos By.id porque los creadores de la web les pusieron IDs muy claros
    private By botonAnadirMochila = By.id("add-to-cart-sauce-labs-backpack");
    private By botonAnadirLuz = By.id("add-to-cart-sauce-labs-bike-light");

    // Cuando se añade la mochila, el botón cambia de ID y se convierte en el botón "Remove"
    // Necesitamos "mapearlo" para poder comprobar después si ha aparecido
    private By botonQuitarMochila = By.id("remove-sauce-labs-backpack");

    // Mapeamos el globo rojo que aparece sobre el carrito con el número de productos
    // Esta vez usamos By.className porque en el HTML está definido con un class
    private By iconoCarrito = By.className("shopping_cart_badge");

    // 3. El constructor

    public InventoryPage(WebDriver driver){
        this.driver = driver;
    }

    // 4. Métodos

    // Método para añadir un producto al carrito
    // Creamos un método para hacer clic en el botón de la mochila
    public void anadirMochilaAlCarrito(){
        driver.findElement(botonAnadirMochila).click();
    }

    // Método para hacer clic en el botón de la luz de bicicleta
    public void anadirLuzAlCarrito(){
        driver.findElement(botonAnadirLuz).click();
    }

    // Método para obtener el número de productos del carrito
    public int obtenerNumeroProductosCarrito(){

        // 1. Selenium lee la web y extrae el numero del carrito, pero lo extrae como si fuera
        // texto (un String), por ejemplo: "1"

        String textoDelNumero = driver.findElement(iconoCarrito).getText();

        // 2. Para que nuestro test pueda hacer matemáticas o comparar números, necesitamos
        // convertir ese texto en un número entero de verdad, para eso usamos Integer.parseInt()

        int numeroReal = Integer.parseInt(textoDelNumero);

        // 3. Devolvemos (con return) el número para que el test lo use
        return numeroReal;

    }

    // Método para comrpobar si un texto o botón esperado aparece en pantalla
    // Este método devuelve un valor booleano (verdadero o falso)
    public boolean botonRemoveApareceEnPantalla(){

        // Le pedimos a Selenium que encuentre el botón rojo de "Remove"
        // La intrucción ".isDisplayed()" es como si preguntara si se ve el botón en pantalla
        // Devuelve true si se ve, y si no devolverá false

        return driver.findElement(botonQuitarMochila).isDisplayed();
    }

    // Método para ir a la página del carrito haciendo clic en el icono
    public void hacerClicEnElCarrito() {
        driver.findElement(iconoCarrito).click();
    }

    // Método para leer el nombre del producto que está dentro del carrito
    public String obtenerNombreProductoEnCarrito() {

        // Encontramos el elemento que contiene el nombre del producto y sacamos su texto
        return driver.findElement(By.className("inventory_item_name")).getText();
    }


}
