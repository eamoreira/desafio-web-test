package pageObjects;

import elementMapper.CategoryPageElementMapper;
import org.openqa.selenium.support.PageFactory;
import utils.Browser;

public class CategoryPage extends CategoryPageElementMapper {

    public CategoryPage() {
        PageFactory.initElements(Browser.getCurrentDriver(),this);
    }

    public boolean isPageTShirts() {
        return getAutheticationPageTShirts().contains("T-SHIRTS");
    }

    public String getAutheticationPageTShirts() {
        return nameCategoryTShirt.getText();
    }

    public void clickProductAddToProductPage() {
        BasePage.mouserOver(productNameCategory);
        buttonMoreAddToProductPage.click();
    }

    public String getProductNameCategory() {
        return productNameCategory.getText();
    }
}
