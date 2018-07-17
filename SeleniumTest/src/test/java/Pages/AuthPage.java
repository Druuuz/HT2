package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthPage extends WebPage {

    @FindBy(id = "j_username")
    private WebElement userField;

    @FindBy(name = "j_password")
    private WebElement passField;

    @FindBy(id = "yui-gen1-button")
    private WebElement authButton;

    public AuthPage(WebDriver driver) {
        super(driver);
    }

    public void auth(String username, String password) {
        setUsername(username);
        setPassword(password);
        click(authButton);
    }

    private void setUsername(String value) {
        userField.clear();
        userField.sendKeys(value);
    }

    private void setPassword(String value) {
        passField.clear();
        passField.sendKeys(value);
    }
}
