package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RefreshArea extends WebPage {

    @FindBy(linkText = "ENABLE AUTO REFRESH")
    private WebElement autoRefreshLink;

    @FindBy(linkText = "DISABLE AUTO REFRESH")
    private WebElement disAutoRefreshLink;

    @FindBy(linkText = "ENABLE AUTO REFRESH")
    private List<WebElement> autoRefreshLinks;

    @FindBy(linkText = "DISABLE AUTO REFRESH")
    private List<WebElement> disAutoRefreshLinks;

    public RefreshArea(WebDriver driver) {
        super(driver);
    }


    public void clickAutoRefreshLink() {
        click(autoRefreshLink);
    }

    public void clickDisAutoRefreshLink() {
        click(disAutoRefreshLink);
    }

    public boolean autoRefreshLinkExists() {
        return isExist(autoRefreshLinks);
    }

    public boolean disAutoRefreshLinkExists() {
        return isExist(disAutoRefreshLinks);
    }
}
