package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public abstract class WebPage {

    protected WebDriverWait wait;
    protected final WebDriver driver;

    @FindBy(tagName = "body")
    protected WebElement body;

    public WebPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 30);
    }


    public boolean isExist(List<WebElement> element) {
        if (!element.isEmpty()) {
            return true;
        }
        return false;
    }

    public void click(WebElement element) {
        element.click();
    }

    public boolean isEnabled(WebElement element) {
        return element.isEnabled();
    }

    public boolean existsText(String text) {
        if (body.getText().contains(text)) {
            return true;
        }
        return false;
    }

}
