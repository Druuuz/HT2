package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ManageJenkinsPage extends WebPage {

    @FindBy(xpath = "//dt[contains(text(), 'Manage Users')]")
    private List<WebElement> dts;

    @FindBy(xpath = "//dd[contains(text(), 'Create/delete/modify users that can log in to this Jenkins')]")
    private List<WebElement> dds;

    @FindBy(xpath = "//a[@href = 'securityRealm/']")
    private WebElement manageUsersLink;

    public ManageJenkinsPage(WebDriver driver) {
        super(driver);
    }

    public void clickManageUsers() {
        manageUsersLink.click();
    }

    public boolean tagDtWithTextExists() {
        return isExist(dts);
    }

    public boolean tagDdWithTextExists() {
        return isExist(dds);
    }
}
