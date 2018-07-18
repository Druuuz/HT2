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
   0.	После ввода авторизационных данных происходит переход на главную страницу сайта.
    */
    @Test
    public void testAuth() {
        driver.get(base_url);
        AuthPage authPage = PageFactory.initElements(driver, AuthPage.class);
        authPage.auth("user", "password");
        MainJenkinsPage mainJenkinsPage = PageFactory.initElements(driver, MainJenkinsPage.class);

        Assert.assertTrue(mainJenkinsPage.isLoggedIn(), "Not logged in");
    }

    /*
    1.	После клика по ссылке «Manage Jenkins» на странице появляется элемент dt с текстом «Manage Users»
        и элемент dd с текстом «Create/delete/modify users that can log in to this Jenkins».
     */
    @Test(dependsOnMethods = "testAuth")
    public void testManageJenkins() {
        MainJenkinsPage mainJenkinsPage = PageFactory.initElements(driver, MainJenkinsPage.class);
        ManageJenkinsPage manageJenkinsPage = PageFactory.initElements(driver, ManageJenkinsPage.class);

        mainJenkinsPage.clickManageJenkins();
        Assert.assertTrue(manageJenkinsPage.tagDdWithTextExists(), "Tag dd doesn't contain text");
        Assert.assertTrue(manageJenkinsPage.tagDtWithTextExists(), "Tag dt doesn't contain text");
    }

    /*
    2.	После клика по ссылке, внутри которой содержится элемент dt с текстом «Manage Users»,
        становится доступна ссылка «Create User».
     */
    @Test(dependsOnMethods = "testManageJenkins")
    public void testManageUsers() {
        ManageJenkinsPage manageJenkinsPage = PageFactory.initElements(driver, ManageJenkinsPage.class);
        ManageUsersPage manageUsersPage = PageFactory.initElements(driver, ManageUsersPage.class);

        manageJenkinsPage.clickManageUsers();
        Assert.assertTrue(manageUsersPage.createUserLinkEnabled(), "User Link isn't enabled");
    }

    /*
    3.	После клика по ссылке «Create User» появляется форма с тремя полями типа text и двумя полями типа password,
        причём все поля должны быть пустыми.
     */
    @Test(dependsOnMethods = "testManageUsers")
    public void testCreateUser() {
        ManageUsersPage manageUsersPage = PageFactory.initElements(driver, ManageUsersPage.class);
        CreateUserPage createUserPage = PageFactory.initElements(driver, CreateUserPage.class);

        manageUsersPage.clickCreateUser();
        Assert.assertTrue(createUserPage.formExists(), "Form does not exist");
    }

    /*
    4.	После заполнения полей формы («Username» = «someuser», «Password» = «somepassword»,
        «Confirm password» = «somepassword», «Full name» = «Some Full Name», «E-mail address» = «some@addr.dom») и
        клика по кнопке с надписью «Create User» на странице появляется строка таблицы (элемент tr),
         в которой есть ячейка (элемент td) с текстом «someuser».
     */
    @Test(dependsOnMethods = "testCreateUser")
    public void testExistingCreatedUser() {
        ManageUsersPage manageUsersPage = PageFactory.initElements(driver, ManageUsersPage.class);
        CreateUserPage createUserPage = PageFactory.initElements(driver, CreateUserPage.class);

        createUserPage.submitFieldForm("someuser", "somepassword", "somepassword", "Some Full Name", "some@addr.com");
        Assert.assertTrue(manageUsersPage.userCreated(), "Created user isn't exist");
    }


    /*
    5.	После клика по ссылке с атрибутом href равным «user/someuser/delete»
        появляется текст «Are you sure about deleting the user from Jenkins?».
     */
    @Test(dependsOnMethods = "testExistingCreatedUser")
    public void testDeletingMessage() {
        ManageUsersPage manageUsersPage = PageFactory.initElements(driver, ManageUsersPage.class);
        DeletePage deletePage = PageFactory.initElements(driver, DeletePage.class);

        manageUsersPage.clickOnDeleteUserLink();
        Assert.assertTrue(deletePage.existsText("Are you sure about deleting the user from Jenkins?"), "Text does not exist on page");
    }

    /*
    6.	После клика по кнопке с надписью «Yes» на странице отсутствует строка таблицы (элемент tr),
        с ячейкой (элемент td) с текстом «someuser».
        На странице отсутствует ссылка с атрибутом href равным «user/someuser/delete».
     */
    @Test(dependsOnMethods = "testDeletedUser")
    public void testNotExistLinks() {
        ManageUsersPage manageUsersPage = PageFactory.initElements(driver, ManageUsersPage.class);
        Assert.assertFalse(manageUsersPage.someUserDeleteLinkExists(), "User delete link exists");
    }

    /*
    7.	{На той же странице, без выполнения каких бы то ни было действий}.
        На странице отсутствует ссылка с атрибутом href равным «user/admin/delete».
     */
    @Test(dependsOnMethods = "testDeletingMessage")
    public void testDeletedUser() {
        DeletePage deletePage = PageFactory.initElements(driver, DeletePage.class);
        ManageUsersPage manageUsersPage = PageFactory.initElements(driver, ManageUsersPage.class);

        deletePage.clickOnButtonYes();
        Assert.assertFalse(manageUsersPage.userCreated(), "User wasn't delete");
        Assert.assertFalse(manageUsersPage.adminDeleteLinkExists(), "Admin delete link exists");
    }


}
