package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MainJenkinsPage extends WebPage {

    @FindBy(linkText = "Manage Jenkins")
    private WebElement manageJenkinsLink;


    public MainJenkinsPage(WebDriver driver) {
        super(driver);
    }

    public void clickManageJenkins() {
        click(manageJenkinsLink);
    }

    public boolean isLoggedIn(){
        return existsText("Welcome to Jenkins!");
    }

}
