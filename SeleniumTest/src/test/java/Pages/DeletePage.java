package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeletePage extends WebPage {

    @FindBy(id = "yui-gen1-button")
    private WebElement buttonYes;

    public DeletePage(WebDriver driver) {
        super(driver);
    }


    public void clickOnButtonYes() {
        buttonYes.click();
    }
}

