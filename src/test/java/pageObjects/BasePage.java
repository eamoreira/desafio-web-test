package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.Browser;

public class BasePage extends Browser {

    public static void mouserOver(WebElement element) {
        Actions action = new Actions(Browser.getCurrentDriver());
        action.moveToElement(element).build().perform();

    }

}
