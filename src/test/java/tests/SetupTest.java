package tests;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageObjects.*;
import utils.Browser;
import utils.Utils;

import static org.junit.Assert.*;

public class SetupTest extends BaseTests{
    @Test
    public void testOpeningBrowserAndLoadingPage(){
        assertTrue(Browser.getCurrentDriver().getCurrentUrl().contains(Utils.getBaseUrl()));
        System.out.println("Abrimos o navegador e carregamos a url!");
    }

    @Test
    public void testLogin(){
        //Iniciar as páginas
        HomePage home = new HomePage();
        LoginPage login = new LoginPage();

        home.clickBtnLogin();
        System.out.println("clicou em sign In e direcionou para a página de login");
        assertTrue(Browser.getCurrentDriver().getCurrentUrl()
                .contains(Utils.getBaseUrl().concat("index.php?controller=authentication&back=my-account")));

        login.fillEmail();
        System.out.println("Preencheu o email");
        login.fillPasswd();
        System.out.println("Preencheu a senha");
        login.clickBtnSubmitLogin();
        System.out.println("clicou em sign in");
        assertTrue(Browser.getCurrentDriver().getCurrentUrl()
                .contains(Utils.getBaseUrl().concat("index.php?controller=my-account")));
        System.out.println("Validou a url da minha conta");
        assertTrue(Browser.getCurrentDriver().findElement(By.className("page-heading")).getText().contains("MY ACCOUNT"));
        System.out.println("Validou a minha conta no site");

        //      Browser.getCurrentDriver().findElement(By.className("login")).click();
        //System.out.println("clicou em sign In e direcionou para a página de login");
        // assertTrue(Browser.getCurrentDriver().getCurrentUrl()
        // .contains(Utils.getBaseUrl().concat("index.php?controller=authentication&back=my-account")));
        //  Browser.getCurrentDriver().findElement(By.id("email")).sendKeys("eamoreira.1@gmail.com");
        //   System.out.println("Preencheu o email");
//        Browser.getCurrentDriver().findElement(By.id("passwd")).sendKeys("12345");
//        System.out.println("Preencheu a senha");
//        Browser.getCurrentDriver().findElement(By.id("SubmitLogin")).click();
//        System.out.println("clicou em sign in");
//        assertTrue(Browser.getCurrentDriver().getCurrentUrl().contains(Utils.getBaseUrl().concat("index.php?controller=my-account")));
//        System.out.println("Validou a url da minha conta");
        assertTrue(Browser.getCurrentDriver().findElement(By.className("page-heading")).getText().contains("MY ACCOUNT"));
        System.out.println("Validou a minha conta no site");

    }

    @Test
    public void testSearch() {

        String quest = "\"DRESS\"";
        String qestResultQtd = "7";

        //Iniciar pagina
        HomePage home = new HomePage();
        SearchPage search = new SearchPage();

        //Fazer a pesquisa
        home.doSearch("dress");

        //Validar a pesquisa
        assertTrue(search.isSearchPage());
        assertEquals(search.getTextLighter(), quest);
        assertThat(search.getTextHeading_counter(), CoreMatchers.containsString(qestResultQtd));
    }

    @Test
    public void testAcessCategoryTShirts() {
        //Iniciar as paginas

        HomePage home = new HomePage();

        CategoryPage category = new CategoryPage();

        //Clicar na categoria TShirt
        home.clickCategoriaTShirts();

        //valida se ao clicar na categoria T_SHIRTS ocorre o direcionamento correto
        assertTrue(category.isPageTShirts());
    }

    @Test
    public void testAddProductToProductPage(){
        //Acessar a categoria T-shirts
        testAcessCategoryTShirts();

        //Iniciar as páginas
        CategoryPage category = new CategoryPage();
        ProductPage product = new ProductPage();

        //Salva nome do produto na pagina de categoria
        String nameProductCategory = category.getProductNameCategory();

        //Clicar em More e direcionar para a pagina do produto
        category.clickProductAddToProductPage();

        //Verificar se produto estah na pagina de detalhes do produto corretamente
        assertTrue(product.getProductNamePDP().equals(nameProductCategory));
    }

    @Test
    public void testAddProductToCartPage() {
        // Acessar a pagina de produto
        testAddProductToProductPage();

        //Iniciar a pagina
        ProductPage product = new ProductPage();
        CartPage cart = new CartPage();

        //Salvar o nome do produto na pagina PDP
        String nameProductPDP = product.getProductNamePDP();

        //Clicar no botao de Adicionar ao carrinho
        product.clickButtonAddToCart();


        //Clicar no botao proceed da checkout
        product.clickButtonModalProceedToCheckout();

        assertTrue(cart.getNameProductCart().equals(nameProductPDP));

    }
}
