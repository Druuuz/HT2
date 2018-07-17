import Pages.AuthPage;
import Pages.IncorrectAuthPage;
import Pages.RefreshArea;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ExtendtionTests {

    String base_url = "http://localhost:8080/";
    StringBuffer verificationErrors = new StringBuffer();
    WebDriver driver = null;

    @BeforeClass
    public void beforeClass() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    /*
        При клике по ссылке с текстом «ENABLE AUTO REFRESH» эта ссылка пропадает,
        а вместо неё появляется ссылка с текстом «DISABLE AUTO REFRESH».
        При клике по ссылке с текстом «DISABLE AUTO REFRESH» эта ссылка пропадает,
        а вместо неё появляется ссылка с текстом «ENABLE AUTO REFRESH».
     */
    @Test
    public void testRefresh() {
        driver.get(base_url);
        AuthPage authPage = PageFactory.initElements(driver, AuthPage.class);
        authPage.auth("user", "password");
        RefreshArea refreshArea = PageFactory.initElements(driver, RefreshArea.class);
        Assert.assertTrue(refreshArea.autoRefreshLinkExists(), "AutoRefresh link does not exist");
        refreshArea.clickAutoRefreshLink();
        Assert.assertFalse(refreshArea.autoRefreshLinkExists(), "AutoRefresh link does not disappeared");
        Assert.assertTrue(refreshArea.disAutoRefreshLinkExists(), "DisAutoRefresh link does not exist");
        refreshArea.clickDisAutoRefreshLink();
        Assert.assertFalse(refreshArea.disAutoRefreshLinkExists(), "DisAutoRefresh link does not disappeared");
        Assert.assertTrue(refreshArea.autoRefreshLinkExists(), "AutoRefresh link does not exist");
    }

    /*
    	При попытке создать пользователя с пустым (незаполненным) именем
    	на странице появляется текст «"" is prohibited as a full name for security reasons.»
     */
    @Test
    public void testAuth() {
        driver.get(base_url);
        AuthPage authPage = PageFactory.initElements(driver, AuthPage.class);
        authPage.auth("", "password");
        IncorrectAuthPage incorrectAuthPage = PageFactory.initElements(driver, IncorrectAuthPage.class);
        Assert.assertTrue(incorrectAuthPage.existsText("is prohibited as a full name for security reasons."), "Page does not exist text");
    }

}
