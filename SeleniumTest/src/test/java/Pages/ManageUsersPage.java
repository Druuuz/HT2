package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class ManageUsersPage extends WebPage {

    @FindBy(linkText = "Create User")
    private WebElement createUserLink;

    @FindBy(xpath = "//tr/td/a[contains(text(),'someuser')]")
    private WebElement user;

    @FindBy(xpath = "//a[contains(@href,'user/someuser/delete')]")
    private WebElement someUserDeleteLink;

    @FindBy(xpath = "//a[contains(@href,'user/admin/delete')]")
    private WebElement adminDeleteLink;

    @FindBy(xpath = "//tr/td/a[contains(text(),'someuser')]")
    private List<WebElement> users;

    @FindBy(xpath = "//a[contains(@href,'user/someuser/delete')]")
    private List<WebElement> someUserDeleteLinks;

    @FindBy(xpath = "//a[contains(@href,'user/admin/delete')]")
    private List<WebElement> adminDeleteLinks;

    public ManageUsersPage(WebDriver driver) {
        super(driver);
    }

    public boolean createUserLinkEnabled() {
        return isEnabled(createUserLink);
    }

    public void clickCreateUser() {
        click(createUserLink);
    }


    public boolean userCreated() {
        return isExist(users);
    }

    public void clickOnDeleteUserLink() {
        click(someUserDeleteLink);
    }

    public boolean adminDeleteLinkExists() {
        return isExist(adminDeleteLinks);
    }

    public boolean someUserDeleteLinkExists() {
        return isExist(someUserDeleteLinks);
    }

}
