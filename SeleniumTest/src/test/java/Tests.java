import Pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Tests {

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

    @Test
    public void testAuth() {
        driver.get(base_url);
        AuthPage authPage = PageFactory.initElements(driver, AuthPage.class);
        authPage.auth("user", "password");
        MainJenkinsPage mainJenkinsPage = PageFactory.initElements(driver, MainJenkinsPage.class);

        Assert.assertTrue(mainJenkinsPage.isLoggedIn(),"Not logged in");
    }

    @Test(dependsOnMethods = "testAuth")
    public void testManageJenkins() {
        MainJenkinsPage mainJenkinsPage = PageFactory.initElements(driver, MainJenkinsPage.class);
        ManageJenkinsPage manageJenkinsPage = PageFactory.initElements(driver, ManageJenkinsPage.class);

        mainJenkinsPage.clickManageJenkins();
        Assert.assertTrue(manageJenkinsPage.tagDdWithTextExists(), "Tag dd doesn't contain text");
        Assert.assertTrue(manageJenkinsPage.tagDtWithTextExists(), "Tag dt doesn't contain text");
    }

    @Test(dependsOnMethods = "testManageJenkins")
    public void testManageUsers() {
        ManageJenkinsPage manageJenkinsPage = PageFactory.initElements(driver, ManageJenkinsPage.class);
        ManageUsersPage manageUsersPage = PageFactory.initElements(driver, ManageUsersPage.class);

        manageJenkinsPage.clickManageUsers();
        Assert.assertTrue(manageUsersPage.createUserLinkEnabled(), "User Link isn't enabled");
    }

    @Test(dependsOnMethods = "testManageUsers")
    public void testCreateUser() {
        ManageUsersPage manageUsersPage = PageFactory.initElements(driver, ManageUsersPage.class);
        CreateUserPage createUserPage = PageFactory.initElements(driver, CreateUserPage.class);

        manageUsersPage.clickCreateUser();
        Assert.assertTrue(createUserPage.formExists(), "Form does not exist");
    }

    @Test(dependsOnMethods = "testCreateUser")
    public void testExistingCreatedUser() {
        ManageUsersPage manageUsersPage = PageFactory.initElements(driver, ManageUsersPage.class);
        CreateUserPage createUserPage = PageFactory.initElements(driver, CreateUserPage.class);

        createUserPage.submitFieldForm("someuser", "somepassword", "somepassword", "Some Full Name", "some@addr.com");
        Assert.assertTrue(manageUsersPage.userCreated(), "Created user isn't exist");
    }

    @Test(dependsOnMethods = "testExistingCreatedUser")
    public void testDeletingMessage() {
        ManageUsersPage manageUsersPage = PageFactory.initElements(driver, ManageUsersPage.class);
        DeletePage deletePage = PageFactory.initElements(driver, DeletePage.class);

        manageUsersPage.clickOnDeleteUserLink();
        Assert.assertTrue(deletePage.existsText("Are you sure about deleting the user from Jenkins?"), "Text does not exist on page");
    }

    @Test(dependsOnMethods = "testDeletingMessage")
    public void testDeletedUser() {
        DeletePage deletePage = PageFactory.initElements(driver, DeletePage.class);
        ManageUsersPage manageUsersPage = PageFactory.initElements(driver, ManageUsersPage.class);

        deletePage.clickOnButtonYes();
        Assert.assertFalse(manageUsersPage.userCreated(), "User wasn't delete");
        Assert.assertFalse(manageUsersPage.adminDeleteLinkExists(), "Admin delete link exists");
    }

    @Test(dependsOnMethods = "testDeletedUser")
    public void testNotExistingLink() {
        ManageUsersPage manageUsersPage = PageFactory.initElements(driver, ManageUsersPage.class);
        Assert.assertFalse(manageUsersPage.someUserDeleteLinkExists(), "User delete link exists");
    }


}
